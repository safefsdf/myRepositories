package com.jiuqi.cosmos.util;
import java.util.Comparator;
import com.jiuqi.cosmos.entity.FoodRecipe;
public class ComparatorOuter implements Comparator<FoodRecipe> {
	@Override
	public int compare(FoodRecipe arg0, FoodRecipe arg1) {
		if(arg0==null||arg1==null||arg0.getCreatetime()==null || arg1.getCreatetime()==null) {
			return 0;
		}
		return arg0.getCreatetime().compareTo(arg1.getCreatetime());
	}
}
