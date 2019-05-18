<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value='/login' var="url"/>
<form action="${url }" method="post">

	<input type="text" name="username" placeholder="username">
	<input type="password" name="password" placeholder="password">
	<button type="submit">login</button>
	
</form>