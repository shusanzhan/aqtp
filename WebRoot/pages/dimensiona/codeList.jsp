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
<title>二维码明细</title>
</head>
<body class="bodycolor">
<table class="TableTop" width="100%">
		<tbody>
			<tr>
				<td class="left"></td>
				<td class="center">二维码明细</td>
				<td class="right"></td>
			</tr>
		</tbody>
</table>
<div id="search" style="width: 100%; height: 32px;margin-top: 2px;margin-bottom: 8px;">
	 <form name="searchPageForm" id="searchPageForm" action="${ctx}/dimensiona/queryDimCodeList" method="post">
     <input type="hidden" id="currentPage" name="currentPage" value='${page.currentPageNo}'>
     <input type="hidden" id="paramPageSize" name="pageSize" value='${page.pageSize}'>
     <input type="hidden" id="paramPageSize" name="pageSize" value='${param.status}'>
     <input type="hidden" id="dbid" name="dbid" value='${param.dbid}'>
	</form>
</div>
	<table width="92%" cellspacing="0" cellpadding="0" border="0"  style="margin-top: 10px;margin: 0 auto;margin-bottom: 10px;" align="center">
	<tr style="border-bottom: 1px solid black;" height="40">
		<td width="80" align="right">名称：</td><td>${dimensiona.name }</td>
		<td width="80" align="right">生成日期：</td><td>
			<fmt:formatDate value="${dimensiona.createDate }" />
		</td>
		<td width="80" align="right">生成人：</td><td>
			${dimensiona.user.realName }
		</td>
		<td width="80" align="right">数量：</td><td>
		<span style="font-size: 14px;color: red;">${dimensiona.quantity }</span>
		</td>
		<td></td>
		</tr>
	<tr style="border-bottom: 1px solid black;" height="40">
		<td width="80" align="right">名称：</td><td width="120">${dimensiona.chickenbatch.name }</td>
		<td width="80" align="right">批次：</td><td width="120">${dimensiona.chickenbatch.batchNo }</td>
		<td  width="80" align="right">品级：</td><td width="120">${dimensiona.chickenbatch.grade.name }</td>
		<td  width="80" align="right">品系：</td><td width="120">${dimensiona.chickenbatch.breed.name }</td>
		<td  width="80" align="center"><a href="${ctx }/chickenBatch/queryList" style="color: blue;">返回</a></td>
	</tr>
</table>
<c:if test="${empty(page.result)||page.result==null }" var="status">
	<div id="result" class="result"style="padding-left: 12px;">
	</div>
</c:if>
<c:if test="${status==false }">
<div id="result">
	<table width="100%" class="TableList" border="0">
	<thead class="TableHeader">
		<tr>
			<th class="span1" style="width: 40px;"><input type='checkbox' id="selectAllCheck" onclick="selectAll(this,'id')" />全选</th>
			<th class="span2">图片</th>
			<th class="span2">二维码名称</th>
			<th class="span2">编号</th>
			<th class="span2" align="left">操作</th>
		</tr>
	</thead>
	<c:forEach var="dimensionaCode" items="${page.result }">
		<tr height="32" align="center">
			<td><input type='checkbox' name="id" id="id1" value="${dimensionaCode.dbid }"/></td>
			<td>
				<img alt="" src="${dimensionaCode.photo  }" height="50" width="80">
			</td>
			<td>${dimensionaCode.dimensiona.name }</td>
			<td>${dimensionaCode.code }</td>
			<td>
			<a href="javascript:void(-1)"  class="system_right_table_a"	onclick="$.utile.deleteById('${ctx }/dimensiona/deleteCode?dbids=${dimensionaCode.dbid}','searchPageForm')">删除</a>
		</tr>
	</c:forEach>
	 <tr height="34"  >
       <td colspan="5" align="right" class="border-top: 0px"><%@ include file="../commons/common_pagination.jsp" %></td>
     </tr>	
	</table>
</div>
</c:if>
<div class="buttons" style="margin-top: 20px;text-align: left;margin-bottom: 20px;">
	<a class="ui-state-default" href="javascript:void(-1);" onclick="window.close()">关闭</a>
	<a class="ui-state-default" href="javascript:void(-1);" onclick="$.utile.deleteIds('${ctx }/dimensiona/deleteCode','searchPageForm')">删除</a>
	<a class="ui-state-default" href="javascript:void(-1);" onclick="window.location.href='${ctx}/dimensiona/queryList'">返回</a>
</div>
</body>
</html>