package com.josh.dto;

import java.sql.Date;

public class HierarchyDto {
    private int no;
    private int userNo;
    private String title;
    private String content;
    private int hit;
    private Date regDate;
    private int groupNo;
    private int orderNo;
    private int depth;
    private String status;
    private String userName;
	public int getNo() {
		return no;
	}
	public void setNumber(int no) {
		this.no = no;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
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
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

  public String getUserName(){
    return userName;
  }

  public void setUserName(String userName){
    this.userName = userName;
  }
  
	@Override
	public String toString() {
		return "HierarchyDto [number=" + no + ", userNo=" + userNo + ", userName=" + userName + ", title=" + title + ", content=" + content
				+ ", hit=" + hit + ", regDate=" + regDate + ", groupNo=" + groupNo + ", orderNo=" + orderNo + ", depth="
				+ depth + ", status=" + status + "]";
	}


}
