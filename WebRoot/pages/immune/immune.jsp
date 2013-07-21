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
<script type="text/javascript" src="${ctx }/widgets/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/widgets/utile/utile.js"></script>
<script type="text/javascript" src="${ctx }/widgets/easyvalidator/js/jquery.bgiframe.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/easyvalidator/js/easy_validator.pack.js"></script>
<script type="text/javascript" src="${ctx }/widgets/My97DatePicker/WdatePicker.js"></script>

<title>添加免疫信息</title>
</head>
<body class="bodycolor">
	<br>
	<form action="" name="frmId" id="frmId" style="margin-bottom: 40px;" target="_parent">
		<s:token></s:token>
		<input type="hidden" name="immune.dbid" id="dbid" value="${immune.dbid }">
		<input type="hidden" name="chickenBatchDbid" id=chickenBatchDbid value="${param.chickenBatchDbid }">
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 92%;">
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;">免疫员:&nbsp;</td>
				<td ><input type="text" name="immune.immunePerson" id="immunePerson"
					value="${immune.immunePerson }" class="input-medium field" title="名称"	checkType="string,1,20" tip="检疫名称不能为空"><span style="color: red;">*</span></td>
				<td class="formTableTdLeft" style="width: 60px;">免疫时间:&nbsp;</td>
				<td ><input type="text" name="immune.immuneDate" id="immuneDate"
					value="${immune.immuneDate }" class="input-medium field" title="免疫时间" readonly="readonly"	onFocus="WdatePicker({isShowClear:false,readOnly:true})"></td>
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
			<tr height="32">
				<td class="formTableTdLeft" style="width: 60px;">备注:&nbsp;</td>
				<td colspan="3">
					 <textarea rows="" cols="" id="note" class="textarea-xxlarge" name="immune.note">${immune.note }</textarea>
				</td>
			</tr>
		</table>
		<div class="buttons" style="margin-top: 20px;">
			<a href="javascript:void()" 	onclick="$.utile.submitForm('frmId','${ctx}/immune/save')"
					class="ui-state-default">保存</a> 
		    <a href="${ctx }/chickenBatch/index?dbid=${param.chickenBatchDbid}" target="contentUrl"	class="ui-state-default">关闭</a>
		</div>
	</form>
</body>
</html>