<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/todolist-presentation/create" method="POST">
	<input type="text" name="taskName"/>
	<select name="status">
		<c:forEach items="${status}" var="statut">
     		<option value="${statut.id}">${statut.name}</option>
	 	</c:forEach>
	</select>
	<input type="submit" value="creer"/>
</form>
</body>
</html>