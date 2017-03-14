package com.netease.okr.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.netease.okr.mapper.okr.AppendixMapper;
import com.netease.okr.model.entity.Appendix;
import com.netease.okr.service.FileService;
import com.netease.okr.util.ConstantsUtil;
import com.netease.okr.util.LoggerUtil;
import com.netease.okr.util.MyStringUtil;



@Service("fileService")
public class FileServiceImpl implements FileService {

	@Autowired
	private NosBaseServiceImpl nosBaseService;
	@Autowired
	private AppendixMapper appendixMapper;

	@Override
	public Appendix upload(MultipartFile file) throws DataAccessException {
		
		String fileName = file.getOriginalFilename();
		
		String realPath =System.getProperty(ConstantsUtil.REAL_PATH)+ConstantsUtil.FILE_TMP_DIR_PATH + File.separator
				+ UUID.randomUUID() + fileName;
		File tempFile = new File(realPath);
		
		try {
			if(!tempFile.getParentFile().exists()) {  
	            //如果目标文件所在的目录不存在，则创建父目录  
				LoggerUtil.info("目标文件所在目录不存在，准备创建它！");  
	            if(!tempFile.getParentFile().mkdirs()) {  
	            	LoggerUtil.info("创建目标文件所在目录失败！");  
	            }  
	        }  
			
			if (!tempFile.exists()) {
				LoggerUtil.info("目标文件不存在，准备创建它！");  
				if(!tempFile.createNewFile()) {  
					LoggerUtil.info("创建目标文件失败！");  
	            } 
			}
			
			LoggerUtil.info("创建文件成功");
			
			file.transferTo(tempFile);

			Appendix appendix = uploadUrlCommon(tempFile, fileName);
			tempFile.delete();
			return appendix ;
		} catch (IllegalStateException e) {
			LoggerUtil.error("上传文件异常", e);
		} catch (IOException e) {
			LoggerUtil.error("上传文件异常", e);
		}
		return null;
	}
	
	@Override
	public Appendix upload(String fileName, byte[] file) throws DataAccessException {
		String realPath =  ConstantsUtil.FILE_TMP_DIR_PATH + File.separator
				+ UUID.randomUUID() + fileName;
		File tempFile = new File(realPath);
		try {
			FileUtils.writeByteArrayToFile(tempFile, file);
			Appendix appendix = uploadUrlCommon(tempFile, fileName);
			return appendix ;
		} catch (IOException e) {
			LoggerUtil.error("上传文件异常", e);
		}
		return null;
	}

	@Override
	public String delete(Integer id) throws DataAccessException {
		Appendix app = appendixMapper.getAppendix(id);
		String msg = "";
		if (app == null) {
			msg = "附件已不存在";
		} else {
			if (appendixMapper.deleteAppendix(id) > 0)
				msg = nosBaseService.delete(app.getNosKey());
			else
				msg = "删除附件错误";
		}
		return msg;
	}

	/**
	 * @param file
	 * @param fname
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private Appendix uploadUrlCommon(File file, String fname)
			throws UnsupportedEncodingException {
		String nosKey = getNosKey();
		LoggerUtil.info("nosKey:" + nosKey);
		String msg = nosBaseService.upload(nosKey, file);
		if (StringUtils.isNotEmpty(msg))
			return null;

		URL longNosUrl = nosBaseService.generatePresignedUrl(nosKey, fname);
		
		String shortNosUrl = getUrl(longNosUrl.toString());
		
		// 保存appendix表
		Appendix appendix = new Appendix();
		appendix.setName(fname);
		appendix.setNosKey(nosKey);// noskey
		appendix.setUrl(shortNosUrl);

		if (appendixMapper.addAppendix(appendix) < 0) {
			LoggerUtil.error("保存附件数据错误", null);
			nosBaseService.delete(nosKey);
			return null;
		}

		return appendix;
	}

	private String getUrl(String url){
		
		String retUrl = "";
		
		if(MyStringUtil.isNotBlank(url)){
			String[] urls1 = url.split("\\?");
			retUrl = urls1[0];
			String[] urls2 = urls1[1].split("&");
			for(int i=0;i<urls2.length;i++){
				urls2[i].contains("download=");
				retUrl+="?"+urls2[i];
				break;
			}
			
		}
		return retUrl;
	}
	
	/**
	 * 上传文件
	 */
	@Override
	public String uploadFile(File file, String fileName,String nosKey) throws DataAccessException {
		if (file == null) {
			LoggerUtil.info("uploadFile()---file is null");
			return null;
		}
		//String nosKey = getNosKey();
		LoggerUtil.info("nosKey:" + nosKey);
		String msg = nosBaseService.upload(nosKey, file);
		if (StringUtils.isNotEmpty(msg))
			return null;
		URL nosUrl = null;
		try {
			nosUrl = nosBaseService.generatePresignedUrl(nosKey, fileName);
			file.delete();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return nosUrl == null ? null : getUrl(nosUrl.toString());
	}
	
	
	/**
	 * @return
	 */
	@Override
	public String getNosKey() {
		Random r = new Random();
		r.setSeed(new Date().getTime());
		String pre = String.valueOf(r.nextInt(1000000));

		return pre + "-" + UUID.randomUUID().toString().replaceAll("-", "");
	}

}
