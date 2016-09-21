<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<ul class="nav nav-pills nav-stacked">
	<li class="nav-item"><a href="reports"
		<c:choose>
		    <c:when test="${leftNavigationMenuItemName == 'shiftReport'}"> class="nav-link active" </c:when>
		    <c:otherwise>
		        class="nav-link"
		    </c:otherwise>
		    </c:choose>>Shift
			Report</a></li>

	<li class="nav-item"><a href="performanceReport"
		<c:choose>
		    <c:when test="${leftNavigationMenuItemName == 'performanceReport'}"> class="nav-link active" </c:when>
		    <c:otherwise>
		        class="nav-link"
		    </c:otherwise>
		    </c:choose>>Performance
			Report</a></li>
</ul>