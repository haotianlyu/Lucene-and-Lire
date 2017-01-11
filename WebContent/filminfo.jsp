<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page errorPage="error.jsp" %>
<%@ page import="com.Bean.filmBean" %>
<%@ page import="com.Bean.CommentBean" %>
<%@ page import="java.util.*" %>
<% 
   filmBean film=(filmBean)request.getAttribute("film");
%>
<html>
<head>
<title><%=film.getName() %></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Photo-Hub Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Custom Theme files -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!-- Custom Theme files -->
<!--webfont-->
<script language="javascript">
   function logintest(){
	   if(document.getElementById("username").value==""||document.getElementById("username").value==null){alert("Username can not be empty");return false}
	   else if(document.getElementById("password").value==""||document.getElementById("password").value==null){alert("password can not be empty");return false}
	   else return true;
   }  
</script>
<script language="javascript">
   function testcomment(){
	   if(document.getElementById("commentvalue").value==""||document.getElementById("commentvalue").value==null){alert("You should write some comments before you submit");return false}
	   else return true;
   }  
</script>
<link href='http://fonts.useso.com/css?family=Quicksand:300,400,700' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Open+Sans:300,400,600,700,800' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="js/menu_jquery.js"></script> 
</head>
<%
   String username=null;
   username=(String)request.getSession().getAttribute("username");
   String message=null;
   message=(String)request.getSession().getAttribute("message");
   List<CommentBean> comment=new ArrayList<CommentBean>();
   comment=(List<CommentBean>)request.getAttribute("commentlist");
%>  
<body>
	<div class="header">	
      <div class="container"> 
  	     <div class="logo">
			<h1><a href="Index.jsp">FILM Hub</a></h1>
		 </div>
		 <div class="top_right">
		   <ul>
		   <%if(message!=null&&!message.equals("")) {%>
		       <li><%=message %></li>|
		   <%} %>
			<li><a href="Register.jsp">Register</a></li>|
			<%if(username==null||username.equals("")) {%>
			<li class="login" >
				 <div id="loginContainer"><a href="#" id="loginButton"><span>Login</span></a>
					  <div id="loginBox">                
						  <form action="LoginServlet" method="post" id="loginForm" onsubmit="return logintest()">
			                <fieldset id="body">
			                	<fieldset>
			                          <label for="username">Username</label>
			                          <input type="text" name="username" id="username">
			                    </fieldset>
			                    <fieldset>
			                            <label for="password">Password</label>
			                            <input type="password" name="password" id="password">
			                     </fieldset>
			                    <input type="submit" id="login" value="Sign in">
						   </form>
				        </div>
			      </div>
			  </li>
			  <%}else{ %>
			     <li><div><a href="UserRecordServlet"><span><%=username%></span></a></div></li>|
			     <li><div><a href="LogoutServlet"><span>Logout</span></a></div></li>
			  <%} %>
		   </ul>
	     </div>
		 <div class="clearfix"></div>
		</div>
	</div>
	<div class="single">
		<div class="container">
		   <div class="single_box1">
			 <div class="col-sm-5 single_left">
				<img src="images/photodatabase/graph/<%=film.getPhotoid() %>" class="img-responsive" onerror=this.src='images/default.jpg' alt=""/><br>
				<% if(!film.getUrl().isEmpty()&&!film.getUrl().equals("")) {%>
					<a href="<%=film.getUrl()%>" >
					<b>外网链接</b></a>
				<%} %><br/><hr/>
			 </div>
			 <div class="col-sm-7 col_6">
				<ul class="size">
					<h3><%=film.getName() %></h3>
					<% if(!film.getRename().isEmpty()&&!film.getRename().equals("")) {%>
					<b>又名：</b>
					<%=film.getRename()%>
					<%} %><br/>
					
					
					<% if(!film.getDirector().isEmpty()&&!film.getDirector().equals("")) {%>
					<li><b>导演：</b>
					<%=film.getDirector()%></li>
					<%} %><br/>
					
					<% if(!film.getWriter().isEmpty()&&!film.getWriter().equals("")) {%>
					<li><b>编剧：</b>
					<%=film.getWriter()%></li>
					<%} %><br/>
					
					<% if(!film.getMainactors().isEmpty()&&!film.getMainactors().equals("")) {%>
					<li><b>主演：</b>
					<%=film.getMainactors()%></li>
					<%} %><br/>
					
					<% if(!film.getType().isEmpty()&&!film.getType().equals("")) {%>
					<li><b>类型：</b>
					<%=film.getType()%></li>
					<%} %><br/>
					
					<% if(!film.getDate().isEmpty()&&!film.getDate().equals("")) {%>
					<li><b>上映日期：</b>
					<%=film.getDate()%></li>
					<%} %><br/>
					
					<% if(!film.getTime().isEmpty()&&!film.getTime().equals("")) {%>
					<li><b>片长：</b>
					<%=film.getTime()%></li>
					<%} %><br/>
					
					<% if(!film.getCountry().isEmpty()&&!film.getCountry().equals("")) {%>
					<li><b>出品国或地区：</b>
					<%=film.getCountry()%></li>
					<%} %><br/>
					
					<% if(!film.getLanguage().isEmpty()&&!film.getLanguage().equals("")) {%>
					<li><b>语言：</b>
					<%=film.getLanguage()%></li>
					<%} %><br/>
					
					<% if(!film.getBrief().isEmpty()&&!film.getBrief().equals("")) {%>
					<li><b>剧情概要：</b>
					<%=film.getBrief()%></li>
					<%} %><br/>
					
					<% if(!film.getAward().isEmpty()&&!film.getAward().equals("")) {%>
					<li><b>获奖情况：</b>
					<%=film.getAward()%></li>
					<%} %><br/>
					
					<% if(!film.getWebsite().isEmpty()&&!film.getWebsite().equals("")) {%>
					<li><b>官方网站：</b>
					<%=film.getWebsite()%></li>
					<%} %><br/>
					
					<% if(film.getScore()!=0&&film.getCommentsnum()!=0){%>
		                 <li>电影评分：<%=film.getScore()%>/10,由<%=film.getCommentsnum() %>人评出</li>
		            <%} %>
		            <br/>
		            <% if(!film.getPercent().isEmpty()&&!film.getPercent().equals("")) {%>  
		                <% String[] aa=film.getPercent().split("/"); %>
		                <br><img src="images/fivestar.png"><%=aa[4]%><br/>
		                <img src="images/fourstar.png"><%=aa[3]%><br/>
		                <img src="images/threestar.png"><%=aa[2]%><br/>
		                <img src="images/twostar.png"><%=aa[1]%><br/>
		                <img src="images/onestar.png"><%=aa[0]%><br/>
		            <% }%>   
				</ul>
			</div>
			 <div class="clearfix"> </div>
			<div>
			<%if(comment.size()>0){%>
			    <ul>
			    <h4>comments</h4>
			    <%for(int i=0;i<comment.size();i++){ %>
			       <li>
			          <%=comment.get(i).getComments() %><br>
			       </li>
			          <b>Writen by user </b><%=comment.get(i).getUsername() %>   <br>
			          <b>Date is </b><%=comment.get(i).getDate() %><br>
			       <div class="clearfix"> </div>		    	   
			<%}%></ul><%}%>
			</div>
			<%if(username!=null&&!username.equals("")) {%>
			<div>
			   <form action="WriteCommentServlet?id=<%=film.getId()%>" method="post" onsubmit="return testcomment()">    
			     <h4>Write Comments</h4><br>
			     <textarea name="commentvalue" id="commentvalue">
			     </textarea>
			     <input type="submit" name="submit">
			   </form> 
			</div>
			<%} %>
			<div class="clearfix"> </div>
		  </div>
		   <h4 class="tag_head">Keywords</h4>
	         <ul class="tags_links">
	            <% if(!film.getDirector().isEmpty()&&!film.getDirector().equals("")&&(film.getDirector().indexOf("/")>=0)){ 
	               String[] aa=film.getDirector().split("/");
	               for(int i=0;i<aa.length-1;i++){  %>
	            	   <li><a href="DosearchServlet?field=director&search_text=<%=aa[i]%>&searchtype=some"><%=aa[i]%></a></li>
	            <% }}else{%>
	                    <li><a href="DosearchServlet?field=director&search_text=<%=film.getDirector()%>&searchtype=some"><%=film.getDirector()%></a></li>
	             <%} %>
	             
	             <% if(!film.getWriter().isEmpty()&&!film.getWriter().equals("")&&(film.getWriter().indexOf("/")>=0)){ 
	               String[] aa=film.getWriter().split("/");
	               for(int i=0;i<aa.length;i++){  %>
	            	   <li><a href="DosearchServlet?field=writer&search_text=<%=aa[i]%>&searchtype=some"><%=aa[i]%></a></li>
	            <% }}else{%>
	                    <li><a href="DosearchServlet?field=writer&search_text=<%=film.getWriter()%>&searchtype=some"><%=film.getWriter()%></a></li>
	             <%} %>
	             
	             <% if(!film.getMainactors().isEmpty()&&!film.getMainactors().equals("")){ 
	               String[] aa=film.getMainactors().split("/");
	               for(int i=0;i<aa.length;i++){  %>
	            	   <li><a href="DosearchServlet?field=mainactors&search_text=<%=aa[i]%>&searchtype=some"><%=aa[i]%></a></li>
	            <% }}%>  
	            
	             <% if(!film.getLanguage().isEmpty()&&!film.getLanguage().equals("")){ 
	               String[] aa=film.getLanguage().split("/");
	               for(int i=0;i<aa.length;i++){  %>
	            	   <li><a href="DosearchServlet?field=language&search_text=<%=aa[i]%>&searchtype=some"><%=aa[i]%></a></li>
	            <% }}%>  
	            
	            <% if(!film.getType().isEmpty()&&!film.getType().equals("")){ 
	               String[] aa=film.getType().split("/");
	               for(int i=0;i<aa.length;i++){  %>
	            	   <li><a href="DosearchServlet?field=type&search_text=<%=aa[i]%>&searchtype=some"><%=aa[i]%></a></li>
	            <% }}%>  
	            
	            <% if(!film.getCountry().isEmpty()&&!film.getCountry().equals("")){ 
	               String[] aa=film.getCountry().split("/");
	               for(int i=0;i<aa.length;i++){  %>
	            	   <li><a href="DosearchServlet?field=country&search_text=<%=aa[i]%>&searchtype=some"><%=aa[i]%></a></li>
	            <% }}%>  
			 </ul>
	    </div>
	</div>
	<div class="grid_2">
		<div class="container"> 
			<div class="col-md-3 col_2">
				<h3>Film<br>Categories</h3>
			</div>
			<div class="col-md-9 col_5">
				<div class="col_1_of_5 span_1_of_5">
					<ul class="list1">
					    <li><a href="DosearchServlet?field=type&search_text=剧情&searchtype=some">Dramatic</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=爱情&searchtype=some">Romance</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=科幻&searchtype=some">Science fiction</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=动作&searchtype=some">Action</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=恐怖&searchtype=some">Horror</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=历史&searchtype=some">History</a></li>
		            </ul>
				</div>
				<div class="col_1_of_5 span_1_of_5">
					<ul class="list1">
					    <li><a href="DosearchServlet?field=type&search_text=喜剧&searchtype=some">Comedy</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=悬疑&searchtype=some">Suspense</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=记录&searchtype=some">Documentary</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=动画&searchtype=some">Animation</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=奇幻&searchtype=some">Fantasy</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=家庭&searchtype=some">Family</a></li>
		            </ul>
				</div>
				<div class="col_1_of_5 span_1_of_5">
					<ul class="list1">
					    <li><a href="DosearchServlet?field=country&search_text=美国&searchtype=some">America</a></li>
			            <li><a href="DosearchServlet?field=country&search_text=中国&searchtype=some">China</a></li>
			            <li><a href="DosearchServlet?field=country&search_text=法国&searchtype=some">France</a></li>
			            <li><a href="DosearchServlet?field=country&search_text=意大利&searchtype=some">Italy</a></li>
			            <li><a href="DosearchServlet?field=country&search_text=日本&searchtype=some">Japan</a></li>
			            <li><a href="DosearchServlet?field=country&search_text=韩国&searchtype=some">Korea</a></li>
		            </ul>
				</div>
				<div class="col_1_of_5 span_1_of_5">
					<ul class="list1">
					    <li><a href="DosearchServlet?field=language&search_text=英语&searchtype=some">English</a></li>
			            <li><a href="DosearchServlet?field=language&search_text=汉语&searchtype=some">Chinese</a></li>
			            <li><a href="DosearchServlet?field=language&search_text=法语&searchtype=some">French</a></li>
			            <li><a href="DosearchServlet?field=language&search_text=德语&searchtype=some">Germany</a></li>
			            <li><a href="DosearchServlet?field=language&search_text=日语&searchtype=some">Japanese</a></li>
			            <li><a href="DosearchServlet?field=language&search_text=粤语&searchtype=some">Cantonese</a></li>
		            </ul>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>
	<div class="grid_3">
	  <div class="container">
	  	 <ul id="footer-links">
			<li><a href="#">Terms of Use</a></li>
			<li><a href="#">Royalty Free License</a></li>
			<li><a href="#">Extended License</a></li>
			<li><a href="#">Privacy</a></li>
			<li><a href="support.html">Support</a></li>
			<li><a href="about.html">About Us</a></li>
			<li><a href="faq.html">FAQ</a></li>
			<li><a href="#">Categories</a></li>
         </ul>
         <p>Copyright &copy; 2015.Company name All rights reserved.</p>
	  </div>
	</div>
</body>
</html>		