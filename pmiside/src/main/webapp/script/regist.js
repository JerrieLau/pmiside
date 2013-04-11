$(document).ready(function(){
	if($('#actionerror').text() != '') {
		$('#registform').poshytip({
			content: $('#actionerror').text(),
			showOn: 'none',
			alignTo: 'target',
			alignX: 'inner-left',
			offsetX: 10,
			offsetY: 5
		});
		$('#registform').poshytip('show');
	}
	
	$('#regist').click(function(){
		//清除tips
		$('#registform').poshytip('hide');
		$("#userid").poshytip('hide');
		$("#password").poshytip('hide');
		$("#passwordconfirm").poshytip('hide');
		$("#passwordconfirm").poshytip('hide');
		
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
		
		var passwordconfirm = $("#passwordconfirm").val();
		if (passwordconfirm == '') {
			$("#passwordconfirm")[0].focus();
			$("#passwordconfirm").poshytip({
				content:$("#errorpasswordconfirm").text(),
				showOn: 'none',
				alignTo: 'target',
				alignX: 'inner-left',
				offsetX: 90,
				offsetY: 5
			});
			$("#passwordconfirm").poshytip('show');
			return;
		}
		
		if(password != passwordconfirm) {
			$("#passwordconfirm")[0].focus();
			$("#passwordconfirm").poshytip({
				content:$("#errorpasswordconfirmdifferent").text(),
				showOn: 'none',
				alignTo: 'target',
				alignX: 'inner-left',
				offsetX: 90,
				offsetY: 5
			});
			$("#passwordconfirm").poshytip('show');
			return;
		}
		
		$("form[class='regist-form']")[0].submit();
	});
});