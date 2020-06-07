package com.jiuqi.cosmos.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jiuqi.cosmos.constants.FtpCommons;
import com.jiuqi.cosmos.util.FtpUtils;

@RestController
@RequestMapping("/uploadController")
public class UploadController {

	@RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
	public String saveHeaderPic(@RequestParam("file") MultipartFile file) throws Exception {
		// 文件保存路径
		String filePath = FtpCommons.headImgRealPath; // 映射的地址
		String vitrualPath = FtpCommons.headImgVirtualPath;
		// 获取file图片名称
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") );
		String uploadFile = FtpUtils.uploadFile(filePath, file.getBytes(),
				UUID.randomUUID().toString().replaceAll("-", "") + suffix,vitrualPath);
		return uploadFile;
	}
}