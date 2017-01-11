<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="com.Bean.filmBean" %>
<%@ page import="java.util.*" %>
<%@ page import="com.Bean.CommentBean" %>
<!DOCTYPE HTML>
<html>
<head>
<title>film result</title>
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
<link href='http://fonts.useso.com/css?family=Quicksand:300,400,700' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Open+Sans:300,400,600,700,800' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
    <script type="text/javascript">
	    $(document).ready(function () {
	        $('#horizontalTab').easyResponsiveTabs({
	            type: 'default', //Types: default, vertical, accordion           
	            width: 'auto', //auto or any width like 600px
	            fit: true   // 100% fit in a container
	        });
	    });
    </script>	
<script src="js/menu_jquery.js"></script> 
<script language="javascript">
function checkboxtest(){
	var checkbox=document.getElementsByName('checkfield');
	var m=0;
	for(var i=0;i<checkbox.length;i++){
		if(checkbox[i].checked){
			m++;
		}
	}
	if(m<1){
		alert("choose one fields as a start");
		return false;
	}
	else if(m<2){
		alert("choose at least two fields");
		return false;
	}
	else{
	for(var i=0;i<checkbox.length;i++){
		if(checkbox[i].checked){
			var j=i+1;
			if(document.getElementById("search_text"+j).value==""||document.getElementById("search_text"+j).value==null){
				alert("please input text"+j+" value");
				return false;
			}
		}
	}return true;
	}	
}
</script>   
</head>
<body>
<% 
     List<CommentBean> commentlist=new ArrayList<CommentBean>();
     commentlist=(List<CommentBean>)request.getAttribute("commentlist");
     List<filmBean> filmlist=new ArrayList<filmBean>();
     filmlist=(List<filmBean>)request.getAttribute("filmlist");
%>
<body>
	<div class="header">	
      <div class="container"> 
  	     <div class="logo">
			<h1><a href="Index.jsp">FILM Hub</a></h1>
		 </div>
		 <div class="clearfix"></div>
		</div>
	</div>
	<div class="stock_box">
	 <div class="col-md-2 stock_left">
	   <div class="w_sidebar">
       <form action="FiltersearchServlet" method="get" onsubmit="return checkboxtest()">
		<section class="sky-form">
		   <h4>Filter</h4>
			 <div class="col col-4">
				<label class="checkbox"><input type="checkbox" name="checkfield" value="mainactors" ><i></i>Actor</label>
				<input type="text" name="search_text1" id="search_text1" width=50px></br>
			    <label class="checkbox"><input type="checkbox" name="checkfield" value="director" ><i></i>Director</label>
			    <input type="text" name="search_text2" id="search_text2" width=50px></br>
				<label class="checkbox"><input type="checkbox" name="checkfield" value="award" ><i></i>Award</label>
				<input type="text" name="search_text3" id="search_text3" width=50px></br>
				<label class="checkbox"><input type="checkbox" name="checkfield" value="writer" ><i></i>Writer</label>
				<input type="text" name="search_text4" id="search_text4" width=50px></br>
				<label class="checkbox"><input type="checkbox" name="checkfield" value="name" ><i></i>Title</label>
                <input type="text" name="search_text5" id="search_text5" width=50px></br>
                <label class="checkbox"><input type="checkbox" name="checkfield" value="date" ><i></i>Date</label>
                <input type="number" name="search_text6" id="search_text6" min="1950" max="2016" value="2015"/>Year
                <label class="checkbox"><input type="checkbox" name="checkfield" value="time" ><i></i>Film length</label>
                <input type="number" name="search_text7" id="search_text7" min="20" max="160" value="150"/>Minutes
                <label class="checkbox"><input type="checkbox" name="checkfield" value="type" ><i></i>Type</label>
                <input type="text" name="search_text8" id="search_text8" width=50px></br>
                <label class="checkbox"><input type="checkbox" name="checkfield" value="country" ><i></i>Country</label>
                <input type="text" name="search_text9" id="search_text9" width=50px></br>
                <label class="checkbox"><input type="checkbox" name="checkfield" value="language" ><i></i>Language</label>
                <input type="text" name="search_text10" id="search_text10" width=50px></br>
                <input type="submit" name="search_submit" value="Search" height=50px with=200px>
		    </div>
		</section>
        </form>
<form method="get" action="FileUploadServlet" enctype="multipart/form-data">
		<section class="sky-form">
			<h4>Search graph</h4>
			<ul class="w_nav2">
				
Please upload the picture<input type="file" name="upfile" width="1px" >
<input type="submit" value="upload">

            </ul>
        </section>
</form>
	   </div>
      </div>
      <div class="col-md-10 sap_tabs">	
      <div class="clearfix"> </div>
	  <%if(commentlist.size()>0){%>
	  <%for(int i=0;i<commentlist.size();i++){ %>
	      <div>Record <%=i %>:</br>
	           Content:　<%=commentlist.get(i).getComments() %></br>
	           Date: <%=commentlist.get(i).getDate() %></br>
	           <a href="FilterDetailServlet?id=<%=commentlist.get(i).getFilmid()%>">
	           filmid: <%=commentlist.get(i).getFilmid() %></br>
	           <img src="images/photodatabase/graph/<%=filmlist.get(i).getPhotoid() %>" class="img-responsive" onerror=this.src='images/default.jpg' alt="<%=filmlist.get(i).getName() %>"/>
	           </a>
	      </div>
	      <div class="clearfix"> </div>
	  <%} %>
	     
	  <%}else{ %>
	     <h2>The user has no history records in filmHub.</h2>
	  <%} %>
	  
	  
	  
	  
       <div class="clearfix"> </div>
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
					    <li><a href="DosearchServlet?field=type&search_text=剧情&searchtype=some">dramatic</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=爱情&searchtype=some">romance</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=科幻&searchtype=some">science fiction</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=动作&searchtype=some">action</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=恐怖&searchtype=some">horror</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=历史&searchtype=some">history</a></li>
		            </ul>
				</div>
				<div class="col_1_of_5 span_1_of_5">
					<ul class="list1">
					    <li><a href="DosearchServlet?field=type&search_text=喜剧&searchtype=some">comedy</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=悬疑&searchtype=some">suspense</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=记录&searchtype=some">documentary</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=动画&searchtype=some">animation</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=奇幻&searchtype=some">fantasy</a></li>
			            <li><a href="DosearchServlet?field=type&search_text=家庭&searchtype=some">family</a></li>
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