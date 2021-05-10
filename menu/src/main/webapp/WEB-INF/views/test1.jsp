<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="top" style='text-align:center'>
		<h2 style='text-align:center'>menu</h2>
		<input id='search' type="text">
	</div>
	<div id="menuList"style='text-align:center; margin:auto;'>MenuList
		<table border="1" style='margin:auto'>
		<tr>
		<td>NO</td><!--<td>MENU_ID</td> --><td>MENU_NAME</td><td>UP_MENU_ID</td>
		</tr>
		<c:forEach var="list" items="${list}" varStatus="idx">
		<tr>
	 	<td>${idx.index+1}</td><!-- <td>${list.menu_id}</td> --><td><a href="#">${list.menu_nm}</a></td><td> ${list.up_menu_id}</td>
	 	</tr>
		</c:forEach>
		</table>
	</div>
</body>
</html>