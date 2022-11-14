package com.company.biz.vo;

public class ContentVO {
	private int contentcode;
	private String genre;
	private String contentname;
	private String author;
	private String publisher;
	private String publicationdate;
	private String reservation;
	private int  price;
	private int cnt;
	
	
	
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getContentcode() {
		return contentcode;
	}
	public void setContentcode(int contentcode) {
		this.contentcode = contentcode;
	}
	public String getContentname() {
		return contentname;
	}
	public void setContentname(String contentname) {
		this.contentname = contentname;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublicationdate() {
		return publicationdate;
	}
	public void setPublicationdate(String publicationdate) {
		this.publicationdate = publicationdate;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ContentVO [contentcode=" + contentcode + ", genre=" + genre + ", contentname=" + contentname
				+ ", author=" + author + ", publisher=" + publisher + ", publicationdate=" + publicationdate
				+ ", reservation=" + reservation + ", price=" + price + ", cnt=" + cnt + "]";
	}

	

	
}
