<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
    <div class="alert alert-danger" role="alert" id="errorAlert">
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
      <td>
       <a href=#>Edit</a> 
      </td>
      <td>
       <a href="deleteCar?id=${tempCar.id}">Delete</a> 
      </td>  
    </tr>
    </c:forEach>
  </tbody>
</table>
 </div>

