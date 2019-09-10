package it.giuseppe.cometa.dao.imp;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Service;

import it.giuseppe.cometa.dao.IArticoliDao;

@Service
public class ArticoliDao extends NamedParameterJdbcDaoSupport implements IArticoliDao{
	
	@Autowired
	public ArticoliDao(DataSource dataSource) {
		super();
		setDataSource(dataSource);
	}
	
	
	@Override
	public List<String> getAllArticoli() {

		String sql = "SELECT DISTINCT A.descrizione, A.codice FROM cometa.articoli A LEFT JOIN lotti L WHERE A.id = L.id_articolo";
		List<String> articoli=  getJdbcTemplate().queryForList(sql, String.class);
		return articoli;
	}
	
	@Override
	public List<String> getAllDetailsArticoli(int id) {

		String sql = "SELECT codice, descrizione, L.quantita FROM articoli A LEFT JOIN lotti L ON L.id_articolo = A.id WHERE A.id = ?";
		//MapSqlParameterSource params = new MapSqlParameterSource();
		//params.addValue("id", id);
		//BeanPropertyRowMapper<String> rm = new BeanPropertyRowMapper<String>(String.class);
		//List<String> detailsArticoli = getNamedParameterJdbcTemplate().query(sql, params, rm);
		
		List<String> detailsArticoli = getJdbcTemplate().queryForList(sql,new Object[]{id}, String.class);
		

		return detailsArticoli;

	} 
	
	public void updateArticolo(int codice, String newDescrizione) {

		String sql = "UPDATE articolo SET descrizione = :newDescrizione WHERE codice = :codice";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("newDescrizione", newDescrizione);
		params.addValue("codice", codice);
		getNamedParameterJdbcTemplate().update(sql, params);

	}

	public void addArticolo(String addArticolo) {

		String sql = "INSERT INTO articoli descrizione values :addArticolo";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("addArticolo", addArticolo);
		getNamedParameterJdbcTemplate().update(sql, params);

	}
	
	public List<String> filtroDescrizioneArticoli (String descrizioneFiltro) {
		
		String sql = "SELECT DISTINCT A.descrizione, A.codice FROM cometa.articoli A LEFT JOIN lotti L WHERE A.id = L.id_articolo and A.descrizione LIKE " + "'%" + ":descrizioneFiltro" + "%'";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("descrizioneFiltro", descrizioneFiltro);
		BeanPropertyRowMapper<String> rm = new BeanPropertyRowMapper<String>(String.class);
		List<String> articoliFiltroDescrizione = getNamedParameterJdbcTemplate().query(sql, params, rm);
		
		return articoliFiltroDescrizione;
	}
	
	public List<String> filtroCodiceArticoli (int codiceFiltro){
		
		String sql = "SELECT DISTINCT A.descrizione, A.codice FROM cometa.articoli A LEFT JOIN lotti L WHERE A.id = L.id_articolo and A.codice LIKE " + "'%" + ":codiceFiltro" + "%'";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("codiceFiltro", codiceFiltro);
		BeanPropertyRowMapper<String> rm = new BeanPropertyRowMapper<String>(String.class);
		List<String> articoliFiltroCodice = getNamedParameterJdbcTemplate().query(sql, params, rm);
		
		return articoliFiltroCodice;
		
	}
	
}
