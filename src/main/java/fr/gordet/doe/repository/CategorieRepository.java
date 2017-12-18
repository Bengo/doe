package fr.gordet.doe.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import fr.gordet.doe.model.Categorie;

@Service
public interface CategorieRepository extends MongoRepository<Categorie, String> {

	public Categorie findById(String id);
	
	public Categorie findByLibelle(String libelle);
	
	public List<Categorie> findByLibelleLike(String libelle);
	
	public void deleteById(String id);

}
