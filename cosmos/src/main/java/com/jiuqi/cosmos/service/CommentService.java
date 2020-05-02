package com.jiuqi.cosmos.service;


import java.util.List;

import com.jiuqi.cosmos.entity.Comment;
import com.jiuqi.cosmos.pojo.BlogCommentDTO;

public interface CommentService {

	/**
	 *  新建评论
	 * @param record
	 * @return
	 */
	int insert(Comment record);

	/**
	 * 查询食谱recipeId的一级评论信息
	 * @param recipeId
	 * @return
	 */
//	List<Comment> selectFirstLevel(Integer recipeId);
	 
	public List<BlogCommentDTO> selectSecondLevel(Integer recipeId);
}