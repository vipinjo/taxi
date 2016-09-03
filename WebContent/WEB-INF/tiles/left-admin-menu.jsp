<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<ul class="nav nav-pills nav-stacked">
	<li class="nav-item"><a href="admin"
    <c:choose>
		    <c:when test="${leftNavigationMenuItemName == 'adminCar'}"> class="nav-link active" </c:when>
		    <c:otherwise>
		        class="nav-link"
		    </c:otherwise>
		    </c:choose>>Car</a>

	</li>
	<li class="nav-item"><a href="#"
		<c:choose>
		    <c:when test="${leftNavigationMenuItemName == 'driver'}"> class="nav-link active" </c:when>
		    <c:otherwise>
		        class="nav-link"
		    </c:otherwise>
		    </c:choose>>Driver</a>
	</li>
	<li class="nav-item"><a href="adminIncomeType"
		<c:choose>
		    <c:when test="${leftNavigationMenuItemName == 'adminIncome'}"> class="nav-link active" </c:when>
		    <c:otherwise>
		        class="nav-link"
		    </c:otherwise>
		    </c:choose>>Income
			Type</a></li>
	<li class="nav-item"><a href="adminExpenseType"
		<c:choose>
		    <c:when test="${leftNavigationMenuItemName == 'adminExpense'}"> class="nav-link active" </c:when>
		    <c:otherwise>
		        class="nav-link"
		    </c:otherwise>
		    </c:choose>>Expense
			Type</a></li>
</ul>