package com.jiuqi.cosmos.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.jiuqi.cosmos.constants.UserConstants;
import com.jiuqi.cosmos.dao.ClassifyInfoMapper;
import com.jiuqi.cosmos.dao.FoodRecipeMapper;
import com.jiuqi.cosmos.dao.FoodStepMapper;
import com.jiuqi.cosmos.dao.UserDao;
import com.jiuqi.cosmos.entity.ClassifyInfo;
import com.jiuqi.cosmos.entity.FoodRecipe;
import com.jiuqi.cosmos.entity.FoodStep;
import com.jiuqi.cosmos.entity.User;
import com.jiuqi.cosmos.util.HttpUtils;
import com.jiuqi.cosmos.util.RandomUtil;

@Service
public class CrawlerServiceImpl {

	@Autowired
	public ClassifyInfoMapper classifyDao;
	@Autowired
	public FoodRecipeMapper foodRecipeDao;
	@Autowired
	public FoodStepMapper foodStepDao;
	@Autowired
	public UserDao userDao;
	@Autowired
	private HttpUtils httpUtils;

	public void itemTask() throws Exception {
		String url = "https://www.douguo.com/fenlei";
		String html = httpUtils.doGetHtml(url);
		Document doc = Jsoup.parse(html);
		getLevelOne(doc);
		 
	}

	public void getLevelOne(Document doc) {
		String levelThreeUrl = "";
		String levelThreeText = "";
		Elements levelOne = doc.select("#content>h2.sorttit");
		for (Element content : levelOne) {
			ClassifyInfo selectByClassifyName = classifyDao.selectByClassifyName(content.text());
			if(selectByClassifyName!=null) {
				continue;
			}
			ClassifyInfo classifyOne = new ClassifyInfo(content.text(), 0);
			classifyDao.insert(classifyOne);
			System.out.println("One: "+classifyOne.toString());
			Integer levelTwoPid = classifyOne.getClassifyId();
			Elements div_sort_item = content.nextElementSibling().children();
			for(Element con : div_sort_item) {
				String twoTitle = con.select("h2").text();
				ClassifyInfo cataTwo = classifyDao.selectByClassifyName(twoTitle);
				if(cataTwo!=null) {
					continue;
				}
				ClassifyInfo classifyTwo = new ClassifyInfo(twoTitle, levelTwoPid);
				classifyDao.insert(classifyTwo);
				System.out.println("Two: "+classifyTwo.toString());
				Integer levelThreePid = classifyTwo.getClassifyId();
				Elements li = con.select("ul.sortlist").get(0).children();
				for(Element l : li) {
					levelThreeText = l.select("a").text();
					levelThreeUrl = l.select("a").attr("href");
					ClassifyInfo cataThree = classifyDao.selectByClassifyName(levelThreeText);
					if(cataThree!=null) {
						continue;
					}
					ClassifyInfo classifyThree = new ClassifyInfo(levelThreeText, levelThreePid);
					classifyDao.insert(classifyThree);
					Integer recipePId = classifyThree.getClassifyId();
					System.out.println(classifyThree.toString());
					accessLevelThree(levelThreeUrl, recipePId);
				}
			}
		}
	}

	/**
	 * 针对三级分类获取的链接，获取该分类下的每一个食谱的url地址
	 * @param levelThreeUrl
	 * @param threePId
	 */
	public void accessLevelThree(String levelThreeUrl, Integer threePId) {
		String infoUrl = UserConstants.CRAWLERURL + levelThreeUrl;
		String[] urlPages = { infoUrl + "/0/0", infoUrl + "/0/20", infoUrl + "/0/40" };
		for (String url : urlPages) {
			String html = httpUtils.doGetHtml(url);
			System.out.println("accessLevelThree中请求: "+url+" 成功");
			Document docInfo = Jsoup.parse(html);
			getLevelSon(docInfo, threePId);
		}
	}

	/**
	 * 获取
	 * @param doc
	 * @param recipePId
	 */
	public void getLevelSon(Document doc, Integer recipePId) {
		try {
			Elements urls = doc.select(".mt25>ul.cook-list>li");
			Element element = urls.get(0);
			for (Element contentLi : urls) {
				String attr = UserConstants.CRAWLERURL + contentLi.select("a.cook-img").attr("href");
				String html = httpUtils.doGetHtml(attr);
				System.out.println("getLevelSon中请求: "+attr+" 成功");
				Document docInfo = Jsoup.parse(html);
				getLastToRecipe(docInfo, recipePId);
			}
		} catch (Exception e) {
			System.out.println(" java.lang.IndexOutOfBoundsException: Index: 0, Size: 0  CrawlerServiceImpl.getLevelSon");
		}
	}
	/**
	 *	 获取 食谱 存如数据库
	 * @param doc
	 * @param classifyPid
	 */
	public void getLastToRecipe(Document doc, Integer classifyPid) {
		Elements urls = doc.select("div#content>div#left");
		try {
			Element ele = urls.get(0);
			// 食谱的封面图片
			String coverImg = ele.select("#banner>a").attr("href");
			// 食谱的标题
			String recipeTitle = ele.select("div.rinfo>h1.title").text();
			// 食谱介绍： ele.select("div.rinfo>p.intro").text();
			String recipeIntro = ele.select("div.rinfo>p.intro").text();
			// 用法用量
			Elements recipeUsageEles = ele.select("div.metarial td");
			Map<String, String> usageMap = new HashMap<String, String>();
			for (Element recipeUsageEle : recipeUsageEles) {
				String usageMapKey = recipeUsageEle.select("span.scname").text();
				String usageMapValue = recipeUsageEle.select("span.scnum").text();
				usageMap.put(usageMapKey, usageMapValue);
			}
			String recipeUsage = JSON.toJSONString(usageMap);
			// 用户的Id:获取用户图片的链接，解析，获取图片，获取昵称，获取个性签名，性别，地址，自定义账号密码，.get(0).select("div.author-info>a")
			Elements userSelect = ele.select("div.rinfo>div.vcnum").next("div");
			String userHref = userSelect.get(0).select("a.author-img").attr("href");
			String userUrl = UserConstants.CRAWLERURL + userHref;
			
			Integer userId = parseUserUrl(userUrl);
			if(userId==null) {
				return;
			}
			StringBuffer sb;
			// 食谱的步骤
			Elements select = ele.select("div.step>div.stepcont");
			String recipeTips = ele.select("div.tips>p").text();
			
			FoodRecipe recipe = new FoodRecipe(userId, classifyPid, coverImg,
					recipeIntro, recipeTitle, recipeUsage, recipeTips);
			foodRecipeDao.insert(recipe);
			int recipeId = recipe.getRecipeId();
			sb = new StringBuffer();
			for (Element step : select) {
				if(step.child(0)!=null) {
					if( step.child(0).child(0)!=null) {
						String stepImgUrl = step.child(0).child(0).attr("src");
						String stepTitle = step.child(0).child(0).attr("alt");
						String stepDesc = step.child(1).text();
						FoodStep foodStep = new FoodStep(recipeId, stepTitle, stepImgUrl, stepDesc);
						foodStepDao.insert(foodStep);
						sb.append(foodStep.getStepId());
					}
				}
			}
			String recipeSteps = sb.toString();
			// 食谱的小技巧
			
			
			System.out.println(recipe.toString());
		} catch (Exception e) {
			System.out.println("java.lang.IndexOutOfBoundsException: Index: 0, Size: 0");
		}
	}

	/**
	 * 抓取用户的数据，返回用户的主键
	 * 
	 * @param userUrl：用户页面的链接
	 * @return userId
	 */
	private Integer parseUserUrl(String userUrl) {
		String html = httpUtils.doGetHtml(userUrl);
		Document docInfo = Jsoup.parse(html);
		System.out.println("解析user html完成");
		Elements select = docInfo.select("#content>div.person-info>a.person-img");
		String head = select.get(0).attr("style");
		String headImg = "";
		if(head.contains("(") && head.contains(")")) {
			headImg = head.substring(head.indexOf("(") + 1, head.indexOf(")"));
		}
		String nickname = docInfo.select("#content>div.person-info>div.info>a.nickname").text();

		String address = docInfo.select("#content>div.person-info>div.info>p.sex").text();
		String se = docInfo.select("#content>div.person-info>div.info>p.sex>i").attr("class");
		String sex = "female".equalsIgnoreCase(se)?"女":"男";
		String signature = docInfo.select("#content>div.person-info>div.info>p.intro>span").text();
		
		String phone = RandomUtil.getRandomPhone();
		User u = userDao.quaryByPhone(phone);
		while(u!=null) {
			phone = RandomUtil.getRandomPhone();
			u = userDao.quaryByPhone(phone);
		}
		 
		String password = RandomUtil.getRandomPassword(10);
		
		User user = new User(phone, password, nickname, address, headImg, headImg,
				null, sex, signature);
		int createUser = userDao.insert(user);
		if(createUser==1) {
			int userid = user.getUserid();
			return userid;
		}
		return null;
	}
	
	 

}
