package com.pricing.extras;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.zkoss.zk.ui.Executions;
 
public class lectorPDF 
{
    
   private PDFParser parser;
   private PDFTextStripper pdfStripper;
   private PDDocument pdDoc ;
   private COSDocument cosDoc ;
   
   private String Text ;
   private String filePath;
   private File file;
   
   public final String separator = System.getProperty("file.separator");
 //=================================
    public lectorPDF() {
		// TODO Auto-generated constructor stub
	}
    //=================================
    //Obteniendo la ruta de la aplicacion en en tiempo real
  	public String getPath(){
  		return Executions.getCurrent().getDesktop().getWebApp().getRealPath(separator)+separator+"auxDisp"+separator;
  	}
   public String ToText() throws IOException
   {
       this.pdfStripper = null;
       this.pdDoc = null;
       this.cosDoc = null;
       
       file = new File(filePath);
       if(file.exists())
       {
    	   parser = new PDFParser(new RandomAccessFile(file,"r")); // update for PDFBox V 2.0
           
           parser.parse();
           cosDoc = parser.getDocument();
           pdfStripper = new PDFTextStripper();
           pdDoc = new PDDocument(cosDoc);
           pdDoc.getNumberOfPages();
           pdfStripper.setStartPage(1);
           pdfStripper.setEndPage(pdDoc.getNumberOfPages());
           
           Text = pdfStripper.getText(pdDoc);
           pdDoc.close();
           cosDoc.close();
       }
       else
    	   Text="";
       return Text;
   }
   public static void descargarPdf(String url, String ficheroDestino) throws Exception {

	   URL ficheroUrl = new URL(url);
	   InputStream inputStream = ficheroUrl.openStream();			
	   OutputStream outputStream = new FileOutputStream(ficheroDestino); // path y nombre del nuevo fichero creado

	   byte[] b = new byte[2048];
	   int longitud;

	   while ((longitud = inputStream.read(b)) != -1) {
	      outputStream.write(b, 0, longitud);
	   }

	   inputStream.close();  // Cerramos la conexión entrada
	   outputStream.close(); // Cerramos la conexión salida
	}
    public void setFilePath(String filePath) {
        this.filePath = getPath()+filePath;
    }
}
