<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
	<html>
		<head>
			<meta name="viewport" content="width=device-width, initial-scale=1" />
			<meta charset="UTF-8">
			<title>Overview Products</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${color == null ? 'yellow' : color}.css" />
		</head>
		
		<body>
		<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Overview Products" />
		</jsp:include>

			<main>
				<table>
					<tr>
						<th>ID</th>
						<th>Description</th>
						<th>Price</th>
					</tr>
					
					<c:forEach var="product" items="${producten}">	
					<tr>
						<td><a href= "servlet?action=naarUpdate&idUpdate=${product.productId}">${product.productId}</a></td>
						<td>${product.description}</td>
						<td>${product.price}</td>
						<td><a href="servlet?action=naarDeleteProduct&id=${product.productId}">delete</a></td>
					</tr>
					</c:forEach>
				
					<caption>Products Overview</caption>
				</table>
			</main>
			
		<jsp:include page="footer.jsp">
			<jsp:param name="page" value="naarProductOverview" />
		</jsp:include>
		</div>
		</body>
	</html>