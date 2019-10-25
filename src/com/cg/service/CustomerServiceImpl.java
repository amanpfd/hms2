package com.cg.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cg.bean.Booking;
import com.cg.bean.Hotel;
import com.cg.bean.Room;
import com.cg.bean.User;
import com.cg.dao.CustomerDao;
import com.cg.dao.CustomerDaoImpl;
import com.cg.exception.BookingNotFoundException;
import com.cg.exception.HotelNotFoundException;
import com.cg.exception.UserNotFoundException;

public class CustomerServiceImpl implements CustomerService {

	CustomerDao cdao = new CustomerDaoImpl();

	@Override
	public int addUser(User user) throws UserNotFoundException {
		return cdao.saveUser(user);
	}

	@Override
	public List<Hotel> findHotelByCity(String city, double minPrice, double maxPrice) throws HotelNotFoundException {
		return cdao.searchHotelByCity(city, minPrice, maxPrice);
	}

	@Override
	public Booking bookRoom(Booking booking) throws Exception {
		return cdao.bookRoom(booking);
	}

	@Override
	public List<Booking> viewStatus(String username) throws BookingNotFoundException {
		return null;
//		return cdao.viewStatus(userId);
	}

	@Override
	public boolean isRoomAvailable(LocalDate checkIn, LocalDate checkOut, int roomId) throws BookingNotFoundException {
		List<LocalDate> dates = cdao.getBookdDates(roomId);
		if (((checkIn.isAfter(dates.get(0))) && (checkIn.isBefore(dates.get(1)))
				|| (checkOut.isAfter(dates.get(0))) && (checkOut.isBefore(dates.get(1))))
				|| (checkIn.isBefore(dates.get(0)) && checkOut.isAfter(dates.get(1))))
			return false;
		return true;
	}

	@Override
	public boolean validateLogin(String userId, String password){
		try {
			boolean vl = cdao.validateLogin(userId, password);
			return vl;
		} catch (UserNotFoundException e) {
			return false;
		}
	}

	@Override
	public List<Hotel> findHotelByName(String hotelName) throws HotelNotFoundException {
		return cdao.searchHotelByName(hotelName);
	}

	@Override
	public boolean validateNumber(int select, int i) {
		if (select >= 0 && select <= i)
			return true;
		else
			return false;
	}

	@Override
	public Room assignRoomFromType(String selectedType, Hotel hotel) {
		return cdao.assignRoom(selectedType, hotel.getHotelId());
	}

	@Override
	public double calculateAmount(Room room, LocalDate bookedFrom, LocalDate bookedTo) {
		long intervalDays = ChronoUnit.DAYS.between(bookedFrom, bookedTo);
		return room.getRatePerNight() * intervalDays;
	}

	@Override
	public Booking bookRoom(User user, Hotel hotel, Room room, LocalDate bookedFrom, LocalDate bookedTo, int adults,
			int children, double amount) throws Exception {
		Booking booking = new Booking();
		booking.setAdults(adults);
		booking.setChildren(children);
		booking.setAmount(amount);
		booking.setBookedFrom(bookedFrom);
		booking.setBookedTo(bookedTo);
		booking.setHotelId(hotel.getHotelId());
		booking.setRoomId(room.getRoomId());
		booking.setUserId(user.getUserId());
		Booking booked = cdao.bookRoom(booking);
		return booked;
	}

	@Override
	public User getUser(String username, String password) {
		return cdao.getUser(username, password);
	}

	@Override
	public List<String> getRoomTypes(int hotelId) {
		List<String> type = cdao.getRoomTypes(hotelId);
		List<String> roomTypes = new ArrayList<String>();
		Set<String> hSet = new HashSet<String>();
		for (String x : type)
			hSet.add(x);
		for (String x : hSet)
			roomTypes.add(x);
		return roomTypes;
	}

	
	@Override
	public boolean validateUserId(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validatehotelId(int hotelId) {
		// TODO Auto-generated method stub
		return false;
	}


}
