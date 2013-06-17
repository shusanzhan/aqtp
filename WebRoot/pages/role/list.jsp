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
<title>用户管理</title>
</head>
<body class="bodycolor">
<div id="search" style="width: 100%; height: 32px;margin-top: 2px;margin-bottom: 8px;">
	 <form name="searchPageForm" id="searchPageForm" action="${ctx}/role/queryList" method="get">
     <input type="hidden" id="currentPage" name="currentPage" value='${page.currentPageNo}'>
     <input type="hidden" id="paramPageSize" name="pageSize" value='${page.pageSize}'>
	</form>
</div>
<div id="result">
	<table width="100%" class="TableList" border="0">
	<thead class="TableHeader">
		<tr>
			<th class="span1"><input type='checkbox' id="selectAllCheck" onclick="selectAll(this,'id')" />全选</th>
			<th class="span2">角色名称</th>
			<th class="span2">状态</th>
			<th class="span2">备注</th>
			<th class="span5" align="left">操作</th>
		</tr>
	</thead>
	<c:forEach var="role" items="${page.result }">
		<tr height="32" align="center">
			<td><input type='checkbox' name="id" id="id1" value="${role.dbid }"/></td>
			<td>${role.name }</td>
			<td>
				<c:if test="${role.state==1 }"><span style="color: blue;">启用</span></c:if>
				<c:if test="${role.state==0 }"><span style="color: red;">未启用</span></c:if>
			</td>
			<td>${role.note} </td>
			<td><a href="#" class="system_right_table_a"
				onclick="window.location.href='${ctx }/role/edit?dbid=${role.dbid}'">编辑</a>
				<a href="#" class="system_right_table_a"
				onclick="$.utile.deleteById('${ctx }/role/delete?dbid=${role.dbid}')">删除</a>
				<a href="#" class="system_right_table_a"
				onclick="window.location.href='${ctx }/role/roleResource?dbid=${role.dbid}'">分配权限</a>
		</tr>
	</c:forEach>
	 <tr height="34"  >
       <td colspan="7" align="right" class="border-top: 0px"><%@ include file="../commons/common_pagination.jsp" %></td>
     </tr>	
	</table>
</div>
<div class="buttons" style="margin-top: 20px;text-align: left;">
<a class="ui-state-default" href="javascript:void();" onclick="window.location.href='${ctx}/pages/role/edit.jsp'">添加</a>
</div>
</body>
</html>