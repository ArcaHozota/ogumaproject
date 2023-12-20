function showSubMenu(el) {
	el.classList.add('show-submenu');
	document.addEventListener('click', function onDocClick(e) {
		e.preventDefault();
		if (el.contains(e.target)) {
			return;
		}
		document.removeEventListener('click', onDocClick);
		hideSubMenu(el);
	});
}

function hideSubMenu(el) {
	el.classList.remove('show-submenu');
}

function clickSubMenu(linkIndex) {
	$("#clickLink").val(linkIndex);
	$("#clickLink").trigger("click");
}
$(window).on('load', function() {
	$("#header").load('/html/layout/header.html');

//	$("#centers").load('/html/layout/body.html');
//	alert("111");
//	var menuVal = $("#subHtmlIndex").val();
//	alert("222");
//	alert("menuVal:"+menuVal);
//	alert("333");
	//		if (menuVal == 0) {
	//			$("#centers").load('/html/layout/body.html');
	//		} else if (menuVal == 11) {
	//			$("#centers").load('/html/layout/body11.html');
	//		} else if (menuVal == 12) {
	//			$("#centers").load('/html/layout/body12.html');
	//		}
	$("#footer").load('/html/layout/footer.html');
});