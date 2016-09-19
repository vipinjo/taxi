jQuery('#date').datetimepicker({
	format : 'Y/m/d',
	onShow : function(ct) {
		this.setOptions({
			maxDate : jQuery('#endDate').val() ? jQuery('#endDate')
					.val() : false
		})
	},
	timepicker : false,
});

$("#saveExpense").click(function(){
	var errorMessage = "";
	
	if ($("#date").val() == "") {
		errorMessage = "date is empty</br>";
	}
	
	if ($("#amount").val() == "") {
		errorMessage += "Please enter amount</br>";
	}
	
	if (errorMessage != "") {
		$("#errorAlert").html(errorMessage);
		$("#errorAlert").show();
		return false;
	}
	
	
	
});
	
