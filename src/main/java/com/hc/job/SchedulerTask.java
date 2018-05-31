package com.hc.job;

import com.hc.service.RecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 定时任务
 * @author yusj
 * @date 2018/5/17
 */
@Component
public class SchedulerTask {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private RecordService  service;

    /**
     * 每隔两个小时刷新一次缓存
     */
    @Scheduled(cron="0 0/2 * * * ?")
    private void refreshEhcache(){
        logger.info("-----begin refresh ehcache-----");
        service.refreshEhcache();
        logger.info("-----end refresh ehcache-----");
    }
}
