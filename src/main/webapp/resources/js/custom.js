$(function() {
	$('#colorpicker-showColor').click(function() {
		$('#colorpicker').toggle('slide', {
			direction: 'left'
		});
	});
	$('#colorpicker').each(function() {
		$(this).farbtastic(function(color) {
			$('input[name=color]').attr('value', color);
			$('#colorpicker-showColor').attr('style', 'background-color:' + color);
		});
	});

	$('form').submit(function() {
		if (isValid()) {
			var submitButton = $('input[name=submit]');
			submitButton.attr('value', 'Loading...');
			$('#ajax-spinner').appendTo("body").css({
				position: "fixed",
				left: "40%",
				top: "50%"
			}).show();
			submitButton.attr("disabled", "true");
			return true;
		} else {
			return false;
		}
	});
});

function isValid() {
	if ($('input[name=term]').attr('value') == "") {
		alert('Field keyword must be filled in.');
		return false;
	}
	if (!(($('input[name=numberOfResults]').attr('value') > 0) && ($('input[name=numberOfResults]').attr('value') <= 1500))) {
		alert('Field number of items must contains value between 1 and 1500.');
		return false;
	}
	return true;
}

$(function() {
	$('.results img').load(function() {
		$(this).fadeTo(2000, 1);
	});
	$('.results a').lightBox()
});
