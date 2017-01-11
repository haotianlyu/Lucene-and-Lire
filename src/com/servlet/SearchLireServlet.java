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

import dao.CommentDao;
import dao.LireinfoDao;

public class SearchLireServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String photoid=req.getParameter("photoid");
		LireinfoDao info=new LireinfoDao();
		CommentDao comment=new CommentDao();
		List<CommentBean> commentlist=new ArrayList<CommentBean>();
		filmBean film=info.findfilm(photoid);
		if(film!=null){
			int id=film.getId();
			commentlist=comment.searchComment(id);
			req.setAttribute("commentlist", commentlist);
			req.setAttribute("film", film);
			req.getRequestDispatcher("filminfo.jsp").forward(req,resp);}
			else{
				req.getRequestDispatcher("error.jsp").forward(req,resp); 
			}
	}

}
