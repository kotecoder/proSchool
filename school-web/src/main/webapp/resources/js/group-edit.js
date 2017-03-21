var modal_options = {
	"backdrop" : "static",
	"keyboard" : "false",
	"show" : "true"
}
var students_of_group = [];
var students_free = [];
var fillSelect = function(listOfItems) {
	var content = "";
	for (i in listOfItems) {
		content += "<option value = \"" + listOfItems[i].id + "\">"
				+ listOfItems[i].fullName + "</option>\n";
	}
	return content;
};
var fillTable = function(listOfRows, checkboxName) {
	var content = "";
	for (i in listOfRows) {
		content += "<tr><td><input name=\"" + checkboxName
				+ "\" type=\"checkbox\" value = " + listOfRows[i].foreignId
				+ "></td><td><a class=\"anchor\" data-value=\""
				+ listOfRows[i].id + "\">" + listOfRows[i].name
				+ "</a></td><td>" + listOfRows[i].yearStr + "</td></tr>\n";
	}
	return content;
};
// refresh tables with students
var refresh = function() {

	$(".group_stuff").html(fillTable(students_of_group, "removal_checkbox"));
	$(".other_stuff").html(fillTable(students_free, "add_checkbox"));
	$('table.paginated_right').each(makePagesRight);
	$('table.paginated_left').each(makePagesLeft);
	profileSet();

};
function profileSet() {
	$(".anchor").click(function() {
		var id = $(this).data("value");
		$.ajax({
			type : "GET",
			url : "profile/information",
			data : "id=" + id,
			headers : {
				Accept : "text/plain; charset=utf-8",
				"Content-Type" : "text/plain; charset=utf-8"
			},
			async : false,
			success : function(response) {
				$("#profile_modal_content").html(response);
				$("#profileModalButton").click();
			},
			error : function() {
				alert('Internal Server Error');
			}
		});
	});
};
// init functions
$(function() {
	$("#loading_modal").modal(modal_options);
	$(".datepicker").datepicker();
	// $("#loading_modal").modal(modal_options);
	fillHeader();
	$("#loading_modal").modal("hide");
});
// fill header with data
var fillHeader = function() {
	var groupId = $(".hiddenGroup").val();
	$.post("group-edit-header-fill", {
		"groupId" : groupId
	}, function(data) {
		$(".className").html(" "+data.name);
		$("input[name='dateFrom']").val(data.dateFrom);
		$("input[name='dateTill']").val(data.dateTill);
		$(".teachers").html(fillSelect(data.teachers));
		students_of_group = data.groupStudents;
		students_free = data.allFreeStudents;
		$(".main_submit").show();
		refresh();
	}, "json");
};
// REMOVE STUDENT TO GROUP
$(".student_remove").click(function() {
	// $("#loading_modal").modal("show");
	$('#select_for_remove').prop('checked', false);
	$("input[name='removal_checkbox']").each(function() {
		if (this.checked == true) {
			var index = findIndex(students_of_group, this.value);
			transferElement(index, students_of_group, students_free);

		}
	});
	refresh();
	// $("#loading_modal").modal("hide");

});
// ADD STUDENT TO GROUP
$(".student_add").click(function() {
	// $("#loading_modal").modal("show");
	$('#select_for_add').prop('checked', false);
	$("input[name='add_checkbox']").each(function() {
		if (this.checked == true) {
			var index = findIndex(students_free, this.value);
			transferElement(index, students_free, students_of_group);
		}
	});
	refresh();
	// $("#loading_modal").modal("hide");
});
var transferElement = function(fromId, arreyFrom, arreyTo) {
	arreyTo.push(arreyFrom[fromId]);
	arreyFrom.splice(fromId, 1);
};

var findIndex = function(array, lookedId) {
	for (i in array) {
		if (array[i].foreignId == lookedId) {
			return i;
		}
	}
};

$('#select_for_add').click(function(event) {
	if (this.checked) {
		$("input[name='add_checkbox']").each(function() {
			this.checked = true;
		});
	} else {
		$("input[name='add_checkbox']").each(function() {
			this.checked = false;
		});
	}
});
$('#select_for_remove').click(function(event) {
	if (this.checked) {
		$("input[name='removal_checkbox']").each(function() {
			this.checked = true;
		});
	} else {
		$("input[name='removal_checkbox']").each(function() {
			this.checked = false;
		});
	}
});

$(".main_submit").click(function() {
	/*
	 * var studentIds=[]; for(i in students_of_group){
	 * studentIds.push(students_of_group[i].id); }
	 */
	var output = {
		"groupId" : $(".hiddenGroup").val(),
		"teacherId" : $(".teachers").val(),
		"dateFrom" : $("input[name='dateFrom']").val(),
		"dateTill" : $("input[name='dateTill']").val(),
		"students" : students_of_group
	};
	$.ajax({
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		'type' : 'POST',
		'url' : "group-edit-update",
		'data' : JSON.stringify(output),
		'dataType' : 'json',
		'success' : function() {

		}
	});
	setTimeout(function() {
		window.open("headteacher-groups", "_self");
	}, 500);
	// document.location.href = "headteacher-groups";
	// $.post("group-edit-update", JSON.stringify(output),function(){},"json");
	console.log(output);
});
$("thead").click(function() {
	setTimeout(function() {
		$("li.active").each(function() {
			this.click();
		});
	}, 50);
});
