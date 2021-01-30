console.log("ana fay9");
$(function() {
	$("button").on("click", function() {
		$.post("http://localhost:7777/rating/update",
			{
				email: "",
				valeur: choix
			},
			function(data, status) {
				alert("Data: " + data + "\nStatus: " + status);
			});
	});
});
