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
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 90%;">
		<thead class="TableHeader">
		<tr>
			<th class="span1">序号</th>
			<th class="span2">名称</th>
			<th class="span2">排序</th>
		</tr>
	</thead>
		<c:forEach var="dishType" items="${dishTypes }" varStatus="len">
			<tr height="42">
				<td class="formTableTdLeft" style="width: 40px;">${len.index+1 }&nbsp;</td>
				<td width="200" >${dishType.name }</td>
				<td width="100">
				<input type="hidden" name="dbid" id="dbid${len.index+1 }"	value="${dishType.dbid }" >
				<input type="text" name="orderNum" id="orderNum${len.index+1 }"
					value="${dishType.orderNum }" class="input-small field" title="${dishType.name } 排序号"	><span style="color: red;">*</span></td>
			</tr>
		</c:forEach>
		</table>
		<div class="buttons" style="margin-top: 20px;">
			<a href="javascript:void(-1)"	onclick="$.utile.submitForm('frmId','${ctx}/dishType/updateOrderNum')" class="ui-state-default">保存</a> 
		</div>
	</form>
</body>
</html>