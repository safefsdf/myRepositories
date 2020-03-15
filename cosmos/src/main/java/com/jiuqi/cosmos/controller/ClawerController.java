package com.jiuqi.cosmos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiuqi.cosmos.pojo.R;
import com.jiuqi.cosmos.service.impl.CrawlerServiceImpl;

@RestController
public class ClawerController {
	@Autowired
	CrawlerServiceImpl craw;
	@RequestMapping("hello")
	public R testPathon() {
		try {
			craw.itemTask();
			return new R(200, "suc");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
