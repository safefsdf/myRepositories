package com.jiuqi.cosmos.entity;

public class FoodStep {
    private Integer stepId;
    
    private Integer recipeId;

    private String stepTitle;

    private String stepImg;

    private String stepDescription;
    
    

    public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public String getStepTitle() {
        return stepTitle;
    }

    public void setStepTitle(String stepTitle) {
        this.stepTitle = stepTitle == null ? null : stepTitle.trim();
    }

    public String getStepImg() {
        return stepImg;
    }

    public void setStepImg(String stepImg) {
        this.stepImg = stepImg == null ? null : stepImg.trim();
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription == null ? null : stepDescription.trim();
    }

 

	@Override
	public String toString() {
		return "FoodStep [stepId=" + stepId + ", recipeId=" + recipeId + ", stepTitle=" + stepTitle + ", stepImg="
				+ stepImg + ", stepDescription=" + stepDescription + "]";
	}

	public FoodStep(Integer stepId, String stepTitle, String stepImg, String stepDescription) {
		super();
		this.stepId = stepId;
		this.stepTitle = stepTitle;
		this.stepImg = stepImg;
		this.stepDescription = stepDescription;
	}

	public FoodStep() {
		super();
	}

	public FoodStep(int recipeId, String stepTitle, String stepImg, String stepDescription) {
		super();
		this.recipeId = recipeId;
		this.stepTitle = stepTitle;
		this.stepImg = stepImg;
		this.stepDescription = stepDescription;
	}
	
    
}