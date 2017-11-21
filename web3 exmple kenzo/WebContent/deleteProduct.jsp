<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<jsp:param name="title" value="Delete"/>
		</jsp:include>
		<form action="Controller?action=productdeleteConfirmed" method="post">
			<input type="hidden" name="id" value="<c:out value='${id}'/>"/>
			<input type="submit" name="submit" value="ja"/>
			<input type="submit" name="submit" value="nee"/>
		</form>
		<jsp:include page="footer.jsp">
			<jsp:param name="page" value="productdeleteConfirmed" />
		</jsp:include>
	</div>
</body>
</html>