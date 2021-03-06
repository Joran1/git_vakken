<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
	<html>
		<head>
			<meta name="viewport" content="width=device-width, initial-scale=1" />
			<meta charset="UTF-8">
			<title>Overview</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${color == null ? 'yellow' : color}.css" />
		</head>
		
		<body>
		<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Overview Persons" />
		</jsp:include>

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
						<td><a href="servlet?action=naarCheckPassword&id=<c:out value='${person.userid}'/>">check</a></td>
					</tr>
					</c:forEach>
				
					<caption>Users Overview</caption>
				</table>
			</main>
			
		<jsp:include page="footer.jsp">
			<jsp:param name="page" value="naarPersonOverview" />
		</jsp:include>
		</div>
		</body>
	</html>