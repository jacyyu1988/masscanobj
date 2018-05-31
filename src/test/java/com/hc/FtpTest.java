package com.hc;

import com.hc.comm.ComProperties;
import com.hc.comm.Ftp;
import com.hc.comm.SFtp;
import com.jcraft.jsch.ChannelSftp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by yusj on 2018/5/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FtpTest {

    @Resource
    ComProperties pro;
    @Test
    public void test() throws Exception {
        File file=new File("C:\\Users\\yusj\\Desktop\\test.txt");
        FileInputStream input=new FileInputStream(file);

        SFtp sftp=new SFtp();
        sftp.getChannel("192.168.146.31","22","root","123456")
                .put("C:\\Users\\yusj\\Desktop\\test.txt","/home/yusj/download/work/test.txt", ChannelSftp.OVERWRITE);

        sftp.closeChannel();
    }
}