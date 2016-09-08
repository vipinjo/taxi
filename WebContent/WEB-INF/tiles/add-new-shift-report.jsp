<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
    <div class="alert alert-danger errAlert" role="alert" id="errorAlert">
    </div>   
	<form action="addShiftReport" id="addShiftReportForm">
		<div class="form-group row">
			<label for="date" class="col-sm-2 col-form-label">Start Time</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="startDate" name="startDate">
			</div>
		</div>
		<div class="form-group row">
			<label for="date" class="col-sm-2 col-form-label">Finish Time</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="finishDate" name="finishDate">
			</div>
		</div>
		<div class="form-group row">
			<label for="meterRevenue" class="col-sm-2 col-form-label">Meter Revenue</label>
			<div class="col-sm-4">
				<div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="meterRevenue" name="meterRevenue" aria-label="Amount (to the nearest dollar)">
                </div>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="ownerRevenue" class="col-sm-2 col-form-label">Owner Revenue</label>
			<div class="col-sm-4">
				<div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="ownerRevenue" name="ownerRevenue" aria-label="Amount (to the nearest dollar)" readonly>
                </div>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="subsidy" class="col-sm-2 col-form-label">Subsidy</label>
			<div class="col-sm-4">
				<div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="subsidy" name="subsidy" aria-label="Amount (to the nearest dollar)">
                </div>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="bailmentFee" class="col-sm-2 col-form-label">Bailment Fee</label>
			<div class="col-sm-4">
				<div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="bailmentFee" name="bailmentFee" aria-label="Amount (to the nearest dollar)" readonly>
                </div>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="paperVoucher" class="col-sm-2 col-form-label">Paper Voucher</label>
			<div class="col-sm-4">
				<div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="paperVoucher" name="paperVoucher" aria-label="Amount (to the nearest dollar)">
                </div>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="onlineReceipt" class="col-sm-2 col-form-label">Online Receipts (EFT Pos)</label>
			<div class="col-sm-4">
				<div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="onlineReceipt" name="onlineReceipt" aria-label="Amount (to the nearest dollar)">
                </div>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="onlineReceipt" class="col-sm-2 col-form-label">Fuel Receipt</label>
			<div class="col-sm-4">
				<div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="fuelReceipt" name="fuelReceipt" aria-label="Amount (to the nearest dollar)">
                </div>
			</div>
		</div>
		
		<div class="form-group row">
			<label for="total" class="col-sm-2 col-form-label">Total</label>
			<div class="col-sm-4">
				<div class="input-group">
                    <span class="input-group-addon">$</span>
                    <input type="number" class="form-control" id="total" name="total" aria-label="Amount (to the nearest dollar)" readonly>
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



