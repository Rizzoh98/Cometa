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
	public List<String> getDetailArticolo(int id) {

		List<String> articoli = new ArrayList<String>();

		try {
			articoli = articoliDao.getAllDetailsArticoli(id);
		} catch (Exception e) {

			e.printStackTrace();
		}

		return articoli;
	}

	@RequestMapping("/articolo/add/")
	public ResponseEntity<String> addArticolo(@RequestParam("addArticolo") String addArticolo) {

		if (addArticolo != "") {
			articoliDao.addArticolo(addArticolo);
			return new ResponseEntity<>(addArticolo, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping("/artiocolo/update/")
	public ResponseEntity<Integer> updateArticolo(@PathParam("codice") Integer codice,
			@RequestParam("descrizione") String descrizione) {

		if (codice != 0) {
			articoliDao.updateArticolo(codice, descrizione);
			return new ResponseEntity<>(codice, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping("/filtro/desc/articolo/")
	public ResponseEntity<String> filtroDescrizioneArticolo(
			@RequestParam("descrizioneArticolo") String descrizioneArticolo) {

		if (descrizioneArticolo != "") {
			articoliDao.filtroDescrizioneArticoli(descrizioneArticolo);
			return new ResponseEntity<>(descrizioneArticolo, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping("/filtro/cod/articolo/")
	public ResponseEntity<Integer> filtroCodiceArticolo(@PathParam("codice") Integer codice) {

		if (codice != null) {
			articoliDao.filtroCodiceArticoli(codice);
			return new ResponseEntity<>(codice, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
}
