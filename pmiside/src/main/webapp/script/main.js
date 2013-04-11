$(document).ready(function(){
	$("div[class='func']").each(function(index){
		var w = $(window).width() * 0.2;
		var gap = 10;
		var wrem = ($(window).width() - (w*3 + gap*2)) * 0.5;
		var hrem = ($(window).height() * 0.2) + (div(index, 3) * (150 + gap));
		
		$(this).css("width", "20%");
		$(this).css("height", "150px");
		$(this).css("border", "none");
		$(this).css("position", "absolute");
		$(this).css("box-shadow", "0 8px 50px rgba(21, 62, 78, 30)");
		$(this).css("left", ((index%3)*(w + gap) + wrem) + "px");
		$(this).css("top", hrem);
		
		$(this).click(function(){
			window.open($(this).find('a').attr('href'));
		});
		
		$(this).mousedown(function(){
			$(this).css("width", "20%");
			$(this).css("left", ((index%3)*(w + gap) + wrem) + "px");
			$(this).css("top", hrem);
			$(this).css("box-shadow", "inset 0 1px 0 rgb(86, 174, 251), 0 0 10px 3px rgba(74, 162, 241, 0.5)");
		});
		
		$(this).resize(function(){
			$(this).css("width", "20%");
			$(this).css("left", ((index%3)*(($(window).width() * 0.2) + gap) + (($(window).width() - (w*3 + gap*2)) * 0.5)) + "px");
			$(this).css("top", ($(window).height() * 0.2) + (div(index, 3) * (150 + gap)));
			$(this).css("box-shadow", "0 8px 50px rgba(21, 62, 78, 30)");
		});
		
		$(this).hover(
			function(){
				$(this).css("width", "20%");
				$(this).css("left", ((index%3)*(($(window).width() * 0.2) + gap) + (($(window).width() - (w*3 + gap*2)) * 0.5)) + "px");
				$(this).css("top", hrem);
				$(this).css("box-shadow", "0 8px 50px rgba(21, 62, 78, 30)");
			},
			function(){
				$(this).css("width", "20%");
				$(this).css("left", ((index%3)*(($(window).width() * 0.2) + gap) + (($(window).width() - (w*3 + gap*2)) * 0.5)) + "px");
				$(this).css("top", hrem);
				$(this).css("box-shadow", "0 8px 50px rgba(21, 62, 78, 30)");
			}
		);
	});
});