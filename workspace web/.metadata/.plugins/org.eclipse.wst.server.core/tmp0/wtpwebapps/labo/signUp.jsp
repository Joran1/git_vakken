<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>Sign Up</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${color == null ? 'yellow' : color}.css" />
</head>
<body>
<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Sign Up" />
		</jsp:include>

<main>
	<div class="alert-danger">
	
				<c:forEach var="fout" items="${foutenPersoon}">	
					<ul>
						<li>${fout}</li>
					</ul>
				</c:forEach>
		
	</div>

    <form method="post" action="servlet?action=maakPersoon" novalidate="novalidate">
    	<!-- novalidate in order to be able to run tests correctly -->
        <p><label for="userid">User id</label><input type="text" id="userid" name="userid" required value="<c:out value='${userid}'/>"> </p>
        <p><label for="firstName">First Name</label><input type="text" id="firstName" name="firstName" required value="<c:out value='${firstName}'/>"> </p>
        <p><label for="lastName">Last Name</label><input type="text" id="lastName" name="lastName" required value="<c:out value='${lastName}'/>"> </p>
        <p><label for="email">Email</label><input type="email" id="email" name="email" required value="<c:out value='${email}'/>"></p>
        <p><label for="password">Password</label><input type="password" id="password"  name="password" required value="<c:out value='${password}'/>"> </p>
        <p><input type="submit" id="signUp" value="Sign Up"></p>
        
    </form>
</main>
		<jsp:include page="footer.jsp">
			<jsp:param name="page" value="naarMaakPersoon" />
		</jsp:include>
</div>
</body>
</html>
