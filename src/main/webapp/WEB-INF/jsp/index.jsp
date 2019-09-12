<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">

<script>

	$(document).ready(function() {
	
		getArticoliList();

	});

	function getArticoliList ()
	{
		$.get("/articoli/list/", function(response){
		
			var out = "";
			var list="";
			
			
			out+='<table><tr><th>Codice</th><th>Descrizione</th><th>Quantita</th></tr>';
			
			$.each(response, function (i,list){
				out+='<tr><th>  </th><th> list </th><th>  </th></tr>';
			});
			
			out +='</table>';
		
			$("#articoli").html(out);
		});
	}

</script>



</head>
<body>
	<h1>Articoli</h1>
	<br>
	<div id="articoli"></div>

	
</body>
</html>