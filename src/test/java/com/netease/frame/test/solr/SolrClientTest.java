package com.netease.frame.test.solr;

import java.io.IOException;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.BooleanQuery.Builder;
import org.apache.lucene.search.TermQuery;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;

import com.netease.okr.util.MyStringUtil;

public class SolrClientTest {

	private static SolrClient solrClient;

	private static final String DEFAULT_URL = "http://10.171.160.53:8983/solr";

	public static void main(String[] arg) throws Exception {
		try {
			solrClient = new HttpSolrClient(DEFAULT_URL + "/user");
			/*SolrDocument solrDocument = solrClient.getById("2");
			System.out.println(solrDocument.toString());*/
			
			//定义查询内容 * 代表查询所有    这个是基于结果集  
	         SolrQuery query = new SolrQuery("*:*"); //定义查询内容  
	         query.setStart(3);//起始页  
	         query.setRows(3);//每页显示数量  
	         QueryResponse rsp = solrClient.query( query );  
	         SolrDocumentList results = rsp.getResults();  
	         System.out.println(results.getNumFound());//查询总条数  
	         for(SolrDocument doc:results){  
	             System.out.println(doc);  
	         }  
			//create();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			solrClient.close();
		}

	}
	
	private static String getQueryExpression() {
		Builder builder = new BooleanQuery.Builder();
/*        BooleanQuery queryfinal = builder.build();*/
        
        addTerm(builder, "id", "1", BooleanClause.Occur.MUST);
        //addTerm(builder, "name", "yejinfu", BooleanClause.Occur.MUST);
        /*setKeyword(queryfinal, CustomerFields.BUYER_NICK, buyerNick);
        setKeyword(queryfinal, CustomerFields.NAME, name);*/
        

        String expression = builder.toString();
        if (MyStringUtil.isBlank(expression)) {
            expression = "*:*";
        }
        return expression;
    }
	
	private static void addTerm(Builder builder, String name, Object val, BooleanClause.Occur flag) {
        if (val != null) {
            Term term = null;
            if (val instanceof String) {
                if (MyStringUtil.isNotBlank((String) val)) {
                    term = new Term(name, (String) val);
                }
            } else {
                term = new Term(name, String.valueOf(val));
            }
            if (term != null) builder.add(new TermQuery(term), flag);
        }
    }
	
	private static void create() throws Exception {
		// 链接到本地的core1核心文件
		HttpSolrClient server = new HttpSolrClient(DEFAULT_URL + "/user");
		// 创建数据
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", "3");
		doc.addField("name", "测试3");
		server.add(doc);
		server.commit();
	}
}