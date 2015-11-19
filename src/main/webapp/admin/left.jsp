<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
 <head>
        <title>Your Admin Panel</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

        
        <!-- jQuery AND jQueryUI -->
        <script type="text/javascript" src="<%=basePath%>admin/js/libs/jquery/1.6/jquery.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>admin/js/libs/jqueryui/1.8.13/jquery-ui.min.js"></script>
        
        <!-- Compressed Version
        <link type="text/css" rel="stylesheet" href="min/b=CoreAdmin&f=css/reset.css,css/style.css,css/jqueryui/jqueryui.css,js/jwysiwyg/jquery.wysiwyg.old-school.css,js/zoombox/zoombox.css" />
        <script type="text/javascript" src="min/b=CoreAdmin/js&f=cookie/jquery.cookie.js,jwysiwyg/jquery.wysiwyg.js,tooltipsy.min.js,iphone-style-checkboxes.js,excanvas.js,zoombox/zoombox.js,visualize.jQuery.js,jquery.uniform.min.js,main.js"></script>
        -->
        <link rel="stylesheet" href="<%=basePath%>admin/css/min.css" />
        <script type="text/javascript" src="<%=basePath%>admin/js/min.js"></script>
        <script type="text/javascript">
        	$(document).ready(function(){
        		  $('.subul').find('li').find('a').click(function(){
        				$(this).parent().siblings().removeClass('current');
        				$(this).parent().addClass('current');
        			});
        	});
        </script>
    </head>
    <body>
        
        <script type="text/javascript" src="<%=basePath%>admin/content/settings/main.js"></script>
		<link rel="stylesheet" href="<%=basePath%>admin/content/settings/style.css">

        <div id="sidebar">
            <ul>
                <li>
                    <a href="index.html">
                        <img src="<%=basePath%>admin/img/icons/menu/inbox.png" alt="" />
                        Dashboard
                    </a>
                </li>
                <li class="current"><a href="#"><img src="<%=basePath%>admin/img/icons/menu/layout.png" alt="" />系统管理</a>
                    <ul class="subul">
                         <li class="current"><a target="rightFrame" href="./ManageUser.jsp">用户管理</a></li>
                         <li ><a target="rightFrame" href="./ManageRole.jsp">角色管理</a></li>
                         <li ><a target="rightFrame" href="./ManageUserRole.jsp">用户角色管理</a></li>
                         <li ><a target="rightFrame" href="./ManagePower.jsp">权限管理</a></li>
                    </ul>
                </li>
                <li ><a href="#"><img src="<%=basePath%>admin/img/icons/menu/layout.png" alt="" /> Elements</a>
                    <ul class="subul">
                         <li class="current"><a href="dashboard.html?p=index">Dashboard</a></li>
                         <li><a href="forms.html?p=forms">Forms</a></li>
                         <li><a href="table.html?p=table">Table</a></li>
                         <li><a href="tabs.html?p=tabs">Tabs</a></li>
                         <li><a href="gallery.html?p=gallery">Gallery</a></li>
                         <li><a href="notifications.html?p=notif">Notifications</a></li>
                         <li><a href="charts.html?p=charts">Charts</a></li>
                         <li><a href="typography.html?p=typo">Typography</a></li>
                         <li><a href="icons.html?p=icons">Icons</a></li>
                         <li><a href="calendar.html?p=calendar">Calendar</a></li>
                    </ul>
                </li>
                <li><a href="#"><img src="<%=basePath%>admin/img/icons/menu/brush.png" alt="" /> Another submenu</a>
                    <ul>
                        <li><a href="#">Fake menu #1</a></li>
                        <li><a href="#">Fake menu #2</a></li>
                        <li><a href="#">Fake menu #3</a></li>
                    </ul>
                </li>
                <li><a href="#"><img src="<%=basePath%>admin/img/icons/menu/brush.png" alt="" /> Infinite sublevel</a>
                    <ul>
                        <li><a href="#">Fake menu #1</a></li>
                        <li><a href="#">Fake menu #2</a></li>
                        <li><a href="#">Fake menu #3</a>
                        <ul>
                            <li><a href="#">Fake menu #1</a></li>
                            <li><a href="#">Fake menu #2</a></li>
                            <li><a href="#">Fake menu #3</a>
                                <ul>
                                    <li><a href="#">Fake menu #1</a></li>
                                    <li><a href="#">Fake menu #2</a></li>
                                    <li><a href="#">Fake menu #3</a></li>
                                </ul>
                            </li>
                        </ul>
                        </li>
                    </ul>
                </li>
                <li class="nosubmenu"><a href="#"><img src="<%=basePath%>admin/img/icons/menu/lab.png" alt="" /> This button is useless</a></li>
                <li class="nosubmenu"><a href="modal.html" class="zoombox w450 h700"><img src="<%=basePath%>admin/img/icons/menu/comment.png" alt="" /> Modal box</a></li>
            </ul>


        </div>
                
                        
    </body>
</html>