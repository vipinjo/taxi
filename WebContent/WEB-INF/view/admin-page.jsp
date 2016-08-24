<!DOCTYPE html>
<html lang="en">
<%@include file="header.jsp"%>

<body>
	<%@include file="navigation.jsp"%>
	<div class="container" id="mainContainer">
		<form action="addCar">
			<div class="form-group row">
				<label for="carMake" class="col-sm-2 col-form-label">Car
					Make</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="carMake" name="carMake"
						placeholder="Car Make">
				</div>
			</div>
			<div class="form-group row">
				<label for="rego" class="col-sm-2 col-form-label">Rego</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="rego" name="rego"
						placeholder="Rego">
				</div>
			</div>
			<div class="form-group row">
				<div class="offset-sm-2 col-sm-10">
					<button type="submit" class="btn btn-primary">Save</button>
				</div>
			</div>
		</form>
	</div>
	<%@include file="footer.jsp"%>

</body>
</html>
