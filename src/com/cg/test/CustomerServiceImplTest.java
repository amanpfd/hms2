package com.cg.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.bean.Hotel;
import com.cg.bean.User;
import com.cg.exception.HotelNotFoundException;
import com.cg.exception.UserNotFoundException;
import com.cg.service.CustomerService;
import com.cg.service.CustomerServiceImpl;

public class CustomerServiceImplTest {
	
	CustomerService service;
	
	@Before
	public void setUp() {
		service=new CustomerServiceImpl();
	}

	@After
	public void tearDown() {
		service=null;
	}

	@Test
	public void testAddUser() throws UserNotFoundException {
		User u= new User("abc", "abcdef", "user", "9876543210", "7845127845", "Mumbai", "abc@def.com");
		int id=service.addUser(u);
		System.out.println(id);
	}

	@Test
	public void testFindHotelByCity() throws HotelNotFoundException {
		List<Hotel> hotels = service.findHotelByCity("Kolkata", 0, 10000);
		assertNotNull(hotels);
	}

	@Test
	public void testBookRoomBooking() {
		fail("Not yet implemented");
	}

	@Test
	public void testViewStatus() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsRoomAvailable() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidateLogin() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindHotelByName() {
		fail("Not yet implemented");
	}

	@Test
	public void testAssignRoomFromType() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateAmount() {
		fail("Not yet implemented");
	}

	@Test
	public void testBookRoomUserHotelRoomLocalDateLocalDateIntIntDouble() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUser() {
		fail("Not yet implemented");
	}

}
