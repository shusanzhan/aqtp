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
<link href="${ctx }/widgets/ckeditor/sample.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${ctx }/widgets/jqueryui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/utile/utile.js"></script>
<script type="text/javascript" src="${ctx }/widgets/easyvalidator/js/jquery.bgiframe.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/easyvalidator/js/easy_validator.pack.js"></script>
<script type="text/javascript" src="${ctx }/widgets/ckeditor/ckeditor.js"></script>
<script src="${ctx }/widgets/ckeditor/sample.js" type="text/javascript"></script>
<title>添加新闻</title>
</head>
<body class="bodycolor">
	
	<br>
	<form action="" name="frmId" id="frmId" style="margin-bottom: 40px;" target="_self">
		<s:token></s:token>
		<input type="hidden" name="news.dbid" id="dbid" value="${news.dbid }">
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 92%;">
			<tr height="42">
				<td class="formTableTdLeft">类型:&nbsp;</td>
				<td >
					<select id="newTypeDbid" name="newTypeDbid" class="select input-large" >
					<c:forEach var="newType" items="${newTypes }">
					    <option value="${newType.dbid }" ${news.newType.dbid==newType.dbid?'selected="selected"':'' } >${newType.name }</option>
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">标题:&nbsp;</td>
				<td ><input type="text" name="news.title" id="title"
					value="${news.title }" class="input-large field" title="标题"  checkType="string,1,50" tip="标题不能为空"><span style="color: red;">*</span></td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">接受人员:&nbsp;</td>
				<td ><textarea rows="" class="textarea-xxlarge field"  cols="" id="receviePersonNames" name="news.receviePersonNames">${news.receviePersonNames }</textarea> <a href="javascript:void(-1)" onclick="getSelectedUser('receviePersonIds','receviePersonNames');">选择人员</a>
					<input type="hidden" name="news.receviePersonIds" id="receviePersonIds" value="${news.receviePersonIds }">
					如果为选择接受人，默认发给系统中的所有人！
				</td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">是否评论:&nbsp;</td>
				<td >
				<select id="commentStatus" name="news.commentStatus"  class="select input-large">
					<option value="1" ${news.commentStatus==1?'selected="selected"':'' } > 可以评论</option>
					<option value="0" ${news.commentStatus==0?'selected="selected"':'' }> 不可评论</option>
				</select>
				</td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">提醒:&nbsp;</td>
				<td ><input type="checkbox" id="messageWarm" value="" name="messageWarm"><label for="messageWarm">发送事务提醒消息</label>  </td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">置顶:&nbsp;</td>
				<td ><input type="checkbox" id="topStatus" value="1" name="news.topStatus" ${news.topStatus==1?'checked="checked"':'' } ><label for="topStatus">使新闻置顶，显示为重要</label></td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">内容简介:&nbsp;</td>
				<td ><textarea name="news.introduction" id="introduction"
					class="textarea-xxlarge field" title="内容简介"  checkType="string" tip="标题不能为空">${news.introduction }</textarea><span style="color: red;">*</span></td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">附件:&nbsp;</td>
				<td>
					<input type="hidden" id="attach" name="news.attach">
				</td>
			</tr>
			
			<tr height="32">
				<td colspan="2">
						<textarea cols="59" rows="10" id="content" name="news.content">${news.content }</textarea>
				</td>
			</tr>
		</table>
		<div class="buttons" style="margin-top: 20px;">
			<a href="javascript:void(-1)"	onclick="$.utile.submitForm('frmId','${ctx}/news/save',true)" class="ui-state-default">保存</a> 
		    <a href="${ctx }/news/queryList"	onclick="" class="ui-state-default">返回</a>
		</div>
	</form>
</body>
<script type="text/javascript">
	var editor=CKEDITOR.replace("content");
</script>
</html>