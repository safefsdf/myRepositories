package com.jiuqi.cosmos.entity;

import java.util.Date;

public class User {
    private Integer userId;

    private String phone;

    private String password;

    private String answer;

    private String nickname;
    
    private String address;

    private String headimg;

    private String coverimg;

    private Date createtime;

    private String sex;

    private Integer focusCount;//userId关注的人的列表
    private Integer funCount;//userId的粉丝数量

    private String signature;
    
    private String token;
     
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getCoverimg() {
		return coverimg;
	}

	public void setCoverimg(String coverimg) {
		this.coverimg = coverimg;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getFocusCount() {
		return focusCount;
	}

	public void setFocusCount(Integer focusCount) {
		this.focusCount = focusCount;
	}

	public Integer getFunCount() {
		return funCount;
	}

	public void setFunCount(Integer funCount) {
		this.funCount = funCount;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User(Integer userId, String phone, String password, String answer, String nickname, String address,
			String headimg, String coverimg, Date createtime, String sex, Integer focusCount, Integer funCount,
			String signature, String token) {
		super();
		this.userId = userId;
		this.phone = phone;
		this.password = password;
		this.answer = answer;
		this.nickname = nickname;
		this.address = address;
		this.headimg = headimg;
		this.coverimg = coverimg;
		this.createtime = createtime;
		this.sex = sex;
		this.focusCount = focusCount;
		this.funCount = funCount;
		this.signature = signature;
		this.token = token;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", phone=" + phone + ", password=" + password + ", answer=" + answer
				+ ", nickname=" + nickname + ", address=" + address + ", headimg=" + headimg + ", coverimg=" + coverimg
				+ ", createtime=" + createtime + ", sex=" + sex + ", focusCount=" + focusCount + ", funCount="
				+ funCount + ", signature=" + signature + ", token=" + token + "]";
	}

	 
}