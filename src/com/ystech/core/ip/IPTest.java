package com.ystech.core.ip;

  
import com.ystech.core.util.PathUtil;

import junit.framework.TestCase;   
  
public class IPTest{   
       
    public static void Main(){   
    } 
    public static void main(String[] args) {
    	//指定纯真数据库的文件名，所在文件夹   
    	IPSeeker ip=new IPSeeker();   
    	//测试IP 13   
    	System.out.println(ip.getIPLocation("182.151.158.149").getCountry()+":"+ip.getIPLocation("182.151.158.149").getArea());   
    	System.out.println(ip.getIPLocation("125.87.28.41").getCountry()+":"+ip.getIPLocation("125.87.28.41").getArea());   
    	String result = IPTest.class.getResource("IPTest.class").toString();
    	
    	System.out.println(PathUtil.getWebRootPath());
	}
}  