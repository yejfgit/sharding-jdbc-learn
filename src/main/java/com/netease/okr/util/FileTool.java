package com.netease.okr.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileTool extends FileUtils {

	public final static String WINDOWS = "Windows";

	public final static String LINUX = "Linux";

	/***************************************************************************
	 * 判断操作系统
	 * 
	 * @return
	 *************************************************************************/
	public static String getOS() {
		if (File.separator.equalsIgnoreCase("\\"))
			return WINDOWS;
		else
			return LINUX;
	}

	/**
	 * 得到需要写入数据库的文件名称
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static List<String> getReadFiles(Date date, String fileDir, String filePre) throws Exception {
		File file = new File(fileDir);
		List<String> nameList = new ArrayList<String>();
		if (file.exists()) {
			String[] names = getAllFileName(fileDir);
			for (String name : names) {
				if ((!name.startsWith(filePre))
						|| (!name.equalsIgnoreCase(filePre + MyDateUtils.dateToString(date, "yyyyMMdd_HH")))) {
					continue;
				}
				nameList.add(fileDir.concat(name));
			}
		}

		Collections.sort(nameList);
		Collections.reverse(nameList);
		return nameList;
	}

	/**
	 * 判断文件是否可以被操作
	 * 
	 * @param file
	 * @return
	 */
	public static boolean checkCanUse(File file) {
		if (file.renameTo(file))
			return true;
		else
			return false;
	}

	public static void createFile(String filePath) throws IOException {
		File fl = new File(filePath);
		if (!fl.exists())
			fl.createNewFile();
	}
	
	public static void createFile(String filePath,byte[] fileByte) throws IOException {
		
		BufferedOutputStream bos = null;  
        FileOutputStream fos = null;  
        File file = null;  
        try  
        {  
            file = new File(filePath);  

            if(!file.getParentFile().exists()) {  
	            if(!file.getParentFile().mkdirs()) {  
	            	LoggerUtil.info("创建目标文件所在目录失败！");  
	            }  
	        }  
            
            if (!file.exists())  
            {  
            	file.createNewFile();  
            }  

            fos = new FileOutputStream(file);  
            bos = new BufferedOutputStream(fos);  
            bos.write(fileByte);  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
        finally  
        {  
            if (bos != null)  
            {  
                try  
                {  
                    bos.close();  
                }  
                catch (IOException e)  
                {  
                    e.printStackTrace();  
                }  
            }  
            if (fos != null)  
            {  
                try  
                {  
                    fos.close();  
                }  
                catch (IOException e)  
                {  
                    e.printStackTrace();  
                }  
            }  
        }  
	}

	public static void createFileDir(String filePath) throws IOException {
		File dirFile = new File(filePath);
		if (!(dirFile.exists())) {
			dirFile.mkdir();
		}
	}

	public static String[] getAllFileName(String filePath) {
		File pathName = new File(filePath);
		String[] fileNames = pathName.list();
		return fileNames;
	}

	public static String getBasePath() {
		String filePath = FileTool.class.getResource("/").getPath();
		return filePath;
	}

	public BufferedReader getFileContent(File file) throws IOException {
		FileInputStream fin = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(fin));
		return br;
	}

	public static void main(String[] args) {
		// System.out.println("getTopNumber==" +
		// FileTool.checkFileExists("http://reg.163.com/mainForBiTest.htm"));
	}

	public static boolean checkFileExists(String URLName) {
		try {
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
			con.setRequestMethod("HEAD");
			return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void copy(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		int BUFFER_SIZE = 8192;
		try {

			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			while (in.read(buffer) > 0) {
				out.write(buffer);
			}
		} catch (Exception e) {

		} finally {

			try {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static int countFileLines(File file) {
		int sum = 0;
		if (file.isFile()) {
			FileReader fr;
			try {
				fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				int fileLine = 0;
				while (br.readLine() != null) {
					fileLine++;
				}
				sum += fileLine;
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sum;
	}

	public static String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}
}
