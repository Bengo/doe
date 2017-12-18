package fr.gordet.doe.service;

import java.io.File;

import fr.gordet.doe.model.DoeMetaData;

public interface DoeService {

	
	void putDetaildDoe();
	DoeMetaData getDetailsDoe();
	File getDoePDF() throws Exception;
}
