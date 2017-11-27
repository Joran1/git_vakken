<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Overview</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/<%=request.getAttribute("color") %>.css" />
</head>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Overview"/>
		</jsp:include>
		<main>
		<table>
			<tr>
				<th>E-mail</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Delete</th>
				<th>check password</th>
			</tr>
			<c:forEach var="person" items="${users}">
				<tr>
					<td><c:out value='${person.email}'/></td>
					<td><c:out value='${person.firstName}'/></td>
					<td><c:out value='${person.lastName}'/></td>
					<td><a href="Controller?action=deleteUser&id=<c:out value='${person.userid}'/>">delete</a></td>
					<td><a href="Controller?action=checkPassword&id=<c:out value='${person.userid}'/>">check</a></td>
				</tr>
			</c:forEach>
			<caption>Users Overview</caption>
		</table>
		</main>
		<jsp:include page="footer.jsp">
			<jsp:param name="page" value="userOverview" />
		</jsp:include>
	</div>
</body>
</html>