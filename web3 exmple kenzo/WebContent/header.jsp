<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<h1>
		<span>Web shop</span>
	</h1>
	<nav>
		<ul>
			<li><a href="Controller?action=index">Home</a></li>
			<c:if test="${user.role == 'CUSTOMER'}">
				<li id="actual"><a href="Controller?action=productOverview">products</a></li>
				<c:if test="${user.role == 'ADMIN'}">
					<li id="actual"><a href="Controller?action=userOverview">users</a></li>
					<li id="actual"><a href="Controller?action=productForm">add product</a></li>		
					<li><a href="Controller?action=signUp">Sign up</a></li>
				</c:if>
			</c:if>
		</ul>
	</nav>
	<h2>${param.title}</h2>
</header>