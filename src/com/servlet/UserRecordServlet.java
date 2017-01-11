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
import dao.DetailFactoryDao;
import dao.RecommendDao;

public class UserRecordServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username=(String)req.getSession().getAttribute("username");
		CommentDao comment=new CommentDao();
		List<CommentBean> commentlist=new ArrayList<CommentBean>();
		commentlist=comment.searchAllComment(username);
		List<filmBean> filmlist=new ArrayList<filmBean>();
		filmBean film=null;
		DetailFactoryDao f=(DetailFactoryDao)Factory.getFactoryInstance("RecommendDaoWithInterface");
		//RecommendDao filminfo=new RecommendDao();
		for(int i=0;i<commentlist.size();i++){
			int filmid=commentlist.get(i).getFilmid();
			film=f.findOne(filmid);
			filmlist.add(film);
		}
		req.setAttribute("commentlist", commentlist);
		req.setAttribute("filmlist", filmlist);
		req.getRequestDispatcher("userRecord.jsp").forward(req,resp); 
	}

}
