<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>Confirm Delete</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${color == null ? 'yellow' : color}.css" />
	</head>
	
	<body>
	<div id="container">
	<jsp:include page="header.jsp">
		<jsp:param name="title" value="Confirm Delete Product" />
	</jsp:include>
		<main>
				<form action="servlet?action=DeleteProduct&id=${param.id}" method="post">
					<p><input type="submit" id="Yes" value="Yes"></p>
				</form>
				
				<form method = "post" action = "servlet?action=naarProductOverview">
					<p> <input type="submit" id="No" value="No"> </p>
				</form>
		</main>
		
		<jsp:include page="footer.jsp">
			<jsp:param name="page" value="naarDeleteProduct" />
		</jsp:include>
	</div>
	</body>
</html>