package fr.gordet.doe.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.gordet.doe.model.Materiau;
import fr.gordet.doe.repository.MateriauxRepository;
import io.swagger.annotations.ApiParam;

@RestController
public class MateriauxController {

	@Autowired
	MateriauxRepository repository;

	@RequestMapping(value = "/materiaux", method = RequestMethod.POST)
	@ApiParam(value = "libelle", type = "string", required = true)
	public Materiau postMateriau(@Valid @ModelAttribute("materiau") Materiau materiau) {

		Materiau materiauFound = repository.findByCategorieAndMarqueAndReference(materiau.getCategorie().toUpperCase(),
				materiau.getMarque().toUpperCase(), materiau.getReference());
		if (materiauFound != null) {
			return materiauFound;
		} else {
			Materiau materiauToSave = new Materiau();
			materiauToSave.setMarque(materiau.getMarque().toUpperCase());
			materiauToSave.setCategorie(materiau.getCategorie().toUpperCase());
			materiauToSave.setFichier(materiau.getFichier());
			materiauToSave.setReference(materiau.getReference());
			return repository.save(materiauToSave);
		}
	}

	@RequestMapping(value = "/materiaux", method = RequestMethod.GET)
	public List<Materiau> getMateriaux() {
		return repository.findAll();

	}

	@RequestMapping(value = "/materiauxSearchByCategorie", method = RequestMethod.GET)
	@ApiParam(value = "marque", type = "string", required = true)
	public List<Materiau> searchMateriauxByCategorie(@RequestParam(value = "categorie", required = true) String categorie) {
		return repository.findAllByCategorieLike(categorie.toUpperCase());
	}

	@RequestMapping(value = "/materiaux/{id}", method = RequestMethod.GET)
	@ApiParam(value = "id", type = "string", required = true)
	public Materiau getMateriauById(@PathVariable(value = "id", required = true) String id) {
		Materiau materiau = repository.findById(id);
		if (materiau != null) {
			return materiau;
		} else {
			throw new ResourceNotFoundException("Aucun materiau n'existe avec cet id");
		}
	}

	@RequestMapping(value = "/materiaux/{id}", method = RequestMethod.DELETE)
	@ApiParam(value = "id", type = "string", required = true)
	public void deleteMateriauById(@PathVariable(value = "id", required = true) String id) {
		repository.deleteById(id);
	}

}
