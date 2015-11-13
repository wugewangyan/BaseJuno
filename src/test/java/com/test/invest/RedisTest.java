package com.test.invest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.juno.bean.Config;
import com.juno.dao.CustomDao;
import com.juno.model.Custom;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring/spring-*.xml")
public class RedisTest {

	@Autowired
	private CustomDao customDao;
	
	@Autowired
	private Config config;
	
	@Test
	public void addTest(){
		String codeBase = config.getCodeBase();
		String redisIp = config.getRedisIp();
		Custom custom = new Custom();
		custom.setCustomId("custom001");
		custom.setCustomName("yixin");
		customDao.save(custom);
	}
	
	@Test
	public void getTest(){
		Custom custom = customDao.get("custom001");
		System.out.println(custom.getCustomId());
		System.out.println(custom.getCustomName());
	}
	
	@Test
	public void deleteTest(){
		this.customDao.delete("custom001");
	}
	
}
