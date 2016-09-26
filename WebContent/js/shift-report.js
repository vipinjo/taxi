jQuery(function() {
	jQuery('#startDate').datetimepicker(
			{
				format : 'Y/m/d',
				onShow : function(ct) {
					this.setOptions({
						maxDate : jQuery('#endDate').val() ? jQuery('#endDate')
								.val() : false
					})
				},
				timepicker : false,
			});
	jQuery('#endDate').datetimepicker(
			{
				format : 'Y/m/d',
				onShow : function(ct) {
					this
							.setOptions({
								minDate : jQuery('#startDate')
										.val() ? jQuery(
										'#startDate').val() : false
							})
							
				},
				timepicker : false,
			});
});

function showShiftReportDetais(id) {
	var data = $("#" + id +"").val()
	var obj = JSON.parse(data);
	$("#shiftReportDetailsTable").empty();
	$("#shiftReportDetailsTable").append("<tr> <td> Start Time </td> <td>" + obj.date  +"</td></tr>");
	$("#shiftReportDetailsTable").append("<tr> <td> Finish Time </td> <td>" + obj.finishDate  +"</td></tr>");
	$("#shiftReportDetailsTable").append("<tr> <td> Driver </td> <td>" + obj.driver.givenName + " " + obj.driver.familyName  +"</td></tr>");
	$("#shiftReportDetailsTable").append("<tr> <td> Car </td> <td>" + obj.car.rego +"</td></tr>");
	$("#shiftReportDetailsTable").append("<tr> <td> Start Meter Reading </td> <td>" + obj.startMeterReading +"</td></tr>");
	$("#shiftReportDetailsTable").append("<tr> <td> End Meter Reading </td> <td>" + obj.endMeterReading +"</td></tr>");
	$("#shiftReportDetailsTable").append("<tr> <td> Meter Revenue </td> <td>" + obj.meterRevenue  +"</td></tr>");
	$("#shiftReportDetailsTable").append("<tr> <td> Owner Revenue </td> <td>" + obj.ownerRevenue  +"</td></tr>");
	$("#shiftReportDetailsTable").append("<tr> <td> Owner Subsidy </td> <td>" + obj.ownerSubsidy  +"</td></tr>");
	$("#shiftReportDetailsTable").append("<tr> <td> Bailment Fee </td> <td>" + obj.bailmentFee  +"</td></tr>");
	$("#shiftReportDetailsTable").append("<tr> <td> Paper Voucher </td> <td>" + obj.paperVoucher  +"</td></tr>");
	$("#shiftReportDetailsTable").append("<tr> <td> Fuel Receipt </td> <td>" + obj.fuelReceipt  +"</td></tr>");
	$("#shiftReportDetailsTable").append("<tr> <td> Online Receipt </td> <td>" + obj.onlineReceipt  +"</td></tr>");
	$("#shiftReportDetailsTable").append("<tr> <td> Account Voucher </td> <td>" + obj.accountVoucher  +"</td></tr>");
	$("#shiftReportDetailsTable").append("<tr> <td> Total </td> <td>" + obj.total  +"</td></tr>");
	$("#shiftReportDetailsModal").modal();
}

function deleteShiftReport(id) {
	var data = $("#" + id + "").val()
	var obj = JSON.parse(data);
	var shouldDelete = confirm("You are about to delete shift resport : "
			+ obj.date + " drove " + obj.driver.givenName + " "
			+ obj.driver.familyName);
	if (!shouldDelete)
		return false;
	$.get("deleteShiftReport?id=" + id, function(data) {
		location.reload();
	});
}



