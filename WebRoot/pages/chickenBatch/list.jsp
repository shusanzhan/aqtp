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
	 <form name="searchPageForm" id="searchPageForm" action="${ctx}/chickenBatch/queryList" method="post">
     <input type="hidden" id="currentPage" name="currentPage" value='${page.currentPageNo}'>
     <input type="hidden" id="paramPageSize" name="pageSize" value='${page.pageSize}'>
	 <table>
		<tr>
			<td>姓名：</td>
			<td><input type="text" id="name" name="name" value="${param.name}"></input></td>
			<td><input type="submit" value="查询"></input></td>
		</tr>
	 </table>
	</form>
</div>
<div id="result">
	<table width="100%" class="TableList" border="0">
	<thead class="TableHeader">
		<tr>
			<th class="span1" style="width: 30px;"><input type='checkbox' id="selectAllCheck" onclick="selectAll(this,'id')" />全选</th>
			<th class="span2">名称</th>
			<th class="span2">批次</th>
			<th class="span2">品级</th>
			<th class="span2">品系</th>
			<th class="span1">数量</th>
			<th class="span2">入栏日期</th>
			<th class="span2">出栏日期</th>
			<th class="span2">出生日期</th>
			<th class="span2" align="left">操作</th>
		</tr>
	</thead>
	<c:forEach var="chickenBatch" items="${page.result }">
		<tr height="32" align="center">
			<td><input type='checkbox' name="id" id="id1" value="${chickenBatch.dbid }"/></td>
			<td>${chickenBatch.name }</td>
			<td>${chickenBatch.batchNo }</td>
			<td>${chickenBatch.grade.name }</td>
			<td>${chickenBatch.breed.name }</td>
			<td>${chickenBatch.countNum }</td>
			<td>
				<fmt:formatDate value="${chickenBatch.intoBarDate }" pattern="yyyy-MM-dd"/>
			</td>
			<td>
				<fmt:formatDate value="${chickenBatch.outBarDate }" pattern="yyyy-MM-dd"/>
			</td>
			<td>
				<fmt:formatDate value="${chickenBatch.birthday }"/>
			</td>
			<td><a href="#" class="system_right_table_a"
				onclick="window.location.href='${ctx }/chickenBatch/edit?dbid=${chickenBatch.dbid}'">编辑</a>
				<a href="#" class="system_right_table_a"
				onclick="$.utile.operatorDataByDbid('${ctx }/chickenBatch/delete?dbids=${chickenBatch.dbid}','searchPageForm','确定要删除该批次信息吗？删除该批次将删除该批次的所有记录！')">删除</a>
				<a href="#" class="system_right_table_a"
				onclick="window.location.href='${ctx }/chickenBatch/index?dbid=${chickenBatch.dbid}'">批次主页</a>
		</tr>
	</c:forEach>
	 <tr height="34"  >
       <td colspan="10" align="right" class="border-top: 0px"><%@ include file="../commons/common_pagination.jsp" %></td>
     </tr>	
	</table>
</div>
<div class="buttons" style="margin-top: 20px;text-align: left;margin-bottom: 20px;">
<a class="ui-state-default" href="javascript:void(-1);" onclick="window.location.href='${ctx}/chickenBatch/add'">添加</a>
<a class="ui-state-default" href="javascript:void(-1);" onclick="$.utile.operatorDataByDbids('${ctx }/chickenBatch/delete','searchPageForm','确定要选择的删除该批次信息吗？删除该批次将删除该批次的所有记录！')">删除</a>
</div>
</body>
</html>