package com.jiuqi.cosmos.entity;

public class FocusInfo {
	private Integer id;

	// 被关注的用户的id（主）
	private Integer focusUserId;

	// 主动关注的用户的id（访客）
	private Integer focusPostId;

	// 关注的状态（0/1）
	private Integer status ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFocusUserId() {
		return focusUserId;
	}

	public void setFocusUserId(Integer focusUserId) {
		this.focusUserId = focusUserId;
	}

	public Integer getFocusPostId() {
		return focusPostId;
	}

	public void setFocusPostId(Integer focusPostId) {
		this.focusPostId = focusPostId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "FocusInfo [id=" + id + ", focusUserId=" + focusUserId + ", focusPostId=" + focusPostId + ", status="
				+ status + "]";
	}

	public FocusInfo() {
		super();
	}

	public FocusInfo(Integer id, Integer focusUserId, Integer focusPostId, Integer status) {
		super();
		this.id = id;
		this.focusUserId = focusUserId;
		this.focusPostId = focusPostId;
		this.status = status;
	}

	public FocusInfo(Integer focusUserId, Integer focusPostId, Integer status) {
		super();
		this.focusUserId = focusUserId;
		this.focusPostId = focusPostId;
		this.status = status;
	}
	
	
	
	
}
