package fr.gordet.doe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import fr.gordet.doe.repository.MateriauxRepository;

@SpringBootApplication
public class DoeApplication {

	@Autowired
	private MateriauxRepository materiauxRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DoeApplication.class, args);
	}
}
