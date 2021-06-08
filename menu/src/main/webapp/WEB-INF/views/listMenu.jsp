<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/static/js/moveWindow.js"> 
</script>
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
			<td>DEPTH1</td><<td>DEPTH2</td><td>DEPTH3</td><td>DEPTH4</td><td>DEPTH5</td>
			</tr>
			<c:forEach var = "list" items = "${list}">
			<tr>
			<td><a href = "/detailMenu?menu_id=${list.d1_id}" >${list.d1_nm}</a></td>
		 	<td><a href = "/detailMenu?menu_id=${list.d2_id}" >${list.d2_nm}</a></td>
		 	<td><a href = "/detailMenu?menu_id=${list.d3_id}" >${list.d3_nm}</a></td>
		 	<td><a href = "/detailMenu?menu_id=${list.d4_id}" >${list.d4_nm}</a></td>
		 	<td><a href = "/detailMenu?menu_id=${list.d5_id}" >${list.d5_nm}</a></td>
		 	</tr>
			</c:forEach>
		</table>
	</div>
	<div>
		<input type = "button" value = "등록" onclick = "AddMenuWindow()">
	</div>
</body>
</html>