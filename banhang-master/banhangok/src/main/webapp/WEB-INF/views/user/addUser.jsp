<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 

	<p>form them nguoi dung</p>
	<c:url value="/admin/them-khach-hang" var="hhh"/>
		<form:form modelAttribute="user" method="post" action="${hhh}" enctype="multipart/form-data">
			
			
			
			name <form:input path="name"/><br/>
			<p style="color:red"><form:errors path="name"></form:errors></p>
			
			password<form:input path="phone"/><br/>
			<p style="color:red"><form:errors path="phone"></form:errors></p>
			
			<button type="submit">submit</button>
		</form:form>	

