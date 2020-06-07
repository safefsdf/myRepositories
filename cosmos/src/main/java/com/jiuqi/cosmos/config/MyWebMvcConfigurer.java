package com.jiuqi.cosmos.config;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
	@Value("${uploadFile.resourceHandler}")
	private String resourceHandler;// 请求 url 中的资源映射，不推荐写死在代码中，最好提供可配置，如 /uploadFiles/**     相对路径

	@Value("${uploadFile.location}")
	private String location;// 上传文件保存的本地目录，使用@Value获取全局配置文件中配置的属性值，如 E:/wmx/uploadFiles/   绝对路径
	
	@Value("${uploadFile.linuxResourceHandler}")
	private String linuxResourceHandler; 
	
	@Value("${uploadFile.linuxLocation}")
	private String linuxLocation;
	/**
	 * 配置静态资源映射
	 *
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 就是说 url 中出现 resourceHandler 匹配时，则映射到 location 中去,location 相当于虚拟路径 "file:///"
		// 映射本地文件时，开头必须是 file:/// 开头，表示协议
		registry.addResourceHandler(resourceHandler).addResourceLocations("file:"+location);	
		registry.addResourceHandler(linuxResourceHandler).addResourceLocations(linuxLocation);
//        registry.addResourceHandler("/files/**").addResourceLocations("file:///home/community_cloud_admin/upload/images/");
//        registry.addResourceHandler("/app/**").addResourceLocations("file:///home/community_cloud_admin/upload/package/");
     //   registry.addResourceHandler("/app/**").addResourceLocations("file:///D:/community_cloud_admin/upload/package/");
		
		 
	}
	

}
