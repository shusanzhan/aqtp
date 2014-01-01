/**
 * 
 */
package com.ystech.aqtp.pdf;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.ystech.aqtp.model.Breed;
import com.ystech.aqtp.model.ChickenBatch;
import com.ystech.aqtp.model.Dimensiona;
import com.ystech.aqtp.model.DimensionaCode;
import com.ystech.aqtp.model.Grade;
import com.ystech.core.util.DateUtil;
import com.ystech.core.util.PathUtil;

/**
 * @author shusanzhan
 * @date 2013-8-10
 */
public class ItextPdfManageImpl {
	
	public  String createPdf(ChickenBatch chickenBatch,Dimensiona dimensionas,List<DimensionaCode> dimensionaCodes) throws DocumentException, IOException {
		String pdfFilePath=new String();
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);   
		Font fontChinese = new Font(bfChinese);   
		fontChinese.setSize(10f); 
		
		Font fontChinese2 = new Font(bfChinese);   
		fontChinese2.setSize(18f); 
		
		try {
			Document document=new Document();
			//PDF存放路径
			pdfFilePath=PathUtil.getWebRootPath()+System.getProperty("file.separator")+
								"dimensiona"+System.getProperty("file.separator")+
								dimensionas.getDbid()+System.getProperty("file.separator")+
								dimensionas.getDbid()+".pdf";
			PdfWriter.getInstance(document, new FileOutputStream(pdfFilePath));
			document.open();
			
			Font font=new Font();
			font.setFamily("STSongStd-Light");
     		font.setColor(BaseColor.BLUE);
     		
     		if(null!=chickenBatch){
     			//设置pdf标题
     			PdfPTable title = getTitle(chickenBatch.getName(), fontChinese2);
     			document.add(title);
     			
     			//设置批次信息
    			PdfPTable chieckenBatch = getChieckenBatch(chickenBatch,fontChinese);
    			document.add(chieckenBatch);
     		}else{
     			PdfPTable title = getTitle("", fontChinese2);
     			document.add(title);
     		}
     		
			List<List<DimensionaCode>> dimensionaCodeFouth = getDimensionaCodeFouth(dimensionaCodes);
			//二维码信息
			PdfPTable dimensionPdf = getDimensionaCodes(dimensionaCodeFouth);
			
			document.add(dimensionPdf);
			
     	    document.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("创建pdf发生错误");
			return null;
		}
		return pdfFilePath;
	}
	
	/**
	 * 功能描述：设置pdf文档标题内容
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws Exception
	 */
	public PdfPTable getTitle(String title,Font fontChinese) throws Exception {
		String title2=title+"批次二维码信息";
		PdfPTable pdfPTable = getTable();
		
		Paragraph titleParagraph = new Paragraph(title2,fontChinese);
		titleParagraph.setAlignment(1);
		
		PdfPCell cell = new PdfPCell(titleParagraph);  
		BaseColor bgcolor = new BaseColor(192, 192, 192); //灰色
		cell.setFixedHeight(30); 
		//cell.setPadding(0);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);// 设置内容水平居中显示
		cell.setVerticalAlignment(Element.ALIGN_CENTER); 
		cell.setBorder(Rectangle.NO_BORDER);  
		cell.setBackgroundColor(bgcolor);  
		pdfPTable.addCell(cell);
		return pdfPTable;
	}
	/**
	 * 功能描述：设置pdf文档标题内容
	 * 参数描述：
	 * 逻辑描述：
	 * @return
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws Exception
	 */
	public PdfPTable getChieckenBatch(ChickenBatch chickenBatch,Font fontChinese) throws DocumentException, MalformedURLException, IOException {
		PdfPTable contentPdfPTable=new PdfPTable(1);
		//float[] widths = {4f,260,10f,260,10f,260f,4f}; 
		//设置表格属性
		//contentPdfPTable.setWidths(widths);
		//设置表格在页面上的宽度，设成100表示可以表格填满页面，但是要去掉页面margin   
		contentPdfPTable.setWidthPercentage(100); 
		//设置表格上端的空白距离，类似css中的margin-top:xxpx;这样在给表格加上标题后，标题就不会跟表格重叠在一起了。   
		contentPdfPTable.setSpacingBefore(3f);
		contentPdfPTable.getDefaultCell().setBorder(0);//设置表格默认为无边框
		
		PdfPCell cell = null;  // 最外层table的cell 
		
		//设置第一行批次信息数据
		PdfPTable firstPdfPTable=new PdfPTable(8);
		PdfPCell nameLable = setLableCell("名称");
		PdfPCell nameValue = setCellValue(chickenBatch.getName());
		firstPdfPTable.addCell(nameLable);
		firstPdfPTable.addCell(nameValue);
		PdfPCell batchNoLable = setLableCell("批号");
		PdfPCell batchNoValue = setCellValue(chickenBatch.getBatchNo());
		firstPdfPTable.addCell(batchNoLable);
		firstPdfPTable.addCell(batchNoValue);
		PdfPCell gradeLable = setLableCell("品级");
		Grade grade = chickenBatch.getGrade();
		PdfPCell gradeValue=null;
		if(null!=grade){
			gradeValue= setCellValue(chickenBatch.getGrade().getName());
		 }else{
			gradeValue=setCellValue("特级");
		}
		firstPdfPTable.addCell(gradeLable);
		firstPdfPTable.addCell(gradeValue);
		PdfPCell breedLable = setLableCell("品级");
		Breed breed = chickenBatch.getBreed();
		PdfPCell breedValue=null;
		if(null!=breedValue){
			breedValue = setCellValue(chickenBatch.getBreed().getName());
		}
		else{
			breedValue=setCellValue("无品系");
		}
		firstPdfPTable.addCell(breedLable);
		firstPdfPTable.addCell(breedValue);
		
		cell=new PdfPCell(firstPdfPTable);
		contentPdfPTable.addCell(cell);
		
		//设置批次信息第二行信息
		PdfPTable secondPdfPTable=new PdfPTable(8);
		PdfPCell birthdayLable = setLableCell("出生日期");
		PdfPCell birthdayValue = setCellValue(DateUtil.format(chickenBatch.getBirthday()));
		secondPdfPTable.addCell(birthdayLable);
		secondPdfPTable.addCell(birthdayValue);
		PdfPCell intoBarDateLable = setLableCell("入栏日期");
		PdfPCell intoBarDateValue = setCellValue(DateUtil.format(chickenBatch.getIntoBarDate()));
		secondPdfPTable.addCell(intoBarDateLable);
		secondPdfPTable.addCell(intoBarDateValue);
		PdfPCell outBarDateLable = setLableCell("出栏日期");
		PdfPCell outBarDateValue = setCellValue(DateUtil.format(chickenBatch.getOutBarDate()));
		secondPdfPTable.addCell(outBarDateLable);
		secondPdfPTable.addCell(outBarDateValue);
		PdfPCell countLable = setLableCell("数量");
		PdfPCell countValue = setCellValue(chickenBatch.getCountNum()+"");
		secondPdfPTable.addCell(countLable);
		secondPdfPTable.addCell(countValue);
		
		cell=new PdfPCell(secondPdfPTable);
		contentPdfPTable.addCell(cell);
		
		return contentPdfPTable;
	}
	/**
	 * 生成批量二维码
	 * @param dimensionaCodes
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws DocumentException 
	 */
	public PdfPTable getDimensionaCodes(List<List<DimensionaCode>> dimensionaCodes) throws MalformedURLException, IOException, DocumentException {
		PdfPTable pdfPTable=new PdfPTable(1);
		pdfPTable.setWidthPercentage(100); 
		//设置表格上端的空白距离，类似css中的margin-top:xxpx;这样在给表格加上标题后，标题就不会跟表格重叠在一起了。   
		pdfPTable.setSpacingBefore(3f);
		pdfPTable.getDefaultCell().setBorder(0);
		
		if(null!=dimensionaCodes&&dimensionaCodes.size()>0){
			for (int i=0;i<dimensionaCodes.size();i++) {
				List<DimensionaCode> diCodes= dimensionaCodes.get(i);
				PdfPTable dimensionaCode = getDimensionaCode(diCodes);
				pdfPTable.addCell(dimensionaCode);
			}
		}
		return pdfPTable;
	}
	/**
	 * 设置页面二维码信息
	 * @param dimensionaCodes
	 * @return
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws DocumentException 
	 */
	public PdfPTable getDimensionaCode(List<DimensionaCode> dimensionaCodes) throws MalformedURLException, IOException, DocumentException {
		int size = dimensionaCodes.size();
		PdfPTable pdfPTable=new PdfPTable(8);
		pdfPTable.setWidthPercentage(size/8*100); 
		for (DimensionaCode dimensionaCode : dimensionaCodes) {
			PdfPTable dimensionaCodeTable = setDimensionCode(dimensionaCode);
			PdfPCell cell=new PdfPCell(dimensionaCodeTable);
			cell.setBorder(0);
			pdfPTable.addCell(cell);
		}
		if(size<8){
			for (int i = 0; i < 8-size; i++) {
				PdfPTable dimensionaCodeTable = setDimensionCode(null);
				PdfPCell cell=new PdfPCell(dimensionaCodeTable);
				cell.setBorder(0);
				pdfPTable.addCell(cell);
			}
		}
		return pdfPTable;
	}
	/**
	 * 设置单个二维码信息
	 * @param dimensionaCode
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws DocumentException 
	 */
	public PdfPTable setDimensionCode(DimensionaCode dimensionaCode) throws MalformedURLException, IOException, DocumentException {
		PdfPTable pdfPTable=new PdfPTable(1);
		if(dimensionaCode!=null){
			pdfPTable.getDefaultCell().setBorder(0);//设置表格默认为无边框
			pdfPTable.setTotalWidth(60);// 设置表格的宽度
			pdfPTable.setLockedWidth(true);// 设置表格的宽度固定 
			//设置图片信息
			Image img = Image.getInstance(PathUtil.getWebRootPath()+dimensionaCode.getPhoto());
			//BaseColor bgcolorwrite = new BaseColor(255, 255, 255); //底色灰色
			img.scaleAbsolute(mmTopx(15),mmTopx(15));
			
			PdfPCell celltemp = new PdfPCell(img) ; 
			celltemp.setBorder(Rectangle.NO_BORDER);
			celltemp.setBorder(0);
			celltemp.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celltemp.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			celltemp.setBorder(0);
			pdfPTable.addCell(celltemp);
			
			//批次信息
			//String No="No"+dimensionaCode.getDimensiona().getDbid()+""+dimensionaCode.getCode()+""+dimensionaCode.getDimensiona().getChickenbatch().getBatchNo();
			String code = dimensionaCode.getCode().toString();
			String codeStr = getCode(code);
			String No=dimensionaCode.getDimensiona().getChickenbatch().getBatchNo()+codeStr;
			PdfPCell batchNoCell = setDimenisonValue(No);
			batchNoCell.setBorder(0);
			batchNoCell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			batchNoCell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
			batchNoCell.setPaddingTop(-8.0f);
			pdfPTable.addCell(batchNoCell);
		}
		return pdfPTable;
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
	 * 设置批次信息标题栏
	 * @param lable
	 * @return
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public PdfPCell  setLableCell(String lable) throws DocumentException, IOException {
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
		Font fontChinese = new Font(bfChinese);   
		fontChinese.setSize(10f);
		
		Paragraph paragraph = new Paragraph(lable,fontChinese);
		
		PdfPCell pdfPCell = new PdfPCell(paragraph); 
		
		BaseColor bgcolor = new BaseColor(192, 192, 192); //灰色
        pdfPCell.setFixedHeight(20); 
        pdfPCell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
		pdfPCell.setVerticalAlignment(PdfPCell.ALIGN_RIGHT);
		pdfPCell.setBackgroundColor(bgcolor);
        return pdfPCell;
	}
	/**
	 * 设置批次信值栏目
	 * @param lable
	 * @return
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public PdfPCell  setCellValue(String value) throws DocumentException, IOException {
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
		Font fontChinese = new Font(bfChinese);   
		fontChinese.setSize(10f); 
		
		Paragraph paragraph = new Paragraph(value,fontChinese);
		
		PdfPCell pdfPCell = new PdfPCell(paragraph); 
		pdfPCell.setFixedHeight(20); 
		pdfPCell.setPadding(5f);
		
		return pdfPCell;
	}
	/**
	 * 设置二维码信息
	 * @param lable
	 * @return
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public PdfPCell  setDimenisonValue(String value) throws DocumentException, IOException {
		BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
		Font fontChinese = new Font(bfChinese);   
		fontChinese.setSize(8f); 
		
		Paragraph paragraph = new Paragraph(value,fontChinese);
		
		PdfPCell pdfPCell = new PdfPCell(paragraph); 
		pdfPCell.setFixedHeight(20); 
		pdfPCell.setPadding(5f);
		
		return pdfPCell;
	}
	public PdfPTable getTable() throws DocumentException {
		PdfPTable pdfPTable=new PdfPTable(1);
		//float[] widths = {4f,260,10f,260,10f,260f,4f}; 
		//设置表格属性
		//pdfPTable.setWidths(widths);
		//设置表格在页面上的宽度，设成100表示可以表格填满页面，但是要去掉页面margin   
		pdfPTable.setWidthPercentage(100); 
		//设置表格上端的空白距离，类似css中的margin-top:xxpx;这样在给表格加上标题后，标题就不会跟表格重叠在一起了。   
		pdfPTable.setSpacingBefore(3f);
		pdfPTable.getDefaultCell().setBorder(0);//设置表格默认为无边框
		return pdfPTable;
	}
	
	//处理二维码数据,每一行处理8条数据
	public List<List<DimensionaCode>> getDimensionaCodeFouth(List<DimensionaCode> codes) {
		List<List<DimensionaCode>> diList=new ArrayList<List<DimensionaCode>>();
		if(null!=codes&&codes.size()>0){
			List<DimensionaCode> codes2=new ArrayList<DimensionaCode>();
			for (int i = 0; i < codes.size(); i++) {
				if(i%8==0&&i!=0){
					diList.add(codes2);
					codes2=new ArrayList<DimensionaCode>();
					codes2.add(codes.get(i));
				}else{
					codes2.add(codes.get(i));
				}
			}
			if(codes2.size()>0){
				diList.add(codes2);
			}
		}
		return diList;
	}
	/**
	 * 毫米转像素
	 * @param mm
	 * @return
	 */
	public static float mmTopx(float mm){
	   mm = (float) (mm *3.33) ;
	   return mm ;
  	}
	public static void main(String[] args) {
		ItextPdfManageImpl testPdf=new ItextPdfManageImpl();
	System.out.println("生成pdf开始！");
		try {
			testPdf.createPdf(null,null,null);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("生成pdf结束!");
	}
}
