package com.jiuqi.cosmos.dao;

import com.jiuqi.cosmos.entity.LikeToBlog;
import com.jiuqi.cosmos.pojo.UserInfoDTO;

import java.util.List;

public interface LikeToBlogMapper {
    int deleteByPrimaryKey(Integer likeId);
    
    int deleteByRecipeId(Integer recipeId);

    int insert(LikeToBlog record);

//    LikeToBlog selectByPrimaryKey(Integer likeId);
    
    LikeToBlog selectByBlogAndUser(Integer recipeId, Integer userId);
    

    List<UserInfoDTO> selectUserInfoByBlog(Integer recipeId);
    
    List<LikeToBlog> selectLikeByBlog(Integer recipeId);

    int updateByPrimaryKey(LikeToBlog record);
}