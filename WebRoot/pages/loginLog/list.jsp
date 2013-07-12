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
<title>登录日志管理</title>
</head>
<body class="bodycolor">
<table class="TableTop" width="100%">
		<tbody>
			<tr>
				<td class="left"></td>
				<td class="center">登录日志管理</td>
				<td class="right"></td>
			</tr>
		</tbody>
</table>
<div id="search" style="width: 100%; height: 32px;margin-top: 2px;margin-bottom: 8px;">
	 <form name="searchPageForm" id="searchPageForm" action="${ctx}/loginLog/queryList" method="post">
     <input type="hidden" id="currentPage" name="currentPage" value='${page.currentPageNo}'>
     <input type="hidden" id="paramPageSize" name="pageSize" value='${page.pageSize}'>
	 <table>
		<tr>
			<td>登录用户：</td>
			<td><input type="text" id="userName" name="userName" class="input-small field" value="${param.userName}"></input></td>
			<td><input type="submit" value="查询"></input></td>
		</tr>
	 </table>
	</form>
</div>
<c:if test="${empty(page.result)||page.result==null }" var="status">
	<div id="result" style="padding-left: 12px;">
		无操作数据！请点击“添加”按钮进行添加数据操作
	</div>
</c:if>
<c:if test="${status==false }">
<div id="result">
	<table width="100%" class="TableList" border="0">
	<thead class="TableHeader">
		<tr>
			<th class="span1" style="width: 30px;"><input type='checkbox' id="selectAllCheck" onclick="selectAll(this,'id')" />全选</th>
			<th class="span1">登录ID</th>
			<th class="span2">登录人</th>
			<th class="span2">登录时间</th>
			<th class="span2">登录IP</th>
			<th class="span4">登录地点</th>
			<th class="span3">sessionId</th>
		</tr>
	</thead>
	<c:forEach var="loginLog" items="${page.result }">
		<tr height="32" align="center">
			<td><input type='checkbox' name="id" id="id1" value="${loginLog.dbid }"/></td>
			<td>${loginLog.userId }</td>
			<td>${loginLog.userName }</td>
			<td>${loginLog.loginDate }</td>
			<td>${loginLog.ipAddress }</td>
			<td>${loginLog.loginAddress }</td>
			<td>${loginLog.sessionId }</td>
		</tr>
	</c:forEach>
	 <tr height="6"  >
       <td colspan="10" align="right" class="border-top: 0px"><%@ include file="../commons/common_pagination.jsp" %></td>
     </tr>	
	</table>
</div>
</c:if>
</body>
</html>