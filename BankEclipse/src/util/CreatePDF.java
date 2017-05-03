/**
 *@Author: David Riviere 
 *2 mai 2017
 * Source : http://developers.itextpdf.com/fr
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
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;

public class CreatePDF {

	private String iban;
	private String pdfName;
	private String ownerName;
	private String bankName;
	private String agencyName;
	private String savePath;

	public CreatePDF(String pdfName, String iban, String bankName, String agencyName
			, String ownerName, String savePath) {
		checkPdfName(pdfName);
		checkIban(iban);
		checkBankName(bankName);
		checkAgencyName(agencyName);
		checkOwnerName(ownerName);
		
		this.pdfName = pdfName;
		this.iban = iban;
		this.bankName = bankName;
		this.agencyName= agencyName;
		this.ownerName = ownerName;
		this.savePath = savePath;
		
		PdfWriter writer;
		try {
			String name = String.format("%s/%s",this.savePath,this.pdfName);
			FileOutputStream fos = new FileOutputStream(name);
			writer = new PdfWriter(fos);
			PdfDocument pdf = new PdfDocument(writer);
			Document document = new Document(pdf,PageSize.A4);
			PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
			addDataToPDF(document, this.iban, font, bankName, agencyName,
					ownerName);
			document.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e){
		}
	}
	
	private static void addDataToPDF(Document document, String iban, PdfFont font,
			String bankName, String agencyName, String ownerName) {
		document.add(new Paragraph(String.format("Name of the bank : %s", bankName))).setFont(font);
		document.add(new Paragraph(String.format("Name of the agency : %s", agencyName))).setFont(font);
		document.add(new Paragraph(String.format("Owner : %s", ownerName))).setFont(font);
		document.add(new Paragraph("Iban Number :")).setFont(font);
		List list = new List()
			    .setSymbolIndent(12)
			    .setListSymbol("\u2190")
			    .setFont(font);
		list.add(new ListItem(iban));
		document.add(list);
	}
	
	private void checkPdfName(String pdfName) throws NullPointerException{
		if(pdfName.equals(null)){
			throw new NullPointerException("The name of the document can't be null");
		}
		int ind=0;
		for(int i=0;i<pdfName.length();i++){
			if(pdfName.charAt(i) == '.'){
				ind =1;
			}
		}
		if(ind==0){
			throw new IllegalArgumentException("pdfName must have a file extension");
		}
	}
	
	private void checkIban(String iban) throws NullPointerException{
		if(iban.equals(null)){
			throw new NullPointerException("The name of the document can't be null");
		}
	}
	
	private void checkBankName(String bankName) throws NullPointerException{
		if(bankName.equals(null)){
			throw new NullPointerException("The name of the document can't be null");
		}
	}
	
	private void checkAgencyName(String agencyName) throws NullPointerException{
		if(agencyName.equals(null)){
			throw new NullPointerException("The name of the agency can't be null");
		}
	}
	
	private void checkOwnerName(String ownerName){
		if(ownerName.equals(null)){
			throw new NullPointerException("The Owner name can't be null");
		}
	}
}
