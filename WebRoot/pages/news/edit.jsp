<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/css/list.css" type="text/css" rel="stylesheet">
<link href="${ctx }/css/style.css" type="text/css" rel="stylesheet">
<link  href="${ctx }/widgets/easyvalidator/css/validate.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx }/css/zTreeStyle/zTreeStyleSrc.css" type="text/css">
<script type="text/javascript" src="${ctx }/widgets/jqueryui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/utile/utile.js"></script>
<script type="text/javascript" src="${ctx }/widgets/easyvalidator/js/jquery.bgiframe.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/easyvalidator/js/easy_validator.pack.js"></script>
<script type="text/javascript" src="${ctx }/widgets/ckeditor/ckeditor.js"></script>
<script type="text/javascript"	src="${ctx }/widgets/SWFUpload/swfupload.js"></script>
<script type="text/javascript"	src="${ctx }/widgets/SWFUpload/plugins/swfupload.queue.js"></script>
<script type="text/javascript"	src="${ctx }/widgets/SWFUpload/plugins/swfupload.speed.js"></script>
<script type="text/javascript"	src="${ctx }/widgets/SWFUpload/js/fileupload.handlers.js"></script>
<script type="text/javascript" src="${ctx }/widgets/utile/fileUpload.js"></script>
<script type="text/javascript" src="${ctx }/widgets/ztree/jquery.ztree.core-3.4.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/ztree/jquery.ztree.excheck-3.4.min.js"></script>
<title>添加新闻</title>
<style type="text/css">
ul.ztree {
    background: none repeat scroll 0 0 #F0F6E4;
    border: 1px solid #617775;
    height: 200px;
    margin-top: 10px;
    overflow-x: auto;
    overflow-y: scroll;
    width: 320px;
}
</style>
<script type="text/javascript">
	function setTitlePicture(){
		var checkeds = $("input[type='checkbox'][name='news.isTitlePicture']");
		var length = 0;
		$.each(checkeds, function(i, checkbox) {
			if (checkbox.checked) {
				length++;
			}
		})
		if (length <= 0) {
			$("#isTitlePictureTr").hide();
		}else{
			$("#isTitlePictureTr").show();
		}
	}
	function bannerPictureTr(){
		var checkeds = $("input[type='checkbox'][name='news.isBannerPicture']");
		var length = 0;
		$.each(checkeds, function(i, checkbox) {
			if (checkbox.checked) {
				length++;
			}
		})
		if (length <= 0) {
			$("#isBannerPictureTr").hide();
		}else{
			$("#isBannerPictureTr").show();
		}
	}
</script>
<script type="text/javascript">
var upload1,upload2;

window.onload = function() {
	upload1 = new SWFUpload(
			{
				// Backend Settings
				upload_url : "${ctx}/swfUpload/uploadFile",
				post_params : {
					"PHPSESSID" : "6a95034fff6ba3a6aa8a990ca3af42ee","userId":"${sessionScope.user.dbid}"
				},
				//上传文件的名称
				file_post_name : "file",

				// File Upload Settings
				file_size_limit : "5242880", // 200MB
				file_types : "*.jpg;*.gif;*.png",
				file_types_description : "All Files",
				file_upload_limit : "5",
				file_queue_limit : "0",

				// Event Handler Settings (all my handlers are in the Handler.js file)
				file_dialog_start_handler : fileDialogStart,
				file_queued_handler : fileQueued,
				file_queue_error_handler : fileQueueErrorHandler,
				file_dialog_complete_handler : fileDialogComplete,
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess,
				upload_complete_handler : uploadComplete,

				// Button Settings
				button_image_url : "${ctx}/widgets/SWFUpload/images/XPButtonUploadText_61x22.png",
				button_placeholder_id : "spanButtonPlaceholder1",
				button_width : 61,
				button_height : 22,

				// Flash Settings
				flash_url : "${ctx}/widgets/SWFUpload/Flash/swfupload.swf",

				custom_settings : {
					progressTarget : "uploadFileContent",
					cancelButtonId : "btnCancel1",
					titlePicture : "titlePicture",
					fileUploadImage : "fileUploadImage"
				},

				// Debug Settings
				debug : false
			});
	upload2 = new SWFUpload(
			{
				// Backend Settings
				upload_url : "${ctx}/swfUpload/uploadFile",
				post_params : {
					"PHPSESSID" : "6a95034fff6ba3a6aa8a990ca3af42ee","userId":"${sessionScope.user.dbid}"
				},
				//上传文件的名称
				file_post_name : "file",

				// File Upload Settings
				file_size_limit : "5242880", // 200MB
				file_types : "*.jpg;*.gif;*.png",
				file_types_description : "All Files",
				file_upload_limit : "5",
				file_queue_limit : "0",

				// Event Handler Settings (all my handlers are in the Handler.js file)
				file_dialog_start_handler : fileDialogStart,
				file_queued_handler : fileQueued,
				file_queue_error_handler : fileQueueErrorHandler,
				file_dialog_complete_handler : fileDialogComplete,
				upload_start_handler : uploadStart,
				upload_progress_handler : uploadProgress,
				upload_error_handler : uploadError,
				upload_success_handler : uploadSuccess,
				upload_complete_handler : uploadComplete,

				// Button Settings
				button_image_url : "${ctx}/widgets/SWFUpload/images/XPButtonUploadText_61x22.png",
				button_placeholder_id : "spanButtonPlaceholder2",
				button_width : 61,
				button_height : 22,

				// Flash Settings
				flash_url : "${ctx}/widgets/SWFUpload/Flash/swfupload.swf",

				custom_settings : {
					progressTarget : "uploadFileContent2",
					cancelButtonId : "btnCancel2",
					titlePicture : "bannerPicture",
					fileUploadImage : "bannerPictureUploadImage"
				},

				// Debug Settings
				debug : false
			});

}
</script>
</head>
<body class="bodycolor">
	
	<br>
	<form action="" name="frmId" id="frmId" method="post" style="margin-bottom: 40px;" target="_self">
		<s:token></s:token>
		<input type="hidden" name="news.dbid" id="dbid" value="${news.dbid }">
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 92%;">
			<tr height="42">
				<td class="formTableTdLeft">类型:&nbsp;</td>
				<td >
					<input id="newTypeName" type="text" readonly="readonly" name="newTypeName" value="${news.newstype.name }"  class="input-large field" onclick="showMenu();" />
					<input id="newTypeDbid" type="hidden" readonly="readonly" name="newTypeDbid" value="${news.newstype.dbid }"  />
					&nbsp;<a id="menuBtn" href="#" onclick="showMenu(); return false;" class="ui-state-default">选择新闻类型</a>
				</td>
				
			</tr>
			<tr>
			<td class="formTableTdLeft">标题:&nbsp;</td>
				<td ><input type="text" name="news.title" id="title"
					value="${news.title }" class="input-large field" title="标题"  checkType="string,1,50" tip="标题不能为空"><span style="color: red;">*</span>
					<input type="checkbox" id="isTitlePicture" value="true" name="news.isTitlePicture" onclick="setTitlePicture();" ${news.isTitlePicture==true?'checked="checked"':'' } ><label for="isTitlePicture">启用标题图片</label>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" id="isBannerPicture" value="true" name="news.isBannerPicture" onclick="bannerPictureTr();" ${news.isBannerPicture==true?'checked="checked"':'' } ><label for="isBannerPicture">启用幻灯片图片</label>
				</td>
			</tr>
			<c:if test="${news.isTitlePicture==true }" var="status">
				<tr id="isTitlePictureTr" >
				<td class="formTableTdLeft">标题图片:&nbsp;</td>
					<td >
						<table  border="0" cellpadding="0" cellspacing="0" style="width: 340px;" height="120">
						<tr>
							<td height="120" width="200">
								<input type="hidden" name="news.titlePicture" id="titlePicture"
								value="${news.titlePicture }" class="input-large field" title="标题"   >
								<img alt="" id="fileUploadImage" src="${news.titlePicture }" width="200" height="120">
							</td>
							<td height="40" width="140">
								<div id="div1">
									<div style="padding-left: 5px;">
									<span id="spanButtonPlaceholder1"></span> <br />
								</div>
								<div id="uploadFileContent" class="uploadFileContent"></div>
								</div>
							</td>
						</tr>
				</table>
					</td>
					
				</tr>
			</c:if>
			<c:if test="${status==false }">
				<tr id="isTitlePictureTr"  style="display: none;">
				<td class="formTableTdLeft">标题图片:&nbsp;</td>
					<td >
						<table  border="0" cellpadding="0" cellspacing="0" style="width: 340px;" height="120">
						<tr>
							<td height="120" width="200">
								<input type="hidden" name="news.titlePicture" id="titlePicture"
								value="${news.titlePicture }" class="input-large field" title="标题"   >
								<img alt="" id="fileUploadImage" src="${news.titlePicture }" width="200" height="120">
							</td>
							<td height="40" width="140">
								<div id="div1">
									<div style="padding-left: 5px;">
									<span id="spanButtonPlaceholder1"></span> <br />
								</div>
								<div id="uploadFileContent" class="uploadFileContent"></div>
								</div>
							</td>
						</tr>
				</table>
					</td>
					
			</tr>
		</c:if>
		<c:if test="${news.isBannerPicture==true }" var="status">
			<tr id="isBannerPictureTr">
				<td class="formTableTdLeft">幻灯片图片:&nbsp;</td>
				<td >
					<table  border="0" cellpadding="0" cellspacing="0" style="width: 340px;" height="120">
						<tr>
							<td height="120" width="200">
								<input type="hidden" name="news.bannerPicture" id="bannerPicture"
								value="${news.bannerPicture }" class="input-large field" title="标题"  ">
								<img alt="" id="bannerPictureUploadImage" src="${news.bannerPicture }" width="200" height="120">
							</td>
							<td height="40" width="140">
								<div id="div1">
									<div style="padding-left: 5px;">
									<span id="spanButtonPlaceholder2"></span> <br />
								</div>
								<div id="uploadFileContent2" class="uploadFileContent2"></div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:if>
		<c:if test="${status==false }">
			<tr id="isBannerPictureTr" style="display: none;">
				<td class="formTableTdLeft">幻灯片图片:&nbsp;</td>
				<td >
					<table  border="0" cellpadding="0" cellspacing="0" style="width: 340px;" height="120">
						<tr>
							<td height="120" width="200">
								<input type="hidden" name="news.bannerPicture" id="bannerPicture"
								value="${news.bannerPicture }" class="input-large field" title="标题"  ">
								<img alt="" id="bannerPictureUploadImage" src="${news.bannerPicture }" width="200" height="120">
							</td>
							<td height="40" width="140">
								<div id="div1">
									<div style="padding-left: 5px;">
									<span id="spanButtonPlaceholder2"></span> <br />
								</div>
								<div id="uploadFileContent2" class="uploadFileContent2"></div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:if>
			<tr height="42">
				<td class="formTableTdLeft">最新动态:&nbsp;</td>
				<td >
					<input type="checkbox" id="isOnTime" value="true" name="news.isOnTime" ${news.isOnTime==true?'checked="checked"':'' } ><label for="isOnTime">是否为实时要闻</label>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="checkbox" id="isLatestNew" value="true" name="news.isLatestNew" ${news.isLatestNew==true?'checked="checked"':'' } ><label for="isLatestNew">是否为最新动态</label> 
				</td>
			</tr>
			<tr height="42" style="display: none;">
				<td class="formTableTdLeft">附件:&nbsp;</td>
				<td >
					<input type="hidden" id="attach" name="news.attach">
				</td>
			</tr>
			<tr height="90">
				<td class="formTableTdLeft">内容简介:&nbsp;</td>
				<td >
						<textarea cols="59" rows="10" id="introduction" name="news.introduction" class="textarea-xxlarge" checkType="string,1,2000" canEmpty="Y" tip="内容简介必须小于2000个字符">${news.introduction }</textarea>
				</td>
			</tr>
			<tr height="32">
				<td colspan="4">
						<textarea cols="59" rows="10" id="content" name="news.content">${news.content }</textarea>
						<input type="hidden" id="contentText" name="news.contentText">
				</td>
			</tr>
		</table>
		<div class="buttons" style="margin-top: 20px;">
			<a href="javascript:void(-1)"	onclick="$.utile.submitForm('frmId','${ctx}/news/save',true)" class="ui-state-default">保存</a> 
		    <a href="${ctx }/news/queryList"	onclick="" class="ui-state-default">返回</a>
		</div>
		
		<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
		<ul id="treeDemo" class="ztree" style="margin-top:0; width:260px; height: 250px;"></ul>
</div>
	</form>
</body>
<script type="text/javascript">
	var editor=CKEDITOR.replace("content");
	var setting = {
			check: {
				enable: true,
				chkStyle: "radio",
				radioType: "all"
			},
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: onClick,
				onCheck: onCheck
			}
		};
	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.checkNode(treeNode, !treeNode.checked, null, true);
		return false;
	}

	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getCheckedNodes(true),
		v = "";
		db = '';
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
			db += nodes[i].id + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		if (db.length > 0 ) db = db.substring(0, db.length-1);
		var cityObj = $("#newTypeName");
		var newTypeDbid = $("#newTypeDbid");
		
		cityObj.attr("value", v);
		newTypeDbid.attr("value", db);
		
	}

	function showMenu() {
		var cityObj = $("#newTypeName");
		var cityOffset = $("#newTypeName").offset();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "newTypeName" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}

	$(document).ready(function(){
		$.post("${ctx}/newsType/jsonNewsType?timeStamp="+new Date()+"&urlType=2", { } ,function callback(json){
			$.fn.zTree.init($("#treeDemo"), setting, json);
			
		});
	});
</script>
</html>