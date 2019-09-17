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
	
	$(document).on('mouseenter','tr.riga',function(){
		$(this).css("color", "white");
	});
	
	$(document).on('mouseleave','tr.riga',function(){
		$(this).css("color", "black");
	});
	
	<!-- DETTAGLI ARTICOLO -->
	$(document).on('click','tr.riga',function(){
		var codice = $(this).find('td.codice').text();
		getDettagliArticoli(codice);
	});
	
	<!-- FILTRI -->
	$(document).on('click','#filtroCodice',function(){
		var filtroTxt = $(this).find('#filtroTxt').text();
		var option = 0;
		getFiltroArticoli(filtroTxt, option);
	});
	
	$(document).on('click','#filtroDescrizione',function(){
		var filtroTxt = $(this).find('#filtroTxt').text();
		var option = 1;
		getFiltroArticoli(filtroTxt, option);
	});
	
	<!-- LISTA ARTICOLI -->
	function getArticoliList ()
	{
		$.get("/articoli/list/", function(response){
		
			var out = "";
			out +='<tr><th><font size="5">Codice</font></th><th><font size="5">Descrizione</font></th><th><font size="5">Quantita</font></th></tr>';
			
			for(var articolo of response) {
				out+='<tr class="riga" align = "center"><td class ="codice">'+articolo.codice+'</td><td>'+articolo.descrizione+'</td><td>'+articolo.quantita+'</td></tr>';
			}
		
			$("#articoli").html(out);
		});
	}
	
	<!-- DETTAGLI ARTICOLO -->
	function getDettagliArticoli (codice)
	{
		$.get("/articoli/list/details/?codice=" + codice, function(response){
		
			var out = "";
			out +='<tr><th><font size="5">Codice</font></th><th><font size="5">Descrizione</font></th><th><font size="5">Quantita</font></th></tr>';
			
			for(var articolo of response) {
				out+='<tr class="riga" align = "center"><td class ="codice">'+articolo.codice+'</td><td>'+articolo.descrizione+'</td><td>'+articolo.quantita+'</td></tr>';
			}
		
			$("#articoli").html(out);
		});
	}
	
	<!-- FILTRO ARTICOLI -->
	function getFiltroArticoli (filtroTxt, option)
	{
		$.get("filtro/articoli/list/?filtroTxt=" + filtroTxt + "&option=" + option, function(response){
		
			var out = "";
			out +='<tr><th><font size="5">Codice</font></th><th><font size="5">Descrizione</font></th><th><font size="5">Quantita</font></th></tr>';
			
			for(var articolo of response) {
				out+='<tr class="riga" align = "center"><td class ="codice">'+articolo.codice+'</td><td>'+articolo.descrizione+'</td><td>'+articolo.quantita+'</td></tr>';
			}
		
			$("#articoli").html(out);
		});
	}

</script>

</head>

<body style = "background :#008080">
	
	<h1 align ="center">Lista articoli</h1><br>
	
	<div  align="center">
		<form>
	  	<input type="text" id="filtroTxt" placeholder="Inserisci il valore del filtro">
	  	<input type="button" onclick="getArticoliListCodice()" id="filtroCodice" value = "Filtra per codice">
	  	<input type="button" onclick="getArticoliListDescrizione()" id="filtroDescrizione" value = "Filtra per descrizione">
	  	<input type="button" onclick="getArticoliList()" id="resetPage" value = "Elimina filtri">
	  	</form>
	</div>
	<br>
	<table align ="center" id="articoli">
	</table>
	
</body>
</html>