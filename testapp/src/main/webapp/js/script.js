function confirmDelete(){
	var selected = document.getElementsByName("selectedNewsId");
	for( var i = 0; i < selected.length; i++){
		if(selected[i].checked){
			return confirmDialog();
		}
	}
	alert(nothingSelectedMessage);
	return false;
}

function confirmDialog(){
	return confirm(confirmDeleteMessage);
}

function verifyForm( form ){
	var field;
	var fieldName;
	var fieldValue;
	var errors = [];
	for ( var i = 0; i < form.elements.length; i++) {
		field = form.elements[i];
		fieldName = field.nodeName.toLowerCase();
		fieldValue = field.value;
		if (fieldName == "input") {
			if (field.name == "news.title") {
				if (fieldValue == "") {
					errors.push(title_required);
				} else if (fieldValue.length > 100) {
					errors.push(title_length);
				}
			}
		if (field.name == "dateString") {
				var expr = new RegExp(datePattern);
				if(fieldValue == "") {
					errors.push(date_required);
				}  else if (!(fieldValue.match(expr))) {
					errors.push(date_format);
				}
			}
		} else if (fieldName == "textarea") {
			if (field.name == "news.brief") {
				if (fieldValue == "") {
					errors.push(brief_required);
				} else if (fieldValue.length > 500) {
					errors.push(brief_length);
				}
			}
			if (field.name == "news.content") {
				if (fieldValue == "") {
					errors.push(content_required);
				} else if (fieldValue.length > 2000) {
					errors.push(content_length);
				}
			}
		}
	}
	if (errors.length == 0) {
		return true;
	} else {
		var errorMessage = "";
		for (i = 0; i < errors.length; i++) {
			errorMessage += errors[i] + "\n";
		}
		alert(errorMessage);
	}
	return false;
}