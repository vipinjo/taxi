package com.vipinstraders.taxi.mvc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vipinstraders.taxi.domain.ExpenseType;
import com.vipinstraders.taxi.domain.Transaction;
import com.vipinstraders.taxi.object.criteria.TransactionSearchCriteria;
import com.vipinstraders.taxi.service.admin.ExpenseTypeService;
import com.vipinstraders.taxi.service.transaction.ExpenseService;

@Controller
public class TransactionController {
	
	private ExpenseService expenseService;
	private ExpenseTypeService expenseTypeService;
	
	@Autowired
	public TransactionController(ExpenseService expenseService, ExpenseTypeService expenseTypeService) {
		this.expenseService = expenseService;
		this.expenseTypeService = expenseTypeService;
	}
	
	@RequestMapping("/transaction")
	public String showTransactionTab(Model model) {
		
		List<ExpenseType> expenseTypeList = expenseTypeService.getAll();
		List<Transaction> transactionFromFirstDayOfThisMonth = getTransactionFromFirstDayOfThisMonth();
		model.addAttribute("expenseTypeList", expenseTypeList);
		model.addAttribute("transactionList", transactionFromFirstDayOfThisMonth) ;
		model.addAttribute("displayText", getDisplayTextAfterAddingExpense(transactionFromFirstDayOfThisMonth));
		
		return "expenseReport";
	}
	
	@RequestMapping("/showAddExpensePage")
	public String showAddExpensePage(Model model) {
		List<ExpenseType> expenseTypeList = expenseTypeService.getAll();
		model.addAttribute("expenseTypeList", expenseTypeList);
		model.addAttribute("action", "saveNewExpense");
		
		return "addExpense";
	}
	
	@RequestMapping("/saveNewExpense")
	public String saveExpense(HttpServletRequest request, Model model) {
		
		Transaction expense = getExpense(request);
		expenseService.add(expense);
		List<ExpenseType> expenseTypeList = expenseTypeService.getAll();
		List<Transaction> transactionFromFirstDayOfThisMonth = getTransactionFromFirstDayOfThisMonth();
		model.addAttribute("transactionList", transactionFromFirstDayOfThisMonth) ;
		model.addAttribute("expenseTypeList", expenseTypeList);
		model.addAttribute("displayText", getDisplayTextAfterAddingExpense(transactionFromFirstDayOfThisMonth));
		
		return "expenseReport";
	}
	
	@RequestMapping("/searchExpenses")
	public String searchExpense(HttpServletRequest request, Model model) {
		TransactionSearchCriteria searchCriteria = getSearchCriteria(request);
		
		List<ExpenseType> expenseTypeList = expenseTypeService.getAll();
		List<Transaction> allExpenses = expenseService.getAllExpenses(searchCriteria);
		model.addAttribute("transactionList", allExpenses) ;
		model.addAttribute("expenseTypeList", expenseTypeList);
		model.addAttribute("displayText", getDisplayTextBasedOnTransactionList(searchCriteria, allExpenses));
		model.addAttribute("searchCriteria", searchCriteria);
		return "expenseReport";
	}
	
	@RequestMapping("/editExpense")
	public String updateExpense(HttpServletRequest request, Model model) {
		Transaction expense = getExpense(request);
		expenseService.update(expense);
		List<ExpenseType> expenseTypeList = expenseTypeService.getAll();
		TransactionSearchCriteria searchCriteria = getSearchCriteriaFromUpdateRequest(request);
		List<Transaction> transactionList = null;
		String displayText = "";
		if (!isRequestFromSearchPage(searchCriteria)) {
			transactionList = getTransactionFromFirstDayOfThisMonth();
			displayText = getDisplayTextAfterAddingExpense(transactionList);
		} else {
			transactionList = expenseService.getAllExpenses(searchCriteria);
			getDisplayTextBasedOnTransactionList(searchCriteria, transactionList); 
		}
		model.addAttribute("transactionList", transactionList) ;
		model.addAttribute("expenseTypeList", expenseTypeList);
		model.addAttribute("displayText", displayText);
		model.addAttribute("searchCriteria", searchCriteria);
		
		return "expenseReport";
	}
	
	@RequestMapping("/deleteExpense")
	public String deleteExpense(HttpServletRequest request, Model model) {
		
		if (hasValue(request, "id"))  {
		    expenseService.delete(Integer.parseInt(request.getParameter("id")));
		    
		}
		
		List<ExpenseType> expenseTypeList = expenseTypeService.getAll();
		TransactionSearchCriteria searchCriteria = getSearchCriteriaFromUpdateRequest(request);
		List<Transaction> transactionList = null;
		String displayText = "";
		if (!isRequestFromSearchPage(searchCriteria)) {
			transactionList = getTransactionFromFirstDayOfThisMonth();
			displayText = getDisplayTextAfterAddingExpense(transactionList);
		} else {
			transactionList = expenseService.getAllExpenses(searchCriteria);
			getDisplayTextBasedOnTransactionList(searchCriteria, transactionList); 
		}
		model.addAttribute("transactionList", transactionList) ;
		model.addAttribute("expenseTypeList", expenseTypeList);
		model.addAttribute("displayText", displayText);
		model.addAttribute("searchCriteria", searchCriteria);
		
		return "expenseReport";
	}
	
	
	private Transaction getExpense(HttpServletRequest request) {
		Transaction expense = new Transaction();
		
		if (hasValue(request, "id")) {
			expense.setId(Integer.parseInt(request.getParameter("id")));
		}
		
 		if (hasValue(request, "date")) {
 			expense.setDate(getDate(request.getParameter("date")));
 		}
		
		if (hasValue(request, "type")) {
			ExpenseType expenseType = new ExpenseType();
			expenseType.setId(Integer.parseInt(request.getParameter("type")));
			expense.setExpenseType(expenseType);
		}
		
		if (hasValue(request, "amount")) {
			expense.setExpenseAmount(Double.parseDouble(request.getParameter("amount")));
			
		}
		
		if (hasValue(request, "meterReading")) {
			expense.setMeterReading(Integer.parseInt(request.getParameter("meterReading")));
		}
		
		if (hasValue(request, "description")) {
			expense.setDescription(request.getParameter("description"));
		}
		
		return expense;
	}
	
	private boolean hasValue(HttpServletRequest request, String parameter) {
		if (parameter != null && request.getParameter(parameter) != null && !request.getParameter(parameter).isEmpty())
			return true;
		return false;
	}
	
	private Date getDate(String dateStr) {
		Date date = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		try {
			date = dateFormat.parse(dateStr);
			
		} catch (ParseException e) {
			e.printStackTrace();
			date = new Date();
		}
		
		return date;
	}
	
	private List<Transaction> getTransactionFromFirstDayOfThisMonth() {
		TransactionSearchCriteria searchCriteria = new TransactionSearchCriteria(getFirstDayOfThisMonth(), null);
		return expenseService.getAllExpenses(searchCriteria);
	}
	
	private Date getFirstDayOfThisMonth() {
		Calendar c = Calendar.getInstance(); // this takes current date
		c.set(Calendar.DAY_OF_MONTH, 1);
		return c.getTime();
	}
	
	private String getDisplayTextBasedOnTransactionList(TransactionSearchCriteria criteria,
			List<Transaction> transactionList) {
		StringBuilder resultText = new StringBuilder("");

		if (transactionList != null && transactionList.isEmpty()) {
			resultText.append("No results found ");
		} else if (transactionList != null && !transactionList.isEmpty()) {
			resultText.append("Search results ");
		}
		if (criteria.getStartDate() != null && criteria.getEndDate() == null) {
			resultText.append("from ").append(formatDate(criteria.getStartDate(), "EEE, MMM d, yyyy")).append(" ");
		} else if (criteria.getStartDate() != null && criteria.getEndDate() != null) {
			resultText.append("from ").append(formatDate(criteria.getStartDate(), "EEE, MMM d, yyyy")).append(" to ")
					.append(formatDate(criteria.getEndDate(), "EEE, MMM d, yyyy"));
		}

		return resultText.toString();
	}
	
	private String getDisplayTextAfterAddingExpense(List<Transaction> transactionList) {
		StringBuilder resultText = new StringBuilder("");
		if (transactionList != null && !transactionList.isEmpty()) {
			resultText.append("Expenses from ");
		} else {
			resultText.append("No expenses from ");
		}
		
		resultText.append(formatDate(getFirstDayOfThisMonth(), "EEE, MMM d, yyyy")).append(" ");
		
		return resultText.toString();
	}
	
	private String formatDate(Date date, String format) {
		if (date == null)
			return "";
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}
	
	private TransactionSearchCriteria getSearchCriteria(HttpServletRequest request) {
		TransactionSearchCriteria criteria = new TransactionSearchCriteria();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		if (hasValue(request, "startDate")) {
			try {
				criteria.setStartDate(dateFormat.parse(request.getParameter("startDate")));
			} catch (ParseException e) {
				e.printStackTrace();
				criteria.setStartDate(new Date());
			}
		}
		
		if (hasValue(request, "endDate")) {
			try {
				criteria.setEndDate(dateFormat.parse(request.getParameter("endDate")));
			} catch (ParseException e) {
				e.printStackTrace();
				criteria.setEndDate(new Date());
			}
		}
		return criteria;
	}
	
	private TransactionSearchCriteria getSearchCriteriaFromUpdateRequest(HttpServletRequest request) {
		TransactionSearchCriteria criteria = new TransactionSearchCriteria();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		if (hasValue(request, "searchCriteriaStartDate")) {
			try {
				criteria.setStartDate(dateFormat.parse(request.getParameter("searchCriteriaStartDate")));
			} catch (ParseException e) {
				e.printStackTrace();
				criteria.setStartDate(new Date());
			}
		}
		
		if (hasValue(request, "searchCriteriaEndDate")) {
			try {
				criteria.setEndDate(dateFormat.parse(request.getParameter("searchCriteriaEndDate")));
			} catch (ParseException e) {
				e.printStackTrace();
				criteria.setEndDate(new Date());
			}
		}
		return criteria;
	}
	
	private boolean isRequestFromSearchPage(TransactionSearchCriteria searchCriteria) {
		return searchCriteria != null && searchCriteria.getStartDate() == null && searchCriteria.getEndDate() == null;
	}

}
