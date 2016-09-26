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

$("#searchPerformanceReport").click(function(){
	$("#errorAlert").hide();
	var errorMessage = "";
	if ($("#startDate").val() == 0 ) {
		errorMessage += "Please enter start date";
	}
	if (errorMessage != "") {
		$("#errorAlert").html(errorMessage);
		$("#errorAlert").show();
		return false;
	}
});

function showPerformanceReportDetais(id) {
	var data = $("#" + id +"").val()
	var obj = JSON.parse(data);
	$("#performanceReportDetailsTable").empty();
	$("#performanceReportDetailsTable").append("<tr> <td> Date </td> <td>" + obj.date  +"</td></tr>");
	$("#performanceReportDetailsTable").append("<tr> <td> Meter Revenue </td> <td>" + obj.meterRevenue  +"</td></tr>");
	$("#performanceReportDetailsTable").append("<tr> <td> Driver Fees </td> <td>" + obj.driverFees  +"</td></tr>");
	$("#performanceReportDetailsTable").append("<tr> <td> Owner Revenue </td> <td>" + obj.ownerIncome  +"</td></tr>");
	$("#performanceReportDetailsTable").append("<tr> <td> Fuel</td> <td>" + obj.fuelCost  +"</td></tr>");
	$("#performanceReportDetailsTable").append("<tr> <td> Meter Reading</td> <td>" + obj.meterReading  +"</td></tr>");
	if (typeof obj.maintanenceType != 'undefined')
	    $("#performanceReportDetailsTable").append("<tr> <td> Maintenance Type</td> <td>" + obj.maintanenceType  +"</td></tr>");
	$("#performanceReportDetailsTable").append("<tr> <td> Maintenance Cost</td> <td>" + obj.maintanenceCost  +"</td></tr>");
	if (typeof obj.maintanenceDescription != 'undefined')
	    $("#performanceReportDetailsTable").append("<tr> <td> Maintenance Details</td> <td>" + obj.maintanenceDescription  +"</td></tr>");
	$("#performanceReportDetailsModal").modal();
}





