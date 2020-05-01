package com.jiuqi.cosmos.service;

import java.util.List;

import com.jiuqi.cosmos.entity.Comment;
import com.jiuqi.cosmos.pojo.AidInfo;

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
	List<Comment> selectFirstLevel(Integer recipeId);

	/**
	 * @param aidInfo 定义recipeId 及 一级评论列表主键 的辅助类
	 * @return
	 */
	List<Comment> selectSecondLevel(AidInfo aidInfo);

}
