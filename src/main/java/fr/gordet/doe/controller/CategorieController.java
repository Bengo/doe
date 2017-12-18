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

import fr.gordet.doe.model.Categorie;
import fr.gordet.doe.repository.CategorieRepository;
import io.swagger.annotations.ApiParam;

@RestController
public class CategorieController {

	@Autowired
	CategorieRepository repository;

	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	@ApiParam(value = "libelle", type = "string", required = true)
	public Categorie postCategorie(@RequestParam(value = "libelle", required = true) String libelle) {

		Categorie categorie = repository.findByLibelle(libelle.toUpperCase());
		if (categorie != null) {
			return categorie;
		} else {
			categorie = new Categorie();
			categorie.setLibelle(libelle.toUpperCase());
			return repository.save(categorie);
		}

	}

	@RequestMapping(value = "/categoriesSearch", method = RequestMethod.GET)
	@ApiParam(value = "libelle", type = "string", required = true)
	public List<Categorie> searchCategorie(@RequestParam(value = "libelle", required = true) String libelle) {

		List<Categorie> categories = repository.findByLibelleLike(libelle.toUpperCase());
		if (CollectionUtils.isNotEmpty(categories)) {
			return categories;
		} else {
			throw new ResourceNotFoundException("Aucune categorie trouvée");
		}

	}

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public List<Categorie> getCategories() {
		return repository.findAll();
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
	@ApiParam(value = "id", type = "string", required = true)
	public Categorie getCategorieById(@PathVariable(value = "id", required = true) String id) {
		Categorie categorie = repository.findById(id);
		if (categorie != null) {
			return categorie;
		} else {
			throw new ResourceNotFoundException("Aucune categorie avec cet id n'existe");
		}
	}

	@RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
	@ApiParam(value = "id", type = "string", required = true)
	public void delCategorie(@PathVariable(value = "id", required = true) String id) {
		repository.deleteById(id);
	}

}
