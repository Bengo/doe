package com.gordet.doe.service;

import java.io.File;

import com.gordet.doe.model.DoeMetaData;

public interface DoeService {

	
	void putDetaildDoe();
	DoeMetaData getDetailsDoe();
	File getDoePDF() throws Exception;	
}
