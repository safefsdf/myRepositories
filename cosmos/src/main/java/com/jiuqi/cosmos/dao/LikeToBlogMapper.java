package com.jiuqi.cosmos.dao;

import com.jiuqi.cosmos.entity.LikeToBlog;
import com.jiuqi.cosmos.pojo.UserInfoDTO;

import java.util.List;

public interface LikeToBlogMapper {
    int deleteByPrimaryKey(Integer likeId);

    int insert(LikeToBlog record);

//    LikeToBlog selectByPrimaryKey(Integer likeId);
    
    LikeToBlog selectByBlogAndUser(Integer blogId, Integer userId);
    

    List<UserInfoDTO> selectAllByBlog(Integer blogId);

    int updateByPrimaryKey(LikeToBlog record);
}