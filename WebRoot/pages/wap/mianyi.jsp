<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel='stylesheet' type='text/css' href='${ctx }/css/wap/front.css' />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>保健信息</title>
</head>
	<body class='global'>
		<div class='module' id='content'>
			<div class="nav" id='navigator'>
				<ul>
					<li><a href='${ctx }/wapHome/home?batchNo=${chickenBatch.batchNo}'>首页</a></li>
					<li><a href='${ctx }/wapHome/fodder?batchNo=${chickenBatch.batchNo}'>饲料</a></li>
					<li><a href='${ctx }/wapHome/mianyi?batchNo=${chickenBatch.batchNo}'>免疫</a></li>
					<li><a href='${ctx }/wapHome/baojian?batchNo=${chickenBatch.batchNo}'>保健</a></li>
					<li><a href='${ctx }/wapHome/jianyi?batchNo=${chickenBatch.batchNo}'>检疫</a></li>
				</ul>
			</div>
			<div class="list topnews">
				<ul>
				<c:if test="${empty(immunes) }" var="status">
						 <li>
	                        <strong><a>系统无数据</a></strong>
	                    </li>
					</c:if>
					<c:if test="${status==false }">
					<c:forEach var="immune" items="${immunes }">
						<li>
	                        <strong><a>名称：</a></strong>
	                        <span>
	                        <c:if test="${empty(immune.immunePerson)||fn:length(immune.immunePerson)<=0 }" var="status">
	                        	无数据
	                        </c:if>
	                        <c:if test="${status==false }">
	                       	 ${immune.immunePerson }
	                        </c:if>
	                        </span>
	                    </li>
	                   	<div class="content">
	                   		<span> 时间:</span>    
			    			<fmt:formatDate value="${immune.immuneDate }" />
			    			<br>
			    			<span style="color: #E76F16;font-weight: bold;"> 药品:</span>
							<c:if test="${!empty(immune.immunedrags) }" var="status">
				    			<c:forEach var="healthcaredrag" items="${healthCare.healthcaredrags }" end="1">
			    				${healthcaredrag.name }&nbsp;${healthcaredrag.dose };
			    				</c:forEach>
			    			</c:if>
			    			<c:if test="${status==false }">
			    				无药品信息
			    			</c:if>
			    			<br>
							<span style="color: #E76F16;font-weight: bold;"> 备注:</span>
								<c:if test="${empty(immune.note )||fn:length(immune.note )<=0 }" var="status">
		                        	无数据
		                        </c:if>
		                        <c:if test="${status==false }">
		                     	 ${immune.note }
		                     	</c:if>  
		                   </div>
					</c:forEach>
                   </c:if> 
                </ul>
			</div>
		</div> 
	</body>
</html>