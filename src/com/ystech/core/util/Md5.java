package com.ystech.core.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
	/**
	 */
	public static String calcMD5(String str) {
		try {
			MessageDigest alga = MessageDigest.getInstance("MD5");
			alga.update(str.getBytes());
			byte[] digesta = alga.digest();
			return byte2hex(digesta);
		} catch (NoSuchAlgorithmException ex) {
		}
		return "NULL";
	}

	public static String byte2hex(byte[] b) {

		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
			if (n < b.length - 1) {
				hs = hs + "";
			}
		}
		return hs;
	}

	public static void main(String[] args) {
		//System.out.println(Md5.calcMD5("123456{shusanzhan}"));		
		System.out.println(Md5.calcMD5("123456admin"));		
	}
}
