<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Home</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/<%=request.getAttribute("color") %>.css" />
</head>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Add product"/>
		</jsp:include>
		<c:if test="${ errors != null}">
			<div class="alert-danger">
				<ul>
					<c:forEach var="error" items="${errors}">
						<li><c:out value='${error}'/></li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		<form action="Controller?action=addProduct" method="post">
			<p>
				<label for="name">naam</label> <input id="name" name="name"
					type="text" />
			</p>
			<p>
				<label for="description">description</label> <input id="description"
					name="description" type="text" />
			</p>
			<p>
				<label for="price">price</label> <input id="price" name="price"
					type="text" />
			</p>
			<input type="submit" />
		</form>
		<jsp:include page="footer.jsp">
			<jsp:param name="page" value="productForm" />
		</jsp:include>
	</div>
</body>
</html>