package com.jiuqi.cosmos.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@Component
public class HttpUtils {
	private PoolingHttpClientConnectionManager cm;

	public HttpUtils() {
		this.cm = new PoolingHttpClientConnectionManager();
		// 设置最大连接数
		this.cm.setMaxTotal(100);
		// 设置每个主机的最大连接数
		this.cm.setDefaultMaxPerRoute(10);
	}

	/**
	 * 根据请求地址下载页面数据
	 * 
	 * @param url
	 * @return页面数据
	 */
	public String doGetHtml(String url) {
		// 获取httpclient对象
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();
		// 创建httpget请求对象，设置url
		HttpGet httpGet = new HttpGet(url);
		// 设置请求信息
		httpGet.setConfig(this.getConfig());
		// 使用httpclient发起请求，获取响应
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			System.out.println("请求的响应码： " + response.getStatusLine().getStatusCode());
			System.out.println("请求的响应体： " + response.getEntity());
			if (response.getStatusLine().getStatusCode() == 200) {
				// 判断响应体是否不为空，如果不为空就可以使用EntityUtils
				if (response.getEntity() != null) {
					String content = EntityUtils.toString(response.getEntity(), "utf-8");
					return content;
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	// 设置请求信息
	private RequestConfig getConfig() {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(1000)// 创建连接的最长时间
				.setConnectionRequestTimeout(500)// 请求的最长时间
				.setSocketTimeout(10000)// 数据传输的最长时间
				.build();
		return config;
	}

	/**
	 * 下载图片
	 * 
	 * @param url
	 * @return图片
	 */
	public String doGetImage(String url) {
		// 获取httpclient对象
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();
		// httpClient.getParams().setParameter("http.protocol.content-charset",
		// "UTF-8");

		// 创建httpget请求对象，设置url
		HttpGet httpGet = new HttpGet(url);
		// 设置请求信息
		httpGet.setConfig(this.getConfig());
		// 使用httpclient发起请求，获取响应
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			response.setHeader("Content-Type", "image/jpeg");
			if (response.getStatusLine().getStatusCode() == 200) {
				// 判断响应体是否不为空，如果不为空就可以使用EntityUtils
				if (response.getEntity() != null) {
					// 下载图片
					// 截取图片的后缀名：
					String imgUrl = url.substring(0, url.lastIndexOf("?"));
					String suffix = imgUrl.substring(imgUrl.lastIndexOf("."));
					// 创建图片名，重命名图片
					// https://i3.meishichina.com/attachment/pai/2019/10/23/2019102315717839124636731806550.JPG?x-oss-process=style/c180
					// 下载图片
					// 声明outputstream OutputStreamWriter out = new OutputStreamWriter(new
					// FileOutputStream(“dd.txt”),”UTF-8”);
					InputStream in = response.getEntity().getContent();
					String picName = null;
					int b;
					picName = UUID.randomUUID().toString().replace("-", "") + suffix;
					OutputStream osOutputStream = new FileOutputStream(
							new File("D:\\software\\123-JAVA\\temp\\" + picName));
					while ((b = in.read()) != -1) {
						osOutputStream.write(b);
					}
					// 返回图片名称
					return picName;
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 解析响应，如果下载失败返回空串

		return "";
	}

}
