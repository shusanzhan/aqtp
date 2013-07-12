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
					cancelButtonId : "btnCancel1"
				},

				// Debug Settings
				debug : false
			});

}
</script>
<title>用户添加</title>
</head>
<body class="bodycolor">
	<table class="TableTop" width="100%">
		<tbody>
			<tr>
				<td class="left"></td>
				<td class="center">添加用户</td>
				<td class="right"></td>
			</tr>
		</tbody>
	</table>
	<br>
	<form action="" name="frmId" id="frmId" style="margin-bottom: 40px;" target="_self">
		<s:token></s:token>
		<input type="hidden" name="user.dbid" id="dbid" value="${user.dbid }">
		<input type="hidden" name="breeder.dbid" id="dbid" value="${breeder.dbid }">
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 92%;">
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;">用户ID:&nbsp;</td>
				<td ><input type="text" name="user.userId" id="userId"
					value="${user.userId }" class="input-large field" title="用户ID" url="${ctx }/user/validateUser"	checkType="string,5,20" tip="用户名不能为空,并且5-20个字符"><span style="color: red;">*</span></td>
				<td rowspan="4" colspan="2">
				<table  border="0" cellpadding="0" cellspacing="0" style="width: 500px;" height="200">
					<tr>
						<td height="200" width="360">
							<input type="hidden" name="breeder.photo" id="fileUpload" readonly="readonly"	value="${breeder.photo }">
							<img alt="" id="fileUploadImage" src="${breeder.photo }" width="300" height="180">
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
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;">姓名:&nbsp;</td>
				<td ><input type="text" name="user.realName" id="realName"
					value="${user.realName }" class="input-large field" title="姓名"	checkType="string,1,10" tip="真实名称不能为空"><span style="color: red;">*</span></td>
			</tr>
			
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;">生日:&nbsp;</td>
				<td ><input type="text" name="breeder.birthday" id="birthday"
					value="${breeder.birthday }" readonly="readonly" class="input-large field" onFocus="WdatePicker({isShowClear:false,readOnly:true})"></td>
			</tr>
			<tr height="32">
				<td class="formTableTdLeft" style="width: 60px;">性别:&nbsp;</td>
				<td >
					<input type="radio" id="sex1"  name="breeder.sex" ${breeder.sex=='男'?'checked="checked"':'' }  value="男"><label id="" for="sex1">男</label>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" id="sex2"  name="breeder.sex" ${breeder.sex=='女'?'checked="checked"':'' } value="女"><label id="" for="sex2">女</label>
				</td>
			</tr>
			<tr height="32">
				<td class="formTableTdLeft" style="width: 60px;">手机:&nbsp;</td>
				<td><input type="text" name="user.mobilePhone" id="mobilePhone"
					value="${user.mobilePhone }" class="input-large field"  checkType="phoneNo" canEmpty="Y" tip="请输入正确的手机号"></td>
				<td class="formTableTdLeft" style="width: 60px;">座机:&nbsp;</td>
				<td><input type="text" name="user.phone" id="phone"
					value="${user.phone }" class="input-large field"  checkType="phoneNo"  canEmpty="Y" tip="请输入正确的座机号"></td>
			</tr>
			<tr height="42">
			    <td class="formTableTdLeft" style="width: 60px;">邮箱:&nbsp;</td>
				<td ><input type="text" name="user.email" id="email"
					value="${user.email }" class="input-large field" title="邮箱"	checkType="email" canEmpty="Y" tip="请输入正确的邮箱"></td>
				<td class="formTableTdLeft" style="width: 60px;">学历:&nbsp;</td>
				<td >
					<select class="select field" id="educationalBackground" name="breeder.educationalBackground" style="width: 120px;">
						<option value="小学"  ${breeder.educationalBackground=='小学'?'selected="selected"':'' } >小学</option>
						<option value="初中生" ${breeder.educationalBackground=='初中生'?'selected="selected"':'' }>初中生</option>
						<option value="高中生" ${breeder.educationalBackground=='高中生'?'selected="selected"':'' }>高中生</option>
						<option value="中专生" ${breeder.educationalBackground=='中专生'?'selected="selected"':'' }>中专生</option>
						<option value="大专生" ${breeder.educationalBackground=='大专生'?'selected="selected"':'' }>大专生</option>
						<option value="本科生" ${breeder.educationalBackground=='本科生'?'selected="selected"':'' }>本科生</option>
						<option value="硕士生" ${breeder.educationalBackground=='硕士生'?'selected="selected"':'' }>硕士生</option>
						<option value="博士生" ${breeder.educationalBackground=='博士生'?'selected="selected"':'' }>博士生</option>
						<option value="其它" ${breeder.educationalBackground=='其它'?'selected="selected"':'' }>其它</option>
					</select>
				</td>
			</tr>
			<tr height="42">
				<td class="formTableTdLeft" style="width: 60px;">毕业学校:&nbsp;</td>
				<td colspan="3"><input type="text" name="breeder.graduationSchool" id="graduationSchool"
					value="${breeder.graduationSchool }" class="input-xlarge field" title="毕业学校"></td>
			</tr>
		</table>
		<div class="buttons" style="margin-top: 20px;">
			<a href="javascript:void(-1)"
					onclick="$.utile.submitForm('frmId','${ctx}/breeder/save')"
					class="ui-state-default">保存</a> 
		    <a href="${ctx }/breeder/queryList"	onclick="" class="ui-state-default">返回</a>
		</div>
	</form>
</body>
</html>