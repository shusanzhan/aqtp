<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../commons/taglib.jsp" %>
<html>
<head>
<title>质量追溯系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/css/index.css" type="text/css" rel="stylesheet">
<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="${ctx }/css/zTreeStyle/zTreeStyle.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx }/widgets/ztree/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/ztree/jquery.ztree.core-3.4.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/jqueryui/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx }/widgets/artDialog/artDialog.js?skin=default"></script>
<script type="text/javascript" src="${ctx }/widgets/artDialog/plugins/iframeTools.source.js"></script>
<script type="text/javascript">
function resizeLayout()
{
   // 主操作区域高度
   var wWidth = (window.document.documentElement.clientWidth || window.document.body.clientWidth || window.innerHeight);
   var wHeight = (window.document.documentElement.clientHeight || window.document.body.clientHeight || window.innerHeight);
   var nHeight = $('#north').is(':visible') ? $('#north').outerHeight() : 0;
   var fHeight = $('#funcbar').is(':visible') ? $('#funcbar').outerHeight() : 0;
   var cHeight = wHeight - nHeight - fHeight - $('#south').outerHeight() - $('#taskbar').outerHeight();
   $('#center').height(cHeight);
   
   $("#center iframe").css({height: cHeight});

/*
   if(isTouchDevice())
   {
      $('.tabs-panel:visible').height(cHeight);
      if($('.tabs-panel > iframe:visible').height() > cHeight)
         $('.tabs-panel:visible').height($('.tabs-panel > iframe:visible').height());
   }
*/
   //一级标签宽度
   var width = wWidth - $('#taskbar_left').outerWidth() - $('#taskbar_right').outerWidth();
   $('#tabs_container').width(width - $('#tabs_left_scroll').outerWidth() - $('#tabs_right_scroll').outerWidth() - 2);
   $('#taskbar_center').width(width-1);   //-1是为了兼容iPad

   $('#tabs_container').triggerHandler('_resize');
};
function initHideTopbar()
{
   //隐藏topbar事件
   $('#hide_topbar').bind('click', function(){
      $('#north').slideToggle(300, function(){resizeLayout();});
      $(this).toggleClass('up');

      var hidden = $(this).attr('class').indexOf('up') >= 0;
      $.cookie('hideTopbar', (hidden ? '1' : null), {expires:1000, path:'/'});
   });

   if($.cookie('hideTopbar') == '1')
      $('#hide_topbar').triggerHandler('click');
}

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
	{ id:11, pId:1, name:"批次管理","target":"contentUrl",url:"${ctx}/pages/chickenBatch/list.jsp"},
	{ id:12, pId:1, name:"二维码管理","target":"contentUrl",url:"${ctx}/dimensiona/queryList"},
	{ id:3, pId:0, name:"基础数据"},
	{ id:31, pId:3, name:"药品管理","target":"contentUrl",url:"${ctx}/drag/queryList"},
	{ id:32, pId:3, name:"饲料管理","target":"contentUrl",url:"${ctx}/feeder/queryList"},
	{ id:33, pId:3, name:"饲养员管理","target":"contentUrl",url:"${ctx}/breeder/queryList"},
	{ id:34, pId:3, name:"品系管理","target":"contentUrl",url:"${ctx}/breed/queryList"},
	{ id:35, pId:3, name:"评级管理","target":"contentUrl",url:"${ctx}/grade/queryList"},
	{ id:4, pId:0, name:"系统设置"},
	{ id:51, pId:4, name:"用户管理","target":"contentUrl",url:"${ctx}/user/queryList"},
	/* { id:52, pId:4, name:"角色管理","target":"contentUrl",url:"${ctx}/role/queryList"},
	{ id:53, pId:4, name:"权限管理","target":"contentUrl",url:"${ctx}/resource/queryList"},
	{ id:54, pId:4, name:"部门管理","target":"contentUrl",url:"${ctx}/department/list"},
	{ id:57, pId:4, name:"部门管理","target":"contentUrl",url:"${ctx}/enterprise/enterprise"}, */
	{ id:55, pId:4, name:"登录日志","target":"contentUrl",url:"${ctx}/loginLog/queryList"},
	{ id:56, pId:4, name:"操作日志","target":"contentUrl",url:"${ctx}/operateLog/queryList"}
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
	initHideTopbar();
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
<title>Index</title>
</head>
<body>
		<div id="north" style="display: block;">
		<div id="north_left">
         	<table><tbody><tr><td><img src="${ctx }/images/product.png" align="absmiddle"></td></tr></tbody></table>
		</div>
	</div>
	<!-- 菜单任务栏   开始 -->
	<div id="taskbar">
      <div id="taskbar_center" style="width: 935px;">
         <div id="tabs_left_scroll" style="display: none;"></div>
         <div id="tabs_container" style="width: 876px;">
	        <div id="tabs_portal_4" class="selected"> </div>
	        <div id="tabs_portal_2"></div>
	        <div id="tabs_portal_0"></div>
	        <div id="tabs_portal_1"></div>
        </div>
         <div id="tabs_right_scroll" style="display: none;"></div>
      </div>
      <div id="taskbar_right">
         <a title="返回主页" hidefocus="hidefocus" href="${ctx }/main/index" id="theme"></a> 
         <a title="个人设置" hidefocus="hidefocus" href="javascript:;" id="person_info"></a>
         <a title="注销登录" hidefocus="hidefocus" href="${ctx }/j_spring_security_logout" id="logout">
         <a title="隐藏顶部" hidefocus="hidefocus" href="javascript:;" id="hide_topbar"></a>
      </div>
   </div>  
   <!-- 菜单任务栏   结束-->
   
   <!-- 导航菜单 -->
   <div id="start_menu_panel">
      <div class="panel-head"></div>
      <div class="panel-foot"></div>
   </div>
   <div id="overlay_startmenu"></div>
   
    <!-- 查询栏  二级菜单栏 -->
   <div id="funcbar">
      <div id="funcbar_left"></div>
      <div id="funcbar_right">
      </div>
   </div> 
  
 <div id="center" style="height: 780px;">
  <!-- 门户切换 -->
      <div id="portal_panel" class="over-mask-layer">
         <div class="icon"></div>
         <div class="left"></div>
         <div class="center" id="portal_slider"></div>
         <div class="right"></div>
         <div class="close">
            <a class="btn-black-a" href="javascript:;" onClick="openURL(40, '门户设置', 'person_info/?MAIN_URL=portal');" hidefocus="hidefocus">设置</a>
            <a class="btn-black-a" href="javascript:;" onClick="jQuery('#portal').click();" hidefocus="hidefocus">关闭</a>
         </div>
      </div>
 
   <!-- 主题切换 -->
      <div id="theme_panel" class="over-mask-layer">
         <div class="icon"></div>
         <div class="center" id="theme_slider"></div>
         <div class="close">
            <a class="btn-black-a" href="javascript:;" onClick="jQuery('#theme').click();" hidefocus="hidefocus">关闭</a>
         </div>
         <div class="bottom"></div>
      </div>
	   	<div style="height: 100%;background: none repeat scroll 0 0 #F6F7F9;" class="tabs-panel selected" id="tabs_portal_1_panel" >
   		<%-- <iframe frameborder="0" style="width: 100%; " marginwidth="0" marginheight="0" framespacing="0" border="0"  src="${ctx }/mainIndex/mainIndex" allowtransparency="true" name="tabs_portal_1_iframe" id="tabs_portal_1_iframe"></iframe> --%>
   		<div style="width: 208px;float: left;height: 600px;margin-left: 5px;">
	    	<ul id="treeDemo" class="ztree" style="height: 580px;"></ul>
		</div> 
		<div style="float: left;width: 84%;margin-top: 5px;height: 600px;">
			<iframe frameborder="0" style="width: 100%; " marginwidth="0" marginheight="0" framespacing="0" border="0"  src="${ctx }/pages/mainIndex/left.jsp" allowtransparency="true" name="contentUrl" id="contentUrl"></iframe>
		</div>
   		</div>
      <div id="overlay_panel"></div>
 </div>
   
   <div id="south">
   <table>
         <tbody><tr>
            <td class="left" style="color:#FFFFFF !important;font-family: Microsoft Yahei ">
            	<div  id="online_link" style="font-size: 14px;font-weight: bold;text-align: left;float: left;">追踪系统</div>
            	<div  id="online_link" style="font-size: 14px;font-weight: bold;padding-left: 20px;float: left;">V1.0</div>
            	<div style="clear: both;"></div>
            </td>
            <td class="left"><div id="new_sms"></div><span style="width:1px;height:1px;" id="new_sms_sound"></span></td>
            <td class="right" style="cursor:hand;">
            </td>
         </tr>
      </tbody></table>
   </div>
   
</body>
</html>