<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>精战网站后台管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/login/login.css" />
</head>
 
<script type="text/javascript"> 
window.onload = function() {
	var status="<%=request.getParameter("error")%>";
	if(status=="true"){
		alert("用户名或密码错误！");
	}
}
function CheckForm()
{
	var name=document.getElementById("name").value;
	var password=document.getElementById("password").value;
	if(name==null||name.length<=0){
		alert("请输入用户名！");
		document.getElementById("name").focus();
		return false;
	}
	if(password==null||password.length<=0){
		alert("请输入密码！");
		document.getElementById("password").focus();
		return false;
	}
   return true;
}
 
</script>
<body>
 
<form id="form1" name="form1" method="post" action="/j_spring_security_check" >
<div id="logo">
   <div id="form">
      <div class="left">
         <div class="user">
        	<input type="text" id="name" class="text" name=j_username maxlength="20" value=""></div>
         <div class="pwd">
         <input type="password" id="password" class="text" name="j_password"  value=""></div>
      </div>
      <div class="right">
         <input type="button" class="submit" title="登录" onclick="if(CheckForm()){form1.submit();}" />
      </div>
   </div>
</div>
<br />
</form>
<%-- Error Messages --%>
<logic:messagesPresent>
		<script type="text/javascript">
			var error="";
			<html:messages id="error">	error+='${error}\n';</html:messages>
				if(error.length>0&&error!=""){
					alert(error);
				}    
		
		</script>
</logic:messagesPresent>

<%-- Success Messages --%>
<logic:messagesPresent message="true">
		<script type="text/javascript">
		var message="";
		<html:messages id="message" message="true">message+='${message}\n';	</html:messages>
		if(message.length>0&&message!=""){
			alert(message);
		} 
		</script>
</logic:messagesPresent>
</body>
</html>
