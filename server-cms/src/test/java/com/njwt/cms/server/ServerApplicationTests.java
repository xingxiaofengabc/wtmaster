package com.njwt.cms.server;

import com.njwt.cms.entity.UserEntity;
import com.njwt.cms.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {

    @Resource
	private UserServiceImpl userServiceImpl;
	@Test
	public void testSetObject(){
		String key = "user";
		UserEntity userEntity=new UserEntity();
		userEntity.setCode(1);
		userEntity.setName("张三三");
		userEntity.setAge(20);
		userEntity.setStatus(1);
		this.userServiceImpl.set(key, userEntity);
	}


	@Test
	public void testGetObject(){
		String key = "user";
		Object value = this.userServiceImpl.get(key);
		System.out.println(value);
		System.out.println(value.getClass().getName());
	}

}
