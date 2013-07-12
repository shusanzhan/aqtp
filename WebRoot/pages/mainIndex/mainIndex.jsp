<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet">
<link href="${ctx }/css/indexTable.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="${ctx }/css/zTreeStyle/zTreeStyle.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx }/widgets/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/ztree/jquery.ztree.core-3.4.min.js"></script>
<script type="text/javascript">
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
		beforeClick: beforeClick,
		onClick: onClick
	}
};

var zNodes =[
	{ id:1, pId:0, name:"批次管理", open:true},
	{ id:11, pId:1, name:"批次管理"},
	{ id:12, pId:1, name:"二维码管理"},
	{ id:3, pId:0, name:"基础数据"},
	{ id:31, pId:3, name:"药品管理"},
	{ id:32, pId:3, name:"饲料管理"},
	{ id:33, pId:3, name:"饲养员管理"},
	{ id:34, pId:3, name:"品系管理"},
	{ id:35, pId:3, name:"评级管理"},
	{ id:4, pId:0, name:"系统设置"},
	{ id:51, pId:4, name:"用户管理"},
	{ id:52, pId:4, name:"登录日志"},
	{ id:53, pId:4, name:"操作日志"}
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
function onClick(event, treeId, treeNode, clickFlag) {
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
</script>
<title>登录系统默认主页</title>
</head>
<body class="bodycolor">
	<div style="width: 208px;float: left;height: 400px;margin-left: 5px;">
	    <ul id="treeDemo" class="ztree"></ul>
	</div> 
	<div style="float: left;width: 80%;margin-top: 5px;">
		<table width="100%" cellspacing="0" cellpadding="0" border="0">
 <tbody><tr>
  <td width="50%" valign="top" id="col_l">

<div class="module listColor" id="module_26">
  <div class="head">
    <h4 class="moduleHeader" id="module_26_head">
      <a class="expand" href="javascript:_resize(26);"></a>      <span onclick="_resize(26);" class="text" id="module_26_text">批次信息</span>
      <span style="cursor: move; width: 520px;" class="title" id="module_26_title"></span>
      <span class="close" id="module_26_op">
        <a class="PageLinkDisable" title="上一页" id="module_26_link_pre" href="javascript:NextPage('26',-1);"><b>▲</b></a>
        <a title="下一页" id="module_26_link_next" href="javascript:NextPage('26',1);"><b>▼</b></a>
        <a href="/general/book/query/">全部</a>&nbsp;&nbsp;&nbsp;<a title="设置" href="javascript:_edit(26);"><img src="/images/pencil.png"></a>&nbsp;<a title="关闭模块" href="javascript:_del(26);"><img src="/images/close_x.png"></a>        </span>
    </h4>
  </div>
  <div style="" class="module_body" id="module_26_body">
    <div style="height:160px;" class="module_div" id="module_26_ul"><ul><ul></ul></ul></div>
  </div>
</div>
<div class="module listColor" id="module_40">
  <div class="head">
    <h4 class="moduleHeader" id="module_40_head">
      <a class="expand" href="javascript:_resize(40);"></a>      <span onclick="_resize(40);" class="text" id="module_40_text">登录日志</span>
      <span style="cursor: move; width: 520px;" class="title" id="module_40_title"></span>
      <span class="close" id="module_40_op">
        <a class="PageLinkDisable" title="上一页" id="module_40_link_pre" href="javascript:NextPage('40',-1);"><b>▲</b></a>
        <a title="下一页" id="module_40_link_next" href="javascript:NextPage('40',1);"><b>▼</b></a>
        <a title="_(&quot;撰写工作日志&quot;)" href="/general/diary/?MAIN_URL=new"><img src="/images/email_edit.png"></a>&nbsp;<a title="设置" href="javascript:_edit(40);"><img src="/images/pencil.png"></a>&nbsp;<a title="关闭模块" href="javascript:_del(40);"><img src="/images/close_x.png"></a>        </span>
    </h4>
  </div>
  <div style="" class="module_body" id="module_40_body">
    <div style="height:160px;" class="module_div" id="module_40_ul"><ul><li><a target="_blank" href="/general/diary/read.php?DIA_ID=2&amp;USER_NAME=系统管理员&amp;FROM_FLAG=enterprise">系统管理员(OA 管理员)2013-06-19 星期三 日志</a> (系统管理员 2013-06-19)</li><li><a target="_blank" href="/general/diary/read.php?DIA_ID=1&amp;USER_NAME=系统管理员&amp;FROM_FLAG=enterprise">系统管理员(OA 管理员)2013-06-19 星期三 日志</a> (系统管理员 2013-06-19)</li><ul></ul></ul></div>
  </div>
</div>
<div class="shadow"></div>
  </td>
  <td valign="top" style="padding-right:10px;" id="col_r">
<div class="module listColor" id="module_6">
  <div class="head">
    <h4 class="moduleHeader" id="module_6_head">
      <a class="expand" href="javascript:_resize(6);"></a>      <span onclick="_resize(6);" class="text" id="module_6_text">二维码</span>
      <span style="cursor: move; width: 165px;" class="title" id="module_6_title"></span>
      <span class="close" id="module_6_op" style="display: none;">
        <a class="PageLinkDisable" title="上一页" id="module_6_link_pre" href="javascript:NextPage('6',-1);"><b>▲</b></a>
        <a title="下一页" id="module_6_link_next" href="javascript:NextPage('6',1);"><b>▼</b></a>
        <a title="撰写" href="/general/email/?MAIN_URL=new"><img src="/images/email_edit.png"></a>&nbsp;<a title="设置" href="javascript:_edit(6);"><img src="/images/pencil.png"></a>&nbsp;<a title="关闭模块" href="javascript:_del(6);"><img src="/images/close_x.png"></a>        </span>
    </h4>
  </div>
  <div style="display: block;" class="module_body" id="module_6_body">
    <div class="moduleType" id="module_6_type">
      <div class="moduleTypeOp" id="module_6_type_op" style="display: none;"><a title="更多类型" href="javascript:ScrollType('6');"><b>▼</b></a></div>
    </div>

    <div style="height:160px;" class="module_div" id="module_6_ul"><ul><li>暂无内部邮件</li><ul>
</ul></ul></div>
  </div>
</div>
<div class="module listColor" id="module_23">
  <div class="head">
    <h4 class="moduleHeader" id="module_23_head">
      <a class="expand" href="javascript:_resize(23);"></a>      <span onclick="_resize(23);" class="text" id="module_23_text">饲养员</span>
      <span style="cursor: move; width: 191px;" class="title" id="module_23_title"></span>
      <span class="close" id="module_23_op" style="display: none;">
        <a class="PageLinkDisable" title="上一页" id="module_23_link_pre" href="javascript:NextPage('23',-1);"><b>▲</b></a>
        <a title="下一页" id="module_23_link_next" href="javascript:NextPage('23',1);"><b>▼</b></a>
        <a title="设置" href="javascript:_edit(23);"><img src="/images/pencil.png"></a>&nbsp;<a title="关闭模块" href="javascript:_del(23);"><img src="/images/close_x.png"></a>        </span>
    </h4>
  </div>
  <div style="" class="module_body" id="module_23_body">
    <div style="height:160px;overflow-y:auto;" class="module_div" id="module_23_ul">
</div>
  </div>
</div>
<div class="shadow"></div>
  </td>
 </tr>
</tbody></table>
	</div>
</body>
</html>