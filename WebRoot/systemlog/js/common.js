function clearNoNum(obj) {
    obj.value = obj.value.replace(/[^\d]/g,'');
}

//复选框全选/全不选
function checkboxCheckedAll(checkName) {
    var checkboxObj = document.getElementsByName(checkName);
	var nLength = checkboxObj.length;

    for (i = 0; i < nLength; i ++) {
        checkboxObj[i].checked = event.srcElement.checked;
    }
}

//获取选中单选框值
function getCheckedRadioValue(radioName) {
    var radioObj = document.getElementsByName(radioName);	
	var nLength = radioObj.length;

	for (i = 0; i < nLength; i ++) {
		if (radioObj[i].checked) {
			return radioObj[i].value;
		}
	}

	return -1;
}

//下拉框自动选中
function toSelected(selectId, selectValue) {
	var optionObj = document.getElementById(selectId).options;
	var nLength = optionObj.length;

    for (i = 0; i < nLength; i ++) {
	    if (optionObj[i].value == selectValue) {
		    optionObj[i].selected = true;
		    break;
	    }
    }
}