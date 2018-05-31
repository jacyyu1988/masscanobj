package com.hc;

import com.hc.comm.ComProperties;
import com.hc.comm.EhcacheUtil;
import com.hc.mapper.User;
import com.hc.service.RecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MasscanApplicationTests {

	@Resource
	ComProperties  pro;

	@Resource
	User  userMapper;

	@Resource
	RecordService  recordService;

	@Test
	public void contextLoads() {
		Set set=recordService.getIps();
		System.out.println("--------------"+set.size());

	}




}
