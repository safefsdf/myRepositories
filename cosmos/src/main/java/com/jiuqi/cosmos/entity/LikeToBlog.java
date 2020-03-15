package com.jiuqi.cosmos.entity;

public class LikeToBlog {
    private Integer likeId;

    private Integer recipeId;

    private Integer userId;

    private Integer likeStatus;

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
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

    public Integer getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(Integer likeStatus) {
        this.likeStatus = likeStatus;
    }

	@Override
	public String toString() {
		return "LikeToBlog [likeId=" + likeId + ", recipeId=" + recipeId + ", userId=" + userId + ", likeStatus="
				+ likeStatus + "]";
	}

	public LikeToBlog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LikeToBlog(Integer likeId, Integer recipeId, Integer userId, Integer likeStatus) {
		super();
		this.likeId = likeId;
		this.recipeId = recipeId;
		this.userId = userId;
		this.likeStatus = likeStatus;
	}

	public LikeToBlog(Integer recipeId, Integer userId, Integer likeStatus) {
		super();
		this.recipeId = recipeId;
		this.userId = userId;
		this.likeStatus = likeStatus;
	}
	
    
}