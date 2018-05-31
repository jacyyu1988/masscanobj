package com.hc.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hc.comm.BaseScan;
import com.hc.comm.ComProperties;
import com.hc.entity.RckTaskInfoEntity;
import com.hc.mapper.RckTaskInfo;
import com.hc.service.RecordService;
import com.hc.service.ScanService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 *
 * @author yusj
 * @date 2018/5/15
 */
@RestController
public class ScanController {

    @Resource
    private ComProperties  pro;

    @Resource
    private RecordService  recordService;

    @Resource
    private ScanService scanService;

    @Resource
    private BaseScan baseScan;

    @Resource
    private RckTaskInfo  taskInfo;

    /**
     *
     * @param param
     * @return
     */
    @RequestMapping(value="/masscan",method = RequestMethod.POST)
    public String scan(String param){
        JSONObject  t=new JSONObject();

        JSONObject   obj=(JSONObject)JSON.parse(param);
        if(obj==null){
            t.put("code","0001");
            t.put("msg","In put param is null");
            return t.toJSONString();
        }

        String command=obj.get("command").toString();
        if(command==null){
            t.put("code","0002");
            t.put("msg","In put command is null");
            return t.toJSONString();
        }

        String taskId=obj.get("taskId").toString();
        if(command==null){
            t.put("code","0003");
            t.put("msg","In put taskId is null");
            return t.toJSONString();
        }

        JSONObject  params=(JSONObject)JSON.parse(obj.get("params").toString());
        if(params==null){
            t.put("code","0003");
            t.put("msg","In put taskId is null");
            return t.toJSONString();
        }

        Map<String,Object> map=params.getInnerMap();
        if(map.get("outputfile")==null){
            t.put("msg","outputfile  is null");
            return t.toJSONString();
        }

        // do scan...
        baseScan.doScan(pro.getMasscanPath(),command,taskId,map,scanService);

        /*RckTaskInfoEntity  entity=new RckTaskInfoEntity();
        entity.setTaskId(taskId);
        entity.setStatus("R");
        taskInfo.update(entity);*/
        

        t.put("code","0000");
        t.put("msg","submit success! please waitting for return!");
        return t.toJSONString();

    }


    @RequestMapping(value="/refreshEhcache",method = RequestMethod.POST)
    public String refreshEhcache(){
        recordService.refreshEhcache();
        return "sucess";
    }

}
