/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package D3;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.geometry.Box;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;

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
        float ancho=0.1f,alto=0,posX=0,posY=0,posZ=0;
        String forma="normal";
        
        ObjectFile myOBJ = new ObjectFile();
        Scene myOBJScene = null;
       
        Transform3D  rotatehuman= new Transform3D(); // rotarPuerta
        Vector3f pP; //posPuerta
        
        
        try {
                        
            Iterator ite = arr.iterator();
            while(ite.hasNext()){
                ArrayList objto = (ArrayList)ite.next();
                //System.out.println(objto);
                if (objto.get(0).equals("forma")){
                    formaB=true;
                    forma = objto.get(1).toString();
                }else if (objto.get(0).equals("alto")){
                    altoB=true;
                    alto=Float.parseFloat(objto.get(1).toString());
                }else if (objto.get(0).equals("posX")){
                    posXB=true;
                    posX=Float.parseFloat(objto.get(1).toString());
                }else if (objto.get(0).equals("posY")){
                    posYB=true;
                    posY=Float.parseFloat(objto.get(1).toString());
                }else if (objto.get(0).equals("posZ")){        
                    posZB=true;
                    posZ=Float.parseFloat(objto.get(1).toString());
                }else {
                    setErrors("Alerta: No se reconoce la propiedad: '"+objto.get(0)+"' Valor: "+objto); //Error Sintactico
                }
            }
            if(altoB==false && formaB==false && posXB==false && posYB==false && posZB==false){
                        setErrors("Alerta: Lista de propiedades vacia. PERSONAS");
                }
            
            if (forma.equals("normal")){
                myOBJScene = myOBJ.load("c:/obj/LEGO_man.obj");  
            }else if(forma.equals("gordo")){
                myOBJScene = myOBJ.load("c:/obj/fat.obj");  
            }
                   
            pP = new Vector3f(posX, posY, posZ);
            t3d.set(pP);
            rotatehuman.mul(t3d);
            t3d.setTranslation(pP);
            modelo.setTransform(rotatehuman);
            modelo.addChild(myOBJScene.getSceneGroup());
            
        } catch (FileNotFoundException e) {
           setErrors("Alerta: No se encontró el objeto para persona.");
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
