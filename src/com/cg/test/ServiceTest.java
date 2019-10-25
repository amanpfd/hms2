package com.cg.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.bean.User;
import com.cg.exception.UserNotFoundException;
import com.cg.service.CustomerService;
import com.cg.service.CustomerServiceImpl;

public class ServiceTest {
	
	CustomerService service;
	
	@Before
	public void init() {
		service=new CustomerServiceImpl();
	}
	
	@After
	public void destroy() {
		service=null;
	}
	
	@Test
	public void testRegister() throws UserNotFoundException {
		User u= new User("abc", "abcdef", "user", "9876543210", "7845127845", "Mumbai", "abc@def.com");
		int id=service.addUser(u);
		System.out.println(id);
	}
	
	
}
