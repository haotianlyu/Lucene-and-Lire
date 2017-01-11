function chkUsername() {
 var username = $.trim($("#username").val());
 if(username=="") {
  return 0;
 }
  return 1;
} 
$(document).ready(function(){
	  /** ----------- 用户名输入框事件 ----------- */
	  //当文本框成为焦点时
	  $("#username").bind("focus", function(){
	  var ret=chkUsername();
	  if(ret==0){
	  //用户名输入框为空,显示规则
	   $("#div_uname_err_info").hide();
	   $("#div_uname_rule").show();  
	  }
	  return false;
	  }); 
	  
	  //当文本框失去焦点时
	  $("#username").bind("blur", function(){
	   var ret=chkUsername();
	   $("#div_uname_rule").hide();
	   $("#div_uname_err_info").show();
	   if (ret>0){
	    var url="testnameServlet?username="+$("#username").val();
	    $.get(url,null,callback);
	   }
	   else if(ret==0){
	   //用户名输入框为空,显示规则
	    $("#div_uname_err_info").html("username can not be empty");  
	   } 
	  
	   return false;
	  }); 
	});
	  //接收服务器返回的数值，将服务器返回的数据动态的显示在页面上
	  function callback(data){
	   var resultObj=$("#div_uname_err_info");
	   resultObj.html(data);
	  }