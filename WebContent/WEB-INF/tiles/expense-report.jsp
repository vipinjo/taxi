<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
    <div class="alert alert-danger errAlert" role="alert" id="errorAlert"></div>
	<form action="searchExpenses" id="searchExpenseForm">
		<div class="form-group row">
			<label for="startDate" class="col-sm-2 col-form-label">Start Time</label>
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
				<button type="submit" id="searchExpense" class="btn btn-primary">Search</button>
			</div>
		</div>
	</form>
</div>
<div id="searchResultsInfo">
    <h6>${displayText}</h6>
</div>

<div>
    <c:if test="${fn:length(transactionList) gt 0}">
    <table class="table">
		<thead>
			<tr>
				<th>#</th>
				<th>Date</th>
				<th>Expense</th>
				<th>Amount</th>
				<th>Meter Reading</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
		    <c:set var="count" value="0" scope="page" />
			<c:forEach var="tempTransaction" items="${transactionList}">
			   <c:set var="count" value="${count + 1}" scope="page"/>
			   <input type="hidden" name="${tempTransaction.id}" id="${tempTransaction.id}" value='${tempTransaction}'>
				<tr>
					<td>${count}</td>
					<td><a href=#
						onclick="showExpenseDetails('${tempTransaction.id}')"><fmt:formatDate value='${tempTransaction.date}' pattern='dd/MM/yyyy'/></a></td>
					<td>${tempTransaction.expenseType.name}</td>	
					<td>${tempTransaction.expenseAmount}</td>
					<td>${tempTransaction.meterReading}</td>
					<td><a href=# onclick="editExpenses('${tempTransaction.id}')">Edit</a>
					</td>
					<td>
					  <a href="#" onclick="deleteExpenseReport('${tempTransaction.id}')">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
    </c:if>
</div>

<!-- Modal: This model displays is the details in a pop up -->
<div class="modal fade" id="expenseDetailsModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="expenseDetailsModalTitle"></h4>
			</div>
			<form action="editExpense" id="editExpense" method="post">
				<input type="hidden" name="id" id="id" value="">
				<input type="hidden" name="searchCriteriaStartDate" id="searchCriteriaStartDate" value="<fmt:formatDate value='${searchCriteria.startDate}' pattern='yyyy/MM/dd'/>">
			    <input type="hidden" name="searchCriteriaEndDate" id="searchCriteriaEndDate" value="<fmt:formatDate value='${searchCriteria.endDate}' pattern='yyyy/MM/dd'/>">
				<div class="modal-body">
				<div class="alert alert-danger errAlert" role="alert"
						id="editExpenseAlert"></div>
					<div class="form-group row">
						<label for="date" class="col-sm-2 col-form-label">Date</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="date" name="date">
						</div>
					</div>
					<div class="form-group row">
						<label for="type" class="col-sm-2 col-form-label">Type</label>
						<div class="col-sm-6">
							<select class="form-control" id="type" name="type">
								<option value="">Select Expense Type</option>
								<c:forEach var="tempExpenseType" items="${expenseTypeList}">
									<option value="${tempExpenseType.id}">${tempExpenseType.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group row">
						<label for="amount" class="col-sm-2 col-form-label">Amount</label>
						<div class="col-sm-4">
							<div class="input-group">
								<span class="input-group-addon">$</span> <input type="number"
									class="form-control" id="amount" name="amount" step="any"
									min="0" max="100000"
									aria-label="Amount (to the nearest dollar)">
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label for="meterReading" class="col-sm-2 col-form-label">Meter
							Reading</label>
						<div class="col-sm-4">
							<input type="number" class="form-control" id="meterReading"
								name="meterReading" min="0" max="10000000"
								aria-label="Amount (to the nearest dollar)">
						</div>
					</div>
					<div class="form-group row">
						<label for="expenseDesc" class="col-sm-2 col-form-label">Description</label>
						<div class="col-sm-8">
							<textarea class="form-control" name="description"
								id="description" rows="4" maxlength="300"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" id="editCarModalClose">Close</button>
					<button type="submit" class="btn btn-primary" id=editExpenseButton>Save
						changes</button>	
				</div>
			</form>
		</div>
	</div>
</div>



