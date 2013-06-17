package com.ystech.core.img;

import java.io.File;

/** 
 * @author 作者  舒三战
 * @version 创建时间：2012-11-9 下午4:02:32 
 * 类说明 
 **/
public class ImageInfio {
	//源文件大小类
	private Dimension srcDeimension;
	
	//源文件
	private File srcImage;
	
	//相册封面
	private File albumImage;
	
	
	private File bannelImage;


	public Dimension getSrcDeimension() {
		return srcDeimension;
	}


	public void setSrcDeimension(Dimension srcDeimension) {
		this.srcDeimension = srcDeimension;
	}


	public File getSrcImage() {
		return srcImage;
	}


	public void setSrcImage(File srcImage) {
		this.srcImage = srcImage;
	}


	public File getAlbumImage() {
		return albumImage;
	}


	public void setAlbumImage(File albumImage) {
		this.albumImage = albumImage;
	}


	public File getBannelImage() {
		return bannelImage;
	}


	public void setBannelImage(File bannelImage) {
		this.bannelImage = bannelImage;
	}
	
}
