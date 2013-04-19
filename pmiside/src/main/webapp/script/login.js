$(document).ready(function(){
	if($('#actionerror').text() != '') {
		$('#loginform').poshytip({
			content: $('#actionerror').text(),
			showOn: 'none',
			alignTo: 'target',
			alignX: 'inner-left',
			offsetX: 10,
			offsetY: 5
		});
		$('#loginform').poshytip('show');
	}
	
	$('#login').click(function(){
		//清除tips
		$("#userid").poshytip('hide');
		$("#password").poshytip('hide');
		$('#loginform').poshytip('hide');
		
		var userid = $("#userid").val();
		if (userid == '') {
			$("#userid")[0].focus();
			$("#userid").poshytip({
				content:$("#errorusername").text(),
				showOn: 'none',
				alignTo: 'target',
				alignX: 'inner-left',
				offsetX: 90,
				offsetY: 5
			});
			$("#userid").poshytip('show');
			return;
		}

		var password = $("#password").val();
		if (password == '') {
			$("#password")[0].focus();
			$("#password").poshytip({
				content:$("#errorpassword").text(),
				showOn: 'none',
				alignTo: 'target',
				alignX: 'inner-left',
				offsetX: 90,
				offsetY: 5
			});
			$("#password").poshytip('show');
			return;
		}
		$("#password").val($.md5(password));
		$("form[class='login-form']")[0].submit();
	});
});