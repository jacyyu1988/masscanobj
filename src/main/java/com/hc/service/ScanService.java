package com.hc.service;


import com.hc.comm.*;
import com.hc.entity.RckTaskDetailEntity;
import com.hc.entity.RckTaskInfoEntity;
import com.hc.entity.TSSmsEntity;
import com.hc.mapper.RckTaskDetail;
import com.hc.mapper.RckTaskInfo;
import com.hc.mapper.TSSmsMapper;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author yusj
 * @date 2018/5/15
 */
@Service("scanService")
public class ScanService implements IMasscanCallback {

    Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    private RckTaskInfo  taskInfo;

    @Resource
    private RckTaskDetail taskDetail;

    @Resource
    private RecordService recordService;

    @Resource
    private ComProperties  pro;

    @Resource
    private TSSmsMapper  smsMapper;

    @Override
    public synchronized void callback(ExecutionResults results) {

        logger.info("----callback begin taskId|"+results.getTaskId());

        //处理结果
        String taskId=results.getTaskId();
        RckTaskInfoEntity taskEntity=taskInfo.one(taskId);
        taskEntity.setCommand(results.getExecutedCommand());

        //新增detail表数据
        RckTaskDetailEntity taskDetail = new RckTaskDetailEntity();
        taskDetail.setId(UUID.randomUUID().toString());
        taskDetail.setCid(System.currentTimeMillis());
        taskDetail.setTaskId(taskId);
        taskDetail.setInputfile(taskEntity.getInputfile());
        taskDetail.setPorts(taskEntity.getPorts());
        taskDetail.setHosts(taskEntity.getHosts());
        taskDetail.setCheckType(taskEntity.getCheckType());
        taskDetail.setIsrecord(taskEntity.getIsckrecord());


        //处理结果
        if (results.getErrors().contains("ERROR")||
                results.getErrors().contains("FAIL")) {
            logger.info("----is error return |"+results.getErrors());
            if(taskEntity!=null){
                taskEntity.setErrorcode("0001");
                taskEntity.setErrormsg(results.getErrors().substring(0,50));
                taskEntity.setStatus("E");

                taskDetail.setEndtime(new Date());
                taskDetail.setStatus("E");
                taskDetail.setNote(results.getErrors());

                this.taskDetail.insert(taskDetail);
                this.taskInfo.update(taskEntity);
            }

        }else{

            logger.info("----is normal return |"+results.getErrors().substring(0,100));
            //输出文件路径地址
            Map<String,Object> map=results.getParams();

            String outputfile=map.get("outputfile").toString();
            String tmpRet=pro.getTmpFilePath()+taskId+".xml";

            File file=new File(tmpRet);
            if(file.length()==0){
                logger.info("----no result |-----");
                taskEntity.setOvertime(new Date());
                taskEntity.setStatus("E");
                taskEntity.setErrorcode("0010");
                taskEntity.setErrormsg("no result");
                this.taskInfo.update(taskEntity);


                //no detail

            }else{
                String trueRet=pro.getOutFilePath()+taskId+".cvs";
                String outPath="";
                if(pro.getFtpType()==FtpType.local){
                    outPath=outputfile;
                }else{
                    outPath=trueRet;
                }

                //处理文件/生成cvs/ftp到调用主机
                try {

                    Set set=null;
                    if(taskEntity.getIsckrecord().equals("Y")){
                        set=recordService.getIps();
                    }

                    File out=new File(outPath);
                    FileWriter fw=new FileWriter(out);
                    String[] ret= XmlParseUtil.create(fw,recordService.getIps(),tmpRet,
                            new Hcounter(),new Hcounter()).parseXml();

                    //ftp  处理
                    if(pro.getFtpType()==FtpType.other){
                        logger.info("----do ftp |-----");
                        String ip= pro.getFtpHost();
                        if(!StringUtils.isEmpty(results.getParams().get("IP"))){
                            ip=results.getParams().get("IP").toString();
                        }
                        //回传结果文件
                        SFtp sftp=new SFtp();
                        sftp.getChannel(ip,pro.getFtpPort(),pro.getFtpUser(),pro.getFtpPass())
                                .put(outPath,outputfile, ChannelSftp.OVERWRITE);

                        sftp.closeChannel();
                    }

                    //更新taskinfo表状态
                    taskEntity.setOutputType("file");
                    taskEntity.setOutputfile(outputfile);
                    taskEntity.setOvertime(new Date());
                    taskEntity.setStatus("O");
                    taskEntity.setErrorcode("0000");
                    taskEntity.setErrormsg("success!");

                    taskDetail.setUp(ret[1]);
                    taskDetail.setDown(ret[2]);
                    taskDetail.setTotal(ret[3]);
                    taskDetail.setRecordnum(ret[4]);
                    taskDetail.setOpennum(ret[5]);
                    taskDetail.setEndtime(new Date());
                    taskDetail.setSavetype("file");
                    taskDetail.setOutputfile(outPath);
                    taskDetail.setSavetype("file");
                    taskDetail.setSavedist("");


                    this.taskDetail.insert(taskDetail);
                    this.taskInfo.update(taskEntity);

                    //发送邮件
                    if(taskEntity.getSendEmail().equals("Y")){
                        logger.info("----sending Email |-----");
                        sendEmail(taskEntity.getEmail(),taskId);
                    }

                } catch (DocumentException e) {
                    logger.error("---DocumentException----"+e);
                    taskEntity.setOvertime(new Date());
                    taskEntity.setStatus("E");
                    taskEntity.setErrorcode("0010");
                    taskEntity.setErrormsg("error");
                    this.taskInfo.update(taskEntity);

                } catch (IOException e) {
                    logger.error(results.getTaskId()+"---IOException----"+e);
                    taskEntity.setOvertime(new Date());
                    taskEntity.setStatus("E");
                    taskEntity.setErrorcode("0010");
                    taskEntity.setErrormsg("error");
                    this.taskInfo.update(taskEntity);
                } catch (JSchException e) {
                    logger.error(results.getTaskId()+"---JSchException----"+e);
                    taskEntity.setOvertime(new Date());
                    taskEntity.setStatus("E");
                    taskEntity.setErrorcode("0010");
                    taskEntity.setErrormsg("error");
                    this.taskInfo.update(taskEntity);
                } catch (SftpException e) {
                    logger.error(results.getTaskId()+"---SftpException----"+e);
                    taskEntity.setOvertime(new Date());
                    taskEntity.setStatus("E");
                    taskEntity.setErrorcode("0010");
                    taskEntity.setErrormsg("error");
                    this.taskInfo.update(taskEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(results.getTaskId()+"---Exception----"+e.getMessage());
                    taskEntity.setOvertime(new Date());
                    taskEntity.setStatus("E");
                    taskEntity.setErrorcode("0010");
                    taskEntity.setErrormsg("error");
                    this.taskInfo.update(taskEntity);
                }
            }

        }

        logger.info("----callback over |-----");

    }


    //发送邮件
    public  void sendEmail(String emailAddr,String taskId){
        if (emailAddr != null) {
            TSSmsEntity tsSmsEntity = new TSSmsEntity();
            tsSmsEntity.setCreateBy("system");
            tsSmsEntity.setRemark(taskId);
            tsSmsEntity.setEsContent("您的检测任务[" + taskId + "]已完成，请登陆系统查看");
            tsSmsEntity.setEsSender("system");
            tsSmsEntity.setEsReceiver(emailAddr);
            tsSmsEntity.setEsStatus("1");
            tsSmsEntity.setEsType("2");
            tsSmsEntity.setEsTitle("任务[" + taskId + "]已完成");
            tsSmsEntity.setTaskid(taskId);

            tsSmsEntity.setUpdateDate(new Date());
            smsMapper.insert(tsSmsEntity);
        }
    }


}
