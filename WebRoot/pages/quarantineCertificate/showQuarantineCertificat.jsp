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
<title>查看检疫证明</title>
</head>
<body class="bodycolor">
	<br>
	<form action="" name="frmId" id="frmId" style="margin-bottom: 40px;" target="_parent">
		<input type="hidden" name="quarantineCertificate.dbid" id="dbid" value="${quarantineCertificate.dbid }">
		<input type="hidden" name="chickenBatchDbid" id=chickenBatchDbid value="${param.chickenBatchDbid }">
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 92%;">
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;">名称:&nbsp;</td>
				<td ><input type="text" readonly="readonly" name="quarantineCertificate.title" id="title"
					value="${quarantineCertificate.title }" class="input-medium field" title="名称"	checkType="string,1,20" tip="检疫名称不能为空"></td>
				<td class="formTableTdLeft" style="width: 60px;">检疫时间:&nbsp;</td>
				<td ><input type="text" readonly="readonly" name="quarantineCertificate.awardDate" id="awardDate"
					value="${quarantineCertificate.awardDate }" class="input-medium field" title="名称" readonly="readonly"	></td>
			</tr>
			
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;">检疫机构:&nbsp;</td>
				<td colspan="3"><input type="text" name="quarantineCertificate.awardGroup" id="awardGroup"
					value="${quarantineCertificate.awardGroup }"  readonly="readonly" checkType="string,1,100" tip="检疫机构不能为空" class="input-medium field" ></td>
			</tr>
			<tr height="32">
				<td class="formTableTdLeft" style="width: 60px;">图片:&nbsp;</td>
				<td colspan="3">
					<table  border="0" cellpadding="0" cellspacing="0" style="width: 300px;" height="200">
					<tr>
						<td height="200" width="260" style="">
							<input type="hidden" name="quarantineCertificate.certificateImage" id="fileUpload" readonly="readonly"	value="${quarantineCertificate.certificateImage }">
							<img alt="" id="fileUploadImage" src="${quarantineCertificate.certificateImage }" width="300" height="180" style="margin-left: -12px;">
						</td>
					</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr height="32">
				<td class="formTableTdLeft" style="width: 60px;">备注:&nbsp;</td>
				<td colspan="3">
					 <textarea readonly="readonly" rows="" cols="" id="note" class="textarea-xxlarge" name="quarantineCertificate.note">${quarantineCertificate.note }</textarea>
				</td>
			</tr>
		</table>
		<div class="buttons" style="margin-top: 20px;">
		    <a href="${ctx }/chickenBatch/index?dbid=${param.chickenBatchDbid}" target="contentUrl"	class="ui-state-default">关闭</a>
		</div>
	</form>
</body>
</html>