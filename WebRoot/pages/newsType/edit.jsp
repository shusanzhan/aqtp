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
<script type="text/javascript" src="${ctx }/widgets/jqueryui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/utile/utile.js"></script>
<script type="text/javascript" src="${ctx }/widgets/easyvalidator/js/jquery.bgiframe.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/easyvalidator/js/easy_validator.pack.js"></script>
<script type="text/javascript"	src="${ctx }/widgets/SWFUpload/swfupload.js"></script>
<script type="text/javascript"	src="${ctx }/widgets/SWFUpload/plugins/swfupload.queue.js"></script>
<script type="text/javascript"	src="${ctx }/widgets/SWFUpload/plugins/swfupload.speed.js"></script>
<script type="text/javascript"	src="${ctx }/widgets/SWFUpload/js/fileupload.handlers.js"></script>
<script type="text/javascript">
var upload1;

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
				file_types : "*.jpg",
				file_types_description : "All Files",
				file_upload_limit : "1",
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
					titlePicture : "fileUpload",
					fileUploadImage : "fileUploadImage"
				},

				// Debug Settings
				debug : false
			});

}
</script>
<title>添加菜单分类</title>
</head>
<body class="bodycolor">
	<br>
	<form action="" name="frmId" id="frmId" style="margin-bottom: 40px;" target="_parent">
		<c:if test="${not empty(newsType) }">
			<input type="hidden" name="parentId" value="${newsType.parent.dbid }" id="parentId"></input>
		</c:if>
		<c:if test="${empty(newsType) }">
			<input type="hidden" name="parentId" value="${param.parentId }" id="parentId"></input>
		</c:if>
		<input type="hidden" name="newsType.dbid" id="dbid" value="${newsType.dbid }">
		<s:token></s:token>
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 90%;">
			<tr height="42">
				<td class="formTableTdLeft">标题:&nbsp;</td>
				<td width="300"><input type="text" name="newsType.name" id="title"
					value="${newsType.name }" class="input-large field" title="用户名"	checkType="string,1,20" tip="标题不能为空"><span style="color: red;">*</span></td>
			</tr>
			<tr height="80">
				<td class="formTableTdLeft">标题图片:&nbsp;</td>
				<td width="300"><input type="text" name="newsType.bannerUrl" id="fileUpload"
					value="${newsType.bannerUrl }" class="input-small field" readonly="readonly"	>
							<img alt="" id="fileUploadImage" src="${breeder.photo }" width="300" height="180" style="display: none;">
						<div id="div1">
							<div style="padding-left: 5px;">
							<span id="spanButtonPlaceholder1"></span> <br />
						</div>
						<div id="uploadFileContent" class="uploadFileContent"></div>
						</div>
				</td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">标题图片启用状态:&nbsp;</td>
				<td width="300">
					<select name="newsType.bannerStatus" id="bannerStatus">
						<option ${newsType.bannerStatus==1?'selected="selected"':'' } value="1">停用</option>
						<option ${newsType.bannerStatus==0?'selected="selected"':'' } value="0">启用</option>
					</select>
				</td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft">备注:&nbsp;</td>
				<td >
					<textarea rows="" cols="" class="input-large field" name="newsType.note">${newsType.note }</textarea>
				</td>
			</tr>
		</table>
		<div class="buttons" style="margin-top: 20px;">
			<a href="javascript:void(-1)"
					onclick="$.utile.submitForm('frmId','${ctx}/newsType/save')"
					class="ui-state-default">保存</a> 
		</div>
	</form>
</body>
</html>