<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div>
	<div class="alert alert-danger errAlert" role="alert" id="errorAlert"></div>
	<form action="reportsShiftReports" id="searchShiftReportForm">
		<h5>Search Shift Report</h5>
		<div class="form-group row">
			<label for="date" class="col-sm-2 col-form-label">Start Date</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="startDate"
					name="startDate" autocomplete="off">
			</div>
		</div>
		<div class="form-group row">
			<label for="date" class="col-sm-2 col-form-label">End Date</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="endDate" name="endDate" autocomplete="off">
			</div>
		</div>
		<hr>
		<h5>Filter the results with</h5>
		<div class="form-group row">
			<label for="date" class="col-sm-2 col-form-label">Driver</label>
			<div class="col-sm-6">
				<select class="form-control" id="driver" name="driver">
					<option></option>
					<c:forEach var="tempDriver" items="${driverList}">
						<option value="${tempDriver.id}">${tempDriver.givenName}
							${tempDriver.familyName}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="form-group row">
			<div class="offset-sm-2 col-sm-8">
				<button type="submit" id="searchShiftReport" class="btn btn-primary">Search</button>
			</div>
		</div>
	</form>
</div>

<div id="searchResultsInfo">
	<h6>${displayText}</h6>
</div>

<div>
	<c:if test="${fn:length(shiftReportList) gt 0}">
		<h5>Report Details</h5>
		<div class="col-sm-4">
			<div class="card">
				<ul class="list-group list-group-flush">
					<li class="list-group-item"><span class="detailsHeading">Meter
							Revenue : </span> ${shiftReportDetails.totalMeterRevenue}</li>
					<li class="list-group-item"><span class="detailsHeading">Owner
							Revenue : </span>${shiftReportDetails.totalOwnerRevenue}</li>
					<li class="list-group-item"><span class="detailsHeading">Driver
							Revenue : </span>${shiftReportDetails.totalDriverRevenue}</li>
				</ul>
			</div>
		</div>
		<div class="col-sm-4">
			<div class="card">
				<ul class="list-group list-group-flush">
					<li class="list-group-item"><span class="detailsHeading">Paper
							Voucher : </span> ${shiftReportDetails.paperVoucher}</li>
					<li class="list-group-item"><span class="detailsHeading">Online
							Receipts : </span>${shiftReportDetails.onlineReceipts}</li>
					<li class="list-group-item"><span class="detailsHeading">Account
							Voucher : </span>${shiftReportDetails.accountVoucher}</li>
				</ul>
			</div>
		</div>

		<div class="col-sm-4">
			<div class="card">
				<ul class="list-group list-group-flush">
					<li class="list-group-item"><span class="detailsHeading">Fuel
							Receipts : </span> ${shiftReportDetails.fuelRecipts}</li>
					<li class="list-group-item"><span class="detailsHeading">Total
							: </span>${shiftReportDetails.total}</li>
				</ul>
			</div>
		</div>
	</c:if>
</div>

<div>
	<c:if test="${fn:length(shiftReportList) gt 0}">
		<div class="clearFloat"></div>
		<div id="printButton">
			<a
				href="downloadShiftReportfiles?startDate=<fmt:formatDate value='${searchCriteria.startDate}' pattern='yyyy/MM/dd'/>&endDate=<fmt:formatDate value='${searchCriteria.endDate}' pattern='yyyy/MM/dd'/>&driver=${searchCriteria.driverId}"
				class="btn btn-outline-primary">Print</a>
		</div>
		<div class="clearFloat extraSpace"></div>
		<table class="table">
			<thead>
				<tr>
					<th>#</th>
					<th>Date</th>
					<th>Driver</th>
					<th>Meter Revenue</th>
					<th>Owner Revenue</th>
					<th>Driver Revenue</th>
					<th>paper Voucher</th>
					<th>Online Receipt</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="count" value="0" scope="page" />
				<c:forEach var="tempShiftReport" items="${shiftReportList}">
					<c:set var="count" value="${count + 1}" scope="page" />
					<input type="hidden" name="${tempShiftReport.id}"
						id="${tempShiftReport.id}" value='${tempShiftReport}'>
					<tr>
						<td>${count}</td>
						<td><a href=#
							onclick="showShiftReportDetais('${tempShiftReport.id}')"><fmt:formatDate
									value='${tempShiftReport.date}' pattern='dd/MM/yyyy HH:mm' /></a></td>
						<td>${tempShiftReport.driver.familyName}</td>
						<td>${tempShiftReport.meterRevenue}</td>
						<td>${tempShiftReport.ownerRevenue}</td>
						<td>${tempShiftReport.driverRevenue}</td>
						<td>${tempShiftReport.paperVoucher}</td>
						<td>${tempShiftReport.onlineReceipt}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
        <nav aria-label="Page navigation">
          <ul class="pagination">
           <c:if test="${currentPage != 1}">
            <li class="page-item">
              <a class="page-link" href="reportsShiftReports?startDate=<fmt:formatDate value='${searchCriteria.startDate}' pattern='yyyy/MM/dd'/>&endDate=<fmt:formatDate value='${searchCriteria.endDate}' pattern='yyyy/MM/dd'/>&driver=${searchCriteria.driverId}&page=${currentPage - 1}" aria-label="Previous">
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
                        <li class="page-item"><a class="page-link" href="reportsShiftReports?startDate=<fmt:formatDate value='${searchCriteria.startDate}' pattern='yyyy/MM/dd'/>&endDate=<fmt:formatDate value='${searchCriteria.endDate}' pattern='yyyy/MM/dd'/>&driver=${searchCriteria.driverId}&page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${currentPage lt noOfPages}">
            <li class="page-item">
              <a class="page-link" href="reportsShiftReports?startDate=<fmt:formatDate value='${searchCriteria.startDate}' pattern='yyyy/MM/dd'/>&endDate=<fmt:formatDate value='${searchCriteria.endDate}' pattern='yyyy/MM/dd'/>&driver=${searchCriteria.driverId}&page=${currentPage + 1}" aria-label="Next">
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
<div class="modal fade" id="shiftReportDetailsModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="shiftReportDetailsModal">Shift
					Report Details</h4>
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
			<a id="detailsPrintButton"
				href=""
				class="btn btn-outline-primary">Print</a>
				<button type="button" class="btn btn-secondary" data-dismiss="modal"
					id="shiftReportDetailsModalClose">Close</button>
			</div>
		</div>
	</div>
</div>



