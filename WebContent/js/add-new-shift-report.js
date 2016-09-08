jQuery('#startDate').datetimepicker();
jQuery('#finishDate').datetimepicker();


$('#meterRevenue').focusout(
		function() {
			updateOwnerRevenue();
		});

$('#subsidy').focusout(
		function() {
			updateBailmentFee();
		});

$('#addShiftReportForm').mouseover(function(){
	updateOwnerRevenue();
	updateBailmentFee();
	updateTotal();
});

function updateOwnerRevenue() {
	if ($('#meterRevenue').val().length > 0 && $.isNumeric($('#meterRevenue').val())) {
		$('#ownerRevenue').val(getOwnerRevenue($('#meterRevenue').val()))
		return;
	} else {
		$('#ownerRevenue').val(parseFloat("0.00").toFixed(2));
	}
}

function updateBailmentFee() {
	if ($('#meterRevenue').val().length > 0 && $.isNumeric($('#meterRevenue').val())) {
	    var ownerRev = getOwnerRevenue($('#meterRevenue').val());
	    var bailment = parseFloat(ownerRev);
	    var total = bailment;
	    if ($('#subsidy').val() != "") {
	    	total = parseFloat(bailment) + parseFloat($('#subsidy').val());
	    }
	    $('#bailmentFee').val(total.toFixed(2));
	} else {
		 $('#bailmentFee').val(parseFloat("0.00").toFixed(2));
	}
}

function updateTotal() {

	if ($('#bailmentFee').val().length > 0
			&& parseFloat($('#bailmentFee').val()) > 0) {
        
		if (isTotalAmountBreakUpCorrect()) {
			 var bailmentFee = parseFloat($('#bailmentFee').val()).toFixed(2);
			 $('#total').val(bailmentFee); 
		}
	}

}

$( document ).ready(function() {
	setDateToTodaysDate();
});

function setDateToTodaysDate() {
	
	var d = new Date();

	var month = d.getMonth()+1;
	var day = d.getDate();

	var output = d.getFullYear() + '-' +
	    (month<10 ? '0' : '') + month + '-' +
	    (day<10 ? '0' : '') + day;
	$('#date').val(output);
}

function isTotalAmountBreakUpCorrect() {
	
	return true;
	
	//TODO: implement this method:
	// check the functionality with Jomon chettan and finalize this method. 
	
//	if ($('#bailmentFee').val() != ""
//			&& parseFloat($('#bailmentFee').val()) > 0) {
//
//		if ($('#paperVoucher').val().length > 0
//				|| $('#onlineReceipt').val().length > 0) {
//			var total = 0.00;
//			if ($('#paperVoucher').val().length > 0
//					&& $('#onlineReceipt').val().length > 0) {
//				total = parseFloat($('#paperVoucher').val())
//						+ parseFloat($('#onlineReceipt').val());
//			} else if ($('#paperVoucher').val().length > 0) {
//				total = parseFloat($('#paperVoucher').val())
//			} else {
//				total = parseFloat($('#onlineReceipt').val());
//			}
//
//			total = parseFloat(total).toFixed(2);
//			if (parseFloat($('#bailmentFee').val()).toFixed(2) == total) {
//				// alert("calculations are correct");
//			} else {
//				// alert("calculations are wrong");
//			}
//
//		}
//
//	}
}

function getOwnerRevenue(meterRevenue) {
	var ownerRev = meterRevenue * 45 / 100;
	return ownerRev.toFixed(2);
}

function isValidStartEndTime() {
	
	if ($("#startDate").val().length == 0 || $("#finishDate").val().length == 0)
		return false;
	var startDate = new Date($("#startDate").val());
	var endDate = new Date($("#finishDate").val());

	if (endDate > startDate)
		return true;
	else
		return false;
	
}

$("#saveShiftReport").click(function() {
	
	$("#errorAlert").html("");
	$("#errorAlert").hide();
	
	var errorMessage = "";
	
	if ($("#startDate").val() == "" ) {
		errorMessage += "Please enter Start date and time<br/>";
	}
	
	if ($("#finishDate").val() == "" ) {
		errorMessage += "Please enter Finish date and time<br/>";
	}
	
	if (!isValidStartEndTime()) {
		errorMessage += "End date must be greater than start date<br/>";
	}
	
	if ($("#meterRevenue").val() == "" ) {
		errorMessage += "Please enter Meter Revenue<br/>";
	}
	
	if (errorMessage != "") {
		$("#errorAlert").html(errorMessage);
		$("#errorAlert").show();
		return false;
	}
	
});