package com.gordet.doe.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gordet.doe.model.DoeMetaData;
import com.gordet.doe.model.Materiau;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

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
		materiau.setReferences(Arrays.asList(new String[] {"Sikaviscochape"}));
		materiaux.add(materiau); 
		Materiau materiau1 = new Materiau();
		materiau1.setCategorie("CHAPE");
		materiau1.setMarque("");
		materiau1.setReferences(Arrays.asList(new String[] {"Sable","Ciment"}));
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
		materiaux.add(materiau);
		materiaux.add(materiau1);
		materiaux.add(materiau);
		materiaux.add(materiau1);
		data.setMateriaux(materiaux);
		return data;
	}

	@Override
	public File getDoePDF() {
		try {
		// Get template stream (either the default or overridden by the user)
		File doeTemplate = new File("/home/bengo/Documents/boulot/boite/workspace/doe/src/main/resources/templates/doe.odt");
		InputStream in = new FileInputStream(doeTemplate);
		// Prepare the IXDocReport instance based on the template, using
		// Freemarker template engine
			IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Velocity);

            FieldsMetadata metadata = report.createFieldsMetadata();
//            metadata.load( "titres", String.class, true );
//            metadata.load("materiaux", Materiau.class, true);
//            metadata.setBeforeRowToken("@row");
//            metadata.setAfterRowToken("/@row");
//            metadata.load("materiaux.References",String.class,true);
			Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.ODFDOM);

			// Add properties to the context
			IContext ctx = report.createContext();

			DoeMetaData data = getDetailsDoe();

			ctx.put("data", data);
			ctx.put("date", data.getDate());
			ctx.put("titres", data.getLignesTitre());
			ctx.put("moanom", data.getMoaNom());
			ctx.put("moaadresse", data.getMoaAdresse());
			ctx.put("moaville", data.getMoaVille());
			ctx.put("moenom", data.getMoeNom());
			ctx.put("moeadresse", data.getMoeAdresse());
			ctx.put("moeville", data.getMoeVille());

			
			ctx.put("lot", data.getLot());
			
			ctx.put("materiaux", data.getMateriaux());

//				ctx.put("to", invoice.getTo());
//				ctx.put("sender", invoice.getInvoicer());
//				// instruct XDocReport to inspect InvoiceRow entity as well
//				// which is given as a list and iterated in a table
//				FieldsMetadata metadata = report.createFieldsMetadata();
//				metadata.load("r", String.class, true);
//				ctx.put("r", invoice.getInvoiceRows());
		
		
		File pdf = new File("/tmp/out.pdf");
		OutputStream out = new FileOutputStream(pdf);
		// Write the PDF file to output stream
		report.convert(ctx, options, out);
		out.close();
		return pdf;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XDocReportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		return null;
	}

}
