/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package D3;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

/**
 * @author Christian Sum 
 * Correo   :   christian.osum@gmail.com
 * Carné    :   3190-09-02
 * Clase    :   Compiladores
 * Semestre :   5to
 * Catedratico  :   Elder Prado
 * Universidad Mariano Galvez 
 * Guatemala 2015
 */
public class Persona {
    private ArrayList errores = new ArrayList();
    
    public TransformGroup persona(ArrayList arr){
        Transform3D t3d = new Transform3D();
        TransformGroup modelo = new TransformGroup(t3d);
        Boolean anchoB=false, altoB=false, formaB=false, posXB=false,posYB=false,posZB=false;        
        
        ObjectFile myOBJ = new ObjectFile();
        Scene myOBJScene = null;
        try {
            System.out.println(System.getProperty("java.class.path"));
            myOBJScene = myOBJ.load("c:/LEGO_man.obj");
            modelo.addChild(myOBJScene.getSceneGroup());
        } catch (FileNotFoundException e) {
           setErrors("Alerta: No se encontró el objeto para persona.");
        }
         
        
        Iterator ite = arr.iterator();
        while(ite.hasNext()){
            ArrayList objto = (ArrayList)ite.next();
            //System.out.println(objto);
            if (objto.get(0).equals("forma")){
                formaB=true;
            }else if (objto.get(0).equals("alto")){
                altoB=true;
            }else if (objto.get(0).equals("posX")){
                posXB=true;
            }else if (objto.get(0).equals("posY")){
                posYB=true;
            }else if (objto.get(0).equals("posZ")){        
                posZB=true;
            }else if(anchoB==false && altoB==false && formaB==false && posXB==false && posYB==false && posZB==false){
                    setErrors("Alerta: Lista de propiedades vacia.");
            }else{
                setErrors("Alerta: No se reconoce la propiedad: '"+objto.get(0)+"' Valor: "+objto); //Error Sintactico
            }
        }
        return modelo;
    }
    public ArrayList getError(){
        return errores;
    }
    public void setErrors(String e){
        errores.add(e);
    }
}
