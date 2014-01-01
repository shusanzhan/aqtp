<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel='stylesheet' type='text/css' href='${ctx }/css/wap/index2.css' />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网页查询饲料信息</title>
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

					<c:if test="${empty(feeders) }" var="status">
						 <li>
	                        <strong><a>系统无数据</a></strong>
	                    </li>
					</c:if>
					<c:if test="${status==false }">
					<c:forEach var="feeder" items="${feeders }">
					
					<div class="group mainmessage">
					<div>   
					<div class="odd first"><h1>名称</h1></div>  
					<div class="odd second">
					<h1>
	                        <c:if test="${empty(feeder.feeder.name)||fn:length(feeder.feeder.name)<=0 }" var="status">
	                        	无数据
	                        </c:if>
	                        <c:if test="${status==false }">
	                       	 ${feeder.feeder.name }
	                        </c:if>
	                 </h1>
	                 </div>
	                 </div>
	                 <div> 
	                 <div class="even first"><h1>组成:</h1></div>   
	                 <div class="even second"><h1>
						<c:if test="${empty(feeder.feeder.elementsPercentage )||fn:length(feeder.feeder.elementsPercentage )<=0 }" var="status">
	                        	无数据
		                        </c:if>
		                        <c:if test="${status==false }">
		                     	 ${feeder.feeder.elementsPercentage }
		                        </c:if></h1>
	                 </div>
	                 </div>

	                 <div> 
	                 <div class="even first"><h1>备注:</h1></div>   
	                 <div class="even second"><h1>
						<c:if test="${empty(feeder.feeder.note )||fn:length(feeder.feeder.note )<=0 }" var="status">
		                        	无数据
		                        </c:if>
		                        <c:if test="${status==false }">
		                     	 ${feeder.feeder.note }
		                     	</c:if>    
		                     	</h1>
	                 </div>
	                 </div>
	                 </div>
	                 </c:forEach>
	                 </c:if>
	                 </div>


	</body>
</html>