package it.giuseppe.cometa.dao;

import java.util.List;

import it.giuseppe.cometa.domain.Articolo;

public interface IArticoliDao {
	
	public List<Articolo> getAllArticoli();
	public List<String> getAllDetailsArticoli(int id);
	public void updateArticolo(int codice, String newDescrizione);
	public void addArticolo(String addArticolo);
	public List<String> filtroDescrizioneArticoli (String descrizioneFiltro);
	public List<String> filtroCodiceArticoli (int codiceFiltro);
	
}
