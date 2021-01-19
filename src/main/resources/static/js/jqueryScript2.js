$(document).ready(function() {
	
	// DO GET
	$.ajax({
		type : "GET",
		url : "http://localhost:7777/article/apiall",
		success: function(result){
			$.each(result, function(i, article){
				
				var articleRow = '<tr>' +
									'<td>' + article.id + '</td>' +
									'<td>' + article.libelle + '</td>' +
									'<td>' + article.prix + '</td>' +
									'<td>' + article.qte_stock + '</td>' +
									'<td> ' + '<a class="btn btn-primary" href="/ '+ article.id +'">Ajouter au panier</a>'+'</td>' +
								  '</tr>';
				
				$('#articleTable tbody').append(articleRow);
				
	        });
			
			$( "#articleTable tbody tr:odd" ).addClass("info");
			$( "#articleTable tbody tr:even" ).addClass("success");
		},
		error : function(e) {
			alert("ERROR: ", e);
			console.log("ERROR: ", e);
		}
	});
	
	// do Filter on View
	$("#inputFilter").on("keyup", function() {
	    var inputValue = $(this).val().toLowerCase();
	    $("#articleTable tr").filter(function() {
	    	$(this).toggle($(this).text().toLowerCase().indexOf(inputValue) > -1)
	    });
	});
})