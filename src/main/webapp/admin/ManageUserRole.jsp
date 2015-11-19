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
	#addItem{margin-left: 65px;}
	#addValue{margin-left: 65px;margin-top: 10px;}
</style>


<script>

	function getdata(){
		$('#treegrid').treegrid({
		    url:'<%=basePath%>/user.do?method=getUserTree',
		    idField:'id',
		    treeField:'name',
		    rownumbers: true,
		    fitColumns:true,
		    pageSize: 5,//每页显示的记录条数，默认为10  
	        pageList: [5,10,15,20,25,100],//可以设置每页记录条数的列表  
		    pagination: true,//是否这是分页
		    columns:[[
				{title:'用户名/角色名',field:'name',width:180},
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
			var userid = index.substring(0,temp);
			var roleid = index.substring(temp+1,index.length);
			$.ajax({
				url:'<%=basePath%>/userrole.do?method=deleteUserRole',
				type:'post',
				data:{userid:userid,roleid:roleid},
				success:function(data){
					if(data==1){
						$.messager.alert('提示','删除成功','info');
						$('#treegrid').treegrid('reload'); 
					}else{
						$.messager.alert('提示','删除失败','info');
						$('#treegrid').treegrid('reload'); 
					}
				},error:function(){
					$.messager.alert('提示','连接错误','error');
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
				$('#addValue').html(str);
			},error:function(){
				$.messager.alert('提示','连接错误','error');
			}
				
		});
		
		$.ajax({
			url:'<%=basePath%>/user.do?method=getalluser',
			type:'POST',
			data:{},
			success:function(data){
				var str="";
				data= data.users;
				for(var i=0;i<data.length;i++){
					var username = data[i].username;
					var id = data[i].id;
					str+="<option value="+id+">"+username+"</option>";
				}
				$('#addItem').html(str);
			},error:function(){
				$.messager.alert('提示','连接错误','error');
			}
				
		});
	}
	//---------------------添加--------------------
	function AddItemValue(item,value){
		$.ajax({
			url:'<%=basePath%>userrole.do?method=adduserrole',
			type:'post',
			data:{item:item,value:value},
			success:function(data){
				if(data==1){
					$.messager.alert('提示','该用户已经拥有该角色','info');
				}else if(data==2){
					$.messager.alert('提示','添加角色成功','info');
					$('#adddialog').dialog('close');
					$('#treegrid').treegrid('reload'); 
				}
			},error:function(){
				$.messager.alert('提示','连接错误','error');
			}
		});
	}
	//------------主体部分----------------------------
	var doedit = undefined;//用来记录当前编辑的行，如果没有编辑的行则置为undefined
	$(function(){
		$('#adddialog').dialog('close');
		getdata();
		GetAddDialog();
		$('#AddBtn').click(function(){
			var item = $('#addItem').val();
			var value = $('#addValue').val();
			AddItemValue(item,value);
		});
	});
	
    
</script>


</head>

<body bgcolor="#DDF3FF" class = "h2">
	<table id="treegrid"></table>
	
	<div id="adddialog" class="easyui-dialog" title="添加" style="width:400px;height:200px;"
    data-options="iconCls:'icon-save',resizable:true,modal:true">
    	<div id="totalplane" style="margin-top: 36px;padding-left: 60px;">
	  		<select id="addItem" class="<a href="http://wuzhuti.cn/tag/easyui/" title="EasyUI">EasyUI</a>-combobox" name="dept" style="width:300px;margin-left: 65px">  
			</select>  <br/>
	  		<select id="addValue" class="<a href="http://wuzhuti.cn/tag/easyui/" title="EasyUI">EasyUI</a>-combobox" name="dept" style="width:300px;margin-top:10px;margin-left: 65px">  
			</select>  <br/>
	  		<a href="javascript:void(0)" id="AddBtn" class="easyui-linkbutton" iconCls="icon-ok" style="width:150px;height:32px;margin-top: 10px;margin-left: 65px">确定</a>
  		</div>
	</div>
	
	
	
</body>
</html>







