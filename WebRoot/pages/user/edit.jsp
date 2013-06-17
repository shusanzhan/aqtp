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
<title>用户添加</title>
</head>
<body class="bodycolor">
	<table class="TableTop" width="100%">
		<tbody>
			<tr>
				<td class="left"></td>
				<td class="center">添加用户</td>
				<td class="right"></td>
			</tr>
		</tbody>
	</table>
	<br>
	<form action="" name="frmId" id="frmId" style="margin-bottom: 40px;" target="_self">
		<s:token></s:token>
		<input type="hidden" name="user.dbid" id="dbid" value="${user.dbid }">
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 92%;">
			<tr height="42">
				<td class="formTableTdLeft">用户ID:&nbsp;</td>
				<td ><input type="text" name="user.userId" id="userId"
					value="${user.userId }" class="input-xlarge field" title="用户名"	checkType="string,5,20" tip="用户名不能为空,并且5-20个字符"><span style="color: red;">*</span></td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">真实名称:&nbsp;</td>
				<td ><input type="text" name="user.realName" id="realName"
					value="${user.realName }" class="input-xlarge field" title="用户名"	checkType="string" tip="真实名称不能为空"><span style="color: red;">*</span></td>
			</tr>
			<tr height="32">
				<td class="formTableTdLeft">邮 箱:&nbsp;</td>
				<td><input type="text" name="user.email" id="email"
					value="${user.email }" class="input-xlarge field"  checkType="email" tip="请输入正确的email"><span style="color: red;">*</span></td>
			</tr>
		</table>
		<div class="buttons" style="margin-top: 20px;">
			<a href="javascript:void()"
					onclick="$.utile.submitForm('frmId','${ctx}/user/save')"
					class="ui-state-default">保存</a> 
		    <a href="${ctx }/user/queryList"	onclick="" class="ui-state-default">返回</a>
		</div>
	</form>
</body>
</html>