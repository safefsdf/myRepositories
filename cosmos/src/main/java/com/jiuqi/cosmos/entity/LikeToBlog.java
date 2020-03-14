package com.jiuqi.cosmos.entity;

public class LikeToBlog {
    private Integer likeId;

    private Integer blogId;

    private Integer userId;

    private Integer likeStatus;

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
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

    public Integer getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(Integer likeStatus) {
        this.likeStatus = likeStatus;
    }

	@Override
	public String toString() {
		return "LikeToBlog [likeId=" + likeId + ", blogId=" + blogId + ", userId=" + userId + ", likeStatus="
				+ likeStatus + "]";
	}

	public LikeToBlog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LikeToBlog(Integer likeId, Integer blogId, Integer userId, Integer likeStatus) {
		super();
		this.likeId = likeId;
		this.blogId = blogId;
		this.userId = userId;
		this.likeStatus = likeStatus;
	}

	public LikeToBlog(Integer blogId, Integer userId, Integer likeStatus) {
		super();
		this.blogId = blogId;
		this.userId = userId;
		this.likeStatus = likeStatus;
	}
	
    
}