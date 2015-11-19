<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>查看列表</title>
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/easyUI/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyUI/themes/icon.css">
<script type="text/javascript" src="<%=path%>/js/easyUI/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/easyUI/jquery.easyui.min.js"></script>

<style type="text/css">
	body{background: url(css/img/bg.png);}
	#addRole{margin-left: 65px;}
	#addPower{margin-left: 65px;margin-top: 10px;}
</style>


<script>

	function getdata(){
		$('#treegrid').treegrid({
		    url:'<%=basePath%>/power.do?method=getpowertree',
		    idField:'id',
		    treeField:'name',
		    rownumbers: true,
		    fitColumns:true,
		    pageSize: 5,//每页显示的记录条数，默认为10  
	        pageList: [5,10,15,20,25,100],//可以设置每页记录条数的列表  
		    pagination: true,//是否这是分页
		    columns:[[
				{title:'角色名称/功能名称',field:'name',width:180},
		    ]],
		    toolbar:[
					   {//添加数据
						   text:"添加",
						   iconCls: "icon-add",
						   handler: _insertRow,
						
					   },'-',{//添加删除
						   text:"删除",
						   iconCls: "icon-add",
						   handler: _Remove,
						
					   },'-',
			],
			onLoadSuccess: function(){
				$('#treegrid').treegrid('collapseAll');
				doedit = undefined;
			},
		});
		//分页设置
		var p = $('#treegrid').treegrid('getPager');
		$(p).pagination({
	        beforePageText: '第',//页数文本框前显示的汉字  
	        afterPageText: '页    共 {pages} 页',  
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
	        BeforeRefresh:function(){
				$(this).treegrid('reload'); 
				//获取数据库全部数据
			},
		});
		
	}
	//-------------------增加数据-----------------
	function _insertRow(){
		$('#adddialog').dialog('open');
	};
	//--------------------删除数据-----------------
	function _Remove(){
		doedit = $('#treegrid').treegrid('getSelected');
		if(null==doedit){
			$.messager.alert('提示','请选择相应权限','info');
			return;
		}
		var index = doedit.id;
		if(index.indexOf("_")==-1){
			$.messager.alert('提示','请选择相应权限','info');
		}else{
			var temp = index.indexOf("_");
			var roleid = index.substring(0,temp);
			var functionid = index.substring(temp+1,index.length);
			$.ajax({
				url:'<%=basePath%>/power.do?method=deleterolepower',
				type:'post',
				data:{roleid:roleid,functionid:functionid},
				success:function(data){
					if(data==1){
						$.messager.alert('提示','删除成功','info');
						$('#treegrid').treegrid('reload'); 
					}else{
						$.messager.alert('提示','删除失败','info');
						$('#treegrid').treegrid('reload'); 
					}
				},error:function(){
					alert('fail');
				}
			});
		}
	};
	//---------------为下拉框赋值---------------
	function GetAddDialog(){
		$.ajax({
			url:'<%=basePath%>/role.do?method=getallrole',
			type:'POST',
			data:{},
			success:function(data){
				var total = data.roles;
				var str="";
				for(var i=0;i<total.length;i++){
					var rolename = total[i].rolename;
					var id = total[i].id;
					str+="<option value="+id+">"+rolename+"</option>";
				}
				$('#addRole').html(str);
			},error:function(){
				alert('fail');
			}
				
		});
		
		$.ajax({
			url:'<%=basePath%>/function.do?method=getfunction',
			type:'POST',
			data:{},
			success:function(data){
				var str="";
				for(var i=0;i<data.length;i++){
					var functionname = data[i].functionname;
					var id = data[i].id;
					str+="<option value="+id+">"+functionname+"</option>";
				}
				$('#addPower').html(str);
			},error:function(){
				alert('fail');
			}
				
		});
	}
	//---------------------添加角色权限--------------------
	function AddRoleFunction(roleid,functionid){
		$.ajax({
			url:'<%=basePath%>power.do?method=addrolepower',
			type:'post',
			data:{roleid:roleid,functionid:functionid},
			success:function(data){
				if(data==1){
					$.messager.alert('提示','该角色已拥有该权限','info');
				}else if(data==2){
					$.messager.alert('提示','添加权限成功','info');
					$('#adddialog').dialog('close');
					$('#treegrid').treegrid('reload'); 
				}
			},error:function(){
				alert("fail");
			}
		});
	}
	//------------主体部分----------------------------
	var doedit = undefined;//用来记录当前编辑的行，如果没有编辑的行则置为undefined
	$(function(){
		$('#adddialog').dialog('close');
		getdata();
		GetAddDialog();
		$('#PowerBtn').click(function(){
			var roleid = $('#addRole').val();
			var functionid = $('#addPower').val();
			AddRoleFunction(roleid,functionid);
		});
	});
	
    
</script>


</head>

<body bgcolor="#DDF3FF" class = "h2">
	<table id="treegrid"></table>
	
	<div id="adddialog" class="easyui-dialog" title="添加" style="width:400px;height:200px;"
    data-options="iconCls:'icon-save',resizable:true,modal:true">
    	<div id="totalplane" style="margin-top: 36px;padding-left: 60px;">
	  		<select id="addRole" class="<a href="http://wuzhuti.cn/tag/easyui/" title="EasyUI">EasyUI</a>-combobox" name="dept" style="width:300px;margin-left: 65px">  
			</select>  <br/>
	  		<select id="addPower" class="<a href="http://wuzhuti.cn/tag/easyui/" title="EasyUI">EasyUI</a>-combobox" name="dept" style="width:300px;margin-top:10px;margin-left: 65px">  
			</select>  <br/>
	  		<a href="javascript:void(0)" id="PowerBtn" class="easyui-linkbutton" iconCls="icon-ok" style="width:150px;height:32px;margin-top: 10px;margin-left: 65px">确定</a>
  		</div>
	</div>
	
	
	
</body>
</html>







