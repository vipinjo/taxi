<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
	<form action="searchShiftReport" id="searchShiftReportForm">
		<div class="form-group row">
			<label for="date" class="col-sm-2 col-form-label">Start Time</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="startDate"
					name="startDate">
			</div>
		</div>
		<div class="form-group row">
			<label for="date" class="col-sm-2 col-form-label">End Time</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="endDate"
					name="endDate">
			</div>
		</div>
		
		<div class="form-group row">
			<div class="offset-sm-2 col-sm-8">
				<button type="submit" id="saveShiftReport" class="btn btn-primary">Search</button>
			</div>
		</div>
	</form>
</div>

<div id="searchResultsInfo">
    <h6>${displayText}</h6>
</div>

<div>
    <c:if test="${fn:length(shiftReportList) gt 0}">
    <table class="table">
		<thead>
			<tr>
				<th>#</th>
				<th>Date</th>
				<th>Meter Revenue</th>
				<th>Owner Revenue</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
		    <c:set var="count" value="0" scope="page" />
			<c:forEach var="tempShiftReport" items="${shiftReportList}">
			   <c:set var="count" value="${count + 1}" scope="page"/>
			   <input type="hidden" name="${tempShiftReport.id}" id="${tempShiftReport.id}" value='${tempShiftReport}'>
				<tr>
					<td>${count}</td>
					<td><a href=#
						onclick="showShiftReportDetais('${tempShiftReport.id}')"><fmt:formatDate value='${tempShiftReport.date}' pattern='dd/MM/yyyy HH:mm'/></a></td>
					<td>${tempShiftReport.meterRevenue}</td>
					<td>${tempShiftReport.ownerRevenue}</td>
					<td><a href="editShiftReport?id=${tempShiftReport.id}&startDate=<fmt:formatDate value='${searchCriteria.startDate}' pattern='yyyy/MM/dd'/>&endDate=<fmt:formatDate value='${searchCriteria.endDate}' pattern='yyyy/MM/dd'/>">Edit</a>
					</td>
					<td><a href="showAddNewShiftReportPage?id=${tempShiftReport.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
    </c:if>
</div>	

<!-- Modal: This model displays is the details in a pop up -->
<div class="modal fade" id="shiftReportDetailsModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="shiftReportDetailsModal">Shift Report Details</h4>
			</div>
				<input type="hidden" name="carId" id="carId" value="">
				<div class="modal-body">
					<div id="shiftReportDetails"> 
					  <table class="table table-striped">
                       <thead>
                         <tr>
                           <th>Details</th>
                           <th>Value</th>
                         </tr>
                       </thead>
                       <tbody id="shiftReportDetailsTable">
                         
                       </tbody>
                     </table>
					
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" id="editCarModalClose">Close</button>
				</div>
		</div>
	</div>
</div>
	


