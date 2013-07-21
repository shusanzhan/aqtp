<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<link href="${ctx }/css/indexTable.css" type="text/css" rel="stylesheet"></link>
</head>
<body>
<table width="100%" cellspacing="0" cellpadding="0" border="0">
			 <tbody><tr>
			  <td width="50%" valign="top" id="col_l">
			
			<div class="module listColor" id="module_26">
			  <div class="head">
			    <h4 class="moduleHeader" id="module_26_head">
			      <a class="expand" href="javascript:_resize(26);"></a>      <span onclick="_resize(26);" class="text" id="module_26_text">批次信息</span>
			      <span style="cursor: move; width: 200px;" class="title" id="module_26_title"></span>
			      <span class="close" id="module_26_op">
					<a href="javascript:void(-1)" onclick="window.location.href='${ctx}/chickenBatch/add'" style="color: white;">添加</a>&nbsp;
					<a href="${ctx}/chickenBatch/queryList" style="color: white;">更多</a>&nbsp;
				  </span>
			    </h4>
			  </div>
			    <div style="" class="module_body" id="module_40_body">
			    <div style="height:200px;" class="module_div" id="module_40_ul" >
			    		<c:forEach items="${chickenBatchs }" var="chickenBatch" end="5">
			    			<div  style="text-align: left;border-bottom:1px dashed  #000000 ;height: 30px;line-height: 30px;">
			    				<span style="width: 100px;float: left;">
					    			${chickenBatch.name }
					    		</span> 
					    			<span style="width: 120px;float: left;">
					    			${chickenBatch.batchNo }
					    		</span>
					    			<span style="width: 100px;float: left;">${chickenBatch.grade.name }</span>
					    			<span style="width: 100px;float: left;">${chickenBatch.breed.name }</span>
					    			<span style="width: 80px;float: left;">
					    			<fmt:formatDate value="${chickenBatch.birthday }" pattern="yyyy-MM-dd"/>
					    		</span>
					    			<span style="width: 40px;float: left;">
					    		<a href="javascript:void(-1)" class="system_right_table_a"
									onclick="window.location.href='${ctx }/chickenBatch/index?dbid=${chickenBatch.dbid}'">明细</a>
					    		</span>
					    	</div>
			    		</c:forEach>
			    </div>
			  </div>
			</div>
			<div class="module listColor" id="module_40">
			  <div class="head">
			    <h4 class="moduleHeader" id="module_40_head">
			      <span onclick="_resize(40);" class="text" id="module_40_text">登录日志</span>
			      <span style="cursor: move; width: 200px;" class="title" id="module_40_title"></span>
			      <span class="close" id="module_40_op" style="line-height: 28px;">
			        <a href="${ctx}/loginLog/queryList" style="color: white;">更多</a>&nbsp;
			      </span>
			    </h4>
			  </div>
			  <div style="" class="module_body" id="module_40_body">
			    <div style="height:200px;" class="module_div" id="module_40_ul" >
			    	<ul>
			    		<c:forEach items="${loginLogs }" var="loginLog" end="5">
				    		<li  style="text-align: left;border-bottom:1px dashed  #000000 ;height: 30px;line-height: 30px; ">
					    		<a target="_blank" href="javascript:void(-1)" style="margin-left:-10px;">${loginLog.userName }</a> 
					    		<span style="padding-left: 20px;">
					    			<fmt:formatDate value="${loginLog.loginDate }" pattern="yyyy-MM-dd HH:mm"/>
					    		</span>
					    		<span style="padding-left: 80px;">${loginLog.ipAddress }</span>
					    		<span style="padding-left: 80px;">${loginLog.loginAddress }</span>
				    		</li>
			    		</c:forEach>
			    	</ul>
			    </div>
			  </div>
			</div>
			<div class="shadow"></div>
			  </td>
			  <td valign="top" style="padding-right:10px;" id="col_r">
			<div class="module listColor" id="module_6">
			   <div class="head">
			    <h4 class="moduleHeader" id="module_26_head">
			      <a class="expand" href="javascript:_resize(26);"></a>      <span onclick="_resize(26);" class="text" id="module_26_text">二维码信息</span>
			      <span style="cursor: move; width: 200px;" class="title" id="module_26_title"></span>
			      <span class="close" id="module_26_op">
					<a href="${ctx}/dimensiona/queryList" style="color: white;">更多</a>&nbsp;
				  </span>
			    </h4>
			  </div>
			    <div style="" class="module_body" id="module_40_body">
			    <div style="height:200px;" class="module_div" id="module_40_ul" >
			    		<c:forEach items="${dimensionas }" var="dimensiona" end="5">
			    			<div  style="text-align: left;border-bottom:1px dashed  #000000 ;height: 30px;line-height: 30px;">
			    				<span style="width: 100px;float: left;">
					    			${dimensiona.name }
					    		</span> 
					    			<span style="width: 100px;float: left;">
					    			<fmt:formatDate value="${dimensiona.createDate }" pattern="yyyy-MM-dd"/>
					    		</span>
					    			<span style="width: 130px;float: left;">${dimensiona.chickenbatch.batchNo }</span>
					    			<span style="width: 60px;float: left;">${dimensiona.quantity }</span>
					    			<span style="width: 100px;float: left;">${dimensiona.user.realName }</span>
					    			<span style="width: 40px;float: left;">
						    		<a href="#" class="system_right_table_a"
										onclick="window.location.href='${ctx}/dimensiona/queryDimCodeList?dbid=${dimensiona.dbid}'">明细</a>
					    		</span>
					    	</div>
			    		</c:forEach>
			    </div>
			  </div>
			</div>
			<div class="module listColor" id="module_23">
			  <div class="head">
			    <h4 class="moduleHeader" id="module_23_head">
			      <a class="expand" href="javascript:_resize(23);"></a>      <span onclick="_resize(23);" class="text" id="module_23_text">饲养员</span>
			      <span style="cursor: move; width: 191px;" class="title" id="module_23_title"></span>
			      <span class="close" id="module_23_op" >
			      	<a  href="javascript:void(-1);" onclick="window.location.href='${ctx}/pages/breeder/add.jsp'" style="color: white;">添加</a>&nbsp;
					<a href="${ctx}/breeder/queryList" style="color: white;">更多</a>&nbsp;
			      </span>
			    </h4>
			  </div>
			   <div style="" class="module_body" id="module_40_body">
			    <div style="height:200px;" class="module_div" id="module_40_ul" >
			    	<ul>
			    		<c:forEach items="${breeders }" var="breeder" end="5">
				    		<div  style="text-align: left;border-bottom:1px dashed  #000000 ;height: 30px;line-height: 30px; ">
				    			<span style="width: 100px;float: left;">
					    			<a target="_blank" href="javascript:void(-1)"  style="">${breeder.name }</a>
					    		</span> 
					    		<span style="width: 100px;float: left;">
					    			<fmt:formatDate value="${breeder.birthday }" pattern="yyyy-MM-dd"/>
					    		</span>
					    		<span style="width: 40px;float: left;">${breeder.sex }</span>
					    		<span style="width: 120px;float: left;">${breeder.graduationSchool }</span>
					    		<span style="width: 120px;float: left;">${breeder.educationalBackground }</span>
					    		<span style="clear: both;"></span>
				    		</div>
			    	</c:forEach>
			    	</ul>
			    </div>
			  </div>
			</div>
			<div class="shadow"></div>
			  </td>
			 </tr>
			</tbody>
			</table>
</body>
</html>
