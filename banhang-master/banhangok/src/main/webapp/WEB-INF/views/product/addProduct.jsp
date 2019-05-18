<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 

	<p>form them nguoi dung</p>
	<c:url value="/them-san-pham" var="hhh"/>
		<form:form modelAttribute="product" method="post" action="${hhh}" enctype="multipart/form-data">
			
			
			
			<p><spring:message code="product.name"/></p><br/>
			<form:input path="name"/><br/>
			
			<p style="color:red"><form:errors path="name"></form:errors></p>
			
			<p><spring:message code="product.price"/></p><br/>
			<form:input path="price"/><br/>
			<p style="color:red"><form:errors path="price"></form:errors></p>
			<br/><form:input type="file" path="file"/>
			
			<button type="submit">submit</button>
		</form:form>	

