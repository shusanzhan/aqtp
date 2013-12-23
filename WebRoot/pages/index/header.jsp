<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<div class="contWith top">
		<div class="logo">
		</div>
		<div class="operater">
			<div class="oper">
				<a href="javascript:void(-1)" onclick="AddFavorite(window.location,document.title)">加入收藏</a>
				&nbsp;|&nbsp;
				<a href="javascript:void(-1)" onclick="SetHome(this,window.location)">设为首页</a>
				&nbsp;|&nbsp;
				<a href="">联系我们</a>
			</div>
			<div class="conn">
				<span style="color: #655d5d">联系电话：</span>
				<span style="color: #df0611">400-929-1020</span>
			</div>
			<div></div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="nav">
		<div class="contWith">
			<ul>
				<li><a href="${ctx }/home/home">首页</a></li>
				<c:forEach var="newsType" items="${parents }" end="6">
					<li class="liImg" style="width: 2px;"></li>
					<li><a href="${ctx }/home/intro?newTypePDbid=${newsType.dbid }">${newsType.name }</a></li>
				</c:forEach>
				<%-- <li class="liImg" style="width: 2px;"></li>
				<li><a href="${ctx }/pages/index/silky.jsp">雪峰乌骨鸡</a></li>
				<li class="liImg" style="width: 2px;"></li>
				<li><a href="${ctx }/pages/index/sfchain.jsp">安全生产链</a></li>
				<li class="liImg" style="width: 2px;"></li>
				<li><a href="${ctx }/pages/index/sfcer.jsp">安全认证</a></li>
				<li class="liImg" style="width: 2px;"></li>
				<li><a href="">质量追溯</a></li>
				<li class="liImg" style="width: 2px;"></li>
				<li><a href="${ctx }/pages/index/knowledge.jsp">相关知识</a></li>
				<li class="liImg" style="width: 2px;"></li>
				<li><a href="${ctx }/pages/index/solemnPromise.jsp">郑重承诺</a></li> --%>
			</ul>
		</div>
	</div>
	<script type="text/javascript">
//JavaScript Document
//加入收藏 <a onclick="AddFavorite(window.location,document.title)">加入收藏</a>

function AddFavorite(sURL, sTitle)
{
try
{
    window.external.addFavorite(sURL, sTitle);
}
catch (e)
{
    try
    {
        window.sidebar.addPanel(sTitle, sURL, "");
    }
    catch (e)
    {
        alert("加入收藏失败，请使用Ctrl+D进行添加");
    }
}
}
//设为首页 <a onclick="SetHome(this,window.location)">设为首页</a>
function SetHome(obj,vrl){
    try{
            obj.style.behavior='url(#default#homepage)';obj.setHomePage(vrl);
    }
    catch(e){
            if(window.netscape) {
                    try {
                            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
                    }
                    catch (e) {
                            alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
                    }
                    var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
                    prefs.setCharPref('browser.startup.homepage',vrl);
             }
    }
}
</script>