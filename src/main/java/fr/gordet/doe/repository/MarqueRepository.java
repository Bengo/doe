package fr.gordet.doe.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import fr.gordet.doe.model.Marque;

@Service
public interface MarqueRepository extends MongoRepository<Marque, String> {

	public Marque findById(String id);
	
	public Marque findByLibelle(String libelle);
	
	public List<Marque> findByLibelleLike(String libelle);
	
	public void deleteById(String id);

}
