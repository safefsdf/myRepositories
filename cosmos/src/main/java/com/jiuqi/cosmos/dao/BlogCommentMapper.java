package com.jiuqi.cosmos.dao;

import com.jiuqi.cosmos.entity.BlogComment;
import java.util.List;

public interface BlogCommentMapper {
	/**
	 * 根据评论的id删除该条评论或回复
	 * @param blogCommentId
	 * @return
	 */
    int deleteByPrimaryKey(Integer blogCommentId);
    /**
	 * 根据博客的id删除所有的评论或回复
	 * @param recipeId
	 * @return
	 */
    int deleteByrecipeId(Integer recipeId);

    /**
     * 新增评论
     * @param record
     * @return
     */
    int insert(BlogComment record);

    BlogComment selectByPrimaryKey(Integer blogCommentId);
    /**
     * 查询对 recipeId 的所有评论及回复
     * @param recipeId
     * @return
     */
    List<BlogComment> selectByrecipeId(Integer recipeId);

    /**
     * 查询对blogCommentPid（评论或回复）的所有回复
     * @param blogCommentPid
     * @return
     */
    List<BlogComment> selectByCommentPid(Integer blogCommentPid);
    
    
    List<BlogComment> selectByCommentPidrecipeId(Integer blogCommentPid, Integer recipeId);
    /**
     * 查询所有的评论回复
     * @return
     */
    List<BlogComment> selectAll();

    /**
     * 修改评论
     * @param record
     * @return
     */
    int updateByPrimaryKey(BlogComment record);
}