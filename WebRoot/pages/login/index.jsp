<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../commons/taglib.jsp" %>
<html>
<head>
<title>美都后台管理中心</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/css/index.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="${ctx }/widgets/jqueryui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/jqueryui/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/jqueryui/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx }/widgets/jqueryui/jquery.dynatree.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/jqueryui/jquery.effects.bounce.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/jqueryui/jquery.plugins.js"></script>
<script type="text/javascript" src="${ctx }/widgets/jqueryui/jquery.ui.autocomplete.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/jqueryui/sys_function.js"></script>
<script type="text/javascript" src="${ctx }/widgets/jqueryui/utility.js"></script>
<script type="text/javascript" src="${ctx }/widgets/index.js"></script>
<script type="text/javascript" src="${ctx }/widgets/jqueryui/tree.js"></script>
<script type="text/javascript" src="${ctx }/widgets/jqueryui/weather.js"></script>
<script type="text/javascript" src="${ctx }/widgets/jqueryui/sterm.js"></script>
<script type="text/javascript" src="${ctx }/widgets/artDialog/artDialog.js?skin=default"></script>
<script type="text/javascript">
self.moveTo(0,0);
self.resizeTo(screen.availWidth,screen.availHeight);
self.focus();

var loginUser="${sessionScope.user.name }";
/* var loginUserId="${sessionScope.user.id }"; */

var loginUserId="admin";
var bEmailPriv = true;
var bSmsPriv = true;
var bTabStyle = true;
var OA_TIME = new Date();
var bInitWeather = true;
var weatherCity = "45005";
var menuExpand = "10";
var shortcutArray = Array(1,3,42,184,4,147,148,7,8,9,10,16,181,11,130,5,131,132,227,256,83,229,82,182,45,23,194,79,24,20,196,22,105,21,119,35,80,25,81,50,220,49,97,47,98,48,179,63,127,62,128,557,199,556,238,555,251,554,110,553,112,552,53,550,54,221,153,193,217,192,52,190,239,189,240,188,86,187,87,186,88,248,89,244,137,243,138,241,222,597,91,598,92,595,152,594,93,592,94,591,95,588,118,589,237,590,106,587,17,585,18,584,19,583,15,582,76,581,115,209,185,208,235,207,61,205,481,204,482,201,483,202,484,200,485,475,486,474,487,473,488,472,490,470,491,469,492,468,120,467,495,465,496,464,497,463,498,462,499,234,500,233,501,232,503,231,505,230,504,513,506,512,507,39,508,511,515,510,123,129,124,28,125,29,514,509);
var loginUser = {uid:5, user_id:loginUserId, user_name:loginUser};
var logoutText = "轻轻的您走了，正如您轻轻的来……";
var monInterval = {online:120,sms:30};
var ispirit = "";
var statusTextScroll = 60;
var newSmsHtml = "<div onclick='show_sms();' title='点击查看新消息'><img src='/images/sms1.gif' border='0' height='12'> 新消息</div>";
var newSmsSoundHtml = "<object id='sms_sound' classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='/inc/swflash.cab' width='0' height='0'><param name='movie' value='/wav/9.swf'><param name=quality value=high><embed id='sms_sound' src='/wav/9.swf' width='0' height='0' quality='autohigh' wmode='opaque' type='application/x-shockwave-flash' plugspace='http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash'></embed></object>";
var show_ip = 0;
var unit_name = '精战科技';
var orgTree0 = orgTree1 = null;
var jsonURL0 = '';
var jsonURL1 = '';
var user_total_count = "5";
var portalArray = [];
portalArray["0"] = {src:"${ctx}/images/portal/2.png", url:"portal/group/", title:"集团门户", closable:"true"};
portalArray["1"] = {src:"${ctx}/images/portal/1.png", url:"portal/crm/", title:"CRM门户", closable:"true"};
portalArray["2"] = {src:"${ctx}/images/portal/7.png", url:"portal/workflow/", title:"工作流门户", closable:"true"};
portalArray["3"] = {src:"${ctx}/images/portal/3.png", url:"mytable/intel_view/", title:"企业门户", closable:"true"};
portalArray["4"] = {src:"${ctx}/images/portal/4.png", url:"portal/personal/", title:"个人桌面", closable:"true"};
portalArray["5"] = {src:"${ctx}/images/portal/5.png", url:"portal/hrms/", title:"HRMS门户", closable:"true"};
portalArray["6"] = {src:"${ctx}/images/portal/6.png", url:"portal/info/", title:"资讯门户", closable:"true"};
var themeArray = [];
themeArray["10"] = {src:"/images/themeswitch/theme_thumb_10.jpg", title:"宁静的思考"};
themeArray["11"] = {src:"/images/themeswitch/theme_thumb_11.jpg", title:"爱心与希望"};
themeArray["12"] = {src:"/images/themeswitch/theme_thumb_12.jpg", title:"纯朴之木屋"};
var portalLoadArray = ["4","2","0","1"];

//-- 一级菜单 --
var first_array = ["01","10","20","40","50","60","70","80"];

//-- 二级菜单 --
var second_array = [];
second_array["m01"] = ["1","3","7","8","9","10"];
second_array["m10"] = ["12","13","14","15","16","17","18"];
second_array["m20"] = ["21","22","23","24"];
second_array["m30"] = ["31"];
second_array["m40"] = ["41"];
second_array["m50"] = ["51","52","53"];
second_array["m60"] = ["61"];
second_array["m70"] = ["71"];
second_array["m80"] = ["81","82"];

var third_array = [];
third_array["f13"] = ["97","98","114"];
third_array["f21"] = ["211","212","213","214"];
//-- 当前系统主题
var ostheme = 11; 

</script>
<title>Index</title>
</head>
<body>
		<div id="north" style="display: block;">
		<div id="north_left">
         	<table><tbody><tr><td><img src="${ctx }/images/product.png" align="absmiddle"></td></tr></tbody></table>
		</div>
		<div id="north_right">
         	<div id="datetime">
         		<div id="time_area"></div>
         		<div id="date"></div> 
         		<div title="农历 九月廿八" id="mdate">霜降</div>
         	</div>
         	<c:out value=""></c:out>
         	<div id="weather" style="display: block;">
         		<span onclick="$('area_select').style.display='block';$('weather').style.display='none';" title="点击更改城市" class="city">西安</span> 
         		<img align="absMiddle" src="${ctx }/images/1.gif">  
         		<span class="weather">多云</span>
         		<span title="旋转风小于3级" class="temperature">17℃~10℃</span>
         	</div>
         <!-- 天气预报 -->
         <div id="area_select" style="display: none;">
        	 <div>
              <select onchange="Province_onchange(this.options.selectedIndex);" id="province">
              	<option value="选择省">选择省</option>
	          </select>
           </div>
           <div>
              <select id="chinacity">
              	<option value="0">选择城市</option>
              </select>
           </div>
           <div>
              <input type="button" onclick="GetWeather('1');" class="SmallButton" value="确定">
              <input type="button" onclick="$('area_select').style.display='none';$('weather').style.display='block';" class="SmallButton" value="取消">
           </div>
         </div>
         <!-- 天气预报    结束 -->
      </div>     
	</div>
	<!-- 菜单任务栏   开始 -->
	<div id="taskbar">
      <div id="taskbar_left">
         <a hidefocus="hidefocus" id="start_menu" href="javascript:;"></a>
      </div>
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
         <a title="门户切换" hidefocus="hidefocus" href="javascript:;" id="portal"></a> 
         <a title="控制面板" hidefocus="hidefocus" href="javascript:;" id="person_info"></a>
         <a title="更换皮肤" hidefocus="hidefocus" href="javascript:;" id="theme"></a> 
         <a title="注销登录" hidefocus="hidefocus" href="${ctx }/j_spring_security_logout" id="logout">
         <a title="隐藏顶部" hidefocus="hidefocus" href="javascript:;" id="hide_topbar"></a>
      </div>
   </div>  
   <!-- 菜单任务栏   结束-->
   
   <!-- 导航菜单 -->
   <div id="start_menu_panel">
      <div class="panel-head"></div>
      <!-- 登录用户信息 -->
      <div class="panel-user">
         <div class="avatar">
            <img src="/images/avatar/0.gif" align="absmiddle" />
            <div class="status_icon status_icon_2"></div>
            <div id="on_status">
               <a href="javascript:;" status="1" class="on_status_1" hidefocus="hidefocus">在线</a>
               <a href="javascript:;" status="2" class="on_status_2" hidefocus="hidefocus">忙碌</a>
               <a href="javascript:;" status="3" class="on_status_3" hidefocus="hidefocus">离开</a>
            </div>
         </div>
         <div class="name" title="部门：系统处/OA项目组
角色：部门经理">admin</div>
         <div class="tools">
            <a class="logout" href="###" onClick="logout();" hidefocus="hidefocus" title="注销"></a>
            <a class="exit" href="###" onClick="exit();" hidefocus="hidefocus" title="退出"></a>
         </div>
      </div>
      <div class="panel-menu">
         <!-- 一级菜单 -->
         <div id="first_panel">
            <div class="scroll-up"></div>
            <ul id="first_menu"></ul>
            <div class="scroll-down"></div>
         </div>
         <!-- 二级级菜单 -->
         <div id="second_panel">
            <div class="second-panel-head"></div>
            <div class="second-panel-menu"><ul id="second_menu"></ul></div>
            <div class="second-panel-foot"></div>
         </div>
      </div>
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
      
      <div id="overlay_panel"></div>
 </div>
   
   <div id="south">
   <table>
         <tbody><tr>
            <td class="left"><div title="共 4 人，2 人在线" onclick="ViewOnlineUser()" id="online_link">在线<span id="user_count">2</span>人</div></td>
            <td class="left"><div id="new_sms"></div><span style="width:1px;height:1px;" id="new_sms_sound"></span></td>
            <td class="center">
            	<div id="status_text"><br>打造中国OA第一品牌<br>体验科技关怀   共创美好未来<br>登陆http://www.tongda2000.com/download/下载试用完整版<br></div>
            </td>        
            <td class="right" style="cursor:hand;">
            </td>
            <td class="right">
            	<a hidefocus="hidefocus" title="事务提醒" panel="noc_panel" href="javascript:;" class="ipanel_tab" id="nocbox"></a>
               <a hidefocus="hidefocus" title="微讯盒子" panel="smsbox_panel" href="javascript:;" class="ipanel_tab" id="smsbox"></a>
					
               <a hidefocus="hidefocus" title="组织" panel="org_panel" href="javascript:;" class="ipanel_tab" id="org"></a>
            </td>
         </tr>
      </tbody></table>
   </div>
   
</body>
</html>