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
	<br>
	<form action="" name="frmId" id="frmId" style="margin-bottom: 40px;" target="_parent">
		<c:if test="${not empty(resource) }">
			<input type="hidden" name="parentId" value="${resource.parent.dbid }" id="parentId"></input>
		</c:if>
		<c:if test="${empty(resource) }">
			<input type="hidden" name="parentId" value="${param.parentId }" id="parentId"></input>
		</c:if>
		<input type="hidden" name="resource.dbid" id="dbid" value="${resource.dbid }">
		<s:token></s:token>
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 92%;">
			<tr height="42">
				<td class="formTableTdLeft">连接类型:&nbsp;</td>
				<c:if test="${not empty(param.menu) }">
				<td>
					<select id="menu" name="resource.menu"  class="input-xlarge field" checkType="string" tip="请选择连接类型">
						<option value="">请选择连接类型</option>
						<option value="0" ${param.menu==0?'selected="selected"':'' } >菜单</option>
						<option value="1" ${param.menu==1?'selected="selected"':'' }>列表</option>
						<option value="2" ${param.menu==2?'selected="selected"':'' }>功能</option>
					</select></td>
				</c:if>
				<c:if test="${empty(param.menu) }">
				<td>
					<select id="menu" name="resource.menu"  class="input-xlarge field" checkType="string" tip="请选择连接类型">
						<option value="">请选择连接类型</option>
						<option value="0" ${resource.menu==0?'selected="selected"':'' } >菜单</option>
						<option value="1" ${resource.menu==1?'selected="selected"':'' }>列表</option>
						<option value="2" ${resource.menu==2?'selected="selected"':'' }>功能</option>
					</select></td>
				</c:if>
				
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">标题:&nbsp;</td>
				<td ><input type="text" name="resource.title" id="title"
					value="${resource.title }" class="input-xlarge field" title="用户名"	checkType="string" tip="标题不能为空"><span style="color: red;">*</span></td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">url连接:&nbsp;</td>
				<td ><input type="text" name="resource.content" id="content"
					value="${resource.content }" class="input-xlarge field" title="用户名"></td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">序号:&nbsp;</td>
				<td ><input type="text" name="resource.orderNo" id="orderNo"
					value="${resource.orderNo }" class="input-xlarge field" checkType="integer" canEmpty="Y" tip="必须输入数字" title="用户名"></td>
			</tr>
		</table>
		<div class="buttons" style="margin-top: 20px;">
			<a href="javascript:void()"
					onclick="$.utile.submitForm('frmId','${ctx}/resource/save')"
					class="ui-state-default">保存</a> 
		</div>
	</form>
</body>
</html>