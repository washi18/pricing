package com.pricing.util;

import java.io.*;

import java.io.FileOutputStream;
import java.io.StringReader;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
public class convertHTML_PDF 
{

	  // itextpdf-5.4.1.jar  http://sourceforge.net/projects/itext/files/iText/
	  // xmlworker-5.4.1.jar http://sourceforge.net/projects/xmlworker/files/
	  public static void convertirHtml2Pdf(String mensaje,String url) {
		    try {
		      Document document = new Document(PageSize.LETTER);
		      PdfWriter pdfWriter = PdfWriter.getInstance
		           (document, new FileOutputStream(url));
		      document.open();
		      document.addAuthor("Real Gagnon");
		      document.addCreator("Real's HowTo");
		      document.addSubject("Thanks for your support");
		      document.addCreationDate();
		      document.addTitle("Please read this");

		      XMLWorkerHelper worker = XMLWorkerHelper.getInstance();

		      String str = mensaje;
		      worker.parseXHtml(pdfWriter, document, new StringReader(str));
		      document.close();
		      System.out.println("Done.");
		      }
		    catch (Exception e) {
		      e.printStackTrace();
		    }
		  }

}

