<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<link  href="<c:url value="/resources/css/style.css"/> ">
	</head>
	<body>
	<p>form nguoi dung</p>
	<c:url value="/them-user" var="hhh"/>
		<form:form modelAttribute="user" method="post" action="${hhh}" enctype="multipart/form-data">
			name <form:input path="name"/><br/><br/>
			<p style="color:red"><form:errors path="name"></form:errors></p>
			
			password<form:password path="password"/><br/><br/>
			<p style="color:red"><form:errors path="password"></form:errors></p>
			
			<form:hidden path="id"/>
			<p style="color:red"><form:errors path="id"></form:errors></p>
			
			so thich<form:checkbox path="favouries" value="xem phim" label="xem phim"/>
			<form:checkbox path="favouries" value="nghe nhac" label="nghe nhac"/>
			<form:checkbox path="favouries" value="code" label="Coding"/>
			<br/><br/><p>so thich</p>
			<form:select path="gender">
				<form:option value="nam">nam</form:option>
				<form:option value="nu">nu</form:option>
			</form:select> <br/><br/>
			gioi thieu<form:textarea path="about"/><br/><br/>
			<form:radiobutton path="acceptAgreement" value="true" label="dong y voi dieu khoan su dung"/>
			<br/><br/>
			<form:input path="avata" type="file"/>
			<br/><br/>
			<button type="submit">submit</button>
		</form:form>	
	</body>
</html>
