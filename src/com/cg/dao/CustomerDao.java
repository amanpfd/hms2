package com.cg.dao;

import java.time.LocalDate;
import java.util.List;

import com.cg.bean.Booking;
import com.cg.bean.Hotel;
import com.cg.bean.Room;
import com.cg.bean.User;
import com.cg.exception.BookingNotFoundException;
import com.cg.exception.HotelNotFoundException;
import com.cg.exception.RoomNotFoundException;
import com.cg.exception.UserNotFoundException;

public interface CustomerDao {
	
	int saveUser(User user) throws UserNotFoundException;
	List<Hotel> searchHotelByCity(String city, double minPrice, double maxPrice ) throws HotelNotFoundException;
	List<Room> searchRoom(int hotelId) throws RoomNotFoundException;
	Booking bookRoom(Booking booking) throws Exception;
	List<Booking> viewStatus(int userId) throws BookingNotFoundException;
	List<LocalDate> getBookdDates(int roomId) throws BookingNotFoundException;
	public boolean validateLogin(String username, String password) throws UserNotFoundException;
	List<Hotel> searchHotelByName(String hotelName) throws HotelNotFoundException;
	List<String> getRoomTypes(int hotelId);
	Room assignRoom(String selectedType, int hotelId);
	User getUser(String username, String password);
}
