<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<title> Error </title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/${color == null ? 'yellow' : color}.css" />
	</head>
	
	<body>
	<div id="container">
		<header>
			<h1> <span> Web shop </span> </h1>
			
			<h2>UNEXPECTED ERROR</h2>
		</header>
		
		<main> 
		 
			<p>An ${pageContext.exception} has occured. </p>
			<p><a href="servlet">Home</a></p>
		</main>
	</div>
	</body>
</html>