<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>批次综合管理主页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${ctx }/css/indexTable.css" type="text/css" rel="stylesheet"></link>
<link href="${ctx }/css/list.css" type="text/css" rel="stylesheet"></link>
<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet"></link>
<script type="text/javascript" src="${ctx }/widgets/jqueryui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/utile/utile.js"></script>
<script type="text/javascript" src="${ctx }/widgets/artDialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx }/widgets/artDialog/plugins/iframeTools.source.js"></script>
</head>
<body>
<table class="TableTop" width="100%">
		<tbody>
			<tr>
				<td class="left"></td>
				<td class="center">批次综合页</td>
				<td class="right"></td>
			</tr>
		</tbody>
	</table>
<table width="92%" cellspacing="0" cellpadding="0" border="0"  style="margin-top: 20px;margin: 0 auto;" align="center">
	<tr style="border-bottom: 1px solid black;" height="40">
		<td width="80" align="right">名称：</td><td width="120">${chickenBatch.name }</td>
		<td width="80" align="right">批次：</td><td width="120">${chickenBatch.batchNo }</td>
		<td  width="80" align="right">品级：</td><td width="120">${chickenBatch.grade.name }</td>
		<td  width="80" align="right">品系：</td><td width="120">${chickenBatch.breed.name }</td>
		<td  width="80" align="center"><a href="${ctx }/chickenBatch/queryList" style="color: blue;">返回</a></td>
	</tr>
	<tr style="border-bottom: 1px solid black;" height="40">
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

<table width="100%" cellspacing="0" cellpadding="0" border="0"  style="margin-top: 20px;margin-bottom: 20px;">
			 <tbody><tr>
			  <td width="50%" valign="top" id="col_l">
			<div class="module listColor" id="module_23">
			  <div class="head">
			    <h4 class="moduleHeader" id="module_23_head">
			      <a class="expand" href="javascript:_resize(23);"></a>      <span onclick="_resize(23);" class="text" id="module_23_text">饲料信息</span>
			      <span style="cursor: move; width: 191px;" class="title" id="module_23_title"></span>
			      <span class="close" id="module_23_op" >
			      	<a  href="javascript:void(-1);" onclick="$.utile.openDialog('${ctx}/chickenBatch/addFeeder?chickenBatchDbid=${chickenBatch.dbid }','添加饲料信息',600,320)" style="color: white;">添加</a>&nbsp;
			      </span>
			    </h4>
			  </div>
			   <div  class="module_body" id="module_40_body">
			    <div  class="module_div" id="module_40_ul" style="margin-bottom: 12px;overflow-y: scroll;height: 120px;">
				    <c:if test="${empty(feedfeeders) }" var="status">
				    	 <div style="height:120px;padding-left: 12px;" class="module_div" id="module_6_ul">暂无饲料信息</div>
				    </c:if>
			    	<c:if test="${status==false }">
			    		<c:forEach items="${feedfeeders }" var="feedfeeder" >
				    		<div  style="text-align: left;border-bottom:1px dashed  #000000 ;height: 30px;line-height: 30px;">
				    			<span style="width: 100px;float: left;">
					    			<a target="" href="javascript:void(-1)" onclick="$.utile.openDialog('${ctx}/chickenBatch/showFeeder?chickenBatchDbid=${chickenBatch.dbid }&dbid=${feedfeeder.feeder.dbid }','查看饲料基本信息',720,500)" style="">${feedfeeder.name }</a>
					    		</span> 
					    		<span style="width: 185px;float: left;">
					    			<c:if test="${fn:length(feedfeeder.feeder.elementsPercentage)>10 }" var="status">
					    				${fn:substring(feedfeeder.feeder.elementsPercentage,0,10) }...
					    			</c:if>
					    			<c:if test="${status==false }">
					    				${feedfeeder.feeder.elementsPercentage }
					    			</c:if>
					    			</span>
					    		<span style="width: 200px;float: left;">
					    			<c:if test="${fn:length(feedfeeder.feeder.note)>10 }" var="status">
					    				${fn:substring(feedfeeder.feeder.note,0,10) }...
					    			</c:if>
					    			<c:if test="${status==false }">
					    				${feedfeeder.feeder.note }
					    			</c:if>
					    		</span>
					    		<span style="width: 40px;float: left;">
					    			<a target="" href="javascript:void(-1)"  style="" onclick="$.utile.deleteById('${ctx }/chickenBatch/deleteFeeder?dbids=${feedfeeder.dbid}&chickenBatchDbid=${chickenBatch.dbid }')">删除</a>
					    		</span>
					    		<span style="clear: both;"></span>
				    		</div>
			    		</c:forEach>
			    	</c:if>
			    	<div  style="text-align: left;height: 30px;line-height: 30px;">
			    	</div>
			    </div>
			    <div style="clear: both;"></div>
			  </div>
			</div>
			<div class="module listColor" id="module_40">
			   <div class="head">
			    <h4 class="moduleHeader" id="module_23_head">
			      <a class="expand" href="javascript:_resize(23);"></a>      <span onclick="_resize(23);" class="text" id="module_23_text">检疫证明</span>
			      <span style="cursor: move; width: 191px;" class="title" id="module_23_title"></span>
			      <span class="close" id="module_23_op" >
			      	<a  href="javascript:void(-1);" onclick="$.utile.openDialog('${ctx}/quarantineCertificate/add?chickenBatchDbid=${chickenBatch.dbid }','添加检疫证明信息',740,498)" style="color: white;">添加</a>&nbsp;
			      </span>
			    </h4>
			  </div>
			   <div  class="module_body" id="module_40_body">
			    <div  class="module_div" id="module_40_ul" style="margin-bottom: 12px;overflow-y: scroll;height: 120px;">
				    <c:if test="${empty(quarantinecertificates) }" var="status">
				    	 <div style="height:120px;padding-left: 12px;" class="module_div" id="module_6_ul">暂无检疫证明信息</div>
				    </c:if>
			    	<c:if test="${status==false }">
			    		<c:forEach items="${quarantinecertificates }" var="quarantineCertificate" >
				    		<div  style="text-align: left;border-bottom:1px dashed  #000000 ;height: 30px;line-height: 30px;">
				    			<span style="width: 100px;float: left;">
					    			<a target="" href="javascript:void(-1)" onclick="$.utile.openDialog('${ctx}/quarantineCertificate/showQuarantineCertificat?chickenBatchDbid=${chickenBatch.dbid }&dbid=${quarantineCertificate.dbid }','查看检疫证明信息',720,500)" style="">${quarantineCertificate.title }</a>
					    		</span> 
					    		<span style="width: 80px;float: left;">
					    		 	<fmt:formatDate value="${quarantineCertificate.awardDate }" />	
					    		</span>
					    		<span style="width: 120px;float: left;">
					    			<c:if test="${fn:length(quarantineCertificate.awardGroup)>8 }" var="status">
					    				${fn:substring(quarantineCertificate.awardGroup,0,8) }...
					    			</c:if>
					    			<c:if test="${status==false }">
					    				${quarantineCertificate.awardGroup }
					    			</c:if>
					    		</span>
					    		<span style="width: 160px;float: left;">
					    			<c:if test="${fn:length(quarantineCertificate.note)>8 }" var="status">
					    				${fn:substring(quarantineCertificate.note,0,8) }...
					    			</c:if>
					    			<c:if test="${status==false }">
					    				${quarantineCertificate.note}&nbsp;
					    			</c:if>
					    		</span>
					    		<span style="width: 60px;float: left;">
					    			<a target="" href="javascript:void(-1)"  style="" onclick="$.utile.openDialog('${ctx }/quarantineCertificate/edit?dbid=${quarantineCertificate.dbid}&chickenBatchDbid=${chickenBatch.dbid }','编辑检疫证明信息',740,498)">编辑</a>
					    			<a target="" href="javascript:void(-1)"  style="" onclick="$.utile.deleteById('${ctx }/quarantineCertificate/delete?dbids=${quarantineCertificate.dbid}&chickenBatchDbid=${chickenBatch.dbid }')">删除</a>
					    		</span>
					    		<span style="clear: both;"></span>
				    		</div>
			    		</c:forEach>
			    	</c:if>
			    	<div  style="text-align: left;height: 30px;line-height: 30px;">
			    	</div>
			    </div>
			    <div style="clear: both;"></div>
			  </div>
			</div>
			<div class="module listColor" id="module_40">
			    <div class="head">
			    <h4 class="moduleHeader" id="module_23_head">
			      <a class="expand" href="javascript:_resize(23);"></a>      <span onclick="_resize(23);" class="text" id="module_23_text">免疫信息</span>
			      <span style="cursor: move; width: 191px;" class="title" id="module_23_title"></span>
			      <span class="close" id="module_23_op" >
			      	<a  href="javascript:void(-1);" onclick="$.utile.openDialog('${ctx}/immune/add?chickenBatchDbid=${chickenBatch.dbid }','添加免疫信息',740,498)" style="color: white;">添加</a>&nbsp;
			      </span>
			    </h4>
			  </div>
			   <div  class="module_body" id="module_40_body">
			    <div  class="module_div" id="module_40_ul" style="margin-bottom: 12px;overflow-y: scroll;height: 120px;">
				    <c:if test="${empty(immunes) }" var="status">
				    	 <div style="height:120px;padding-left: 12px;" class="module_div" id="module_6_ul">暂无免疫信息</div>
				    </c:if>
			    	<c:if test="${status==false }">
			    		<c:forEach items="${immunes }" var="immune" >
				    		<div  style="text-align: left;border-bottom:1px dashed  #000000 ;height: 30px;line-height: 30px;">
				    			<span style="width: 100px;float: left;">
					    			<a target="" href="javascript:void(-1)" onclick="$.utile.openDialog('${ctx}/immune/showImmune?chickenBatchDbid=${chickenBatch.dbid }&dbid=${immune.dbid }','查看免疫信息',720,500)" style="">${immune.immunePerson }</a>
					    		</span> 
					    		<span style="width: 80px;float: left;">
					    		 	<fmt:formatDate value="${immune.immuneDate }" />	
					    		</span>
					    		<span style="width: 160px;float: left;">
					    			<c:if test="${!empty(immune.immunedrags) }" var="status">
						    			<c:forEach var="immunedrag" items="${immune.immunedrags }" end="1">
						    				${immunedrag.name }&nbsp;${immunedrag.dose };
						    			</c:forEach>
					    			</c:if>
					    			<c:if test="${status==false }">
					    				无药品信息
					    			</c:if>
					    		</span>
					    		<span style="width: 120px;float: left;">
					    			<c:if test="${fn:length(immune.note)>10 }" var="status">
					    				${fn:substring(immune.note,0,10) }...
					    			</c:if>
					    			<c:if test="${status==false }">
					    				${immune.note}&nbsp;
					    			</c:if>
					    		</span>
					    		<span style="width: 60px;float: left;">
					    			<a target="" href="javascript:void(-1)"  style="" onclick="$.utile.openDialog('${ctx }/immune/edit?dbid=${immune.dbid}&chickenBatchDbid=${chickenBatch.dbid }','编辑免疫信息',740,498)">编辑</a>
					    			<a target="" href="javascript:void(-1)"  style="" onclick="$.utile.deleteById('${ctx }/immune/delete?dbids=${immune.dbid}&chickenBatchDbid=${chickenBatch.dbid }')">删除</a>
					    		</span>
					    		<span style="clear: both;"></span>
				    		</div>
			    		</c:forEach>
			    	</c:if>
			    	<div  style="text-align: left;height: 30px;line-height: 30px;">
			    	</div>
			    </div>
			    <div style="clear: both;"></div>
			  </div>
			</div>
			<div class="shadow"></div>
			  </td>
			  <td valign="top" style="padding-right:10px;" id="col_r">
			<div class="module listColor" id="module_6">
			   <div class="head">
			    <h4 class="moduleHeader" id="module_23_head">
			      <a class="expand" href="javascript:_resize(23);"></a>      <span onclick="_resize(23);" class="text" id="module_23_text">饲养员信息</span>
			      <span style="cursor: move; width: 191px;" class="title" id="module_23_title"></span>
			      <span class="close" id="module_23_op" >
			      	<a  href="javascript:void(-1);" onclick="$.utile.openDialog('${ctx}/chickenBatch/addBreeder?chickenBatchDbid=${chickenBatch.dbid }','添加饲养员信息',600,320)" style="color: white;">添加</a>&nbsp;
			      </span>
			    </h4>
			  </div>
			   <div  class="module_body" id="module_40_body">
			    <div  class="module_div" id="module_40_ul" style="margin-bottom: 12px;overflow-y: scroll;height: 120px;">
				    <c:if test="${empty(breaderBreeds) }" var="status">
				    	 <div style="height:120px;padding-left: 12px;" class="module_div" id="module_6_ul">暂无饲养员信息</div>
				    </c:if>
			    	<c:if test="${status==false }">
			    		<c:forEach items="${breaderBreeds }" var="breaderBreed" >
				    		<div  style="text-align: left;border-bottom:1px dashed  #000000 ;height: 30px;line-height: 30px;">
				    			<span style="width: 100px;float: left;">
					    			<a target="" href="javascript:void(-1)" 
					    				onclick="$.utile.openDialog('${ctx}/chickenBatch/showBreeder?chickenBatchDbid=${chickenBatch.dbid }&dbid=${breaderBreed.breeder.dbid }','查看饲料基本信息',720,450)" >
					    				${breaderBreed.name }
					    			</a>
					    		</span> 
					    		<span style="width: 80px;float: left;">
					    		 	<fmt:formatDate value="${breaderBreed.breeder.birthday }" />	
					    		</span>
					    		<span style="width: 40px;float: left;">
					    		 	${breaderBreed.breeder.sex }	
					    		</span>
					    		<span style="width: 130px;float: left;">
					    			<c:if test="${fn:length(breaderBreed.breeder.graduationSchool)>10 }" var="status">
					    				${fn:substring(breaderBreed.breeder.graduationSchool,0,10) }...
					    			</c:if>
					    			<c:if test="${status==false }">
					    				${breaderBreed.breeder.graduationSchool }&nbsp;
					    			</c:if>
					    		</span>
					    		<span style="width: 120px;float: left;">
					    		 	${breaderBreed.breeder.educationalBackground }	
					    		</span>
					    		<span style="width: 40px;float: left;">
					    			<a target="" href="javascript:void(-1)"  style="" onclick="$.utile.deleteById('${ctx }/chickenBatch/deleteBreeder?dbids=${breaderBreed.dbid}&chickenBatchDbid=${chickenBatch.dbid }')">删除</a>
					    		</span>
					    		<span style="clear: both;"></span>
				    		</div>
			    		</c:forEach>
			    	</c:if>
			    	<div  style="text-align: left;height: 30px;line-height: 30px;">
			    	</div>
			    </div>
			    <div style="clear: both;"></div>
			  </div>
			</div>
			<div class="module listColor" id="module_23">
			  <div class="head">
			    <h4 class="moduleHeader" id="module_23_head">
			      <a class="expand" href="javascript:_resize(23);"></a>      <span onclick="_resize(23);" class="text" id="module_23_text">二维码信息</span>
			      <span style="cursor: move; width: 191px;" class="title" id="module_23_title"></span>
			      <span class="close" id="module_23_op" >
			      	<a  href="javascript:void(-1);"  onclick="$.utile.openDialog('${ctx}/dimensiona/bathNoEdit?chickenBatchDbid=${chickenBatch.dbid }','添加二维码',720,280)"
			      	 style="color: white;">添加</a>&nbsp;
			      </span>
			    </h4>
			  </div>
			   <div  class="module_body" id="module_40_body">
			    <div  class="module_div" id="module_40_ul" style="margin-bottom: 12px;overflow-y: scroll;height: 120px;">
				    <c:if test="${empty(dimensionas) }" var="status">
				    	 <div style="height:120px;padding-left: 12px;" class="module_div" id="module_6_ul">暂无二维码信息</div>
				    </c:if>
			    	<c:if test="${status==false }">
			    		<c:forEach items="${dimensionas }" var="dimensiona" >
				    		<div  style="text-align: left;border-bottom:1px dashed  #000000 ;height: 30px;line-height: 30px;">
				    			<span style="width: 120px;float: left;">
					    			<a target="" href="javascript:void(-1)" 
					    				onclick="window.open('${ctx}/dimensiona/queryDimCodeList?dbid=${dimensiona.dbid}&status=chickenIndex')" >
					    				${dimensiona.name }
					    			</a>
					    		</span> 
					    		<span style="width: 110px;float: left;">
					    		 	<fmt:formatDate value="${dimensiona.createDate }" />	
					    		</span>
					    		<span style="width: 60px;float: left;">
					    		 	${dimensiona.quantity }	
					    		</span>
					    		<span style="width: 100px;float: left;">
					    		 	${dimensiona.user.realName }	
					    		</span>
					    		<span style="width: 120px;float: left;">
					    			<a target="" href="javascript:void(-1)"  style="" onclick="window.open('${ctx}/dimensiona/queryDimCodeList?dbid=${dimensiona.dbid}&status=chickenIndex')">明细</a>
					    			<a target="" href="javascript:void(-1)"  style="" onclick="$.utile.deleteById('${ctx }/dimensiona/delete?dbids=${dimensiona.dbid}&chickenBatchDbid=${chickenBatch.dbid }&addMethod=1')">删除</a>
					    			<a target="" href="javascript:void(-1)"  style="" onclick="window.location.href='${ctx}/dimensiona/exportPDF?dbid=${dimensiona.dbid}'">导出PDF</a>
					    			
					    		</span>
					    		<span style="clear: both;"></span>
				    		</div>
			    		</c:forEach>
			    	</c:if>
			    	<div  style="text-align: left;height: 30px;line-height: 30px;">
			    	</div>
			    </div>
			    <div style="clear: both;"></div>
			  </div>
			</div>
			<div class="module listColor" id="module_23">
			 <div class="head">
			    <h4 class="moduleHeader" id="module_23_head">
			      <a class="expand" href="javascript:_resize(23);"></a>      <span onclick="_resize(23);" class="text" id="module_23_text">保健信息</span>
			      <span style="cursor: move; width: 191px;" class="title" id="module_23_title"></span>
			      <span class="close" id="module_23_op" >
			      	<a  href="javascript:void(-1);"  onclick="$.utile.openDialog('${ctx}/healthCare/add?chickenBatchDbid=${chickenBatch.dbid }&chickenBatchDbid=${chickenBatch.dbid }','保健信息',720,500)"
			      	 style="color: white;">添加</a>&nbsp;
			      </span>
			    </h4>
			  </div>
			   <div  class="module_body" id="module_40_body">
			    <div  class="module_div" id="module_40_ul" style="margin-bottom: 12px;overflow-y: scroll;height: 120px;">
				    <c:if test="${empty(healthCares) }" var="status">
				    	 <div style="height:120px;padding-left: 12px;" class="module_div" id="module_6_ul">暂无保健信息</div>
				    </c:if>
			    	<c:if test="${status==false }">
			    		<c:forEach items="${healthCares }" var="healthCare" >
				    		<div  style="text-align: left;border-bottom:1px dashed  #000000 ;height: 30px;line-height: 30px;">
				    			<span style="width: 100px;float: left;">
					    			<a target="" href="javascript:void(-1)" 
					    				onclick="$.utile.openDialog('${ctx}/healthCare/showHealthCare?dbid=${healthCare.dbid}&chickenBatchDbid=${chickenBatch.dbid }','查看保健信息',740,498)" >
					    				${healthCare.name }
					    			</a>
					    		</span> 
					    		<span style="width: 100px;float: left;">
					    		 	<fmt:formatDate value="${healthCare.beginDate }" />	
					    		</span>
					    		<span style="width: 100px;float: left;">
					    		 	${healthCare.endDate }	
					    		</span>
					    		<span style="width: 135px;float: left;">
					    		 <c:if test="${!empty(healthCare.healthcaredrags) }" var="status">
						    			<c:forEach var="healthcaredrag" items="${healthCare.healthcaredrags }" end="1">
						    				${healthcaredrag.name }&nbsp;${healthcaredrag.dose };
						    			</c:forEach>
					    			</c:if>
					    			<c:if test="${status==false }">
					    				无药品信息
					    			</c:if>
					    		</span>
					    		<span style="width: 80px;float: left;">
					    			<a target="" href="javascript:void(-1)"  style="" onclick="$.utile.openDialog('${ctx }/healthCare/edit?dbid=${healthCare.dbid}&chickenBatchDbid=${chickenBatch.dbid }','编辑保健信息',740,498)">编辑</a>
					    			<a target="" href="javascript:void(-1)"  style="" onclick="$.utile.deleteById('${ctx }/healthCare/delete?dbids=${healthCare.dbid}&chickenBatchDbid=${chickenBatch.dbid }')">删除</a>
					    		</span>
					    		<span style="clear: both;"></span>
				    		</div>
			    		</c:forEach>
			    	</c:if>
			    	<div  style="text-align: left;height: 30px;line-height: 30px;">
			    	</div>
			    </div>
			    <div style="clear: both;"></div>
			  </div>
			</div>
			<div class="shadow"></div>
			  </td>
			 </tr>
			</tbody>
			</table>
</body>
</html>
