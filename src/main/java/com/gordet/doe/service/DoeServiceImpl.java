package com.gordet.doe.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gordet.doe.model.DoeMetaData;
import com.gordet.doe.model.Materiau;
import com.itextpdf.text.pdf.PdfConcatenate;
import com.itextpdf.text.pdf.PdfReader;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

@Service("doeMetadataService")
public class DoeServiceImpl implements DoeService {

	@Override
	public void putDetaildDoe() {
		// TODO Auto-generated method stub

	}

	@Override
	public DoeMetaData getDetailsDoe() {
		DoeMetaData data = new DoeMetaData();
		data.setDate("Février 2017");
		List<String> lignesTitres = new ArrayList<>();
		lignesTitres.add("CONSTRUCTION D'UNE IME");
		lignesTitres.add("Site de Kerlouen");
		lignesTitres.add("LANDERNAU");
		data.setLignesTitre(lignesTitres);
		data.setMoaNom("AIGUILLON CONSTRUCTION");
		data.setMoaAdresse("171 rue du vern");
		data.setMoaVille("35200 - RENNES");
		data.setMoeNom("AUA STRUCTURES");
		data.setMoeAdresse("78, avenue de Kéradannec");
		data.setMoeVille("29000 - QUIMPER");
		data.setLot("LOT 18 - REVETEMENTS DE SOLS ET MURS");
		List<Materiau> materiaux = new ArrayList<Materiau>();
		Materiau materiau = new Materiau();
		materiau.setCategorie("CHAPE");
		materiau.setMarque("SIKA");
		materiau.setReferences(Arrays.asList(new String[] { "Sikaviscochape" }));
		materiaux.add(materiau);
		Materiau materiau1 = new Materiau();
		materiau1.setCategorie("CHAPE");
		materiau1.setMarque("");
		materiau1.setReferences(Arrays.asList(new String[] { "Sable", "Ciment" }));
		materiaux.add(materiau1);
		materiaux.add(materiau1);
		materiaux.add(materiau1);
		materiaux.add(materiau1);
		materiaux.add(materiau1);
		materiaux.add(materiau1);
		materiaux.add(materiau);
		materiaux.add(materiau1);
		materiaux.add(materiau);
		materiaux.add(materiau);
		materiaux.add(materiau1);
		materiaux.add(materiau1);
		materiaux.add(materiau1);
		materiaux.add(materiau1);
		materiaux.add(materiau1);
		materiaux.add(materiau1);
		materiaux.add(materiau);
		materiaux.add(materiau1);
		materiaux.add(materiau);
		materiaux.add(materiau);
		materiaux.add(materiau1);
		materiaux.add(materiau1);
		materiaux.add(materiau1);
		materiaux.add(materiau1);
		materiaux.add(materiau1);
		materiaux.add(materiau);
		materiaux.add(materiau1);
		materiaux.add(materiau);
		materiaux.add(materiau);
		materiaux.add(materiau1);
		materiaux.add(materiau1);
		materiaux.add(materiau);
		materiaux.add(materiau1);
		materiaux.add(materiau);
		materiaux.add(materiau);
		materiaux.add(materiau1);
		materiaux.add(materiau1);
		materiaux.add(materiau1);
		materiaux.add(materiau1);
		materiaux.add(materiau1);
		materiaux.add(materiau);
		materiaux.add(materiau1);
		materiaux.add(materiau);
		materiaux.add(materiau);
		data.setMateriaux(materiaux);
		return data;
	}

	@Override
	public File getDoePDF() throws Exception {
		
		final 			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.ODFDOM);

		
		try {
			
			
			DoeMetaData data = getDetailsDoe();

			
			
			// FIXME path
			// Get template stream (either the default or overridden by the user)
			File doeGardeTemplate = new File(
					"/home/bengo/Documents/boulot/boite/workspace/doe/src/main/resources/templates/doe-page-garde.odt");
			InputStream inGarde = new FileInputStream(doeGardeTemplate);
			// Prepare the IXDocReport instance based on the template, using
			// Freemarker template engine
			IXDocReport reportGarde = XDocReportRegistry.getRegistry().loadReport(inGarde, TemplateEngineKind.Velocity);


			// Add properties to the context
			IContext ctxGarde = reportGarde.createContext();


			ctxGarde.put("data", data);
			ctxGarde.put("date", data.getDate());
			ctxGarde.put("titres", data.getLignesTitre());
			ctxGarde.put("moanom", data.getMoaNom());
			ctxGarde.put("moaadresse", data.getMoaAdresse());
			ctxGarde.put("moaville", data.getMoaVille());
			ctxGarde.put("moenom", data.getMoeNom());
			ctxGarde.put("moeadresse", data.getMoeAdresse());
			ctxGarde.put("moeville", data.getMoeVille());

			ctxGarde.put("lot", data.getLot());

//			ctxGarde.put("materiaux", data.getMateriaux());

			// FIXME path
			File garde = new File("/tmp/out-garde.pdf");
			OutputStream gardePdf = new FileOutputStream(garde);
			// Write the PDF file to output stream
			reportGarde.convert(ctxGarde, options, gardePdf);
			gardePdf.close();

			File doeMateriauxTemplate = new File(
					"/home/bengo/Documents/boulot/boite/workspace/doe/src/main/resources/templates/doe-page-materiaux.odt");
			InputStream inMateriaux = new FileInputStream(doeMateriauxTemplate);
			// Prepare the IXDocReport instance based on the template, using
			// Freemarker template engine
			IXDocReport reportMateriaux = XDocReportRegistry.getRegistry().loadReport(inMateriaux, TemplateEngineKind.Velocity);


			// Add properties to the context
			IContext ctxMateraiaux = reportMateriaux.createContext();



			ctxMateraiaux.put("materiaux", data.getMateriaux());

			// FIXME path
			File materiaux = new File("/tmp/out-materiaux.pdf");
			OutputStream materiauxPdf = new FileOutputStream(materiaux);
			// Write the PDF file to output stream
			reportMateriaux.convert(ctxMateraiaux, options, materiauxPdf);
			materiauxPdf.close();
			
			
			// FIXME path

			// concat pdf
			File doe = new File("/tmp/doe.pdf");

			PdfConcatenate copy = new PdfConcatenate(new FileOutputStream(doe));
			// FIXME path

			copy.addPages(new PdfReader("/tmp/out-garde.pdf"));
			copy.addPages(new PdfReader("/tmp/out-materiaux.pdf"));
			copy.addPages(new PdfReader("/tmp/sable_ciment.pdf"));
			copy.addPages(new PdfReader("/tmp/SADERFLEX_805_D.pdf"));

			copy.close();
			return doe;
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
	}

}
