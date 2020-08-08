package com.dmz.zrw.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class FileUploadUtils {


    public static Map<String, Object> parseRequest(HttpServletRequest request) {

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = request.getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        //设置一个缓存仓库，如果文件很大，那么就边缓存边上传
        factory.setRepository(repository);
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        //设置上传的文件名中文乱码问题
        upload.setHeaderEncoding("utf-8");
        // bytes
        //upload.setFileSizeMax(1024);
        // Parse the request
        Map<String, Object> params = new HashMap<>();
        try {
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iterator = items.iterator();
            while (iterator.hasNext()){
                FileItem fileItem = iterator.next();
                if(fileItem.isFormField()){
                    //是一个常规的form表单数据
                    processFormField(fileItem, params);
                }else {
                    //上传的文件
                    processUploadedFile(fileItem, params, request);
                }
            }
            //map里面有哪些数据？
            System.out.println(params);
            //  BeanUtils.populate(product, params)
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        return params;
    }

    private static void processUploadedFile(FileItem fileItem, Map<String, Object> params, HttpServletRequest request) {
        String fieldName = fileItem.getFieldName();//<input type=file name="">得到这个标签的name属性
        String fileName = fileItem.getName();//上传文件的名字
        String s = UUID.randomUUID().toString();//对文件名字加前缀，使其变得独立
        fileName = s + "-" + fileName;
        System.out.println("file:" + fieldName);
        System.out.println("file:" + fileName);
        //取hashcode
        int hashCode = fileName.hashCode();//对文件名进行hash操作
        String hexString = Integer.toHexString(hashCode);//将32位int 变成8位的字符串（2进制转16进制）
        char[] chars = hexString.toCharArray();
        String uploadPath ="upload";
        for (char aChar : chars) {
            uploadPath = uploadPath + "/" + aChar;
        }
        String relativePath = uploadPath + "/" +  fileName;
        //request.getServletContext().getRealPath("")定位到部署的根目录里面去
        String realPath = request.getServletContext().getRealPath(relativePath);//定位到部署的根目录里面去的relative目录下去
        System.out.println(realPath);

        File file = new File(realPath);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            fileItem.write(file);
            params.put(fieldName, relativePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 普通的form表单数据，name属性以及对应的值
     * @param fileItem
     * @param params
     */
    private static void processFormField(FileItem fileItem, Map<String, Object> params) {
        String fieldName = fileItem.getFieldName();
        String value = null;
        //反射吗？
        try {
            value = fileItem.getString("utf-8");
            params.put(fieldName, value);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(fieldName + ":" + value);
    }
}
