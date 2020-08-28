$(document).ready(function() {

	setMinHeightForWrapperContainer();
});

function setMinHeightForWrapperContainer() {
	var min_height = $(window).height();
	$("#wrapper").css("min-height", min_height + "px");
	$("#content-wrapper").css("min-height", min_height + "px");
}

function confirmation(success, action) {

	$('#acceptConfirm').attr("href", success);
	$('#title').html(action + ' Item');
	$('#ask').html('Are you sure you want to ' + action + ' this Item ?');
	$('#confirm').show();
}

function showMessages(){

	if($("#message").html() != ""){
		$(".alert").css("display", "block");
		setTimeout(function(){ $(".alert").css("display", "none"); }, 5000);
	}
}







