<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
		<link  href="<c:url value="/resources/css/style.css"/> ">
	</head>
	<body>
		
		<p>thong tin nguoi dung</p>
		
		<p>ten: ${u.name }</p>
		<br/><br/><p>${u.password }</p>
		<br/><br/><p>${u.id }</p>
		<br/><br/><p>${u.gender }</p>
		<br/><br/><p>${u.about }</p>
		<br/><br/><p>${u.avata.getOriginalFilename() }</p>
		<br/><br/><p>${u.acceptAgreement }</p><br/><br/>
		<c:forEach items="${u.favouries}" var="item">
			<p>${item}</p>
		</c:forEach>
	</body>
</html>