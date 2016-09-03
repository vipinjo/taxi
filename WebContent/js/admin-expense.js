$("#saveExpenseType").click(function() {
	var errorMessage = "";
	
	if ($("#expenseName").val() == "" ) {
		errorMessage += "Please enter Expense Name<br/>";
	}
	
	if (errorMessage != "") {
		$("#errorAlert").html(errorMessage);
		$("#errorAlert").show();
		return false;
	}
	
})

function showEditModal(id, name, description) {
	setValuesInEditExpenseTypeModal(id, name, description)
	$('#editExpenseTypeAlert').hide();
	$("#editExpenseTypeModal").modal();
}

function setValuesInEditExpenseTypeModal(id, name, description) {
	$('#expenseTypeId').val(id);
	$('#editExpenseName').val(name);
	$('#editExpenseDesc').val(description);
}

$("#editExpenseType").click(function() {
var errorMessage = "";
	
	if ($("#editExpenseName").val() == "" ) {
		errorMessage += "Hey, I think you forgot to enter the Expense Name<br/>";
	}
	
	if (errorMessage != "") {
		$("#editExpeseTypeAlert").html(errorMessage);
		$("#editExpenseTypeAlert").show();
		return false;
	}
	
})

$("#editExpenseTypeModalClose").click(function() {
	$('#editExpenseTypeModal').modal('hide');
})



