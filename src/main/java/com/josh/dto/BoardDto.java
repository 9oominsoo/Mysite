package com.josh.dto;

import java.sql.Date;

public class BoardDto {
	private int no;
	private String title;
	private String content;
	private int hit;
	private Date regDate;
	private int userNo;
	private String userName;
	private int pageNum;

	public BoardDto(){

	}

	public BoardDto(int no, String title, String content, 	int hit, Date regDate, int userNo) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regDate = regDate;
		this.userNo = userNo;
	}

	public BoardDto(int no, String title, String content, int hit, Date regDate, int userNo, String userName){
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.regDate = regDate;
		this.userNo = userNo;
		this.userName = userName;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserName(){
		return userName;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "BoardDto [no=" + no + ", title=" + title + ", content=" + content + ", hit=" + hit + ", regDate="
				+ regDate + ", userNo=" + userNo + ", userName=" + userName + "]";
	}

	
	
}
