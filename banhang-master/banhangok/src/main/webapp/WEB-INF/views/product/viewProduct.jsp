<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 

		
		<p>thong tin chi tiet san pham</p></br>
		
		<p>id: ${product.id }</p><br/>
		<p>name: ${product.name }</p><br/>
		<p>price: ${product.price }</p>
		<p><img src="<c:url value='/files/${product.imageURL }'/>"/></p>
