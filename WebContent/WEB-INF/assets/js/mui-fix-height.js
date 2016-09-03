$(document).ready(function(){
	fixHeight();
	$(window).resize(function(){
		fixHeight();
	});
});

function fixHeight() {
	var windowHeight=$(window).height();
	$(".WH").each(function(){
		var That=$(this), indexNo=That.attr('class').indexOf("wh-"), multNo=1, divNo=1;
		if (indexNo>0) {
			var factors=That.attr('class').substr((indexNo+3),2);
			multNo=factors.substr(0,1);
			divNo=factors.substr(1,1);
		}
		That.css("min-height",windowHeight*multNo/divNo+"px");
	});

	$(".WHF").each(function(){
		var That=$(this), indexNo=That.attr('class').indexOf("wh-"), multNo=1, divNo=1;
		if (indexNo>0) {
			var factors=That.attr('class').substr((indexNo+3),2);
			multNo=factors.substr(0,1);
			divNo=factors.substr(1,1);
		}
		That.css("height",windowHeight*multNo/divNo+"px");
		That.css("overflow","hidden");
	});
	var bodyHeight=$("body").height();
	if(windowHeight>bodyHeight){
		var diff=windowHeight-bodyHeight;
		$(".WH-footer").css("position","relative");
		$(".WH-footer").css("top",diff);
	}else
		$(".WH-footer").css("top",0);
}