/**
 *@Author: David Riviere 
 *2 mai 2017
 */
package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class CreatePDF {

	private String name;

	public CreatePDF(String name) {
		PdfWriter writer;
		try {
			writer = new PdfWriter(this.name);
			PdfDocument pdf = new PdfDocument(writer);
			Document document = new Document(pdf);
			PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
		} catch (FileNotFoundException e) {
		} catch (IOException e){
			
		}
	}

	public static void addDataToPDF(Document document, Paragraph paragraph) {
		document.add(paragraph);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String pdfName) {
		this.name = pdfName;
	}
}
