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
			
			
			out+='<table><tr><th>Codice</th><th>Descrizione</th><th>Quantita</th></tr>';
			
			for(var articolo of response) {
				out+='<tr><td>  </td><td>'+articolo+'</td><td>  </td></tr>';
			}
			
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
	
	<table>
	  <thead>
	  <tr>
	    <th>Codice</th>
	    <th>Descrizione</th>
	    <th>Quantita</th>
	  </tr>
	  </thead>
	  <tbody id="myTable">
	  <tr>
	    <td></td>
	    <td></td>
	    <td></td>
	  </tr>
	  </tbody>
	</table>
	
</body>
</html>