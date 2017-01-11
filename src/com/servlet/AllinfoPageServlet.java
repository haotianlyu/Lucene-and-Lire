package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Bean.PageBean;
import com.Bean.filmBean;

import dao.SearchDao;
import dao.SimpleDao;

public class AllinfoPageServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String search=req.getParameter("searchType");
		String keyword=req.getParameter("search_text");
		String current=req.getParameter("currentPage");
		System.out.println("now the current page is"+current);
		int currentPage=Integer.parseInt(current);
		String total=req.getParameter("totalrows");
		int totalrows=Integer.parseInt(total);
		SimpleDao filmsimple=new SimpleDao();
		SearchDao idlist=new SearchDao();
		List<String> currentSet=new ArrayList<String>();
		List<String> currenthotSet=new ArrayList<String>();
		List<String> currenthighSet=new ArrayList<String>();
		List<filmBean> filmlist=new ArrayList<filmBean>();
		List<filmBean> filmlisthot=new ArrayList<filmBean>();
		List<filmBean> filmlisthigh=new ArrayList<filmBean>();
		String hottestids=req.getParameter("hottestid");
		int hottestid=Integer.parseInt(hottestids);
		String highids=req.getParameter("highid");
		int highid=Integer.parseInt(highids);
		if(search.equals("whole")){
			String[] fields={"id","url","name","director","writer","mainactor"
					,"type","country","language","date"
					,"time","rename","score","commentsnum"
					,"percent","brief","award","website"};	
			String[] keywords={keyword,keyword,keyword,keyword,keyword,keyword,keyword
					,keyword,keyword,keyword,keyword,keyword,keyword,keyword,keyword,
					keyword,keyword,keyword};
			PageBean currenttotal=new PageBean(currentPage,totalrows);
			int startindex=currenttotal.getPageStartindex();
			int endindex=currenttotal.getPageEndindex();
			currentSet=idlist.MultisearchByPage(fields, keywords, startindex, endindex);
			currenthotSet=idlist.HotMultisearchByPage(fields, keywords, startindex, endindex);
			currenthighSet=idlist.HighMultisearchByPage(fields, keywords, startindex, endindex);
			filmlist=filmsimple.findOne(currentSet);
			filmlisthot=filmsimple.findOne(currenthotSet);
			filmlisthigh=filmsimple.findOne(currenthighSet);
		}
		else{
			String field=req.getParameter("field");
			PageBean currentpage=new PageBean(currentPage,totalrows);
			int startindex=currentpage.getPageStartindex();
			int endindex=currentpage.getPageEndindex();
			System.out.println(startindex);
			System.out.println(endindex);
			currentSet=idlist.SearchByPage(field, keyword, startindex, endindex);
			currenthotSet=idlist.HotSearchByPage(field, keyword, startindex, endindex);
			currenthighSet=idlist.HighSearchByPage(field, keyword, startindex, endindex);
			filmlist=filmsimple.findOne(currentSet);
			filmlisthot=filmsimple.findOne(currenthotSet);
			filmlisthigh=filmsimple.findOne(currenthighSet);
			req.setAttribute("field", field);
		}
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
			req.setAttribute("totalrows", totalrows);
			req.getRequestDispatcher("result.jsp").forward(req,resp); 
			}
			else{
				req.getRequestDispatcher("error.jsp").forward(req,resp); 
			}
	}

}
