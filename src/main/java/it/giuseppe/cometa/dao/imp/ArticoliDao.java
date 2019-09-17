package it.giuseppe.cometa.dao.imp;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Service;

import it.giuseppe.cometa.dao.IArticoliDao;
import it.giuseppe.cometa.domain.Articolo;

@Service
public class ArticoliDao extends NamedParameterJdbcDaoSupport implements IArticoliDao{
	
	@Autowired
	public ArticoliDao(DataSource dataSource) {
		super();
		setDataSource(dataSource);
	}
	
	
	@Override
	public List<Articolo> getAllArticoli() {

		String sql = "SELECT A.id, A.codice, A.descrizione, sum(COALESCE(L.quantita,0)) quantita FROM articoli A LEFT JOIN lotti L ON A.id = L.id_articolo GROUP BY A.id, A.codice, A.descrizione";
		BeanPropertyRowMapper<Articolo> rm = new BeanPropertyRowMapper<Articolo>(Articolo.class);
		List<Articolo> articoli=  getJdbcTemplate().query(sql, rm);
		return articoli;
	}
	
	@Override
	public List<Articolo> getAllDetailsArticoli(int codice) {

		String sql = "SELECT codice, descrizione, sum(COALESCE(L.quantita,0)) quantita FROM articoli A LEFT JOIN lotti L ON L.id_articolo = A.id WHERE A.codice = :codice";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("codice", codice);
		BeanPropertyRowMapper<Articolo> rm = new BeanPropertyRowMapper<Articolo>(Articolo.class);
		List<Articolo> detailsArticoli = getNamedParameterJdbcTemplate().query(sql, params, rm);
		return detailsArticoli;
	} 
	
	public List<Articolo> getArticolifiltrati (String filtroTxt, int option) {
		
		List<Articolo> articoliFiltrati = null;
		MapSqlParameterSource params = new MapSqlParameterSource();
		BeanPropertyRowMapper<Articolo> rm = new BeanPropertyRowMapper<Articolo>(Articolo.class);
		
		if (option == 0) 
		{
			String sql = "SELECT DISTINCT A.descrizione, A.codice FROM cometa.articoli A LEFT JOIN lotti L ON A.id = L.id_articolo WHERE A.codice LIKE " + "'%" + ":filtroTxt" + "%'";
			params.addValue("filtroTxt", filtroTxt);
			articoliFiltrati = getNamedParameterJdbcTemplate().query(sql, params, rm);
		}
		else 
		{
			String sql = "SELECT DISTINCT A.descrizione, A.codice FROM cometa.articoli A LEFT JOIN lotti L ON A.id = L.id_articolo WHERE A.descrizione LIKE " + "'%" + ":filtroTxt" + "%'";
			params.addValue("filtroTxt", filtroTxt);
			articoliFiltrati = getNamedParameterJdbcTemplate().query(sql, params, rm);
		}
		
		return articoliFiltrati;
	}
	
}
