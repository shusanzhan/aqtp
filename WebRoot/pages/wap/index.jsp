<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel='stylesheet' type='text/css' href='${ctx }/css/wap/front.css' />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网页查询主页面</title>
</head>
	<body class='global'>
		<div class="nav" id='navigator'>
			<ul>
				<li><a href='${ctx }/wapHome/home?batchNo=${chickenBatch.batchNo}'>首页</a></li>
				<li><a href='${ctx }/wapHome/fodder?batchNo=${chickenBatch.batchNo}'>饲料</a></li>
				<li><a href='${ctx }/wapHome/mianyi?batchNo=${chickenBatch.batchNo}'>免疫</a></li>
				<li><a href='${ctx }/wapHome/baojian?batchNo=${chickenBatch.batchNo}'>保健</a></li>
				<li><a href='${ctx }/wapHome/jianyi?batchNo=${chickenBatch.batchNo}'>检疫</a></li>
			</ul>
		</div>
		<div class='module' id='content'>
			<div class="list topnews">
				<ul>
                    <li>
                        <strong><a>名称：</a></strong>
                        <span>${chickenBatch.name }</span>
                    </li>
                    <li>
                        <strong><a>品系：</a></strong>
                        <span>${chickenBatch.grade.name }</span>
                    </li>
                    <li>
                        <strong><a>品级：</a></strong>
                        <span><a>${chickenBatch.breed.name }</a></span>
                    </li>
                    <li>
                        <strong><a>出生日期：</a></strong>
                        <span><fmt:formatDate value="${chickenBatch.birthday }" pattern="yyyy-MM-dd"/> </span>
                    </li>
                    <li>
                        <strong><a>出栏日期：</a></strong>
                        <span><fmt:formatDate value="${chickenBatch.outBarDate}" pattern="yyyy-MM-dd"/></span>
                    </li>
                    <li>
                        <strong><a>零售价(￥)：</a></strong>
                        <span>${chickenBatch.countNum}元/斤</span>
                    </li>
                </ul>
			</div>
		</div> 
	</body>
</html>