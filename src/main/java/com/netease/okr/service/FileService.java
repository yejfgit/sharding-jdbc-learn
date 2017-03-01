package com.netease.okr.service;

import java.io.File;

import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import com.netease.okr.model.entity.Appendix;


public interface FileService {

	/**
	 * 上传文件
	 * 
	 * @param file
	 * @return
	 * @throws DataAccessException
	 */
	public Appendix upload(MultipartFile file) throws DataAccessException;
	
	/**
	 * 上传文件
	 * 
	 * @param file
	 * @return
	 * @throws DataAccessException
	 */
	public Appendix upload(String fileName, byte[] file) throws DataAccessException;

	/**
	 * 删除附件
	 * 
	 * @param id
	 * @return
	 * @throws DataAccessException
	 */
	public String delete(Integer id) throws DataAccessException;

	public String getNosKey();

	public String uploadFile(File file, String fileName, String nosKey) throws DataAccessException;

}
