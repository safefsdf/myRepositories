package com.jiuqi.cosmos.entity;

import java.util.Date;

public class Comment {
    private Integer cid;

    private Integer recipeId;

    private Integer userId;
    private String headImg;
    private String nickname;
    

    private String content;
    private String pContent;

    private Integer pid;

    private Date replytime;
    private Date pReplytime;
    //一级评论ID，0为根节点
    private Integer topId;
    
    private Integer pUserId;
    

    public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getReplytime() {
        return replytime;
    }

    public void setReplytime(Date replytime) {
        this.replytime = replytime;
    }

    public Integer getTopId() {
        return topId;
    }

    public void setTopId(Integer topId) {
        this.topId = topId;
    }

	public String getpContent() {
		return pContent;
	}

	public void setpContent(String pContent) {
		this.pContent = pContent;
	}

	public Date getpReplytime() {
		return pReplytime;
	}

	public void setpReplytime(Date pReplytime) {
		this.pReplytime = pReplytime;
	}

	public Integer getpUserId() {
		return pUserId;
	}

	public void setpUserId(Integer pUserId) {
		this.pUserId = pUserId;
	}
    
}