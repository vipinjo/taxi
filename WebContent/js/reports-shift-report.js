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

$("#searchShiftReport").click(function(){
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





