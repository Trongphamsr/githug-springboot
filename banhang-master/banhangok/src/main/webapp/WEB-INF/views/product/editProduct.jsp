<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="pring"%>
 

	<p>form sua sp</p>
	<c:url value="/sua-san-pham" var="hhh"/>
		<form:form modelAttribute="product" method="post" action="${hhh}" enctype="multipart/form-data">
			
			<form:hidden path="id"/>
			
			<p><pring:message code="product.name"/></p>
			<form:input path="name"/><br/>
			<p style="color:red"><form:errors path="name"></form:errors></p>
			
			<p><pring:message code="product.price"/></p>
			<form:input path="price"/><br/>
			<p style="color:red"><form:errors path="price"></form:errors></p>
			
			<button type="submit">submit</button>
		</form:form>	

