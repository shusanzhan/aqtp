<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/css/list.css" type="text/css" rel="stylesheet">
<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet">
<link  href="${ctx }/widgets/easyvalidator/css/validate.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/widgets/jqueryui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/utile/utile.js"></script>
<script type="text/javascript" src="${ctx }/widgets/easyvalidator/js/jquery.bgiframe.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/easyvalidator/js/easy_validator.pack.js"></script>
<title>个人设置-修改密码</title>
<script type="text/javascript">
var isExtendsValidate = true;	//如果要试用扩展表单验证的话，该属性一定要申明
function extendsValidate(){	//函数名称，固定写法
	//密码匹配验证
	if( $('#password').val() == $('#repassword').val() ){	//匹配成功
		$('#repassword').validate_callback(null,"sucess");	//此次是官方提供的，用来消除以前错误的提示
		return true;
	}else{//匹配失败
		$('#repassword').validate_callback("密码不匹配","failed");	//此处是官方提供的API，效果则是当匹配不成功，pwd2表单 显示红色标注，并且TIP显示为“密码不匹配”
		return false;
	}
}

</script>
</head>
<body class="bodycolor">
	<table class="TableTop" width="100%">
		<tbody>
			<tr>
				<td class="left"></td>
				<td class="center">修改密码</td>
				<td class="right"></td>
			</tr>
		</tbody>
	</table>
	<br>
	<form action="" name="frmId" id="frmId" style="margin-bottom: 40px;" target="_self">
		<input type="hidden" name="dbid" id="dbid" value="${user.dbid }">
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 92%;">
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;">原密码:&nbsp;</td>
				<td ><input type="password" name="oldPassword" id="oldPassword"
					value="" class="input-large field"  title="原密码" checkType="string,3,20" tip="原密码不能为空,字符长度为3,20"><span style="color: red;">*</span></td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;">新密码:&nbsp;</td>
				<td ><input type="password" name="password" id="password"
					value="" class="input-large field" title="新密码"	checkType="string,3,20" tip="新密码不能为空，字符长度为3,20"><span style="color: red;">*</span></td>
			</tr>
			
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;">确认密码:&nbsp;</td>
				<td ><input type="password" name="repassword" id="repassword"
					 class="input-large field" checkType="string,3,20" tip="确认密码不能为空，字符长度为3,20"><span style="color: red;">*</span></td>
			</tr>
		</table>
		<div class="buttons" style="margin-top: 20px;text-align: center;">
			<a href="javascript:void(-1)"	onclick="$.utile.submitForm('frmId','${ctx}/user/updateModifyPassword')" class="ui-state-default" >修改</a> 
		</div>
	</form>
</body>
</html>