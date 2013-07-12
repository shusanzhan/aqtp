
var totalNum=0,totalSize=0,uploadTotalSize=0,uploadTotalProgress=0;
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
	try {
		//记录文件的总大小
		totalSize=totalSize+file.size;
		//构造文件列表
		var fileContent=" <div class='tp' id='fileContent"+file.id+"'></div>";
		$("#"+this.customSettings.progressTarget).append(fileContent);
		//绑定上传文件队列删除方法
		$("#method"+file.id).bind("click",function(e){
			swfUpload.cancelUpload(file.id);  
			totalNum=totalNum-1;
			if(totalNum<=0){//没有文件时，清空总的统计数据
				$("#amount").text("");
				$("#fileContent" + file.id).slideUp('fast');
			}else{//从新更新数据
				$('#totalNum').text(totalNum);
				totalSize=totalSize-file.size;
				$('#totalSize').text(SWFUpload.speed.formatBytes(totalSize));
				$("#fileContent" + file.id).slideUp('fast');
			}
		});
		//上传文件时判断文件上传文件大小是否超过了上传最大限制
		//超过限制的文件，把其从上传队列中移除，放置调用后台程序
		var fileSizeLimit= swfUpload.settings.file_size_limit;
		if(file.size>fileSizeLimit){
			$("#fileContent"+file.id).addClass("fileContent red");
			$("#uploadState"+file.id).text("上传文件超过了限制");
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
			//临时保存上传数据总大小数据
			totalNum=totalNum+numFilesQueued;
			var mbTotalSize=SWFUpload.speed.formatBytes(totalSize);
			var totalText=$("#amount").text();
			if(null!=totalText&&totalText.length>0){
				$("#totalNum").text("");//清空原来数据
				$("#totalSize").text("");
				$("#totalNum").append(totalNum);
				$("#totalSize").append(mbTotalSize);
			}else{//第一次上传
				totalText="总文件：<span id='totalNum'>"+totalNum+"</span>个，" +
					"总大小：<span id='totalSize'>"+mbTotalSize+"</span>，" +
					"已上传：<span id='uploadTotalSize'>0Mb</span>，" +
					"进度：<span id='uploadTotalProgress'>0%</span>";
				$("#amount").append(totalText);
			}
			uploadBtnAvailable(this.customSettings.uploadButtonId);
			nextStepSubmitUnavailable(this.customSettings.nextStepSubmit);
		}else
			return ;
		
	} catch (ex)  {
        this.debug(ex);
	}
}
function startUpload1(upload) {
	var status=$("#uploadBtn").attr("status");
	var albumId=$("#albumId").val();
	if(null==albumId||albumId==undefined){
		alert("请选择相册！");
		return false;
	}
	var userId=$("#userId").val();
	var postobj={"PHPSESSID" : "6a95034fff6ba3a6aa8a990ca3af42ee","barberShopAlbumId":albumId,"userId":userId};
	upload.setPostParams(postobj);
	if(status=="1"){
		//为不可用状态，不做任何操作
	}else{
		upload.startUpload();
	}
}
//上传文件开始，修改上传文件状态，修改删除文件方法
function uploadStart(file) {
	try {
		var uploadState="<img src='/images/imageUpload/loading.gif' style='position:relative; top:65px;' ></div>";
		$("#fileContent"+file.id).text("");
		$("#fileContent"+file.id).append(uploadState);
		//$("#method"+file.id).hide("fast");
	}
	catch (ex) {
	}
	
	return true;
}
//上传文件进度条，上传进度条控制，修改css样式
function uploadProgress(file, bytesLoaded, bytesTotal) {
	try {
		//设置文件上传百分比
		var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);
		var timeRemaining=SWFUpload.speed.formatTime(file.timeRemaining);
		var uploadState="<div id='progressbar' class='progressbar'><div id='progress' class='progress' style='width:"+percent+"%'></div></div>" +
		"<span id='state' class='stateWating'>"+percent+"%，剩余时间："+timeRemaining+"</span>";
		$("#uploadState"+file.id).text("");
		$("#uploadState"+file.id).append(uploadState);
		$("#method"+file.id).hide("fast");
		//已经上传文件大小
		uploadTotalSize=uploadTotalSize+bytesLoaded;
		var mbuploadTotalSize=SWFUpload.speed.formatBytes(uploadTotalSize);
		//已上传文件百分比
		uploadTotalProgress=Math.ceil((uploadTotalSize / totalSize) * 100);
		if(uploadTotalSize>totalSize){
			$("#uploadTotalProgress").text(100+"%");
			$("#uploadTotalSize").text(SWFUpload.speed.formatBytes(totalSize));
		}else{
			$("#uploadTotalProgress").text(uploadTotalProgress+"%");
			$("#uploadTotalSize").text(mbuploadTotalSize);
		}
	} catch (ex) {
		this.debug(ex);
	}
}
//上传文件成功，修改状态、同时修改删除方法；获取后台传过来的url
function uploadSuccess(file, serverData) {
	var swfUpload = this;
	try {
		//第一步serverData返回数据
		var fileSize=SWFUpload.speed.formatBytes(file.size);
		if(null!=serverData&&undefined!=serverData&&serverData.length>0){
			var returnResult=serverData.split("|");
			if(returnResult[0]=="success"){//上传文件成功
				var result=$("#result").val();
				result=result+"|"+serverData;
				$("#result").val(result);
				var uploadState="<span><img src='/images/imageUpload/icons3.png' /><a href='javascript:void' id='deleteMethod"+file.id+"'>删除</a></span></div>";
				$("#fileContent"+file.id).css("background-image","url("+encodeURI(returnResult[1].replace("source","120_150"))+")");
				$("#fileContent"+file.id).text("");
				$("#fileContent"+file.id).append(uploadState);
				//从新绑定删除方法
				$("#deleteMethod"+file.id).bind("click",function(e){
					$.post('${ctx}/swfUpload/deleteFile?fileUrl='+encodeURI(encodeURI(returnResult[1]))+"&timeStamp="+new Date(),function(data){
						if(data=="success"){
							//swfUpload.cancelUpload(file.id);
							//totalNum=totalNum-1;
							if(totalNum<=0){//没有文件时，清空总的统计数据
								//$("#amount").text("");
								//$("#fileContent" + file.id).slideUp('fast');
							}else{//从新更新数据
								//$('#totalNum').text(totalNum);
								//totalSize=totalSize-file.size;
								//$('#totalSize').text(SWFUpload.speed.formatBytes(totalSize));
								//uploadTotalSize=$("#uploadTotalSize").text();
								//var dd=SWFUpload.speed.getBytes(uploadTotalSize);
								//$("#uploadTotalProgress").text();
								$("#fileContent" + file.id).slideUp('fast');
							}
						}if(data=="failed"){
							var uploadState="<span id='fileSize'>文件大小："+fileSize+"</span><span id='state' class='stateSuccess'>文件删除失败</span>";
							$("#uploadState"+file.id).text("");
							$("#uploadState"+file.id).append(uploadState);
						}
					});
				});
			}if(returnResult[0]=="failed"){//上传文件失败
				var uploadState="<span id='fileSize'>文件大小："+fileSize+"</span><span id='state' class='stateSuccess'>上传失败</span>";
				$("#uploadState"+file.id).text("");
				$("#uploadState"+file.id).append(uploadState);
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
		if (this.getStats().files_queued == 0) {
			uploadBtnUnavailable(this.customSettings.uploadButtonId);
			nextStepSubmitAvailable(this.customSettings.nextStepSubmit);
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
				alert(1);
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

function uploadBtnAvailable(butId){
	//this.startUpload();
	//控制上传按钮可用
	$("#"+butId).css("color","");
	$("#"+butId).attr("status","");
}
function uploadBtnUnavailable(butId){
	//控制上传按钮不可用
	$("#"+butId).css("color","#cccccc");
	$("#"+butId).attr("status","1");
}
function nextStepSubmitAvailable(butId){
	//this.startUpload();
	//控制上传按钮可用
	$("#"+butId).css("color","");
	$("#"+butId).attr("status","");
}
function nextStepSubmitUnavailable(butId){
	//控制上传按钮不可用
	$("#"+butId).css("color","#ff54a0");
	$("#"+butId).attr("status","1");
}