<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/static/js/detailMenu.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div>
		<div>
			<table>
			<input type = "hidden" id = "menu_id" name = "menu_id" value = "${ detail.menu_id }">
					<tr>
					<c:forEach var = "depth" items = "${ depth }">
				 	<td> &nbsp; ▷ ${ depth.menu_nm } </td>
			 		</c:forEach>
			 		<td>&nbsp; │ ${ detail.menu_nm }</td>
					</tr>
			</table>
		</div>
		<br>
		<div class = "nowFile">
			<ul>
			</ul>
		</div>
		<div>
				<a href = "/updateMenuWindow?menu_id=${ detail.menu_id }">수정</a>
				<a onclick="menuDelete( '${ detail.uuid }','${ detail.menu_id }' )"> 삭제</a>
				
		</div>
		<br>
		<div id = "showHTML">
			
		</div>
	</div>
</body>
</html>