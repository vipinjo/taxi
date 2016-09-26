<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<nav class="navbar navbar-dark bg-primary">
	<a class="navbar-brand" href="home">Amalia Operations</a>
	<ul class="nav navbar-nav">
		<li
			<c:choose>
		    <c:when test="${topNavigationMenuItemName == 'home'}"> class="nav-item active" </c:when>
		    <c:otherwise>
		        class="nav-item"
		    </c:otherwise>
		    </c:choose>><a
			class="nav-link" href="home">Home <span class="sr-only">(current)</span></a>
		</li>
		<li
			<c:choose>
		    <c:when test="${topNavigationMenuItemName == 'shiftReports'}"> class="nav-item active" </c:when>
		    <c:otherwise>
		        class="nav-item"
		    </c:otherwise>
		    </c:choose>><a
			class="nav-link" href="shiftReport">Shift Reports</a>
		</li>
		<li
			<c:choose>
		    <c:when test="${topNavigationMenuItemName == 'transaction'}"> class="nav-item active" </c:when>
		    <c:otherwise>
		        class="nav-item"
		    </c:otherwise>
		    </c:choose>><a
			class="nav-link" href="transaction">Transactions</a>
		</li>
		<li
			<c:choose>
		    <c:when test="${topNavigationMenuItemName == 'reports'}"> class="nav-item active" </c:when>
		    <c:otherwise>
		        class="nav-item"
		    </c:otherwise>
		    </c:choose>><a
			class="nav-link" href="reports">Reports</a>
		</li>
		<li
			<c:choose>
		    <c:when test="${topNavigationMenuItemName == 'admin'}"> class="nav-item active" </c:when>
		    <c:otherwise>
		        class="nav-item"
		    </c:otherwise>
		    </c:choose>><a
			class="nav-link" href="admin">Admin</a>
		</li>
	</ul>
	<!--   <form class="form-inline pull-xs-right">
		<input class="form-control" type="text" placeholder="Search">
		<button class="btn btn-outline-success" type="submit">Search</button>
	</form> -->
</nav>