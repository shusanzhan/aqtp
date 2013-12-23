package com.ystech.core.util;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class SignUtil extends HttpServlet {
	// 自定义 token
	private static String TOKEN = "shusanzhan";

	/**
	 * @param response
	 * @param signature
	 * @param echostr
	 * @param timestamp
	 * @param nonce
	 * @throws IOException
	 */
	public static boolean checkSignature( String signature, String timestamp, String nonce) {
		String[] str = { TOKEN, timestamp, nonce };
		Arrays.sort(str); // 字典序排序
		String bigStr = str[0] + str[1] + str[2];
		// SHA1加密
		String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
		System.out.println("==========="+digest+"========="+signature);
		// 确认请求来至微信
		if (digest.equals(signature)) {
			return true;
		}
		return false;
	}
}
