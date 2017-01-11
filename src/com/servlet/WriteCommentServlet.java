package com.servlet;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDao;

public class WriteCommentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String fromURL = req.getHeader("Referer");
		String comments=req.getParameter("commentvalue");
		String username=(String)req.getSession().getAttribute("username");
		String id=req.getParameter("id");
		int filmid=Integer.parseInt(id);
		Format df = new SimpleDateFormat("yyyyMMdd");
		String date=df.format(new Date());
		CommentDao comment=new CommentDao();
		comment.addComment(filmid,username,comments,date);
		resp.sendRedirect(fromURL);
	}

}
