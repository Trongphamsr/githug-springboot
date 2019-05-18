<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 

		<p>thong tin san pham</p>
	
		</br>
			<h1>${msg }</h1>
		</br>
		<table>
			<tr>
				<th>id</th>
				<th>name</th>
				<th>price</th>
				<th>chon</th>
			</tr>
			<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.id}</td>
					<td>${product.name}</td>
					<td>${product.price}</td>
					<td><a href="<c:url value='/chi-tiet-san-pham/${product.id}'/>">chi tiet</a></td>
					<td><a href="<c:url value='/xoa-san-pham/${product.id}'/>">xoa</a></td>
					<td><a href="<c:url value='/sua-san-pham/${product.id}'/>">sua</a></td>
					<td><a href="<c:url value='/them-gio-hang/${product.id}'/>">add to cart</a></td>
				</tr>
			</c:forEach>
		</table>

