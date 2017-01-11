package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Bean.PageBean;
import com.Bean.filmBean;

import dao.FilterDao;
import dao.SearchDao;
import dao.SimpleDao;

public class FiltersearchServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<String> keywords=new ArrayList<String>();
		String keyword="";
		String[] checkbox=req.getParameterValues("checkfield");
		System.out.println("checkbox"+checkbox.length);
		for(int i=0;i<checkbox.length;i++){
			System.out.println(i+checkbox[i]);
			if(checkbox[i].equals("mainactors")){
				keyword=req.getParameter("search_text1");
				keywords.add(keyword);
			}else if(checkbox[i].equals("director")){
				keyword=req.getParameter("search_text2");
				keywords.add(keyword);
			}else if(checkbox[i].equals("award")){
				keyword=req.getParameter("search_text3");
				keywords.add(keyword);
			}else if(checkbox[i].equals("writer")){
				keyword=req.getParameter("search_text4");
				keywords.add(keyword);
			}else if(checkbox[i].equals("name")){
				keyword=req.getParameter("search_text5");
				keywords.add(keyword);
			}else if(checkbox[i].equals("date")){
				keyword=req.getParameter("search_text6");
				keywords.add(keyword);
			}else if(checkbox[i].equals("time")){
				keyword=req.getParameter("search_text7");
				keywords.add(keyword);
			}else if(checkbox[i].equals("type")){
				keyword=req.getParameter("search_text8");
				keywords.add(keyword);
			}else if(checkbox[i].equals("country")){
				keyword=req.getParameter("search_text9");
				keywords.add(keyword);
			}else if(checkbox[i].equals("language")){
				keyword=req.getParameter("search_text10");
				keywords.add(keyword);
			}			
		}
		String message=null;
		String[] keywordset=new String[checkbox.length];
        keywords.toArray(keywordset);
        for(int j=0;j<checkbox.length;j++){
        	System.out.println(j+keywordset[j]);
        }
		SimpleDao filmsimple=new SimpleDao();
		FilterDao idlist=new FilterDao();
		List<String> idSet=new ArrayList<String>();
		List<filmBean> filmlist=new ArrayList<filmBean>();		
			idSet=idlist.MultisearchByterm(checkbox, keywordset);				
            System.out.println("The idset.size is"+idSet.size());
            if(idSet.size()!=0){
    		filmlist=filmsimple.findOne(idSet);
    		if(idSet.size()==1){
    			message="1 result matched has been searched";
    		}
    		else if(idSet.size()>1){
    			message=idSet.size()+"results matched have been searched";
    		}
    		if(!filmlist.isEmpty()){
    		req.setAttribute("filmlist",filmlist);
    		req.setAttribute("filter.message",message);
    		req.getRequestDispatcher("Filterresult.jsp").forward(req,resp); 
    		}
    		else{
    			req.getRequestDispatcher("error.jsp").forward(req,resp); 
    		}
    		}else{
    			req.getRequestDispatcher("Noresult.jsp").forward(req,resp);
    		}
	}
}
