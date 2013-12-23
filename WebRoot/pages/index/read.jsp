<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/css/index/index.css" type="text/css" rel="stylesheet"></link>
<link href="${ctx }/css/index/list.css" type="text/css" rel="stylesheet">
<title>${news.newstype.name }--${news.title }</title>
</head>
<body>
<div class="container">
 <jsp:include page="header.jsp"></jsp:include>
 <div class="banner companyBanner">
	</div>
  <div class="main">
    <div class="main_top">
      <div class="location">当前所在位置：&nbsp;  
	      <img src="${ctx}/images/index/homeIcon.png" style="width:10px;height:10px;cursor: pointer;" onclick="window.location.href='${ctx}/home/home'"/> 
	     	<a href='${ctx}/home/home'>首页</a> - 
	      	<a href="${ctx }/home/intro?newTypePDbid=${parent.dbid}">${parent.name }</a>
	      	<c:if test="${!empty(child) }">
	      		 - <a href="${ctx }/home/intro?newTypePDbid=${parent.dbid}&newTypeCDbid=${child.dbid}">${child.name }</a>
	      	</c:if>
      </div>
     <div class="readInput">
              <form name="frm" id="frm"  action="${ctx}/home/search" method="post">
					<input type="text" id="batchNo" name="batchNo" placeholder="请输入追溯码">
					<div class="zsSearch" onclick="searchFrm()"></div>
		      </form> 
	 </div>
    </div>
    <div class="main_main">
        <jsp:include page="left.jsp"></jsp:include>
    
      <div class="right">
	         <div class="right_head">
	            <div class="rightHeadTitle">${news.newstype.name }</div>
	        </div>
	        <div class="text_head">
	            <div style="font-family:'微软雅黑';font-size:24px;font-weight:bold;color:#000;">${news.title }</div>
	            <div style="padding-top:12px;">时间：<fmt:formatDate value="${news.releaseDate }" pattern="yyyy-MM-dd HH:mm"/>   点击：${news.readNum } 次</div>
	            <div style="background: #BEBEBE repeat-x;width:100%;height:1px;margin-top:12px;"></div>
          	</div>
	        <div class="right_con">
	          <div class="contentIntro">
	          	${news.content }
	          </div>
	        </div>
	     <div style="background:#BEBEBE repeat-x;width:100%;height:1px;margin-top:30px;"></div>
	        <div class="turnpage">
	          <div style="float:left;padding-left: 12px;">
	         	 <c:if test="${empty(previous) }">
					<a href="#" style="text-decoration: none;padding-right: 12px;color: #E3E0D5">
						<span>上一篇:</span>&nbsp;&nbsp;没有文章了
					</a>
				</c:if>
				<c:if test="${!empty(previous) }">
					<a href="${ctx }/home/read?dbid=${previous.dbid}" title="${previous.title }">
						<span>上一篇:</span>&nbsp;&nbsp;
						<c:if test="${fn:length(previous.title)>18 }" var="status">
							${fn:substring(previous.title,0,14) }...
						</c:if>
						<c:if test="${status==false }">
							${previous.title }
						</c:if>
					</a>
				</c:if>
	          </div>
	          <div style="float:right;padding-right: 12px;">
	           <c:if test="${empty(next) }">
					<a href="#" style="float: right;text-decoration: none;color: #E3E0D5">
						<span>下一篇:</span>&nbsp;&nbsp;没有文章了
					</a>
				</c:if>
				<c:if test="${!empty(next) }">
					<a href="${ctx }/home/read?dbid=${next.dbid}" title="${next.title }">
						<span>下一篇:</span>&nbsp;&nbsp;
						<c:if test="${fn:length(next.title)>14 }" var="status">
							${fn:substring(next.title,0,14) }...
						</c:if>
						<c:if test="${status==false }">
							${next.title }
						</c:if>
					</a>
				</c:if>
	          </div>
	          <div style="clear:both;"></div>
	        </div>
      </div>
	          <div style="clear:both;"></div>
  </div>
  </div>
 <jsp:include page="bottom.jsp"></jsp:include>  
</div>
</body>
<script type="text/javascript" src="${ctx }/widgets/jqueryui/jquery.min.js"></script>
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