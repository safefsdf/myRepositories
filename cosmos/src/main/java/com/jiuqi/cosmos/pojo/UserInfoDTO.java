package com.jiuqi.cosmos.pojo;

public class UserInfoDTO {
	private Integer userId;
	private String nickname;
	private String signature;
	private String headimg;
	private String coverimg;
	private Integer focusCount;

	public UserInfoDTO(Integer userId,  String nickname, String signature, String headimg,
			String coverimg, Integer focusCount) {
		super();
		this.userId = userId;
		this.nickname = nickname;
		this.signature = signature;
		this.headimg = headimg;
		this.coverimg = coverimg;
		this.focusCount = focusCount;
	}

	@Override
	public String toString() {
		return "UserInfoDTO [userId=" + userId +  ", nickname=" + nickname + ", signature="
				+ signature + ", headimg=" + headimg + ", coverimg=" + coverimg + ", focusCount=" + focusCount + "]";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	 
	public UserInfoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
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

	public Integer getFocusCount() {
		return focusCount;
	}

	public void setFocusCount(Integer focusCount) {
		this.focusCount = focusCount;
	}

}
