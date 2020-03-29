package com.jiuqi.cosmos.util;

import com.jiuqi.cosmos.constants.UserConstants;
import com.jiuqi.cosmos.pojo.LikeCollectDTO;

import io.netty.util.internal.StringUtil;

public class RedisKeyUtils {

	

    /**	
     * 拼接被点赞的用户id和点赞的人的id作为key。格式 222222::333333
     * @param likedUserId 被点赞的人id
     * @param likedPostId 点赞的人的id
     * @return
     */
    public static String getLikedKey(Integer likedUserId, Integer likedPostId){
        StringBuilder builder = new StringBuilder();
        builder.append(likedUserId);
        builder.append("::");
        builder.append(likedPostId);
        return builder.toString();
    }
    
    
    public static String likeOrCollectKey(LikeCollectDTO likeCollectDTO) {
    	String like_key = null;
    	 
    	if(!StringUtil.isNullOrEmpty(likeCollectDTO.getType())) {//类型不为空
			if("like".equalsIgnoreCase(likeCollectDTO.getType())) {
				like_key = UserConstants.MAP_KEY_USER_LIKED;
			}else if("collect".equalsIgnoreCase(likeCollectDTO.getType())) {
				like_key = UserConstants.MAP_KEY_USER_COLLECT;
			}
		}
    	return like_key;
    }
    public static String likeOrCollectCountKey(LikeCollectDTO likeCollectDTO) {
    	String like_count_key = null;
    	if(!StringUtil.isNullOrEmpty(likeCollectDTO.getType())) {//类型不为空
			if("like".equalsIgnoreCase(likeCollectDTO.getType())) {
				like_count_key = UserConstants.MAP_KEY_USER_LIKED_COUNT;
			}else if("collect".equalsIgnoreCase(likeCollectDTO.getType())) {
				like_count_key = UserConstants.MAP_KEY_USER_COLLECT_COUNT;
			}
		}
    	return like_count_key;
    }
}
