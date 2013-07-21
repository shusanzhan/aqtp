<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/css/list.css" type="text/css" rel="stylesheet">
<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet">

<title>查看保健信息</title>
</head>
<body class="bodycolor">
	<br>
	<form action="" name="frmId" id="frmId" style="margin-bottom: 40px;" target="_parent">
		<s:token></s:token>
		<input type="hidden" name="healthCare.dbid" id="dbid" value="${healthCare.dbid }">
		<input type="hidden" name="chickenBatchDbid" id=chickenBatchDbid value="${param.chickenBatchDbid }">
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 92%;">
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;">名称:&nbsp;</td>
				<td colspan="3"><input type="text" name="healthCare.name" id="name"
					value="${healthCare.name }" class="input-medium field" title="名称" readonly="readonly"	></td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;">开始时间:&nbsp;</td>
				<td ><input type="text" name="healthCare.beginDate" id="beginDate"
					value="${healthCare.beginDate }" class="input-medium field" title="保健开始时间" readonly="readonly"	></td>
				<td class="formTableTdLeft" style="width: 60px;">结束时间:&nbsp;</td>
				<td ><input type="text" name="healthCare.endDate" id="endDate"
					value="${healthCare.endDate }" class="input-medium field" title="保健开始时间" readonly="readonly"	></td>
			</tr>
			<tr height="32">
				<td class="formTableTdLeft" style="width: 60px;">药品:&nbsp;</td>
				<td colspan="3">
					<table  border="0" cellpadding="0" cellspacing="0" style="width: 300px;" height="200">
					<tr>
						<td height="200" width="260" style="">
						</td>
						<td height="40" width="40">
						<div id="div1">
							<div style="padding-left: 5px;">
							<span id="spanButtonPlaceholder1"></span> <br />
						</div>
						<div id="uploadFileContent" class="uploadFileContent"></div>
						</div>
					</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<div class="buttons" style="margin-top: 20px;">
		    <a href="${ctx }/chickenBatch/index?dbid=${param.chickenBatchDbid}" target="contentUrl"	class="ui-state-default">关闭</a>
		</div>
	</form>
</body>
</html>