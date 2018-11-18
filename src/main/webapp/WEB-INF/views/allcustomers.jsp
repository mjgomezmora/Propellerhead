<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>List of Customers</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>

</head>


<body>
	<h2>List of Customers</h2>	
	<table>
		<tr>
			<td>CODE</td><td>STATUS</td><td>CREATE TIME</td><td>NAME</td><td>ADDRESS</td><td></td>
		</tr>
		<c:forEach items="${customers}" var="customer">
			<tr>
			<td>${customer.code}</td>
			<td>${customer.status}</td>
			<td>${customer.createTime}</td>
			<td>${customer.name}</td>
			<td>${customer.address}</td>
			<td><a href="<c:url value='/edit-${customer.code}-customer' />">${customer.code}</a></td>
			<td><a href="<c:url value='/delete-${customer.code}-customer' />">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/new' />">Add New Customer</a>
</body>
</html>