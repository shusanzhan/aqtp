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
<title>添加饲料信息</title>
<script type="text/javascript">
function add(){
	try{
		var userSelected = $('#Breeders option:selected');
		if(userSelected!=null&&userSelected.length>0){
			for(var i=0;i<userSelected.length;i++){
				var removeSelected =userSelected[i];
				//重新生成一个option用于append到左边栏的选项中
				var option="<option value='"+removeSelected.value+"'>"+removeSelected.text+"</option>";
				$("#Breeders option[value='"+removeSelected.value+"']").remove();
				$("#BreedersSelected").append(option);
			}
		}
	}catch(e){
		
	}
	
}
//双击添加选择了人员|部门信息到右边列表
function addClick(){
	var options=$("#Breeders option:selected");
	if(options.length>0){
		for(var i=0;i<options.length;i++){
			//遍历整个选择的option
			var removeSelected =options[i];
			//重新生成一个option用于append到左边栏的选项中
			var option="<option value='"+removeSelected.value+"'>"+removeSelected.text+"</option>";
			//删除选择中的option
			$("#Breeders option[value='"+removeSelected.value+"']").remove();
			$("#BreedersSelected").append(option);
		}
	}
}
//双击添加选择了人员|部门信息到右边列表
function removeClick(){
	var options=$("#BreedersSelected option:selected");
	if(options.length>0){
		for(var i=0;i<options.length;i++){
			//遍历整个选择的option
			var removeSelected =options[i];
			//重新生成一个option用于append到左边栏的选项中
			var option="<option value='"+removeSelected.value+"'>"+removeSelected.text+"</option>";
			//删除选择中的option
			$("#BreedersSelected option[value='"+removeSelected.value+"']").remove();
			$("#Breeders").append(option);
		}
	}
}
function remove(){
	//获取全部选中Select的option
	var options = $('#BreedersSelected option:selected');
	if(options.length>0){
		for(var i=0;i<options.length;i++){
			//遍历整个选择的option
			var removeSelected =options[i];
			//重新生成一个option用于append到左边栏的选项中
			var option="<option value='"+removeSelected.value+"'>"+removeSelected.text+"</option>";
			//删除选择中的option
			$("#BreedersSelected option[value='"+removeSelected.value+"']").remove();
			$("#Breeders").append(option);
		}
	}else{
		return ;
	}
}

//添加全部
function addAll(){
	var userSelectAll=null;
	userSelectAll = $("#Breeders option");
	userSelectAll.appendTo('#BreedersSelected');   
}
//删除全部
function removeAll(){
	//获取全部选中Select的option
	var options = $('#BreedersSelected option');
	if(options.length>0){
		for(var i=0;i<options.length;i++){
			//遍历整个选择的option
			var removeSelected =options[i];
			//重新生成一个option用于append到左边栏的选项中
			var option="<option value='"+removeSelected.value+"'>"+removeSelected.text+"</option>";
			//删除选择中的option
			$("#BreedersSelected option[value='"+removeSelected.value+"']").remove();
			$("#Breeders").append(option);
		}
	}else{
		return ;
	}
}
function ok(){
	var options=$("#BreedersSelected")[0];
	var resultIds="";
	var resultNames="";
	if(options.length>0){
		for(var i=0;i<options.length;i++){
			var removeSelected =options[i];
			resultIds+=removeSelected.value+",";
			resultNames+=removeSelected.text+",";
		}
	}
	$("#breederIds").val(resultIds);
}
</script>
</head>
<body class="bodycolor">
	<br>
	<form action="" name="frmId" id="frmId" style="margin-bottom: 40px;" target="_parent">
		<s:token></s:token>
		<input type="hidden" name="breederIds" id="breederIds" value="">
		<input type="hidden" name="chickenBatchDbid" id="chickenBatchDbid" value="${param.chickenBatchDbid }">
		<table border="1" align="center" cellpadding="0" cellspacing="0" style="width: 92%;">
			<tr height="40">
				<td class="formTableTdLeft" align="center">可选饲养员信息&nbsp;</td>
				<td rowspan="2" align="center" style="padding-left: 1px;">
					<table align="center">
						<tr>
							<td><a href="javascript:void(-1)"  id="add" onclick="add()" >></a>
							</td>
						</tr>
						<tr>
							<td><a href="javascript:void(-1)"  id="remve" onclick="remove()" style="width: 30px;height: 20px;line-height: 20px;background-color: blue;color: white;outline: none;list-style: none;">&lt;</a>
							</td>
						</tr>
						<tr>
							<td><a href="javascript:void(-1)"  id="addAll" onclick="addAll()" >>></a>
							</td>
						</tr>
						<tr>
							<td><a href="javascript:void(-1)"  id="removeAll" onclick="removeAll()" >&lt;&lt;</a>
							</td>
						</tr>
					</table>
				</td>
				<td class="formTableTdLeft" align="center">已选饲养员&nbsp;</td>
			</tr>
			<tr height="300">
				<td width="240">
					<select name="Breeders" id="Breeders"  multiple="multiple" style="width: 230px;height: 130px" ondblclick="addClick()">
						<c:forEach items="${breeders }" var="breeder">
							<option value="${breeder.dbid }">${breeder.name }</option>
						</c:forEach>
					</select>
				</td>
				<td width="240" height="140">
					<select name="BreedersSelected" id="BreedersSelected"  multiple="multiple" style="width: 230px;height: 130px" ondblclick="removeClick()">
					</select>
				</td>	
			</tr>
		</table>
		<div class="buttons" style="margin-top: 20px;">
			<a href="javascript:void()"
					onclick="ok();$.utile.submitForm('frmId','${ctx}/chickenBatch/saveBreeder')"
					class="ui-state-default">保存</a> 
		</div>
	</form>
</body>
</html>