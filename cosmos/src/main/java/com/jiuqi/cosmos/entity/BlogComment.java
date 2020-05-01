package com.jiuqi.cosmos.entity;

import java.util.Date;
import java.util.List;

public class BlogComment {
    private Integer blogCommentId;

    private Integer userId;

    private Integer recipeId;

    private Integer blogCommentPid;

    private Date createTime;

    private String content;
    
    private List<BlogComment> child;
    

    public BlogComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<BlogComment> getChild() {
		return child;
	}

	public void setChild(List<BlogComment> child) {
		this.child = child;
	}

	public Integer getBlogCommentId() {
        return blogCommentId;
    }

    public void setBlogCommentId(Integer blogCommentId) {
        this.blogCommentId = blogCommentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getrecipeId() {
        return recipeId;
    }

    public void setrecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getBlogCommentPid() {
        return blogCommentPid;
    }

    public void setBlogCommentPid(Integer blogCommentPid) {
        this.blogCommentPid = blogCommentPid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}