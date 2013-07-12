<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/css/list.css" type="text/css" rel="stylesheet">
<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/widgets/jqueryui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/utile/utile.js"></script>
<script type="text/javascript" src="${ctx }/widgets/easyvalidator/js/easy_validator.pack.js"></script>
<script type="text/javascript" src="${ctx }/widgets/artDialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx }/widgets/artDialog/plugins/iframeTools.source.js"></script>
<title>鸡批次管理</title>
</head>
<body class="bodycolor">
<table class="TableTop" width="100%">
		<tbody>
			<tr>
				<td class="left"></td>
				<td class="center">批次管理</td>
				<td class="right"></td>
			</tr>
		</tbody>
</table>
<div id="search" style="width: 100%; height: 32px;margin-top: 2px;margin-bottom: 8px;">
	 <form name="searchPageForm" id="searchPageForm" action="${ctx}/user/queryList" method="get">
     <input type="hidden" id="currentPage" name="currentPage" value='${page.currentPageNo}'>
     <input type="hidden" id="paramPageSize" name="pageSize" value='${page.pageSize}'>
	 <table>
		<tr>
			<td>姓名：</td>
			<td><input type="text" id="userName" name="userName" value="${param.userName}"></input></td>
			<td><input type="submit" value="查询"></input></td>
		</tr>
	 </table>
	</form>
</div>
<div id="result">
	<table width="100%" class="TableList" border="0">
	<thead class="TableHeader">
		<tr>
			<th class="span1"><input type='checkbox' id="selectAllCheck" onclick="selectAll(this,'id')" />全选</th>
			<th class="span2">名称</th>
			<th class="span2">批次</th>
			<th class="span2">品级</th>
			<th class="span2">品系</th>
			<th class="span2">数量</th>
			<th class="span2">入栏日期</th>
			<th class="span2">出栏日期</th>
			<th class="span2">出生日期</th>
			<th class="span3" align="left">操作</th>
		</tr>
	</thead>
	<c:forEach var="user" items="${page.result }">
		<tr height="32" align="center">
			<td><input type='checkbox' name="id" id="id1" value="${user.dbid }"/></td>
			<td>${user.userId }</td>
			<td>${user.realName }</td>
			<td>${user.email }</td>
			<td>${user.email }</td>
			<td>${user.email }</td>
			<td>${user.email }</td>
			<td>${user.email }</td>
			<td>${user.email }</td>
			<td><a href="#" class="system_right_table_a"
				onclick="window.location.href='${ctx }/user/edit?dbid=${user.dbid}'">编辑</a>
				<a href="#" class="system_right_table_a"
				onclick="$.utile.deleteById('${ctx }/user/delete?dbid=${user.dbid}')">删除</a>
				<%-- <sec:accesscontrollist hasPermission="" domainObject=""></sec:accesscontrollist>
				<sec:authentication property=""/>
				<sec:authorize></sec:authorize> --%>
				<a href="#" class="system_right_table_a"
				onclick="window.location.href='${ctx }/user/userRole?dbid=${user.dbid}'">授权</a>
		</tr>
	</c:forEach>
	 <tr height="34"  >
       <td colspan="10" align="right" class="border-top: 0px"><%@ include file="../commons/common_pagination.jsp" %></td>
     </tr>	
	</table>
</div>
<div class="buttons" style="margin-top: 20px;text-align: left;">
<a class="ui-state-default" href="javascript:void(-1);" onclick="window.location.href='${ctx}/pages/chickenBatch/edit.jsp'">添加</a>
<a class="ui-state-default" href="javascript:void(-1);" onclick="window.location.href='${ctx}/pages/chickenBatch/index.jsp'">主页</a>
</div>
</body>
</html>