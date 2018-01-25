package fr.gordet.doe.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfOutline;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.navigation.PdfExplicitDestination;
import com.itextpdf.kernel.utils.PdfMerger;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import fr.gordet.doe.model.DoeMetaData;
import fr.gordet.doe.model.Materiau;
import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.ConverterTypeVia;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

@Service("doeMetadataService")
public class DoeServiceImpl implements DoeService {
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Value("${tempPath}")
	String tempPath;
	

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
		materiaux.add(materiau);
		Materiau materiau1 = new Materiau();
		materiau1.setCategorie("CHAPE");
		materiau1.setMarque("");
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

	
		data.setMateriaux(materiaux);
		return data;
	}

	@Override
	public File getDoePDF() throws Exception {

		final Options options = Options.getTo(ConverterTypeTo.PDF).via(ConverterTypeVia.ODFDOM);

		try {

			DoeMetaData data = getDetailsDoe();

			Resource resource = resourceLoader.getResource("classpath:templates/doe-page-garde.odt");
			InputStream inGarde = resource.getInputStream();
			
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

			// ctxGarde.put("materiaux", data.getMateriaux());

			// FIXME path
			File garde = new File("/tmp/out-garde.pdf");
			OutputStream gardePdf = new FileOutputStream(garde);
			// Write the PDF file to output stream
			reportGarde.convert(ctxGarde, options, gardePdf);
			gardePdf.close();

			generateMateriauxdf();
			// FIXME path

			// concat pdf avec sommmaire
			PdfDocument pdfDoc = new PdfDocument(new PdfWriter("/tmp/doe.pdf"));
			PdfMerger merger = new PdfMerger(pdfDoc);		
			PdfOutline rootOutline = pdfDoc.getOutlines(false);

			int page = 1;

					 
			PdfDocument pdfDocGarde = new PdfDocument(new PdfReader(tempPath+"/out-garde.pdf"));
			merger.merge(pdfDocGarde, 1, pdfDocGarde.getNumberOfPages());
			
			PdfDocument pdfDocMateriaux = new PdfDocument(new PdfReader(tempPath+"/out-materiaux.pdf"));
			int nbPgMateriaux = pdfDocMateriaux.getNumberOfPages();
			PdfDocument pdfDocRef1 = new PdfDocument(new PdfReader(resourceLoader.getResource("classpath:pdf/sable_ciment.pdf").getInputStream()));

			int nbRef1 = pdfDocRef1.getNumberOfPages();
			String titreRef1 = "Sable & ciment";
			PdfDocument pdfDocRef2 = new PdfDocument(new PdfReader(resourceLoader.getResource("classpath:pdf/SADERFLEX_805_D.pdf").getInputStream()));
			int nbRef2= pdfDocRef2.getNumberOfPages();
			String titreRef2 = "SADERFLEX 805 D";

			
			merger.setCloseSourceDocuments(true)
			.merge(pdfDocMateriaux, 1, pdfDocMateriaux.getNumberOfPages())
			.merge(pdfDocRef1, 1, pdfDocRef1.getNumberOfPages())
			.merge(pdfDocRef2, 1, pdfDocRef2.getNumberOfPages());
			
			
			//sommaire pdf
			PdfOutline gardeOutline = rootOutline.addOutline("Page de garde");
			gardeOutline.addDestination(PdfExplicitDestination.createFit(pdfDoc.getPage(page)));
			page += pdfDocGarde.getNumberOfPages();
		 
			
			PdfOutline materiaux = rootOutline.addOutline("Table des matériaux utilises");
			materiaux.addDestination(PdfExplicitDestination.createFit(pdfDoc.getPage(page)));
			page += nbPgMateriaux;
			
			PdfOutline ref1 = rootOutline.addOutline(titreRef1);
			ref1.addDestination(PdfExplicitDestination.createFit(pdfDoc.getPage(page)));
			page += nbRef1;
			
			PdfOutline ref2 = rootOutline.addOutline(titreRef2);
			ref2.addDestination(PdfExplicitDestination.createFit(pdfDoc.getPage(page)));
			page += nbRef2;
			
			pdfDocGarde.close();	
			pdfDoc.close();
			return new File(tempPath+"/doe.pdf");
		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
	}

	/**
	 * Full itext
	 */

	private File generateMateriauxdf() throws Exception {

		PdfDocument pdfDoc = new PdfDocument(new PdfWriter(tempPath+"/out-materiaux.pdf"));
		Document doc = new Document(pdfDoc);

		Table table = new Table(3, true);

		doc.add(table);

		// Entetes
		table.addCell(new Cell(1, 3).setKeepTogether(true)
				.add((new Paragraph("MATERIAUX UTILISES")).setTextAlignment(TextAlignment.CENTER).setBold()));

		DoeMetaData data = getDetailsDoe();

		for (int i = 0; i < data.getMateriaux().size(); i++) {
			Materiau materiau = data.getMateriaux().get(i);

			// onflush le tableau toutes les 5 lignes
			if (i % 5 == 0) {
				table.flush();
			}

			// si derniere ligne on a une bordure basse
			Border borderBasse = Border.NO_BORDER;
			if (i == data.getMateriaux().size() - 1) {
				borderBasse = new SolidBorder(1);
			}

			// categorie
			table.addCell(
					new Cell().setKeepTogether(true).add(new Paragraph(materiau.getCategorie()).setMargins(10, 0, 0, 0))
							.setBorderTop(Border.NO_BORDER).setBorderBottom(borderBasse));
			// marque
			table.addCell(
					new Cell().setKeepTogether(true).add(new Paragraph(materiau.getMarque()).setMargins(10, 0, 0, 0))
							.setBorderTop(Border.NO_BORDER).setBorderBottom(borderBasse));
			// reference
			com.itextpdf.layout.element.List listeRefs = new com.itextpdf.layout.element.List();
			listeRefs.setListSymbol("");
			listeRefs.setMarginTop(10);
//			materiau.getReferences().forEach(ref -> listeRefs.add(new ListItem(ref)));

			table.addCell(new Cell().setKeepTogether(true).add(listeRefs).setMargins(10, 0, 0, 0)
					.setBorderTop(Border.NO_BORDER).setBorderBottom(borderBasse));
		}

		table.complete();

		doc.close();

		return new File("tempPath+/out-materiaux.pdf");
	}

}
