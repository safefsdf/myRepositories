package com.jiuqi.cosmos.pojo;

public class FocusCountDTO {
	private int userId ;
	private String nickName;
	private Integer focusCount ;
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Integer getFocusCount() {
		return focusCount;
	}
	public void setFocusCount(Integer focusCount) {
		this.focusCount = focusCount;
	}
	public FocusCountDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FocusCountDTO(int userId, Integer focusCount) {
		super();
		this.userId = userId;
		this.focusCount = focusCount;
	}
	
	public FocusCountDTO(int userId, String nickName, Integer focusCount) {
		super();
		this.userId = userId;
		this.nickName = nickName;
		this.focusCount = focusCount;
	}
	@Override
	public String toString() {
		return "FocusCountDTO [userId=" + userId + ", focusCount=" + focusCount + "]";
	}
	
	 
}
