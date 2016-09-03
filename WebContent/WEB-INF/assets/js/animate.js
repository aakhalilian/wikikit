$.fn.extend({
    animateCss: function (animationName,length,callback) {
    	if (typeof length === "undefined" || length === null) { 
		    length = ""; 
		 }
		if (typeof callback === "undefined" || callback === null) { 
		    callback = function(){}; 
		 }
        var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
        $(this).addClass(length +' animated ' +animationName).one(animationEnd, function() {
            $(this).removeClass(length +' animated ' +animationName);
            callback();
        });
    }
});
