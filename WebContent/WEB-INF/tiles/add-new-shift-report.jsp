<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
    <div class="alert alert-danger errAlert" role="alert" id="errorAlert">
    </div>   
    <h3>${displayText}</h3>
	<form action="${action}" id="addShiftReportForm" method="post">
	    <input type="hidden" name="id" value="${shiftReport.id}"/>
	    <input type="hidden" name="searchAfterEditStartDate" id="searchAfterEditStartDate" value="<fmt:formatDate value='${searchCriteria.startDate}' pattern='yyyy/MM/dd'/>"/>
	    <input type="hidden" name="searchAfterEditEndDate" id="searchAfterEditEndDate" value="<fmt:formatDate value='${searchCriteria.endDate}' pattern='yyyy/MM/dd'/>"/>
		<div class="form-group row">
			<label for="date" class="col-sm-2 col-form-label">Start Time</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="startDate" name="startDate" value="<fmt:formatDate value='${shiftReport.date}' pattern='yyyy/MM/dd HH:mm'/>">
			</div>
		</div>
		<div class="form-group row">
			<label for="date" class="col-sm-2 col-form-label">Finish Time</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="finishDate" name="finishDate" value="<fmt:formatDate value='${shiftReport.finishDate}' pattern='yyyy/MM/dd HH:mm'/>">
			</div>
		</div>
		<div class="form-group row">
			<label for="date" class="col-sm-2 col-form-label">Driver</label>
			<div class="col-sm-6">
				<select class="form-control" id="driver" name="driver" >
                    <option></option>
                    <c:forEach var="tempDriver" items="${driverList}">
                        <option value="${tempDriver.id}" <c:if test = "${tempDriver.id == shiftReport.driver.id }">selected</c:if>>${tempDriver.givenName} ${tempDriver.familyName}</option>
                    </c:forEach>
                </select>
			</div>
		</div>
		<div class="form-group row">
			<label for="date" class="col-sm-2 col-form-label">Car</label>
			<div class="col-sm-6">
				<select class="form-control" id="car" name="car">
                    <option></option>
                    <c:forEach var="tempCar" items="${carList}">
                        <option value="${tempCar.id}" <c:if test = "${tempCar.id == shiftReport.car.id }">selected</c:if>>${tempCar.rego}</option>
                    </c:forEach>
                </select>
			</div>
		</div>
		<div class="form-group row">
			<label for="startMeterReading" class="col-sm-2 col-form-label">Start Meter Reading</label>
			<div class="col-sm-4">
                <input type="number" class="form-control" id="startMeterReading" name="startMeterReading"
                   min="0" max="10000000" aria-label="Amount (to the nearest dollar)" value="${shiftReport.startMeterReading}">
			</div>
		</div>
		<div class="form-group row">
			<label for="startMeterReading" class="col-sm-2 col-form-label">End Meter Reading</label>
			<div class="col-sm-4">
                <input type="number" class="form-control" id="endMeterReading" name="endMeterReading" 
                    min="0" max="10000000" aria-label="Amount (to the nearest dollar)" value="${shiftReport.endMeterReading}">
			</div>
		</div>
		<div class="form-group row">
			<label for="meterRevenue" class="col-sm-2 col-form-label">Meter Revenue</label>
			<div class="col-sm-4">
				<div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="meterRevenue" name="meterRevenue" step="any" 
                       min="0" max="100000"  aria-label="Amount (to the nearest dollar)" value="${shiftReport.meterRevenue}">
                </div>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="ownerRevenue" class="col-sm-2 col-form-label">Owner Revenue</label>
			<div class="col-sm-4">
				<div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="ownerRevenue" name="ownerRevenue" step="any" 
                    min="0" max="100000" aria-label="Amount (to the nearest dollar)" value="${shiftReport.ownerRevenue}" readonly>
                </div>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="subsidy" class="col-sm-2 col-form-label">Subsidy</label>
			<div class="col-sm-4">
				<div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="subsidy" name="subsidy" step="any" 
                      min="0" max="100000"  aria-label="Amount (to the nearest dollar)" value="${shiftReport.ownerSubsidy}">
                </div>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="bailmentFee" class="col-sm-2 col-form-label">Bailment Fee</label>
			<div class="col-sm-4">
				<div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="bailmentFee" name="bailmentFee" step="any"
                       min="0" max="100000" aria-label="Amount (to the nearest dollar)" value="${shiftReport.bailmentFee}" readonly>
                </div>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="paperVoucher" class="col-sm-2 col-form-label">Paper Voucher</label>
			<div class="col-sm-4">
				<div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="paperVoucher" name="paperVoucher" step="any"
                        min="0" max="100000" aria-label="Amount (to the nearest dollar)" value="${shiftReport.paperVoucher}">
                </div>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="onlineReceipt" class="col-sm-2 col-form-label">Online Receipts (EFT Pos)</label>
			<div class="col-sm-4">
				<div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="onlineReceipt" name="onlineReceipt" step="any" 
                        min="0" max="100000" aria-label="Amount (to the nearest dollar)" value="${shiftReport.onlineReceipt}">
                </div>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="accountVoucher" class="col-sm-2 col-form-label">Account Voucher</label>
			<div class="col-sm-4">
				<div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="accountVoucher" name="accountVoucher" step="any" 
                        min="0" max="100000" aria-label="Amount (to the nearest dollar)" value="${shiftReport.accountVoucher}">
                </div>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="onlineReceipt" class="col-sm-2 col-form-label">Fuel Receipt</label>
			<div class="col-sm-4">
				<div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="fuelReceipt" name="fuelReceipt" step="any" 
                        min="0" max="100000" aria-label="Amount (to the nearest dollar)" value="${shiftReport.fuelReceipt}">
                </div>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="driverRevenue" class="col-sm-2 col-form-label">Driver Revenue</label>
			<div class="col-sm-4">
				<div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="driverRevenue" name="driverRevenue" step="any" 
                        min="0" max="100000" aria-label="Amount (to the nearest dollar)" value="${shiftReport.driverRevenue}" readonly>
                </div>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="driverSubsidy" class="col-sm-2 col-form-label">Driver Subsidy</label>
			<div class="col-sm-4">
				<div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="driverSubsidy" name="driverSubsidy" step="any" 
                        min="0" max="100000" aria-label="Amount (to the nearest dollar)" value="${shiftReport.driverSubsidy}">
                </div>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="total" class="col-sm-2 col-form-label">Total</label>
			<div class="col-sm-4">
				<div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="total" name="total" step="any" 
                        min="0" max="100000" aria-label="Amount (to the nearest dollar)" value="${shiftReport.total}" readonly>
                </div>
			</div>
		</div>
		
		<div class="form-group row">
			<div class="offset-sm-2 col-sm-8">
				<button type="submit" id="saveShiftReport" class="btn btn-primary">Save</button>
			</div>
		</div>

	</form>

</div>



