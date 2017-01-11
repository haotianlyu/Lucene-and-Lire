package dao;
//电影信息读取类，此类不通过数据库，而是直接从lucene的documents中通过倒排表读取出除了图片信息外的所有数据
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.Bean.filmBean;
import com.luceneutil.luceneUtil;

public class filmDao extends luceneUtil{
	
	public List<filmBean> SearchByTerm(String field,String keyword){
		List<filmBean> filmList=new ArrayList<filmBean>();
		IndexSearcher indexsearcher=null;
		Query query=null;
		try{
			indexsearcher=getSearch();
			Analyzer analyzer = getAnalyzer();
			QueryParser qp = new QueryParser(Version.LUCENE_4_9,
		               field,analyzer);
		       qp.setDefaultOperator(QueryParser.OR_OPERATOR);
		       try {
		    	   System.out.println("try come in");
		    	   query=qp.parse(keyword);
		           ScoreDoc[] hits;
		           System.out.println("get something");
		           hits = indexsearcher.search(query, null, 3).scoreDocs;
		           System.out.println("hits get value");
		           System.out.println(hits.length);
		           for (int i = 0; i < hits.length; i++) {
		        	   Document doc=indexsearcher.doc(hits[i].doc);
		        	   filmBean film=new filmBean(Integer.parseInt(doc.get("id")),doc.get("url"),doc.get("name"),doc.get("director"),doc.get("writer"),doc.get("mainactor"),
		        			   doc.get("type"),doc.get("country"),doc.get("language"),doc.get("date"),doc.get("time"),doc.get("rename"),
		        			   Float.parseFloat(doc.get("score")),Integer.parseInt(doc.get("commentsnum")),doc.get("percent"),doc.get("brief"),doc.get("award"),doc.get("website"));
		        	   filmList.add(film);
		        	   System.out.println(doc.get("name"));
		           }       
		           System.out.println("try finished");
		       } catch (IOException e) {
		    	   System.out.println("failed1");
		           e.printStackTrace();
		       } catch (ParseException e) {
		    	   System.out.println("failed2");
		           e.printStackTrace();
		       }
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
 		return filmList;		
	}
	
	public List<filmBean> MultisearchByterm(String[] fields,String[] keyword){
		    List<filmBean> filmList=new ArrayList<filmBean>();
		    IndexSearcher indexsearcher=null;
		    List<String> idset=new ArrayList<String>();
		    boolean exist=false;
	try{
		    System.out.println("start");
		    Query query=null;  	
		    indexsearcher = getSearch();
	        Analyzer analyzer =  getAnalyzer();
	        BooleanClause.Occur[] clauses={BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD
	        		,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,
	        		BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,
	        		BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD,
	        		BooleanClause.Occur.SHOULD,BooleanClause.Occur.SHOULD};
		try {
			System.out.println("query");
	        query=MultiFieldQueryParser.parse(Version.LUCENE_4_9, keyword, fields, clauses, analyzer);
	        ScoreDoc[] hits=indexsearcher.search(query, 1000).scoreDocs;
	        for(int i=0;i<hits.length;i++){
	        	Document doc = indexsearcher.doc(hits[i].doc);
	        	filmBean film=new filmBean(Integer.parseInt(doc.get("id")),doc.get("url"),doc.get("name"),doc.get("director"),doc.get("writer"),doc.get("mainactor"),
	        			   doc.get("type"),doc.get("country"),doc.get("language"),doc.get("date"),doc.get("time"),doc.get("rename"),
	        			   Float.parseFloat(doc.get("score")),Integer.parseInt(doc.get("commentsnum")),doc.get("percent"),doc.get("brief"),doc.get("award"),doc.get("website"));
	        	exist=false;
	        	if(idset.contains(doc.get("id"))){
	        			exist=true;
	        	}
	        	if(!exist){
	            idset.add(doc.get("id"));
	        	filmList.add(film);
	        	}
	        
	        }
		
		} catch (IOException e) {
	    	   System.out.println("failed1");
	           e.printStackTrace();
	    }catch (ParseException e) {
	    	   System.out.println("failed2");
	           e.printStackTrace();
	       }
		
	}catch(Exception e){
		e.printStackTrace();
	}
	        System.out.println(idset.size()); 
			return filmList;
	}

}
