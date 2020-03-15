package com.jiuqi.cosmos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.jiuqi.cosmos.constants.UserConstants;
import com.jiuqi.cosmos.util.HttpUtils;


public class TestSelectText {
	public static void main(String[] args) throws IOException {
		
		String url = "https://www.douguo.com/cookbook/1714005.html";
		HttpUtils utils = new HttpUtils();
		String doGetHtml = utils.doGetHtml(url);
		PrintStream ps = new PrintStream(new FileOutputStream(new File("D:\\抓取软件\\testText.txt")));
		ps.println(doGetHtml);
		Document docInfo = Jsoup.parse(doGetHtml);
		Elements urls = docInfo.select("div#content>div#left");

		Element ele = urls.get(0);
		Elements select = ele.select("div.rinfo>div.vcnum").next("div");
		String attr = select.get(0).select("a.author-img").attr("href");
		System.out.println("-----------");
	}
	public void get() throws IOException {
		
		
		File file = new File("D:\\抓取软件\\testText.txt");
		FileReader reader = new FileReader(file);// 定义一个fileReader对象，用来初始化BufferedReader
		BufferedReader bReader = new BufferedReader(reader);// new一个BufferedReader对象，将文件内容读取到缓存
		StringBuilder sb = new StringBuilder();// 定义一个字符串缓存，将字符串存放缓存中
		String s = "";
		while ((s = bReader.readLine()) != null) {// 逐行读取文件内容，不读取换行符和末尾的空格
			sb.append(s + "\n");// 将读取的字符串添加换行符后累加存放在缓存中
		}
		bReader.close();
		String str = sb.toString();
	}
}
