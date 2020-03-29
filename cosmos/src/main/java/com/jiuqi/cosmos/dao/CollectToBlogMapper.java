package com.jiuqi.cosmos.dao;

import com.jiuqi.cosmos.entity.CollectToBlog;
import com.jiuqi.cosmos.pojo.UserInfoDTO;

import java.util.List;

public interface CollectToBlogMapper {
	/**
	 * 	根据点赞主键，删除点赞信息
	 * @param collectId
	 * @return
	 */
    int deleteByPrimaryKey(Integer collectId);
    /**
     * 	根据食谱主键，删除收藏表中所有的食谱收藏信息
     * @param recipeId
     * @return
     */
    int deleteByRecipeId(Integer recipeId);

    /**
     * 	新增收藏记录
     * @param record
     * @return
     */
    int insert(CollectToBlog record);

    /**
     * 	查询用户 userId是否收藏过食谱recipeId
     * @param recipeId
     * @param userId
     * @return
     */
    CollectToBlog selectByBlogAndUser(Integer recipeId, Integer userId);
    
    /**
     *	 查询所有收藏recipeId的用户信息
     * @param recipeId
     * @return
     */
    List<UserInfoDTO> selectUserByrecipeId(Integer recipeId);
    
    /**
     * 	查询recipeId的收藏信息，为删除做铺垫
     * @param recipeId
     * @return
     */
    List<CollectToBlog> selectCollectByBlog(Integer recipeId);

    /**
     * 	查询该用户所点过赞的食谱
     * @param recipeId
     * @return
     */
    List<CollectToBlog> selectCollectByUser(Integer userId);
    /**
     *	 更新收藏状态
     * @param record
     * @return
     */
    int updateByblogAndUser(CollectToBlog record);
}