<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title>Confirm Delete</title>
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
			
			<h2>Confirm Delete</h2>
		</header>
		<main>
				<form action="servlet?action=DeleteProduct&id=${param.id}" method="post">
					<p><input type="submit" id="Yes" value="Yes"></p>
				</form>
				
				<form method = "post" action = "servlet?action=naarProductOverview">
					<p> <input type="submit" id="No" value="No"> </p>
				</form>
		</main>
		
		<footer>
			&copy; Webontwikkeling 3, UC Leuven-Limburg
		</footer>
	</div>
	</body>
</html>