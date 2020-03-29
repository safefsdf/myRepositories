package com.jiuqi.cosmos.dao;

import com.jiuqi.cosmos.entity.LikeToBlog;
import com.jiuqi.cosmos.pojo.UserInfoDTO;

import java.util.List;

public interface LikeToBlogMapper {
    int deleteByPrimaryKey(Integer likeId);
    
    int deleteByRecipeId(Integer recipeId);

    int insert(LikeToBlog record);

//    LikeToBlog selectByPrimaryKey(Integer likeId);
    
    /**
     * 	查询用户 userId是否为食谱recipeId点过赞
     * @param recipeId
     * @param userId
     * @return
     */
    LikeToBlog selectByBlogAndUser(Integer recipeId, Integer userId);
    

    /**
     * 	根据食谱主键，查询为该食谱点过赞的用户信息列表
     * @param recipeId
     * @return
     */
    List<UserInfoDTO> selectUserInfoByBlog(Integer recipeId);
    
    /**
     * 	根据食谱主键，查询该食谱的点赞列表，为删除做铺垫
     * @param recipeId
     * @return
     */
    List<LikeToBlog> selectLikeByBlog(Integer recipeId);
    /**
     * 	查询该用户所点过赞的食谱
     * @param recipeId
     * @return
     */
    List<LikeToBlog> selectRecipeByUser(Integer userId);

    /**
     *	 更新点赞状态
     * @param record
     * @return
     */
    int updateByPrimaryKey(LikeToBlog record);
}