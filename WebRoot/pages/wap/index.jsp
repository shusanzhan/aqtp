<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel='stylesheet' type='text/css' href='${ctx }/css/wap/index2.css' />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网页查询主页面</title>
</head>
	<body class='global'>
		<div class="nav">
			<ul>
				<li><div><a href='${ctx }/wapHome/home?batchNo=${param.batchNo}'>首页</a></div></li>
				<li><div><a href='${ctx }/wapHome/fodder?batchNo=${param.batchNo}'>饲料</a></div></li>
				<li><div><a href='${ctx }/wapHome/mianyi?batchNo=${param.batchNo}'>免疫</a></div></li>
				<li><div><a href='${ctx }/wapHome/baojian?batchNo=${param.batchNo}'>保健</a></div></li>
				<li><div><a href='${ctx }/wapHome/jianyi?batchNo=${param.batchNo}'>检疫</a></div></li>
			</ul>
		</div>
		
			<div class="mainmessage">
			<div> <div class="odd first"><h1>名称</h1></div>    <div class="odd second"><h1>${chickenBatch.name }</h1></div></div>
			<div> <div class="even first"><h1>品系</h1></div>   <div class="even second"><h1>${chickenBatch.breed.name}</h1></div></div>	
			<div> <div class="odd first"><h1>品级</h1></div>    <div class="odd second"><h1>${chickenBatch.breed.name }</h1></div> </div>
			<div> <div class="even first"><h1>出生日期</h1></div>   <div class="even second"><h1><fmt:formatDate value="${chickenBatch.birthday }" pattern="yyyy-MM-dd"/></h1></div></div>
			<div> <div class="odd first"><h1>出栏日期</h1></div>    <div class="odd second"><h1><fmt:formatDate value="${chickenBatch.outBarDate}" pattern="yyyy-MM-dd"/></h1></div></div>
			<div> <div class="even first"><h1>建议零售价</h1></div>    <div class="even second"><h1>${chickenBatch.countNum}元/斤</h1></div></div>
			</div>
			<div class="logo"></div>
		
	</body>
</html>