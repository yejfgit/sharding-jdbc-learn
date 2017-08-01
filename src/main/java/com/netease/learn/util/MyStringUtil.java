package com.netease.learn.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class MyStringUtil extends StringUtils {

	public static final String WINDOWS = "Windows";
	public static final String LINUX = "Linux";

	public final static String doMd5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getRandomPassword(int pwd_len) {
		// 24个小写字母(去掉了小写l、o) + 9个数字(去掉了0) + 24个大写字母(去掉了大写I、O)
		final int maxNum = 57;
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z', '0', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'X' };

		StringBuilder pwd = new StringBuilder();
		Random r = new Random();
		while (count < pwd_len) {
			i = Math.abs(r.nextInt(maxNum));

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}

	public static byte[] getByteMD5(byte[] source) {
		byte tmp[] = null;
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(source);
			tmp = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
			return tmp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp;
	}

	public static String getOS() {
		if (File.separator.equalsIgnoreCase("\\"))
			return WINDOWS;
		else
			return LINUX;
	}

	public static String formatInteger(DecimalFormat df, Integer iv) {
		if (iv != null)
			return df.format(iv);
		else
			return "";
	}

	public static String formatFloat(DecimalFormat df, Float fv) {
		if (fv != null)
			return df.format(fv);
		else
			return "";
	}

	public static String formatDouble(DecimalFormat df, Double dv) {
		if (df != null)
			return df.format(dv);
		else
			return "";
	}

	public static String getGbkString(String param) throws UnsupportedEncodingException {
		if (StringUtils.isEmpty(param))
			return "";
		return new String(param.getBytes("ISO8859_1"), "gb2312");
	}

	public static String[] stringToArray(String strings) {
		if (strings == null)
			return null;
		if (strings.equalsIgnoreCase(""))
			return null;
		String[] rs = strings.split(",");

		return rs;
	}

	public static void main(String[] args) {
		// getEncrypt("130", "http://www.163.com");
		// getEncrypt("", "http://www.163.com");
		// getEncrypt("1212", "http://www.sohu.com");
		// getEncrypt("1231",
		// "http://news.xinhuanet.com/fortune/2010-03/22/content_13222642.htm");
		// System.out.println("2013051717".substring(0, 8));
		// java.sql.Date uvDate = new java.sql.Date(
		// MyDateUtils.stringToDate("2013051717".substring(0, 8),
		// "yyyyMMdd").getTime());
		// System.out.println(uvDate);
	}

	public static String arrayToString(String[] items) {
		if (items == null)
			return "";
		StringBuffer buffer = new StringBuffer();
		for (String item : items) {
			buffer.append(item + ",");
		}
		buffer.delete(buffer.length() - 1, buffer.length());
		return buffer.toString();
	}

	public static String arrayToSpaceString(String[] items) {
		if (items == null)
			return "";
		StringBuffer buffer = new StringBuffer();
		for (String item : items) {
			buffer.append(item + " ");
		}
		buffer.delete(buffer.length() - 1, buffer.length());
		return buffer.toString();
	}

	public static String setToString(Set items) {
		if (items == null)
			return "";
		if (items.size() == 0)
			return "";
		StringBuffer buffer = new StringBuffer();
		for (Iterator its = items.iterator(); its.hasNext();) {
			buffer.append((String) (its.next()) + ",");
		}
		buffer.delete(buffer.length() - 1, buffer.length());
		return buffer.toString();
	}

	public static String listToString(List items) {
		if (items == null)
			return "";
		if (items.size() == 0)
			return "";
		StringBuffer buffer = new StringBuffer();
		for (Iterator its = items.iterator(); its.hasNext();) {
			buffer.append((String) (its.next()) + ",");
		}
		buffer.delete(buffer.length() - 1, buffer.length());
		return buffer.toString();
	}

	public static String integerListToString(Collection<Integer> items) {
		if (items == null)
			return "";
		if (items.size() == 0)
			return "";
		StringBuffer buffer = new StringBuffer();
		for (Object element : items) {
			buffer.append((element) + ",");
		}
		buffer.delete(buffer.length() - 1, buffer.length());
		return buffer.toString();
	}

	public static String arrayToString(int[] items) {
		if (items == null)
			return "";
		StringBuffer buffer = new StringBuffer();
		for (int item : items) {
			buffer.append(item + ",");
		}
		buffer.delete(buffer.length() - 1, buffer.length());
		return buffer.toString();
	}

	public static int[] stringArrayToIntArray(String[] items) {

		int[] r = new int[items.length];
		for (int i = 0; i < items.length; i++) {
			r[i] = Integer.parseInt(items[i]);
		}
		return r;
	}

	public static void pppp(byte[] value) {
		for (byte element : value) {
			/* System.out.printf("%x", value[i]); */
		}
		/* System.out.printf("\n"); */
	}

	public static String getEncrypt(String aid, String url) {
		// System.out.print("aid=" + aid + "\t url=" + url + "\t");
		byte[] aByte = getByteMD5(aid.concat(url).getBytes());
		pppp(aByte);
		byte[] bByte = getByteMD5(aByte);
		pppp(bByte);
		byte[] cByte = new byte[aByte.length];
		for (int i = 0; i < aByte.length; i++) {
			cByte[i] = (byte) ((aByte[i] + bByte[i]) % 256);
		}
		pppp(cByte);
		byte[] dByte = getByteMD5(cByte);
		pppp(dByte);
		long result = 0l;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) + (dByte[i] + 256) % 256;
		}
		// System.out.println("keys=" + String.valueOf(result));
		return String.valueOf(result);
	}

	public static int getByte(byte b) {
		if (b < 0)
			return b + 256;
		return b;
	}

	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}

	public static byte[] hex2Byte(String str) {
		if (str == null)
			return null;
		str = str.trim();
		int len = str.length();
		if (len == 0 || len % 2 == 1)
			return null;
		byte[] b = new byte[len / 2];
		try {
			for (int i = 0; i < str.length(); i += 2) {
				b[i / 2] = (byte) Integer.decode("0x" + str.substring(i, i + 2)).intValue();
			}
			return b;
		} catch (Exception e) {
			return null;
		}
	}

	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (byte element : bArray) {
			sTemp = Integer.toHexString(0xFF & element);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp);
		}
		return sb.toString();
	}

	private static byte toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	public static String convertEncoding(String str, String sourceEncoding, String destEncoding)
			throws UnsupportedEncodingException {
		String ret = null;
		if (!isBlank(str)) {
			ret = new String(str.getBytes(sourceEncoding), destEncoding);
			str = null;
		}
		return ret;
	}
	
	/**
	 * hzyejinfu  括号统一
	 * */
	public static String bracketsFormat(String str){
		
		if(isBlank(str)){
			return str;
		}else{
			str = str.replace("（", "(").replace("（", "(").replace("（", "(");
			str = str.replace("）", ")").replace("）", ")").replace("）", ")");
		}
		
		return str;
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
                    b = String.valueOf(c).getBytes("utf-8");
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
        return sb.toString();
    }
	
	
	public static String getFileType(String fileName) {
		if(isBlank(fileName)){
			return "";
		}
		
		String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
		return prefix;
	}

}
