package com.hc.service;

import com.hc.comm.EhcacheUtil;
import com.hc.mapper.Record;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 备案信息service
 * @author yusj
 * @date 2018/5/17
 */
@Service("recordService")
public class RecordService {
    @Resource
    private Record record;

    public Set<String> getIps(){
        //get IPs from cache or database
        Set<String> list=(Set<String>)EhcacheUtil.
                get(EhcacheUtil.RECORD_CACHE,EhcacheUtil.RECORD_CACHE_IP);

        if(list==null){
            refreshEhcache();
            list=(Set<String>)EhcacheUtil.
                    get(EhcacheUtil.RECORD_CACHE,EhcacheUtil.RECORD_CACHE_IP);
        }

        return list;
    }


    public Map<String,Map<String,Object>> getDomains(){
        //get IPs from cache or database
        Map<String,Map<String,Object>> list=(Map<String,Map<String,Object>>)EhcacheUtil.
                get(EhcacheUtil.RECORD_CACHE,EhcacheUtil.RECORD_CACHE_IP);

        if(list==null){
            refreshEhcache();
            list=(Map<String,Map<String,Object>>)EhcacheUtil.
                    get(EhcacheUtil.RECORD_CACHE,EhcacheUtil.RECORD_CACHE_IP);
        }

        return list;
    }

    public void refreshEhcache(){

        EhcacheUtil.clean();
        //重新添加
        List<Map<String,Object>>  list=record.all();
        Set<String> ip=new HashSet();
        Map<String,Map<String,Object>> domain=new HashMap<>();
        if(list!=null){
            for(Map<String,Object> map:list){
                domain.put(map.get("domain").toString(),map);
                String ips[]=map.get("ips").toString().split(";");
                Collections.addAll(ip,ips);
            }
        }

        EhcacheUtil.put(EhcacheUtil.RECORD_CACHE,EhcacheUtil.RECORD_CACHE_IP,ip);
        EhcacheUtil.put(EhcacheUtil.RECORD_CACHE,EhcacheUtil.RECORD_CACHE_DOMAIN,domain);

    }


    //定时刷新



}
