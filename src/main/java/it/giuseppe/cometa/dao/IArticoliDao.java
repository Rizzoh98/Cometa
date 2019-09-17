package it.giuseppe.cometa.dao;

import java.util.List;

import it.giuseppe.cometa.domain.Articolo;

public interface IArticoliDao {
	
	public List<Articolo> getAllArticoli();
	public List<Articolo> getAllDetailsArticoli(int codice);
	public List<Articolo> getArticolifiltrati (String filtroTxt, int option);
}
