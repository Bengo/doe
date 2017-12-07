package com.gordet.doe.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gordet.doe.model.DoeMetaData;
import com.gordet.doe.service.DoeService;

import io.swagger.annotations.ApiParam;

@RestController
public class DoeController {

	@Autowired
	private DoeService doeService;

	@RequestMapping(value = "/doe/{id}/metadata", method = RequestMethod.GET)
	@ApiParam(name = "id", type="string", required=true)	
	public DoeMetaData getDoeMetaData(@PathVariable("id") String id) {
		return doeService.getDetailsDoe();
	}

	@RequestMapping(value = "/doe/{id}/pdf", method = RequestMethod.GET)
	@ApiParam(name = "id", type="string", required=true)	
	public void getDoeFile(@PathVariable("id") String id, HttpServletResponse response) {
		 try {
			 	File pdf = doeService.getDoePDF();

		      // get your file as InputStream
		      InputStream is = new FileInputStream(pdf);
		      // copy it to response's OutputStream
		      org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
		      response.setContentType("application/pdf");
		      response.flushBuffer();
		    } catch (IOException ex) {
		      throw new RuntimeException("IOError writing file to output stream");
		    }
	}
	
	@RequestMapping(value = "/doe/{id}/metadata", method = RequestMethod.POST)
	@ApiParam(name = "id", type="string", required=true)
	public void postDoeMetaData(@PathVariable("id") String id) {
		doeService.putDetaildDoe();
	}
}
