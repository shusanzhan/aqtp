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
<script type="text/javascript" src="${ctx}/widgets/charscode.js"></script>
<title>添加药品</title>
</head>
<body class="bodycolor">
	<form action="" name="frmId" id="frmId" style="margin-bottom: 40px;" target="_parent">
		<s:token></s:token>
		<input type="hidden" name="drag.dbid" id="dbid" value="${drag.dbid }">
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 92%;">
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;" style="width: 60px;">名称:&nbsp;</td>
				<td ><input type="text" name="drag.name" id="name"
					value="${drag.name }" class="input-medium field" title="药品名称" onchange="pingyin.value = getCharsCode(this.value);"	checkType="string,1,50" tip="药品名称不能为空,并且1-50个字符">
					<input type="hidden" name="drag.pingyin" id="pingyin" value="${drag.pingyin }">
					<span style="color: red;">*</span></td>
					
				<td class="formTableTdLeft" style="width: 60px;">药品类型:&nbsp;</td>
				<td >
					<select class="select field" id="dragTypeId" name="dragTypeId" style="width: 120px;">
						<c:forEach var="dragType" items="${dragTypes }">
							<option value="${dragType.dbid }" ${dragType.dbid==drag.dragtype.dbid?'selected="selected"':'' } >${dragType.name }</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;">生产批号:&nbsp;</td>
				<td ><input type="text" name="drag.generateBatch" id="generateBatch"
					value="${drag.generateBatch }" class="input-medium field" title="生产批号"	checkType="string,1,50" tip="生产批号不能为空"><span style="color: red;">*</span></td>
				<td class="formTableTdLeft" style="width: 60px;">药品规格:&nbsp;</td>
				<td ><input type="text" name="drag.specification" id="specification"
					value="${drag.specification }" class="input-medium field" title="药品规格"></td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;" height="100">药品作用:&nbsp;</td>
				<td colspan="3">
					 <textarea rows="" cols="" id="effect" class="textarea-xxlarge" name="drag.effect">${drag.effect }</textarea>	
				</td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;" height="100">药品说明:&nbsp;</td>
				<td colspan="3">
				    <textarea rows="" cols="" id="directions" class="textarea-xxlarge" name="drag.directions">${drag.directions }</textarea>	
				</td>
			</tr>
			
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;" height="100">备注:&nbsp;</td>
				<td colspan="3">
				  <textarea rows="" cols="" id="note" class="textarea-xxlarge" name="drag.note">${drag.note }</textarea>	
				</td>
			</tr>
		</table>
		<div class="buttons" style="margin-top: 20px;">
			<a href="javascript:void()"	onclick="$.utile.submitForm('frmId','${ctx}/drag/save')" class="ui-state-default">保存</a> 
		    <a href="${ctx }/drag/queryList"  target="contentUrl" 	onclick="" class="ui-state-default">返回</a>
		</div>
	</form>
</body>
</html>