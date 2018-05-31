package com.hc.entity;

import java.util.Date;

/**
 *
 * @author yusj
 * @date 2018/5/17
 */
public class RckTaskDetailEntity {
    /**id*/
    private java.lang.String id;
    /**cid*/
    private java.lang.Long cid;
    /**taskId*/
    private java.lang.String taskId;
    /**inputfile*/
    private java.lang.String inputfile;
    /**outputfile*/
    private java.lang.String outputfile;
    /**endtime*/
    private java.util.Date endtime;
    /**savetype*/
    private java.lang.String savetype;
    /**savedist*/
    private java.lang.String savedist;
    /**dataurl*/
    private java.lang.String dataurl;
    /**note*/
    private java.lang.String note;

    private java.lang.String ports;
    private java.lang.String hosts;


    /**status*/
    private java.lang.String status;
    /**检测类型*/
    private java.lang.String checkType;

    private java.lang.String isrecord;
    private java.lang.String total;
    private java.lang.String up;
    private java.lang.String down;
    private java.lang.String recordnum;
    private java.lang.String opennum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getSavetype() {
        return savetype;
    }

    public void setSavetype(String savetype) {
        this.savetype = savetype;
    }

    public String getSavedist() {
        return savedist;
    }

    public void setSavedist(String savedist) {
        this.savedist = savedist;
    }

    public String getDataurl() {
        return dataurl;
    }

    public void setDataurl(String dataurl) {
        this.dataurl = dataurl;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPorts() {
        return ports;
    }

    public void setPorts(String ports) {
        this.ports = ports;
    }

    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getIsrecord() {
        return isrecord;
    }

    public void setIsrecord(String isrecord) {
        this.isrecord = isrecord;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getDown() {
        return down;
    }

    public void setDown(String down) {
        this.down = down;
    }

    public String getRecordnum() {
        return recordnum;
    }

    public void setRecordnum(String recordnum) {
        this.recordnum = recordnum;
    }

    public String getOpennum() {
        return opennum;
    }

    public void setOpennum(String opennum) {
        this.opennum = opennum;
    }
}
