package fr.gordet.doe.controller;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.gordet.doe.model.Marque;
import fr.gordet.doe.repository.MarqueRepository;
import io.swagger.annotations.ApiParam;

@RestController
public class MarqueController {

	@Autowired
	MarqueRepository repository;

	@RequestMapping(value = "/Marques", method = RequestMethod.POST)
	@ApiParam(value = "libelle", type = "string", required = true)
	public Marque postMarque(@RequestParam(value = "libelle", required = true) String libelle) {

		Marque marque = repository.findByLibelle(libelle.toUpperCase());
		if (marque != null) {
			return marque;
		} else {
			marque = new Marque();
			marque.setLibelle(libelle.toUpperCase());
			return repository.save(marque);
		}

	}

	@RequestMapping(value = "/MarquesSearch", method = RequestMethod.GET)
	@ApiParam(value = "libelle", type = "string", required = true)
	public List<Marque> searchMarque(@RequestParam(value = "libelle", required = true) String libelle) {

		List<Marque> marques = repository.findByLibelleLike(libelle.toUpperCase());
		if (CollectionUtils.isNotEmpty(marques)) {
			return marques;
		} else {
			throw new ResourceNotFoundException("Aucune Marque trouvée");
		}

	}

	@RequestMapping(value = "/Marques", method = RequestMethod.GET)
	public List<Marque> getMarques() {
		return repository.findAll();
	}

	@RequestMapping(value = "/Marques/{id}", method = RequestMethod.GET)
	@ApiParam(value = "id", type = "string", required = true)
	public Marque getMarqueById(@PathVariable(value = "id", required = true) String id) {
		Marque marque = repository.findById(id);
		if (marque != null) {
			return marque;
		} else {
			throw new ResourceNotFoundException("Aucune Marque avec cet id n'existe");
		}
	}

	@RequestMapping(value = "/Marques/{id}", method = RequestMethod.DELETE)
	@ApiParam(value = "id", type = "string", required = true)
	public void delMarque(@PathVariable(value = "id", required = true) String id) {
		repository.deleteById(id);
	}

}
