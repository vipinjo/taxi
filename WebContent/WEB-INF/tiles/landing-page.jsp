<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container" style="margin-top: 50px;">
   <input type="hidden" name="thisWeek" id="thisWeek" value='${thisWeek}'>
   <input type="hidden" name="thisMonth" id="thisMonth" value='${thisMonth}'>
   <input type="hidden" name="thisYear" id="thisYear" value='${thisYear}'>
	<div class="row">
		<div class="col-md-12">
			 <div id="chart_yearly_div" class="chart"></div>
			 <div id="chart_monthly_div" class="chart"></div>
			 <div id="chart_weekly_div" class="chart"></div>
		</div>
	</div>

</div>


