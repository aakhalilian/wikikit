$(document).ready(function(){
	$(document).on("click","button#submit-button", function(e){
		e.preventDefault();
		var button=$(this);
		button.prop("disabled",true);
    	button.parent().append('<i class="fa fa-circle-o-notch fa-spin fa-fw"></i>');
		$.post("",
		        {
			email: $('form#login-form input#inputEmail').val(),
			password: $('form#login-form input#inputPassword').val()
		        },
		        function(status){
		            if(status=="success"){
		            	window.location.replace("main");
		            }
		            else if(status=="error"){
		            	throwError('Something is Wrong!!!',"<p>Sorry we can't let you in!</p>");
		            	button.prop("disabled",false);
		        		button.parent().children('i').fadeOut();
		            }
		            else
		            	console.log(status);
		});
	});
})
function throwError(title,messag){
	var n={
		type	: "danger",
		title 	: title,
		message : messag,
	};
	notification(n);	
}
function throwSuccess(title,messag){
	var n={
		type	: "success",
		title 	: title,
		message : messag,
	};
	notification(n);	
}