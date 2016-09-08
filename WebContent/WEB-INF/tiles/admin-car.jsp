<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<div class="alert alert-danger errAlert" role="alert" id="errorAlert">
	</div>
	<form action="addCar">
		<div class="form-group row">
			<label for="carMake" class="col-sm-2 col-form-label">Car Make</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="carMake" name="carMake"
					placeholder="Car Make">
			</div>
		</div>
		<div class="form-group row">
			<label for="rego" class="col-sm-2 col-form-label">Rego</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="rego" name="rego"
					placeholder="Rego">
			</div>
		</div>
		<div class="form-group row">
			<div class="offset-sm-2 col-sm-8">
				<button type="submit" id="saveCar" class="btn btn-primary">Save</button>
			</div>
		</div>
	</form>
</div>
<div>
	<table class="table">
		<thead>
			<tr>
				<th>#</th>
				<th>Rego</th>
				<th>Make</th>
				<th>Edit</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="tempCar" items="${carList}">
				<tr>
					<td>${tempCar.id}</td>
					<td>${tempCar.rego}</td>
					<td>${tempCar.make}</td>
					<td><a href=#
						onclick="showEditModal('${tempCar.id}',' ${tempCar.rego}', '${tempCar.make}')">Edit</a>
					</td>
					<td><a href="deleteCar?id=${tempCar.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<!-- Modal: This model displays is the edit form in a pop up -->
<div class="modal fade" id="editCarModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Edit Car Details</h4>
			</div>
			<form action="editCar" id="editCar" method="post">
				<input type="hidden" name="carId" id="carId" value="">
				<div class="modal-body">
					<div class="alert alert-danger errAlert" role="alert"
						id="editCarAlert"></div>
					<div class="form-group row">
						<label for="carMake" class="col-sm-2 col-form-label">Car
							Make</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="editCarMake"
								name="editCarMake" placeholder="Car Make">
						</div>
					</div>
					<div class="form-group row">
						<label for="rego" class="col-sm-2 col-form-label">Rego</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="editRego"
								name="editRego" placeholder="Rego">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal" id="editCarModalClose">Close</button>
					<button type="submit" class="btn btn-primary" id="editCar">Save
						changes</button>
				</div>
			</form>
		</div>
	</div>
</div>



