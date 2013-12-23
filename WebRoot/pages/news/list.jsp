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
			<td><input type="submit" value="查&nbsp;&nbsp;询" class="inputSubmit" style="width: 60px;height: 30px;"></input></td>
		</tr>
	 </table>
	</form>
</div>
<div id="result">
	<table width="100%" class="TableList" border="0">
	<thead class="TableHeader">
		<tr>
			<th class="span1"><input type='checkbox' id="selectAllCheck" onclick="selectAll(this,'id')" />全选</th>
			<th class="span6">标题</th>
			<th class="span2">发布人</th>
			<th class="span2">类型</th>
			<th class="span3">发布时间</th>
			<th class="span1">点击数</th>
			<th class="span1">标题图片</th>
			<th class="span1">幻灯片</th>
			<th class="span1">实时要闻</th>
			<th class="span1">最新动态</th>
			<th class="span1">状态</th>
			<th class="span4" align="left">操作</th>
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
			<td>${news.newstype.name }</td>
			<td>
			<fmt:formatDate value="${news.releaseDate }" pattern="yyyy-MM-dd mm:hh"/>
			</td>
			<td>${news.readNum }</td>
			<td>
				<c:if test="${news.isTitlePicture==true }" var="status">
				<span style="color: #008000;">是</span>
				</c:if> 
				<c:if test="${status==false }">
					<span style="color: red;">否</span>
				</c:if>
			</td>
			<td>
			<c:if test="${news.isBannerPicture==true }" var="status">
				<span style="color: #008000;">是</span>
			</c:if>
			<c:if test="${status==false }">
				<span style="color: red;">否</span>
			</c:if>
			</td>
			<td>
				<c:if test="${news.isOnTime==true }" var="status">
					<span style="color: #008000;">是</span>
				</c:if>
				<c:if test="${status==false }">
					<span style="color: red;">否</span>
				</c:if>
			</td>
			<td>
				<c:if test="${news.isLatestNew==true }" var="status">
					<span style="color: #008000;">是</span>
				</c:if>
				<c:if test="${status==false }">
					<span style="color: red;">否</span>
				</c:if>
			</td>
			<td>
				<c:if test="${news.isStop==true }" var="status">
					<span style="color: #008000;">启用</span>
				</c:if>
				<c:if test="${status==false }">
					<span style="color: red;">停用</span>
				</c:if>
			</td>
			<td><a href="javascript:void(-1)" class="system_right_table_a"
				onclick="window.location.href='${ctx }/news/edit?dbid=${news.dbid}'">编辑</a>
				<a href="javascript:void(-1)" class="system_right_table_a" onclick="$.utile.deleteById('${ctx }/news/delete?dbids=${news.dbid}','searchPageForm')">删除</a>
				<a href="javascript:void(-1)" class="system_right_table_a" onclick="window.open('${ctx}/home/read?dbid=${news.dbid }')">预览</a>
				<c:if test="${news.isStop==true }" var="status">
					<a href="javascript:void(-1)" class="system_right_table_a" onclick="$.utile.operatorDataByDbid('${ctx}/news/startNews?dbid=${news.dbid}','searchPageForm','您确定要停止该新闻吗？')">停止</a>
				</c:if>
				<c:if test="${status==false }">
					<a href="javascript:void(-1)" class="system_right_table_a" onclick="$.utile.operatorDataByDbid('${ctx}/news/startNews?dbid=${news.dbid}','searchPageForm','您确定要启用该新闻吗？')">启用</a>
				</c:if>
			</td>
		</tr>
	</c:forEach>
	 <tr height="34"  >
       <td colspan="12" align="right" class="border-top: 0px"><%@ include file="../commons/common_pagination.jsp" %></td>
     </tr>	
	</table>
</div>
<div class="buttons" style="margin-top: 20px;text-align: left;margin-bottom: 20px;">
	<a class="ui-state-default" href="javascript:void(-1);" onclick="window.location.href='${ctx}/news/add'">添加</a>
	<a class="ui-state-default" href="javascript:void(-1);" onclick="$.utile.deleteIds('${ctx }/news/delete','searchPageForm')">删除</a>
	<a class="ui-state-default" href="javascript:void(-1);" onclick="$.utile.operatorDataByDbids('${ctx}/news/startNewsByIds','searchPageForm','您确定要启用所选新闻吗？')" title="启用所选新闻">启用</a>
	<a class="ui-state-default" href="javascript:void(-1);" onclick="$.utile.operatorDataByDbids('${ctx}/news/canncelNewsByIds','searchPageForm','您确定要停止所选新闻吗？')"  title="停止所选新闻">停止</a>
	<a class="ui-state-default" href="javascript:void(-1);" onclick="createIndex('${ctx}/news/createIndex')"  title="创建索引">创建索引</a>
</div>
</body>
<script type="text/javascript">
function createIndex(url){
	window.top.art.dialog({
		content : '您确要创建索引吗？',
		icon : 'warning',
		width:"250px",
		height:"80px",
		window : 'top',
		lock : true,
		ok : function() {// 点击去定按钮后执行方法
			$.post(url + "?datetime=" + new Date(),{},function callBack(data) {
				if (data[0].mark == 2) {// 关系存在引用，删除时提示用户，用户点击确认后在退回删除页面
					window.top.art.dialog({
						content : data[0].message,
						icon : 'warning',
						window : 'top',
						width:"250px",
						height:"80px",
						lock : true,
						ok : function() {// 点击去定按钮后执行方法

							$.utile.close();
							return;
						}
					});
				}
				if (data[0].mark == 1) {// 删除数据失败时提示信息
					/* $.cqtaUtile.errMessage(data[0].message); */
					$.utile.tips(data[0].message);
				}
				if (data[0].mark == 0) {// 删除数据成功提示信息
					/* $.cqtaUtile.okDeleteMessage(data[0].message); */
					$.utile.tips(data[0].message);
				}
				// 页面跳转到列表页面
				setTimeout(function() {
					window.location.href = data[0].url
				}, 1000);
			});
		},
		cancel : true
	});
}
</script>
</html>