<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
	<html>
		<head>
			<meta name="viewport" content="width=device-width, initial-scale=1" />
			<meta charset="UTF-8">
			<title>Overview</title>
			<link rel="stylesheet" type="text/css" href="css/style.css">
		</head>
		
		<body>
		<div id="container">
			<header>
				<h1><span>Web shop</span></h1>
				
				<nav>
					<ul>
						<li> <a href="servlet">Home</a></li>
						<li id="actual"> <a href="servlet?action=naarPersonOverview">Overview</a></li>
						<li> <a href="servlet?action=naarMaakPersoon">Sign up</a></li>
						<li> <a href="servlet?action=naarProductOverview">Products</a></li>
						<li> <a href="servlet?action=naarMaakProduct">Add Product</a></li>
					</ul>
				</nav>
				
				<h2>User Overview</h2>
			</header>

			<main>
				<table>
					<tr>
						<th>E-mail</th>
						<th>First Name</th>
						<th>Last Name</th>
					</tr>
					
					<c:forEach var="person" items="${personen}">	
					<tr>
						<td>${person.email}</td>
						<td>${person.firstName}</td>
						<td>${person.lastName}</td>
						<td><a href="servlet?action=naarDeletePersoon&id=${person.userid}">delete</a></td>
					</tr>
					</c:forEach>
				
					<caption>Users Overview</caption>
				</table>
			</main>
			
			<footer> 
				&copy; Webontwikkeling 3, UC Leuven-Limburg 
			</footer>
		</div>
		</body>
	</html>