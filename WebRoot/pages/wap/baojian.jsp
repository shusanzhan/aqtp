<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel='stylesheet' type='text/css' href='${ctx }/css/wap/index2.css' />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>保健信息</title>
</head>
<body class='global'>
	<div class="nav">
		<ul>
			<li><div>
					<a href='${ctx }/wapHome/home?batchNo=${param.batchNo}'>首页</a>
				</div></li>
			<li><div>
					<a href='${ctx }/wapHome/fodder?batchNo=${param.batchNo}'>饲料</a>
				</div></li>
			<li><div>
					<a href='${ctx }/wapHome/mianyi?batchNo=${param.batchNo}'>免疫</a>
				</div></li>
			<li><div>
					<a href='${ctx }/wapHome/baojian?batchNo=${param.batchNo}'>保健</a>
				</div></li>
			<li><div>
					<a href='${ctx }/wapHome/jianyi?batchNo=${param.batchNo}'>检疫</a>
				</div></li>
		</ul>
	</div>
	<div>

		<c:if test="${empty(healthCares) }" var="status">
			<strong><a>系统无数据</a></strong>
		</c:if>
		<c:if test="${status==false }">
			<c:forEach var="healthCare" items="${healthCares }">

				<div class="group mainmessage">
					<div>
						<div class="odd first">
							<h1>保健名称</h1>
						</div>
						<div class="odd second">
							<h1>
								<c:if
									test="${empty(healthCare.name)||fn:length(healthCare.name)<=0 }"
									var="status">
	                        	无数据
	                        </c:if>
								<c:if test="${status==false }">
	                       	 ${healthCare.name }
	                        </c:if>
							</h1>
						</div>
					</div>
					<div>
						<div class="even first">
							<h1>保健时间</h1>
						</div>
						<div class="even second">
							<h1>
								<fmt:formatDate value="${healthCare.beginDate }" />
								&nbsp;&nbsp;至&nbsp;&nbsp;
								<fmt:formatDate value="${healthCare.endDate }" />
							</h1>
						</div>
					</div>
					<div>
						<div class="odd first">
							<h1>保健药品</h1>
						</div>
						<div class="odd second">
							<h1>
								<c:if test="${!empty(healthCare.healthcaredrags) }" var="status">
									<c:forEach var="healthcaredrag"
										items="${healthCare.healthcaredrags }" >
			    				${healthcaredrag.name }&nbsp;${healthcaredrag.dose };
			    				</c:forEach>
								</c:if>
								<c:if test="${status==false }">
			    				无药品信息
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