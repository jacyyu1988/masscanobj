package com.hc.comm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * masscan  检测类
 * @author yusj
 * @date 2018/5/15
 */
public class Masscan implements Runnable{

    Logger logger= LoggerFactory.getLogger(this.getClass());

    private final String path;
    private final String command;
    private final String taskId;
    private final Map<String,Object> params;
    private final IMasscanCallback callback;

    public Masscan(String path,String command,String taskId,Map<String,Object> params,
                   IMasscanCallback callback){
        this.path=path;
        this.command=command;
        this.taskId=taskId;
        this.params=params;
        this.callback=callback;
    }


    public static Masscan create(String path,String command,
                                 String taskId,Map<String,Object> params,IMasscanCallback callback){
        return  new Masscan(path,command,taskId,params,callback);
    }

    /**
     *
     * @return ExecutionResults
     */
    private synchronized ExecutionResults execute() throws  Exception {

        //等待2秒再执行避免太快锁表
        this.wait(2000);

        ExecutionResults results = new ExecutionResults() ;
        try {

            logger.info("----exec command----|"+path+command+" taskId|"+taskId);
            Process process = Runtime.getRuntime().exec( path+command ) ;

            RetThread outputThread = new RetThread(process.getInputStream());
            RetThread errorOutputThread = new RetThread(process.getErrorStream());

            outputThread.start();
            errorOutputThread.start();

            int rs=process.waitFor();

            outputThread.join();
            errorOutputThread.join();

            results.setExecutedCommand( command ) ;
            results.setErrors(errorOutputThread.getOutPut());
            results.setOutput(outputThread.getOutPut());
            results.setTaskId(taskId);
            results.setParams(params);

        } catch ( IOException e ) {
            logger.error("----IOException---|"+e);
            results.setExecutedCommand( command ) ;
            results.setTaskId(taskId);
            results.setParams(params);
            results.setErrors(e.getMessage());

        } catch (InterruptedException e) {
            logger.error("----InterruptedException---|"+e);
            results.setExecutedCommand( command ) ;
            results.setTaskId(taskId);
            results.setParams(params);
            results.setErrors(e.getMessage());
        }

        logger.info("----exec command----|"+path+command+" over---");
        return results ;
    }


    @Override
    public void run() {
        try {
            this.callback.callback(execute());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
