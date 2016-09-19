<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<ul class="nav nav-pills nav-stacked">
	<li class="nav-item"><a href="transaction"
		<c:choose>
		    <c:when test="${leftNavigationMenuItemName == 'viewExpense'}"> class="nav-link active" </c:when>
		    <c:otherwise>
		        class="nav-link"
		    </c:otherwise>
		    </c:choose>>View Expenses</a>
    </li>
	<li class="nav-item"><a href="showAddExpensePage"
		<c:choose>
		    <c:when test="${leftNavigationMenuItemName == 'addExpense'}"> class="nav-link active" </c:when>
		    <c:otherwise>
		        class="nav-link"
		    </c:otherwise>
		    </c:choose>>Add Expenses</a>
    </li>
</ul>