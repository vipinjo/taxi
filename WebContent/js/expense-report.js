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
	jQuery('#date').datetimepicker(
			{
				format : 'Y/m/d',
				timepicker : false,
			});
});

$("#searchExpense").click(function(){
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

function showExpenseDetails(id) {
	clearTheErrorMessages();
	setTheValuesInModal(id)
	setDetailsPageReadOnly();
	$("#expenseDetailsModalTitle").html("Expense Details");
	$("#editExpenseButton").hide();
	$("#expenseDetailsModal").modal();
}

function editExpenses(id) {
	clearTheErrorMessages();
	setDetailsPageEditable();
	setTheValuesInModal(id);
	$("#expenseDetailsModalTitle").html("Update Expense Details");
	$("#editExpenseButton").show();
	$("#expenseDetailsModal").modal();
}

function setDetailsPageReadOnly() {
	$("#date").prop('readonly', true);
	$("#type").attr("disabled", true);
	$("#amount").prop('readonly', true);
	$("#meterReading").prop('readonly', true);
	$("#description").prop('readonly', true);
}

function setDetailsPageEditable() {
	$("#date").prop('readonly', false);
	$("#type").attr("disabled", false);
	$("#amount").prop('readonly', false);
	$("#meterReading").prop('readonly', false);
	$("#description").prop('readonly', false);
}

function setTheValuesInModal(id) {
	var data = $("#" + id +"").val();
	var obj = JSON.parse(data);
	$("#id").val(obj.id);
	$("#date").val(obj.date);
	$('#type option[value=' + obj.expenseType.id +']').attr('selected','selected');
	$("#amount").val(obj.expenseAmount);
	$("#meterReading").val(obj.meterReading);
	$("#description").val(obj.description);
}

$("#editExpenseButton").click(function() {
	var errorMessage = "";

	if ($("#date").val() == "") {
		errorMessage = "date is empty</br>";
	}

	if ($("#amount").val() == "") {
		errorMessage += "Please enter amount</br>";
	}

	if (errorMessage != "") {
		$("#editExpenseAlert").html(errorMessage);
		$("#editExpenseAlert").show();
		return false;
	}
});

function clearTheErrorMessages() {
	$("#editExpenseAlert").html("");
	$("#editExpenseAlert").hide();
}

function deleteExpenseReport(id) {
	var data = $("#" + id + "").val()
	var obj = JSON.parse(data);
	var shouldDelete = confirm("You are about to delete the expense on : "
			+ obj.date + " on " + obj.expenseType.name + " for $"
			+ obj.expenseAmount);
	if (!shouldDelete)
		return false;

	$.get("deleteExpense?id=" + id + "&searchCriteriaStartDate"
			+ $("#searchCriteriaStartDate") + "&searchCriteriaEndDate"
			+ $("#searchCriteriaEndDate"), function(data) {
		location.reload();
	});

}



