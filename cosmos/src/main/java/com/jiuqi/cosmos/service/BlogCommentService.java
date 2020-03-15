package com.jiuqi.cosmos.service;

import java.util.List;

import com.jiuqi.cosmos.entity.BlogComment;

public interface BlogCommentService {

    /**
     * 新增评论
     * @param record
     * @return
     */
	int insert(BlogComment record);
	
	//根据博客id，查询所有的评论和回复信息
	List<BlogComment> selectAllByrecipeId(Integer recipeId);
	
	//删除评论
	/**
	 * 根据博客的id删除所有的评论或回复
	 * @param recipeId
	 * @return
	 */
	int deleteByrecipeId(Integer recipeId);
	/**
	 * 根据评论的id删除该条评论或回复
	 * @param blogCommentId
	 * @return
	 */
    int deleteByPrimaryKey(Integer blogCommentId);
}
