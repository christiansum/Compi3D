/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package D3;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import java.io.FileNotFoundException;
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
public class Arbol {
    private ArrayList errores = new ArrayList();
         
    
    public TransformGroup arbol(ArrayList arr){
        Transform3D t3d = new Transform3D();
        TransformGroup modelo = new TransformGroup(t3d);
        float ancho=5;
        float largo=5;
        float alto= 10;
        String forma="pino";
        float posX=0,posY=0,posZ=0;
         ObjectFile myOBJ = new ObjectFile();
        Scene myOBJScene = null;
        Transform3D  rotatetree= new Transform3D(); // rotarPuerta
        Vector3f pP; //posPuerta
       Boolean anchoB=false, altoB=false, formaB=false, posXB=false,posYB=false,posZB=false,tipoB=true;    
        try {
            Iterator ite = arr.iterator();
            while(ite.hasNext()){
                ArrayList objto = (ArrayList)ite.next();
                //System.out.println(objto);
                if (objto.get(0).equals("tipo")){
                    tipoB=true;
                }else if (objto.get(0).equals("forma")){
                    formaB=true;
                    forma=objto.get(1).toString();
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
                        setErrors("Alerta: Lista de propiedades vacia ARBOL.");
                }
            
            if (forma.equals("otro")){
                myOBJScene = myOBJ.load("c:/obj/tree.obj");  
            }else if(forma.equals("mine")){
                myOBJScene = myOBJ.load("c:/obj/mine.obj");  
            }
            pP = new Vector3f(posX, posY, posZ);
            //Vector3f scal= new Vector3f();
            
            t3d.set(pP);
            rotatetree.mul(t3d);
            t3d.setTranslation(pP);
            modelo.setTransform(rotatetree);
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
