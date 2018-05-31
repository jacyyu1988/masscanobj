package com.hc.entity;

import java.util.Date;

/**
 * 检测信息表
 * @author yusj
 * @date 2018/5/17
 */
public class RckTaskInfoEntity {
    /**id*/
    private java.lang.String id;
    /**任务号*/
    private java.lang.String taskId;
    /**输入类型*/
    private java.lang.String inputType;
    /**输出类型*/
    private java.lang.String outputType;
    /**hosts*/
    private java.lang.String hosts;
    /**端口*/
    private java.lang.String ports;
    /**输入文件*/
    private java.lang.String inputfile;
    /**输出文件*/
    private java.lang.String outputfile;
    /**检测类型*/
    private java.lang.String checkType;
    /**是否检测备案信息*/
    private java.lang.String isckrecord;
    /**command*/
    private java.lang.String command;
    /**checkuserid*/
    private java.lang.String checkuserid;
    /**addtime*/
    private java.util.Date addtime;
    /**status*/
    private java.lang.String status;
    /**overtime*/
    private java.util.Date overtime;
    /**优先ping*/
    private java.lang.String isping;
    /**定时任务命令*/
    private java.lang.String ctimestr;
    /**超时时间*/
    private java.lang.Integer outtime;
    /**线程数*/
    private java.lang.Integer hostgroup;
    /**errormsg*/
    private java.lang.String errormsg;
    /**recorddatatype*/
    private java.lang.String recorddatatype;
    /**recorddatano*/
    private java.lang.String recorddatano;
    /**errorcode*/
    private java.lang.String errorcode;
    private String sendEmail;
    private String email;
    /*是否定时执行*/
    private String isDs;
    /**结果记录表*/
    private String rtable;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getOutputType() {
        return outputType;
    }

    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }

    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public String getPorts() {
        return ports;
    }

    public void setPorts(String ports) {
        this.ports = ports;
    }

    public String getInputfile() {
        return inputfile;
    }

    public void setInputfile(String inputfile) {
        this.inputfile = inputfile;
    }

    public String getOutputfile() {
        return outputfile;
    }

    public void setOutputfile(String outputfile) {
        this.outputfile = outputfile;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getIsckrecord() {
        return isckrecord;
    }

    public void setIsckrecord(String isckrecord) {
        this.isckrecord = isckrecord;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCheckuserid() {
        return checkuserid;
    }

    public void setCheckuserid(String checkuserid) {
        this.checkuserid = checkuserid;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getOvertime() {
        return overtime;
    }

    public void setOvertime(Date overtime) {
        this.overtime = overtime;
    }

    public String getIsping() {
        return isping;
    }

    public void setIsping(String isping) {
        this.isping = isping;
    }

    public String getCtimestr() {
        return ctimestr;
    }

    public void setCtimestr(String ctimestr) {
        this.ctimestr = ctimestr;
    }

    public Integer getOuttime() {
        return outtime;
    }

    public void setOuttime(Integer outtime) {
        this.outtime = outtime;
    }

    public Integer getHostgroup() {
        return hostgroup;
    }

    public void setHostgroup(Integer hostgroup) {
        this.hostgroup = hostgroup;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

    public String getRecorddatatype() {
        return recorddatatype;
    }

    public void setRecorddatatype(String recorddatatype) {
        this.recorddatatype = recorddatatype;
    }

    public String getRecorddatano() {
        return recorddatano;
    }

    public void setRecorddatano(String recorddatano) {
        this.recorddatano = recorddatano;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public String getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(String sendEmail) {
        this.sendEmail = sendEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsDs() {
        return isDs;
    }

    public void setIsDs(String isDs) {
        this.isDs = isDs;
    }

    public String getRtable() {
        return rtable;
    }

    public void setRtable(String rtable) {
        this.rtable = rtable;
    }
}
