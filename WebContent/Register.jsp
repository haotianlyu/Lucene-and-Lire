<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Register</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Photo-Hub Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Custom Theme files -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link href="css/style-extra.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js" charset="GBK">
</script>
<script type="text/javascript" src="js/function.js" charset="GBK">
</script>
<!-- Custom Theme files -->
<!--webfont-->
<link href='http://fonts.useso.com/css?family=Quicksand:300,400,700' rel='stylesheet' type='text/css'>
<link href='http://fonts.useso.com/css?family=Open+Sans:300,400,600,700,800' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
<script src="js/menu_jquery.js"></script> 
</head>
<script >
function validate(){
	var user=document.lform.username.value;
	var psw1=document.lform.password.value;
	var psw2=document.lform.confirmpassword.value;
	if(user==""){
		alert("Please input username");
		document.lform.username.focus();//光标定位在文本框acccountName中
		return false;
	}
	if(psw2 != psw1){
		alert("password and confirm password are not same");
		document.lform.password.focus();//光标定位在文本框acccountName中
		return false;
	}
	return true;
}
</script>
<body>
	<div class="header">	
      <div class="container"> 
  	     <div class="logo">
			<h1><a href="Index.jsp">Film Hub</a></h1>
		 </div>
		 <div class="clearfix"></div>
		</div>
	</div>
	<div class="register">
		<div class="container">
		   <form action="RegisterServlet" method="post" name="lform" onSubmit="return validate()"> 
				 <div class="register-top-grid">
					<h1>PERSONAL INFORMATION</h1>
					 <div>
						<span>Username<label>*</label></span>
						<input type="text" id="username" name="username"> 
					 </div>
					 <!--正常提示 Start-->              
                     <!-- class属性用于连接css  -->                
                     <div id="div_uname_rule" class="name-pop">
                     Input a name<br>                                      
                     </div>
                     <!--正常提示 End--> 
                     <!--错误提示 Start-->
                     <div id="div_uname_err_info" class="name-pop">
                     </div>             
                     <!--错误提示 End-->
					 <div class="clearfix"> </div>
					 <a class="news-letter" href="#">
						
					   </a>
					 </div>
				     <div class="register-bottom-grid">
						    <h4>LOGIN INFORMATION</h4>
							 <div>
								<span>Password<label>*</label></span>
								<input type="password" name="password">
							 </div>
							 <div>
								<span>Confirm Password<label>*</label></span>
								<input type="password" name="confirmpassword">
							 </div>
							 <div class="clearfix"> </div>
					 </div>
					 <div class="clearfix"> </div>
					 <div class="register-but">
					      <input type="submit" value="submit">
					      <div class="clearfix"> </div>
					 </div>
				</form>
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
			<li><a href="#">Support</a></li>
			<li><a href="#">About Us</a></li>
			<li><a href="#">FAQ</a></li>
			<li><a href="#">Categories</a></li>
         </ul>
         <p>Copyright &copy; HaotianLyu All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p>
	  </div>
	</div>
</body>
</html>		