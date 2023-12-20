

function clickRead(obj) {
	//参照ボタンを押下
    $("#read").val(obj.getAttribute("value"));
	$("#read").trigger("click");
}
function clickUpdate(obj) {
	//更新ボタンを押下
	$("#update").val(obj.getAttribute("value"));
	$("#update").trigger("click");
}
function clickDelete(obj) {
	if (confirm("削除してもよろしいでしょうか")) {
		//削除ボタンを押下
		$("#delete").val(obj.getAttribute("value"));
		$("#delete").trigger("click");
		return true;
	} else {
		return false;
	}

}

$(document).ready(function() {
	commonLoad();
});
