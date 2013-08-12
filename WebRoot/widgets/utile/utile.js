(function($) {
	$.utile = {};
})(jQuery);

/**
 * Iframe的底层应用，短暂提示
 * 
 * @param {String}
 *            提示内容
 * @param {Number}
 *            显示时间 (默认1.5秒)
 */
$.utile.tips = function(content, time) {
	return window.top.art.dialog({
		id : 'Tips',
		title : false,
		cancel : false,
		lock : true,
		fixed : true
	}).content('<div style="padding: 0 1em;">' + content + '</div>').time(
			time || 1);
}

/*******************************************************************************
 * 添加提交Ajax表单 1、验证表单数据 2、获取页面表单数据 3、提交表单数据 4、处理返回数据页面跳转target标识
 * 保存数据时页面跳转表示，如果是弹窗页面保存成功页面跳转到父页面 如果数据是新开页面保存数据成功后页面跳转到原页面
 ******************************************************************************/
$.utile.submitForm = function(frmId, url, state) {
	var target = $("#" + frmId).attr("target") || "_self";
	try {
		if (undefined != frmId && frmId != "") {
			var validata = validateForm(frmId);
			if (validata == true) {
				var params = getParam(frmId, state);
				$.post(url,params,function callBack(data) {
					if (data[0].mark == 0) {// 返回标志为0表示添加数据成功
						$.utile.tips(data[0].message);
						if (target == "_self") {
							setTimeout(
									function() {
										window.location.href = data[0].url
									}, 1000);
						}
						if (target == "_parent") {
							// 同时关闭弹出窗口
							var parent = window.parent;
							window.parent.frames["contentUrl"].location=data[0].url;
						}
						// 保存数据成功时页面需跳转到列表页面
					}
					if (data[0].mark == 1) {// /返回标志为1表示保存数据失败
						$.utile.tips(data[0].message);
						// 保存失败时页面停留在数据编辑页面
					}
					return;
				});
			} else {
				return;
			}
		} else {
			return;
		}
	} catch (e) {
		$.utile.tips(e);
		return;
	}
}
function getParam(frmId, state) {
	// 富文本编辑器取数据
	var ckeditorState = state || false;
	if (ckeditorState == true) {
		$('#content').val(CKEDITOR.instances.content.getData());
	}
	var params = $("#" + frmId).serialize();
	return params;
}
// 删除数据前验证选择数据
function checkBefDel() {
	var checkeds = $("input[type='checkbox'][name='id']");
	var length = 0;
	$.each(checkeds, function(i, checkbox) {
		if (checkbox.checked) {
			length++;
		}
	})
	if (length <= 0) {
		window.top.art.dialog({
			icon : 'warning',
			title : '警告',
			content : '请选择操作数据！',
			cancelVal : '关闭',
			lock : true,
			time : 3,
			width:"250px",
			height:"80px",
			cancel : true
		// 为true等价于function(){}
		});
		return false;
	} else {
		return true;
	}
}
function getTabID(windowParent) {
	var center = windowParent.getElementById("center");
	var divSelect = $(center).children();
	for ( var i = 0; i < divSelect.length; i++) {
		var hel = $(divSelect[i]).attr("class");
		if (hel != undefined && hel != "") {
			if (hel == "tabs-panel selected") {
				var tabId = $(divSelect[i]).children()[0];
				return $(tabId).attr("id");
			}
		}
	}
}
/** 弹窗关闭* */
$.utile.close = function() {
	// 同时关闭弹出窗口
	var dd = window.parent.parent.document;
	var tabId = getTabID(dd);
	var win = parent.document.getElementById(tabId).contentWindow;
	win.document.location.reload();
}
/** 获取checkBox的value* */
function getCheckBox() {
	var array = new Array();
	var checkeds = $("input[type='checkbox'][name='id']");
	$.each(checkeds, function(i, checkbox) {
		if (checkbox.checked) {
			array.push(checkbox.value);
		}
	});
	return array.toString();
}

/**
 * 将选择的用户自动赋值到 列表中
 */
$.utile.showChooseUser = function() {
	var array = new Array();
	var checkeds = $("input[type='checkbox'][name='id']");
	var users = getCheckBox();
	var userValues = $('#users').val();
	if (userValues == null || userValues == "") {
		$('#users').val(users);
		return;
	} else if (userValues != null) {
		var temp = userValues.substring(userValues.length - 1,
				userValues.length);

		if (temp != ",") {
			$('#users').val(userValues + ',');
		}

		$.each(checkeds, function(i, checkbox) {
			if (checkbox.checked) {
				if (userValues.indexOf(checkbox.value) == -1) {
					array.push(checkbox.value);
				}
			}
		});
	}

	$('#users').val($('#users').val() + array.toString());
	return;

}

/** 删除封装提示信息方法* */
$.utile.deleteIds = function(url,searchFrm) {
	var checkBef = checkBefDel();
	var params=$("#"+searchFrm).serialize();
	try {
		if (checkBef == true) {
			window.top.art.dialog({
				content : '您确定删除选择数据吗？',
				icon : 'question',
				width:"250px",
				height:"80px",
				lock : true,
				ok : function() {// 点击去定按钮后执行方法
					var param = getCheckBox();
					$.post(url + "?dbids=" + param + "&datetime=" + new Date(),params,	callBack);
					function callBack(data) {
						if (data[0].mark == 2) {// 关系存在引用，删除时提示用户，用户点击确认后在退回删除页面

							window.top.art.dialog({
								content : data[0].message,
								icon : 'warning',
								window : 'top',
								lock : true,
								width:"200px",
								height:"80px",
								ok : function() {// 点击去定按钮后执行方法

									$.utile.close();
									return;
								}
							});

						}

						if (data[0].mark == 1) {// 删除数据失败时提示信息
							$.utile.tips(data[0].message);
						}
						if (data[0].mark == 0) {// 删除数据成功提示信息
							$.utile.tips(data[0].message);
						}
						// 页面跳转到列表页面
						setTimeout(function() {
							window.location.href = data[0].url
						}, 1000);
					}
				},
				cancel : true
			});
		} else {
			return;
		}
	} catch (e) {
		return;
	}
}

/*
 * 1、删除一条数据 2、url格式为：${ctx}/user/deleteById?dbid=1
 */
$.utile.deleteById = function(url,searchFrm) {
	
	var param=$("#"+searchFrm).serialize();
	window.top.art.dialog({
		content : '您确启用选择数据吗？',
		icon : 'warning',
		width:"250px",
		height:"80px",
		window : 'top',
		lock : true,
		ok : function() {// 点击去定按钮后执行方法
			$.post(url + "&datetime=" + new Date(),param,function callBack(data) {
				if (data[0].mark == 2) {// 关系存在引用，删除时提示用户，用户点击确认后在退回删除页面

					window.top.art.dialog({
						content : data[0].message,
						icon : 'warning',
						window : 'top',
						width:"250px",
						height:"80px",
						lock : true,
						ok : function() {// 点击去定按钮后执行方法

							$.utile.close();
							return;
						}
					});

				}
				if (data[0].mark == 1) {// 删除数据失败时提示信息
					/* $.cqtaUtile.errMessage(data[0].message); */
					$.utile.tips(data[0].message);
				}
				if (data[0].mark == 0) {// 删除数据成功提示信息
					/* $.cqtaUtile.okDeleteMessage(data[0].message); */
					$.utile.tips(data[0].message);
				}
				// 页面跳转到列表页面
				setTimeout(function() {
					window.location.href = data[0].url
				}, 1000);
			});
		},
		cancel : true
	});
}

/** 删除封装提示信息方法* */
$.utile.operatorDataByDbids = function(url,searchFrm,conf) {
	var content="您确定删除选择数据吗？";
	if(null!=conf&&conf!=undefined){
		content=conf;
	}
	var checkBef = checkBefDel();
	var params;
	if(null!=searchFrm&&searchFrm!=undefined){
		params=$("#"+searchFrm).serialize();
	}
	try {
		if (checkBef == true) {
			window.top.art.dialog({
				content : content,
				icon : 'question',
				width:"250px",
				height:"80px",
				lock : true,
				ok : function() {// 点击去定按钮后执行方法
					var param = getCheckBox();
					$.post(url + "?dbids=" + param + "&datetime=" + new Date(),params,
							callBack);
					function callBack(data) {

						if (data[0].mark == 2) {// 关系存在引用，删除时提示用户，用户点击确认后在退回删除页面

							window.top.art.dialog({
								content : data[0].message,
								icon : 'warning',
								window : 'top',
								width:"250px",
								height:"80px",
								lock : true,
								ok : function() {// 点击去定按钮后执行方法

									$.utile.close();
									return;
								}
							});

						}

						if (data[0].mark == 1) {// 删除数据失败时提示信息
							$.utile.tips(data[0].message);
						}
						if (data[0].mark == 0) {// 删除数据成功提示信息
							$.utile.tips(data[0].message);
						}
						// 页面跳转到列表页面
						setTimeout(function() {
							window.location.href = data[0].url
						}, 1000);
					}
				},
				cancel : true
			});
		} else {
			return;
		}
	} catch (e) {
		return;
	}
}
/*
 * 1、删除一条数据 2、url格式为：${ctx}/user/deleteById?dbid=1
 */
$.utile.operatorDataByDbid = function(url,searchFrm,conf) {
	var content="您确定删除选择数据吗？";
	if(null!=conf&&conf!=undefined){
		content=conf;
	}
	var params=$("#"+searchFrm).serialize();
	window.top.art.dialog({
		content : content,
		icon : 'warning',
		width:"250px",
		height:"80px",
		window : 'top',
		lock : true,
		ok : function() {// 点击去定按钮后执行方法
			$.post(url + "&datetime=" + new Date(),params,function callBack(data) {
				if (data[0].mark == 2) {// 关系存在引用，删除时提示用户，用户点击确认后在退回删除页面
					
					window.top.art.dialog({
						content : data[0].message,
						icon : 'warning',
						window : 'top',
						width:"250px",
						height:"80px",
						lock : true,
						ok : function() {// 点击去定按钮后执行方法
							
							$.utile.close();
							return;
						}
					});
					
				}
				if (data[0].mark == 1) {// 删除数据失败时提示信息
					/* $.cqtaUtile.errMessage(data[0].message); */
					$.utile.tips(data[0].message);
				}
				if (data[0].mark == 0) {// 删除数据成功提示信息
					/* $.cqtaUtile.okDeleteMessage(data[0].message); */
					$.utile.tips(data[0].message);
				}
				// 页面跳转到列表页面
				setTimeout(function() {
					window.location.href = data[0].url
				}, 1000);
			});
		},
		cancel : true
	});
}

$.utile.openDialog = function(url, title, swidth, sheight) {
	var width = swidth || 550;
	var heigth = sheight || 200;
	art.dialog.open(url, {
		title : title,
		width : width,
		height : heigth,
		lock : true
	});
}
/*******************************************************************************
 * 系统弹出封装，待考虑一个问题就系统提交出现错误时 页面不能停留在提交页面，还是会跳转到list页面
 * 
 * $.utile.openDialog=function(url,title,sfrmId,tegartUrl,swidth,sheight){ //var
 * width=swidth>0?swidth:550; //var heigth=sheight>0?sheight:200; var
 * width=swidth || 550; var heigth=sheight||200; art.dialog.open(url, {title:
 * title,width:width,height:heigth, init: function () { var iframe =
 * this.iframe.contentWindow; var top = art.dialog.top;// 引用顶层页面window对象 }, ok:
 * function () { var iframe = this.iframe.contentWindow; var
 * frmId=iframe.document.getElementById(sfrmId); if (!iframe.document.body) {
 * alert('iframe还没加载完毕呢') return false; }; if(validateForm(frmId)==false){
 * return false; } var params=$(frmId).serialize(); var
 * po=$.post(tegartUrl,params,function (data){ /*var top = art.dialog.top;
 * if(data[0].mark==0){//返回标志为0表示添加数据成功 $.utile.tips(data[0].message);
 * setTimeout(function(){window.location.href=data[0].url},1000); }
 * if(data[0].mark==1){ $.utile.tips(data[0].message); } } ); }, cancel: true
 * }); }
 ******************************************************************************/

/**
 * 显示人员选择器 list页面，checkBox选择数据
 */
function selectAll(checkbox, domname) {
	var doms = document.getElementsByName(domname);
	for ( var i = 0; i < doms.length; i++) {
		if (doms[i].type == "checkbox") {
			doms[i].checked = checkbox.checked;
		}
	}
}
/**
 * 功能描述：弹出人员选择对话框
 * @returns
 */
function showUserSelector(){
	//项目的contextPath,如果有必要，请修改
	var path="";
	var obj = new Object();
	obj.name = "title";
	var rv = window.showModalDialog(path+"/compoent/userSelect",obj,'dialogWidth=520px;dialogHeight=420px,center=yes,help=1,resizable=0,status=0,scroll=2,edge=sunken,unadorned=yes,dialogHide=0');
	if(rv!=undefined){ 
		if($("#personId").val()!=undefined){
			$("#personId").val(rv.split("|")[0]);
		//	alert($("#personId").val());
		}
		return rv;
	}
	return "";
}
function getSelectedUser(userIds,userNames){
	var result= showUserSelector();
	if($.trim(result).length>0){
		$('#'+userIds).val(result.split("|")[0]);
		$('#'+userNames).val(result.split("|")[1]);
	}
}