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
</style>


<script>

	function getdata(){
		$('#treegrid').treegrid({
		    url:'<%=basePath%>/user.do?method=gettest',
		    idField:'id',
		    treeField:'name',
		    fitColumns:true,
		    pagination: true,//是否这是分页
		    columns:[[
				{title:'name',field:'name',width:180},
		    ]],
		    toolbar:[
					   {//添加数据
						   text:"添加",
						   iconCls: "icon-add",
						   handler: _insertRow,
						
					   },'-',
					],
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
	function _insertRow(){
		
	};
	$(function(){
		getdata();
	});
</script>


</head>

<body bgcolor="#DDF3FF" class = "h2">
	<table id="treegrid"></table>
</body>
</html>







