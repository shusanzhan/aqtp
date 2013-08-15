/*package com.ystech.aqtp.pdf;

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
*//**
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
 *//*

public class PdfUt2
{
	public static void main(String[] args) 
	{
		String pdfPath = "D:/test.pdf"; 
		String pdfPath2 = "D:/test2.pdf"; 
		getColspan(pdfPath2);
	}
	
	public static void getColspan(String pdfPath){ 
        Document document = new Document(PageSize.A4, 36, 36, 36, 36); 
        try { 
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath)); 
            document.open();   
             
            PdfPTable pdfPTable = new PdfPTable(4);  // 最外层table 
            float[] wid = {80f, 100f, 80f, 60f}; 
            pdfPTable.setTotalWidth(wid); //设置基本信息
            pdfPTable.setLockedWidth(true); 
             
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
            
            //table.addCell(cell);  // 将这个cell加入table中 
             
        
             
            cell = new PdfPCell(iTable); 
            cell.setFixedHeight(3*30);  // 跨3列了 
           // table.addCell(cell); 
             
            document.add(pdfPTable);//设置表格到文档中 
        } catch (Exception de) { 
            de.printStackTrace(); 
        } 
        document.close(); 
    } 

}
*/