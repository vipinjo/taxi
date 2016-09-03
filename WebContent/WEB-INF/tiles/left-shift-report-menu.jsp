<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<ul class="nav nav-pills nav-stacked">
	<li class="nav-item"><a href="shiftReport"
		<c:choose>
		    <c:when test="${leftNavigationMenuItemName == 'viewShiftReport'}"> class="nav-link active" </c:when>
		    <c:otherwise>
		        class="nav-link"
		    </c:otherwise>
		    </c:choose>>View
			Bailment Report</a></li>

	<li class="nav-item"><a href="showAddNewShiftReportPage"
		<c:choose>
		    <c:when test="${leftNavigationMenuItemName == 'addNewShiftReport'}"> class="nav-link active" </c:when>
		    <c:otherwise>
		        class="nav-link"
		    </c:otherwise>
		    </c:choose>>Add
			Bailment Report</a></li>
</ul>