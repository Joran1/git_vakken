<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Home</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css"
	href="css/${color == null ? 'yellow' : color}.css" />
</head>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Home" />
		</jsp:include>
		<main> Sed ut perspiciatis unde omnis iste natus error sit
		voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque
		ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae
		dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
		aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
		qui ratione voluptatem sequi nesciunt. </main>
		<c:choose>
			<c:when test="${user == null}">
				<form action="Controller?action=login" method="post">
					<p>
						<label for="email">email</label> <input id="email" name="email" type="text" />
					</p>
					<p>
						<label for="password">password</label> <input id="password" name="password" type="password" />
					</p>
					<input type="submit" value="login" />
				</form>
			</c:when>
			<c:otherwise>
				<p>Welcome <c:out value="${user.firstName}" /></p>
				<p>role <c:out value="${user.role}"/></p>
				<form action="Controller?action=logout" method="post">
					<input type="submit" value="logout" />
				</form>
			</c:otherwise>
		</c:choose>
		<jsp:include page="footer.jsp">
			<jsp:param name="page" value="index" />
		</jsp:include>
	</div>
</body>
</html>