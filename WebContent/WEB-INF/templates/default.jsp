<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:importAttribute name="javascripts"/>
<tiles:importAttribute name="stylesheets"/>
<tiles:importAttribute name="topNavigationMenuItemName" scope="request"/>
<tiles:importAttribute name="leftNavigationMenuItemName" scope="request"/>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="images/favicon.ico">
<!-- stylesheets -->
    <c:forEach var="css" items="${stylesheets}">
        <link rel="stylesheet" type="text/css" href="<c:url value="${css}"/>">
    </c:forEach>
<!-- end stylesheets -->
<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
</head>
<body>
   
	<div>
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
	</div>
	
	<div class="container" id="mainContainer">
	    <div class="col-sm-2">
	        <tiles:insertAttribute name="leftMenu"></tiles:insertAttribute>
	    </div>
	    <div class="col-sm-10">
	    	<tiles:insertAttribute name="content"></tiles:insertAttribute>
	    </div>
	</div>
	<div>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</div>

<!-- scripts -->
    <c:forEach var="script" items="${javascripts}">
        <script src="<c:url value="${script}"/>"></script>
    </c:forEach>

</body>


</html>