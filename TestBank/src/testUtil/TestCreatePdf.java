package testUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import util.CreatePDF;

public class TestCreatePdf {
	
	@Before 
	public void setUp(){
		CreatePDF pdfTest = new CreatePDF("name.pdf","iban","bankname", "agencyName",
					"ownername","C:/Users/Beltharion/Desktop/name.pdf");
	}
	
	@Test(expected=NullPointerException.class)
	public void testPdfName_nullName() {
		CreatePDF nameNull = new CreatePDF(null,"iban","bankname", "agencyName",
				"ownername","savepath");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPdfName_noExtension() {
		CreatePDF noExtension = new CreatePDF("name","iban","bankname", "agencyName",
				"ownername","savepath");
	}
	
	@Test(expected=NullPointerException.class)
	public void testPdfName_nullIban() {
		CreatePDF ibanNull = new CreatePDF("name.pdf",null,"bankname", "agencyName",
				"ownername","savepath");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPdfName_emptyIban() {
		CreatePDF ibanNull = new CreatePDF("name.pdf","","bankname", "agencyName",
				"ownername","savepath");
	}
	
	@Test(expected=NullPointerException.class)
	public void testPdfName_nullBank() {
		CreatePDF banknameNull = new CreatePDF("name.pdf","iban",null, "agencyName",
				"ownername","savepath");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPdfName_emptyBank() {
		CreatePDF banknameEmpty = new CreatePDF("name.pdf","iban","", "agencyName",
				"ownername","savepath");
	}
	
	@Test(expected=NullPointerException.class)
	public void testPdfName_nullAgency() {
		CreatePDF agencyNull = new CreatePDF("name.pdf","iban","bankname", null,
				"ownername","savepath");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPdfName_emptyAgency() {
		CreatePDF agencyNull = new CreatePDF("name.pdf","iban","bankname", "",
				"ownername","savepath");
	}
	
	@Test(expected=NullPointerException.class)
	public void testPdfName_nullOwner() {
		CreatePDF ownernameNull = new CreatePDF("name.pdf","iban","bankname", "agencyName",
				null,"savepath");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPdfName_emptyOwner() {
		CreatePDF ownernameNull = new CreatePDF("name.pdf","iban","bankname", "agencyName",
				"","savepath");
	}
	
	@Test(expected=NullPointerException.class)
	public void testPdfName_nullSavePath() {
		CreatePDF savepathNull = new CreatePDF("name.pdf","iban","bankname", "agencyName",
				"ownername",null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPdfName_emptySavePath() {
		CreatePDF savepathNull = new CreatePDF("name.pdf","iban","bankname", "agencyName",
				"ownername","");
	}
	
	@Test(expected=FileNotFoundException.class)
	public void test_pdfNotCreated(){
		//TODO
	}
}
