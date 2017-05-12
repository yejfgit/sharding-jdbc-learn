package com.netease.okr.Lucence;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import com.netease.okr.util.LoggerUtil;
import com.netease.okr.util.PropertiesUtils;

public class SolrClientUtil {

	private static SolrClient solrClient;

	private static final String DEFAULT_URL = new PropertiesUtils("config", null).getPropery("solr.host");;

	@SuppressWarnings("deprecation")
	public static void buildIndex(SolrInputDocument doc, String core){
		try {
			solrClient = new HttpSolrClient(DEFAULT_URL + core);
			solrClient.add(doc);
			solrClient.commit();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			LoggerUtil.error("创建索引异常SolrServerException", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LoggerUtil.error("创建索引异常IOException", e);
		} finally {
			try {
				solrClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LoggerUtil.error("solrClient。close异常", e);
			}
		}

	}

	@SuppressWarnings("deprecation")
	public static SolrDocumentList search(SolrQuery solrQuery, String core) {
		SolrDocumentList docs = null;
		try {
			solrClient = new HttpSolrClient(DEFAULT_URL + core);
			QueryResponse rsp = solrClient.query(solrQuery);
			docs = rsp.getResults();  

		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			LoggerUtil.error("检索索引异常SolrServerException", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LoggerUtil.error("检索索引异常IOException", e);
		} finally {
			try {
				solrClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LoggerUtil.error("solrClient。close异常", e);
			}
		}
		
		return docs;

	}
	
	
	@SuppressWarnings("deprecation")
	public static void deleteIndex(String core) throws Exception {
		try {
			solrClient = new HttpSolrClient(DEFAULT_URL + core);
			solrClient.deleteByQuery("*:*");
			solrClient.commit();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			LoggerUtil.error("删除索引异常SolrServerException", e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LoggerUtil.error("删除索引异常IOException", e);
		} finally {
			try {
				solrClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LoggerUtil.error("solrClient。close异常", e);
			}
		}
		

	}
}