<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/css/list.css" type="text/css" rel="stylesheet">
<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="${ctx }/widgets/jqueryui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/utile/utile.js"></script>
<script type="text/javascript" src="${ctx }/widgets/artDialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx }/widgets/artDialog/plugins/iframeTools.source.js"></script>
<title>新闻管理</title>
</head>
<body class="bodycolor">
<div id="search" style="width: 100%; height: 32px;margin-top: 2px;margin-bottom: 8px;">
 <form name="searchPageForm" id="searchPageForm" action="${ctx}/news/queryNewType" method="post">
     <input type="hidden" id="currentPage" name="currentPage" value='${page.currentPageNo}'>
     <input type="hidden" id="paramPageSize" name="pageSize" value='${page.pageSize}'>
	</form>
</div>
<div id="result">
	<table width="100%" class="TableList" border="0">
	<thead class="TableHeader">
		<tr>
			<th class="span2" width="40" style="width: 40px;">序号</th>
			<th class="span2" style="width: 400px;">标题</th>
			<th class="span5" align="left" width="120" style="width: 120px;">操作</th>
		</tr>
	</thead>
	<c:forEach var="newType" items="${page.result }" varStatus="i">
		<tr height="32" align="center">
			<td>${i.index+1 }</td>
			<td align="left">${newType.name }</td>
			<td><a href="javascript:void(-1)" class="system_right_table_a"
				onclick="$.utile.openDialog('${ctx}/news/editNewType?dbid=${newType.dbid}','编辑新闻类型')">编辑</a>
				<a href="javascript:void(-1)" class="system_right_table_a"
				onclick="$.utile.deleteById('${ctx }/news/deleteNewType?dbid=${newType.dbid}','searchPageForm')">删除</a>
		</tr>
	</c:forEach>
	 <tr height="34"  >
       <td colspan="4" align="right" class="border-top: 0px"><%@ include file="../../commons/common_pagination.jsp" %></td>
     </tr>	
	</table>
</div>
<div class="buttons" style="margin-top: 20px;text-align: left;margin-bottom: 20px;">
<a class="ui-state-default" href="javascript:void(-1);" onclick="$.utile.openDialog('${ctx}/news/addNewType','添加新闻类型')">添加</a>
</div>
</body>
</html>