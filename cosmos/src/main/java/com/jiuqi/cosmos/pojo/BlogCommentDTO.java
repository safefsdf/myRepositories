package com.jiuqi.cosmos.pojo;

import java.util.List;

import com.jiuqi.cosmos.entity.Comment;

public class BlogCommentDTO {
	private Comment com;
	private List<Comment> list;
	public Comment getCom() {
		return com;
	}
	public void setCom(Comment com) {
		this.com = com;
	}
	public List<Comment> getList() {
		return list;
	}
	public void setList(List<Comment> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "BlogCommentDTO [com=" + com + ", list=" + list + "]";
	}
	public BlogCommentDTO() {
		super();
	}
	public BlogCommentDTO(Comment com, List<Comment> list) {
		super();
		this.com = com;
		this.list = list;
	}
	
	
}
