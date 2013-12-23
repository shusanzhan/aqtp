<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/css/index/index.css" type="text/css" rel="stylesheet">
<link href="/css/index/list.css" type="text/css" rel="stylesheet">
<link href="/css/index/search.css" type="text/css" rel="stylesheet">
<title>中心简介</title>
<style type="text/css">
	.searchResult{
		width: 96%;margin:0px auto;margin-bottom: 12px;cursor: pointer;
	}
	.searchResult:HOVER {
	border: 1px solid ;
}
</style>
</head>
<body>
<div class="container">
 <jsp:include page="header.jsp"></jsp:include>
  <div class="main">
  
    <div class="main_top">
      <div class="location">当前所在位置：&nbsp;  
	      <img src="${ctx}/images/index/homeIcon.png" style="width:10px;height:10px;cursor: pointer;" onclick="window.location.href='${ctx}/home/home'"/> 
	      	<a href='${ctx}/home/home'>首页</a> - 
	      	<a href="javascript:void(-1)">搜索结果</a>
      </div>
        <div class="readInput">
              <form name="frm" id="frm"  action="${ctx}/home/search" method="post">
					<input type="text" id="batchNo" name="batchNo" value="${param.batchNo }" placeholder="请输入追溯码">
					<div class="zsSearch" onclick="searchFrm()"></div>
		      </form> 
	 </div>
    </div>
    
    <div class="main_main">
      <jsp:include page="leftSearch.jsp"></jsp:include>
      <div class="right">
       <c:if test="${empty(chickenBatch)||chickenBatch==null }" var="status"><!-- 无数情况下 提示系统暂时未添加数据 -->
	         <div class="right_head">
	            <div class="rightHeadTitle">
	            	搜索结果
	            </div>
	        </div>
	        <div class="right_con">
	          <div class="content">
	          	对不起！没有找到您所要的数据！
	          </div>
	        </div>
       </c:if>
       <c:if test="${status==false }">
	       <div class="right_head">
	            <div class="rightHeadTitle">搜索结果
	            </div>
       	   </div>
	        <div class="rightList">
	        	<div class="searchContent">
	        		<div class="searchTitle">综合信息</div>
	        		<div>
	        			<table width="92%" cellspacing="0" cellpadding="0" border="0"  style="margin-top: 20px;margin: 0 auto;" align="center">
							<tr style="border-bottom: 1px solid black;" height="30">
								<td width="80" align="right" class="">名称：</td><td width="120">${chickenBatch.name }</td>
								<td width="80" align="right">批次：</td><td width="120">${chickenBatch.batchNo }</td>
								<td  width="80" align="right">品级：</td><td width="120">${chickenBatch.grade.name }</td>
								<td  width="80" align="right">品系：</td><td width="120">${chickenBatch.breed.name }</td>
							</tr>
							<tr style="border-bottom: 1px solid black;" height="30">
								<td width="80" align="right">出生日期：</td><td>${chickenBatch.birthday }</td>
								<td width="80" align="right">入栏日期：</td><td>
									<fmt:formatDate value="${chickenBatch.intoBarDate }" />
								</td>
								<td width="80" align="right">出栏日期：</td><td>
									<fmt:formatDate value="${chickenBatch.outBarDate }" />
								</td>
								<td width="80" align="right">数量：</td><td>
								<span style="font-size: 14px;color: red;">${chickenBatch.countNum }</span>
								</td>
								<td></td>
							</tr>
						</table>
	        		</div>
	        	</div>
	        	<div class="searchContent">
	        		<div class="searchTitle">饲料信息</div>
	        		<div style="width: 100%;">
	        		<c:if test="${empty(feedfeeders) }" var="status">
				    	 <div style="height:120px;padding-left: 12px;" class="module_div" id="module_6_ul">暂无饲料信息</div>
				    </c:if>
			    	<c:if test="${status==false }">
			    		<c:forEach items="${feedfeeders }" var="feedfeeder" >
				    		<div  style="text-align: left;height: 30px;line-height: 30px;">
					    		<p>
					    			<span style="color: #E76F16;font-size: 16px;line-height: 32px ">${feedfeeder.name }</span>
					    			 组成:&nbsp;&nbsp;&nbsp;&nbsp;${feedfeeder.feeder.elementsPercentage } &nbsp;&nbsp;&nbsp;&nbsp; 备注:&nbsp;&nbsp;&nbsp;&nbsp;${feedfeeder.feeder.note} 
					    		</p>
				    		</div>
			    		</c:forEach>
			    	</c:if>
	        		</div>
	        	</div>
	        	<div class="searchContent">
	        		<div class="searchTitle">饲养员信息</div>
	        		<div>
	        		   <c:if test="${empty(breaderBreeds) }" var="status">
				    	 <div style="height:120px;padding-left: 12px;" >暂无饲养员信息</div>
				    </c:if>
				    <c:if test="${status==false }">
		        		<c:forEach var="breaderBreed" items="${breaderBreeds }">
			          		<div class="searchPic" >
				          		<div class="introPicture">
				          			<img src="${breaderBreed.breeder.photo }" width="180" height="120"></img>
				          		</div>
				          		<div class="searchPictureTitle"><p><a>${breaderBreed.name }</a></p></div>
			          		</div>
			         	 </c:forEach>
						 <div class="clear"></div>
		          	</c:if>
	        		</div>
	        	</div>
	        	<div class="searchContent">
	        		<div class="searchTitle">检疫证明</div>
	        		<div>
	        		   <c:if test="${empty(quarantinecertificates) }" var="status">
				    	 <div style="height:120px;padding-left: 12px;" >暂无检疫证明信息</div>
				    </c:if>
				    <c:if test="${status==false }">
		        		<c:forEach var="quarantineCertificate" items="${quarantinecertificates }">
			          		<div class="searchPic" >
				          		<div class="introPicture">
				          			<img src="${quarantineCertificate.certificateImage }" width="180" height="120"></img>
				          		</div>
				          		<div class="searchPictureTitle"><p><a>${quarantineCertificate.title }</a></p></div>
			          		</div>
			         	 </c:forEach>
						 <div class="clear"></div>
		          	</c:if>
	        		</div>
	        	</div>
	        	<div class="searchContent">
	        		<div class="searchTitle">免疫信息</div>
	        		<div>
	        			<c:if test="${empty(immunes) }" var="status">
				    	 	<div style="height:120px;padding-left: 12px;" class="module_div" id="module_6_ul">暂无免疫信息</div>
					    </c:if>
				    	<c:if test="${status==false }">
				    		<c:forEach items="${immunes }" var="immune" >
					    		<div  style="text-align: left;height: 30px;line-height: 30px;">
						    		<p>
						    			<span style="color: #E76F16;font-size: 16px;line-height: 32px ">${immune.immunePerson }</span>
						    			 时间:<fmt:formatDate value="${immune.immuneDate }" />	&nbsp;&nbsp;&nbsp;&nbsp;
						    			 药品:&nbsp;&nbsp;&nbsp;&nbsp;
										<c:if test="${!empty(immune.immunedrags) }" var="status">
							    			<c:forEach var="immunedrag" items="${immune.immunedrags }" end="1">
							    				${immunedrag.name }&nbsp;${immunedrag.dose };
							    			</c:forEach>
						    			</c:if>
						    			<c:if test="${status==false }">
						    				无药品信息
						    			</c:if>
 										&nbsp;&nbsp;&nbsp;&nbsp; 备注:&nbsp;&nbsp;&nbsp;&nbsp;${immune.note} 
						    		</p>
					    		</div>
				    		</c:forEach>
				    	 </c:if>
	        		</div>
	        	</div>
	        	<div class="searchContent">
	        		<div class="searchTitle">保健信息</div>
	        		<c:if test="${empty(healthCares) }" var="status">
				    	 	<div style="height:120px;padding-left: 12px;" class="module_div" id="module_6_ul">暂无保健信息</div>
					    </c:if>
				    	<c:if test="${status==false }">
				    		<c:forEach items="${healthCares }" var="healthCare" >
					    		<div  style="text-align: left;height: 30px;line-height: 30px;">
						    		<p>
						    			<span style="color: #E76F16;font-size: 16px;line-height: 32px ">${healthCare.name  }</span>
						    			 开始时间:<fmt:formatDate value="${healthCare.beginDate }" />		&nbsp;&nbsp;&nbsp;&nbsp;
						    			 结束时间:<fmt:formatDate value="${healthCare.endDate }" />		&nbsp;&nbsp;&nbsp;&nbsp;
						    			 药品:&nbsp;&nbsp;&nbsp;&nbsp;
										<c:if test="${!empty(healthCare.healthcaredrags) }" var="status">
							    			<c:forEach var="healthcaredrag" items="${healthCare.healthcaredrags }" end="1">
						    				${healthcaredrag.name }&nbsp;${healthcaredrag.dose };
						    				</c:forEach>
						    			</c:if>
						    			<c:if test="${status==false }">
						    				无药品信息
						    			</c:if>
						    		</p>
					    		</div>
				    		</c:forEach>
				    	 </c:if>
	        		</div>
	        	</div>
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