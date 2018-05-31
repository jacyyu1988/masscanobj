package com.hc;

import com.hc.entity.RckTaskDetailEntity;
import com.hc.entity.RckTaskInfoEntity;
import com.hc.mapper.RckTaskDetail;
import com.hc.mapper.RckTaskInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yusj on 2018/5/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisTest {
    @Resource
    RckTaskDetail  taskDetail;
    @Resource
    RckTaskInfo taskinfo;

    @Test
    public void test(){
        RckTaskInfoEntity entity=taskinfo.one("201804201524202682986");
        System.out.println("---before update|"+entity.getStatus());

        entity.setStatus("0001");

        int i=taskinfo.update(entity);

        System.out.println("---update done result is |"+i);

        RckTaskInfoEntity entity1=taskinfo.one("201804201524202682986");
        System.out.println("---after update|"+entity1.getStatus());


        //insert
        long cid=System.currentTimeMillis();
        System.out.println("----add detail cid is |"+cid);

        RckTaskDetailEntity  detail=new RckTaskDetailEntity();
        detail.setCid(cid);
        detail.setCheckType(entity1.getCheckType());
        detail.setHosts(entity1.getHosts());
        detail.setPorts(entity1.getPorts());
        detail.setInputfile(entity1.getInputfile());
        detail.setIsrecord(entity1.getIsckrecord());

        //rest 接口入参中获取
        detail.setOutputfile(entity1.getOutputfile());
        detail.setStatus("O");
        detail.setSavetype("file");
        detail.setSavedist(entity1.getOutputfile());
        //file download path
        detail.setDataurl("");
        detail.setTaskId(entity1.getTaskId());
        detail.setTotal("0");
        detail.setUp("0");
        detail.setDown("0");
        detail.setRecordnum("0");
        detail.setOpennum("0");


        int j=taskDetail.insert(detail);

    }
}
