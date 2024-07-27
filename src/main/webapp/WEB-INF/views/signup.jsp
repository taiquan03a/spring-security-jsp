<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Signup</title>
</head>
<body>
	<h1>Signup</h1>
	<form:form action="/signup" method="post" modelattribute="user">
		<div class="form-group" style="margin-bottom: 10px;">
			<label>Username:</label>
			<input type="text" name="username" required />
		</div>
		<div class="form-group" style="margin-bottom: 10px;">
			<label style="margin-right: 26px;">Email:</label>
			<input type="text" name="email" required />
		</div>
		<div class="form-group" style="margin-bottom: 10px;">
			<label style="margin-right: 3px;">Password:</label>
			<input type="password" name="password" required />
		</div>
		
		<c:if test="${not empty errmsg}">
		<div class="form-group"  style="margin-bottom: 10px;">
			<p>${errmsg}</p>
		</div>
		</c:if>
		
		<div class="form-group"  style="margin-top: 10px; margin-bottom: 20px;">
			<button type="submit">Signup</button>
		</div>
	</form:form>
	<footer><a href="/">spring-security-jsp-user</a> &copy; 2024. Made in Penang.</footer>
</body>
</html>