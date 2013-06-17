<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx }/widgets/ztree/css/demo.css" type="text/css">
<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="${ctx }/widgets/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx }/widgets/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/artDialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx }/widgets/artDialog/plugins/iframeTools.source.js"></script>
<script type="text/javascript" src="${ctx }/widgets/easyvalidator/js/jquery.bgiframe.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/easyvalidator/js/easy_validator.pack.js"></script>
<script type="text/javascript" src="${ctx }/widgets/utile/utile.js"></script>
<script type="text/javascript" src="${ctx }/widgets/ztree/jquery.ztree.all-3.4.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/ztree/jquery.ztree.core-3.4.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/ztree/jquery.ztree.excheck-3.4.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/ztree/jquery.ztree.exedit-3.4.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/ztree/jquery.ztree.exhide-3.4.min.js"></script>
<SCRIPT type="text/javascript">
		<!--
		var setting = {
				view: {
					dblClickExpand: dblClickExpand
				},
				data: {
					simpleData: {
						enable: true
					}
				},
			check: {
				enable: true
			},
			callback: {
				beforeClick: function(treeId,treeNode){
					var zTree = $.fn.zTree.getZTreeObj("treeDemo");
					zTree.checkNode(treeNode, !treeNode.checked, null, true);
					return false;
				},
				onCheck: function(e, treeId, treeNode){
					var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
					nodes = zTree.getCheckedNodes(true),
					v = "";
					val="";
					
					for (var i=0, l=nodes.length; i<l; i++) {
						v += nodes[i].name + ",";
						val+=nodes[i].id+",";
					}
					if (v.length > 0 ) v = v.substring(0, v.length-1);
					if (val.length > 0 ) val = val.substring(0, val.length-1);
					$("#resourceIds").val(val);
				}
			}
		}
	
		function dblClickExpand(treeId, treeNode) {
			return treeNode.level > 0;
		}

		var zTree, rMenu;
		$(document).ready(function(){
				//异步获取部门信息，每当点击右边功能菜单是自动刷新获取部门信息
				$.post("${ctx}/role/roleResourceJson?dbid=${role.dbid}&timeStamp="+new Date(), { } ,function callback(json){  
				if(null!=json&&json!=1){
					$.fn.zTree.init($("#treeDemo"), setting, json);
					zTree = $.fn.zTree.getZTreeObj("treeDemo");
					rMenu = $("#rMenu");
				}else{
					var zNodes =[];
					$.fn.zTree.init($("#treeDemo"), setting, zNodes);
					zTree = $.fn.zTree.getZTreeObj("treeDemo");
					rMenu = $("#rMenu");
					$("#treeDemo").append("<li>暂无部门信息！<br>点击右键添加部门信息！</li>");
				}
			});
		});
		//-->
	</SCRIPT>
	<style type="text/css">
	.ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
	.ztree li ul.level0 {padding:0; background:none;}
	  ul, li{
		margin: 0;padding: 0;border: 0;outline: 0;
	}

	div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #4786C6;text-align: left;padding: 2px;}
	div#rMenu ul li{
		padding: 0;border: 0;outline: 0;
		margin: 1px 0;
		padding: 0 5px;
		cursor: pointer;
		list-style: none outside none;
		background-color: #66A0DF;
		color: white;
	}
	</style>
<title>用户添加</title>
</head>
<body class="bodycolor">
	<table class="TableTop" width="100%">
		<tbody>
			<tr>
				<td class="left"></td>
				<td class="center">资源管理</td>
				<td class="right"></td>
			</tr>
		</tbody>
	</table>
	<br>
<div class="content_wrap" style="margin-left: 24px;margin-top: -12px;height: 480px;">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	<div class="right">
		<ul class="info">
			<li class="title"><h2>操作</h2>
				<form action="" id="frmId" name="frmId" method="post" target="_self">
					<s:token></s:token>
					<input type="hidden" id="resourceIds" name="resourceIds" value="${resourceIds }"></input>
					<input type="hidden" id="roleId" name="roleId" value="${role.dbid}"></input>
					<div class="buttons" style="margin-top: 20px;">
						<a href="javascript:;"	onclick="$.utile.submitForm('frmId','${ctx}/role/saveResource')"	class="ui-state-default">保存</a> 
					    <a href="${ctx }/role/queryList"	onclick="" class="ui-state-default">返回</a>
					</div>
				</form>
			</li>
		</ul>
	</div>
</div>
</body>
</html>