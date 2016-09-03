var boxLength=5;
var iconSet={
	default	: "bars",
	primary	: "envelope",
	success	: "check-circle",
	info	: "info-circle",
	warning	: "exclamation-triangle",
	danger	: "exclamation-circle"
};
$(document).ready(function(){
	$(document).on("click","div.mui-notifications button.close",function(){
		var notify=$(this).parents("div.panel");
		notificationClose(notify);
	})
})

function notification(notify){
	notify=notificationVerify(notify);
	var notification=notificationBuild(notify);
	var box=notification.parents("div.mui-notifications");
	if(box.children("div.panel").length>boxLength){
		for (var i =0; i<box.children("div.panel").length - boxLength; i++) {
			notificationClose(box.children("div.panel:eq("+i+")"));
		}	
	}
	notification.removeClass("hide");
	notificationPopUp(notification);
	if (notify.hasOwnProperty("timeOut"))
		setTimeout(function(){
			notificationFade(notification);
		}, notify.timeOut);
}

function notificationVerify(notify){
	if (notify.hasOwnProperty("timeOut")){
		if($.isNumeric(notify.timeOut)){
			if(notify.timeOut<1500)
				notify.timeOut=1500;	
		}else notify.timeOut=10000;
	}
	if (!notify.hasOwnProperty("type"))
		notify.type="default";
	else{
		switch (notify.type){
			case "primary":
			notify.i=iconSet.primary;
			break;
			case "success":
			notify.i=iconSet.success;
			break;
			case "info":
			notify.i=iconSet.info;
			break;
			case "warning":
			notify.i=iconSet.warning;
			break;
			case "danger":
			notify.i=iconSet.danger;
			break;
			default:
			notify.type="default";
			notify.i=iconSet.default;
			break;
		}
	}
	if(notify.hasOwnProperty("icon")){
		if(notify.icon!=false)
			notify.icon=true;
	}else
		notify.icon=true;
	return notify;
}

function notificationBuild(notify){
	if(!$("div.mui-notifications").length){
		var box='<div class="mui-notifications"></div>';
		$("body").prepend(box);
	}
	var notification='<div class="panel hide" ><div class="panel-heading"><h3 class="panel-title"><button type="button" class="close" aria-label="Close"><span aria-hidden="true">&times;</span></button></h3></div><div class="panel-body"></div></div>'
	$("div.mui-notifications").append(notification);
	var notification=$("div.mui-notifications div.panel:last-child");
	notification.addClass("panel-"+notify.type);
	var heading=notification.children("div.panel-heading").children("h3");
	heading.html(notify.title+heading.html());
	if(notify.icon){
		var notificationIcon='<i class="fa" aria-hidden="true"></i>';
		heading.prepend(notificationIcon+" ");
		heading.children("i").addClass("fa-"+notify.i);
	}
	var body=notification.children("div.panel-body");
	body.html(notify.message);
	return notification;
}
function notificationPopUp(notify){
	notify.addClass("notification-loading");
	notify.animateCss("fadeInRight","",function(){
		setTimeout(function(){
			notify.removeClass("notification-loading");
		}, 500);
	});
}

function notificationClose(notify){
	if(notify.hasClass("notification-fading") 
		|| notify.hasClass("notification-closing")){
		return;
	}
	notify.addClass("notification-closing");
	notify.animateCss("bounceOutUp","short",function(){
		var box=notify.parents("div.mui-notifications");
		notify.fadeTo(0,0).slideUp(500,"easeOutExpo",function(){
			notify.remove();
			if(box.children("div.panel").length==0)
			box.remove();
		});
	});
}

function notificationFade(notify){
	if( notify.hasClass("notification-closing")){
		return;
	}	
	notify.addClass("notification-fading");
	notify.fadeTo(1500,0,function(){
		var box=notify.parents("div.mui-notifications");
		notify.slideUp(500,"easeOutExpo",function(){
			notify.remove();
			if(box.children("div.panel").length==0)
			box.remove();
		});
	});
		

}