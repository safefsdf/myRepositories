package com.jiuqi.cosmos.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiuqi.cosmos.constants.ResultEnum;
import com.jiuqi.cosmos.pojo.LikeCollectDTO;
import com.jiuqi.cosmos.pojo.LikeCollectStatusDTO;
import com.jiuqi.cosmos.pojo.LikeCountDTO;
import com.jiuqi.cosmos.pojo.R;
import com.jiuqi.cosmos.pojo.UserInfoDTO;
import com.jiuqi.cosmos.service.LikeCollectService;
@RequestMapping("likeOrCollect")
@RestController
public class LikeCollectController {

	@Autowired
	public LikeCollectService like_collectService;
	
	/**
	 * 当用户点赞时，需要两个操作，将信息存到like的hash表中，将数量存到likeCount的hash表中。
	 * 根据likeCollectDTO中的类型是like或者collect来执行点赞或者收藏的操作
	 * @param likeCollectDTO
	 * @return
	 */
	@RequestMapping("likeOrUnlike")
	public R<?> LikeOrCollectToBlog(@RequestBody LikeCollectDTO likeCollectDTO) {
		System.out.println(likeCollectDTO.toString());
		boolean flag = false;
		if(likeCollectDTO!=null) {
			if(likeCollectDTO.getStatus() == 1) {//点赞
				flag = like_collectService.likeToblogToRedis(likeCollectDTO);
			}else {//取消点赞
				flag = like_collectService.unlikeToblogToRedis(likeCollectDTO);
			}
		}
		if(flag) {
			return R.success();
		}
		return R.error();
	}
	@RequestMapping("likeOrCollectCount")
	public R<LikeCountDTO> getBlogFun(Integer recipeId) {
		try {
			List<UserInfoDTO> likeUserList = like_collectService.getLikedByrecipeId(recipeId);
			Integer likeCount = likeUserList.size();
			List<UserInfoDTO> collectUserList = like_collectService.getCollectedByrecipeId(recipeId);
			Integer collectCount = collectUserList.size();
			
			LikeCountDTO dto = new LikeCountDTO(recipeId, likeCount, likeUserList, collectCount, collectUserList);
			return R.success(dto, ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
		
	}
	@RequestMapping("getLCStatus")
	public R<LikeCollectStatusDTO> getLikeAndCollectStatus(Integer recipeId, Integer userId){
		try {
			 LikeCollectStatusDTO status = like_collectService.getStatus(recipeId, userId);
			 return R.success(status, ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return R.error();
	}
	
	
}
