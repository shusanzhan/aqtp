<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<HEAD>
	<TITLE> ZTREE DEMO - left_menu for Outlook</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	
	<link rel="stylesheet" href="${ctx }/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx }/widgets/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/ztree/jquery.ztree.core-3.4.min.js"></script>
	<SCRIPT type="text/javascript">
		<!--
		var curMenu = null, zTree_Menu = null;
		var setting = {
			view: {
				showLine: false,
				showIcon: false,
				selectedMulti: false,
				dblClickExpand: false,
				addDiyDom: addDiyDom
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick
			}
		};

		var zNodes =[
			{ id:1, pId:0, name:"文件夹", open:true},
			{ id:11, pId:1, name:"收件箱"},
			{ id:12, pId:1, name:"垃圾邮件"},
			{ id:13, pId:1, name:"草稿"},
			{ id:14, pId:1, name:"已发送邮件"},
			{ id:15, pId:1, name:"已删除邮件"},
			{ id:3, pId:0, name:"快速视图"},
			{ id:31, pId:3, name:"文档"},
			{ id:32, pId:3, name:"照片"}
		];

		function addDiyDom(treeId, treeNode) {
			var spaceWidth = 5;
			var switchObj = $("#" + treeNode.tId + "_switch"),
			icoObj = $("#" + treeNode.tId + "_ico");
			switchObj.remove();
			icoObj.before(switchObj);

			if (treeNode.level > 1) {
				var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
				switchObj.before(spaceStr);
			}
		}

		function beforeClick(treeId, treeNode) {
			if (treeNode.level == 0 ) {
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.expandNode(treeNode);
				return false;
			}
			return true;
		}

		$(document).ready(function(){
			var treeObj = $("#treeDemo");
			$.fn.zTree.init(treeObj, setting, zNodes);
			zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
			curMenu = zTree_Menu.getNodes()[0].children[0].children[0];
			zTree_Menu.selectNode(curMenu);

			treeObj.hover(function () {
				if (!treeObj.hasClass("showIcon")) {
					treeObj.addClass("showIcon");
				}
			}, function() {
				treeObj.removeClass("showIcon");
			});
		});
		//-->
	</SCRIPT>
	<style type="text/css">

	</style>
 </HEAD>

<BODY>
<h1>OutLook 样式的左侧菜单</h1>
<h6>[ 文件路径: super/left_menuForOutLook.html ]</h6>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	
	
</div>
</BODY>
</HTML>
