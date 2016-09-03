$(document).ready(function(){
	$(document).on("click","button#send-button", function(e){
		e.preventDefault();
		var button=$(this);
		button.prop("disabled",true);
    	button.parent().append('<i class="fa fa-circle-o-notch fa-spin fa-fw"></i>');
		$.post("",
		        {
			email: $('form#contact-form input#inputEmail').val(),
			subject: $('form#contact-form input#inputSubject').val(),
			body: $('form#contact-form textarea#inputBody').val()
		        },
		        function(status){
		            if(status=="success"){
		            	$('form#contact-form').trigger("reset");
		            	throwSuccess("Message Has been Sent!","<p>We will send you an email to confirm that we recieved your message!</p>");
		            	button.prop("disabled",false);
		        		button.parent().children('i').fadeOut();
		            }
		            else if(status=="error"){
		            	throwError('Something is Wrong!!!','<p>Failed to send the Message!</p>');
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