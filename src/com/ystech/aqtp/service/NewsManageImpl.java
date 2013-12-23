/**
 * 
 */
package com.ystech.aqtp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ystech.aqtp.model.News;
import com.ystech.aqtp.model.NewsType;
import com.ystech.core.dao.HibernateEntityDao;
import com.ystech.core.dao.support.Page;

/**
 * @author shusanzhan
 * @date 2013-11-12
 */
@Component("newsManageImpl")
public class NewsManageImpl extends HibernateEntityDao<News>{

	/**
	 * 功能描述：查询图片新闻
	 * @return
	 */
	public List<News> queryBannerNews() {
		//String sql="SELECT * FROM news WHERE isBannerPicture=? ORDER BY releaseDate DESC LIMIT 4";
		String hql="from News where isBannerPicture=? ORDER BY releaseDate DESC LIMIT 4";
		List<News> bannerNews = find(hql, new Object[]{true});
		//List<News> bannerNews = executeSqlQuery(News.class, sql, new Object[]{1}).list();
		return bannerNews;
	}
	
	/**
	 * 功能描述：实时要闻
	 * 参数描述：如果limit为空null，那么默认查询所有数据，如果limit不为空查询limit条数据
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public List<News> queryIsOnTime(Integer limit) throws Exception {
		String hql="";
		if(null==limit||limit<0){
			hql="from News where isOnTime=? and isStop=? ORDER BY releaseDate ";
		}else{
			hql="from News where isOnTime=? and isStop=? ORDER BY releaseDate DESC LIMIT "+limit;
		}
		List<News> onIimeNews = find(hql, new Object[]{true,true});
		return onIimeNews;
	}
	/**
	 * 功能描述：实时要闻 查看更多分页
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public Page<News> queryIsOnTimePage(Integer currentPage,Integer pageSize) throws Exception {
		String hql="from News where isOnTime=? and isStop=? ORDER BY releaseDate DESC ";
		Page page = pageQuery(currentPage, pageSize, hql, new Object[]{true,true});
		return page;
	}
	
	/**
	 * 功能描述：时事要闻
	 * @return
	 * @throws Exception
	 */
	public List<News> queryIsLatestNew(Integer limit) throws Exception {
		String hql="";
		if(null==limit||limit<0){
			 hql="from News where isLatestNew=? and isStop=? ORDER BY releaseDate ";
		}else{
			 hql="from News where isLatestNew=? and isStop=? ORDER BY releaseDate DESC LIMIT "+limit;
		}
		List<News> latestNew = find(hql, new Object[]{true,true});
		return latestNew;
	}

	/**功能描述：查询所有新闻分类栏目
	 * @param pageNo
	 * @param pageSize
	 * @param parendId
	 * @return
	 */
	public Page<News> pageQueryByNewsType(java.lang.Integer pageNo,
			java.lang.Integer pageSize, java.lang.Integer parendId) {
		String hql="from News where 1=1 ";
		String sql="select * from News where 1=1 ";
		List param=new ArrayList();
		if(null!=parendId&&parendId>0){
			hql=hql+" and newstype.dbid=? ";
			sql=sql+" and newsTypeDbid=? ";
			param.add(parendId);
		}
		hql=hql+" and isStop=? ";
		sql=sql+" and isStop=? ";
		param.add(true);
		Page page = pagedQueryHqlSql(pageNo, pageSize, sql, hql, param.toArray());
		return page;
	}


	/**
	 * 功能描述：查询分类下的新闻条数
	 * @param dbid
	 * @return
	 */
	public List<News> listQueryByNewsType(java.lang.Integer dbid) {
		String hql="from News where 1=1 ";
		List param=new ArrayList();
		if(null!=dbid&&dbid>0){
			hql=hql+" and newstype.dbid=? ";
			param.add(dbid);
		}
		hql=hql+" and isStop=? ";
		param.add(true);
		List<News> news = find(hql, param.toArray());
		return news;
	}

	/**
	 * @return
	 */
	public List<News> queryIntroXueRen() {
		String hql="from News where isLatestNew=? and isStop=? ORDER BY releaseDate DESC LIMIT 13";
		List<News> IntroXueRen = find(hql, new Object[]{true,true});
		return IntroXueRen;
	}

	/**
	 * 功能描述：创新实践
	 * 参数描述：如果limit为空null，那么默认查询所有数据，如果limit不为空查询limit条数据
	 * 逻辑描述：
	 * @param i
	 * @return
	 */
	public List<News> queryByNewType(Integer typeId,Integer limit) {
		String hql="";
		if(null==limit||limit<0){
			hql="from News where newstype.dbid=? and isStop=? ORDER BY releaseDate ";
		}else{
			hql="from News where  newstype.dbid=? and isStop=? ORDER BY releaseDate DESC LIMIT "+limit;
		}
		List<News> practices = find(hql, new Object[]{typeId,true});
		return practices;
	}
	

}
