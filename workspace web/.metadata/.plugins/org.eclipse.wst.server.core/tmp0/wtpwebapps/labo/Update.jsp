<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>Update</title>
		<link rel="stylesheet" type="text/css" href="css/style.css">
	</head>
	
	<body>
	<div id="container">
		<header>
			<h1> <span> Web shop </span> </h1>
				
			<nav>
				<ul>
					<li> <a href="servlet">Home</a></li>
					<li> <a href="servlet?action=naarPersonOverview">Overview</a></li>
					<li> <a href="servlet?action=naarMaakPersoon">Sign up</a></li>
					<li> <a href="servlet?action=naarProductOverview">Products</a></li>
					<li> <a href="servlet?action=naarMaakProduct">Add Product</a></li>
				</ul>
			</nav>
			
			<h2>Update Product</h2>
		</header>
		<main>
		
			<div class="alert-danger">
	
				<c:forEach var="fout" items="${foutenProduct}">	
					<ul>
						<li>${fout}</li>
					</ul>
				</c:forEach>
		
			</div>
			
			<form method="post" action="servlet?action=Update&idUpdate=${p.productId}" novalidate="novalidate">
    		<!-- novalidate in order to be able to run tests correctly -->
        	<p><label for="name">Name</label><input type="text" id="name" name="name" required value=${p.name}> </p>
        	<p><label for="description">Description</label><input type="text" id="description" name="description" required value=${p.description}> </p>
        	<p><label for="price">Price</label><input type="text" id="price" name="price" required value=${p.price}> </p>
        	<p><input type="submit" id="updateProduct" value="update Product"></p>        
    		</form>
		</main>
		
		<footer>
			&copy; Webontwikkeling 3, UC Leuven-Limburg
		</footer>
	</div>
	</body>
</html>