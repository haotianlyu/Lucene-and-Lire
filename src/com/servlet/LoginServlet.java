package com.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String fromURL = req.getHeader("Referer");
		System.out.println(fromURL);
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		UserDao userlogin=new UserDao();
		boolean exist=userlogin.testuser(username);
		if(exist){
			String confirm=userlogin.login(username);
			System.out.println(confirm);
			if(confirm.equals(password)){
				req.getSession().setAttribute("username", username);
				req.getSession().removeAttribute("message");
				resp.sendRedirect(fromURL);
			}
			else{
				String message="password is not correct";
				System.out.println(message);
				req.getSession().setAttribute("message", message);
				resp.sendRedirect(fromURL);
			}
		}else{
			    String message="user is not exist";
			    System.out.println(message);
			    req.getSession().setAttribute("message", message);
			    resp.sendRedirect(fromURL);
		}
	}

}
