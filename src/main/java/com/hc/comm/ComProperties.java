package com.hc.comm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 属性类
 * @author yusj
 * @date 2018/5/16
 */
@Component
public class ComProperties {
    @Value("${masscan.path}")
    private String masscanPath;

    @Value("${masscan.output.path}")
    private String tmpFilePath;

    @Value("${masscan.output.path.out}")
    private String outFilePath;

    @Value("${masscan.command.rate}")
    private String masscanRate;

    @Value("${masscan.ftptype}")
    private String ftpType;
    @Value("${masscan.ftp.host}")
    private String ftpHost;
    @Value("${masscan.ftp.user}")
    private String ftpUser;
    @Value("${masscan.ftp.pass}")
    private String ftpPass;
    @Value("${masscan.ftp.port}")
    private String ftpPort;

    public  String getMasscanPath() {
        return this.masscanPath;
    }

    public  void setMasscanPath(String masscanPath) {
        this.masscanPath = masscanPath;
    }

    public String getTmpFilePath() {
        return tmpFilePath;
    }

    public void setTmpFilePath(String tmpFilePath) {
        this.tmpFilePath = tmpFilePath;
    }

    public String getOutFilePath() {
        return outFilePath;
    }

    public void setOutFilePath(String outFilePath) {
        this.outFilePath = outFilePath;
    }

    public String getMasscanRate() {
        return masscanRate;
    }

    public void setMasscanRate(String masscanRate) {
        this.masscanRate = masscanRate;
    }

    public FtpType getFtpType() {
        return FtpType.valueOf(ftpType);
    }

    public void setFtpType(String ftpType) {
        this.ftpType = ftpType;
    }

    public String getFtpHost() {
        return ftpHost;
    }

    public void setFtpHost(String ftpHost) {
        this.ftpHost = ftpHost;
    }

    public String getFtpUser() {
        return ftpUser;
    }

    public void setFtpUser(String ftpUser) {
        this.ftpUser = ftpUser;
    }

    public String getFtpPass() {
        return ftpPass;
    }

    public void setFtpPass(String ftpPass) {
        this.ftpPass = ftpPass;
    }

    public String getFtpPort() {
        return ftpPort;
    }

    public void setFtpPort(String ftpPort) {
        this.ftpPort = ftpPort;
    }
}
