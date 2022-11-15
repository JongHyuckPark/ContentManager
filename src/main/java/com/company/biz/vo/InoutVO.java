package com.company.biz.vo;

public class InoutVO {
	private String id;
	private String name;
	private int contentcode;
	private String genre;
	private String contentname;
	private String rentaldate;
	private String returndate;
	private int price;
	


	public String getRentaldate() {
		return rentaldate;
	}
	public void setRentaldate(String rentaldate) {
		this.rentaldate = rentaldate;
	}
	public String getReturndate() {
		return returndate;
	}
	public void setReturndate(String returndate) {
		this.returndate = returndate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getContentcode() {
		return contentcode;
	}
	public void setContentcode(int contentcode) {
		this.contentcode = contentcode;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getContentname() {
		return contentname;
	}
	public void setContentname(String contentname) {
		this.contentname = contentname;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	
	
}
