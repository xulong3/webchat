<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统标签页面</title>
<jsp:include page="head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="./css/lmc_sys.css">
<script type="text/javascript" src="./js/frame.js"></script>
<script type="text/javascript" src="./js/lmc_sys.js"></script>
</head>
<body>
	<div id="sys-container">
	
		<jsp:include page="lmc.jsp">
			<jsp:param value="2" name="nodeid"/>
		</jsp:include>
		
		<div id="sys-content" style="height: 100%;float: left;width: 78%;padding-left: 80px;padding-top: 0;">
			<table style="width: 100%;border-collapse:separate;border-spacing: 0px 20px;" border="0">
				
				<tr style="width: 100%;">
					<td style="width: 10%;"></td>
					<td style="width: 10%;"></td>
					<td style="width: 10%;"></td>
					<td style="width: 10%;"></td>
					<td style="width: 10%;"></td>
					<td style="width: 10%;"></td>
					<td style="width: 10%;"></td>
					<td style="width: 10%;"></td>
					<td style="width: 10%;"></td>
					<td style="width: 10%;"></td>
				</tr>
			
				<form id="sys-form">
					<input type="hidden" name="account">
					<tr>
						<td>真实姓名</td>
						<td colspan="3"><input name="realname" type="text" style="width: 80%;"></td>
						<td>英文名</td>
						<td colspan="3"><input name="englishName" type="text" style="width: 80%;"></td>
						<td>性别</td>
						<td>
							<select name="sex">
								<option value=""></option>
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
						</td>
					</tr>
				
					<tr>
						<td>生日</td>
						<td colspan="3"><input name="birthday" type="text" style="width: 80%;" id="birthday" onclick="WdatePicker({isShowClear:false,readOnly:true})"></td>
						<td>头像</td>
						<td colspan="3"><input name="portrait" id="portrait" type="file" accept="image/*" /></td>
					</tr>
				
					<tr>
						<td>年龄</td>
						<td colspan="3"><input type="text" style="width: 80%;" disabled="disabled"></td>
						<td>星座</td>
						<td colspan="3"><input type="text" style="width: 80%;" disabled="disabled"></td>
						
					</tr>
					
					<tr>
						
						<td>公司</td>
						<td colspan="5"><input name="company" type="text" style="width: 80%;"></td>
						<td>职业</td>
						<td colspan="3"><input name="profession" type="text" style="width: 85%;"></td>
					</tr>
					
					<tr>
						<td>学校</td>
						<td colspan="5"><input name="school" type="text" style="width: 80%;"></td>
						<td>手机号</td>
						<td colspan="3"><input name="phone" type="text" style="width: 85%;"></td>
						
					</tr>
					
					<tr>
						<td>现居地</td>
						<td colspan="5"><input name="presentAddress" type="text" style="width: 80%;"></td>
						<td>故乡</td>
						<td colspan="3"><input name="hometown" type="text" style="width: 85%;"></td>
					</tr>
					
					<tr>
						<td>兴趣爱好</td>
						<td colspan="9"><input name="hobby" type="text" style="width: 95%;"></td>
						
					</tr>
					
					<tr>
						
						<td>个性签名</td>
						<td colspan="9"><input name="signature" type="text" style="width: 95%;"></td>
					</tr>
				</form>
				<tr>
					
					<td>
						<button id="save-btn" class="btn-success" style="width: 50px;border-radius: 5px;">保存</button>
					</td>
					<td><button class="btn-default" style="width: 50px;border-radius: 5px;">取消</button></td>
				</tr>
			
			</table>
			
			
		
			
		</div>
	
		<div style="clear: both;"></div>
	</div>
	
		
		
</body>
</html>