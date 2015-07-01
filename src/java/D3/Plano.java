/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package D3;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import java.util.ArrayList;
import java.util.Iterator;
import javax.media.j3d.Appearance;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Point3d;


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
public class Plano {
    public TransformGroup plano(ArrayList arr){
        float dimX=20;
        //System.out.println("Si entrooo");
        float dimY=20;
        Textura textu= new Textura();
        Appearance grama = textu.textura(getClass().getResource("/img/grama.jpg"));
        
        Box plano;
        TransformGroup modelo =  new  TransformGroup (); 
        Transform3D transP =  new  Transform3D (); 
        Vector3f posP; 
        Iterator ite = arr.iterator();
        while(ite.hasNext()){
            ArrayList objto = (ArrayList)ite.next();
            //System.out.println(objto);
            if (objto.get(0).equals("dimX")){
                dimX = Float.parseFloat(objto.get(1).toString());
                
            }else if (objto.get(0).equals("dimY")){
                dimY = Float.parseFloat(objto.get(1).toString());
                //System.out.println(dimY);
            }
        }
    //elderprado9@gmail.com
        plano= new Box(dimX, 0.01f, dimY, Primitive.GENERATE_TEXTURE_COORDS, grama);
        posP =  new  Vector3f(0f,-2.5f,0f); 
        transP . setTranslation ( posP ); 
        modelo . setTransform ( transP ); 
        modelo . addChild ( plano );
        return modelo;
        
    }
}
