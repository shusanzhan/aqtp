<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/css/index/index.css" type="text/css" rel="stylesheet"></link>
<link href="${ctx }/css/index/list.css" type="text/css" rel="stylesheet">
<title>人安生态---${parent.name }</title>
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
       <c:if test="${empty(page)||page.totalCount<=0 }"><!-- 无数情况下 提示系统暂时未添加数据 -->
	         <div class="right_head">
	            <div class="rightHeadTitle">
	            	 <c:if test="${!empty(child) }" var="status">
		            	${child.name }
		            </c:if>
		            <c:if test="${status==false }">
		           		 ${parent.name }
		            </c:if>
	            </div>
	        </div>
	        <div class="right_con">
	          <div class="contentIntro">
	          	系统尚未添加数据，请联系管理员！
	          </div>
	        </div>
       </c:if>
       <c:if test="${page.totalCount==1 }">
	        <div class="right_head">
	            <div class="rightHeadTitle"> 
	            	<c:if test="${!empty(child) }" var="status">
		            	${child.name }
		            </c:if>
		            <c:if test="${status==false }">
		           		 ${parent.name }
		            </c:if></div>
	        </div>
	        <div class="right_con">
          	<c:forEach var="news" items="${page.result }">
	        <div class="text_head">
	            <div style="font-family:'微软雅黑';font-size:24px;font-weight:bold;color:#000;">${news.title }</div>
	            <div style="padding-top:12px;">时间：<fmt:formatDate value="${news.releaseDate }" pattern="yyyy-MM-dd HH:mm"/>   点击：${news.readNum } 次</div>
	            <div style="background: #BEBEBE repeat-x;width:100%;height:1px;margin-top:12px;"></div>
          	</div>
	          <div class="contentIntro">
	          		${news.content }
	          </div>
	          	</c:forEach>
	        </div>
	    </c:if>
       <c:if test="${page.totalCount>1 }">
	       <div class="right_head">
	            <div class="rightHeadTitle">
		            <c:if test="${!empty(child) }" var="status">
		            	${child.name }
		            </c:if>
		            <c:if test="${status==false }">
		           		 ${parent.name }
		            </c:if>
	            </div>
	          <div style="clear:both;"></div> 
       	   </div>
	        <div class="rightList">
	        		<c:forEach var="news" items="${page.result }">
		          		<div class="introXrzl" >
			          		<div class="introPicture">
			          			<a href="${ctx}/home/read?dbid=${news.dbid }">
			          				<img src="${news.titlePicture }" width="132" height="82" title="${news.title }"></img></a>
			          		</div>
			          		<div class="sContent">
				          		<div class="introPictureTitle"><p><a title="${news.title }" href="${ctx}/home/read?dbid=${news.dbid }">${news.title }</a></p></div>
				          		<div class="introduction">
				          			<c:if test="${fn:length(news.introduction) >100 }" var="status">
										${fn:substring(news.introduction,0,100)  }......
									</c:if>
									<c:if test="${status==false }">
										${news.introduction }
						</c:if>
				          		</div>
			          		</div>
			          		<div  class="clear"></div>
		          		</div>
		          </c:forEach>
	        </div>
	      <div class="turnpage">
	          <div style="float:left;margin-left:30px;"><p>〖第 ${page.currentPageNo } 页，共  ${page.totalPageCount } 页，共 ${page.totalCount } 条〗</p></div>
	         <div style="float:right;margin-right:30px;">
	         				<c:if test="${page.currentPageNo>1}"><a href="${ctx}/home/intro?currentPage=1&pageSize=${param.pageSize==null?16:param.pageSize}&newTypePDbid=${param.newTypePDbid}&newTypeCDbid=${param.newTypeCDbid}" title="首页"><img src="${ctx}/images/index/first.png" title="首页"></a>&nbsp;</c:if>
							<c:if test="${page.currentPageNo<=1}"><img src="${ctx}/images/index/first.png">&nbsp;</c:if>
							<c:if test="${page.currentPageNo>1}"><a href="${ctx}/home/intro?currentPage=${param.currentPage>1?param.currentPage-1:1}&pageSize=${param.pageSize==null?16:param.pageSize}&newTypePDbid=${param.newTypePDbid}&newTypeCDbid=${param.newTypeCDbid}" title="上一页"><img src="${ctx}/images/index/turnup.png"></a></c:if>
							<c:if test="${page.currentPageNo<=1}"><img src="${ctx}/images/index/turnup.png"></c:if>
							<c:if test="${page.currentPageNo < page.totalPageCount}"><a href="${ctx}/home/intro?currentPage=${param.currentPage<page.totalPageCount?param.currentPage+1:page.totalPageCount}&pageSize=${param.pageSize==null?16:param.pageSize}&newTypePDbid=${param.newTypePDbid}&newTypeCDbid=${param.newTypeCDbid}" title="下页"><img src="${ctx}/images/index/turndown.png"></a></c:if>
							<c:if test="${page.currentPageNo >= page.totalPageCount}"><img src="${ctx}/images/index/turndown.png"></c:if>
							<c:if test="${page.currentPageNo < page.totalPageCount}"><a href="${ctx}/home/intro?currentPage=${page.totalPageCount}&pageSize=${param.pageSize==null?16:param.pageSize}&newTypePDbid=${param.newTypePDbid}&newTypeCDbid=${param.newTypeCDbid}" title="末页"><img src="${ctx}/images/index/last.png"></a></c:if>
							<c:if test="${page.currentPageNo >= page.totalPageCount}"><img src="${ctx}/images/index/last.png"></c:if>
	          </div>
	          <div style="clear:both;"></div>
        </div>  
	    </c:if>
      </div>   
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