/**
 * @author Jerrie
 */

var extimer;
var flag;

$(document).ready(function(){
	
	//用户页弹出
	$("div[class='user']").click(function(){
		destroyTips();
		$(this).css("left", "18%");
		$(this).css("box-shadow", "inset 0 1px 0 rgb(86, 174, 251), 0 0 10px 3px rgba(74, 162, 241, 0.5)");
		$("div[class='project']").css("box-shadow", "0 8px 50px rgba(21, 62, 78, 0.8)");
		$("div[class='project']").css("left", "41%");
		$("div[class='date']").css("box-shadow", "0 8px 50px rgba(21, 62, 78, 0.8)");
		$("div[class='date']").css("left", "41%");
	});

	//项目页弹出
	$("div[class='project']").click(function(){
		destroyTips();
		$(this).css("left", "18%");
		$(this).css("box-shadow", "inset 0 1px 0 rgb(86, 174, 251), 0 0 10px 3px rgba(74, 162, 241, 0.5)");
		$("div[class='user']").css("box-shadow", "0 8px 50px rgba(21, 62, 78, 0.8)");
		$("div[class='user']").css("left", "41%");
		$("div[class='date']").css("box-shadow", "0 8px 50px rgba(21, 62, 78, 0.8)");
		$("div[class='date']").css("left", "41%%");
	});

	//日期页弹出
	$("div[class='date']").click(function(){
		destroyTips();
		$(this).css("left", "18%");
		$(this).css("box-shadow", "inset 0 1px 0 rgb(86, 174, 251), 0 0 10px 3px rgba(74, 162, 241, 0.5)");
		$("div[class='project']").css("box-shadow", "0 8px 50px rgba(21, 62, 78, 0.8)");
		$("div[class='project']").css("left", "41%%");
		$("div[class='user']").css("box-shadow", "0 8px 50px rgba(21, 62, 78, 0.8)");
		$("div[class='user']").css("left", "41%%");
	});
	
	
	//PMIS用户标识输入框已填值时隐藏填值提示
	if($('input[name="message.pmisUserid"]').val() != '') {
		$('span[class="user-icon"]').css('visibility', 'hidden');
	}
	//PMIS用户标识输入框获得焦点时隐藏填值提示
	$('input[name="message.pmisUserid"]').focus(function(){
		$('span[class="user-icon"]').css('visibility', 'hidden');
	});
	//PMIS用户标识输入框失去焦点时判断输入是否为空，如果为空则显示填值提示
	$('input[name="message.pmisUserid"]').blur(function(){
		if($(this).val() == '') {
			$('span[class="user-icon"]').css('visibility', 'visible');
		}
	});
	
	
	//PMIS密码输入框已填值时隐藏填值提示
	if($('input[name="message.pmisPassword"]').val() != '') {
		$('span[class="password-icon"]').css('visibility', 'hidden');
	}
	//PMIS密码输入框获得焦点时隐藏填值提示
	$('input[name="message.pmisPassword"]').focus(function(){
		$('span[class="password-icon"]').css('visibility', 'hidden');
	});
	//PMIS密码输入框失去焦点时判断输入是否为空，如果为空则显示填值提示
	$('input[name="message.pmisPassword"]').blur(function(){
		if($(this).val() == '') {
			$('span[class="password-icon"]').css('visibility', 'visible');
		}
	});
	
	
	//PMIS用户姓名输入框已填值时隐藏填值提示
	if($('input[name="message.pmisName"]')[0].value != '') {
		$('span[class="name-icon"]').css('visibility', 'hidden');
	}
	//PMIS用户姓名输入框获得焦点时隐藏填值提示
	$('input[name="message.pmisName"]').focus(function(){
		$('span[class="name-icon"]').css('visibility', 'hidden');
	});
	//PMIS用户姓名输入框失去焦点时判断输入是否为空，如果为空则显示填值提示
	$('input[name="message.pmisName"]').blur(function(){
		if($(this)[0].value == '') {
			$('span[class="name-icon"]').css('visibility', 'visible');
		}
	});
	
	
	//办公地点输入框已填值时隐藏填值提示
	if($('input[name="message.pmisOfficePlace"]')[0].value != '') {
		$('span[class="place-icon"]').css('visibility', 'hidden');
	}
	//办公地点输入框获得焦点时隐藏填值提示
	$('input[name="message.pmisOfficePlace"]').focus(function(){
		$('span[class="place-icon"]').css('visibility', 'hidden');
	});
	//办公地点输入框失去焦点时判断输入是否为空，如果为空则显示填值提示
	$('input[name="message.pmisOfficePlace"]').blur(function(){
		if($(this)[0].value == '') {
			$('span[class="place-icon"]').css('visibility', 'visible');
		}
	});
	
		
	//PMIS项目名称输入框已填值时隐藏填值提示
	if($('input[name="message.pmisProjectName"]')[0].value != '') {
		$('span[class="projectname-icon"]').css('visibility', 'hidden');
	}
	//PMIS项目名称输入框获得焦点时隐藏填值提示
	$('input[name="message.pmisProjectName"]').focus(function(){
		$('span[class="projectname-icon"]').css('visibility', 'hidden');
	});
	//PMIS项目名称输入框失去焦点时判断输入是否为空，如果为空则显示填值提示
	$('input[name="message.pmisProjectName"]').blur(function(){
		if($(this)[0].value == '') {
			$('span[class="projectname-icon"]').css('visibility', 'visible');
		}
	});
	
	//供应商输入框已填值时隐藏填值提示
	if($('input[name="message.supplier"]')[0].value != '') {
		$('span[class="supplier-icon"]').css('visibility', 'hidden');
	}
	//供应商输入框获得焦点时隐藏填值提示
	$('input[name="message.supplier"]').focus(function(){
		$('span[class="supplier-icon"]').css('visibility', 'hidden');
	});
	//供应商输入框失去焦点时判断输入是否为空，如果为空则显示填值提示
	$('input[name="message.supplier"]').blur(function(){
		if($(this)[0].value == '') {
			$('span[class="supplier-icon"]').css('visibility', 'visible');
		}
	});
		
	
	//PMIS项目角色输入框已填值时隐藏填值提示
	if($('input[name="message.pmisProjectRole"]')[0].value != '') {
		$('span[class="projectrole-icon"]').css('visibility', 'hidden');
	}
	//PMIS项目角色输入框获得焦点时隐藏填值提示
	$('input[name="message.pmisProjectRole"]').focus(function(){
		$('span[class="projectrole-icon"]').css('visibility', 'hidden');
	});
	//PMIS项目角色输入框失去焦点时判断输入是否为空，如果为空则显示填值提示
	$('input[name="message.pmisProjectRole"]').blur(function(){
		if($(this)[0].value == '') {
			$('span[class="projectrole-icon"]').css('visibility', 'visible');
		}
	});
	
	var now = new Date();
	$('input[name="exportDate"]')[0].value = now.getFullYear()+ '/' + (now.getMonth() + 1);
	
	
	$("#export").click(function(){
		//重置标记
		flag = false;
		
		//隐藏下载
		$('#download').css('visibility', "hidden");
		
		//清除TIPs
		destroyTips();
		
		//清除进度轮询
		clearInterval(extimer);
		
		//输入页放入打印机
		$("div[class='project']").css("box-shadow", "0 8px 50px rgba(21, 62, 78, 0.8)");
		$("div[class='project']").css("left", "41%");
		$("div[class='user']").css("box-shadow", "0 8px 50px rgba(21, 62, 78, 0.8)");
		$("div[class='user']").css("left", "41%");
		$("div[class='date']").css("box-shadow", "0 8px 50px rgba(21, 62, 78, 0.8)");
		$("div[class='date']").css("left", "41%");
		
		//判空
		if($('#pmisuserid').val() == '') {
			$("div[class='user']").css("left", "18%");
			$("div[class='user']").css("box-shadow", "inset 0 1px 0 rgb(86, 174, 251), 0 0 10px 3px rgba(74, 162, 241, 0.5)");
			$('#pmisuserid').poshytip({
				content: $("#errorpmisuserid").text(),
				showOn: 'none',
				alignTo: 'target',
				alignX: 'inner-left',
				offsetX: 10,
				offsetY: 5
			});
			$('#pmisuserid').poshytip('show'); 
			return;
		}
		if($('#pmispassword').val() == '') {
			$("div[class='user']").css("left", "18%");
			$("div[class='user']").css("box-shadow", "inset 0 1px 0 rgb(86, 174, 251), 0 0 10px 3px rgba(74, 162, 241, 0.5)");
			$('#pmispassword').poshytip({
				content: $("#errorpmispassword").text(),
				showOn: 'none',
				alignTo: 'target',
				alignX: 'inner-left',
				offsetX: 10,
				offsetY: 5
			});
			$('#pmispassword').poshytip('show'); 
			return;
		}
		
		var datevalue = $('#exportdate').val(); 
		if(datevalue == '' || !isValidateDate(datevalue)) {
			$("div[class='date']").css("left", "18%");
			$("div[class='date']").css("box-shadow", "inset 0 1px 0 rgb(86, 174, 251), 0 0 10px 3px rgba(74, 162, 241, 0.5)");
			
			$('#exportdate').poshytip({
				content: $("#errorpmisexportdate").text(),
				showOn: 'none',
				alignTo: 'target',
				alignX: 'inner-left',
				offsetX: 10,
				offsetY: 5,
			});
			$('#exportdate').poshytip('show'); 
			return;
		};		
		var filledDate = new Date(datevalue + "/1");
		$('input[name="message.year"]')[0].value = filledDate.getFullYear();
		$('input[name="message.month"]')[0].value = filledDate.getMonth() + 1;
		
		var postdata = {
			      'message.pmisUserid':$('input[name="message.pmisUserid"]').val(),
				  'message.pmisPassword':$('input[name="message.pmisPassword"]').val(),
				  'message.pmisName':$('input[name="message.pmisName"]').val(),
				  'message.pmisOfficePlace':$('input[name="message.pmisOfficePlace"]').val(),
				  'message.pmisProjectName':$('input[name="message.pmisProjectName"]').val(),
				  'message.pmisProjectRole':$('input[name="message.pmisProjectRole"]').val(),
				  'message.month':$('input[name="message.month"]').val(),
				  'message.year':$('input[name="message.year"]').val(),
				  'message.supplier':$('input[name="message.supplier"]').val(),
		};
		
		if($('input[name="needsave"]').attr('checked')){
			postdata = {
				      'message.pmisUserid':$('input[name="message.pmisUserid"]').val(),
					  'message.pmisPassword':$('input[name="message.pmisPassword"]').val(),
					  'message.pmisName':$('input[name="message.pmisName"]').val(),
					  'message.pmisOfficePlace':$('input[name="message.pmisOfficePlace"]').val(),
					  'message.pmisProjectName':$('input[name="message.pmisProjectName"]').val(),
					  'message.pmisProjectRole':$('input[name="message.pmisProjectRole"]').val(),
					  'message.month':$('input[name="message.month"]').val(),
					  'message.year':$('input[name="message.year"]').val(),
					  'message.supplier':$('input[name="message.supplier"]').val(),
					  'needsave':$('input[name="needsave"]').val(),
			};
		} 
		$.post("mworkexporter", postdata, function(data, status){
			if(status == 'success'){
				$('#pb1').css("visibility", "visible");
				$("#pb1").progressBar(0);
				extimer = setInterval('reqProgress()',50);
			} else {
				$('#controller').poshytip({
					content: $("#fatalerrorconactauthor").text(),
					showOn: 'none',
					alignTo: 'target',
					alignX: 'inner-left',
					offsetX: 10,
					offsetY: 5,
				});
				$('#controller').poshytip('show'); 
			}
		});
	});
	
	$('#logout').mouseenter(function(){
		$(this).attr('src', 'images/logoutfire.png');
		$(this).poshytip({
			content: $("#logouttips").text(),
			showOn: 'none',
			alignTo: 'target',
			alignX: 'inner-left',
			offsetX: 18,
			offsetY: -20
		});
		$(this).poshytip('show');
	});
	
	$('#logout').mouseleave(function(){
		$(this).attr('src', 'images/logoutpre.png');
		$(this).poshytip('destroy');
	});
	
	$('#logout').click(function(){
		$.get('logout', function(){
			//重置标记
			flag = false;
			//清除进度轮询
			clearInterval(extimer);
			//隐藏下载
			$('#download').css('visibility', "hidden");
			//清除TIPs
			destroyTips();
			window.close();
		});
	});
	
	$('#settingsicon').click(function(){
		$('#option').css({
			'visibility':'visible',
			'position':'absolute',
			'width':'200px',
			'height':'100px',
			'box-shadow':'inset 0 1px 0 rgb(86, 174, 251), 0 0 10px 3px rgba(74, 162, 241, 0.5)',
			'top':'100px',
			'right':'30px'
		});
	});
	
});

function destroyTips(){
	//清除tips
	$('#pmisuserid').poshytip('destroy');
	$('#pmispassword').poshytip('destroy'); 
	$('#exportdate').poshytip('destroy');
	$('#pb1').poshytip('destroy'); 
	$('#logout').poshytip('destroy'); 
}

function reqProgress(){
	$.get('getprogress', function(data, status){
		if(status == 'success'){
			var jobj = $.parseJSON(data);
			if(jobj.result == 'success') {
				$("#pb1").progressBar(jobj.progress);
				if(!flag && jobj.progress == 100) {
					flag = true;
					clearInterval(extimer);
					$('#download').click(function(){
						window.open(jobj.filename);
					});
					$('#download').css('visibility', "visible");
					return;
				}
			} else if(jobj.result == 'error') {
				clearInterval(extimer);
				$('#pb1').poshytip({
					content: jobj.msg,
					showOn: 'none',
					alignTo: 'target',
					alignX: 'inner-left',
					offsetX: 2,
					offsetY: 1,
				});
				$('#pb1').poshytip('show'); 
			}  else {
				clearInterval(extimer);
			}
		} else {
			clearInterval(extimer);
			$('#pb1').poshytip({
				content: $('#error').text(),
				showOn: 'none',
				alignTo: 'target',
				alignX: 'inner-left',
				offsetX: 2,
				offsetY: 1,
			});
			$('#pb1').poshytip('show');
		}
	});
}
