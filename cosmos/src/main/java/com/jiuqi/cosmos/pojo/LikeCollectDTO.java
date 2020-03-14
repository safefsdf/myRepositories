package com.jiuqi.cosmos.pojo;

public class LikeCollectDTO {
	//用户userId给blogId点赞，或收藏了blogId
	private Integer blogId;
	private Integer userId;
	private Integer status;
	private String type;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getBlogId() {
		return blogId;
	}
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public LikeCollectDTO() {
		super();
	}
	
	@Override
	public String toString() {
		return "LikeCollectDTO [blogId=" + blogId + ", userId=" + userId + ", status=" + status + ", type=" + type
				+ "]";
	}
	public LikeCollectDTO(Integer blogId, Integer userId, Integer status) {
		super();
		this.blogId = blogId;
		this.userId = userId;
		this.status = status;
	}
	public LikeCollectDTO(Integer userId, Integer status, String type) {
		super();
		this.userId = userId;
		this.status = status;
		this.type = type;
	}
	
}
