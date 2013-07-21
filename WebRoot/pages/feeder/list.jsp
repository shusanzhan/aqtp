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
<title>饲料管理</title>
</head>
<body class="bodycolor">
<table class="TableTop" width="100%">
		<tbody>
			<tr>
				<td class="left"></td>
				<td class="center">饲料管理</td>
				<td class="right"></td>
			</tr>
		</tbody>
</table>
<div id="search" style="width: 100%; height: 32px;margin-top: 2px;margin-bottom: 8px;">
	 <form name="searchPageForm" id="searchPageForm" action="${ctx}/feeder/queryList" method="post">
     <input type="hidden" id="currentPage" name="currentPage" value='${page.currentPageNo}'>
     <input type="hidden" id="paramPageSize" name="pageSize" value='${page.pageSize}'>
	 <table>
		<tr>
			<td>姓名：</td>
			<td><input type="text" id="name" name="name" class="input-small field" value="${param.name}"></input></td>
			<td><input type="submit" value="查询"></input></td>
		</tr>
	 </table>
	</form>
</div>
<c:if test="${empty(page.result)||page.result==null }" var="status">
	<div id="result" class="result"style="padding-left: 12px;">
		无用户信息！请点击“添加”按钮进行添加数据操作
	</div>
</c:if>
<c:if test="${status==false }">
<div id="result">
	<table width="100%" class="TableList" border="0">
	<thead class="TableHeader">
		<tr>
			<th class="span1"><input type='checkbox' id="selectAllCheck" onclick="selectAll(this,'id')" />全选</th>
			<th class="span2">图片</th>
			<th class="span2">名字</th>
			<th class="span3">百分比</th>
			<th class="span4">备注</th>
			<th class="span3" align="left">操作</th>
		</tr>
	</thead>
	<c:forEach var="feeder" items="${page.result }">
		<tr height="32" align="center">
			<td><input type='checkbox' name="id" id="id1" value="${feeder.dbid }"/></td>
			<td align="left" style="width: 80px;">
				<img alt="" src="${feeder.image }" width="80" height="60">
			</td>
			<td>${feeder.name }</td>
			<td align="left">${feeder.elementsPercentage }</td>
			<td align="left">${feeder.note}</td>
			<td><a href="#" class="system_right_table_a"
				onclick="window.location.href='${ctx }/feeder/edit?dbid=${feeder.dbid}'">编辑</a>
				<a href="#" class="system_right_table_a"
				onclick="$.utile.deleteById('${ctx }/feeder/delete?dbids=${feeder.dbid}','searchPageForm')">删除</a>
			</td>
		</tr>
	</c:forEach>
	 <tr height="34"  >
       <td colspan="9" align="right" class="border-top: 0px"><%@ include file="../commons/common_pagination.jsp" %></td>
     </tr>	
	</table>
</div>
</c:if>
<div class="buttons" style="margin-top: 20px;text-align: left;margin-bottom: 20px;">
<a class="ui-state-default" href="javascript:void();" onclick="window.location.href='${ctx}/pages/feeder/edit.jsp'">添加</a>
<a class="ui-state-default" href="javascript:void(-1);" onclick="$.utile.deleteIds('${ctx }/feeder/delete','searchPageForm')">删除</a>
</div>
</body>
</html>