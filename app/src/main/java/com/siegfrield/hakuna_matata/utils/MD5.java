package com.siegfrield.hakuna_matata.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

/**
 *  MD5 加密
 */
public class MD5 {
	// MD5加密，32位
	public static String md5(String str) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		char[] charArray = str.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}
		byte[] md5Bytes = md5.digest(byteArray);

		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	public static String getFileMD5String(File file) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		FileInputStream in = null;
		try {
			byte[] buffer = new byte[1024];
			int numRead = 0;
			in = new FileInputStream(file);

			while ((numRead = in.read(buffer)) > 0) {
				md5.update(buffer, 0, numRead);
			}

			byte[] md5Bytes = md5.digest();
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
			return hexValue.toString();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";

	}

	// 全局数组
	private final static String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static String md5(String username, String password, String timeTampString) {
		StringBuilder builder = new StringBuilder();
		builder.append("username").append("=").append(username);
		builder.append("&");
		builder.append("password").append("=").append(password);
		builder.append("&");
		builder.append("timestamp").append("=").append(timeTampString);
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("md5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		String result = builder.toString();
		byte[] resultBytes = result.getBytes();
		// md5.update(resultBytes);
		byte[] message = md5.digest(resultBytes);
		return getString(message);
	}

	public static String md5Game(String gid, String uid, String timeTampString) {
		StringBuilder builder = new StringBuilder();
		builder.append("gid").append("=").append(gid);
		builder.append("&");
		builder.append("uid").append("=").append(uid);
		builder.append("&");
		builder.append("timestamp").append("=").append(timeTampString);
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("md5");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		String result = builder.toString();
		byte[] resultBytes = result.getBytes();
		// md5.update(resultBytes);
		byte[] message = md5.digest(resultBytes);
		return getString(message);
	}

	private static String getString(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			sb.append(byteToArrayString(b[i]));
		}
		return sb.toString();
	}

	// 返回形式为数字跟字符串
	private static String byteToArrayString(byte bByte) {
		int iRet = bByte;
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

}
