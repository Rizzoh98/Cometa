package it.giuseppe.cometa.dao;

import java.util.List;

public interface IArticoliDao {
	
	public List<String> getAllArticoli();
	public List<String> getAllDetailsArticoli(int id);
	public void updateArticolo(int codice, String newDescrizione);
	public void addArticolo(String addArticolo);
	public List<String> filtroDescrizioneArticoli (String descrizioneFiltro);
	public List<String> filtroCodiceArticoli (int codiceFiltro);
	
}
