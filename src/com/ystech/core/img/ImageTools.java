package com.ystech.core.img;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;

 
/**
 * ImageMagick和im4java处理图片
 * @author shusanzhan
 */
public class ImageTools {
     
    /**
     * ImageMagick的路径
     */
    public  String imageMagickPath = null;
    public  String graphicsMagick=null;
    public ImageTools(){
    	/**
         * 获取ImageMagick的路径
         */
        //linux下不要设置此值，不然会报错
    	Properties properties=new Properties();
    	InputStream resourceAsStream = getClass().getResourceAsStream("/image.properties");
    	try {
			properties.load(resourceAsStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//属性文件流 
    	imageMagickPath = properties.getProperty("imageMagickPath");
    	System.out.println("====="+imageMagickPath);
    	graphicsMagick=properties.getProperty("graphicsMagick");
    	System.out.println("====="+graphicsMagick);
    }
     
    /**
     * 根据坐标裁剪图片
     *
     * @param srcPath   要裁剪图片的路径
     * @param newPath   裁剪图片后的路径
     * @param x   起始横坐标
     * @param y   起始挫坐标
     * @param x1  结束横坐标
     * @param y1  结束挫坐标
     */
    public  void cutImage(String srcPath, String newPath, int x, int y, int x1,
            int y1)  throws Exception {
        int width = x1 - x;
        int height = y1 - y;
        IMOperation op = new IMOperation();
        op.addImage(srcPath);
         
        /**
         * width：裁剪的宽度
         * height：裁剪的高度
         * x：裁剪的横坐标
         * y：裁剪的挫坐标
         */
        op.crop(width, height, x, y);
         
        op.addImage(newPath);
         
        ConvertCmd convert = new ConvertCmd();
         
        String im=imageMagickPath;
        //linux下不要设置此值，不然会报错
        convert.setSearchPath(imageMagickPath);
         
 
        convert.run(op);
    }
     
    /**
     * 根据尺寸缩放图片
     * @param width  缩放后的图片宽度
     * @param height  缩放后的图片高度
     * @param srcPath   源图片路径
     * @param newPath   缩放后图片的路径
     */
    public  void cutImage(int width, int height, String srcPath, String newPath) throws Exception {
        IMOperation op = new IMOperation();
        op.addImage(srcPath);
         
         
        op.resize(width, height);
        op.addImage(newPath);
 
         
        ConvertCmd convert = new ConvertCmd();
         
        //linux下不要设置此值，不然会报错
        convert.setSearchPath(imageMagickPath);
         
        convert.run(op);
    }
     
     
    /**
     * 根据宽度缩放图片
     * @param width  缩放后的图片宽度
     * @param srcPath   源图片路径
     * @param newPath   缩放后图片的路径
     */
    public  void cutImage(int width, String srcPath, String newPath) throws Exception {
        IMOperation op = new IMOperation();
        op.addImage(srcPath);
         
         
        op.resize(width, null);
        op.addImage(newPath);
         
        ConvertCmd convert = new ConvertCmd();
         
        //linux下不要设置此值，不然会报错
        convert.setSearchPath(imageMagickPath);
         
        convert.run(op);
    }
     
     
     
     
    /**
     * 给图片加水印
     * @param srcPath   源图片路径
     */
    public  void addImgText(String srcPath) throws Exception {
        IMOperation op = new IMOperation();
        op.font("宋体").gravity("southeast").pointsize(64).fill("#red").draw("text 5,5 modo.co");      
         
        op.addImage();
        op.addImage();
        ConvertCmd convert = new ConvertCmd();
         
        //linux下不要设置此值，不然会报错
        convert.setSearchPath(imageMagickPath);
 
        convert.run(op,srcPath,srcPath);
    }
    /** 
     * 先缩放，后居中切割图片 
     * @param srcPath 源图路径 
     * @param desPath 目标图保存路径 
     * @param rectw 待切割在宽度 
     * @param recth 待切割在高度 
     * @throws IM4JavaException  
     * @throws InterruptedException  
     * @throws IOException  
     */  
    public  void cropImageCenter(String srcPath, String desPath, int rectw, int recth) throws IOException, InterruptedException, IM4JavaException  
    {  
        IMOperation op = new IMOperation();  
          
        op.addImage();  
        op.addRawArgs("-sample", "600x600^");  
        op.addRawArgs("-gravity", "center");  
        op.addRawArgs("-extent", "600x600");  
        op.addRawArgs("-quality", "50"); 
        //op.resize(rectw, recth, '^').gravity("center").extent(rectw, recth);  
        op.addImage();  
  
        ConvertCmd convert = new ConvertCmd(true);
        convert.setSearchPath(graphicsMagick);
        //convert.createScript("e:\\test\\myscript.sh",op);  
        convert.run(op, srcPath, desPath);  
    }  
     
    public static void main(String[] args){
    	ImageTools imageTools=new ImageTools();
       /* try {
        	imageTools.cutImage("E:\\20121106_194641.jpg", "E:\\20121106_194641aa.jpg", 400, 300, 370, 320);
		} catch (Exception e) {
			e.printStackTrace();
		}
        try {
        	imageTools.cutImage(200,300, "E:\\20121106_194641.jpg", "E:\\20121106_194641eee.jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        try {
        	imageTools.addImgText("E:\\20121106_194651.jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
        	long startTime = System.currentTimeMillis();
        	String src="E:\\DSC_0989.jpg";
        	for(int i=0;i<100;i++){
        		String target="E:\\src\\20121106_194641"+i+".jpg";
        		imageTools.cropImageCenter(src, target, 120, 150);
        	}
        	System.err.println("Embedded Tomcat started in "
        					+ (System.currentTimeMillis() - startTime) + " ms.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IM4JavaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}