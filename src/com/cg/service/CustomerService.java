package com.cg.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.bean.Booking;
import com.cg.bean.Hotel;
import com.cg.bean.Room;
import com.cg.bean.User;
import com.cg.exception.BookingNotFoundException;
import com.cg.exception.HotelNotFoundException;
import com.cg.exception.UserNotFoundException;

public interface CustomerService {

	String usernameRule = "^[a-z0-9_-]{3,15}$";

	String passwordRule = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

	String mobilenoRule = "[7-9][0-9]{9}";

	String emailRule = "^[A-Za-z0-9+_.-]+@(.+)$";

	String cityRule = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$";

	String hotelnameRule = "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$";

	int addUser(User user) throws UserNotFoundException;

	List<Hotel> findHotelByCity(String city, double minPrice, double maxPrice) throws HotelNotFoundException;

	Booking bookRoom(Booking booking) throws Exception;

	List<Booking> viewStatus(String username) throws BookingNotFoundException;

	boolean isRoomAvailable(LocalDate checkIn, LocalDate checkOut, int roomId) throws BookingNotFoundException;

	public boolean validateLogin(String username, String password) throws UserNotFoundException;

	List<Hotel> findHotelByName(String hotelName) throws HotelNotFoundException;

	boolean validateRole(String role);

	boolean validateUserId(int userId);

	boolean validatehotelId(int hotelId);

	boolean validateNumber(int select, int i);

	Room assignRoomFromType(String selectedType, Hotel hotel);

	double calculateAmount(Room room, LocalDate bookedFrom, LocalDate bookedTo);

	Booking bookRoom(User user, Hotel hotel, Room room, LocalDate bookedFrom, LocalDate bookedTo, int adults,
			int children, double amount) throws Exception;

	User getUser(String username, String password);

	List<String> getRoomTypes(int hotelId);
	
	default boolean validateUsername(String username) {
		return username.matches(usernameRule);
	}

	default boolean validatePassword(String password) {
		return password.matches(passwordRule);
	}

	default	boolean validateMobileNo(String mobileNo) {
		return mobileNo.matches(mobilenoRule);
	}

	default boolean validateEmail(String email) {
		return email.matches(emailRule);
	}

	default boolean validateCity(String city) {
		return city.matches(cityRule); 
	}

	default boolean validateHotelName(String hotelName) {
		return hotelName.matches(hotelnameRule);
	}

}
