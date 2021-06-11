<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/static/js/updateMenu.js"></script>
<title>Insert title here</title>
</head>
<body>

	<form role = "updateMenuFile" method = 'post' action = "updateMenuFile" enctype = 'multipart/form-data'>
		<table>
			<input type = "hidden" id = "menu_id" name = "menu_id" value = ${ detail.menu_id }>
			<input type = "hidden" id = "uuid" name = "uuid" value = ${ detail.uuid }><tr>
			
				<td>메뉴이름</td>
				<td><input type = "text" id = "menu_nm" name = "menu_nm" value = ${ detail.menu_nm }></td>
			</tr>
			<tr>
				<td>
					현재 파일
				</td>
				<td>
					<div class = "nowFile">
						<ul>
						</ul>
					</div>
				</td>		
			</tr>
			<tr>
			<tr>
				<td>
					업로드 파일
				</td>
				<td>
					<div class = "uploadResult">
						<ul>
						</ul>
					</div>
				</td>		
			</tr>
			<tr>
				<td>files</td>
				<td><input type = "file" name = "uploadFile" multiple></td>
			</tr>
			<tr>
				<td><input type = "submit" value = "등록"></td>
				<td><input type = "button" value = "취소"></td>
			</tr>
		</table>
	</form>
</body>
</html>