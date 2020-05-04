package com.jiuqi.cosmos.pojo;

public class LikeCollectStatusDTO {
	private Integer likeStatus;
	private Integer collectStatus;
	public Integer getLikeStatus() {
		return likeStatus;
	}
	public void setLikeStatus(Integer likeStatus) {
		this.likeStatus = likeStatus;
	}
	public Integer getCollectStatus() {
		return collectStatus;
	}
	public void setCollectStatus(Integer collectStatus) {
		this.collectStatus = collectStatus;
	}
	@Override
	public String toString() {
		return "LikeCollectStatusDTO [likeStatus=" + likeStatus + ", collectStatus=" + collectStatus + "]";
	}
	
}
