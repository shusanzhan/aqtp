/**
 * 
 */
package com.ystech.aqtp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.Access;
import com.ystech.aqtp.model.BreaderBreed;
import com.ystech.aqtp.model.ChickenBatch;
import com.ystech.aqtp.model.Dimensiona;
import com.ystech.aqtp.model.FeedFeeder;
import com.ystech.aqtp.model.News;
import com.ystech.aqtp.model.NewsType;
import com.ystech.aqtp.service.ChickenBatchManageImpl;
import com.ystech.aqtp.service.NewsManageImpl;
import com.ystech.aqtp.service.NewsTypeManageImpl;
import com.ystech.core.dao.support.Page;
import com.ystech.core.util.ParamUtil;
import com.ystech.core.util.SignUtil;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-11-7
 */
@Component("homeAction")
@Scope("prototype")
public class HomeAction extends BaseController {
	private ChickenBatchManageImpl chickenBatchManageImpl;
	private NewsManageImpl newsManageImpl;
	private NewsTypeManageImpl newsTypeManageImpl;

	@Resource
	public void setChickenBatchManageImpl(
			ChickenBatchManageImpl chickenBatchManageImpl) {
		this.chickenBatchManageImpl = chickenBatchManageImpl;
	}

	@Resource
	public void setNewsManageImpl(NewsManageImpl newsManageImpl) {
		this.newsManageImpl = newsManageImpl;
	}

	@Resource
	public void setNewsTypeManageImpl(NewsTypeManageImpl newsTypeManageImpl) {
		this.newsTypeManageImpl = newsTypeManageImpl;
	}

	/**
	 * 功能描述： 参数描述： 逻辑描述：
	 * 
	 * @return
	 * @throws Exception
	 */
	public String home() throws Exception {
		HttpServletRequest request = getRequest();
		// 查询新闻一级新闻类型
		List<NewsType> newsTypes = newsTypeManageImpl.findBy("parent.dbid", 1);
		request.setAttribute("parents", newsTypes);

		// 图片新闻
		List<News> bannerNews = newsManageImpl.queryBannerNews();
		request.setAttribute("bannerNews", bannerNews);

		List<News> renanIntros = newsManageImpl.findBy("newstype.dbid",
				NewsType.INTRO);
		if (null != renanIntros && renanIntros.size() > 0) {
			request.setAttribute("renanIntro", renanIntros.get(0));
		}

		// 咨询动态
		List<News> isOnTimes = newsManageImpl.findBy("newstype.dbid",
				NewsType.NEWESDT);
		request.setAttribute("isOnTimes", isOnTimes);
		if (null != isOnTimes && isOnTimes.size() > 0) {
			request.setAttribute("topIsOnTime", isOnTimes.get(0));
		}

		return "home";
	}

	/**
	 * 功能描述： 参数描述： 逻辑描述：
	 * 
	 * @return
	 * @throws Exception
	 */
	public String search() throws Exception {
		HttpServletRequest request = this.getRequest();
		String batchNo = getBatchNo(request);
		leftMenu(request);
		try {
			ChickenBatch chickenBatch = chickenBatchManageImpl.findUniqueBy(
					"batchNo", batchNo);
			request.setAttribute("chickenBatch", chickenBatch);
			if(null!=chickenBatch){
				Set<FeedFeeder> feedfeeders = chickenBatch.getFeedfeeders();
				Set<BreaderBreed> breaderbreeds = chickenBatch.getBreaderbreeds();
				Set quarantinecertificates = chickenBatch
						.getQuarantinecertificates();
				Set<Dimensiona> dimensionas = chickenBatch.getDimensionas();
				Set immunes = chickenBatch.getImmunes();
				Set healthcares = chickenBatch.getHealthcares();
				request.setAttribute("chickenBatch", chickenBatch);
				request.setAttribute("feedfeeders", feedfeeders);
				request.setAttribute("quarantinecertificates",
						quarantinecertificates);
				request.setAttribute("dimensionas", dimensionas);
				request.setAttribute("breaderBreeds", breaderbreeds);
				request.setAttribute("immunes", immunes);
				request.setAttribute("healthCares", healthcares);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "search";
	}

	/**
	 * 功能描述：关于我们 模块 参数描述： 逻辑描述：
	 * 
	 * @return
	 * @throws Exception
	 */
	public String intro() throws Exception {
		HttpServletRequest request = this.getRequest();
		// 左边菜单栏目
		Integer pageSize = ParamUtil.getIntParam(request, "pageSize", 16);
		Integer pageNo = ParamUtil.getIntParam(request, "currentPage", 1);

		// 父节点dbid newTypePDbid 一级菜单
		Integer newTypePDbid = ParamUtil.getIntParam(request, "newTypePDbid",
				-1);

		// 子节点dbid newTypePDbid 二级菜单
		Integer newTypeCDbid = ParamUtil.getIntParam(request, "newTypeCDbid",
				-1);
		// 分页列表栏目
		Page<News> page = null;
		try {
			if (newTypePDbid > 0) {
				leftMenu(request);
				// 导航栏目一级导航 名称
				NewsType parent = newsTypeManageImpl.get(newTypePDbid);

				// 左侧栏目
				List<NewsType> childListes = newsTypeManageImpl
						.queryByNewsType(newTypePDbid);
				if (null == childListes || childListes.size() <= 0) {
					// 如果左侧栏目为空，那么添加默认栏目
					childListes = new ArrayList<NewsType>();
					childListes.add(parent);

					// 查询当前类型数据
					page = newsManageImpl.pageQueryByNewsType(pageNo, pageSize,
							newTypePDbid);
					request.setAttribute("page", page);

				} else {
					// 左侧栏目不为空，
					if (newTypeCDbid < 0) {
						// 如果是第一次进入及newTypeCDbid<0 那么 查询第一条二级类型数据
						NewsType newsType = childListes.get(0);
						// 分页列表栏目
						page = newsManageImpl.pageQueryByNewsType(pageNo,
								pageSize, newsType.getDbid());
						request.setAttribute("page", page);
					} else {
						// 如果是第一次进入及newTypeCDbid<0 那么 查询给定二级分类数据
						NewsType child = newsTypeManageImpl.get(newTypeCDbid);
						request.setAttribute("child", child);
						page = newsManageImpl.pageQueryByNewsType(pageNo,
								pageSize, newTypeCDbid);
						request.setAttribute("page", page);
					}
				}

				request.setAttribute("parent", parent);
				request.setAttribute("childListes", childListes);
			} else {
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		if (null != page) {
			List<News> result = page.getResult();
			if (null != result && result.size() == 1) {
				News news = result.get(0);
				Integer readNum = news.getReadNum();
				readNum = readNum + 1;
				news.setReadNum(readNum);
				newsManageImpl.save(news);
			}
		}
		return "intro";
	}

	/**
	 * 功能描述：阅读页面 参数描述： 逻辑描述：
	 * 
	 * @return
	 * @throws Exception
	 */
	public String read() throws Exception {
		HttpServletRequest request = getRequest();
		leftMenu(request);
		java.lang.Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);

		if (dbid > 0) {
			News news = newsManageImpl.get(dbid);
			List<NewsType> childListes = null;
			NewsType newstype = news.getNewstype();
			NewsType parent = newstype.getParent();
			if (parent.getDbid() > 1) {
				// 表明该新闻类型属于三级类型，左边标题栏为父及标题栏目
				childListes = newsTypeManageImpl.queryByNewsType(news
						.getNewstype().getParent().getDbid());

				request.setAttribute("parent", parent);

				request.setAttribute("child", newstype);
			} else {
				// 表明该新闻类型属于二级类型，左边标题栏目为自己
				childListes = new ArrayList<NewsType>();
				childListes.add(newstype);

				request.setAttribute("parent", newstype);
			}
			request.setAttribute("childListes", childListes);

			List<News> listNews = newsManageImpl.listQueryByNewsType(newstype
					.getDbid());
			/* 判断页面是否有上一条，下一条数据 */
			int size = listNews.size();
			int index = 0;
			for (int i = 0; i < listNews.size(); i++) {
				if (news.getDbid() == listNews.get(i).getDbid()) {
					index = i + 1;
					break;
				}
			}
			if (index == size && index == 1) {
				request.setAttribute("news", news);
			}
			if (index == 1 && index < size) {
				request.setAttribute("news", news);
				request.setAttribute("next", listNews.get(index));
			}
			if (index == size && index > 1) {
				request.setAttribute("previous", listNews.get(index - 2));
				request.setAttribute("news", news);
			}
			if (index < size && index > 1) {
				request.setAttribute("previous", listNews.get(index - 2));
				request.setAttribute("news", news);
				request.setAttribute("next", listNews.get(index));
			}
			// 更新阅读数量
			news.setReadNum(news.getReadNum() + 1);
			newsManageImpl.save(news);
		}
		return "read";
	}

	/**
	 * 功能描述：
	 * 
	 * @param request
	 * @throws Exception
	 */
	private void leftMenu(HttpServletRequest request) throws Exception {
		// 咨询动态
		List<News> isOnTimes = newsManageImpl.executeSql("select * from news where newsTypeDbid=? and isStop=? limit 3", new Object[]{NewsType.NEWESDT,1});
		request.setAttribute("latestNews", isOnTimes);

		// 查询新闻一级新闻类型
		List<NewsType> newsTypes = newsTypeManageImpl.findBy("parent.dbid", 1);
		request.setAttribute("parents", newsTypes);

	}
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public void checkWeiXin() throws Exception {
		HttpServletRequest request = this.getRequest();
		HttpServletResponse response = this.getResponse();
        // 微信加密签名 
        String signature = request.getParameter("signature"); 
        // 时间戳 
        String timestamp = request.getParameter("timestamp"); 
        // 随机数 
        String nonce = request.getParameter("nonce"); 
        // 随机字符串 
        String echostr = request.getParameter("echostr"); 
 
        PrintWriter out = response.getWriter(); 
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败 
        if (SignUtil.checkSignature(signature, timestamp, nonce)) { 
        	System.out.println("=============="+echostr);
           // out.print(echostr); 
            out.write(echostr);
        } 
        out.close(); 
        out = null; 
	}
	/**
	 * @param request
	 * @return
	 */
	private String getBatchNo(HttpServletRequest request) {
		String batchNo = request.getParameter("batchNo");
		if (null!=batchNo&&batchNo.trim().length()>0) {
			batchNo=batchNo.substring(0, batchNo.length()-5);
		}else{
			batchNo=null;
		}
		return batchNo;
	}
}
