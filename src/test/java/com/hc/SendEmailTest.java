package com.hc;

import com.hc.mapper.TSSmsMapper;
import com.hc.service.ScanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by yusj on 2018/5/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SendEmailTest {
    @Resource
    TSSmsMapper  smsMapper;

    @Resource
    ScanService  scanService;

    @Test
    public void sendTest(){
        scanService.sendEmail("805958658@qq.com","12345");
    }
}
