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
<title>部门信息编辑页面</title>
</head>
<body class="bodycolor">
	<br>
	<form action="" name="frmId" id="frmId" style="margin-bottom: 40px;" target="_parent">
		<c:if test="${not empty(department) }">
			<input type="hidden" name="parentId" value="${department.parent.dbid }" id="parentId"></input>
		</c:if>
		<c:if test="${empty(department) }">
			<input type="hidden" name="parentId" value="${param.parentId }" id="parentId"></input>
		</c:if>
		<input type="hidden" name="department.dbid" id="dbid" value="${department.dbid }">
		<s:token></s:token>
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 92%;">
			<tr height="42">
				<td class="formTableTdLeft">名称:&nbsp;</td>
				<td ><input type="text" name="department.name" id="name"
					value="${department.name }" class="input-large field" title="部门名称"	checkType="string,1,20" tip="部门名称不能为空"><span style="color: red;">*</span></td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">电话:&nbsp;</td>
				<td ><input type="text" name="department.phone" id="phone"
					value="${department.phone }" class="input-large field" title="电话"	checkType="phoneNo" canEmpty="Y" tip="电话格式不对"></td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">传真:&nbsp;</td>
				<td ><input type="text" name="department.fax" id="fax"
					value="${department.fax }" class="input-large field" title="传真"	checkType="phoneNo" canEmpty="Y" tip="传真格式不对"></td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">部门主管:&nbsp;</td>
				<td ><input type="text" name="managerName" id="managerName"
					value="${department.manager.realName }" class="input-large field" title="部门主管" readonly="readonly">
					<input type="hidden" name="managerId" id="managerId"
					value="${department.manager.dbid }" class="input-large field" >
					 <a href="javascript:void(-1)" onclick="getSelectedUser('managerId','managerName');">选择部门主管</a>
					</td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">序号:&nbsp;</td>
				<td ><input type="text" name="department.suqNo" id="suqNo"
					value="${department.suqNo }" class="input-small field" checkType="integer" canEmpty="Y" tip="必须输入数字" title="序号">3位数字，用于同一级次部门排序，不能重复</td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">部门职能:&nbsp;</td>
				<td ><textarea  name="department.discription" id="discription"
					 class="input-xlarge field" title="用户名">${department.discription }</textarea></td>
			</tr>
		</table>
		<div class="buttons" style="margin-top: 20px;">
			<a href="javascript:void(-1)"	onclick="$.utile.submitForm('frmId','${ctx}/department/save')"	class="ui-state-default">保存</a> 
		</div>
	</form>
</body>
</html>