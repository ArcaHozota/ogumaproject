/**
 * 画面初期化
 */
function transitionTo(linkIndex) {
	$("#transitionTo").val(linkIndex);
	$("#transitionTo").trigger("click");
}

function linktest(linkIndex) {
	alert("1");
	$("#transitionTo").val(linkIndex);
	alert("2");
	$("#transitionTo").trigger("click");
	alert("3");
}
/**
 * 共通画面レアいうとを初期化する
 */
function commonLoad() {
	//共通ヘッダを取込する
//	$(".publicHeader").load('resources/templates/layout/header.html');
	//メニュー画面を取込する
//	$(".left").load('resources/templates/layout/left.html');
	//footerを取込する
//	$(".footer").load('resources/templates/layout/footer.html');
}