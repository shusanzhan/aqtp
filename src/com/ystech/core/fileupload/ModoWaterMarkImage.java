package com.ystech.core.fileupload;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;


/** 
 * @author 作者  舒三战
 * @version 创建时间：2012-11-9 上午9:59:35 
 * 类说明：为上传图片进行添加水印操作 
 **/
public class ModoWaterMarkImage {
	
	/**
	 * 传递图片路径
	 * 返回图片保存路径
	 * @param imagePath
	 * @return
	 * @throws IOException 
	 */
	public String waterMark(String imagePath,String strWaterMarkFileName) throws IOException{
		//源文件
		File imageFile=new File(imagePath);
		Image image = ImageIO.read(imageFile);
		
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		
		BufferedImage bufferedImage=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D createGraphics = bufferedImage.createGraphics();
		createGraphics.drawImage(image, 0, 0, width, height,null);
		
		
		//水印文件
		File waterFile=new File(strWaterMarkFileName);
		Image waterImage=ImageIO.read(waterFile);
		int waterWidth = waterImage.getWidth(null);
		int waterHeight=waterImage.getHeight(null);
		
		//水印放在文件的位置
		createGraphics.drawImage(waterImage, width-waterWidth, height-waterHeight, waterWidth, waterHeight, null);
		createGraphics.dispose();
		
		
		FileOutputStream outputStream=new FileOutputStream(strWaterMarkFileName);
		
		outputStream.flush();
		outputStream.close();
		
		return null;
	}
	/** 
     * 文字水印 
     * @param pressText 水印文字 
     * @param targetImg 目标图片 
     * @param fontName 字体名称 
     * @param fontStyle 字体样式 
     * @param color 字体颜色 
     * @param fontSize 字体大小 
     * @param x 修正值 
     * @param y 修正值 
     * @param alpha 透明度 
     */  
    public static void pressText(String pressText, String targetImg, String fontName, int fontStyle, Color color, int fontSize, int x, int y, float alpha) {  
        try {  
            File img = new File(targetImg);  
            Image src = ImageIO.read(img);  
            int width = src.getWidth(null);  
            int height = src.getHeight(null);  
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
            Graphics2D g = image.createGraphics();  
            g.drawImage(src, 0, 0, width, height, null);  
            g.setColor(color);  
            g.setFont(new Font(fontName, fontStyle, fontSize));  
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));  
            g.drawString(pressText, (width - (getLength(pressText) * fontSize))  + x, (height - fontSize) + y);  
            g.dispose();  
            ImageIO.write((BufferedImage) image, "jpg", img);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    /** 
     * 图片水印 
     * @param pressImg 水印图片 
     * @param targetImg 目标图片 
     * @param x 修正值 默认在中间 
     * @param y 修正值 默认在中间 
     * @param alpha 透明度 
     */  
    public final static void pressImage(String pressImg, String targetImg, int x, int y, float alpha) {  
        try {  
            File img = new File(targetImg);  
            Image src = ImageIO.read(img);  
            int wideth = src.getWidth(null);  
            int height = src.getHeight(null);  
            BufferedImage image = new BufferedImage(wideth, height, BufferedImage.TYPE_INT_RGB);  
            Graphics2D g = image.createGraphics();  
            g.drawImage(src, 0, 0, wideth, height, null);  
            //水印文件  
            Image src_biao = ImageIO.read(new File(pressImg));  
            int wideth_biao = src_biao.getWidth(null);  
            int height_biao = src_biao.getHeight(null);  
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));  
            g.drawImage(src_biao, (wideth - wideth_biao) / 2, (height - height_biao) / 2, wideth_biao, height_biao, null);  
            //水印文件结束  
            g.dispose();  
            ImageIO.write((BufferedImage) image, "jpg", img);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
  
    /** 
     * 缩放 
     * @param filePath 图片路径 
     * @param height 高度 
     * @param width 宽度 
     * @param bb 比例不对时是否需要补白 
     */  
    public static void resize(String filePath, int height, int width, boolean bb) {  
        try {  
            double ratio = 0.0; //缩放比例   
            File f = new File(filePath);  
            BufferedImage bi = ImageIO.read(f);  
            Image itemp = bi.getScaledInstance(width, height, bi.SCALE_SMOOTH);  
            //计算比例  
            if ((bi.getHeight() > height) || (bi.getWidth() > width)) {  
                if (bi.getHeight() > bi.getWidth()) {  
                    ratio = (new Integer(height)).doubleValue() / bi.getHeight();  
                } else {  
                    ratio = (new Integer(width)).doubleValue() / bi.getWidth();  
                }  
                AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);  
                itemp = op.filter(bi, null);  
            }  
            if (bb) {  
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
                Graphics2D g = image.createGraphics();  
                g.setColor(Color.white);  
                g.fillRect(0, 0, width, height);  
                if (width == itemp.getWidth(null))  
                    g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);  
                else  
                    g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null), Color.white, null);  
                g.dispose();  
                itemp = image;  
            }  
            ImageIO.write((BufferedImage) itemp, "jpg", f);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
  
    public static int getLength(String text) {  
        int length = 0;  
        for (int i = 0; i < text.length(); i++) {  
            if (new String(text.charAt(i) + "").getBytes().length > 1) {  
                length += 2;  
            } else {  
                length += 1;  
            }  
        }  
        return length / 2;  
    }  
	public static void main(String[] args) {
		//pressImage("G:\\imgtest\\sy.jpg", "G:\\imgtest\\test1.jpg", 0, 0, 0.5f);  
        pressText("英盛科技", "D:/project/modo/WebRoot/archives/2012-11-08/20121105_195801.jpg", "黑体", 12, Color.white, 42, 12, 12, 0.3f);  
        //resize("G:\\imgtest\\test1.jpg", 500, 500, true);
		ModoWaterMarkImage modoWaterMarkImage=new ModoWaterMarkImage();
		/*try{
			modoWaterMarkImage.waterMark("D:/project/modo/WebRoot/archives/2012-11-08/20121105_195936.jpg","D:/project/modo/WebRoot/archives/2012-11-08/20121105_1959361.jpg");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}*/
	}
}
