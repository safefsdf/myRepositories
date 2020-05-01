package com.jiuqi.cosmos.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.jiuqi.cosmos.pojo.UploadFileStatus;


@CrossOrigin
public class picUtil {
	private static String url = "http://39.101.186.6:80";
    public static String singleFileUpload(MultipartFile pc1 ,String relativePositionToServer) throws IOException {
        try {
            Path path = Paths.get( url+"/"+relativePositionToServer);
            if (!Files.isWritable(path)) {
                Files.createDirectories(path);
            }            
            String extension = pc1.getOriginalFilename().substring(pc1.getOriginalFilename().lastIndexOf("."));
            String picname = GuidUtils.getUuid();        
            String relativeAddr = picname  + extension;       
            pc1.transferTo(path);
            return Paths.get(path + "/" + relativeAddr).toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "后端异常...";
        }
    }
 
    
    public static UploadFileStatus getFileStatus(File file, String path) throws FileNotFoundException {
    	 UploadFileStatus fileStatus = new UploadFileStatus();
         // 上传到服务器后的文件名
         fileStatus.setFileName("test2");
         // 上传到服务器的哪个位置
         fileStatus.setFilePath("/root/myFile/");
         // 文件的大小
         fileStatus.setFileSize(file.length());
         // 文件的类型
         fileStatus.setFileType("png");
         fileStatus.setInputStream(new FileInputStream(file));
		return fileStatus;
    	
    }
    
    /**
     * 接收上传的文件，并且将上传的文件存储在指定路径下
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload")
    public String upload(HttpServletRequest request) {

        ServletInputStream sis = null;
        FileOutputStream fos = null;
        try {
            // 文件名
            String filename = request.getHeader("fileName");
            // 文件类型，例如：jpg、png、pdf...
            String fileType = request.getHeader("fileType");
            // 存储路径
            String filePath = request.getHeader("filePath");

            File file = new File(filePath+filename+"."+fileType);
            if(!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if(!file.exists()) {
                file.createNewFile();
            }

            sis = request.getInputStream();
            fos = new FileOutputStream(file);
            byte[] content = new byte[1024];
            int len = 0;
            while((len=sis.read(content)) > -1) {
                fos.write(content, 0, len);
            }
            fos.flush();

            return "success";
        } catch (Exception ex) {
            ex.printStackTrace();

            return "fail";
        } finally {
            try {
                if(sis!=null) {
                    sis.close();
                }
                if(fos!=null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

