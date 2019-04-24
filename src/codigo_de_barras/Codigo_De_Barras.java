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
import java.util.Scanner;

/**
 *
 * @author Sertes
 */
public class Codigo_De_Barras {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, DocumentException{
        Scanner sc=new Scanner(System.in);
        String codigo,completo;
        do{
            System.out.println("Escriba un codigo de 12 digitos y un guion en la posicion que se quiera hallar el valor");
            codigo=sc.nextLine();
        }while(codigo.length()!=13 || !codigo.contains("-"));
        completo=completar(codigo);
        
        crearbarras(completo);
    }
    public static String completar(String codigo){
        //como comienza desde 0 se desplazan a la izquierda, y se tomas los pares
        int indice=0,sum=0,cifra=0;
        String completo="";        
        for (int i = 0; i < codigo.length(); i++) {
            System.out.print("i"+codigo.substring(i, i+1));
            System.out.print(" ");
            if(codigo.substring(i, i+1).equals("-")){
                indice=i;
            }
        }        
        for (int i = 0; i < codigo.length(); i++) {
            if(indice!=i){
                if(i%2==0){
                    System.out.print(Integer.parseInt(codigo.substring(i, i+1))+" ");
                    sum=Integer.parseInt(codigo.substring(i, i+1))+sum;
                }else{
                    System.out.print(Integer.parseInt(codigo.substring(i, i+1))*3+" ");
                    sum=Integer.parseInt(codigo.substring(i, i+1))*3+sum;
                }
            }
        }
        System.out.println("sum "+sum);
        System.out.println("indice "+indice);
        for (int i = 0; i < codigo.length(); i++) {
            if(indice%2==0){
                if((i+sum)%10==0){
                    cifra=i;
                }
            }else{
                if((i*3+sum)%10==0){
                    cifra=i;
                }
            }
        }
        System.out.println("cifra "+cifra);
        for (int i = 0; i < codigo.length(); i++) {
            if(indice==i){
                completo=completo+cifra;
            }else{
                completo=completo+codigo.substring(i, i+1);
            }
        }
        System.out.println("completo "+completo);
        return completo;
    }
    
    public static void crearbarras(String completo) throws FileNotFoundException, DocumentException{
        Document doc = new Document();
        PdfWriter pw = PdfWriter.getInstance(doc,new FileOutputStream("codigo.pdf"));
        doc.open();
        
        BarcodeEAN bean = new BarcodeEAN();
        bean.setCode(completo);
        Image img = bean.createImageWithBarcode(pw.getDirectContent(), BaseColor.BLACK, BaseColor.BLACK);
        
        doc.add(img);
        doc.close();
    }
    
    
}
