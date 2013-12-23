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
<script type="text/javascript" src="${ctx }/widgets/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx }/widgets/utile/utile.js"></script>
<script type="text/javascript" src="${ctx }/widgets/easyvalidator/js/jquery.bgiframe.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/easyvalidator/js/easy_validator.pack.js"></script>
<script type="text/javascript" src="${ctx }/widgets/My97DatePicker/WdatePicker.js"></script>
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
<title>添加检疫证明</title>
</head>
<body class="bodycolor">
	<br>
	<form action="" name="frmId" id="frmId" style="margin-bottom: 40px;" target="_parent">
		<s:token></s:token>
		<input type="hidden" name="quarantineCertificate.dbid" id="dbid" value="${quarantineCertificate.dbid }">
		<input type="hidden" name="chickenBatchDbid" id=chickenBatchDbid value="${param.chickenBatchDbid }">
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 92%;">
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;">名称:&nbsp;</td>
				<td ><input type="text" name="quarantineCertificate.title" id="title"
					value="${quarantineCertificate.title }" class="input-medium field" title="名称"	checkType="string,1,20" tip="检疫名称不能为空"><span style="color: red;">*</span></td>
				<td class="formTableTdLeft" style="width: 60px;">检疫时间:&nbsp;</td>
				<td ><input type="text" name="quarantineCertificate.awardDate" id="awardDate"
					value="${quarantineCertificate.awardDate }" class="input-medium field" title="名称" readonly="readonly"	onFocus="WdatePicker({isShowClear:false,readOnly:true})"></td>
			</tr>
			
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;">检疫机构:&nbsp;</td>
				<td colspan="3"><input type="text" name="quarantineCertificate.awardGroup" id="awardGroup"
					value="${quarantineCertificate.awardGroup }"  checkType="string,1,100" tip="检疫机构不能为空" class="input-medium field" ><span style="color: red;">*</span></td>
			</tr>
			<tr height="32">
				<td class="formTableTdLeft" style="width: 60px;">图片:&nbsp;</td>
				<td colspan="3">
					<table  border="0" cellpadding="0" cellspacing="0" style="width: 300px;" height="200">
					<tr>
						<td height="200" width="260" style="">
							<input type="hidden" name="quarantineCertificate.certificateImage" id="fileUpload" readonly="readonly"	value="${quarantineCertificate.certificateImage }">
							<img alt="" id="fileUploadImage" src="${quarantineCertificate.certificateImage }" width="300" height="180" style="margin-left: -12px;">
						</td>
						<td height="40" width="40">
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
			<tr height="32">
				<td class="formTableTdLeft" style="width: 60px;">备注:&nbsp;</td>
				<td colspan="3">
					 <textarea rows="" cols="" id="note" class="textarea-xxlarge" name="quarantineCertificate.note">${quarantineCertificate.note }</textarea>
				</td>
			</tr>
		</table>
		<div class="buttons" style="margin-top: 20px;">
			<a href="javascript:void()" 	onclick="$.utile.submitForm('frmId','${ctx}/quarantineCertificate/save')"
					class="ui-state-default">保存</a> 
		    <a href="${ctx }/chickenBatch/index?dbid=${param.chickenBatchDbid}" target="contentUrl"	class="ui-state-default">关闭</a>
		</div>
	</form>
</body>
</html>