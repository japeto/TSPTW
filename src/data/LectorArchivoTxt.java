/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import java.io.*;
import javax.swing.*;

/**
 *
 * @author esteban
 */
public class LectorArchivoTxt {

    FileReader ArchivoInput = null;
    BufferedReader br = null;
    
    String lectura[];
    String salida="";
    String salida1="";
    int nNodos = 0; 

    public LectorArchivoTxt(File archivo){

        try{            
            ArchivoInput = new FileReader(archivo);
            br = new BufferedReader(ArchivoInput);
                      
            //Se incluye el numero de nodos en el archivo.**********
            String linea = br.readLine();            
            nNodos = Integer.parseInt(linea);
            salida += nNodos + "\n";
            //******************************************************
                        
            //Se carga las distancias entre los clientes N*(N - 1).***************
            for(int i =0; i < nNodos ; i++){                
                linea = br.readLine();
                lectura = linea.split(" ");                
                for(int j = 0; j < nNodos; j++){
                    if(i != j)
                        salida1 = salida1 + (i+1) +" "+ (j+1) +" "+ lectura[j]+"\n";                    
                }                
            }
            //********************************************************************
            
            //Se generan las ventanas en cada cliente N.*************************           
            for(int i =0; i < nNodos ; i++){ 
                linea = br.readLine();
                lectura = linea.split(" ");
                    salida = salida + (i + 1) +" "+ (0) +" "+ lectura[0]+ " "+ lectura[lectura.length-1]+"\n";
            }
            //********************************************************************
           
            String ruta = archivo.getPath()+".prueba.txt";
            FileWriter prueba = new FileWriter(ruta);
            prueba.write(salida+salida1);
            prueba.close(); 
            
                        
        }catch (FileNotFoundException ex){
                System.out.print( "Error. " + ex.toString() + ": " + ex.getMessage() );
                
        }catch(IOException ex){
               System.out.print( "Error. " + ex.toString() + ": " + ex.getMessage() );
        }
        javax.swing.JOptionPane.showMessageDialog(null,"End Convert");
    }
    public String[] getMatriz(){
        return lectura;
    }
}
