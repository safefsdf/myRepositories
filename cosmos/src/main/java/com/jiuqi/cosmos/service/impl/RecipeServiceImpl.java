package com.jiuqi.cosmos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiuqi.cosmos.dao.ClassifyInfoMapper;
import com.jiuqi.cosmos.dao.CollectToBlogMapper;
import com.jiuqi.cosmos.dao.FoodRecipeMapper;
import com.jiuqi.cosmos.dao.FoodStepMapper;
import com.jiuqi.cosmos.dao.LikeToBlogMapper;
import com.jiuqi.cosmos.entity.ClassifyInfo;
import com.jiuqi.cosmos.entity.FoodRecipe;
import com.jiuqi.cosmos.entity.FoodStep;
import com.jiuqi.cosmos.service.RecipeService;
@Service
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
		return recipeDao.insert(recipe);
	}

	/**
	 * 00：recipe不存在 01: recipeStep未删除成功 02：recipeSteps为空 03： 删除成功
	 * 
	 * 	删除某一条食谱：食谱表，步骤表，点赞表，收藏表。凡是跟食谱Id相关的表中的数据都要删除
	 */
	@Override
	public void delete(Integer recipeId) {
		try {
			recipeDao.deleteByPrimaryKey(recipeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public FoodRecipe selectByPrimaryKey(Integer recipeId) {
		FoodRecipe foodRecipe = recipeDao.selectByPrimaryKey(recipeId);
		List<FoodStep> recipeSteps = stepDao.selectByRecipeId(recipeId);
		foodRecipe.setRecipeSteps(recipeSteps);
		return null;

	}
	

	@Override
	public List<FoodRecipe> selectByClassifyId(Integer classifyId) {
		List<FoodRecipe> list = recipeDao.selectByClassifyId(classifyId);
		for (FoodRecipe recipe : list) {
			List<FoodStep> stepsByRecipe = stepDao.selectByRecipeId(recipe.getRecipeId());
			recipe.setRecipeSteps(stepsByRecipe);
		}
		return list;
	}

	@Override
	public List<FoodRecipe> selectByUserId(Integer userId) {
		List<FoodRecipe> list = recipeDao.selectByUserId(userId);
		for (FoodRecipe recipe : list) {
			List<FoodStep> stepsByRecipe = stepDao.selectByRecipeId(recipe.getRecipeId());
			recipe.setRecipeSteps(stepsByRecipe);
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
