package com.ystech.core.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.core.img.FileNameUtil;
import com.ystech.core.util.DateUtil;
import com.ystech.core.util.PathUtil;
import com.ystech.core.util.ZipUtils;



@Component("swfUploadAction")
@Scope("prototype")
public class SwfUploadAction extends BaseController{
	private File file;
	private String fileFileName;
	private String fileContentType;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	//文件上传！出现异常文能解决
	public void uploadFile() {
		File dataFile = null;
		String filePath=null;
		try{
			if (null!=file&&!file.getName().trim().equals("")) {// getName()返回文件名称，如果是空字符串，说明没有选择文件。
				System.err.println("==============================================="+fileFileName);
				dataFile = FileNameUtil.getResourceFile(fileFileName);
				file.renameTo(dataFile);
				filePath=dataFile.getAbsolutePath();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		if (dataFile == null && !dataFile.exists()) {
			renderText("failed|上传失败");
			return;
		}
		renderText("success|"+ filePath.replaceAll("\\\\", "/").replace(PathUtil.getWebRootPath(), ""));
		return ;
	}
	public void uploadImages() throws Exception {
		File dataFile = null;
		String CKEditorFuncNum = this.getRequest().getParameter("CKEditorFuncNum");  
		if (null!=file&&!file.getName().trim().equals("")) {// getName()返回文件名称，如果是空字符串，说明没有选择文件。
			if (!new File(PathUtil.getWebRootPath() + System.getProperty("file.separator") + "archives"
					+ System.getProperty("file.separator") + DateUtil.format(new Date())).exists()) {
				FileUtils.forceMkdir(new File(PathUtil.getWebRootPath() + System.getProperty("file.separator")
						+ "archives" + System.getProperty("file.separator") + DateUtil.format(new Date())));
			}
			
			dataFile = new File(PathUtil.getWebRootPath() + System.getProperty("file.separator") + "archives"
					+ System.getProperty("file.separator") + DateUtil.format(new Date())
					+ System.getProperty("file.separator") + fileFileName);
			file.renameTo(dataFile);
		}
		if (dataFile == null && !dataFile.exists()) {
			//renderText(response, "failed|上传失败");
			renderText("failed|上传失败");
			return;
		}
		HttpServletResponse response = this.getResponse();
		response.getWriter().write("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("  
                + CKEditorFuncNum  
                + ", '"  
                + dataFile.getAbsolutePath().replaceAll("\\\\", "/").replace(PathUtil.getWebRootPath(),"")  
                + "' , '"  
                + ""  
                + "');</script>");
		//renderText("success|"+ dataFile.getAbsolutePath().replaceAll("\\\\", "/").replace(PathUtil.getWebRootPath(), ""));
	}
		
	

	
	public String userSelect() throws Exception{
		return "userSelect";
	}
	
	public void downLoad() throws Exception {
		HttpServletRequest request = this.getRequest();
		HttpServletResponse response = this.getResponse();
		String path = URLDecoder.decode(request.getParameter("path"), "utf-8");
		File file = new File(PathUtil.getWebRootPath() + path);
		byte[] b = new byte[100];
		int len = 0;
		System.out.println("=================" + request.getCharacterEncoding());
		System.out.println("hello++++++" + path);
		System.out.println("hello ++++fileName" + file.getName());
		InputStream is = new FileInputStream(file);

		// 防止IE缓存
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setDateHeader("Expires", 0);

		// 设置编码
		request.setCharacterEncoding("UTF-8");
		// 设置输出的格式
		response.reset();

		response.setContentType("application/octet-stream;charset=UTF-8");// 解决在弹出文件下载框不能打开文件的问题
		// 解决在弹出文件下载框不能打开文件的问题
		response.addHeader("Content-Disposition", "attachment;filename=" + ZipUtils.toUtf8String(file.getName()));// );


		ServletOutputStream outputStream = response.getOutputStream();
		// 循环取出流中的数据
		while ((len = is.read(b)) > 0) {
			outputStream.write(b, 0, len);
		}
		is.close();
		response.flushBuffer();
		outputStream.flush();
		outputStream.close();
		directlyOutput(request);
		return;
	}
	//删除文件
	public void deleteFile() throws Exception{
		HttpServletRequest request = this.getRequest();
		String fileUrl = request.getParameter("fileUrl");
		String path = URLDecoder.decode(request.getParameter("fileUrl"), "utf-8");
		try{
			File file = new File(PathUtil.getWebRootPath() + path);
			boolean delete = file.delete();
			if (delete==true) {
				renderText("success");
				return ;
			}else{
				renderText("failed");
				return ;
			}
		}catch (Exception e) {
			e.printStackTrace();
			renderText("failed");
		}
		return ;
	}
	
	public String departmentSelect() throws Exception {

		return "departmentSelect";
	}
	
	
	public static void main(String[] args) {
		String abc="1pu";
		System.out.println("==========="+abc.replace("pu", ""));
		
	}
	/** 
	* 替换文件上传中出现的错误信息 
	* */  
	@Override  
	public void addActionError(String anErrorMessage) {  
	           //这里要先判断一下，是我们要替换的错误，才处理  
	    if (anErrorMessage.startsWith("the request was rejected because its size")) {  
	                  //这些只是将原信息中的文件大小提取出来。  
	        Matcher m = Pattern.compile("\\d+").matcher(anErrorMessage);  
	        String s1 = "";  
	        if (m.find())   s1 = m.group();  
	        String s2 = "";  
	        if (m.find())   s2 = m.group();  
	                   //偷梁换柱，将信息替换掉  
	        super.addActionError("你上传的文件（" + s1 + "）超过允许的大小（" + s2 + "）");  
	    } else {//不是则不管它  
	        super.addActionError(anErrorMessage);  
	    }  
	}  
	public String selfUpload() throws Exception {
		return "selfUpload";
	}
	public String save() throws Exception {
		HttpServletRequest request = this.getRequest();
		String userName = request.getParameter("userName");
		
		return "selfUpload";
	}
}
