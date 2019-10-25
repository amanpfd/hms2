package com.cg.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.bean.Booking;
import com.cg.bean.Hotel;
import com.cg.bean.Room;
import com.cg.bean.User;
import com.cg.exception.UserNotFoundException;

public interface AdminService {

	String usernameRule = "^[a-z0-9_-]{3,15}$";

	String passwordRule = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

	String hotelnameRule = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$";
	String cityRule = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$";

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

	public boolean validateLogin(String username, String password);

	default boolean validateUsername(String username) {
		return username.matches(usernameRule);
	}

	default boolean validatePassword(String password) {
		return password.matches(passwordRule);
	}

	default boolean validateHotelName(String hotelName) {
		return hotelName.matches(hotelnameRule);
	}
	
	default boolean validateCity(String city) {
		return city.matches(cityRule); 
	}
}
