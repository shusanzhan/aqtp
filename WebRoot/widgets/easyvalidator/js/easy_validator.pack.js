/*
	Copyright (C) 2009 - 2012
	WebSite:	Http://wangking717.javaeye.com/
	Author:		wangking
 */
$(function() {

	var inps = $("input");
	for ( var i = 0; i < inps.length; i++) {
		var check_type = $(inps[i]).attr("checkType");
		if (/^integer/.test(check_type) || /^integer/.test(check_type)) {
			// integer;float
			// onkeypress="return checkInput('float',this)"
			// style="ime-mode:Disabled"
			$(inps[i]).keypress(function(event) {
				return checkInput(event, this);
			});
			// style="ime-mode:Disabled"禁止输入法
			$(inps[i]).css("ime-mode", "Disabled");
		}
	}

	var xOffset = -20; // x distance from mouse
	var yOffset = 20; // y distance from mouse

	// input action
	$("[checkType],[reg],[url]:not([reg]),[tip]").hover(
			function(e) {
				if ($(this).attr('tip') != undefined) {
					var top = (e.pageY + yOffset);
					var left = (e.pageX + xOffset);
					$('body').append(
							'<p id="vtip"><img id="vtipArrow" src="/widgets/easyvalidator/images/vtip_arrow.png"/>'
									+ $(this).attr('tip') + '</p>');
					$('p#vtip').css("top", top + "px").css("left",
							left + "px");
					$('p#vtip').bgiframe();
				}
			}, function() {
				if ($(this).attr('tip') != undefined) {
					$("p#vtip").remove();
				}
			}).mousemove(
			function(e) {
				if ($(this).attr('tip') != undefined) {
					var top = (e.pageY + yOffset);
					var left = (e.pageX + xOffset);
					$("p#vtip").css("top", top + "px").css("left",
							left + "px");
				}
			}).blur(function() {
				if ($(this).attr("url") != undefined) {
					ajax_validate($(this));
				} else if ($(this).attr("reg") != undefined||$(this).attr("checkType") != undefined) {
					validate($(this));
				}
	});
});

function validateForm(frmId){
	var isSubmit = true;
	var objectO=$("#frmId").find("[checkType],[reg],[url]:not([reg])");
	for(var i=0;i<objectO.length;i++){
		var obj=objectO[i];
		if ($(obj).attr("reg") == undefined&&$(obj).attr("checkType") == undefined) {
			if (!ajax_validate($(this))) {
				isSubmit = false;
			}
		} else {
			if (!validate($(obj))) {
				isSubmit = false;
			}
		}
	}
	if (typeof (isExtendsValidate) != "undefined") {
		if (isSubmit && isExtendsValidate) {
			return extendsValidate();
		}
	}
	return isSubmit;
}

//方法目前还未完全考虑ajax验证
function validate(obj) {
	var reg = new RegExp(obj.attr("reg"));
	var regString=obj.attr("reg");
	var url=obj.attr("url");
	var objValue = obj.attr("value");
	//第一步判断reg正则表达式是否为undefined，如果不为undefined支持原生的验证
	if(reg!=undefined&&regString!=undefined){
		if(!reg.test(objValue)){ //验证不通过
			change_error_style(obj,"add");
			change_tip(obj,null,"remove"); 
			return false; 
		 }else{ //验证通过情况，1、如果url为undefined，那么没有进行ajax验证，2、如果url不为undeifned，还要进行ajax验证
			if(obj.attr("url") == undefined){ 
				change_error_style(obj,"remove");
				change_tip(obj,null,"remove"); 
				return true; 
			}else{ 
				return
				ajax_validate(obj); 
			} 
		  }
	}
/*	if(url!=undefined){
		if(obj.attr("url") == undefined){ 
			change_error_style(obj,"remove");
			change_tip(obj,null,"remove"); 
			return true; 
		}else{ 
			return
			ajax_validate(obj); 
		} 
	}*/
	if (obj.attr('checkType') == "phoneNo") {
		if (!checkPhoneNo(obj)) {
			change_error_style(obj, "add");
			change_tip(obj, null, "remove");
			return false;
		} else {
			change_error_style(obj, "remove");
			change_tip(obj, null, "remove");
			return true;
		}
	} else if (obj.attr('checkType') == "email") {
		if (!checkEmail(obj)) {
			change_error_style(obj, "add");
			change_tip(obj, null, "remove");
			return false;
		} else {
			change_error_style(obj, "remove");
			change_tip(obj, null, "remove");
			return true;
		}
	} else if (obj.attr('checkType') == "chinese") {
		if (!checkChinese(obj)) {
			change_error_style(obj, "add");
			change_tip(obj, null, "remove");
			return false;
		} else {
			change_error_style(obj, "remove");
			change_tip(obj, null, "remove");
			return true;
		}
	} else if (obj.attr('checkType') == "IDCard") {
		var state = checkIDCard(obj);
		if (!checkIDCard(obj)) {
			change_error_style(obj, "add");
			change_tip(obj, null, "remove");
			return false;
		} else {
			change_error_style(obj, "remove");
			change_tip(obj, null, "remove");
			return true;
		}
	} else if (obj.attr('checkType') == "zipCode") {
		if (!checkZipCode(obj)) {
			change_error_style(obj, "add");
			change_tip(obj, null, "remove");
			return false;
		} else {
			change_error_style(obj, "remove");
			change_tip(obj, null, "remove");
			return true;
		}
	} else if (/^integer/.test(obj.attr('checkType'))) {
		if (!checkInteger(obj)) {
			change_error_style(obj, "add");
			change_tip(obj, null, "remove");
			return false;
		} else {
			change_error_style(obj, "remove");
			change_tip(obj, null, "remove");
			return true;
		}
	}else if (/^float/.test(obj.attr('checkType'))) {
		if (!checkFloat(obj)) {
			change_error_style(obj, "add");
			change_tip(obj, null, "remove");
			return false;
		} else {
			change_error_style(obj, "remove");
			change_tip(obj, null, "remove");
			return true;
		}
	} else if (/^string/.test(obj.attr('checkType'))) {
		var state = checkString(obj);
		if (!checkString(obj)) {
			change_error_style(obj, "add");
			change_tip(obj, null, "remove");
			return false;
		} else {
			change_error_style(obj, "remove");
			change_tip(obj, null, "remove");
			return true;
		}
	}
}

function ajax_validate(obj) {
	var url_str = obj.attr("url");
	if (url_str.indexOf("?") != -1) {
		url_str = url_str + "&" + obj.attr("name") + "=" + obj.attr("value");
	} else {
		url_str = url_str + "?" + obj.attr("name") + "=" + obj.attr("value");
	}
	var feed_back = $.ajax({
		url : url_str,
		cache : false,
		async : false
	}).responseText;
	feed_back = feed_back.replace(/(^\s*)|(\s*$)/g, "");
	if (feed_back == 'success') {
		change_error_style(obj, "remove");
		change_tip(obj, null, "remove");
		return true;
	} else {
		change_error_style(obj, "add");
		change_tip(obj, feed_back, "add");
		return false;
	}
}

function change_tip(obj, msg, action_type) {
	if (obj.attr("tip") == undefined) {// 初始化判断TIP是否为空
		obj.attr("is_tip_null", "yes");
	}
	if (action_type == "add") {
		if (obj.attr("is_tip_null") == "yes") {
			obj.attr("tip", msg);
		} else {
			if (msg != null) {
				if (obj.attr("tip_bak") == undefined) {
					obj.attr("tip_bak", obj.attr("tip"));
				}
				obj.attr("tip", msg);
			}
		}
	} else {
		if (obj.attr("is_tip_null") == "yes") {
			obj.removeAttr("tip");
			obj.removeAttr("tip_bak");
		} else {
			obj.attr("tip", obj.attr("tip_bak"));
			obj.removeAttr("tip_bak");
		}
	}
}

function change_error_style(obj, action_type) {
	var normal=obj.attr("class");
	var fail=normal.split(" ")[0]+" input_validation-failed";
	var success=normal.split(" ")[0]+" field";
	if (action_type == "add") {//添加错误样式
		if(normal.indexOf("input_validation-failed")>0){//如果包含错误样式
			obj.removeClass(normal);
			obj.addClass(success);
		}else{
			obj.removeClass(success);
			obj.addClass(fail);
		}
	} else {//删除错我样式
		obj.removeClass(normal);
		obj.addClass(success);
	}
}

$.fn.validate_callback = function (msg, action_type, options) {
	this.each(function() {
		if (action_type == "failed") {
			change_error_style($(this), "add");
			change_tip($(this), msg, "add");
		} else {
			change_error_style($(this), "remove");
			change_tip($(this), null, "remove");
		}
	});
};

function checkEmail(obj) {
	var value = obj.attr("value");
	if (obj.attr("canEmpty") == "Y" && value.length == 0)
		return true;
	return (/^([\.\w-]){3,}@([\w-]){2,}(\.([\w]){2,4}){1,}$/.test(value));
}
/**
 * 功能：判断电话号码是否合法，电话号码的格式为：电话号码；或者 区号-电话号码
 */
function checkPhoneNo(obj) {
	var value = obj.attr("value");
	if (obj.attr("canEmpty") == "Y" && value.length == 0)
		return true;
	var valid = false;
	valid = /^\d{3}-\d{8}$|^\d{4}-\d{7}$/.test(value);
	return valid;
}
/**
 * 输入框只能输入中文
 * 
 * @param obj
 * @returns
 */
function checkChinese(obj) {
	var value = obj.attr("value");
	if (obj.attr("canEmpty") == "Y" && value.length == 0)
		return true;
	return (/^[\u4e00-\u9fa5]+$/.test(value));
}
/**
 * 判断身份证
 * 
 * @param obj
 * @returns
 */
function checkIDCard(obj) {
	var value = obj.attr("value");
	if (obj.attr("canEmpty") == "Y" && value.length == 0)
		return true;
	if (value.length == 15)
		return (/^([0-9]){15,15}$/.test(value));
	if (value.length == 18)
		return (/^([0-9]){17,17}([0-9xX]){1,1}$/.test(value));
	return false;
}
/**
 * 功能：判断邮编是否合法
 * 
 * @param obj
 * @return
 */
function checkZipCode(obj) {
	var value = obj.attr("value");
	if (obj.attr("canEmpty") == "Y" && value.length == 0)
		return true;
	var valid = false;
	valid = /^[1-9][0-9]{5}$/.test(value);
	return valid;
}
/**
 * 验证整数，先判断是否为整数，为整数在判断是否在给定的区间类
 * 
 * @param obj（实例：checkType=“integer,1,100”）
 * @returns {Boolean}
 */
function checkInteger(obj) {
	var value = obj.attr("value");
	if (obj.attr('canEmpty') == "Y" && value.length == 0)
		return true;
	if (!(/^([-]){0,1}([0-9]){1,}$/.test(value))) {
		return false;
	}
	var integerValue = parseInt(value);
	var arr = obj.attr('checkType').split(",");
	var smallInteger = parseInt(arr[1]);
	var bigInteger = parseInt(arr[2]);
	if (integerValue < smallInteger) {
		return false;
	}
	if (integerValue > bigInteger) {
		return false;
	}
	return true;
}
/**
 * 验证整数，先判断是否为字符串，为字符串在判断是否在给定的区间类
 * 
 * @param obj（实例：checkType=“string,6,20”）
 * @returns {Boolean}
 */
function checkString(obj) {
	var value = obj.attr("value");
	if (obj.attr('canEmpty') == "Y" && value.length == 0)
		return true;
	var length = value.length;
	var arr = obj.attr('checkType').split(",");
	var smallLength = parseInt(arr[1]);
	var bigLength = parseInt(arr[2]);

	if (length < smallLength) {
		return false;
	}
	if (length > bigLength) {
		return false;
	}
	return true;
}
/**
 * 验证浮点数，先判断是否为浮点数，为整数在判断是否在给定的区间类
 * 
 * @param obj（实例：checkType=“float,1.0,100.0”）
 * @returns {Boolean}
 */
function checkFloat(obj)
{	
	var value = obj.attr("value");
	if(obj.attr('can_empty')=="Y" && value.length==0) 
		return true;
	if(!(/^([-]){0,1}([0-9]){1,}([.]){0,1}([0-9]){0,}$/.test(value)))
	{
		return false;
	}
	var floatValue = parseFloat(value);
	var arr = obj.attr('checkType').split(",");
	var smallFloat = parseFloat(arr[1]);
	var bigFloat = parseFloat(arr[2]);
	if(floatValue<smallFloat)
	{
		return false;
	}
	if(floatValue > bigFloat)
	{
		return false;
	}
	return true;
}
/**
 * 对输入框限制输入,限制是数字和小数 checktype:integer;float
 * isNegative:是否是负数,默认只能是正数,为true时,也可以是负数(未实现) onkeypress="return
 * checkInput('float',this)" style="ime-mode:Disabled"禁用输入法
 */
function checkInput(event, obj, isNegative) {
	var userAgent = navigator.userAgent.toLowerCase();
	var isSafari = userAgent.indexOf("Safari") >= 0;
	var is_opera = userAgent.indexOf('opera') != -1 && opera.version();
	var is_moz = (navigator.product == 'Gecko')
			&& userAgent.substr(userAgent.indexOf('firefox') + 8, 3);
	var is_ie = (userAgent.indexOf('msie') != -1 && !is_opera)
			&& userAgent.substr(userAgent.indexOf('msie') + 5, 3);
	// 当前光标所在的位置
	var result = 0;
	var curStr = obj.value;
	var checkType = obj.getAttribute("checkType");
	var keycode = "";
	// 判断浏览器类型
	if (navigator.appName == "Microsoft Internet Explorer") {
		keycode = event.keyCode;
		var rng;
		if (obj.tagName == "TEXTAREA") { // 如果是文本域
			rng = event.srcElement.createTextRange();
			rng.moveToPoint(event.x, event.y);
		} else { // 输入框
			rng = document.selection.createRange();
		}
		rng.moveStart("character", -event.srcElement.value.length);
		result = rng.text.length;
	}
	if (is_moz != false) {
		keycode = event.which;
		result = obj.selectionStart;
		if (keycode == 8) {
			return true;
		}
	} else {
		// return true;
		keycode = event.which;
		result = obj.selectionStart;
		if (keycode == 8) {
			return true;
		}
	}
	var str = String.fromCharCode(keycode);
	curStr = curStr.substring(0, result) + str
			+ curStr.substring(result, curStr.length);
	// 进行整数验证
	if (/^integer/.test(checkType)) {
		return (curStr.search(/^(\-)?(\d+)?$/) != -1);
	}
	// 进行数字类型验证，可以输入负数，小数
	else if (/^float/.test(checkType)) {
		return (curStr.search(/^(\-)?(\d+)?(\.)?(\d+)?$/) != -1);
	}
}
