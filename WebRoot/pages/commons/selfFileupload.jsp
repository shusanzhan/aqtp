<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
</head>
<link href="${ctx }/widgets/SWFUpload/css/default.css" rel="stylesheet" type="text/css" />
<style>
.uploadFileContent{
	width: 900px;
}
.fileContent{
	background: #F6F6F6;padding: 5px 5px 5px 5px;float: left;margin-left: 5px;margin-top: 8px；
}
/* Error */
.red {
	border: solid 1px #B50000;
	background-color: #FFEBEB;
}
.thumbnail{
	float:left;width:30px;height:30px;background-image: url('${ctx}/widgets/SWFUpload/images/word.jpg');
}
.uploadFile{
	padding-left:4px;float: left;
}
.fileName{
}
.fileName  span{
	font-weight: bold;
}
.fileName  a{
	float: right;
	padding-right:12px;
	cursor:pointer;
}
.uploadState{
	
}
.uploadState .stateError{
	padding-left: 12px;color: red;
}
.stateSuccess{
	padding-left: 12px;color: blue;
}
.uploadState .stateWating{
	color: blue;
}
.progressbar{ 
	border:1px solid #333; height:5px; background:#fff; width: 80px;float: left;
	margin-top: 4px;margin-bottom: 4px;
}
.progress{ 
	background:#999; width:20%; height:5px; 
}
.amount{
 padding-left: 5px;
}
.amount span{
	padding-right: 5px;
}
</style>
<script type="text/javascript" src="${ctx }/widgets/jqueryui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/SWFUpload/swfupload.js"></script>
<script type="text/javascript" src="${ctx }/widgets/SWFUpload/plugins/swfupload.queue.js"></script>
<script type="text/javascript" src="${ctx }/widgets/SWFUpload/plugins/swfupload.speed.js"></script>
<script type="text/javascript" src="${ctx }/widgets/SWFUpload/js/swfupload.handlers.js"></script>
<script type="text/javascript">
		var upload1;

		window.onload = function() {
			upload1 = new SWFUpload({
				// Backend Settings
				upload_url: "${ctx}/swfUpload/saveFile",
				post_params: {"PHPSESSID" : "6a95034fff6ba3a6aa8a990ca3af42ee"},
				//上传文件的名称
                file_post_name: "file",
                
				// File Upload Settings
				file_size_limit : "5242880",	// 200MB
				file_types : "*.*",
				file_types_description : "All Files",
				file_upload_limit : "100",
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
				button_width: 61,
				button_height: 22,
				
				// Flash Settings
				flash_url : "${ctx}/widgets/SWFUpload/Flash/swfupload.swf",
				

				custom_settings : {
					progressTarget : "uploadFileContent",
					cancelButtonId : "btnCancel1"
				},
				
				// Debug Settings
				debug: false
			});


	     }
	</script>
</head>
<body>
<div id="header">
	<h1 id="logo"><a href="../">SWFUpload</a></h1>
	<div id="version">v2.2.0</div>
</div>
<div id="content">
	<form id="form1" action="index.php" method="post" enctype="multipart/form-data">
		<table>
			<tr valign="top">
				<td>
					<div>
						<div class="fieldset flash" id="fsUploadProgress1">
							<span class="legend">Large File Upload Site</span>
							<table>
								<tbody>
									<tr><td>等待上传：12个</td><td style="padding-left: 12px;">总大小：300Mb</td><td style="padding-left: 12px;">已上传：20Mb</td><td style="padding-left: 12px;">进度：12%</td></tr>
									<tr><td>已经上传：2个</td></tr>
									<tr><td>上传错误：0个</td></tr>
								</tbody>
							</table>
						</div>
						<div style="padding-left: 5px;">
							<span id="spanButtonPlaceholder1"></span>
							<input type="button" value="开始上传" onclick="startUpload1(upload1)" style="margin-left: 2px; height: 22px; font-size: 8pt;" />
							<br />
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form>
	<div id="uploadFileContent" class="uploadFileContent">
	<div id="amount"></div>
	
	</div>
	<%-- <s:form action="/swfUpload/save">
		<s:textfield name="userName"></s:textfield>
		<s:text name="userName"></s:text>
		<s:password name="password"></s:password>
		<s:token></s:token>
		<s:submit value="tijiao"></s:submit>
	</s:form> --%>
</body>
</html>
