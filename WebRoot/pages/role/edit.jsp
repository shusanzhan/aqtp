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
<title>用户角色</title>
</head>
<body class="bodycolor">
	<table class="TableTop" width="100%">
		<tbody>
			<tr>
				<td class="left"></td>
				<td class="center">添加角色</td>
				<td class="right"></td>
			</tr>
		</tbody>
	</table>
	<br>
	<form action="" name="frmId" id="frmId" style="margin-bottom: 40px;" target="_self">
		<s:token></s:token>
		<input type="hidden" name="role.dbid" id="dbid" value="${role.dbid }">
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 92%;">
			<tr height="42">
				<td class="formTableTdLeft">名称:&nbsp;</td>
				<td ><input type="text" name="role.name" id="name"
					value="${role.name }" class="input-xlarge field" title="名称"	checkType="string" tip="名称不能为空"><span style="color: red;">*</span></td>
			</tr>
			<tr height="32">
				<td class="formTableTdLeft">状态:&nbsp;</td>
				<td>
					<select class="input-xlarge field" id="state" name="role.state">
						<option value="1" ${role.state==1?'selected="selected"':'' } >启用</option>
						<option value="0" ${role.state==0?'selected="selected"':'' }>停用</option>
					</select>
				</td>
			</tr>
			<tr height="32">
				<td class="formTableTdLeft">备注:&nbsp;</td>
				<td>
					<textarea rows="8" cols="60" name="role.note" id="note">${role.note }</textarea>
				</td>
			</tr>
		</table>
		<div class="buttons" style="margin-top: 20px;">
			<a href="javascript:void()"
					onclick="$.utile.submitForm('frmId','${ctx}/role/save')"
					class="ui-state-default">保存</a> 
		    <a href="${ctx }/role/queryList"	onclick="" class="ui-state-default">返回</a>
		</div>
	</form>
</body>
</html>