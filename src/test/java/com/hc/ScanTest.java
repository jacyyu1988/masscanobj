package com.hc;


import com.hc.web.ScanController;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by yusj on 2018/5/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScanTest {


    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc= MockMvcBuilders.standaloneSetup(new ScanController()).build();
    }

    @Test
    public void done() throws Exception {

        JSONObject  json=new JSONObject();
        json.put("taskId","201805141526264509273");
        json.put("command","masscan  192.168.1.1-192.168.1.3  -p19-26,3306,1521,8080-9090 ");

        Map<String,Object> params=new HashMap<String,Object>();

        JSONObject  json1=new JSONObject();
        json1.put("outputfile","masstestout.cvs");

        json.put("params",json1);


        System.out.println("-----input|"+json.toString());

        mockMvc.perform(MockMvcRequestBuilders.post("/masscan")
                .param("param",json.toString())
                .accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
    }
}
