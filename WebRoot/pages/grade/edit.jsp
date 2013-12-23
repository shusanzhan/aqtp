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
<title>添加品系</title>
</head>
<body class="bodycolor">
	<form action="" name="frmId" id="frmId" style="margin-bottom: 40px;" target="_parent">
		<s:token></s:token>
		<input type="hidden" name="grade.dbid" id="dbid" value="${grade.dbid }">
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 92%;">
			<tr height="42">
				<td class="formTableTdLeft" style="width: 40px;">名称:&nbsp;</td>
				<td ><input type="text" name="grade.name" id="name"
					value="${grade.name }" class="input-small field" title="名称"	checkType="string,1,20" tip="级别名称为必填项,并且1-20个字符"><span style="color: red;">*</span></td>
				<td class="formTableTdLeft" style="width: 60px;">零售价格:&nbsp;</td>
				<td ><input type="text" name="grade.retailPrice" id="retailPrice"
					value="${grade.retailPrice }" class="input-small field" title="用户名"	checkType="float,1.0,2000.0" tip="零售价格不能为空"><span style="color: red;">*</span></td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft" style="width: 40px;">级别:&nbsp;</td>
				<td colspan="3">
					<input type="text" name="grade.level" id="level" value="${grade.level }" class="input-large field" title="级别">
				</td>
			</tr>
			<tr >
				<td class="formTableTdLeft" style="width: 40px;" height="100">备注:&nbsp;</td>
				<td colspan="3">
					<textarea rows="" cols="" id="note" class="textarea-large" name="grade.note">${grade.note }</textarea>
				</td>
				
			</tr>

		</table>
		<div class="buttons" style="margin-top: 20px;">
			<a href="javascript:void(-1)"	onclick="$.utile.submitForm('frmId','${ctx}/grade/save')" class="ui-state-default">保存</a> 
		    <a href="${ctx }/grade/queryList" target="contentUrl" 	onclick="" class="ui-state-default">返回</a>
		</div>
	</form>
</body>
</html>