package fr.gordet.doe.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.gordet.doe.model.DoeMetaData;
import fr.gordet.doe.service.DoeService;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@RestController
public class DoeController {

	@Autowired
	private DoeService doeService;

	@RequestMapping(value = "/doe/{id}/metadata", method = RequestMethod.GET)
	@ApiParam(name = "id", type="string", required=true)	
	public DoeMetaData getDoeMetaData(@PathVariable("id") String id) {
		return doeService.getDetailsDoe();
	}

	@RequestMapping(value = "/doe/{id}/pdf", method = RequestMethod.GET, produces=MediaType.APPLICATION_PDF_VALUE)
	@ApiParam(name = "id", type="string", required=true)
	@ApiResponse(code = 200, message= "return pdf file", response=File.class)
	public ResponseEntity<InputStreamResource> getDoeFile(@PathVariable("id") String id) throws Exception {
		 try {
			  File pdf = doeService.getDoePDF();

		      // get your file as InputStream
		      InputStream is = new FileInputStream(pdf);
	      
		      HttpHeaders headers = new HttpHeaders();
		      headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		      headers.add("Pragma", "no-cache");
		      headers.add("Expires", "0");
		      headers.add("Content-Disposition", "attachment;filename=doe.pdf");
		      return ResponseEntity
		              .ok()
		              .headers(headers)
		              .contentType(MediaType.APPLICATION_OCTET_STREAM)
		              .body(new InputStreamResource(is));
		    } catch (Exception ex) {
		        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new InputStreamResource(IOUtils.toInputStream(ex.getLocalizedMessage())));   
		    }
	}

	@RequestMapping(value = "/doe/{id}/metadata", method = RequestMethod.POST)
	@ApiParam(name = "id", type="string", required=true)
	public void postDoeMetaData(@PathVariable("id") String id) {
		doeService.putDetaildDoe();
	}
}
