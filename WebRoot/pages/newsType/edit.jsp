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
<title>添加菜单分类</title>
</head>
<body class="bodycolor">
	<br>
	<form action="" name="frmId" id="frmId" style="margin-bottom: 40px;" target="_parent">
		<c:if test="${not empty(newsType) }">
			<input type="hidden" name="parentId" value="${newsType.parent.dbid }" id="parentId"></input>
		</c:if>
		<c:if test="${empty(newsType) }">
			<input type="hidden" name="parentId" value="${param.parentId }" id="parentId"></input>
		</c:if>
		<input type="hidden" name="newsType.dbid" id="dbid" value="${newsType.dbid }">
		<s:token></s:token>
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 90%;">
			<tr height="42">
				<td class="formTableTdLeft">标题:&nbsp;</td>
				<td width="300"><input type="text" name="newsType.name" id="title"
					value="${newsType.name }" class="input-large field" title="用户名"	checkType="string,1,20" tip="标题不能为空"><span style="color: red;">*</span></td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">备注:&nbsp;</td>
				<td >
					<textarea rows="" cols="" class="input-large field" name="newsType.note">${newsType.note }</textarea>
				</td>
			</tr>
		</table>
		<div class="buttons" style="margin-top: 20px;">
			<a href="javascript:void(-1)"
					onclick="$.utile.submitForm('frmId','${ctx}/newsType/save')"
					class="ui-state-default">保存</a> 
		</div>
	</form>
</body>
</html>