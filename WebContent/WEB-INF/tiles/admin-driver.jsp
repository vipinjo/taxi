<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div>
	<div class="alert alert-danger errAlert" role="alert" id="errorAlert">
	</div>
	<form action="addDriver" method="post">
		<div class="form-group row">
			<label for="familyName" class="col-sm-2 col-form-label">Family Name</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="familyName" name="familyName"
					placeholder="Family Name">
			</div>
		</div>
		<div class="form-group row">
			<label for="rego" class="col-sm-2 col-form-label">Given Name</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="givenName" name="givenName"
					placeholder="Given Name">
			</div>
		</div>
		<div class="form-group row">
			<label for="dc" class="col-sm-2 col-form-label">Dc</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="dc" name="dc"
					placeholder="Dc">
			</div>
		</div>
		<div class="form-group row">
			<label for="abn" class="col-sm-2 col-form-label">ABN</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="abn" name="abn"
					placeholder="ABN">
			</div>
		</div>
		
		<div class="form-group row">
			<div class="offset-sm-2 col-sm-8">
				<button type="submit" id="saveDriver" class="btn btn-primary">Save</button>
			</div>
		</div>
	</form>
</div>
<div>
    <c:if test="${fn:length(driverList) gt 0}">
	<table class="table">
		<thead>
			<tr>
				<th>#</th>
				<th>Family Name</th>
				<th>Given Name</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
		    <c:set var="count" value="0" scope="page" />
			<c:forEach var="tempDriver" items="${driverList}">
			    <c:set var="count" value="${count + 1}" scope="page"/>
			    <input type= "hidden" id="${tempDriver.id}" value='${tempDriver}'>
				<tr>
					<td>${count}</td>
					<td><a href="#" onclick="showDriverDetails('${tempDriver.id}')">${tempDriver.familyName}</a></td>
					<td>${tempDriver.givenName}</td>
					<td><a href="#"
						onclick="showEditModal('${tempDriver.id}')">Edit</a>
					</td>
					<td><a href="#" onclick="deleteDriver('${tempDriver.id}')">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</c:if>
</div>

<!-- Modal: This model displays is the details of driver in a model pop up -->
<div class="modal fade" id="driverDetailsModal" tabindex="-1" role="dialog"
	aria-labelledby="driverDetailsModal" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Driver Details</h4>
			</div>
			<form action="editDriver" id="editDriver" method="post">
				<input type="hidden" name="driverId" id="driverId" value="">
				<div class="modal-body">
					<div class="alert alert-danger errAlert" role="alert"
						id="editDriverAlert"></div>
					<div class="form-group row">
						<label for="editFamilyName" class="col-sm-2 col-form-label">Family Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="editFamilyName"
								name="editFamilyName" placeholder="Family Name">
						</div>
					</div>
					<div class="form-group row">
						<label for="editGivenName" class="col-sm-2 col-form-label">Given Name</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="editGivenName"
								name="editGivenName" placeholder="Given Name">
						</div>
					</div>
					<div class="form-group row">
						<label for="editDc" class="col-sm-2 col-form-label">DC</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="editDc"
								name="editDc" placeholder="DC">
						</div>
					</div>
					<div class="form-group row">
						<label for="editAbn" class="col-sm-2 col-form-label">ABN</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="editAbn"
								name="editAbn" placeholder="ABN">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" id="driverDetailsModal">Close</button>
					<button type="submit" class="btn btn-primary" id="editDriverButton">Save
						changes</button>
				</div>
			</form>
		</div>
	</div>
</div>
