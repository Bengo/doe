package fr.gordet.doe.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import fr.gordet.doe.model.Materiau;

@Service
public interface MateriauxRepository extends MongoRepository<Materiau, String> {
	
	Materiau findByCategorieAndMarqueAndReference(String categorie, String marque, String reference);
	
	Materiau findById(String id);

	List<Materiau> findAllByCategorieLike(String categorie);
	
	List<Materiau> findAllByMarqueLike(String marque);
	
	List<Materiau> findAllByReferenceLike(String reference);

	void deleteById(String id);
}
