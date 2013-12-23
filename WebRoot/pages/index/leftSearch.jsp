<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<div class="left">
	<div class="con">
          <div class="conhead"></div>
          <div class="conicon">搜索结果</div>
          <div class="con_content">
            <ul>
		           <li><img src="${ctx}/images/index/tri.png" style="width:7px;height:10px;"><a href="javascript:void(-1)" title="${param.batchNo }">搜索关键词：&nbsp;${param.batchNo }</a></li>
            </ul>
          </div>
    </div>
	<div class="news">
          <div class="conhead">
            <div style="width:90px; height:35px; line-height:35px; text-align:center; font-family:'微软雅黑'; font-size:16px;font-weight:bold; color:#ffffff; float:left;">咨询动态</div>
            <div class="more"><!-- <a href="#">更多>></a --></div>
          </div>
          <div class="newsCon">
          	<ul>
          		<c:forEach var="isOnNew" items="${latestNews }" varStatus="index" end="2">
		          		<li>
			          		<c:if test="${index.index==0 }">
			          			<img src="${ctx}/images/index/numOne.png" ></img>
			          		</c:if>
			          		<c:if test="${index.index==1 }">
			          			<img src="${ctx}/images/index/numTwo.png" ></img>
			          		</c:if>
			          		<c:if test="${index.index==2 }">
			          			<img src="${ctx}/images/index/numThree.png" ></img>
			          		</c:if>
			          		<%-- <a href="javascript:void(-1)" title="${isOnNew.title }" onclick="window.open('${ctx}/home/readIsOnTime?dbid=${isOnNew.dbid }&readType=2')"> --%> 
			          		<a href="javascript:void(-1)" title="${isOnNew.title }" onclick="window.open('${ctx}/home/read?dbid=${isOnNew.dbid }&readType=2')"> 
	          					<c:if test="${fn:length(isOnNew.title)> 16 }" var="status">
									${fn:substring(isOnNew.title,0,16) }...
								</c:if>
								<c:if test="${status==false }">
									${isOnNew.title }
								</c:if>
		          			</a>
		          		</li>
          		</c:forEach>
          	</ul>
          </div>  
        </div>
</div>