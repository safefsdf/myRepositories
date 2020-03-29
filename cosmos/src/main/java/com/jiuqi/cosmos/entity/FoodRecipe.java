package com.jiuqi.cosmos.entity;

import java.util.Date;
import java.util.List;

import com.jiuqi.cosmos.pojo.LikeCollectDTO;
import com.jiuqi.cosmos.pojo.UserInfoDTO;

public class FoodRecipe {
    private Integer recipeId;

    private Integer userId;

    private Integer classifyId;

    private String recipeCoverimg;
    
    private String recipeIntro;
    

    private Date createtime;

    private String recipeSteps;

    private Integer likeCount;

    private Integer collectCount;

    private String recipeTitle;

    private String recipeUsage;

    private String recipeTips;
    
    private List<FoodStep> steps;
    
    private LikeCollectDTO likeCollectDto;
    
    private UserInfoDTO userDto;

    public LikeCollectDTO getLikeCollectDto() {
		return likeCollectDto;
	}

	public void setLikeCollectDto(LikeCollectDTO likeCollectDto) {
		this.likeCollectDto = likeCollectDto;
	}

	public UserInfoDTO getUserDto() {
		return userDto;
	}

	public void setUserDto(UserInfoDTO userDto) {
		this.userDto = userDto;
	}

	public List<FoodStep> getSteps() {
		return steps;
	}

	public void setSteps(List<FoodStep> steps) {
		this.steps = steps;
	}

	public FoodRecipe(Integer userId, Integer classifyId, String recipeCoverimg, String recipeIntro, String recipeSteps,
			String recipeTitle, String recipeUsage, String recipeTips) {
		super();
		this.userId = userId;
		this.classifyId = classifyId;
		this.recipeCoverimg = recipeCoverimg;
		this.recipeIntro = recipeIntro;
		this.recipeSteps = recipeSteps;
		this.recipeTitle = recipeTitle;
		this.recipeUsage = recipeUsage;
		this.recipeTips = recipeTips;
	}

	public FoodRecipe() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "FoodRecipe [recipeId=" + recipeId + ", userId=" + userId + ", classifyId=" + classifyId
				+ ", recipeCoverimg=" + recipeCoverimg + ", recipeIntro=" + recipeIntro + ", createtime=" + createtime
				+ ", recipeSteps=" + recipeSteps + ", likeCount=" + likeCount + ", collectCount=" + collectCount
				+ ", recipeTitle=" + recipeTitle + ", recipeUsage=" + recipeUsage + ", recipeTips=" + recipeTips + "]";
	}

	public String getRecipeIntro() {
		return recipeIntro;
	}

	public void setRecipeIntro(String recipeIntro) {
		this.recipeIntro = recipeIntro;
	}

	public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
    }

    public String getRecipeCoverimg() {
        return recipeCoverimg;
    }

    public void setRecipeCoverimg(String recipeCoverimg) {
        this.recipeCoverimg = recipeCoverimg == null ? null : recipeCoverimg.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(String recipeSteps) {
        this.recipeSteps = recipeSteps == null ? null : recipeSteps.trim();
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(Integer collectCount) {
        this.collectCount = collectCount;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle == null ? null : recipeTitle.trim();
    }

    public String getRecipeUsage() {
        return recipeUsage;
    }

    public void setRecipeUsage(String recipeUsage) {
        this.recipeUsage = recipeUsage == null ? null : recipeUsage.trim();
    }

    public String getRecipeTips() {
        return recipeTips;
    }

    public void setRecipeTips(String recipeTips) {
        this.recipeTips = recipeTips == null ? null : recipeTips.trim();
    }
}