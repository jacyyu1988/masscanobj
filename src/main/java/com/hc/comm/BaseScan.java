package com.hc.comm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.*;

/**
 *
 * @author yusj
 * @date 2018/5/15
 */
@Component
public class BaseScan {
    //private static ExecutorService pool = Executors.newFixedThreadPool(30);
    @Resource
    private ComProperties  pro;

    static Logger  logger= LoggerFactory.getLogger(BaseScan.class);

    private static ThreadPoolExecutor  pool=new ThreadPoolExecutor(
            10,30,3,
            TimeUnit.MINUTES,
            new ArrayBlockingQueue(30)
    );


    /**
     *
     * @param path
     * @param command  masscan hosts -p[ports]
     * @param taskId
     * @param params
     * @param callback
     */
    public synchronized  void doScan(String path,String command,String taskId,
                              Map<String,Object> params,IMasscanCallback callback){

        StringBuilder  sbu=new StringBuilder(command);
        sbu.append(" --banners ")
                .append(" --rate ").append(pro.getMasscanRate())
                .append(" -oX ").append(pro.getTmpFilePath())
                .append(taskId).append(".xml");
        logger.info("begin scan command |"+sbu.toString());
        pool.submit(Masscan.create(path,sbu.toString(),taskId,params,callback));
    }

    public static ExecutorService getPool(){
        return pool;
    }

}
