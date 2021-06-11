<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/static/js/AddMenu.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form role = "addMenu" method = 'post' action = "addMenu" enctype = 'multipart/form-data'>
		<table>
			<tr>
				<td>상위 메뉴</td>
				<td>	
					<div id = "select">
						<select name = "up_menu_id">
								<option value = "1">선택안함(ROOT)</option>
								<c:forEach var = "list" items = "${ list }">
								<option value = "${ list.menu_id }" > ${ list.menu_nm } </option>
								</c:forEach>
						</select>
					</div>
				</td>
			</tr>
			<tr>
				<td>메뉴이름</td>
				<td><input type = "text" id = "menu_nm" name = "menu_nm"></td>
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
	
	<div class = "uploadResult">
		<ul>
		</ul>
	</div>	
</body>
</html>