<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/widgets/SWFUpload/css/default.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/css/img.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="${ctx }/widgets/SWFUpload/swfupload.js"></script>
<script type="text/javascript"
	src="${ctx }/widgets/SWFUpload/plugins/swfupload.queue.js"></script>
<script type="text/javascript"
	src="${ctx }/widgets/SWFUpload/js/fileprogress.js"></script>
<script type="text/javascript"
	src="${ctx }/widgets/SWFUpload/js/handlers.js"></script>
<style type="text/css">
.u {
	background-color: #F6F6F6;
	/* float: left; */
	height: 44px;
	line-height: 16px;
	margin: 0 4px 4px 0;
	position: relative;
	width: 280px;
	background-image: url("");
	background: no-repeat;
}

.j {
	color: #666666;
	height: 16px;
	left: 39px;
	overflow: hidden;
	position: absolute;
	text-overflow: ellipsis;
	top: 5px;
	white-space: nowrap;
	width: 200px;
}

.k {
	color: #B0B0B0;
	height: 16px;
	left: 39px;
	line-height: 160%;
	overflow: visible;
	position: absolute;
	text-overflow: ellipsis;
	top: 21px;
	white-space: nowrap;
}

#nui-txt-suc {
	color: #3D882D !important;
}
</style>
<script type="text/javascript">
	var upload1, upload2;
	window.onload = function() {
		upload1 = new SWFUpload(
				{
					// Backend Settings
					upload_url : "${ctx}/swfUpload/saveFile",
					post_params : {
						"PHPSESSID" : "1266fd75f7e4571c7b1ea4a41a584a81"
					},
					file_post_name : "file",

					// File Upload Settings
					file_size_limit : "5242880", // 100MB
					file_types : "*.*",
					file_types_description : "All Files",
					file_upload_limit : "10",
					file_queue_limit : "0",

					// Event Handler Settings (all my handlers are in the Handler.js file)
					file_dialog_start_handler : fileDialogStart,
					file_queued_handler : fileQueued,
					file_queue_error_handler : fileQueueError,
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
					flash_url : "${ctx}/widgets/SWFUpload/swfupload/swfupload.swf",

					custom_settings : {
						progressTarget : "fsUploadProgress1",
						cancelButtonId : "btnCancel1"
					},

					// Debug Settings
					debug : true
				});

		upload2 = new SWFUpload({
			// Backend Settings
			upload_url : "upload.php",
			post_params : {
				"PHPSESSID" : "1266fd75f7e4571c7b1ea4a41a584a81"
			},

			// File Upload Settings
			file_size_limit : "200", // 200 kb
			file_types : "*.jpg;*.gif;*.png",
			file_types_description : "Image Files",
			file_upload_limit : "10",
			file_queue_limit : "5",

			// Event Handler Settings (all my handlers are in the Handler.js file)
			file_dialog_start_handler : fileDialogStart,
			file_queued_handler : fileQueued,
			file_queue_error_handler : fileQueueError,
			file_dialog_complete_handler : fileDialogComplete,
			upload_start_handler : uploadStart,
			upload_progress_handler : uploadProgress,
			upload_error_handler : uploadError,
			upload_success_handler : uploadSuccess,
			upload_complete_handler : uploadComplete,

			// Button Settings
			button_image_url : "XPButtonUploadText_61x22.png",
			button_placeholder_id : "spanButtonPlaceholder2",
			button_width : 61,
			button_height : 22,

			// Flash Settings
			flash_url : "../swfupload/swfupload.swf",
			swfupload_element_id : "flashUI2", // Setting from graceful degradation plugin
			degraded_element_id : "degradedUI2", // Setting from graceful degradation plugin

			custom_settings : {
				progressTarget : "fsUploadProgress2",
				cancelButtonId : "btnCancel2"
			},

			// Debug Settings
			debug : false
		});

	}
</script>

<title>文件上传</title>
</head>
<body>
	<div id="content">
		<h2>Multi-Instance Demo</h2>
		<form id="form1" action="index.php" method="post"
			enctype="multipart/form-data">
			<p>This page demonstrates how multiple instances of SWFUpload can
				be loaded on the same page. It also demonstrates the use of the
				graceful degradation plugin and the queue plugin.</p>
			<table>
				<tr valign="top">
					<td>
						<div>
							<div class="fieldset flash" id="fsUploadProgress1">
								<span class="legend">Large File Upload Site</span>
							</div>
							<div style="padding-left: 5px;">
								<span id="spanButtonPlaceholder1"></span> <input id="btnCancel1"
									type="button" value="Cancel Uploads"
									onclick="cancelQueue(upload1);" disabled="disabled"
									style="margin-left: 2px; height: 22px; font-size: 8pt;" /> <br />
							</div>
						</div>
					</td>
					<td>
						<div>
							<div class="fieldset flash" id="fsUploadProgress2">
								<span class="legend">Small File Upload Site</span>
							</div>
							<div style="padding-left: 5px;">
								<span id="spanButtonPlaceholder2"></span> <input id="btnCancel2"
									type="button" value="Cancel Uploads"
									onclick="cancelQueue(upload2);" disabled="disabled"
									style="margin-left: 2px; height: 22px; font-size: 8pt;" /> <br />
							</div>
						</div>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="progress" style="border: 1px solid blue; position: relative; width: 80%; margin: 0 auto; height: 500px;">
			<div class="top">
				<div class="top_a">
					<img src="${ctx}/widgets/SWFUpload/images/word.jpg" />
				</div>
				<div class="top_b">
					<div class="top_b_a">公司简介.docx</div>
					<div class="top_b_b">
						<div class="top_b_b_a">98.74K</div>
						<div class="top_b_b_b">上传成功</div>
					</div>
				</div>
				<div class="top_c">
					<a href="#">删除</a>
				</div>
			</div>
			<div class="top">
				<div class="top_a">
					<img src="${ctx}/widgets/SWFUpload/images/word.jpg" />
				</div>
				<div class="top_b">
					<div class="top_b_a">公司简介.docx</div>
					<div class="top_b_b">
						<div class="top_b_b_a">进度条</div>
						<div class="top_b_b_b" style="width: 120px">剩余时间：12秒</div>
					</div>
				</div>
				<div class="top_c">
					<a href="#">删除</a>
				</div>
			</div>
			<div class="top">
				<div class="top_a">
					<img src="${ctx}/widgets/SWFUpload/images/word.jpg" />
				</div>
				<div class="top_b">
					<div class="top_b_a">公司简介.docx</div>
					<div class="top_b_b">
						<div class="top_b_b_a" style="width: 200px">等待上传，请稍等....</div>
					</div>
				</div>
				<div class="top_c">
					<a href="#">删除</a>
				</div>
			</div>
			<div class="top">
				<div class="top_a">
					<img src="${ctx}/widgets/SWFUpload/images/word.jpg" />
				</div>
				<div class="top_b">
					<div class="top_b_a">公司简介.docx</div>
					<div class="top_b_b">
						<div class="top_b_b_a">98.74K</div>
						<div class="top_b_b_b" style="color: red;">上传失败！</div>
					</div>
				</div>
				<div class="top_c">
					<a href="#">删除</a>
				</div>
			</div>
			<div class="top">
				<div class="top_a">
					<img src="${ctx}/widgets/SWFUpload/images/word.jpg" />
				</div>
				<div class="top_b">
					<div class="top_b_a">公司简介.docx</div>
					<div class="top_b_b">
						<div class="top_b_b_a">98.74K</div>
						<div class="top_b_b_b">上传成功</div>
					</div>
				</div>
				<div class="top_c">
					<a href="#">删除</a>
				</div>
			</div>
			<div class="top">
				<div class="top_a">
					<img src="${ctx}/widgets/SWFUpload/images/word.jpg" />
				</div>
				<div class="top_b">
					<div class="top_b_a">公司简介.docx</div>
					<div class="top_b_b">
						<div class="top_b_b_a">98.74K</div>
						<div class="top_b_b_b">上传成功</div>
					</div>
				</div>
				<div class="top_c">
					<a href="#">删除</a>
				</div>
			</div>
		<!-- 
		<div id="u">
			<b id="_mail_icon_53_286" class="nui-ico nui-ico-file32 nui-ico-file32-6 "></b>
			<div class="j" title="title">gcc-g++-3.4.2-20040916-1.tar.gz</div>
			<div class="l">
				<a class="nui-txt-link" href="javascript:void(0)">删除</a>
			</div>
			<div class="k">
				4.61M
				<span class="nui-txt-suc">上传完成</span>
			</div>
		</div>
		<div id="u" style="width: 324px;">
			<b id="_mail_icon_53_286" class="nui-ico nui-ico-file32 nui-ico-file32-6 "></b>
			<div class="j" title="title">gcc-g++-3.4.2-20040916-1.tar.gz</div>
			<div class="l">
				<a class="nui-txt-link" href="javascript:void(0)">删除</a>
			</div>
			<div class="k">
				4.61M
				<span class="nui-txt-suc">上传完成</span>
			</div>
		</div> 
		-->
	</div>
</body>
</html>