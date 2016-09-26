<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
    <div class="alert alert-danger errAlert" role="alert" id="errorAlert">
    </div>   
    <h3>${displayText}</h3>
    <div class="alert alert-danger errAlert" role="alert" id="errorAlert"></div>
	<form action="${action}" id="addExpenseForm" method="post">
	    <input type="hidden" name="id" value="${shiftReport.id}"/>
	    <input type="hidden" name="searchAfterEditStartDate" id="searchAfterEditStartDate" value="<fmt:formatDate value='${searchCriteria.startDate}' pattern='yyyy/MM/dd'/>"/>
	    <input type="hidden" name="searchAfterEditEndDate" id="searchAfterEditEndDate" value="<fmt:formatDate value='${searchCriteria.endDate}' pattern='yyyy/MM/dd'/>"/>
		<div class="form-group row">
			<label for="date" class="col-sm-2 col-form-label">Date</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="date" name="date" autocomplete="off">
			</div>
		</div>
		
		<div class="form-group row">
			<label for="type" class="col-sm-2 col-form-label">Type</label>
			<div class="col-sm-6">
				<select class="form-control" id="type" name="type" >
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
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="amount" name="amount" step="any" 
                       min="0" max="100000"  aria-label="Amount (to the nearest dollar)">
                </div>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="meterReading" class="col-sm-2 col-form-label">Meter Reading</label>
			<div class="col-sm-4">
                <input type="number" class="form-control" id="meterReading" name="meterReading"
                   min="0" max="10000000" aria-label="Amount (to the nearest dollar)">
			</div>
		</div>
		
		<div class="form-group row">
            <label for="expenseDesc" class="col-sm-2 col-form-label">Description</label>
            <div class="col-sm-8">
                <textarea class="form-control" name="description" id="description" rows="4" maxlength="300"></textarea>
            </div>
        </div>
		
		<div class="form-group row">
			<div class="offset-sm-2 col-sm-8">
				<button type="submit" id="saveExpense" class="btn btn-primary">Save</button>
			</div>
		</div>

	</form>

</div>



