package com.cg.dao;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.cg.bean.Booking;
import com.cg.bean.Hotel;
import com.cg.bean.Room;
import com.cg.bean.User;
import com.cg.exception.BookingNotFoundException;
import com.cg.exception.HotelNotFoundException;
import com.cg.exception.RoomNotFoundException;
import com.cg.exception.UserNotFoundException;

public class CustomerDaoImpl implements CustomerDao{
	
	DBSetup db = new DBSetup();

	@Override
	public int saveUser(User user) throws UserNotFoundException {
		Connection conn = null;
		String sql = "insert into users values(useq.nextval,?,?,?,?,?,?,?)";
		String seq = "select useq.currval from dual";
		
		try {
			conn = db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,user.getPassword());
			st.setString(2,user.getRole());
			st.setString(3,user.getUserName());
			st.setString(4,user.getMobileNo());
			st.setString(5,user.getPhone());
			st.setString(6,user.getAddress());
			st.setString(7,user.getEmail());
			st.executeUpdate();
			ResultSet rs = conn.createStatement().executeQuery(seq);
			if(rs.next()) 
				return rs.getInt(1);
			return 0;
		} catch (SQLException e) {
			throw new UserNotFoundException("No User found!");
		}finally {
			try {
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Hotel> searchHotelByCity( String city, double minPrice, double maxPrice ) throws HotelNotFoundException {
		Hotel hotel = null;
		Connection conn = null;
		List<Hotel> hotels = new ArrayList<>();
		String sql = "select * from hotel where city=? and price between ? and ?";
		try {
			conn = db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, city);
			st.setDouble(2, minPrice);
			st.setDouble(3, maxPrice);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				hotel = new Hotel();
				hotel.setHotelId(rs.getInt(1));
				hotel.setCity(rs.getString(2));
				hotel.setHotelName(rs.getString(3));
				hotel.setDescription(rs.getString(4));
				hotel.setPrice(rs.getDouble(5));
				hotel.setPhoneNo1(rs.getString(6));
				hotel.setPhoneNo2(rs.getString(7));
				hotel.setRating(rs.getString(8));
				hotel.setEmail(rs.getString(9));
				hotel.setFax(rs.getString(10));
				hotels.add(hotel);
			}
			if(hotels.isEmpty())
				throw new HotelNotFoundException("No hotels found!");
			return hotels;
		} catch (SQLException e) {
			throw new HotelNotFoundException("");
		}finally {
			try {
				if(conn!=null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public List<Room> searchRoom(int hotelId) throws RoomNotFoundException {
		Room room = null;
		Connection conn = null;
		List<Room> rooms = new ArrayList<>();
		String sql = "select * from rommdetails where room_id=(select * from hotel where hotel_id=?)";
		try {
			conn = db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, hotelId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				room = new Room();
				room.setRoomId(rs.getInt(1));
				room.setHotelId(hotelId);
				room.setRoomNo(rs.getString(3));
				room.setRoomType(rs.getString(4));
				room.setRatePerNight(rs.getDouble(5));
				room.setAvailability(rs.getInt(6));
				rooms.add(room);
			}
			if(rooms.isEmpty())
				throw new RoomNotFoundException("No Room Found");
			return rooms;
		} catch (SQLException e) {
			throw new RoomNotFoundException("");
		}finally {
			try {
				if(conn!=null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
	@Override
	public Booking bookRoom(Booking booking) throws Exception {
		Connection conn = null;
		String sql = "insert into bookingdetails values(bseq.nextval,?,?,?,?,?,?,?,?,sysdate)";
		String seq = "select bseq.currval from dual";
		try {
			conn = db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, booking.getRoomId());
			st.setInt(2, booking.getUserId());
			st.setDate(3, Date.valueOf(booking.getBookedFrom()));
			st.setDate(4, Date.valueOf(booking.getBookedTo()));
			st.setInt(5, booking.getAdults());
			st.setInt(6, booking.getChildren());
			st.setDouble(7, booking.getAmount());
			st.setInt(8, booking.getHotelId());
			st.executeUpdate();
			ResultSet rs = conn.createStatement().executeQuery(seq);
			
			if(rs.next()) 
				return booking;
			return null;
		}catch (SQLException e) {
			throw new Exception("");
		}finally{
			try {
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Booking> viewStatus(int userId) throws BookingNotFoundException{
		Booking booking = null;
		Connection conn = null;
		List<Booking> bookings = new ArrayList<Booking>();
		String sql = "select * from bookingdetails where user_id=?";
		try {
			conn = db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, userId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				booking = new Booking();
				booking.setBookingId(rs.getInt(1));
				booking.setRoomId(rs.getInt(2));;
				booking.setUserId(userId);
				booking.setBookedFrom(rs.getDate(4).toLocalDate());
				booking.setBookedTo(rs.getDate(5).toLocalDate());
				booking.setAdults(rs.getInt(6));
				booking.setChildren(rs.getInt(7));
				booking.setAmount(rs.getDouble(8));
				booking.setHotelId(rs.getInt(9));
				booking.setBookingDate(rs.getDate(10).toLocalDate());
				bookings.add(booking);
			}
			if(bookings.isEmpty())
				throw new BookingNotFoundException("No Room Found");
			return bookings;
		} catch (SQLException e) {
			throw new BookingNotFoundException("");
		}finally {
			try {
				if(conn!=null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public List<LocalDate> getBookdDates(int roomId) throws BookingNotFoundException{
		Connection conn = null;
		String sql = "select bookedfrom, bookedto from bookingdetails where room_id=?";
		List<LocalDate> dates = new ArrayList<>();
		
		try {
			conn = db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, roomId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				dates.add(rs.getDate(1).toLocalDate());
				dates.add(rs.getDate(2).toLocalDate());
			}
			if (dates.isEmpty())
				throw new BookingNotFoundException("Room is not Booked!");
			return dates;
		} catch (SQLException e) {
			throw new BookingNotFoundException("");
		}finally {
			try {
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean validateLogin(int userId, String password) throws UserNotFoundException {
		Connection conn = null;
		String sql = "select password from users where role=customer and user_id=?";
		
		try {
			conn = db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, userId);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return rs.getString(1).equals(password);
			}else
				throw new UserNotFoundException("User Not Found!");
		} catch (SQLException e) {
			throw new UserNotFoundException("");
		}finally {
			try {
				if(conn!=null)
					conn.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Hotel> searchHotelByName(String hotelName) throws HotelNotFoundException {
		Hotel hotel = null;
		Connection conn = null;
		List<Hotel> hotels = new ArrayList<>();
		String sql = "select * from hotel where hotel_name=";
		try {
			conn = db.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, hotelName);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				hotel = new Hotel();
				hotel.setHotelId(rs.getInt(1));
				hotel.setCity(rs.getString(2));
				hotel.setHotelName(rs.getString(3));
				hotel.setDescription(rs.getString(4));
				hotel.setPrice(rs.getDouble(5));
				hotel.setPhoneNo1(rs.getString(6));
				hotel.setPhoneNo2(rs.getString(7));
				hotel.setRating(rs.getString(8));
				hotel.setEmail(rs.getString(9));
				hotel.setFax(rs.getString(10));
				hotels.add(hotel);
			}
			if(hotels.isEmpty())
				throw new HotelNotFoundException("No hotels found!");
			return hotels;
		} catch (SQLException e) {
			throw new HotelNotFoundException("");
		}finally {
			try {
				if(conn!=null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
