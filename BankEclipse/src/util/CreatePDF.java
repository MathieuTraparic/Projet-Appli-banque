/**
 *@Author: David Riviere 
 *2 mai 2017
 */
package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class CreatePDF {

	private String paragraphContent;
	private String PDFName;

	public CreatePDF(String PDFName, String paragraphContent) {
		this.PDFName = PDFName;
		this.paragraphContent = paragraphContent;
		
		PdfWriter writer;
		try {
			String name = String.format("C:/Users/Beltharion/Desktop/ProjetBank/Projet-Appli-banque/BankEclipse/%s",this.PDFName);
			FileOutputStream fos = new FileOutputStream(name);
			writer = new PdfWriter(fos);
			PdfDocument pdf = new PdfDocument(writer);
			Document document = new Document(pdf,PageSize.A4);
			//PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
			addDataToPDF(document, this.paragraphContent);
		} catch (FileNotFoundException e) {
		} catch (IOException e){
		}
	}

	private static void addDataToPDF(Document document, String paragraphContent) {
		document.add(new Paragraph(paragraphContent));
	}
}
