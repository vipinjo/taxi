<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
    <div class="alert alert-danger errAlert" role="alert" id="errorAlert"></div>
	<form action="reportsPerformanceReports" id="searchPerformanceReportForm">
		<div class="form-group row">
			<label for="date" class="col-sm-2 col-form-label">Start Time</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="startDate"
					name="startDate" autocomplete="off">
			</div>
		</div>
		<div class="form-group row">
			<label for="date" class="col-sm-2 col-form-label">End Time</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="endDate"
					name="endDate" autocomplete="off">
			</div>
		</div>
		
		<div class="form-group row">
			<div class="offset-sm-2 col-sm-8">
				<button type="submit" id="searchPerformanceReport" class="btn btn-primary">Search</button>
			</div>
		</div>
	</form>
</div>

<div id="searchResultsInfo">
    <h6>${displayText}</h6>
</div>

<div>
	<c:if test="${fn:length(performanceReportList) gt 0}">
		<h5>Report Details</h5>
		<div class="col-sm-4">
			<div class="card">
				<ul class="list-group list-group-flush">
					<li class="list-group-item"><span class="detailsHeading">Driver
							Fees : </span><fmt:formatNumber type="number" maxFractionDigits="2" value="${performanceConsolidatedDetails.driverFees}" /></li>
							
					<li class="list-group-item"><span class="detailsHeading">Owner
							Income : </span><fmt:formatNumber type="number" maxFractionDigits="2" value="${performanceConsolidatedDetails.ownerIncome}" /></li>
					<li class="list-group-item"><span class="detailsHeading">Fuel
							Cost : </span><fmt:formatNumber type="number" maxFractionDigits="2" value="${performanceConsolidatedDetails.fuelCost}" /></li>
				</ul>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="card">
				<ul class="list-group list-group-flush">
					<li class="list-group-item"><span class="detailsHeading">Maintenance
							Cost : </span><fmt:formatNumber type="number" maxFractionDigits="2" value="${performanceConsolidatedDetails.maintanenceCost}" /> </li>
					<li class="list-group-item"><span class="detailsHeading">Total
							Earnings : </span><fmt:formatNumber type="number" maxFractionDigits="2" value="${performanceConsolidatedDetails.totalEarnings}" /></li>
					<li class="list-group-item"><span class="detailsHeading">Total 
							Meter Revenue : </span><fmt:formatNumber type="number" maxFractionDigits="2" value="${performanceConsolidatedDetails.meterRevenue}" /></li>
				</ul>
			</div>
		</div>
		
	</c:if>
</div>


<div>
    <c:if test="${fn:length(performanceReportList) gt 0}">
    <div class="clearFloat"></div>
		<div id="printButton">
			<a
				href="downloadPerformanceReport?startDate=<fmt:formatDate value='${searchCriteria.startDate}' pattern='yyyy/MM/dd'/>&endDate=<fmt:formatDate value='${searchCriteria.endDate}' pattern='yyyy/MM/dd'/>"
				class="btn btn-outline-primary">Print</a>
		</div>
		<div class="clearFloat extraSpace"></div>
    <table class="table">
		<thead>
			<tr>
				<th>#</th>
				<th>Date</th>
				<th>Driver</th>
				<th>Driver Fees</th>
				<th>Owner Income</th>
				<th>Fuel Cost</th>
				<th>Maintenance Type</th>
				<th>Maintenance Cost</th>
			</tr>
		</thead>
		<tbody>
		    <c:set var="count" value="0" scope="page" />
			<c:forEach var="tempPerformance" items="${performanceReportList}">
			   <c:set var="count" value="${count + 1}" scope="page"/>
			   <input type="hidden" name="${tempPerformance.id}" id="${tempPerformance.id}" value='${tempPerformance}'>
				<tr>
					<td>${count}</td>
					<td><a href=#
						onclick="showPerformanceReportDetais('${tempPerformance.id}')"><fmt:formatDate value='${tempPerformance.date}' pattern='dd/MM/yyyy HH:mm'/></a></td>
					<td>${tempPerformance.driverName}</td>	
					<td>${tempPerformance.driverFees}</td>
					<td>${tempPerformance.ownerIncome}</td>
					<td>${tempPerformance.fuelCost}</td>
					<td>${tempPerformance.maintanenceType}</td>
					<td>${tempPerformance.maintanenceCost}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<nav aria-label="Page navigation">
          <ul class="pagination">
           <c:if test="${currentPage != 1}">
            <li class="page-item">
              <a class="page-link" href="reportsPerformanceReports?startDate=<fmt:formatDate value='${searchCriteria.startDate}' pattern='yyyy/MM/dd'/>&endDate=<fmt:formatDate value='${searchCriteria.endDate}' pattern='yyyy/MM/dd'/>&page=${currentPage - 1}" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
                <span class="sr-only">Previous</span>
              </a>
            </li>
            </c:if> 
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active"> <a class="page-link" href="#">${i} <span class="sr-only">(current)</span></a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="reportsPerformanceReports?startDate=<fmt:formatDate value='${searchCriteria.startDate}' pattern='yyyy/MM/dd'/>&endDate=<fmt:formatDate value='${searchCriteria.endDate}' pattern='yyyy/MM/dd'/>&page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt noOfPages}">
            <li class="page-item">
              <a class="page-link" href="reportsPerformanceReports?startDate=<fmt:formatDate value='${searchCriteria.startDate}' pattern='yyyy/MM/dd'/>&endDate=<fmt:formatDate value='${searchCriteria.endDate}' pattern='yyyy/MM/dd'/>&page=${currentPage + 1}" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
                <span class="sr-only">Next</span>
              </a>
            </li>
             </c:if>
          </ul>
        </nav>
	
    </c:if>
</div>	

<!-- Modal: This model displays is the details in a pop up -->
<div class="modal fade" id="performanceReportDetailsModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="performanceReportDetailsModalTitle">Performance Report Details</h4>
			</div>
				<div class="modal-body">
					<div id="performanceReportDetails"> 
					  <table class="table table-striped">
                       <thead>
                         <tr>
                           <th>Details</th>
                           <th>Value</th>
                         </tr>
                       </thead>
                       <tbody id="performanceReportDetailsTable">
                         
                       </tbody>
                     </table>
					
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" id="shiftReportDetailsModalClose">Close</button>
				</div>
		</div>
	</div>
</div>
	


