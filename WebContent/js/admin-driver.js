$("#saveDriver").click(function() {
	
	var errorMessage = "";
	
	if ($("#familyName").val() == "" ) {
		errorMessage += "Please enter Driver's Family Name<br/>";
	}
	
	if ($("#givenName").val() == "" ) {
		errorMessage += "Hey Please don't miss drives's Given Name<br/>";
	}
	
	if (errorMessage != "") {
		$("#errorAlert").html(errorMessage);
		$("#errorAlert").show();
		return false;
	}
	
})

function showDriverDetails(id) {
	var data = $("#" + id +"").val()
	var obj = JSON.parse(data);
	$("#editDriverAlert").hide();
	$('#driverId').val(obj.id);
	$('#editFamilyName').val(obj.familyName);
	$('#editGivenName').val(obj.givenName);
	$('#editDc').val(obj.dc);
	$('#editAbn').val(obj.abn);
	setAllTextAreasReadOnly()
	$("#editDriverButton").hide();
	$("#driverDetailsModal").modal();
}

function setAllTextAreasReadOnly() {
	$('#editFamilyName').attr('readonly', true);
	$('#editGivenName').attr('readonly', true);
	$('#editDc').attr('readonly', true);
	$('#editAbn').attr('readonly', true);
}

function setAllTextAreasEditable() {
	$('#editFamilyName').attr('readonly', false);
	$('#editGivenName').attr('readonly', false);
	$('#editDc').attr('readonly', false);
	$('#editAbn').attr('readonly', false);
}


function showEditModal(id) {
	var data = $("#" + id +"").val()
	var obj = JSON.parse(data);
	$("#editDriverAlert").hide();
	setAllTextAreasEditable();
	$('#driverId').val(obj.id);
	$('#editFamilyName').val(obj.familyName);
	$('#editGivenName').val(obj.givenName);
	$('#editDc').val(obj.dc);
	$('#editAbn').val(obj.abn);
	$("#editDriverButton").show();
	$("#driverDetailsModal").modal();
}

$("#editDriverButton").click(function() {
var errorMessage = "";
	
	if ($("#editFamilyName").val() == "" ) {
		errorMessage += "Please enter Driver's Family Name<br/>";
	}
	
	if ($("#editGivenName").val() == "" ) {
		errorMessage += "Hey Please don't miss drives's Given Name<br/>";
	}
	
	if (errorMessage != "") {
		$("#editDriverAlert").html(errorMessage);
		$("#editDriverAlert").show();
		return false;
	}
	
})

$("#editCarModalClose").click(function() {
	$('#editCarModal').modal('hide');
})

function deleteDriver(id) {
	var data = $("#" + id +"").val()
	var obj = JSON.parse(data);
	var isOk = confirm("You are about to delete the driver " + obj.givenName + " " + obj.familyName);
	if (!isOk)
		return false;
	$.get( "deleteDriver?id=" + id, function( data ) {
		location.reload();
	});
}



