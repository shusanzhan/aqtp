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
<table class="TableTop" width="100%">
		<tbody>
			<tr>
				<td class="left"></td>
				<td class="center">用户管理</td>
				<td class="right"></td>
			</tr>
		</tbody>
</table>
<div id="search" style="width: 100%; height: 32px;margin-top: 2px;margin-bottom: 8px;">
	 <form name="searchPageForm" id="searchPageForm" action="${ctx}/user/queryList" method="post">
     <input type="hidden" id="currentPage" name="currentPage" value='${page.currentPageNo}'>
     <input type="hidden" id="paramPageSize" name="pageSize" value='${page.pageSize}'>
	 <table>
		<tr>
			<td>姓名：</td>
			<td><input type="text" id="userName" name="userName" class="input-small field" value="${param.userName}"></input></td>
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
			<th class="span2">用户Id</th>
			<th class="span2">名字</th>
			<th class="span2">生日</th>
			<th class="span2">性别</th>
			<th class="span2">邮箱</th>
			<th class="span2">手机</th>
			<th class="span2">座机</th>
			<th class="span3" align="left">操作</th>
		</tr>
	</thead>
	<c:forEach var="user" items="${page.result }">
		<tr height="32" align="center">
			<td><input type='checkbox' name="id" id="id1" value="${user.dbid }"/></td>
			<td>${user.userId }</td>
			<td>${user.realName }</td>
			<td align="left">${user.breeder.birthday }</td>
			<td>${user.breeder.sex }</td>
			<td align="left">${user.email }</td>
			<td align="left">${user.mobilePhone }</td>
			<td align="left">${user.phone }</td>
			<td><a href="#" class="system_right_table_a"
				onclick="window.location.href='${ctx }/user/edit?dbid=${user.dbid}'">编辑</a>
				<a href="#" class="system_right_table_a"
				onclick="$.utile.deleteById('${ctx }/user/delete?dbids=${user.dbid}','searchPageForm')">删除</a>
				<a href="#" class="system_right_table_a"
				onclick="window.location.href='${ctx }/user/userRole?dbid=${user.dbid}'">授权</a>
		</tr>
	</c:forEach>
	 <tr height="34"  >
       <td colspan="9" align="right" class="border-top: 0px"><%@ include file="../commons/common_pagination.jsp" %></td>
     </tr>	
	</table>
</div>
</c:if>
<div class="buttons" style="margin-top: 20px;text-align: left;margin-bottom: 20px;">
<a class="ui-state-default" href="javascript:void();" onclick="window.location.href='${ctx}/pages/user/add.jsp'">添加</a>
<a class="ui-state-default" href="javascript:void(-1);" onclick="$.utile.deleteIds('${ctx }/user/delete','searchPageForm')">删除</a>
</div>
</body>
</html>