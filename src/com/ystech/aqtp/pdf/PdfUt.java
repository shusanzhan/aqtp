package com.ystech.aqtp.pdf;

import java.io.FileOutputStream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font; 
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;  
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont; 
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;   
/**
 * <p>
 * Title: 通过itext包生成PDF文件
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Filename: PDFOpr.java
 * </p>
 * 
 * @version 1.0
 */

public class PdfUt
{
	public static void main(String[] args) 
	{
		String pdfPath = "D:/test.pdf"; 
		String pdfPath2 = "D:/test2.pdf"; 
		createPDF(pdfPath);
		getColspan(pdfPath2);
	}
	public static void createPDF(String pdfPath) 
	{
		//Document(Rectangle pageSize, float marginLeft, float marginRight, float marginTop, float marginBottom) 
		Document document = new Document(PageSize.A4.rotate(), 18f, 18f, 18f, 10f);  
		try
		{
			System.out.println("Generating PDF");
			PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
			
			document.open(); 
			
			BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);   
			Font fontChinese = new Font(bfChinese);   
			fontChinese.setSize(10f); 
			
			Font fontChinese2 = new Font(bfChinese);   
			fontChinese2.setSize(7f); 
			
			float[] widths = {4f,260,10f,260,10f,260f,4f}; 
			
			//new 一个13列的table   
			PdfPTable table = new PdfPTable(7);      
			//设置table每一列的宽度，widths里写的是百分比，他们加和需要是1   
			table.setWidths(widths);   
			//设置表格在页面上的宽度，设成100表示可以表格填满页面，但是要去掉页面margin   
			table.setWidthPercentage(100);   
			//设置表格上端的空白距离，类似css中的margin-top:xxpx;这样在给表格加上标题后，标题就不会跟表格重叠在一起了。   
			table.setSpacingBefore(3f);
			table.getDefaultCell().setBorder(0);//设置表格默认为无边框

			BaseColor bgcolor = new BaseColor(248, 248, 255); //底色灰色
			
			//第一行(begin)
			PdfPCell cell0 = new PdfPCell(new Paragraph("第一行表头",fontChinese));
			cell0.setColspan(7); 
			cell0.setFixedHeight(20); 
			cell0.setPadding(0);
			cell0.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
			cell0.setVerticalAlignment(Element.ALIGN_BOTTOM); 
			cell0.setBorder(Rectangle.NO_BORDER); 
			cell0.setBackgroundColor(bgcolor);
			table.addCell(cell0);
			//第一行(end)
			
			//第一行空白(begin) 
			PdfPCell cell01 = new PdfPCell(new Paragraph("[小字]",fontChinese2)); 
			cell01.setColspan(7); 
			cell01.setFixedHeight(12); 
			cell01.setPadding(0);
			cell01.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
			cell01.setVerticalAlignment(Element.ALIGN_TOP); 
			cell01.setBorder(Rectangle.NO_BORDER); 
			cell01.setBackgroundColor(bgcolor);  
			table.addCell(cell01);
			//第一行空白(begin) 
			
			PdfPCell cell1 = new PdfPCell(); 
			cell1.setColspan(1); 
			cell1.setFixedHeight(520); 
			cell1.setPadding(0);
			cell1.setVerticalAlignment(Element.ALIGN_MIDDLE); 
			cell1.setBorder(Rectangle.NO_BORDER); 
			cell1.setBackgroundColor(bgcolor);  
			table.addCell(cell1);
			 
			PdfPCell cell2 = new PdfPCell(getTable1());  
			cell2.setFixedHeight(150); 
			cell2.setColspan(1); 
			cell2.setFixedHeight(10); 
			cell2.setPadding(0);
			cell2.setVerticalAlignment(Element.ALIGN_TOP); 
			cell2.setBorder(Rectangle.NO_BORDER); 
			cell2.setBackgroundColor(bgcolor); //TODO:土黄色 背景#EFDB96 
			table.addCell(cell2);
			
			PdfPCell cell3 = new PdfPCell(new Paragraph("",fontChinese));  
			cell3.setFixedHeight(150);
			cell3.setColspan(1); 
			cell3.setFixedHeight(10); 
			cell3.setPadding(0);
			cell3.setVerticalAlignment(Element.ALIGN_MIDDLE); 
			cell3.setBorder(Rectangle.NO_BORDER); 
			cell3.setBackgroundColor(bgcolor);   
			table.addCell(cell3);
			
			PdfPCell cell4 = new PdfPCell(getTable2());  
			cell4.setFixedHeight(150);
			cell4.setColspan(1); 
			cell4.setFixedHeight(10); 
			cell4.setPadding(0);
			cell4.setVerticalAlignment(Element.ALIGN_TOP); 
			cell4.setBorder(Rectangle.NO_BORDER); 
			cell4.setBackgroundColor(bgcolor);  
			table.addCell(cell4);
			
			PdfPCell cell5 = new PdfPCell(new Paragraph("",fontChinese));  
			cell5.setFixedHeight(150);
			cell5.setColspan(1); 
			cell5.setFixedHeight(10); 
			cell5.setPadding(0); 
			cell5.setVerticalAlignment(Element.ALIGN_MIDDLE); 
			cell5.setBorder(Rectangle.NO_BORDER); 
			cell5.setBackgroundColor(bgcolor);   
			table.addCell(cell5);
			
			PdfPCell cell6 = new PdfPCell(getTable3());  
			cell6.setFixedHeight(150);
			cell6.setColspan(1); 
			cell6.setFixedHeight(10); 
			cell6.setPadding(0);
			cell6.setVerticalAlignment(Element.ALIGN_TOP); 
			cell6.setBorder(Rectangle.NO_BORDER); 
			cell6.setBackgroundColor(bgcolor);  
			table.addCell(cell6);
			
			PdfPCell cell7 = new PdfPCell(new Paragraph("",fontChinese));  
			cell7.setFixedHeight(150);
			cell7.setColspan(1); 
			cell7.setFixedHeight(10);
			cell7.setPadding(0);
			cell7.setVerticalAlignment(Element.ALIGN_MIDDLE); 
			cell7.setBorder(Rectangle.NO_BORDER);
			cell7.setBackgroundColor(bgcolor);  
			table.addCell(cell7);
			
			
			//最后一行空白(begin) 
			PdfPCell cell_end = new PdfPCell(new Paragraph("",fontChinese));  
			cell_end.setFixedHeight(150);
			cell_end.setColspan(7); 
			cell_end.setFixedHeight(10); 
			cell_end.setPadding(0);
			cell_end.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
			cell_end.setVerticalAlignment(Element.ALIGN_MIDDLE); 
			cell_end.setBorder(Rectangle.NO_BORDER); 
			cell_end.setBackgroundColor(bgcolor);  
			table.addCell(cell_end);
			//最后一行空白(end) 
			
			document.add(table);
			
			System.out.println("Done.");
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			document.close();
		}

	}
	
	/**
	 * Table1 第一列
	 * @return
	 */
	public static PdfPTable getTable1() throws Exception
	{
		PdfPTable iTable = new PdfPTable(1);  
		iTable.setTotalWidth(260);// 设置表格的宽度
		iTable.setLockedWidth(true);// 设置表格的宽度固定 
		
		BaseColor bgcolor = new BaseColor(192, 192, 192); //灰色
		BaseColor bgcolorwrite = new BaseColor(255, 255, 255); //底色灰色
		BaseColor bgcolordise = new BaseColor(248, 248, 255); //底色灰色
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);   
		Font fontChinese = new Font(bfChinese);   
		fontChinese.setSize(7f);
		Font fontChinese2 = new Font(bfChinese);   
		fontChinese2.setSize(10f); 
		
		Font fontChineseUL = new Font(bfChinese);   
		fontChineseUL.setSize(7f); 
		fontChineseUL.setStyle(Font.UNDERLINE); 
		float fFixedHeight = 14f;
		float fFixedHeight2 = 10f;
		//---------  (begin)
		PdfPCell cell = new PdfPCell(new Paragraph("　测试测试测试测试测试   ",fontChinese2));  
		cell.setFixedHeight(150);
		cell.setColspan(1); 
		cell.setFixedHeight(fFixedHeight); 
		cell.setPadding(0);  
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
		cell.setBackgroundColor(bgcolor);  
		cell.setBorder(Rectangle.NO_BORDER);
		iTable.addCell(cell);
		
		Chunk chunk1 = new Chunk(" ",fontChineseUL);  
		Phrase phrase = new Phrase(chunk1);
		Paragraph p = new Paragraph("  ：",fontChinese);
		p.add(phrase);
		PdfPCell cell1 = new PdfPCell(p);		
		cell1.setFixedHeight(150);				
		cell1.setColspan(1); 					
		cell1.setFixedHeight(fFixedHeight2); 	
		cell1.setPadding(0); 					
		cell1.setBorder(Rectangle.NO_BORDER);	
		cell1.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(cell1);
		
		chunk1 = new Chunk(" 测试测试测试测试测试 " ,fontChineseUL);  
		phrase = new Phrase(chunk1);
		p = new Paragraph(" 测试测试测试111 ",fontChinese);
		p.add(phrase);
		
		PdfPCell cell2 = new PdfPCell(p);  
		cell2.setFixedHeight(150);
		cell2.setColspan(1); 
		cell2.setFixedHeight(fFixedHeight2); 
		cell2.setPadding(0); 
		cell2.setBackgroundColor(bgcolorwrite);  
		cell2.setBorder(Rectangle.NO_BORDER);
		iTable.addCell(cell2);
		
		cell2 = new PdfPCell(new Paragraph("          ",fontChinese));  //空行
		cell2.setFixedHeight(150);
		cell2.setColspan(1); 
		cell2.setFixedHeight(4); 
		cell2.setPadding(0); 
		cell2.setBackgroundColor(bgcolorwrite);  
		cell2.setBorder(Rectangle.NO_BORDER);
		iTable.addCell(cell2);
		
		cell2 = new PdfPCell(new Paragraph("                ",fontChinese));  //空行
		cell2.setFixedHeight(150);
		cell2.setColspan(1); 
		cell2.setFixedHeight(4); 
		cell2.setPadding(0); 
		cell2.setBackgroundColor(bgcolordise);  
		cell2.setBorder(Rectangle.NO_BORDER);
		iTable.addCell(cell2);
		
		//---------   (end)
		 
		//--------- 
		cell = new PdfPCell(new Paragraph("　  ",fontChinese2));  
		cell.setFixedHeight(150);
		cell.setColspan(1); 
		cell.setFixedHeight(fFixedHeight); 
		cell.setPadding(0);  
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
		cell.setBackgroundColor(bgcolor);  
		cell.setBorder(Rectangle.NO_BORDER);
		iTable.addCell(cell);
		
		PdfPCell celltemp = new PdfPCell(new Paragraph("                ",fontChinese)) ; 
		
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph("           ________________________",fontChinese)) ; 
		
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph("        ___________________         ________",fontChinese)) ; 
		
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		chunk1 = new Chunk("1      ",fontChineseUL);  
		phrase = new Phrase(chunk1);
		p = new Paragraph("          ：",fontChinese);
		p.add(phrase);
		
		celltemp = new PdfPCell(p);
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph("          __________________________",fontChinese)) ; 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph(" ____________至___________  ",fontChinese)) ; 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		chunk1 = new Chunk("1        ",fontChineseUL);  
		phrase = new Phrase(chunk1);
		p = new Paragraph("       ：",fontChinese);
		p.add(phrase); 
		celltemp = new PdfPCell(p); 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		chunk1 = new Chunk("1    ",fontChineseUL);  
		phrase = new Phrase(chunk1);
		p = new Paragraph("         ：",fontChinese);
		p.add(phrase); 
		celltemp = new PdfPCell(p); 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph("     ________________________________________",fontChinese)) ; 
		
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph("     _________         ____年_____月",fontChinese)) ; 
		
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		chunk1 = new Chunk("1  ",fontChineseUL);  
		phrase = new Phrase(chunk1);
		p = new Paragraph("   ：",fontChinese);
		p.add(phrase); 
		celltemp = new PdfPCell(p);  
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		
		celltemp = new PdfPCell(new Paragraph("       _____        ________   _____",fontChinese)) ;  
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		 
		
		cell2 = new PdfPCell(new Paragraph("",fontChinese));  //空行
		cell2.setFixedHeight(150);
		cell2.setColspan(1); 
		cell2.setFixedHeight(4); 
		cell2.setPadding(0); 
		cell2.setBackgroundColor(bgcolorwrite);  
		cell2.setBorder(Rectangle.NO_BORDER);
		iTable.addCell(cell2);
		
		cell2 = new PdfPCell(new Paragraph("",fontChinese));  //空行
		cell2.setFixedHeight(150);
		cell2.setColspan(1); 
		cell2.setFixedHeight(4); 
		cell2.setPadding(0); 
		cell2.setBackgroundColor(bgcolordise);  
		cell2.setBorder(Rectangle.NO_BORDER);
		iTable.addCell(cell2);
		
		// 
		cell = new PdfPCell(new Paragraph("　测试  ",fontChinese2));  
		cell.setFixedHeight(150);
		cell.setColspan(1); 
		cell.setFixedHeight(fFixedHeight); 
		cell.setPadding(0);  
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
		cell.setBackgroundColor(bgcolor);  
		cell.setBorder(Rectangle.NO_BORDER);
		iTable.addCell(cell);
		
		   
		    
		// 
		cell = new PdfPCell(new Paragraph("　  ",fontChinese2));  
		cell.setFixedHeight(150);
		cell.setColspan(1); 
		cell.setFixedHeight(fFixedHeight); 
		cell.setPadding(0);  
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
		cell.setBackgroundColor(bgcolor);  
		cell.setBorder(Rectangle.NO_BORDER);
		iTable.addCell(cell);
		
		celltemp = new PdfPCell(new Paragraph("                   ",fontChinese)) ; 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph("                     ",fontChinese)) ; 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		   
		// 
		cell = new PdfPCell(new Paragraph("　    ",fontChinese2));  
		cell.setFixedHeight(150);
		cell.setColspan(1); 
		cell.setFixedHeight(fFixedHeight); 
		cell.setPadding(0);  
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
		cell.setBackgroundColor(bgcolor);  
		cell.setBorder(Rectangle.NO_BORDER);
		iTable.addCell(cell);
		
		chunk1 = new Chunk("   ",fontChineseUL);  
		phrase = new Phrase(chunk1);
		p = new Paragraph("   ",fontChinese);
		p.add(phrase);  
		celltemp = new PdfPCell(p);
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		return iTable;
	} 
	/**
	 * Table2 第二列
	 * @return
	 */
	public static PdfPTable getTable2() throws Exception
	{
		PdfPTable iTable = new PdfPTable(1);  
		iTable.setTotalWidth(260);// 设置表格的宽度
		iTable.setLockedWidth(true);// 设置表格的宽度固定 
		
		 
		return iTable;
	} 
	/**
	 * Table3 第三列
	 * @return
	 */
	public static PdfPTable getTable3() throws Exception
	{
		PdfPTable iTable = new PdfPTable(1);  
		iTable.setTotalWidth(260);// 设置表格的宽度
		iTable.setLockedWidth(true);// 设置表格的宽度固定 
		
		BaseColor bgcolor = new BaseColor(192, 192, 192); //灰色
		BaseColor bgcolorwrite = new BaseColor(255, 255, 255); //底色灰色
		BaseColor bgcolordise = new BaseColor(248, 248, 255); //底色灰色
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);   
		Font fontChinese = new Font(bfChinese);   
		fontChinese.setSize(7f);
		Font fontChinese2 = new Font(bfChinese);   
		fontChinese2.setSize(10f); 
		float fFixedHeight = 14f;
		float fFixedHeight2 = 12f;
		//---------  (begin)
		PdfPCell cell = new PdfPCell(new Paragraph("　测试测试测试 ",fontChinese2));   
		cell.setColspan(1); 
		cell.setFixedHeight(fFixedHeight); 
		cell.setPadding(0);  
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE); 
		cell.setBackgroundColor(bgcolor);  
		cell.setBorder(Rectangle.NO_BORDER);
		iTable.addCell(cell); 
		
		PdfPCell celltemp = new PdfPCell(new Paragraph(" ● 测试测试测试：",fontChinese)) ; 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph(" ○ 测试测试测试",fontChinese)) ; 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph("   测试测试测试",fontChinese)) ; 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph("   测试测试测试。",fontChinese)) ; 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph(" ○ 测试测试测试",fontChinese)) ; 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp); 
		
		celltemp = new PdfPCell(new Paragraph("   测试测试测试",fontChinese)) ; 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph("   测试测试测试。",fontChinese)) ; 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		
		celltemp = new PdfPCell(new Paragraph(" ○ 测试测试测试",fontChinese)) ; 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph("   测试测试测试。",fontChinese)) ;  
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph(" ○ 测试测试测试",fontChinese)) ; 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph("   测试测试测试。",fontChinese)) ;  
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph("   ",fontChinese)) ;  
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		Image img = Image.getInstance("D:/hwy.png");
		
		celltemp = new PdfPCell(img) ; 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph(" ●   ",fontChinese)) ; 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph("    ",fontChinese)) ; 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph("       ",fontChinese)) ; 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph("   ",fontChinese)) ; 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(fFixedHeight2); 
		celltemp.setPadding(0); 
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);  
		iTable.addCell(celltemp);
	
		
		celltemp = new PdfPCell(new Paragraph("",fontChinese));  //空行 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(4); 
		celltemp.setPadding(0); 
		celltemp.setBackgroundColor(bgcolorwrite);  
		celltemp.setBorder(Rectangle.NO_BORDER);
		iTable.addCell(celltemp);
		
		celltemp = new PdfPCell(new Paragraph("",fontChinese));  //空行 
		celltemp.setColspan(1); 
		celltemp.setFixedHeight(4); 
		celltemp.setPadding(0); 
		celltemp.setBackgroundColor(bgcolordise);  
		celltemp.setBorder(Rectangle.NO_BORDER);
		iTable.addCell(celltemp); 
		
		celltemp = new PdfPCell(new Paragraph("011111111112221111111111111",fontChinese)) ;  
		celltemp.setColspan(0); 
		celltemp.setPadding(0); 
		celltemp.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
		celltemp.setBorder(Rectangle.NO_BORDER);
		celltemp.setBackgroundColor(bgcolorwrite);
		iTable.addCell(celltemp);
		
		return iTable;
		
	} 
	public static void getColspan(String pdfPath){ 
        Document document = new Document(PageSize.A4, 36, 36, 36, 36); 
        try { 
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath)); 
            document.open();   
             
            PdfPTable table = new PdfPTable(4);  // 最外层table 
            float[] wid = {80f, 100f, 80f, 60f}; 
            table.setTotalWidth(wid); 
            table.setLockedWidth(true); 
             
            PdfPCell cell = null;  // 最外层table的cell 
             
            PdfPTable iTable = null; // 嵌套的table 
            PdfPCell iCell = null;  // 嵌套的table的cell 
             
            iTable = new PdfPTable(3); 
            float[] iWid = {80f, 100f, 80f}; 
            iTable.setTotalWidth(iWid); 
            iTable.setLockedWidth(true); 
            iCell = new PdfPCell(new Paragraph("column 1")); 
            iCell.setFixedHeight(30); 
            iTable.addCell(iCell); 
            iCell.setColspan(2); 
            iTable.addCell(iCell); 
            iCell = new PdfPCell(new Paragraph("column 2")); 
            iCell.setFixedHeight(30); 
            iTable.addCell(iCell); 
            iTable.addCell(iCell); 
            iTable.addCell(iCell); 
             
            iCell = new PdfPCell(new Paragraph("column 3")); 
            iCell.setFixedHeight(30); 
            iTable.addCell(iCell); 
            iTable.addCell(iCell); 
            iTable.addCell(iCell); 
             
            cell = new PdfPCell(iTable);  // 用这个table初始外层table的cell 
            cell.setColspan(3);  // 设置它跨3列 
            cell.setFixedHeight(3*30);  // 设置它的高度 
            table.addCell(cell);  // 将这个cell加入table中 
             
            iTable = new PdfPTable(1);   
            float[] iWid2 = {60f}; 
            iTable.setTotalWidth(iWid2); 
            iTable.setLockedWidth(true); 
            iCell = new PdfPCell(new Paragraph("i am here")); 
            iTable.addCell(iCell); 
             
            cell = new PdfPCell(iTable); 
            cell.setFixedHeight(3*30);  // 跨3列了 
            table.addCell(cell); 
             
            document.add(table); 
        } catch (Exception de) { 
            de.printStackTrace(); 
        } 
        document.close(); 
    } 

}
