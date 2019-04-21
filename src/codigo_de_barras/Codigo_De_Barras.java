/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo_de_barras;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author Sertes
 */
public class Codigo_De_Barras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        
        Document doc = new Document();
        PdfWriter pw = PdfWriter.getInstance(doc,new FileOutputStream("codigo.pdf"));
        doc.open();
        
        BarcodeEAN bean = new BarcodeEAN();
        bean.setCode("9789584247018");
        Image img = bean.createImageWithBarcode(pw.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
        
        doc.add(img);
        doc.close();
    }
    
}
