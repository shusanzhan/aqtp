package com.ystech.core.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ystech.core.util.PathUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseController extends ActionSupport implements SessionAware{
	protected Logger log = Logger.getLogger(BaseController.class);
	private static final long serialVersionUID = 1L;
	private static final String DIRECTLY_OUTPUT_ATTR_NAME = "directlyOutput";
	private String mess;
	
	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	public void setSession(Map<String, Object> sessionMap) {		
	}	
	
	protected Map<String,Object> getSession(){
		return  ActionContext.getContext().getSession();
	}
	
	protected HttpServletRequest getRequest(){
		return ServletActionContext.getRequest (); 
	}
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	protected static HttpServletResponse getRes() {
		return ServletActionContext.getResponse();
	}
	protected static HttpServletRequest getReq() {
		return ServletActionContext.getRequest (); 
	}
	
	protected void render(String text,String contentType){
		HttpServletResponse response = this.getResponse();
		try {
			response.setContentType(contentType);
			response.getWriter().write(text);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	
	protected void rendOk() {
		setMess("ok");
	}
	
	protected void rendError() {
		setMess("error");
	}
	
	//输出JSON文件
	protected void renderJson(String text) {
		render(text, "text/x-json;charset=UTF-8");
	}
	
	/*输出xml*/
	protected void renderXML(String text) {
		render(text, "text/xml;charset=UTF-8");
	}
	/**
	 * 直接输出纯字符串.
	 */
	protected void renderText(String text) {
		render(text, "text/plain;charset=UTF-8");
	}

	/*保存数据成功处理机制 */
	protected void renderMsg(String url,String message){
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		try {
			jsonObject.put("mark", "0");
			jsonObject.put("url",  getRequest().getContextPath()+url);
			jsonObject.put("message", message);
		} catch (JSONException e) {
			e.printStackTrace();
			log.error("保存数据成功！返回提示信息转换成JSON数据是发生错误！");
		}
		jsonArray.put(jsonObject);
		renderJson(jsonArray.toString());
	}
	
	/**
	 * 当存在引用关系，删除出错时当用户点击确认后，才跳转到前一个操作页面
	 * @param t
	 * @param url
	 */
	protected void renderLsitByOk(String url,String message){
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		try {
			jsonObject.put("mark", "2");
			jsonObject.put("url",  getRequest().getContextPath()+url);
			jsonObject.put("message", message);
		} catch (JSONException e) {
			e.printStackTrace();
			log.error("返回提示信息转换成JSON数据是发生错误！");
		}
		jsonArray.put(jsonObject);
		renderJson(jsonArray.toString());
	}
	
	
	
	//异常处理机制
	protected void renderErrorMsg(Throwable t,String url) {
		try {
			this.getResponse().setLocale(java.util.Locale.CHINESE);
			String msg = t.getMessage();
			//转换为unicode码，解决中文乱码问题
			/*if (msg != null) {
				StringBuffer unicode = new StringBuffer();
				for (int i = 0; i < msg.length(); i++) {
					char c = msg.charAt(i);
					if (c >= 0x0391 && c <= 0xFFE5) {//判断是中文就转unicode
						unicode.append("\\u" + Integer.toHexString(c));
					} else if ("'".equals(c+"") || "\"".equals(c+"")) {
						unicode.append(" ");
					} 
					else unicode.append(c);
				}
				msg = unicode.toString();
			}*/
			/*this.getResponse().addHeader("Error-Json", "{code:2001,msg:'"+msg+"',script:''}");
			this.getResponse().sendError(300);*/
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			try {
				jsonObject.put("mark", "1");
				jsonObject.put("url", getRequest().getContextPath()+url);
				jsonObject.put("message",msg);
			} catch (JSONException e) {
				e.printStackTrace();
				log.error("保存数据失败！返回提示信息转换成JSON数据是发生错误！");
			}
			jsonArray.put(jsonObject);
			renderJson(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void directlyOutput(HttpServletRequest request) {
		request.setAttribute(DIRECTLY_OUTPUT_ATTR_NAME, Boolean.TRUE);
	}
}
