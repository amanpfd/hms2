package com.cg.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.cg.bean.Hotel;
import com.cg.bean.Room;
import com.cg.dao.AdminDao;
import com.cg.dao.AdminDaoImpl;

import junit.framework.Assert;

public class DaoTest {
	
	AdminDao dao;
	
	@Before
	public void init() {
		dao = new AdminDaoImpl();
	}
	
	@After
	public void destroy() {
		dao = null;
	}
	
	@Test
	public void daoHotelTest() throws Exception {
		Hotel h = new Hotel("Kolkata","Regent1","Salt Lake","best in town","7894561122"
							,"7903430071",2000.0,"go","reghelp@gmail.com","89-654");
		dao.addHotel(h);
		//dao.deleteHotel(1005);
		//org.junit.Assert.assertNotNull(dao.listHotels().toString());
		
	}
	
	@Test
	public void daoRoomTest() throws Exception {
		Room r = new Room(1023,"204","AC",5223.5,1);
		dao.addRoom(r);
	}
}
