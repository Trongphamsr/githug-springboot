<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 

		<p>thong tin nguoi dung</p>
		
		</br>
		
		</br>
		<table>
			<tr>
				<th>id</th>
				<th>name</th>
				<th>phone</th>
				<th>chon</th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.name }</td>
					<td>${user.phone }</td>
					<td><a href="<c:url value='/admin/chi-tiet-khach-hang/${user.id}'/>">chi tiet</a></td>
					<td><a href="<c:url value='/admin/xoa-khach-hang/${user.id}'/>">xoa</a></td>
					<td><a href="<c:url value='/admin/sua-khach-hang/${user.id}'/>">sua</a></td>
				</tr>
			</c:forEach>
		</table>

