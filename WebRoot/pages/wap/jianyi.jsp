<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel='stylesheet' type='text/css' href='${ctx }/css/wap/index2.css' />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>保健信息</title>
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
			
			
			<div >

					<c:if test="${empty(certificates) }" var="status">
						 <li>
	                        <strong><a>系统无数据</a></strong>
	                    </li>
					</c:if>
					<c:if test="${status==false }">
					<c:forEach var="certificate" items="${certificates }">
					
								<div class="group">
				<div><h2>${certificate.title }</h2></div>
				<div><h1>${certificate.awardDate } ${certificate.awardGroup }</h1></div>
				<div><img class="image" alt="" src="${certificate.certificateImage }"></div>
				<div>${certificate.note }</div>
			</div>
					</c:forEach>
                   </c:if> 
                   
			</div>
	</body>
</html>