package com.ystech.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;

public class ZipUtils {

	private static final int BUFFER = 8192;
	public static String jspDir = "";
	public static String jbpmZipPath = "";

	public static String getJbpmZipPath() {
		return jbpmZipPath;
	}

	public static String getJspDir() {
		return jspDir;
	}



	  public static void doUnZip(File file, String unzipPath) { // String
			try {
				final int BUFFER = 2048;
				BufferedOutputStream dest = null;
				FileInputStream fis = new FileInputStream(file);
				CheckedInputStream checksum = new CheckedInputStream(fis, new Adler32());
				ZipInputStream zis = new ZipInputStream(new BufferedInputStream(checksum));
				ZipEntry entry;
				while ((entry = zis.getNextEntry()) != null) {
					log("Extracting: " + entry);
					int count;
					byte[] data = new byte[BUFFER];
					
					log("unzip to " + unzipPath);
					if (entry.isDirectory()) {
						File dir = new File(unzipPath + File.separator + newDir(file, entry.getName()));
						jspDir = unzipPath;
						if (!dir.exists()) {
							FileUtils.forceMkdir(dir);
						}
						continue;
					}
					
					FileOutputStream fos = new FileOutputStream(unzipPath + File.separator + newDir(file, entry.getName()));

					dest = new BufferedOutputStream(fos, BUFFER);
					while ((count = zis.read(data, 0, BUFFER)) != -1) {
						dest.write(data, 0, count);
					}
					dest.flush();
					dest.close();
					fos.close();
				
					// 解压压缩文件内部的压缩包
					File subZipFile = new File(unzipPath + File.separator + entry.getName());
					doUnZip(subZipFile, getFileName(subZipFile.getPath()));
				}
			
				zis.close();
				fis.close();
		// System.out.println("Checksum: " +
				// checksum.getChecksum().getValue());
			} catch (Exception e) {
				e.printStackTrace();
			}
	  }

	public static void doZip(File file) {
		List<File> fileList = new ArrayList<File>();
		List<File> allFiles = searchFiles(file.getPath(), fileList);

		Object[] fileArray = allFiles.toArray();

		BufferedInputStream in = null;
		FileInputStream fis = null;
		java.util.zip.ZipOutputStream zos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file.getParent() + File.separator
					+ file.getName() + ".zip");
			zos = new java.util.zip.ZipOutputStream(new BufferedOutputStream(fos, BUFFER));
			zos.setLevel(9);
			byte[] data = new byte[BUFFER];
			for (int i = 0; i < fileArray.length; i++) {
				// 设置压缩文件入口entry,为被读取的文件创建压缩条目
				File tempFile = new File(fileArray[i].toString());
				String rootStr = file.getPath();
				String entryStr = null;
				// entry以相对路径的形式设置。以文件夹C:\temp例如temp\test.doc或者test.xls
				// 如果设置不当，会出现拒绝访问等错误
				// 分别处理单个文件/目录的entry
				if (rootStr.equals(tempFile.getPath())) {
					entryStr = tempFile.getName();
				} else {
					entryStr = tempFile.getPath().substring(
							(rootStr + File.separator).length());
				}
				log(entryStr);

				ZipEntry entry = new ZipEntry(entryStr);
				zos.putNextEntry(entry);

				fis = new FileInputStream(tempFile);
				in = new BufferedInputStream(fis, BUFFER);

				int count;
				while ((count = in.read(data, 0, BUFFER)) != -1) {
					zos.write(data, 0, count);
				}
				fis.close();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (zos != null) {
					zos.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	    /** 
	         * 构建目录 
	         * @param outputDir 
	         * @param subDir 
	         */  
	        public static void createDirectory(String outputDir,String subDir){  
	              
	            File file = new File(outputDir);  
	              
	            if(!(subDir == null || subDir.trim().equals(""))){//子目录不为空   
	                  
	                file = new File(outputDir + "/" + subDir);  
	            }  
	              
	            if(!file.exists()){  
	                  
	                file.mkdirs();  
	            }  
	              
	        }  
	private static String getFileName(String filePath) {
		int index = filePath.indexOf(".");
		return filePath.substring(0, index);
	}

	@SuppressWarnings("unused")
	private static String getRootPath(String filePath) {
		int index = filePath.indexOf(".");
		return filePath.substring(0, index);
	}

	private static void log(String msg) {
		// System.out.println(msg);
	}

	@SuppressWarnings("static-access")
	private static String newDir(File file, String entryName) {

		String rootDir = getFileName(file.getPath());
		log("root：" + rootDir);
		int index = entryName.lastIndexOf("\\");
		String dirStr = new File(rootDir).getParent();
		log(dirStr);
		if (index != -1) {

			String path = entryName.substring(0, index);
			log("new Dir：" + rootDir + file.separator + path);
			new File(rootDir + file.separator + path).mkdirs();

			log("entry:" + entryName.substring(0, index));
		} else {
			new File(rootDir).mkdirs();
			log("entry:" + entryName);
		}
		return entryName;

	}

	public static List<File> searchFiles(String sourceFilePath,
			List<File> fileList) {
		File fileDir = new File(sourceFilePath);
		if (fileDir.isDirectory()) {
			File[] subfiles = fileDir.listFiles();
			for (int i = 0; i < subfiles.length; i++) {
				searchFiles(subfiles[i].getPath(), fileList);
			}
		} else {
			fileList.add(fileDir);
		}

		return fileList;
	}


	public static void zip(String sourceFilePath) {
		File fileDir = new File(sourceFilePath);
		if (fileDir.exists()) {
			log(fileDir.getPath() + "               Starting Zip ...");
			long startTime = System.currentTimeMillis();
			doZip(fileDir);
			long endTime = System.currentTimeMillis();
			long costTime = endTime - startTime;
			log("Zip Success!");
			log("use time -- " + costTime + " millsec!");
		} else {
			log("can't find the File!");
		}
	}

	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		String s_utf8 = sb.toString();
		sb.delete(0, sb.length());
		sb.setLength(0);
		sb = null;
		return s_utf8;
	}

	public static void main(String[] args) {

		String str = "leaveDemo.zip";
		String fileDir = str.replaceAll(".zip", "");
		String fileZip = str.replaceAll("Demo.zip", "");
		System.out.println(fileDir + File.separator + fileZip);

	}
}
