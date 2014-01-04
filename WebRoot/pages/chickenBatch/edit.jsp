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
<title>批次添加</title>
</head>
<body class="bodycolor">
	<table class="TableTop" width="100%">
		<tbody>
			<tr>
				<td class="left"></td>
				<td class="center">添加批次</td>
				<td class="right"></td>
			</tr>
		</tbody>
	</table>
	<br>
	<form action="" name="frmId" id="frmId" style="margin-bottom: 40px;" target="_self">
		<s:token></s:token>
		<input type="hidden" name="chickenBatch.dbid" id="dbid" value="${chickenBatch.dbid }">
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 92%;">
			<tr height="42">
				<td class="formTableTdLeft">名称:&nbsp;</td>
				<td ><input type="text" name="chickenBatch.name" id="name"
					value="${chickenBatch.name }" class="input-xmedium field" title="名称"	checkType="string,1,20" tip="名称不能为空,并且1-20个字符"><span style="color: red;">*</span></td>
				<td class="formTableTdLeft">批次:&nbsp;</td>
				<td ><input type="text" name="chickenBatch.batchNo" id="batchNo"
					value="${chickenBatch.batchNo }" class="input-xmedium field" title="批次"	checkType="string,5,20" tip="批次不能为空,并且5-20个字符"><span style="color: red;">*</span></td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">出生日期:&nbsp;</td>
				<td ><input type="text" name="chickenBatch.birthday" id="birthday"
					value="${chickenBatch.birthday }" class="input-xmedium field" title="出生日期" checkType="string,10,20" tip="出生日期不能为空" 	onFocus="WdatePicker({isShowClear:false,readOnly:true})"><span style="color: red;">*</span></td>
				<td class="formTableTdLeft">数量:&nbsp;</td>
				<td ><input type="text" name="chickenBatch.countNum" id="countNum"
					value="${chickenBatch.countNum }" class="input-xmedium field" title="数量"	checkType="integer" tip="数量不能为空,并且必须大于0"><span style="color: red;">*</span></td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">品级:&nbsp;</td>
				<td >
					<select name="gradeDbid" id="gradeDbid" class="">
					<c:forEach items="${grades }"  var="grade">
						<option value="${grade.dbid }" ${grade.dbid==chickenBatch.grade.dbid?'selected="selected"':'' }>${grade.name }</option>
					</c:forEach>
					</select>
				</td>
				<td class="formTableTdLeft">品系:&nbsp;</td>
				<td >
					<select name="breedDbid" id="breedDbid" class="">
					<c:forEach items="${breeds }"  var="breed">
						<option value="${breed.dbid }" ${breed.dbid==chickenBatch.breed.dbid?'selected="selected"':'' }>${breed.name }</option>
					</c:forEach>
					</select>
				</td>
			</tr>
			
			<tr height="32">
				<td class="formTableTdLeft">入栏日期:&nbsp;</td>
				<td><input type="text" name="chickenBatch.intoBarDate" id="intoBarDate"
					value='<fmt:formatDate value="${chickenBatch.intoBarDate }"  pattern="yyyy-MM-dd" />' class="input-xmedium field"  onFocus="WdatePicker({isShowClear:false,readOnly:true})" title="入栏日期" checkType="string,10,20" tip="入栏日期不能为空"><span style="color: red;">*</span></td>
				<td class="formTableTdLeft">出栏日期:&nbsp;</td>
				<td><input type="text" name="chickenBatch.outBarDate" id="outBarDate"
					value='<fmt:formatDate value="${chickenBatch.outBarDate }"  pattern="yyyy-MM-dd"/>' class="input-xmedium field"  onFocus="WdatePicker({isShowClear:false,readOnly:true})" title="出栏日期" checkType="string,10,20" tip="出栏日期不能为空"><span style="color: red;">*</span></td>
			</tr>
		</table>
		<div class="buttons" style="margin-top: 20px;">
			<a href="javascript:void()"
					onclick="$.utile.submitForm('frmId','${ctx}/chickenBatch/save')"
					class="ui-state-default">保存</a> 
		    <a href="${ctx }/chickenBatch/queryList"	onclick="" class="ui-state-default">返回</a>
		</div>
	</form>
</body>
</html>