package com.jiuqi.cosmos.entity;

public class CollectToBlog {
    private Integer collectId;

    private Integer blogId;

    private Integer userId;

    private Integer collectStatus;

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
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

    public Integer getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(Integer collectStatus) {
        this.collectStatus = collectStatus;
    }

	public CollectToBlog(Integer blogId, Integer userId, Integer collectStatus) {
		super();
		this.blogId = blogId;
		this.userId = userId;
		this.collectStatus = collectStatus;
	}

	public CollectToBlog() {
		super();
	}

	@Override
	public String toString() {
		return "CollectToBlog [collectId=" + collectId + ", blogId=" + blogId + ", userId=" + userId
				+ ", collectStatus=" + collectStatus + "]";
	}
    
}