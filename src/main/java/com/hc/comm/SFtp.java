package com.hc.comm;

import com.jcraft.jsch.*;
import org.slf4j.*;
import org.slf4j.Logger;

import java.util.Properties;

/**
 * sFtp
 * @author yusj
 * @date 2018/5/21
 */
public class SFtp {

    Logger logger= LoggerFactory.getLogger(this.getClass());

    private Channel channel=null;
    private Session  session=null;

    public ChannelSftp getChannel(String host, String port,
                                  String user, String pass) throws JSchException {


        String ftpHost = host;
        int ftpPort = Integer.parseInt(port);
        String ftpUserName = user;
        String ftpPassword = pass;

        // 创建JSch对象
        JSch jsch = new JSch();
        // 根据用户名，主机ip，端口获取一个Session对象
        session = jsch.getSession(ftpUserName, ftpHost, ftpPort);

        //logger.info("Session created.");
        if (ftpPassword != null) {
            // 设置密码
            session.setPassword(ftpPassword);
        }

        Properties configTemp = new Properties();
        configTemp.put("StrictHostKeyChecking", "no");
        // 为Session对象设置properties
        session.setConfig(configTemp);
        // 设置timeout时间
        session.setTimeout(60000 );
        session.connect();

        // 通过Session建立链接
        // 打开SFTP通道
        channel = session.openChannel("sftp");
        // 建立SFTP通道的连接
        channel.connect();
        logger.info("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName + ", returning: " + channel);
        return (ChannelSftp) channel;
    }

    /**
     * 断开SFTP Channel、Session连接
     * @throws Exception
     */
    public void closeChannel() throws Exception {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
        logger.info("disconnected SFTP successfully!");
    }





}
