<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<security:authorize access="isAuthenticated()">
<security:authentication property="principal" var="user"/>
wellcome, ${user.username }
<a href="<c:url value='/dang-xuat'/>">thoat</a>
</security:authorize>


<a href="<c:url value='/admin/them-khach-hang'/>">them-khach-hang</a>
<a href="<c:url value='/admin/danh-sach-khach-hang'/>">danh-sach-khach-hang</a><br/>

<a href="<c:url value='/them-san-pham'/>">them-san-pham</a>
<a href="<c:url value='/danh-sach-san-pham'/>">danh-sach-san-pham</a>
<a href="<c:url value='/xem-gio-hang'/>">gio hang</a>