/**
 * @author Christian Sum 
 * Correo   :   Christian.osum@gmail.com
 * Carné    :   3190-09-02
 * Clase    :   Compiladores
 * Semestre :   5to
 * Catedratico  :   Ing. Elder Prado
 * Universidad Mariano Gálvez 
 * Guatemala 2015
 */
package operacional;
import D3.*; //import the package D3
import java.io.*;
import java.util.*;
import java.lang.*;

public class analizador {
    public parser instancia = new parser();
    private ArrayList errores = new ArrayList();
    
    public Boolean procesar(String ciudad) throws Exception {
        if(instancia.cargarString(ciudad)){
            return true;
        }
        return false;
    } 
    public Integer[] getDetLex() throws Exception{
        return instancia.getLexDetails();
    }
    
    public ArrayList getDesign() throws Exception{
        return instancia.getObjto();
    }
    
    public void crear()throws Exception {
        try{
            Design3D diseno= new Design3D();
            diseno.Design3D((ArrayList)instancia.getObjto());
            errores = diseno.getError();
        }catch (Exception e){
            setErrors("Critico: No fue posible generar el universo: Method Desig3D");
        }
    }
    
    public ArrayList getErrorSem(){
        return errores;
    }
    public ArrayList getErrorSin(){
        return instancia.getError();
    }
    public void setErrors(Object e){
        errores.add(e);
    }

}
