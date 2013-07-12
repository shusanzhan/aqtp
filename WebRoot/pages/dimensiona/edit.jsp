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
<title>添加药品</title>
</head>
<body class="bodycolor">
	<form action="" name="frmId" id="frmId" style="margin-bottom: 40px;" target="_parent">
		<s:token></s:token>
		<input type="hidden" name="dimensiona.dbid" id="dbid" value="${dimensiona.dbid }">
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 92%;">
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;" style="width: 60px;">名称:&nbsp;</td>
				<td ><input type="text" name="dimensiona.name" id="name"
					value="${dimensiona.name }" class="input-medium field" title="药品名称"	checkType="string,1,50" tip="药品名称不能为空,并且1-50个字符"><span style="color: red;">*</span></td>
				<td class="formTableTdLeft" style="width: 60px;">鸡批次:&nbsp;</td>
				<td >
					<select class="select field" id="chickenBatchId" name="chickenBatchId" style="width: 120px;">
						<c:forEach var="chickenBatch" items="${chickenBatchs }">
							<option value="${chickenBatch.dbid }" ${chickenBatch.dbid==dimensiona.chickenbatch.dbid?'selected="selected"':'' } >${chickenBatch.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<c:if test="${dimensiona.dbid>0 }" var="status">
			<tr height="42">
					<td class="formTableTdLeft" style="width: 60px;">数量:&nbsp;</td>
					<td colspan="3"><input type="text" name="dimensiona.quantity" id="quantity"
						value="${dimensiona.quantity }"  readonly="readonly" class="input-medium field" title="二维码数量"	checkType="integer,1,1000" tip="二维码数量必须大于1,小于1000" style=""><span style="color: red;">不能对数量进行编辑</span></td>
				</tr>
			</c:if>
			<c:if test="${status==false }">
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;">数量:&nbsp;</td>
				<td colspan="3"><input type="text" name="dimensiona.quantity" id="quantity"
					value="${dimensiona.quantity }" class="input-medium field" title="二维码数量"	checkType="integer,1,1000" tip="二维码数量必须大于1,小于1000"><span style="color: red;">*</span></td>
			</tr>
			</c:if>
		</table>
		<div class="buttons" style="margin-top: 20px;">
			<a href="javascript:void()"	onclick="$.utile.submitForm('frmId','${ctx}/dimensiona/save')" class="ui-state-default">保存</a> 
		    <a href="${ctx }/dimensiona/queryList"  target="contentUrl" 	onclick="" class="ui-state-default">返回</a>
		</div>
	</form>
</body>
</html>