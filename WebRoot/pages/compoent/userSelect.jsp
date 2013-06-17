<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../commons/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" href="${ctx }/widgets/userSelect/css/ui-lightness/jquery-ui-1.8.13.custom.css" rel="stylesheet" />	
<script type="text/javascript" src="${ctx }/widgets/userSelect/js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="${ctx }/widgets/userSelect/js/jquery-ui-1.8.13.custom.min.js"></script>
<title>人员选择器</title>
<style type="text/css">

</style>
</head>

<script type="text/javascript">
	$(function() {
		$("#accordion").accordion({ header: "h3" });
		$.post("${ctx}/compoent/getUser?data="+new Date(),{},function callback(data){
			$("#user").append(data);	
		});
	});

	//全局变量保存《a》标签的innerHTML
	var flag="aus";
	function getAvalue(a){
		flag=a.id;
	}
	
	function selectUserAll(){
		$("#user").empty();
		$.post("${ctx}/compoent/getUser?data="+new Date(),{},function callback(data){
			$("#user").append(data);	
		});
	}
	function selectDepartmentAll(){
		$("#department").empty();
		$.post("${ctx}/compoent/getDepartment?data="+new Date(),{},function callback(data){
			$("#department").append(data);		
		});
	}
	function selectDepartmentUserAll(){
		$("#departmentUserCheck").empty();
		$.post("${ctx}/compoent/getDepartment?data="+new Date(),{},function callback(data){
			$("#departmentUserCheck").append(data);		
		});
	}
	function selectPostUser(){
		$("#postUserCheck").empty();
		$.post("${ctx}/compoent/getPost?data="+new Date(),{},function callback(data){
			$("#postUserCheck").append(data);		
		});
	}
	function selectPersonGroup(){
		$("#personGruopUserCheck").empty();
		$.post("${ctx}/compoent/getPersonalGroup?data="+new Date(),{},function callback(data){
			$("#personGruopUserCheck").append(data);		
		});
	}
	//通过部门ID查询人员信息
	function getUserByDepartmentId(){
		var options=$("#departmentUserCheck option:selected");
		var department=options[0].value;
		var departmentId=department.substr(0,department.indexOf("du"));

		//在change事件完成前清空departmentUser已经有的option
		$("#departmentUser").empty();
		$.post("${ctx}/compoent/getUserByDepartmentId?departmentId="+departmentId+"&date="+new Date(),function callback(data){
			$("#departmentUser").append(data);
		});
	}
	//通过岗位ID查询人员信息
	function getUserByPostId(){
		var options=$("#postUserCheck option:selected");
		var post=options[0].value;
		var postId=post.substr(0,post.indexOf("pu"));

		//在change事件完成前清空departmentUser已经有的option
		$("#postUser").empty();
		$.post("${ctx}/compoent/getUserByPostId?postId="+postId+"&date="+new Date(),function callback(data){
			$("#postUser").append(data);
		});
	}
	function getUserByPgId(){
		var options=$("#personGruopUserCheck option:selected");
		var zu=options[0].value;
		var zuId=zu.substr(0,zu.indexOf("zu"));

		//在change事件完成前清空departmentUser已经有的option
		$("#zUser").empty();
		$.post("${ctx}/compoent/getUserByZuId?zuId="+zuId+"&date="+new Date(),function callback(data){
			$("#zUser").append(data);
		});
	}
	
	function addSelected(selected){
		var userSelected = $("#"+selected+" option:selected");
		if(userSelected!=null&&userSelected.length>0){
			for(var i=0;i<userSelected.length;i++){
				var removeSelected =userSelected[i];
				//重新生成一个option用于append到左边栏的选项中
				var option="<option value='"+removeSelected.value+"'>"+removeSelected.text+"</option>";
				$("#"+selected+" option[value='"+removeSelected.value+"']").remove();
				$("#resultSelected").append(option);
			}
		}
	}
	
	function add(){
		if(flag=="aus"){
			addSelected("user");
		}
		if(flag=="adp"){
			addSelected("department");
		}
		if(flag=="adu"){
			addSelected("departmentUser");
		}
		if(flag=="apu"){
			addSelected("postUser");
		}
		if(flag=="azu"){
			addSelected("zUser");
		}
	}
	
	function remove(){
		removeClick();
	}
	
	//添加全部
	function addAll(){
		var userSelectAll=null;
		if(flag=="aus"){
			userSelectAll = $('#user option');	
		}
		if(flag=="adp"){
			userSelectAll = $('#department option');
		}
		if(flag=="adu"){
			userSelectAll = $('#departmentUser option');
		}
		if(flag=="apu"){
			userSelectAll = $('#postUser option');
		}
		if(flag=="azu"){
			userSelectAll = $('#zUser option');
		}
		userSelectAll.appendTo('#resultSelected');   
	}
	//删除全部
	function removeAll(){
		//获取全部选中Select的option
		var options = $('#resultSelected option');
		if(options.length>0){
			for(var i=0;i<options.length;i++){
				//遍历整个选择的option
				var removeSelected =options[i];
				//重新生成一个option用于append到左边栏的选项中
				var option="<option value='"+removeSelected.value+"'>"+removeSelected.text+"</option>";
				//删除选择中的option
				$("#resultSelected option[value='"+removeSelected.value+"']").remove();
				/**根据选择的类型添加到不同的Select后面,
					u:代表所有人员的select;
					d：代表选择的部门的Select;
					du:代表部门选择人员的select；
					pu：代表岗位选择人员的Select；
					zu:代表自定义用户组的Select
				**/
				if(removeSelected.value.indexOf("us")>0){
					$("#user").append(option);
				}else if(removeSelected.value.indexOf("dp")>0){
					$("#department").append(option);
				}else if(removeSelected.value.indexOf("du")>0){
					$("#departmentUser").append(option);
				}else if(removeSelected.value.match("pu")){
					$("#postUser").append(option);
				}else if(removeSelected.value.match("zu")){
					$("#zUser").append(option);
				}
			}
		}else{
			return ;
		}
	}

	//双击添加选择了人员|部门信息到右边列表
	function addClick(selectId){
		var options=$("#"+selectId+" option:selected");
		if(options.length>0){
			for(var i=0;i<options.length;i++){
				//遍历整个选择的option
				var removeSelected =options[i];
				//重新生成一个option用于append到左边栏的选项中
				var option="<option value='"+removeSelected.value+"'>"+removeSelected.text+"</option>";
				//删除选择中的option
				$("#"+selectId+" option[value='"+removeSelected.value+"']").remove();
				$("#resultSelected").append(option);
			}
		}
	}
	
	//双击已经选择用户进行用户删除操作
	function removeClick(){
		//获取全部选中Select的option
		var options = $('#resultSelected option:selected');
		if(options.length>0){
			for(var i=0;i<options.length;i++){
				//遍历整个选择的option
				var removeSelected =options[i];
				//重新生成一个option用于append到左边栏的选项中
				var option="<option value='"+removeSelected.value+"'>"+removeSelected.text+"</option>";
				//删除选择中的option
				$("#resultSelected option[value='"+removeSelected.value+"']").remove();
				/**根据选择的类型添加到不同的Select后面,
					u:代表所有人员的select;
					d：代表选择的部门的Select;
					du:代表部门选择人员的select；
					pu：代表岗位选择人员的Select；
					zu:代表自定义用户组的Select
				**/
				if(removeSelected.value.indexOf("us")>0){
					$("#user").append(option);
				}else if(removeSelected.value.indexOf("dp")>0){
					$("#department").append(option);
				}else if(removeSelected.value.indexOf("du")>0){
					$("#departmentUser").append(option);
				}else if(removeSelected.value.match("pu")){
					$("#postUser").append(option);
				}else if(removeSelected.value.match("zu")){
					$("#zUser").append(option);
				}
			}
		}else{
			return ;
		}
	}

	function ok(){
		var options=$("#resultSelected")[0];
		var resultIds="";
		var resultNames="";
		if(options.length>0){
			for(var i=0;i<options.length;i++){
				var removeSelected =options[i];
				resultIds+=removeSelected.value+",";
				resultNames+=removeSelected.text+",";
			}
		}
		var param="resultIds="+resultIds+"&resultNames="+encodeURIComponent(resultNames);
		$.post("${ctx}/compoent/analyticSelectedData?&data="+new Date(),param,function callback(data){
			window.returnValue=data;
			window.close();	
		});
	}
	function selectAll(){
		$.post("${ctx}/compoent/getAllUsers?data="+new Date(),{},function callback(data){
			window.returnValue=data;
			window.close();	
		});
	}
</script>
<style type="text/css">
	body{ font: 62.5% "Trebuchet MS", sans-serif;margin: 0 auto; }
	.demoHeaders { margin-top: 2em; }
	#dialog_link {padding: .4em 1em .4em 20px;text-decoration: none;position: relative;}
	#dialog_link span.ui-icon {margin: 0 5px 0 0;position: absolute;left: .2em;top: 50%;margin-top: -8px;}
	ul#icons {margin: 0; padding: 0;}
	ul#icons li {margin: 2px; position: relative; padding: 4px 0; cursor: pointer; float: left;  list-style: none;}
	ul#icons span.ui-icon {float: left; margin: 0 4px;}
</style>	
<body>
<input type="hidden" name="allUsers" value="" id="allUsers">
	<div style="width: 460px;text-align: right;margin: 0 auto;">
		<input type="button" name="" value="全部人员" onclick="selectAll();" ></input>
		<input type="button" name="" value="确定" onclick="ok();">
		<input type="button" name="" value="关闭" onclick="window.close();"></input>
	</div>
	<table width="460" style="border: 1px solid #68ADF5;margin-top: 5px;" cellpadding="0" cellspacing="0" align="center">
		<tr>
			<td width="200" align="center">
				<div id="accordion" style="width: 192px;margin: 0 auto;">
						<h3 id="link_01" onclick="selectUserAll();" style=""><a href="#" id="aus" onclick="getAvalue(this);">所有人员</a></h3>
						<div id="medul_01" style="height: 160px;">
							<select id='user' name='user' style='width:100%;' ondblclick="addClick('user')" multiple='multiple' size="12">
							</select>
						</div>
						<h3 id="link_02" onclick="selectDepartmentAll()"><a href="#" id="adp" onclick="getAvalue(this);">部门选择</a></h3>
							<div id="medul_02">
								<select id='department' name='department' ondblclick="addClick('department')" style="width: 100%;"  multiple='multiple' size="12">
								</select>
							</div>	
						<h3 id="link_03" onclick="selectDepartmentUserAll()"><a href="#" id="adu" onclick="getAvalue(this);">按部门选择人员</a></h3>
						<div id="medul_03">
							<table style="width: 100%;">
								<tr>
								<td height="20">
									<select id='departmentUserCheck' name='departmentUserCheck' onchange="getUserByDepartmentId();" style='width:100%;'>
									</select>
								</td>
								</tr>
								<tr>
								<td>
									<select id="departmentUser" name="departmentUser" ondblclick="addClick('departmentUser')" style="width: 100%"; multiple="multiple" size="10">
									</select>
								</td>
								</tr>
							</table>
						</div>
						<h3 id="link_04" onclick="selectPostUser();"><a href="#" id="apu" onclick="getAvalue(this);">按岗位选择</a></h3>
						<div id="medul_04">
							<table style="width: 100%;">
								<tr>
								<td height="20">
									<select id='postUserCheck' name='postUserCheck' onchange="getUserByPostId()" style='width:100%;'>
									</select>
								</td>
								</tr>
								<tr>
								<td>
									<select id='postUser' name='postUser' ondblclick="addClick('postUser')" style="width: 100%;"  multiple='multiple' size="12">
									</select>
								</td>
								</tr>
							</table>
							
						</div>
						<h3 id="link_05" onclick="selectPersonGroup();"><a href="#" id="azu" onclick="getAvalue(this);">自定义用户组</a></h3>
						<div id="medul_05">
							<table style="width: 100%;">
								<tr>
								<td height="20">
									<select id='personGruopUserCheck' name='personGruopUserCheck' onchange="getUserByPgId()" style='width:100%;'>
									</select>
								</td>
								</tr>
								<tr>
								<td>
									<select id='zUser' name='zUser' ondblclick="addClick('zUser')" style="width: 100%;"  multiple='multiple' size="12">
									</select>
								</td>
								</tr>
							</table>
							
						</div>
				</div>
			</td>
			<td width="60" align="center">
				<div style="margin: 0 auto;width: 52px;">
					<table align="center">
						<tr>
							<td><input type="button" id="add" onclick="add()" value=">">
							</td>
						</tr>
						<tr>
							<td><input type="button" id="remove" onclick="remove()" value="&lt;">
							</td>
						</tr>
						<tr>
							<td><input type="button" id="addAll" onclick="addAll()" value=">>">
							</td>
						</tr>
						<tr>
							<td><input type="button" id="removeAll" onclick="removeAll()" value="&lt;&lt;">
							</td>
						</tr>
					</table>
				</div></td>
			<td width="200">
				<div style="margin: 0 auto;width: 192px;">
					<select id="resultSelected" name="resultSelected" multiple="multiple" ondblclick="removeClick()" size="20" style="width: 100%;">
					</select>
				</div></td>
		</tr>
	</table>
</body>
</html>