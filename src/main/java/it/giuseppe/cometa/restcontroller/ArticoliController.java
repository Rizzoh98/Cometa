package it.giuseppe.cometa.restcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.giuseppe.cometa.dao.IArticoliDao;
import it.giuseppe.cometa.domain.Articolo;

@RestController
public class ArticoliController {

	@Autowired
	IArticoliDao articoliDao;

	@RequestMapping("/articoli/list/")
	public List<Articolo> getArticoliList() {

		List<Articolo> articoli = new ArrayList<Articolo>();

		try {
			articoli = articoliDao.getAllArticoli();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return articoli;
	}

	@RequestMapping("/articoli/list/details/")
	public List<Articolo> getDetailArticolo(@RequestParam("codice") int codice) {

		List<Articolo> articoli = new ArrayList<Articolo>();

		try {
			articoli = articoliDao.getAllDetailsArticoli(codice);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return articoli;
	}
	
	@RequestMapping("filtro/articoli/list/")
	public List<Articolo> getFiltroArticolo(@RequestParam("filtroTxt") String filtroTxt, @RequestParam("option") int option) {

		List<Articolo> articoli = new ArrayList<Articolo>();

		try {
			articoli = articoliDao.getArticolifiltrati(filtroTxt, option);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return articoli;
	}

}
