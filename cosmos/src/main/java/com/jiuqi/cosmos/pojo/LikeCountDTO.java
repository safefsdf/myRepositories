package com.jiuqi.cosmos.pojo;

import java.util.List;

public class LikeCountDTO {
	private Integer blogId;
	private Integer likeCount;
	private List<UserInfoDTO> likeList;
	
	private Integer collectCount;
	private List<UserInfoDTO> collectList;
	public LikeCountDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LikeCountDTO(Integer blogId, Integer likeCount, Integer collectCount) {
		super();
		this.blogId = blogId;
		this.likeCount = likeCount;
		this.collectCount = collectCount;
	}
	public Integer getBlogId() {
		return blogId;
	}
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public Integer getCollectCount() {
		return collectCount;
	}
	public void setCollectCount(Integer collectCount) {
		this.collectCount = collectCount;
	}
	@Override
	public String toString() {
		return "LikeCountDTO [blogId=" + blogId + ", likeCount=" + likeCount + ", collectCount=" + collectCount + "]";
	}
	public List<UserInfoDTO> getLikeList() {
		return likeList;
	}
	public void setLikeList(List<UserInfoDTO> likeList) {
		this.likeList = likeList;
	}
	public List<UserInfoDTO> getCollectList() {
		return collectList;
	}
	public void setCollectList(List<UserInfoDTO> collectList) {
		this.collectList = collectList;
	}
	public LikeCountDTO(Integer blogId, Integer likeCount, List<UserInfoDTO> likeList, Integer collectCount,
			List<UserInfoDTO> collectList) {
		super();
		this.blogId = blogId;
		this.likeCount = likeCount;
		this.likeList = likeList;
		this.collectCount = collectCount;
		this.collectList = collectList;
	}
	
}
