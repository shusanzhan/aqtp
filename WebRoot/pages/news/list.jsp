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
<script type="text/javascript" src="${ctx }/widgets/artDialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx }/widgets/artDialog/plugins/iframeTools.source.js"></script>
<title>新闻管理</title>
</head>
<body class="bodycolor">
<div id="search" style="width: 100%; height: 32px;margin-top: 2px;margin-bottom: 8px;">
	 <form name="searchPageForm" id="searchPageForm" action="${ctx}/news/queryList" method="post">
     <input type="hidden" id="currentPage" name="currentPage" value='${page.currentPageNo}'>
     <input type="hidden" id="paramPageSize" name="pageSize" value='${page.pageSize}'>
	 <table>
		<tr>
			<td>标题：</td>
			<td><input type="text" id="newName" name="newName" value="${param.newName}"></input></td>
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
			<th class="span5">标题</th>
			<th class="span2">发布人</th>
			<th class="span2">类型</th>
			<th class="span2">发布范围</th>
			<th class="span3">发布时间</th>
			<th class="span1">评论(条)</th>
			<th class="span1">点击数</th>
			<th class="span1">置顶</th>
			<th class="span1">状态</th>
			<th class="span3" align="left">操作</th>
		</tr>
	</thead>
	<c:forEach var="news" items="${page.result }">
		<tr height="32" align="center">
			<td><input type='checkbox' name="id" id="id1" value="${news.dbid }"/></td>
			<td align="left" title="${news.title }">
			<c:if test="${fn:length(news.title)<20 }" var="status">
				${news.title }
			</c:if>
			<c:if test="${status==false }">
				${fn:substring(news.title,0,20) }...
			</c:if>
			</td>
			<td>${news.releasePerson.realName }</td>
			<td>${news.newType.name }</td>
			<td>${news.receviePersonNames }</td>
			<td>
			<fmt:formatDate value="${news.releaseDate }" pattern="yyyy-MM-dd mm:hh"/>
			</td>
			<td>${news.newReviewsSize }</td>
			<td>${news.readNum }</td>
			<td>
				<c:if test="${news.topStatus==0 }">未置顶</c:if> 
				<c:if test="${news.topStatus==1 }">
					<span style="color: #008000;">置顶</span>
				</c:if>
			</td>
			<td>
			<c:if test="${news.newSatatus==0 }">
				<span style="color: red;">未发布</span>
			</c:if>
			<c:if test="${news.newSatatus==1 }">
				<span style="color: #008000;">生效</span>
			</c:if>
			</td>
			<td><a href="javascript:void(-1)" class="system_right_table_a"
				onclick="window.location.href='${ctx }/news/edit?dbid=${news.dbid}'">编辑</a>
				<a href="javascript:void(-1)" class="system_right_table_a" onclick="$.utile.deleteById('${ctx }/news/delete?dbids=${news.dbid}','searchPageForm')">删除</a>
				<a href="javascript:void(-1)" class="system_right_table_a" onclick="$.utile.deleteById('${ctx }/news/delete?dbids=${news.dbid}')">管理评论</a>
				<c:if test="${news.newSatatus==0 }">
					<a href="javascript:void(-1)" class="system_right_table_a" onclick="$.utile.operatorDataByDbid('${ctx}/news/startNews?dbid=${news.dbid}','searchPageForm','您确定要启用该新闻吗？')">启用</a>
				</c:if>
				<c:if test="${news.newSatatus==1 }">
					<a href="javascript:void(-1)" class="system_right_table_a" onclick="$.utile.operatorDataByDbid('${ctx}/news/startNews?dbid=${news.dbid}','searchPageForm','您确定要停止该新闻吗？')">停止</a>
				</c:if>
				
			</td>
		</tr>
	</c:forEach>
	 <tr height="34"  >
       <td colspan="11" align="right" class="border-top: 0px"><%@ include file="../commons/common_pagination.jsp" %></td>
     </tr>	
	</table>
</div>
<div class="buttons" style="margin-top: 20px;text-align: left;">
	<a class="ui-state-default" href="javascript:void(-1);" onclick="window.location.href='${ctx}/news/add'">添加</a>
	<a class="ui-state-default" href="javascript:void(-1);" onclick="$.utile.deleteIds('${ctx }/news/delete','searchPageForm')">删除</a>
	<a class="ui-state-default" href="javascript:void(-1);" onclick="$.utile.deleteIds('${ctx }/news/delete','searchPageForm')">查看阅读记录</a>
	<a class="ui-state-default" href="javascript:void(-1);" onclick="$.utile.operatorDataByDbids('${ctx}/news/canncelTop','searchPageForm','您确定要取消选择置顶新闻吗？')" title="取消置顶">取消置顶</a>
	<a class="ui-state-default" href="javascript:void(-1);" onclick="$.utile.operatorDataByDbids('${ctx}/news/startNewsByIds','searchPageForm','您确定要启用所选新闻吗？')" title="启用所选新闻">启用</a>
	<a class="ui-state-default" href="javascript:void(-1);" onclick="$.utile.operatorDataByDbids('${ctx}/news/canncelNewsByIds','searchPageForm','您确定要停止所选新闻吗？')"  title="停止所选新闻">停止</a>
</div>
</body>
</html>