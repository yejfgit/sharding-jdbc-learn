package com.netease.okr.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UploadUtil {

	private static PropertiesUtils properties = new PropertiesUtils("config", null);

	@SuppressWarnings({ "rawtypes" })
	public static Map uploadFile(MultipartHttpServletRequest request) {

		Map<String, Object> map = new HashMap<String, Object>();
		// 文件保存目录路径
		String savePath = properties.getPropery("file_save_path") + "/";
		// 由于上传的的是证书，所以文件夹名字定为file
		String dirName = "file";
		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("file", properties.getPropery("file_type"));
		// 允许上传最大文件大小(字节)
		long maxSize = Long.parseLong(properties.getPropery("max_file_size"));
		// 获取上传的文件
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = request.getFile(itr.next());
		try {
			// 检查文件大小
			if (mpf.getBytes().length > maxSize) {
				map.put("success", false);
				map.put("msg", "上传文件大小超过限制！(允许最大[" + maxSize + "]字节，您上传了[" + mpf.getBytes().length + "]字节)");
				return map;
			}
			// 检查扩展名
			String fileExt = mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf(".") + 1)
					.toLowerCase();
			if (!Arrays.<String> asList(extMap.get("file").split(",")).contains(fileExt)) {
				map.put("success", false);
				map.put("msg", "上传文件扩展名是不允许的扩展名。\n只允许上传PUSH证书的格式 ！");
				return map;
			}
			// 检查目录
			File uploadDir = new File(savePath);
			if (!uploadDir.isDirectory()) {
				uploadDir.mkdirs();
			}
			// 检查目录写权限
			if (!uploadDir.canWrite()) {
				map.put("success", false);
				map.put("msg", "上传目录没有写权限！");
				return map;
			}
			// 创建文件夹（按照年月日的层次结构创建）
			savePath += dirName + "/";
			File saveDirFile = new File(savePath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
			SimpleDateFormat yearDf = new SimpleDateFormat("yyyy");
			SimpleDateFormat monthDf = new SimpleDateFormat("MM");
			SimpleDateFormat dateDf = new SimpleDateFormat("dd");
			Date date = new Date();
			String ymd = yearDf.format(date) + "/" + monthDf.format(date) + "/" + dateDf.format(date);
			savePath += ymd;
			File dirFile = new File(savePath);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			// 获取文件的绝对路径
			String newFileName = UUID.randomUUID().toString() + "." + fileExt;
			savePath = savePath + "/" + newFileName;
			// 保存到文件系统
			FileOutputStream fs = new FileOutputStream(savePath);
			byte[] buffer = new byte[1024 * 1024];
			int byteread = 0;
			InputStream inputStream = mpf.getInputStream();
			while ((byteread = inputStream.read(buffer)) != -1) {
				fs.write(buffer, 0, byteread);
				fs.flush();
			}
			fs.close();
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.put("success", true);
		// 保存到数据库的路径不要Setting.getSetting(Setting.FILE_SAVE_PATH)部分，方便以后移动文件夹
		map.put("savePath", savePath.replace(properties.getPropery("file_save_path"), ""));
		return map;
	}

	@SuppressWarnings({ "rawtypes" })
	public static Map uploadImages(MultipartHttpServletRequest request, String imageDirName) throws IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		// 文件保存目录路径
		String savePath = properties.getPropery("file_save_path") + "/";
		// 由于上传的的是图片，所以文件夹名字定为image
		String dirName = "image";
		// 定义允许上传的图片扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", properties.getPropery("image_type"));
		// 允许上传最大文件大小(字节)
		long maxSize = Long.parseLong(properties.getPropery("max_file_size"));
		// 获取上传的文件
		Iterator<String> itr = request.getFileNames();
		// 循环遍历上传的图片
		while (itr.hasNext()) {
			MultipartFile mpf = request.getFile(itr.next());
			InputStream inputStream = mpf.getInputStream();
			InputStream tempinputStream = mpf.getInputStream();
			// 文件输出流
			FileOutputStream fs = null;
			try {
				// 检查文件大小
				if (mpf.getBytes().length > maxSize) {
					map.put("success", false);
					map.put("msg", "上传文件大小超过限制！(允许最大[" + maxSize + "]字节，您上传了[" + mpf.getBytes().length + "]字节)");
					return map;
				}
				// 检查扩展名
				String fileExt = mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf(".") + 1)
						.toLowerCase();
				if (!Arrays.<String> asList(extMap.get("image").split(",")).contains(fileExt)) {
					map.put("success", false);
					map.put("msg", "上传文件扩展名不对，只允许上传图片的格式 ！");
					return map;
				}
				// 检查目录
				File uploadDir = new File(savePath);
				if (!uploadDir.isDirectory()) {
					uploadDir.mkdirs();
				}
				// 检查目录写权限
				if (!uploadDir.canWrite()) {
					map.put("success", false);
					map.put("msg", "上传目录没有写权限！");
					return map;
				}
				BufferedImage bi = ImageIO.read(inputStream);
				int width = bi.getWidth();
				int height = bi.getHeight();
				String size = width + "x" + height;
				String newFileName = MyDateUtils.dateToString(new Date(), "yyyyMMddHHmmss") + "_" + size;
				newFileName += "." + fileExt;
				// 创建文件夹
				savePath += dirName + "/" + imageDirName;
				File saveDirFile = new File(savePath);
				if (!saveDirFile.exists()) {
					saveDirFile.mkdirs();
				}
				// 获取文件的绝对路径
				savePath = savePath + "/" + newFileName;
				// 保存到文件系统
				try {
					fs = new FileOutputStream(savePath);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					map.put("success", false);
					map.put("msg", "输出文件找不到！请联系开发人员。");
					return map;
				}
				// 写入文件系统
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				// 创建一个Buffer字符串
				byte[] buffer = new byte[1024];
				// 每次读取的字符串长度，如果为-1，代表全部读取完毕
				int len = 0;
				// 使用一个输入流从buffer里把数据读取出来
				while ((len = tempinputStream.read(buffer)) != -1) {
					// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
					outStream.write(buffer, 0, len);
				}
				// 关闭输入流
				inputStream.close();
				// 把outStream里的数据写入内存
				byte[] data = outStream.toByteArray();
				fs.write(data);
				tempinputStream.close();
				inputStream.close();
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		map.put("success", true);
		map.put("msg", "上传成功");
		return map;
	}

}
