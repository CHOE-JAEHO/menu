<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/static/js/moveWindow.js"></script>
<link rel = "stylesheet" href = "/static/css/menuList.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id = "top" style = 'text-align : center' >
		<h2 style = 'text-align : center'>menu</h2>
	<!-- 	<form action = "/" method = "get">
			<input id = 'search' name = 'keyword' type = "text">
			<input type = 'submit' value = "검색">
		</form> -->
	</div>
	
	<div id = "menuList" style = 'text-align : left; margin : auto'>
		<table border = "1" style = 'margin : auto'>
			<tr>
			<td>MENU_NAME</td><<td>PATH</td>
			</tr>
			<c:forEach var = "list" items = "${list}">
			<tr>
			<td>${list.menu_nm }</td><td><a href = "/detailMenu?menu_id=${ list.menu_id }" > ${ list.path }</a></td>
		 	</tr>
			</c:forEach>
		</table>
	</div>
	<div>
		<input type = "button" value = "등록" onclick = "AddMenuWindow()">
	</div>
</body>
</html>