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

    private Integer focusCount;

    private String signature;
    private String token;
    

    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token==null?null:token.trim();
	}

	public Integer getUserid() {
        return userId;
    }

    public void setUserid(Integer userid) {
        this.userId = userid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg == null ? null : headimg.trim();
    }

    public String getCoverimg() {
        return coverimg;
    }

    public void setCoverimg(String coverimg) {
        this.coverimg = coverimg == null ? null : coverimg.trim();
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
        this.sex = sex == null ? null : sex.trim();
    }

     

    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getFocuscount() {
        return focusCount;
    }

    public void setFocuscount(Integer focuscount) {
        this.focusCount = focuscount;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

	@Override
	public String toString() {
		return "User [userid=" + userId + ", phone=" + phone + ", password=" + password + ", answer=" + answer
				+ ", nickname=" + nickname + ", address=" + address + ", headimg=" + headimg + ", coverimg=" + coverimg
				+ ", createtime=" + createtime + ", sex=" + sex + ", focuscount=" + focusCount + ", signature="
				+ signature + ", token=" + token + "]";
	}

	public User(String phone, String password, String nickname, String headimg, String coverimg, Date createtime,
			String sex, String signature) {
		super();
		this.phone = phone;
		this.password = password;
		this.nickname = nickname;
		this.headimg = headimg;
		this.coverimg = coverimg;
		this.createtime = createtime;
		this.sex = sex;
		this.signature = signature;
	}

	public User(String phone, String password, String nickname, String address, String headimg, String coverimg,
			Date createtime, String sex, String signature) {
		super();
		this.phone = phone;
		this.password = password;
		this.nickname = nickname;
		this.address = address;
		this.headimg = headimg;
		this.coverimg = coverimg;
		this.createtime = createtime;
		this.sex = sex;
		this.signature = signature;
	}

	public User() {
		super();
	}

	 
}