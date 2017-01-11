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
import dao.FactoryDao;
import dao.RecommendDao;

public class SearchDetailServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("In servlet detail search");
		String idstr=req.getParameter("id");
		int id=Integer.parseInt(idstr);
		String current=req.getParameter("currentPage");
		String total=req.getParameter("totalrows");
		String rankstr=req.getParameter("rank");
		String keyword=req.getParameter("keyword");
		List<CommentBean> commentlist=new ArrayList<CommentBean>();
		CommentDao comment=new CommentDao();
		commentlist=comment.searchComment(id);
		DetailFactoryDao f=(DetailFactoryDao)Factory.getFactoryInstance("DetailDaoWithInterface");
		//DetailDao detail=new DetailDao();
		//filmBean detailfilm=detail.findOne(id);
		filmBean detailfilm=f.findOne(id);
		System.out.println("the score"+detailfilm.getScore());
		System.out.println("the actor is"+detailfilm.getMainactors());
		//RecommendDao recom=new RecommendDao();
		int hottestid=Integer.parseInt(req.getParameter("hottestid"));
		int highid=Integer.parseInt(req.getParameter("highid"));
		f=(DetailFactoryDao)Factory.getFactoryInstance("RecommendDaoWithInterface");
		//filmBean hottest=recom.findRecommendDao(hottestid);
		//filmBean highest=recom.findRecommendDao(highid);
		filmBean hottest=f.findOne(hottestid);
		filmBean highest=f.findOne(highid);
		req.setAttribute("commentlist", commentlist);
		req.setAttribute("hottest", hottest);
		req.setAttribute("highest", highest);
		req.setAttribute("currentPage", current);
		req.setAttribute("totalrows",total);
		req.setAttribute("rank", rankstr);
		req.setAttribute("keyword", keyword);
		if(detailfilm!=null){
		req.setAttribute("film", detailfilm);
		req.getRequestDispatcher("filminfore.jsp").forward(req,resp);}
		else{
			req.getRequestDispatcher("error.jsp").forward(req,resp); 
		}
		
	}

}
