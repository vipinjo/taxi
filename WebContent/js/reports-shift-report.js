jQuery(function() {
	jQuery('#startDate').datetimepicker(
			{
				format : 'Y/m/d',
				onShow : function(ct) {
					this.setOptions({
						maxDate : jQuery('#endDate').val() ? jQuery('#endDate')
								.val() : false
					})
				},
				timepicker : false,
			});
	jQuery('#endDate').datetimepicker(
			{
				format : 'Y/m/d',
				onShow : function(ct) {
					this
							.setOptions({
								minDate : jQuery('#startDate')
										.val() ? jQuery(
										'#startDate').val() : false
							})
							
				},
				timepicker : false,
			});
});





