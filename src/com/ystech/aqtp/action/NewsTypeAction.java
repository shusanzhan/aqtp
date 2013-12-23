package com.ystech.aqtp.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.News;
import com.ystech.aqtp.model.NewsType;
import com.ystech.aqtp.service.NewsManageImpl;
import com.ystech.aqtp.service.NewsTypeManageImpl;
import com.ystech.core.util.ParamUtil;
import com.ystech.core.web.BaseController;

/** 
 * @author 作者  舒三战
 * @version 创建时间：2013-7-19 下午1:36:06 
 * 类说明 
 **/
@Component("newsTypeAction")
@Scope("prototype")
public class NewsTypeAction extends BaseController{
	private NewsType newsType;
	private NewsTypeManageImpl newsTypeManageImpl;
	private NewsManageImpl newsManageImpl;

	public NewsType getNewsType() {
		return newsType;
	}

	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}

	@Resource
	public void setNewsTypeManageImpl(NewsTypeManageImpl newsTypeManageImpl) {
		this.newsTypeManageImpl = newsTypeManageImpl;
	}

	@Resource
	public void setNewsManageImpl(NewsManageImpl newsManageImpl) {
		this.newsManageImpl = newsManageImpl;
	}

	public String queryList() throws Exception {
		return "list";
	}

	public void save() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer parentId = ParamUtil.getIntParam(request, "parentId", -1);
		if(parentId>0){
			NewsType newsType2 = newsTypeManageImpl.get(parentId);
			newsType.setParent(newsType2);
		}
		try {
			if(null!=newsType.getDbid()&&newsType.getDbid()>0){
				NewsType newsType2 = newsTypeManageImpl.get(newsType.getDbid());
				newsType2.setName(newsType.getName());
				newsType2.setNote(newsType.getNote());
				newsType2.setParent(newsType.getParent());
				newsTypeManageImpl.save(newsType2);
			}else{
				newsTypeManageImpl.save(newsType);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderErrorMsg(e, "");
		}
		renderMsg("/newsType/queryList", "保存数据成功！");
		return;
	}

	public String edit() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);
		if (dbid > 0) {
			NewsType newsType2 = newsTypeManageImpl.get(dbid);
			request.setAttribute("newsType", newsType2);
		}
		return "edit";
	}
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String orderNum() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);
		if (dbid > 0) {
			List<NewsType> newsTypes = newsTypeManageImpl.find("from DishType where parent.dbid=? order by orderNum", new  Object[]{dbid});
					/*findBy("parent.dbid",dbid);*/
			request.setAttribute("newsTypes", newsTypes);
		}
		return "orderNum";
	}
	public void delete() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);
		try {
			List<News> neList = newsManageImpl.findBy("newstype.dbid", dbid);
			if(null!=neList&&neList.size()>0){
				renderErrorMsg(new Throwable("删除数据失败！该菜品类型["+neList.get(0).getNewstype().getName()+"]有新闻在使用中！"),"");
				return ;
			}
			newsTypeManageImpl.deleteById(dbid);
		} catch (Exception e) {
			e.printStackTrace();
			renderErrorMsg(e,"");
			return ;
		}
		renderMsg("/newsType/queryList", "删除数据成功！");
		return;
	}
	
	public void jsonNewsType() {
		HttpServletRequest request = this.getRequest();
		//新闻类型管理页面 获取数据数据为1；新闻发布选择数据2
		Integer urlType = ParamUtil.getIntParam(request, "urlType", -1);
		try {
			List<NewsType> newsTypes = newsTypeManageImpl.executeSql("SELECT * FROM NewsType WHERE parentId IS NULL",new Object[] {  });
			if (null != newsTypes && newsTypes.size() > 0) {
				JSONObject jsonObject = makeJSONObject(newsTypes.get(0),urlType);
				renderJson(jsonObject.toString());
			} else {
				renderJson("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}
	/**
	 * 将传入的对象转化为JSON数据格式
	 * 
	 * @throws JSONException
	 */
	private JSONObject makeJSONObject(NewsType newsType,Integer urlType) throws JSONException {
		JSONObject jObject = new JSONObject();
		List<NewsType> children = newsTypeManageImpl.find("from NewsType where parent.dbid=? ",	new Object[] { newsType.getDbid() });
		if (null != children && children.size() > 0) {// 如果resource不为空
			if (newsType.getParent() != null&&newsType.getParent().getDbid()>0) {
				jObject.put("icon","/widgets/ztree/css/zTreeStyle/img/diy/2.png");// 菜单阶段
				if(urlType==2){//不可选
					jObject.put("nocheck", true);
				}
			} else {
				jObject.put("root", "root");
				jObject.put("icon","/widgets/ztree/css/zTreeStyle/img/diy/1_open.png");// 根节点
				if(urlType==2){//不可选
					jObject.put("nocheck", true);
				}
			}
			jObject.put("id", newsType.getDbid());
			jObject.put("name", newsType.getName());
			jObject.put("open", true);
			jObject.put("children", makeJSONChildren(children,urlType));
			return jObject;
		} else {
			if (newsType.getParent() != null&&newsType.getParent().getDbid()>0) {
				jObject.put("icon","/widgets/ztree/css/zTreeStyle/img/diy/2.png");// 菜单阶段
			} else {
				jObject.put("root", "root");
				jObject.put("icon","/widgets/ztree/css/zTreeStyle/img/diy/1_open.png");// 根节点
			}
			jObject.put("id", newsType.getDbid());
			jObject.put("name", newsType.getName());
			return jObject;
		}
	}
	/**
	 * 将部门数据生成可以编辑的JSON格式
	 * **/
	private JSONArray makeJSONChildren(List<NewsType> children,Integer urlType)
			throws JSONException {
		JSONArray jsonArray = new JSONArray();
		for (NewsType newsType : children) {
			JSONObject subJSONjObject = makeJSONObject(newsType,urlType);
			jsonArray.put(subJSONjObject);
		}
		return jsonArray;
	}
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public void getNewsTypeByDbid() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);
		JSONObject object=new JSONObject();
		if(dbid>0){
			NewsType newsType2 = newsTypeManageImpl.get(dbid);
			if(null!=newsType2){
				object.put("dbid", newsType2.getDbid());
				object.put("name", newsType2.getName());
				object.put("note", newsType2.getNote());
				renderJson(object.toString());
			}else{
				renderText("error");
				return ;
			}
		}
		else{
			renderText("error");
			return ;
		}
		return ;
	}
}
