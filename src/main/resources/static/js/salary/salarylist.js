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

function clickCreatePdf(obj) {
	//請求書ボタンを押下
	$("#createPdf").val(obj.getAttribute("value"));
	$("#createPdf").trigger("click");
}

//Jquery
$(function() {
	// 全選択,全解除
	$('#all').on('click', function() {
		$("input[name='checkBoxId']").prop('checked', this.checked);
	});
	
	// 給料削除
	$('#deleteSalary').on('click', function() {
		var selCount = 0;
		var selectedIds = "";
		$("input[name='checkBoxId']:checked").each(function(i, obj) {
			selectedIds += obj.value + ","
		});

		selCount = $("input[name='checkBoxId']:checked").length;

		if (selCount > 0) {
			$("#itemIds").val(selectedIds);
			return true;
		} else {
			alert("明細をご選択ください。");
			return false;
		}
	});
		// 給料更新
	$('#updateSalary').on('click', function() {
		var selCount = 0;
		var selectedIds = "";
		$("input[name='checkBoxId']:checked").each(function(i, obj) {
			selectedIds += obj.value + ","
		});

		selCount = $("input[name='checkBoxId']:checked").length;

		if (selCount > 0) {
			$("#itemIds").val(selectedIds);
			return true;
		} else {
			alert("明細をご選択ください。");
			return false;
		}
	});
	
});

$(document).ready(function() {
	commonLoad();
});
