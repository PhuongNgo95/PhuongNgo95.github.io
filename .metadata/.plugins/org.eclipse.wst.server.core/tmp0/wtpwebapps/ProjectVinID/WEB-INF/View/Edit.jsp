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
	<form action="UpdateController" method="POST">
	<table border="1">
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>PhoneNumber</th>
		</tr>
			<tr>
			<input type="hidden" name ="id" value= "<%=request.getAttribute("Id")%>">
				<td><input type="text" name ="name" value= "<%=request.getAttribute("Name")%>"></td>
				<td><input type="text" name="email" value="<%=request.getAttribute("Email")%>"></td>
				<td><input type="text" name="phoneNumber" value="<%=request.getAttribute("PhoneNumber")%>"></td>
				<td>
						<input type="submit" value="Update">
				</td>
			</tr>
	</table>
	</form>
</body>
</html>