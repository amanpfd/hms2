package com.cg.bean;

public class Hotel {
	private int hotelId;
	private String city;
	private String hotelName;
	private String address;
	private String description;
	private String phoneNo1;
	private String phoneNo2;
	private double price;
	private String rating;
	private String email;
	private String fax;
	
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPhoneNo1() {
		return phoneNo1;
	}
	public void setPhoneNo1(String phoneNo1) {
		this.phoneNo1 = phoneNo1;
	}
	public String getPhoneNo2() {
		return phoneNo2;
	}
	public void setPhoneNo2(String phoneNo2) {
		this.phoneNo2 = phoneNo2;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public Hotel(String city, String hotelName, String address, String description, String phoneNo1,
			String phoneNo2, double price, String rating, String email, String fax) {
		this.city = city;
		this.hotelName = hotelName;
		this.address = address;
		this.description = description;
		this.phoneNo1 = phoneNo1;
		this.phoneNo2 = phoneNo2;
		this.price = price;
		this.rating = rating;
		this.email = email;
		this.fax = fax;
	}

	public Hotel() {
		
	}
	
}
