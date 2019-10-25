package com.cg.dao;

import java.time.LocalDate;
import java.util.List;

import com.cg.bean.Booking;
import com.cg.bean.Hotel;
import com.cg.bean.Room;
import com.cg.bean.User;
import com.cg.exception.UserNotFoundException;

public interface AdminDao {
	public int addHotel(Hotel hotel) throws Exception;
	public int deleteHotel(int hotelId) throws Exception;
	public int modifyHotel(int hotelId);

	public int addRoom(Room room) throws Exception;
	public int deleteRoom(int roomId) throws Exception;
	public int modifyRoom(int roomId);

	public List<Hotel> listHotels() throws Exception;
	public List<Booking> viewBookings(int hotelId) throws Exception;
	public List<User> viewGuestList(int hotelId) throws Exception; 
	public List<Booking> bookingByDate(LocalDate date);

	public boolean validateLogin(int userId, String password) throws UserNotFoundException;
}
