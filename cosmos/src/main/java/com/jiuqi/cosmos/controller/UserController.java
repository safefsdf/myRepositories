package com.jiuqi.cosmos.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jiuqi.cosmos.constants.ResultEnum;
import com.jiuqi.cosmos.dao.FoodStepMapper;
import com.jiuqi.cosmos.entity.FoodRecipe;
import com.jiuqi.cosmos.entity.FoodStep;
import com.jiuqi.cosmos.entity.User;
import com.jiuqi.cosmos.pojo.R;
import com.jiuqi.cosmos.pojo.UserDTO;
import com.jiuqi.cosmos.service.FocusService;
import com.jiuqi.cosmos.service.LikeCollectService;
import com.jiuqi.cosmos.service.RecipeService;
import com.jiuqi.cosmos.service.UserService;
import com.jiuqi.cosmos.util.RedisUtil;
import com.jiuqi.cosmos.util.VerifyUtil;
import com.jiuqi.cosmos.util.picUtil;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private RedisUtil<FoodRecipe> redisService;

	@Autowired
	private FocusService focusService;
	@Autowired
	private LikeCollectService likeCollectService;
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private FoodStepMapper stepService;

	/**
	 * 使用手机号和密码登录
	 * 
	 * @param phone
	 * @param password
	 * @return
	 */
	@RequestMapping("login")
	public R<User> login(String phone, String password) {
		System.out.println(phone + " " + password);
		User user = userService.getByPhone(phone); // 根据电话查询用户
		if (user != null) {
			user = userService.quaryByPhoneAndPassword(phone, password);
			if (user == null) {// 密码错误
				return R.success(ResultEnum.PASSWORD_ERROR.getCode(), ResultEnum.PASSWORD_ERROR.getMsg());
			} else {
				String token = user.getUserId() + user.getPhone() + System.currentTimeMillis();
				user.setToken(token);
				redisService.set(token, user, 60 * 60 * 24 * 14);// token的过期时间试14天
				List<FoodRecipe> relateRecipeByUserid = recipeService.getRelateRecipeByUserid(user.getUserId());
				redisService.pushToList(token + "1", relateRecipeByUserid);
				UserDTO userDto = getUserDto(user);
				redisService.set(token + "userdto", userDto, 60 * 60 * 24 * 3);
				return R.success(user, ResultEnum.SUCCESS.getCode(), "登录" + ResultEnum.SUCCESS.getMsg());
			}
		} else {// 用户不存在
			return R.success(ResultEnum.USER_NOT_EXIST.getCode(), ResultEnum.USER_NOT_EXIST.getMsg());
		}
	}

	/* 获取食谱封面图 */
	@PostMapping("/headImg")
	public R<String> importData(MultipartFile file, HttpServletRequest req) throws IOException {
		try {
			String singleFileUpload = picUtil.singleFileUpload(file, "headImg");
			System.out.println("headImg" + singleFileUpload);
			return R.success(singleFileUpload, 200, "suc");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping("Logout")
	public R logoutAccount(String token) {
		// 删除redis中key为token的信息，以及key为token+1，token+userdto的信息
		try {
			redisService.del(token, token + "1", token + "userdto");
			return R.success(ResultEnum.SUCCESS.getCode(), "注销" + ResultEnum.SUCCESS.getMsg());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return R.error(ResultEnum.ERROR.getCode(), "注销" + ResultEnum.ERROR.getMsg());
	}

	@RequestMapping("/save")
	public R<User> register(@RequestBody User user) {
		System.out.println(user.toString());
		System.out.println(user.getUserId());
		if (user.getUserId() == null) {// 注册
			try {
				int res = userService.createUser(user);
				if (res == 1) {
					return R.success(user, ResultEnum.SUCCESS.getCode(), "注册" + ResultEnum.SUCCESS.getMsg());
				}
			} catch (Exception e) {
				e.printStackTrace();
				return R.error(ResultEnum.REG_ERROR.getCode(), "该用户已存在，" + ResultEnum.REG_ERROR.getMsg());
			}
		} else {// 修改
			try {
				userService.updateUserinfo(user);
				return R.success(user, ResultEnum.SUCCESS.getCode(), "信息修改" + ResultEnum.SUCCESS.getMsg());
			} catch (Exception e) {
				e.printStackTrace();
				return R.error(ResultEnum.ERROR.getCode(), "信息修改" + ResultEnum.ERROR.getMsg());
			}
		}
		return null;

	}

	/**
	 * 生成图片验证码
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/getVerify")
	public void getVerify(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
			response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
			VerifyUtil vu = new VerifyUtil();
			// 在下面的这个方法中，生成了一张图片，并通过流的方式相应给客户端，并将图片的值存到了session中
			vu.getRandcode(request, response);// 输出验证码图片方法
		} catch (Exception e) {

		}
	}

	/**
	 * 验证码校验
	 * 
	 * @param verifyInput
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/checkVerify", method = RequestMethod.GET)
	public boolean checkVerify(String verifyInput, HttpSession session) {
		try {
			String str = (String) session.getAttribute(VerifyUtil.RANDOMCODEKEY);
			System.out.println("后端生成： " + str);
			System.out.println("前端输入： " + verifyInput);
			if (str == null) {
				return false;
			}
			if (str.equalsIgnoreCase(verifyInput)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取用户资料 1.根据token(token+"userdto")从redis中获取UserDto；---定时2天
	 * 2.如果为空，根据token获取该用户的数据userId，调用公共方法，初始化到redis中。
	 * 
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/getInfo", method = RequestMethod.GET)
	public R<UserDTO> getUserInfo(String token) {
		System.out.println("token: " + token);
		try {
			Object u = redisService.get(token);
			Object object = redisService.get(token + "userdto");
			if (u != null && u instanceof User) {
				User user = (User) u;
				if (object != null && object instanceof UserDTO) {
					return new R<UserDTO>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), (UserDTO) object);
				} else {// 时间到期
					UserDTO userDto = getUserDto(user);
					redisService.set(token + "userdto", userDto, 60 * 60 * 24 * 3);
					return new R<UserDTO>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg(), userDto);
				}
			} else {
				// 跳转到登陆页面，提示当前登录已失效
				R.error(ResultEnum.TOKEN_TIMEOUT.getCode(), ResultEnum.TOKEN_TIMEOUT.getMsg());
			}

		} catch (Exception e1) {
			return R.error(ResultEnum.USER_NOT_EXIST.getCode(), ResultEnum.USER_NOT_EXIST.getMsg());
		}
		return null;

	}

	@RequestMapping(value = "/modifyPwd", method = RequestMethod.POST)
	public R<User> modifyUserPwd(@RequestBody User user) {
		try {
			User us = userService.getByPhone(user.getPhone()); // 根据电话查询用户
			if (us != null) {
				if (user.getAnswer().equals(us.getAnswer())) {
					userService.modifyPwd(user);
					us.setPassword(user.getPassword());
					return R.success(us,ResultEnum.SUCCESS_ON_MODIFY_PWD.getCode(), ResultEnum.SUCCESS_ON_MODIFY_PWD.getMsg());
				}else {
					return R.error(ResultEnum.USER_PWDQUEERROR.getCode(), ResultEnum.USER_PWDQUEERROR.getMsg());
				}
			}else {
				return R.error(ResultEnum.USER_NOT_EXIST.getCode(), ResultEnum.USER_NOT_EXIST.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return R.error();

	}

	private UserDTO getUserDto(User user) {
		if (user == null)
			return null;
		UserDTO userDto = new UserDTO();
		userDto.setUser(user);
		getUserDTOByUid(user.getUserId(), userDto);
		return userDto;
	}

	private void getUserDTOByUid(Integer userid, UserDTO userDto) {
		try {
			List<User> idolList = focusService.getFocusListByFocusPostId(userid);
			List<User> funList = focusService.getFocusListByFocusUserId(userid);
			userDto.setIdolUserList(idolList);
			userDto.setFunUserList(funList);
			userDto.setIdolCount(idolList.size());
			userDto.setFunCount(funList.size());
			List<FoodRecipe> likeRecipeList = likeCollectService.selectLikeListRecipeByUserId(userid);
			for (FoodRecipe recipe : likeRecipeList) {
				List<FoodStep> stepsByRecipe = stepService.selectByRecipeId(recipe.getRecipeId());
				recipe.setRecipeSteps(stepsByRecipe);
				recipe.setUserDto(userService.getById(recipe.getUserId()));
			}

			List<FoodRecipe> collectRecipeList = likeCollectService.selectColListRecipeByUserId(userid);
			for (FoodRecipe recipe : collectRecipeList) {
				List<FoodStep> stepsByRecipe = stepService.selectByRecipeId(recipe.getRecipeId());
				recipe.setRecipeSteps(stepsByRecipe);
				recipe.setUserDto(userService.getById(recipe.getUserId()));
			}
			userDto.setCollectRecipeList(collectRecipeList);
			userDto.setLikeRecipeList(likeRecipeList);
			userDto.setLikeCount(likeRecipeList.size());
			userDto.setCollectCount(collectRecipeList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
