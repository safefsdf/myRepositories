package com.jiuqi.cosmos.entity;

import java.util.Date;

public class User {
    private Integer userid;

    private String phone;

    private String password;

    private String answer;

    private String nickname;

    private String headimg;

    private String coverimg;

    private Date createtime;

    private String sex;

    private Date birthday;

    private Integer focuscount;

    private String signature;
    private String token;
    

    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getFocuscount() {
        return focuscount;
    }

    public void setFocuscount(Integer focuscount) {
        this.focuscount = focuscount;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

	@Override
	public String toString() {
		return "User [userid=" + userid + ", phone=" + phone + ", password=" + password + ", answer=" + answer
				+ ", nickname=" + nickname + ", headimg=" + headimg + ", coverimg=" + coverimg + ", createtime="
				+ createtime + ", sex=" + sex + ", birthday=" + birthday + ", focuscount=" + focuscount + ", signature="
				+ signature + ", token=" + token + "]";
	}
    
}