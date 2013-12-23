<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/css/index/index.css" type="text/css" rel="stylesheet"></link>
<link href="${ctx }/css/index/skdslider.css" type="text/css" rel="stylesheet">
<title>仁安主页</title>
</head>
<body>
   <jsp:include page="header.jsp"></jsp:include>
	<div id="container banner" class="banner">
		<div id="coin-slider" class="skdslider">
			<ul>
				<li id="pb1"></li>
				<li id="pb2" ></li>
				<li id="pb3" ></li>
				<li id="pb4" ></li>
				<li id="pb5" ></li>
			</ul>
		</div>
	</div>
	<div class="contWith contentPanel">
		<div class="contBan conBanMar">
			<div class="contTitle renaanjj"  onclick="window.open('${ctx}/home/read?dbid=${renanIntro.dbid}')">
			</div>
			<div class="conImg">
				<img alt="" src="${renanIntro.titlePicture }" width="285" height="122">
			</div>
			<div class="content">
				<p style="color: #e76f16;font-size: 16px;line-height: 32px">${renanIntro.title }</p>
				<c:if test="${fn:length(renanIntro.introduction) >55 }" var="status">
					${fn:substring(renanIntro.introduction,0,55)  }......
				</c:if>
				<c:if test="${status==false }">
					${renanTntro.introduction }
				</c:if>
			</div>
			<div class="more moreJj" onclick="window.open('${ctx}/home/read?dbid=${renanIntro.dbid}')">
			</div>
		</div>
		<div class="contBan conBanMar">
			<div class="contTitle renaanzxdt">
			</div>
			<div class="conImg">
				<div class="comtentImg" onclick="window.open('${ctx}/home/read?dbid=${topIsOnTime.dbid}')">
					<img alt="" src="${topIsOnTime.titlePicture }" width="110" height="60">
				</div>
				<div class="comtentTitle" onclick="window.open('${ctx}/home/read?dbid=${topIsOnTime.dbid}')">
					<p style="font-size: 16px;color: #033d08;">
					<c:if test="${fn:length(topIsOnTime.title) >10 }" var="status">
							${fn:substring(topIsOnTime.title,0,10)  }...
						</c:if>
						<c:if test="${status==false }">
							${topIsOnTime.title }
						</c:if>
					</p>
					<p><c:if test="${fn:length(topIsOnTime.introduction) >20 }" var="status">
							${fn:substring(topIsOnTime.introduction,0,20)  }......
						</c:if>
						<c:if test="${status==false }">
							${topIsOnTime.introduction }
						</c:if>
					</p>
				</div>
				<div class="content" style="width: 312px;">
					<ul>
						<c:forEach var="onTime" items="${isOnTimes }" begin="1" end="4">
						<li >
							<img alt="" src="${ctx }/images/index/icon.png" style="">
							<a href="javascript:void(-1)" onclick="window.open('${ctx}/home/read?dbid=${onTime.dbid}')" title="${onTime.title}">
								<c:if test="${fn:length(onTime.title) >20 }" var="status">
									${fn:substring(onTime.title,0,20)  }......
								</c:if>
								<c:if test="${status==false }">
									${onTime.title }
								</c:if>
							</a>
							<p > <fmt:formatDate value="${onTime.releaseDate }" pattern="yyyy-MM-dd HH:mm"/>
						</li>
						</c:forEach>
					</ul>
				</div>
				<div class="more moreZx" onclick="window.location.href='${ctx }/home/intro?newTypePDbid=${9 }'">
				</div>
			</div>
		</div>
		<div class="contBan conBanMar">
			<div class="zhuis">
			<form action="${ctx }/home/search" id="frm" name="frm">
				<div class="zsInput">
					<input type="text" id="batchNo" name="batchNo" value="${param.batchNo }" placeholder="请输入追溯码">
					<div class="zsSearch" onclick="searchFrm()"></div>
				</div>
			</form>
			</div>
			<div class="contTitle renaanlxwm">
			</div>
			<div class="content" style="width: 312px;line-height: 24px;">
				<p style="font-size: 15px;line-height: 28px;">资兴市人安生态禽业有限公司</p>	
				<p>地址：资兴市人安生态禽业有限公司</p>
				<p>	电话：54151512-51512-152121</p>
				<p>	传真：5412121212</p>
				<p>	E-mail:451132132@qq.com</p>
			</div>
		</div>
	</div>
	<jsp:include page="bottom.jsp"></jsp:include>
</body>
<script type="text/javascript" src="${ctx }/widgets/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/skdslider.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
		$('#coin-slider').skdslider({'delay':5000, 'fadeSpeed': 2000,'autoStart':true});
	});
    
</script>
<script type="text/javascript">
function searchFrm(){
	var qForm=$("#frm");
    if(typeof(qForm)=="undefined") return;
    var batchNo= $("#batchNo").val();
    if(typeof(batchNo)!="undefined" && batchNo!=null && batchNo!=""){
    }else{
    	alert("请输入追溯码!");
     	return false;
    }
    qForm.submit();
}
</script>
</html>