package com.servlet;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.Bean.PageBean;
import com.Bean.filmBean;

import dao.SearchDao;
import dao.SimpleDao;
import dao.filmDao;
import luceneuse.keywordsearch;
//主要的search管理类，用于多数的搜索管理

public class DosearchServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String field=req.getParameter("field");
		String keyword=req.getParameter("search_text");
		String search=req.getParameter("searchtype");
		System.out.println(req.getParameter("searchtype"));
		SimpleDao filmsimple=new SimpleDao();
		SearchDao idlist=new SearchDao();
		int currentPage=1;
		List<String> idSet=new ArrayList<String>();
		List<String> currentSet=new ArrayList<String>();
		List<String> currenthotSet=new ArrayList<String>();
		List<String> currenthighSet=new ArrayList<String>();
		List<filmBean> filmlist=new ArrayList<filmBean>();
		List<filmBean> filmlisthot=new ArrayList<filmBean>();
		List<filmBean> filmlisthigh=new ArrayList<filmBean>();
		int hottestid=0;
		int highid=0;
		if(search.equals("whole")){
			String[] fields={"id","url","name","director","writer","mainactor","type","country","language","date"
					,"time","rename","score","commentsnum","percent","brief","award","website"};
			String[] keywords={keyword,keyword,keyword,keyword,keyword,keyword,keyword,keyword,keyword,keyword,keyword,keyword,keyword,keyword,keyword,
					keyword,keyword,keyword};			
				idSet=idlist.MultisearchByterm(fields, keywords);				
	            int totalrows=idSet.size();
	            System.out.println("The idset.size is"+idSet.size());
	            if(idSet.size()!=0){
	    		PageBean currentpage=new PageBean(currentPage,totalrows);
	    		int startindex=currentpage.getPageStartindex();
	    		System.out.println("the startindex is"+startindex);
	    		int endindex=currentpage.getPageEndindex();
	    		System.out.println("the endindex is"+endindex);
	    		currentSet=idlist.MultisearchByPage(fields, keywords, startindex, endindex);
	    		currenthotSet=idlist.HotMultisearchByPage(fields,keywords,startindex,endindex);
	    		currenthighSet=idlist.HighMultisearchByPage(fields,keywords,startindex,endindex);
	    		filmlist=filmsimple.findOne(currentSet);
	    		filmlisthot=filmsimple.findOne(currenthotSet);
	    		hottestid=filmlisthot.get(0).getId();
	    		filmlisthigh=filmsimple.findOne(currenthighSet);
	    		highid=filmlisthigh.get(0).getId();
	            }
	    }
		else{
		idSet=idlist.SearchByTerm(field, keyword);
		//filmlist=filmsimple.findOne(idSet);
		int totalrows=idSet.size();
		PageBean currentpage=new PageBean(currentPage,totalrows);
		int startindex=currentpage.getPageStartindex();
		int endindex=currentpage.getPageEndindex();
		currentSet=idlist.SearchByPage(field, keyword, startindex, endindex);
		currenthotSet=idlist.HotSearchByPage(field,keyword,startindex,endindex);
		currenthighSet=idlist.HighSearchByPage(field,keyword,startindex,endindex);
		filmlist=filmsimple.findOne(currentSet);
		filmlisthot=filmsimple.findOne(currenthotSet);
		hottestid=filmlisthot.get(0).getId();
		filmlisthigh=filmsimple.findOne(currenthighSet);
		highid=filmlisthigh.get(0).getId();
		if((!filmlist.isEmpty())&&(!filmlisthot.isEmpty())&&(!filmlisthigh.isEmpty())){
		req.setAttribute("field", field);
		}
		else{
			req.getRequestDispatcher("error.jsp").forward(req,resp); 
		}
		}
		
		System.out.println(idSet.size());
		if(idSet.size()!=0){
		if(!filmlist.isEmpty()){
	    req.setAttribute("hottestid", hottestid);
	    req.setAttribute("highid",highid);
		req.setAttribute("search_text", keyword);
		req.setAttribute("searchType", search);
		//req.setAttribute("curretSet",currentSet);
		//req.setAttribute("currethotSet", currenthotSet);
		//req.setAttribute("currethighSet", currenthighSet);
		req.setAttribute("filmlist",filmlist);
		req.setAttribute("filmlisthot",filmlisthot);
		req.setAttribute("filmlisthigh",filmlisthigh);
		req.setAttribute("currentPage",currentPage);
		req.setAttribute("totalrows", idSet.size());
		req.getRequestDispatcher("result.jsp").forward(req,resp); 
		}
		else{
			req.getRequestDispatcher("error.jsp").forward(req,resp); 
		}
		}else{
			req.getRequestDispatcher("Noresult.jsp").forward(req,resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}
	
}
