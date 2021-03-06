/**
 * 
 */
package com.ystech.aqtp.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.jasper.tagplugins.jstl.core.Param;
import org.aspectj.apache.bcel.generic.DDIV;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.ystech.aqtp.model.ChickenBatch;
import com.ystech.aqtp.model.Dimensiona;
import com.ystech.aqtp.model.DimensionaCode;
import com.ystech.aqtp.model.User;
import com.ystech.aqtp.pdf.ItextPdfManageImpl;
import com.ystech.aqtp.service.ChickenBatchManageImpl;
import com.ystech.aqtp.service.DimensionaCodeManageImpl;
import com.ystech.aqtp.service.DimensionaManageImpl;
import com.ystech.core.dao.support.Page;
import com.ystech.core.security.SecurityUserHolder;
import com.ystech.core.util.DateUtil;
import com.ystech.core.util.ParamUtil;
import com.ystech.core.util.PathUtil;
import com.ystech.core.util.ZipUtils;
import com.ystech.core.web.BaseController;

/**
 * @author shusanzhan
 * @date 2013-7-12
 */
@Component("dimensionaAction")
@Scope("prototype")
public class DimensionaAction extends BaseController{
	private Dimensiona dimensiona;
	private DimensionaCodeManageImpl dimensionaCodeManageImpl;
	private DimensionaManageImpl dimensionaManageImpl;
	private ChickenBatchManageImpl chickenBatchManageImpl;
	public Dimensiona getDimensiona() {
		return dimensiona;
	}
	public void setDimensiona(Dimensiona dimensiona) {
		this.dimensiona = dimensiona;
	}
	@Resource
	public void setDimensionaCodeManageImpl(
			DimensionaCodeManageImpl dimensionaCodeManageImpl) {
		this.dimensionaCodeManageImpl = dimensionaCodeManageImpl;
	}
	@Resource
	public void setDimensionaManageImpl(DimensionaManageImpl dimensionaManageImpl) {
		this.dimensionaManageImpl = dimensionaManageImpl;
	}
	@Resource
	public void setChickenBatchManageImpl(
			ChickenBatchManageImpl chickenBatchManageImpl) {
		this.chickenBatchManageImpl = chickenBatchManageImpl;
	}
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String bathNoEdit() throws Exception {
		return "bathNoEdit";
	}
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		HttpServletRequest request = getRequest();
		List<ChickenBatch> chickenBatchs = chickenBatchManageImpl.getAll();
		request.setAttribute("chickenBatchs", chickenBatchs);
		return "edit";
	}
	/**
	 * 功能描述：列表查询
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String queryList() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer pageSize = ParamUtil.getIntParam(request, "pageSize", 10);
		Integer pageNo = ParamUtil.getIntParam(request, "currentPage", 1);
		String name = request.getParameter("name");
		Page page=null;
		if(null!=name&&name.trim().length()>0){
			 page= dimensionaManageImpl.pageQuery(pageNo, pageSize, "from Dimensiona where name like '%"+name+"%'", new Object[]{});
		}else{
			page= dimensionaManageImpl.pageQuery(pageNo, pageSize, "from Dimensiona ", new Object[]{});
		}
		request.setAttribute("page", page);
		return "list";
	}
	/**
	 * 功能描述：查看二维码图片明细
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String queryDimCodeList() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer pageSize = ParamUtil.getIntParam(request, "pageSize", 10);
		Integer pageNo = ParamUtil.getIntParam(request, "currentPage", 1);
		Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);
		Page page = dimensionaCodeManageImpl.pagedQuerySql(pageNo, pageSize, DimensionaCode.class, "select * from DimensionaCode where dimensionaId=?", new Object[]{dbid});
		Dimensiona dimensiona2 = dimensionaManageImpl.get(dbid);
		request.setAttribute("page", page);
		request.setAttribute("dimensiona", dimensiona2);
		return "dimCodeList";
	}
	/**
	 * 功能描述：保存二维码
	 * 参数描述：二维码的编码为该批次的最后一次号码
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public void save() throws Exception {
		HttpServletRequest request = getRequest();
		Integer chickenBatchId = ParamUtil.getIntParam(request, "chickenBatchId", -1);
		if(chickenBatchId<0){
			renderErrorMsg(new Throwable("鸡批次出现错误"), "");
			return ;
		}
		try {
			ChickenBatch chickenBatch = chickenBatchManageImpl.get(chickenBatchId);
			
			User user = SecurityUserHolder.getCurrentUser();
			dimensiona.setCreateDate(new Date());
			dimensiona.setUser(user);
			dimensiona.setChickenbatch(chickenBatch);
			
			Integer quantity = dimensiona.getQuantity();
			dimensionaManageImpl.save(dimensiona);
			List<DimensionaCode> dimensionaCodes2 = dimensionaCodeManageImpl.find("from DimensionaCode where dimensiona.chickenbatch.dbid=? order by code Desc", new Object[]{chickenBatch.getDbid()});
			Integer code=0;
			if(null!=dimensionaCodes2&&dimensionaCodes2.size()>0){
				DimensionaCode dimensionaCode = dimensionaCodes2.get(0);
				code=dimensionaCode.getCode();
			}
			if(quantity>0){
				for (int i = 0; i < quantity; i++) {
					code++; 	 	
					DimensionaCode dimensionaCode=new DimensionaCode();
					dimensionaCode.setCode(code);
					dimensionaCode.setDimensiona(dimensiona);
					String name=dimensiona.getDbid()+""+(i+1);
					String dimensionaPhoto = dimensionaPhoto(dimensiona, name,dimensionaCode);
					dimensionaCode.setPhoto(dimensionaPhoto);
					dimensionaCodeManageImpl.save(dimensionaCode);
				}
			}
			
		} catch (Exception e) {
			log.error("保存二维码信息时发生错误！保存数据失败！"+e);
			e.printStackTrace();
			renderErrorMsg(e, "");
			return ;
		}
		Integer addMethod = ParamUtil.getIntParam(request, "addMethod", 0);
		if(addMethod==2){
			renderMsg("/dimensiona/queryList", "保存数据成功！");
		}
		if(addMethod==1){
			renderMsg("/chickenBatch/index?dbid="+chickenBatchId, "添加二维码信息成功！");
		}
		return ;
	}
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		HttpServletRequest request = getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid",-1);
		List<ChickenBatch> chickenBatchs = chickenBatchManageImpl.getAll();
		request.setAttribute("chickenBatchs", chickenBatchs);
		if(dbid>-1){
			Dimensiona dimensiona = dimensionaManageImpl.get(dbid);
			request.setAttribute("dimensiona", dimensiona);
		}
		return "edit";
	}
	/**
	 * 功能描述：删除二维码信息
	 * 参数描述：二维码ID
	 * 逻辑描述：根据二维码ID，删除二维码图片；在删除二维码信息
	 * @return
	 * @throws Exception
	 */
	public void delete() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer[] dbids = ParamUtil.getIntArraryByDbids(request,"dbids");
		if(null!=dbids&&dbids.length>0){
			try {
				for (Integer dbid : dbids) {
					dimensionaCodeManageImpl.deleteBDimensionaDbid(dbid);
					dimensionaManageImpl.deleteById(dbid);
				}
			} catch (Exception e) {
				e.printStackTrace();
				renderErrorMsg(e, "");
				return ;
			}
		}
		Integer addMethod = ParamUtil.getIntParam(request, "addMethod", 0);
		if(addMethod==2){
			String query = ParamUtil.getQueryUrl(request);
			renderMsg("/dimensiona/queryList"+query, "删除数据成功！");
		}
		if(addMethod==1){
			Integer chickenBatchId = ParamUtil.getIntParam(request, "chickenBatchDbid", 0);
			renderMsg("/chickenBatch/index?dbid="+chickenBatchId, "删除数据成功！");
		}
		
		return ;
	}
	/**
	 * 功能描述：删除二维图片信息
	 * 参数描述：二维码ID
	 * 逻辑描述：根据二维码ID，删除二维码图片；在删除二维码信息
	 * @return
	 * @throws Exception
	 */
	public void deleteCode() throws Exception {
		HttpServletRequest request = this.getRequest();
		Integer[] dbids = ParamUtil.getIntArraryByDbids(request,"dbids");
		if(null!=dbids&&dbids.length>0){
			try {
				for (Integer dbid : dbids) {
					DimensionaCode dimensionaCode = dimensionaCodeManageImpl.get(dbid);
					Dimensiona dimensiona2 = dimensionaCode.getDimensiona();
					dimensionaCodeManageImpl.deleteById(dbid);
					Integer quantity = dimensiona2.getQuantity();
					if(quantity>1){
						dimensiona2.setQuantity(quantity-1);
						dimensionaManageImpl.save(dimensiona2);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				renderErrorMsg(e, "");
				return ;
			}
		}
		String query = ParamUtil.getQueryUrl(request);
		renderMsg("/dimensiona/queryDimCodeList"+query, "删除数据成功！");
		return ;
	}
	//生成二维码 
	private String dimensionaPhoto(Dimensiona dimensiona,String name,DimensionaCode dimensionaCode) { 
		String pathFile="";
		 //String content ="dbid:"+dimensiona.getDbid()+";chickenBatchDbid:"+dimensiona.getChickenbatch().getDbid();  // "CN:男;COP:公司;ZW:职务";// 二维码内容
		ChickenBatch chickenbatch = dimensiona.getChickenbatch();
		/* String content ="鸡批次信息:"+chickenbatch.getName()+"为"+chickenbatch.getBatchNo()+",品级为"+chickenbatch.getGrade().getName()+",品系为"+chickenbatch.getBreed().getName()
				 +".出栏总数量为："+chickenbatch.getCountNum()+"\r\n"+"http://cd.qq.com/a/20130815/002866.htm";*/
		String code = getCode(dimensionaCode.getCode().toString());
		String content ="http://www.renanshengtai.com/wapHome/home?batchNo="+chickenbatch.getBatchNo()+code;
	    try {  
	    	String path =  PathUtil.getWebRootPath()+System.getProperty("file.separator")+
	        		"dimensiona"+System.getProperty("file.separator")+
	        		dimensiona.getDbid();
			//判断路径是否存在，如果不存在，创建路径
			File filePath = new File(path);
			boolean exists = filePath.exists();
			if (!filePath.exists()) {
				FileUtils.forceMkdir(filePath);
			}
			
	        pathFile   = PathUtil.getWebRootPath()+System.getProperty("file.separator")+
	        		"dimensiona"+System.getProperty("file.separator")+
	        		dimensiona.getDbid()+System.getProperty("file.separator")+
	        		name+".png";
	        BitMatrix byteMatrix;  
	        byteMatrix = new MultiFormatWriter().encode(new String(content.getBytes("UTF-8"),"iso-8859-1"),  
	                BarcodeFormat.QR_CODE, 100, 100);  
	        File file = new File(pathFile);  
	        MatrixToImageWriter.writeToFile(byteMatrix, "png", file);  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	        return null;
	    }  
	    String pathResult = pathFile.replaceAll("\\\\", "/").replace(PathUtil.getWebRootPath(), "");
	    return pathResult;
	}
	/**
	 * @param code
	 */
	private String getCode(String code) {
		String codeNum="";
		int length = code.length();
		for (int i = 0; i < 5-length; i++) {
			codeNum=codeNum+"0";
		}
		codeNum=codeNum+code;
		return codeNum;
	}
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public String printDimensiona() throws Exception {
		HttpServletRequest request = getRequest();
		Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);
		List<DimensionaCode> dimensionaCodes = dimensionaCodeManageImpl.findBy("dimensiona.dbid", dbid);
		Dimensiona dimensiona2 = dimensionaManageImpl.get(dbid);
		request.setAttribute("dimensionaCodes", dimensionaCodes);
		request.setAttribute("dimensiona", dimensiona2);
		return "printDimensiona";
	}
	/**
	 * 功能描述：
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public void exportPDF() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		Integer dbid = ParamUtil.getIntParam(request, "dbid", -1);
		try{
			Dimensiona dimensionas = dimensionaManageImpl.get(dbid);
			ChickenBatch chickenBatch = dimensionas.getChickenbatch();
			List<DimensionaCode> dimensionaCodes=new ArrayList<DimensionaCode>();
			if(null!=dimensionas){
				 dimensionaCodes = dimensionaCodeManageImpl.findBy("dimensiona.dbid", dimensionas.getDbid());
			}
			ItextPdfManageImpl imItextPdfManageImpl=new ItextPdfManageImpl();
			//生存PDF文件
			String createPdf = imItextPdfManageImpl.createPdf(chickenBatch,dimensionas,dimensionaCodes);
			
			File file = new File(createPdf);
			byte[] b = new byte[100];
			int len = 0;
			InputStream is = new FileInputStream(file);
			// 防止IE缓存
			response.setHeader("pragma", "no-cache");
			response.setHeader("cache-control", "no-cache");
			response.setDateHeader("Expires", 0);

			// 设置编码
			request.setCharacterEncoding("UTF-8");
			// 设置输出的格式
			response.reset();
			response.setContentType("application/pdf;charset=UTF-8");
			//response.setContentType("application/octet-stream;charset=UTF-8");// 解决在弹出文件下载框不能打开文件的问题
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
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ;
	}
}

