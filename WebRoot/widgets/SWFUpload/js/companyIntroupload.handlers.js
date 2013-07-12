
/**
 * 选择文件时触发！弹窗文件选择框是触发
 */
function fileDialogStart() {
	/* I don't need to do anything here */
}
//构造文件上传列表，在这里要做总文件大小统计，
//缩略图显示（根据文件类型进行显示、如何获取图片的缩略图待解决）
//构造上传文件删除按钮
function fileQueued(file) {
	var swfUpload = this;
	try {
		//上传文件时判断文件上传文件大小是否超过了上传最大限制
		//超过限制的文件，把其从上传队列中移除，放置调用后台程序
		var fileSizeLimit= swfUpload.settings.file_size_limit;
		if(file.size>fileSizeLimit){
			alert("只能上传一个文件！");
			swfUpload.cancelUpload(file.id);
		}
	} catch (ex) {
		this.debug(ex);
	}
}

//上传文件队列发生错误
function fileQueueErrorHandler(file, errorCode, message) {
	var swfUpload =this;
	var file_upload_limit= swfUpload.settings.file_upload_limit;
	try {
		if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
			alert("当前只能上传.: "+swfUpload.settings.file_upload_limit);
			return;
		}
		switch (errorCode) {
		case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
			$("#uploadState"+file.id).text("File is too big.");
			this.debug("Error Code: File too big, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
			$("#uploadState"+file.id).text("Cannot upload Zero Byte files.");
			this.debug("Error Code: Zero byte file, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
			$("#uploadState"+file.id).text("Invalid File Type.");
			this.debug("Error Code: Invalid File Type, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
			alert("You have selected too many files.  " +  (message > 1 ? "You may only add " +  message + " more files" : "You cannot add any more files."));
			break;
		default:
			if (file !== null) {
				$("#uploadState"+file.id).text("Unhandled Error");
			}
			this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}

//文件选择完成后，可以获取文件上传个数，总大小，上传百分比
//这里是总状态的第一次加入和第二次、第三次。。。添加上传附件
//numFilesSelected选择上传文件总数，numFilesQuened表示上传队列中的数据
function fileDialogComplete(numFilesSelected, numFilesQueued) {
	var swfUpload =this;
	//上传文件总数限制数据
	var file_upload_limit= swfUpload.settings.file_upload_limit;
	try {
		if(numFilesQueued>0){
			this.startUpload();
		}else
			return ;
		
	} catch (ex)  {
        this.debug(ex);
	}
}
function startUpload1(upload) {
	var userId=$("#userId").val();
	var postobj={"PHPSESSID" : "6a95034fff6ba3a6aa8a990ca3af42ee","barberShopAlbumId":userId};
	upload.setPostParams(postobj);
	upload.startUpload();
}
//上传文件开始，修改上传文件状态，修改删除文件方法
function uploadStart(file) {
	return true;
}
//上传文件进度条，上传进度条控制，修改css样式
function uploadProgress(file, bytesLoaded, bytesTotal) {
	try {
	} catch (ex) {
		this.debug(ex);
	}
}
//上传文件成功，修改状态、同时修改删除方法；获取后台传过来的url
function uploadSuccess(file, serverData) {
	var swfUpload = this;
	try {
		//第一步serverData返回数据
		if(null!=serverData&&undefined!=serverData&&serverData.length>0){
			var returnResult=serverData.split("|");
			if(returnResult[0]=="success"){//上传文件成功
				$("#barberShopImage").attr("src",returnResult[1].replace("source","172_232"));
				var length=returnResult[1].lastIndexOf("/");
				$("#logoUrlShow").val(returnResult[1].substring(length+1,returnResult[1].length));
				$("#logoUrl").val(returnResult[1]);
			}else{
				$("#message").text("");
				$("#message").text("上传公司logo发生错误！");
			}
		}else{
			this.debug("文件上传错误！返回位置错误！");
		}
	} catch (ex) {
		this.debug(ex);
	}
}
//上传文件完成 // upload has completed, try the next one in the queue
function uploadComplete(file) {
	try {
		/*  I want the next upload to continue automatically so I'll call startUpload here */
		if (this.getStats().files_queued === 0) {
			document.getElementById(this.customSettings.cancelButtonId).disabled = true;
		} else {	
			this.startUpload();
		}
	} catch (ex) {
		this.debug(ex);
	}
}
//上传错误
function uploadError(file, errorCode, message) {
	try {
		switch (errorCode) {
		case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
			$("#fileContent"+file.id).addClass("fileContent red");
			$("#uploadState"+file.id).text("上传错误发生错误，错位代码: " + message);
			$("#method"+file.id).unbind("click");
			$("#method"+file.id).bind("click",function(e){
				deleteFile(file,"error");
			});
			this.debug("Error Code: HTTP Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:
			$("#uploadState").text("Configuration Error");
			this.debug("Error Code: No backend file, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
			$("#uploadState").text("Upload Failed.");
			this.debug("Error Code: Upload Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.IO_ERROR:
			$("#uploadState").text("Server (IO) Error");
			this.debug("Error Code: IO Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
			$("#uploadState").text("Security Error");
			this.debug("Error Code: Security Error, File name: " + file.name + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
			$("#uploadState").text("Upload limit exceeded.");
			this.debug("Error Code: Upload Limit Exceeded, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND:
			$("#uploadState").text("File not found.");
			this.debug("Error Code: The file was not found, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
			$("#uploadState").text("Failed Validation.  Upload skipped.");
			this.debug("Error Code: File Validation Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
			if (this.getStats().files_queued === 0) {
				document.getElementById(this.customSettings.cancelButtonId).disabled = true;
			}
			$("#uploadState").text("Cancelled");
			progress.setCancelled();
			break;
		case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
			$("#uploadState").text("Stopped");
			break;
		default:
			$("#uploadState").text("Unhandled Error: " + error_code);
			this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
			break;
		}
	} catch (ex) {
        this.debug(ex);
    }
}
