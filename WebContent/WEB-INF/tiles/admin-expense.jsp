<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
    <div class="alert alert-danger errAlert" role="alert" id="errorAlert">
    </div>
    <div class="pageHeading">
        <h3>Expense Type</h3>
    </div>
	<form action="addExpenseType">
		<div class="form-group row">
			<label for="expenseName" class="col-sm-2 col-form-label">Expense Name</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="expenseName" name="expenseName"
					placeholder="Expense Name" maxlength="100">
			</div>
		</div>
		<div class="form-group row">
            <label for="expenseDesc" class="col-sm-2 col-form-label">Description</label>
            <div class="col-sm-8">
                <textarea class="form-control" name="expenseDesc" id="expenseDesc" rows="4" maxlength="300"></textarea>
            </div>
        </div>
		<div class="form-group row">
			<div class="offset-sm-2 col-sm-8">
				<button type="submit" id="saveExpenseType" class="btn btn-primary">Save</button>
			</div>
		</div>
	</form>
 </div>	
 <div>
 <table class="table">
  <thead>
    <tr>
      <th>#</th>
      <th>Expense Name</th>
      <th>Description</th>
      <th>Edit</th>
      <th>Delete</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach var="tempExpenseType" items="${expenseTypeList}">
    <tr>
      <td>${tempExpenseType.id}</td>
      <td>${tempExpenseType.name}</td>
      <td>${tempExpenseType.description}</td>
      <td>
       <a href=# onclick="showEditModal('${tempExpenseType.id}',' ${tempExpenseType.name}', '${tempExpenseType.description}')">Edit</a> 
      </td>
      <td>
       <a href="deleteExpenseType?id=${tempExpenseType.id}">Delete</a> 
      </td>  
    </tr>
    </c:forEach>
  </tbody>
</table>
</div>

<!-- Modal: This model displays is the edit form in a pop up -->
<div class="modal fade" id="editExpenseTypeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h4 class="modal-title" id="myModalLabel">Edit Expense Name</h4>
      </div>
      <form action="editExpenseType" id="editExpenseType" method="post">
         <input type="hidden" name="expenseTypeId" id="expenseTypeId" value="">
          <div class="modal-body">
                <div class="alert alert-danger errAlert" role="alert" id="editExpenseTypeAlert"></div>
                <div class="form-group row">
	  	      	<label for="editExpenseName" class="col-sm-2 col-form-label">Expense Name</label>
	  	      	<div class="col-sm-8">
	  	      		<input type="text" class="form-control" id="editExpenseName" name="editExpenseName"
	  	      			placeholder="Expense Name" maxlength="100">
	  	      	</div>
	  	      </div>
	  	      <div class="form-group row">
	  	      	<label for="editExpenseDesc" class="col-sm-2 col-form-label">Description</label>
            	<div class="col-sm-8">
                	<textarea class="form-control" name="editExpenseDesc" id="editExpenseDesc" rows="4" maxlength="300"></textarea>
            	</div>
	  	      </div>
          </div>
          <div class="modal-footer">
              <button type="button" class="btn btn-secondary" data-dismiss="modal" id="editExpenseTypeModalClose">Close</button>
              <button type="submit" class="btn btn-primary" id="editExpenseType">Save changes</button>
          </div>
      </form>
    </div>
  </div>
</div>
 
 

