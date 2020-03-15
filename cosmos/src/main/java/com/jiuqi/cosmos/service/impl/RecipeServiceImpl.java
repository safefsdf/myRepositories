package com.jiuqi.cosmos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jiuqi.cosmos.constants.UserConstants;
import com.jiuqi.cosmos.dao.ClassifyInfoMapper;
import com.jiuqi.cosmos.dao.CollectToBlogMapper;
import com.jiuqi.cosmos.dao.FoodRecipeMapper;
import com.jiuqi.cosmos.dao.FoodStepMapper;
import com.jiuqi.cosmos.dao.LikeToBlogMapper;
import com.jiuqi.cosmos.entity.ClassifyInfo;
import com.jiuqi.cosmos.entity.CollectToBlog;
import com.jiuqi.cosmos.entity.FoodRecipe;
import com.jiuqi.cosmos.entity.FoodStep;
import com.jiuqi.cosmos.entity.LikeToBlog;
import com.jiuqi.cosmos.pojo.UserInfoDTO;
import com.jiuqi.cosmos.service.RecipeService;

import io.netty.util.internal.StringUtil;

public class RecipeServiceImpl implements RecipeService {

	@Autowired
	FoodRecipeMapper recipeDao;

	@Autowired
	FoodStepMapper stepDao;
	
	@Autowired
	ClassifyInfoMapper classifyDao;
	
	@Autowired
	LikeToBlogMapper likeToBlogDao;
	
	@Autowired
	CollectToBlogMapper collectToBlogDao;
	
	@Override
	public int insert(FoodRecipe recipe) {
		List<FoodStep> steps = recipe.getSteps();
		StringBuffer sb = new StringBuffer();
		for (FoodStep step : steps) {
			int insert = stepDao.insert(step);
			sb.append(insert == 1 ? step.getStepId() : null);
		}
		recipe.setRecipeSteps(sb.toString());
		return recipeDao.insert(recipe);
	}

	/**
	 * 00：recipe不存在 01: recipeStep未删除成功 02：recipeSteps为空 03： 删除成功
	 * 
	 * 	删除某一条食谱：食谱表，步骤表，点赞表，收藏表。凡是跟食谱Id相关的表中的数据都要删除
	 */
	@Override
	public String delete(Integer recipeId) {
		FoodRecipe foodRecipe = recipeDao.selectByPrimaryKey(recipeId);
		List<LikeToBlog> selectLikeByBlog = likeToBlogDao.selectLikeByBlog(recipeId);
		for(LikeToBlog blog :selectLikeByBlog) {
			Integer likeId = blog.getLikeId();
			likeToBlogDao.deleteByPrimaryKey(likeId);
		}
		
		List<CollectToBlog> selectCollectByBlog = collectToBlogDao.selectCollectByBlog(recipeId);
		for(CollectToBlog blog :selectCollectByBlog) {
			Integer collectId = blog.getCollectId();
			collectToBlogDao.deleteByPrimaryKey(collectId);
		}
		
		
		int sum = 0;
		if (foodRecipe != null) {
			List<FoodStep> stepList = getStepsByRecipe(foodRecipe);
			for (FoodStep ch : stepList) {
				if (ch != null) {
					sum += stepDao.deleteByPrimaryKey(ch.getStepId());
				}
			}
			if (sum == stepList.size()) {
				recipeDao.deleteByPrimaryKey(recipeId);
				return UserConstants.RECIPE_DELETE_SUC;
			} else {
				return UserConstants.STEPS_DELETE_FAIL;// 01
			}
		} else {
			return UserConstants.RECIPE_NOT_EXIST;// 00
		}
	}

	@Override
	public FoodRecipe selectByPrimaryKey(Integer recipeId) {
		FoodRecipe foodRecipe = recipeDao.selectByPrimaryKey(recipeId);
		if (foodRecipe != null) {
			List<FoodStep> steps = getStepsByRecipe(foodRecipe);
			foodRecipe.setSteps(steps);
			return foodRecipe;
		}
		return null;

	}

	private List<FoodStep> getStepsByRecipe(FoodRecipe foodRecipe) {
		List<FoodStep> stepList = new ArrayList<FoodStep>();
		String recipeSteps = foodRecipe.getRecipeSteps();
		if (!StringUtil.isNullOrEmpty(recipeSteps)) {
			char[] charArray = recipeSteps.toCharArray();

			for (char ch : charArray) {
				Integer stepId = Integer.valueOf(String.valueOf(ch));
				FoodStep foodStep = stepDao.selectByPrimaryKey(stepId);
				stepList.add(foodStep);
			}
			foodRecipe.setSteps(stepList);
		}
		return stepList;
	}

	@Override
	public List<FoodRecipe> selectByClassifyId(Integer classifyId) {
		List<FoodRecipe> list = recipeDao.selectByClassifyId(classifyId);
		for (FoodRecipe recipe : list) {
			List<FoodStep> stepsByRecipe = getStepsByRecipe(recipe);
			recipe.setSteps(stepsByRecipe);
		}
		return list;
	}

	@Override
	public List<FoodRecipe> selectByUserId(Integer userId) {
		List<FoodRecipe> list = recipeDao.selectByUserId(userId);
		for (FoodRecipe recipe : list) {
			List<FoodStep> stepsByRecipe = getStepsByRecipe(recipe);
			recipe.setSteps(stepsByRecipe);
		}
		return list;
	}

	@Override
	public List<ClassifyInfo> selectClassifies() {
		List<ClassifyInfo> cataLevel1 = classifyDao.selectByClassifyPid(0);
		for(ClassifyInfo cata:cataLevel1) {
			List<ClassifyInfo> cataLevel2 = classifyDao.selectByClassifyPid(cata.getClassifyId());
			cata.setSonList(cataLevel1);
			for(ClassifyInfo catalevel2:cataLevel2) {
				List<ClassifyInfo> cataLevel3 = classifyDao.selectByClassifyPid(catalevel2.getClassifyId());
				catalevel2.setSonList(cataLevel3);
			}
		}
		return cataLevel1;
	}

}
