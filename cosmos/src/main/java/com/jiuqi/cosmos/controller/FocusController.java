package com.jiuqi.cosmos.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiuqi.cosmos.constants.ResultEnum;
import com.jiuqi.cosmos.entity.FocusInfo;
import com.jiuqi.cosmos.entity.User;
import com.jiuqi.cosmos.pojo.R;
import com.jiuqi.cosmos.service.FocusRedisService;
import com.jiuqi.cosmos.service.FocusService;
import com.jiuqi.cosmos.service.UserService;
@RequestMapping("focus")
@RestController
public class FocusController {

	@Autowired
	public FocusService focusService;
	@Autowired
	public FocusRedisService focusRedisService;
	@Autowired
	public UserService userService;

	/**
	 * 关注 ：用户 focusUserId 被 focusPostId 所关注。（此时登录者为postId,看到了用户userId的信息）
	 * 点击：查询如果库中没有，则添加，如果有判断状态值：1--0；0--1
	 * redis更新时间缩短，更具时效性
	 * @param focusUserId 被关注者
	 * @param focusPostId 关注的操作者
	 * @return 关注是否成功的信息
	 */
	@RequestMapping("focusOn")
	@Transactional
	public R<FocusInfo> focusOn(Integer focusUserId, Integer focusPostId) {
		try {
			FocusInfo focusInfo = focusService.getByFocusUserIdAndFocusPostId(focusUserId, focusPostId);
			if(focusInfo == null) {
				FocusInfo info = new FocusInfo(focusUserId, focusPostId, 1);
				focusRedisService.focusToRedis(focusUserId, focusPostId);
				focusRedisService.incrementLikedCount(focusUserId);// 粉丝量+1
				return R.success(info, 200, "success");
			}
			if(focusInfo.getStatus() == 0) {//修改为关注状态
				focusRedisService.focusToRedis(focusUserId, focusPostId);
				return new R<FocusInfo>(ResultEnum.ONFOCUS.getCode(), ResultEnum.ONFOCUS.getMsg());
			}else {//修改为未关注状态
				focusRedisService.unFocusToRedis(focusUserId, focusPostId);
				new R<FocusInfo>(ResultEnum.OFFFOCUS.getCode(), ResultEnum.OFFFOCUS.getMsg());
			}
		} catch (Exception e) {
			return R.success(9, "关注失败");
		}
		return null;
	}
	/**
	 * 取消关注 用户postId不喜欢UserId了
	 * 
	 * @param focusUserId
	 * @param focusPostId
	 * @return 取消关注是否成功的信息
	 */
	@RequestMapping("focusCancel")
	@Transactional
	public R<FocusInfo> focusDown(Integer focusUserId, Integer focusPostId) {
		FocusInfo info = new FocusInfo(focusUserId, focusPostId, 0);
		try {
			focusRedisService.unFocusToRedis(focusUserId, focusPostId);
			focusRedisService.decrementLikedCount(focusUserId);
			return R.success(info, 200, "取消关注成功");
		} catch (Exception e) {
			return R.error(9, "取消关注失败");
		}
	}

	/**
	 * 获取该用户的粉丝
	 * @param focusUserId
	 * @return userList的列表
	 */
	public R<User> getFocusfun(Integer focusUserId) {
		try {
			List<User> userList = focusService.getFocusListByFocusUserId(focusUserId);
			 
			return R.success(userList, ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg());
		} catch (Exception e) {
			return R.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
		}
	}

	// 获取该用户的粉丝量---user表中有一个字段，count是用来表示粉丝量的。
	// 但是根据登录用户的ID就可以获取该用户的全部信息，包括---头像，封面，昵称，个性签名，以及粉丝量。
	
	
	/**
	 * 查看我关注的博主的信息
	 * @param focusPostId
	 * @return userList 我的偶像的集合
	 */
	@RequestMapping("/getMyIdol")
	public R<User> getMyFocus(Integer focusPostId) {
		 
		return R.error(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg());
	}

	/**
	 * 判断用户A B是否有关注关系
	 * @param focusUserId
	 * @param focusPostId
	 * @return 关注的信息
	 */
	@RequestMapping("/isFocus")
	public R<FocusInfo> getIsFocus(Integer focusUserId, Integer focusPostId) {
		try {
			System.out.println(focusUserId+" :  "+focusPostId);
			FocusInfo focusInfo = focusService.getByFocusUserIdAndFocusPostId(focusUserId, focusPostId);
			if(focusInfo!=null && focusInfo.getStatus() ==1) {
				return R.success(ResultEnum.ONFOCUS.getCode(), ResultEnum.ONFOCUS.getMsg());
			}
			return R.success(ResultEnum.OFFFOCUS.getCode(), ResultEnum.OFFFOCUS.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return R.error(ResultEnum.OPER_ERROR.getCode(), ResultEnum.OPER_ERROR.getMsg());
	}
}
