<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
    <div class="alert alert-danger errAlert" role="alert" id="errorAlert">
    </div>
    <div class="pageHeading">
        <h3>Income Type</h3>
    </div>
	<form action="addIncomeType">
		<div class="form-group row">
			<label for="incomeName" class="col-sm-2 col-form-label">Income Name</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="incomeName" name="incomeName"
					placeholder="Income Name" maxlength="100">
			</div>
		</div>
		<div class="form-group">
            <label for="incomeDesc" class="col-sm-2 col-form-label">Description</label>
            <div class="col-sm-8">
                <textarea class="form-control" name="incomeDesc" id="incomeDesc" rows="4" maxlength="300"></textarea>
            </div>
        </div>
		<div class="form-group row">
			<div class="offset-sm-2 col-sm-8">
				<button type="submit" id="saveIncomeType" class="btn btn-primary">Save</button>
			</div>
		</div>
	</form>
 </div>	
 <div>
 <table class="table">
  <thead>
    <tr>
      <th>#</th>
      <th>Income Name</th>
      <th>Description</th>
      <th>Edit</th>
      <th>Delete</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach var="tempIncomeType" items="${incomeTypeList}">
    <tr>
      <td>${tempIncomeType.id}</td>
      <td>${tempIncomeType.name}</td>
      <td>${tempIncomeType.description}</td>
      <td>
       <a href=# onclick="showEditModal('${tempIncomeType.id}',' ${tempIncomeType.name}', '${tempIncomeType.description}')">Edit</a> 
      </td>
      <td>
       <a href="deleteIncomeType?id=${tempIncomeType.id}">Delete</a> 
      </td>  
    </tr>
    </c:forEach>
  </tbody>
</table>
</div>

<!-- Modal: This model displays is the edit form in a pop up -->
<div class="modal fade" id="editIncomeTypeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="myModalLabel">Edit Income Name</h4>
      </div>
      <form action="editIncomeType" id="editIncomeType" method="post">
         <input type="hidden" name="incomeTypeId" id="incomeTypeId" value="">
          <div class="modal-body">
                <div class="alert alert-danger errAlert" role="alert" id="editIncomeTypeAlert"></div>
                <div class="form-group row">
	  	      	<label for="editIncomeName" class="col-sm-2 col-form-label">Income Name</label>
	  	      	<div class="col-sm-8">
	  	      		<input type="text" class="form-control" id="editIncomeName" name="editIncomeName"
	  	      			placeholder="Income Name" maxlength="100">
	  	      	</div>
	  	      </div>
	  	      <div class="form-group row">
	  	      	<label for="editIncomeDesc" class="col-sm-2 col-form-label">Description</label>
            	<div class="col-sm-8">
                	<textarea class="form-control" name="editIncomeDesc" id="editIncomeDesc" rows="4" maxlength="300"></textarea>
            	</div>
	  	      </div>
          </div>
          <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal" id="editIncomeTypeModalClose">Close</button>
              <button type="submit" class="btn btn-primary" id="editIncomeType">Save changes</button>
          </div>
      </form>
    </div>
  </div>
</div>
 
 

