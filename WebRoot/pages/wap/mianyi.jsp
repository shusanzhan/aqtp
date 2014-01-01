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
		<c:if test="${empty(immunes) }" var="status">
			<li><strong><a>系统无数据</a></strong></li>
		</c:if>
		<c:if test="${status==false }">
			<c:forEach var="immune" items="${immunes }">

				<div class="group mainmessage">
					<div>
						<div class="odd first">
							<h1>免疫名称</h1>
						</div>
						<div class="odd second">
							<h1>
								<c:if
									test="${empty(immune.name)||fn:length(immune.name)<=0 }"
									var="status">
	                        	无数据
	                        </c:if>
								<c:if test="${status==false }">
	                       	 ${immune.name }
	                        </c:if>
							</h1>
						</div>
					</div>
					<div>
						<div class="even first">
							<h1>免疫时间</h1>
						</div>
						<div class="even second">
							<h1>
								<fmt:formatDate value="${immune.immuneDate }" />
							</h1>
						</div>
					</div>
					<div>
						<div class="even first">
							<h1>免疫药品</h1>
						</div>
						<div class="even second">
							<h1>
								<c:if test="${!empty(immune.immunedrags) }" var="status">
									<c:forEach var="healthcaredrag"
										items="${immune.immunedrags }">
                                                            ${healthcaredrag.name }&nbsp;${healthcaredrag.dose };
                                                            </c:forEach>
								</c:if>
								<c:if test="${status==false }">
                                                            无药品信息
                                                    </c:if>
							</h1>
						</div>
					</div>
					<div>
						<div class="even first">
							<h1>免疫备注</h1>
						</div>
						<div class="even second">
							<h1>
								<c:if test="${empty(immune.note )||fn:length(immune.note )<=0 }"
									var="status">
		                        	无数据
		                        </c:if>
								<c:if test="${status==false }">
		                     	 ${immune.note }
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