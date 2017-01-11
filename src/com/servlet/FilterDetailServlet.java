package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Bean.CommentBean;
import com.Bean.filmBean;
import com.factory.Factory;

import dao.CommentDao;
import dao.DetailDao;
import dao.DetailFactoryDao;

public class FilterDetailServlet extends HttpServlet {

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
		String idstr=req.getParameter("id");
		int id=Integer.parseInt(idstr);
		CommentDao comment=new CommentDao();
		List<CommentBean> commentlist=new ArrayList<CommentBean>();
		commentlist=comment.searchComment(id);
		DetailFactoryDao f=(DetailFactoryDao)Factory.getFactoryInstance("DetailDaoWithInterface");
		//DetailDao detail=new DetailDao();
		//filmBean film=detail.findOne(id);
		filmBean film=f.findOne(id);
		if(film!=null){
			req.setAttribute("commentlist", commentlist);
			req.setAttribute("film", film);
			req.getRequestDispatcher("filminfo.jsp").forward(req,resp);}
			else{
				req.getRequestDispatcher("error.jsp").forward(req,resp); 
			}
	}
	
}
