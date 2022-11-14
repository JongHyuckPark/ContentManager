package com.company.biz.vo;

public class ContentVO {
	private int contentcode;
	private String content;
	private String contentname;
	private String author;
	private String publisher;
	private String publicationdate;
	private String rentaldate;
	private String returndate;
	private int  price;
	private int cnt;
	
	
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ContentVO [contentcode=" + contentcode + ", content=" + content + ", contentname=" + contentname
				+ ", author=" + author + ", publisher=" + publisher + ", publicationdate=" + publicationdate
				+ ", rentaldate=" + rentaldate + ", returndate=" + returndate + ", price=" + price + ", cnt=" + cnt
				+ "]";
	}

	

	
}
