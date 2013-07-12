package com.ystech.core.img;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.ystech.core.util.DateUtil;
import com.ystech.core.util.PathUtil;
import com.ystech.core.util.SecurityUserHolder;

/** 
 * @author 作者  舒三战
 * @version 创建时间：2012-11-20 上午9:28:10 
 * 类说明 
 **/
public class FileNameUtil {
	/**
	 * 未上传文件命名
	 * @param fileName
	 * @return
	 */
	public static File getResourceFile(String fileName){
			File dataFile = null;
			try {
				if (null != fileName && !fileName.trim().equals("")) {// getName()返回文件名称，如果是空字符串，说明没有选择文件。
					//保存文件路径规则：WebRoot根目录/archives/username/2012-11-20
					//如：WebRoot/archives/admin/2012-11-20
					String path = PathUtil.getWebRootPath()	+ System.getProperty("file.separator") + 
							"archives"+ System.getProperty("file.separator")
							+ SecurityUserHolder.getCurrentUser().getDbid()
							+ System.getProperty("file.separator")
							+ DateUtil.format(new Date());
					//判断路径是否存在，如果不存在，创建路径
					File file = new File(path);
					boolean exists = file.exists();
					if (!file.exists()) {
						FileUtils.forceMkdir(file);
					}
					//文件命名规则为：20121120805022+加文件名称
					//如：20121120093622psb.jpg
					dataFile = new File(PathUtil.getWebRootPath()
							+ System.getProperty("file.separator") + "archives"
							+ System.getProperty("file.separator")
							+ SecurityUserHolder.getCurrentUser().getDbid()
							+ System.getProperty("file.separator")
							+ DateUtil.format(new Date())
							+ System.getProperty("file.separator")
							+ DateUtil.formatFile(new Date()) + fileName);
					return dataFile;
				} else {

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
}
