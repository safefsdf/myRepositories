package com.jiuqi.cosmos.entity;

public class CollectToBlog {
    private Integer collectId;

    private Integer recipeId;

    private Integer userId;

    private Integer collectStatus;

    public CollectToBlog(Integer collectId, Integer recipeId, Integer userId, Integer collectStatus) {
		super();
		this.collectId = collectId;
		this.recipeId = recipeId;
		this.userId = userId;
		this.collectStatus = collectStatus;
	}

	public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
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

    public Integer getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(Integer collectStatus) {
        this.collectStatus = collectStatus;
    }

	public CollectToBlog(Integer recipeId, Integer userId, Integer collectStatus) {
		super();
		this.recipeId = recipeId;
		this.userId = userId;
		this.collectStatus = collectStatus;
	}

	public CollectToBlog() {
		super();
	}

	@Override
	public String toString() {
		return "CollectToBlog [collectId=" + collectId + ", recipeId=" + recipeId + ", userId=" + userId
				+ ", collectStatus=" + collectStatus + "]";
	}
    
}