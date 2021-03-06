<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
	<h1>
		<span>Web shop</span>
	</h1>
		<nav>
			<ul>
				<c:if test = "${param.title == 'Home'}"><li id="actual"> <a href="servlet">Home</a></li></c:if>
				<c:if test = "${param.title != 'Home'}"><li> <a href="servlet">Home</a></li></c:if>				
				<c:if test = "${param.title == 'Overview Persons'}"><li id="actual"> <a href="servlet?action=naarPersonOverview">Overview</a></li></c:if>
				<c:if test = "${param.title != 'Overview Persons'}"><li> <a href="servlet?action=naarPersonOverview">Overview</a></li></c:if>				
				<c:if test = "${param.title == 'Sign Up'}"><li id="actual"> <a href="servlet?action=naarMaakPersoon">Sign up</a></li></c:if>
				<c:if test = "${param.title != 'Sign Up'}"><li> <a href="servlet?action=naarMaakPersoon">Sign up</a></li></c:if>			
				<c:if test = "${param.title == 'Overview Products'}"><li id="actual"> <a href="servlet?action=naarProductOverview">Products</a></li></c:if>
				<c:if test = "${param.title != 'Overview Products'}"><li> <a href="servlet?action=naarProductOverview">Products</a></li></c:if>				
				<c:if test = "${param.title == 'Add Product'}"><li id="actual"> <a href="servlet?action=naarMaakProduct">Add Product</a></li></c:if>
				<c:if test = "${param.title != 'Add Product'}"><li> <a href="servlet?action=naarMaakProduct">Add Product</a></li></c:if>					
			</ul>
		</nav>
	<h2>${param.title}</h2>
</header>