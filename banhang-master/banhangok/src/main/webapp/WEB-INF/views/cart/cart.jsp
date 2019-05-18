<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 

		<p>gio hang</p>
		
		</br>
		
		</br>
		<table>
			<tr>
				<th>name</th>
				<th>price</th>
				<th>number</th>
				<th>chon</th>
			</tr>
			<c:forEach items="${order.itemDTOs}" var="item">
				<tr>
					<td>${item.productDTO.name}</td>
				
					<td>${item.productDTO.price}</td>
						<td>${item.number}</td>
					<td><a href="<c:url value='/xoa-gio-hang/${item.productDTO.id}'/>">xoa</a></td>
					</tr>
			</c:forEach>
		</table>

