package com.jiuqi.cosmos.pojo;

public class LikeCollectDTO {
	//用户userId给recipeId点赞，或收藏了recipeId
	private Integer recipeId;
	private Integer userId;
	private Integer status;
	private String type;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getrecipeId() {
		return recipeId;
	}
	public void setrecipeId(Integer recipeId) {
		this.recipeId = recipeId;
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
		return "LikeCollectDTO [recipeId=" + recipeId + ", userId=" + userId + ", status=" + status + ", type=" + type
				+ "]";
	}
	public LikeCollectDTO(Integer recipeId, Integer userId, Integer status) {
		super();
		this.recipeId = recipeId;
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
