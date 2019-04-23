<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个性标签页面</title>
<jsp:include page="head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="./css/lmc_personal.css">
<script type="text/javascript" src="./js/frame.js"></script>
<script type="text/javascript" src="./js/lmc_personal.js"></script>
</head>
<body>
	<div id="personal-container">
	
		<jsp:include page="lmc.jsp">
			<jsp:param value="3" name="nodeid"/>
		</jsp:include>
		
		<div id="personal-content" style="height: 100%;float: left;width: 78%;padding-left: 50px;padding-top: 20px;">
			<i id="add-i" class="fa fa-plus-square fa-2x"></i>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<i id="save-i" style="color:green;font-size:20px;" class="fa fa-floppy-o"></i>
			<br>
			
			<input id="new-key" type="text" style="width: 200px;" placeholder="请输入自定义标签的名称">&nbsp;&nbsp;
			<input id="new-value" type="text" style="width: 450px;" placeholder="请输入自定义标签的值">
			<ul id="label-ul" style="list-style: none;padding: 0;padding-top: 20px;">
			
			
			</ul>
		
			
		</div>
	
		<div style="clear: both;"></div>
	</div>
	
		
		
</body>
</html>