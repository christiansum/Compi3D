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
    public TransformGroup persona(ArrayList arr){
        Transform3D t3d = new Transform3D();
        TransformGroup modelo = new TransformGroup(t3d);
        
        ObjectFile myOBJ = new ObjectFile();
        Scene myOBJScene = null;
         try {
       myOBJScene = myOBJ.load("C:/LEGO_man.obj");
         } catch (FileNotFoundException e) {
       System.out.println("Could not open OBJ file...exiting");
       //System.exit(1);
     }
         modelo.addChild(myOBJScene.getSceneGroup());
        
        /*Iterator ite = arr.iterator();
        while(ite.hasNext()){
            ArrayList objto = (ArrayList)ite.next();
            //System.out.println(objto);
            if (objto.get(1).equals("columna")){}
        }*/
        return modelo;
    }
}
