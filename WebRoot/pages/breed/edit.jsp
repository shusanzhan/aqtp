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
		<input type="hidden" name="breed.dbid" id="dbid" value="${breed.dbid }">
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 92%;">
			<tr height="42">
				<td class="formTableTdLeft" style="width: 40px;">名称:&nbsp;</td>
				<td ><input type="text" name="breed.name" id="name"
					value="${breed.name }" class="input-medium field" title="用户名"	checkType="string,1,20" tip="品级名称不能为空,并且1-20个字符"><span style="color: red;">*</span></td>
				<td class="formTableTdLeft" style="width: 40px;">代码:&nbsp;</td>
				<td ><input type="text" name="breed.charCode" id="charCode"
					value="${breed.charCode }" class="input-medium field" title="用户名"	checkType="string,1,20" tip="代码不能为空"><span style="color: red;">*</span></td>
			</tr>
			<tr >
				<td class="formTableTdLeft" style="width: 40px;" height="100">特性:&nbsp;</td>
				<td colspan="3">
					<textarea rows="" cols="" id="characteristic" class="textarea-large" name="breed.characteristic">${breed.characteristic }</textarea>
				</td>
				
			</tr>
			<tr >
				<td class="formTableTdLeft" style="width: 40px;" height="100">备注:&nbsp;</td>
				<td colspan="3">
					<textarea rows="" cols="" id="note" class="textarea-large" name="breed.note">${breed.note }</textarea>
				</td>
				
			</tr>
		
		</table>
		<div class="buttons" style="margin-top: 20px;">
			<a href="javascript:void()"
					onclick="$.utile.submitForm('frmId','${ctx}/breed/save')"
					class="ui-state-default">保存</a> 
		    <a href="${ctx }/breed/queryList"  target="contentUrl" 	onclick="" class="ui-state-default">返回</a>
		</div>
	</form>
</body>
</html>