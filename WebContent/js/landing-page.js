// Load the Visualization API and the corechart package.
google.charts.load('current', {
	'packages' : [ 'corechart' ]
});

// Set a callback to run when the Google Visualization API is loaded.
google.charts.setOnLoadCallback(drawChart);

// Callback that creates and populates a data table,
// instantiates the pie chart, passes in the data and
// draws it.
function drawChart() {
	drawYearlyChart();
	drawMonthlyChart();
	drawWeeklyChart();
}

function drawYearlyChart() {
	// Create the data table.
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Income Statement');
	data.addColumn('number', 'Amount');
	var obj = getYearlyData();
	data.addRows([ [ 'Driver Fees', obj.driverFees ], [ 'Fuel Coast', obj.fuelCost ], [ 'Maintenance', obj.maintanenceCost ],
			[ 'Profit', obj.totalEarnings ] ]);

	// Set chart options
	var options = {
		'title' : 'How well I am doing This Year',
		'width' : 400,
		'height' : 300
	};

	// Instantiate and draw our chart, passing in some options.
	var chart = new google.visualization.PieChart(document
			.getElementById('chart_yearly_div'));
	chart.draw(data, options);
}

function drawMonthlyChart() {
	// Create the data table.
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Income Statement');
	data.addColumn('number', 'Amount');
	var obj = getMonthlyData();
	data.addRows([ [ 'Driver Fees', obj.driverFees ], [ 'Fuel Coast', obj.fuelCost ], [ 'Maintenance', obj.maintanenceCost ],
			[ 'Profit', obj.totalEarnings ] ]);

	// Set chart options
	var options = {
		'title' : 'How well I am doing This Month',
		'width' : 400,
		'height' : 300
	};

	// Instantiate and draw our chart, passing in some options.
	var chart = new google.visualization.PieChart(document
			.getElementById('chart_monthly_div'));
	chart.draw(data, options);
}

function drawWeeklyChart() {
	// Create the data table.
	var data = new google.visualization.DataTable();
	data.addColumn('string', 'Income Statement');
	data.addColumn('number', 'Amount');
	var obj = getWeeklyData();
	data.addRows([ [ 'Driver Fees', obj.driverFees ], [ 'Fuel Coast', obj.fuelCost ], [ 'Maintenance', obj.maintanenceCost ],
			[ 'Profit', obj.totalEarnings ] ]);

	// Set chart options
	var options = {
		'title' : 'How well I am doing This Week',
		'width' : 400,
		'height' : 300
	};

	// Instantiate and draw our chart, passing in some options.
	var chart = new google.visualization.PieChart(document
			.getElementById('chart_weekly_div'));
	chart.draw(data, options);
}

function getYearlyData() {
	var data = $("#thisYear").val()
	var obj = JSON.parse(data);
	return obj;
} 

function getMonthlyData() {
	var data = $("#thisMonth").val()
	var obj = JSON.parse(data);
	return obj;
}

function getWeeklyData() {
	var data = $("#thisWeek").val()
	var obj = JSON.parse(data);
	return obj;
}