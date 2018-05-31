package com.hc;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yusj on 2018/5/15.
 */
@SpringBootTest
public class ScanInterTest {
    @Test
    public void testInter(){
        RestTemplate  client=new RestTemplate();


        JSONObject json=new JSONObject();
        json.put("taskId","201805141526264509273");
        json.put("command","masscan 192.168.146.20  -p22");

        Map<String,Object> params=new HashMap<String,Object>();

        JSONObject json1=new JSONObject();
        json1.put("outputfile","/home/yusj/outfile/out/masstestout.cvs");
        //json1.put("IP","192.168.146.20");
        json.put("params",json1);



        MultiValueMap<String, String> input= new LinkedMultiValueMap<String, String>();
        input.add("param", json.toJSONString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(input, headers);

        String url="http://192.168.146.31:8088/masscan";

        ResponseEntity<String> response = client.exchange(url, HttpMethod.POST, requestEntity, String.class);

        System.out.println(response.getBody());

    }
}
