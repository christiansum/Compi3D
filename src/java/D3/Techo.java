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
public class Techo {
    public TransformGroup techo(ArrayList arr){
        float ancho=0;
        float largo=0;
        float alto= 0.1f;
        String forma="plano";
        String orientacion="ejeY";
        float posX=0,posY=0,posZ=0;
        
        Textura textu= new Textura();
        Appearance teja = textu.textura(getClass().getResource("/img/teja.jpg"));
        Appearance cemento= textu.textura(getClass().getResource("/img/cemento.jpg"));
        Appearance metal= textu.textura(getClass().getResource("/img/metal.jpg"));
    
        Box Top; //Top
        
        Box Top1; //Top1
        TransformGroup  modelo1 = new TransformGroup(); //modelo1
        Transform3D  t3d1 = new Transform3D(); //t3d1
        Transform3D  rotateTop1= new Transform3D(); //rotateTop1
        Vector3f pD1;// pD1
        
        Box Top2; //Top2
        TransformGroup  modelo2 = new TransformGroup(); //modelo2
        Transform3D  t3d2 = new Transform3D(); //t3d2
        Transform3D  rotateTop2= new Transform3D(); //rotateTop2
        Vector3f pD2;// pD2
         
        TransformGroup modelo =  new  TransformGroup (); //modelo
        Transform3D  t3d =  new  Transform3D (); //transformarTecho
        Transform3D rotateTop =  new Transform3D(); //rotateTop
        Vector3f pT; //pT
               
        Iterator ite = arr.iterator();
        while(ite.hasNext()){
            ArrayList objto = (ArrayList)ite.next();
            //System.out.println(objto);
            if (objto.get(0).equals("forma")){
                forma=objto.get(1).toString();
            }else if (objto.get(0).equals("orientacion")){
                orientacion=objto.get(1).toString();
            }else if (objto.get(0).equals("ancho")){
                ancho=Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("largo")){
                largo=Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("alto")){
                alto=Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("posX")){
                posX=Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("posY")){
                posY=Float.parseFloat(objto.get(1).toString());
            }
        }
        
        if (orientacion.equals("ejeX")){
            rotateTop.rotX(0);
        }else if (orientacion.equals("ejeY")){
            rotateTop.rotY(Math.PI/4.0d);
        }else if (orientacion.equals("ejeZ")){
            rotateTop.rotZ(Math.PI/4.0d);
        }else{
            rotateTop.rotX(0);
        }
        
        Top= new Box(largo,alto, ancho, Primitive.GENERATE_TEXTURE_COORDS, teja);
        Top1= new Box(largo,alto, ancho, Primitive.GENERATE_TEXTURE_COORDS, teja);
        Top2= new Box(largo,alto, ancho, Primitive.GENERATE_TEXTURE_COORDS, teja);
        
        if (forma.equals("plano")){
            Top= new Box(largo,alto, ancho, Primitive.GENERATE_TEXTURE_COORDS, teja);
        }else if (forma.equals("2aguas")){
            Top1 = new Box(largo, ancho, alto, Primitive.GENERATE_TEXTURE_COORDS, cemento);
            Top2 = new Box (largo, ancho, alto, Primitive.GENERATE_TEXTURE_COORDS, cemento);
        }else {
            Top= new Box(largo,alto, ancho, Primitive.GENERATE_TEXTURE_COORDS, teja);
        }
        
        pD1 = new Vector3f(posX ,  posY ,  posZ);  
              
        rotateTop1.rotX(Math.PI/4.0d);
        t3d1.set(pD1);
        rotateTop1.mul(t3d1);
        t3d1.setTranslation(pD1);
        modelo1.setTransform(rotateTop1);
        modelo1.addChild(Top1);

      //Top2 = new Box (largo, ancho, profundidad, Primitive.GENERATE_TEXTURE_COORDS, concreto);
        pD2 = new Vector3f(posX ,  posY+0.5f ,  posZ ); 
        rotateTop2.rotX(-Math.PI/4.0d);
        t3d2.set(pD2);
        rotateTop2.mul(t3d2);
        t3d2.setTranslation(pD2);
        modelo2.setTransform(rotateTop2);
        modelo2.addChild(Top2);

        pT=  new  Vector3f(posX, posY, posZ ); 


        t3d.setTranslation(pT);
        modelo.setTransform(t3d);
        modelo. addChild (Top);
            
        
        return modelo;
    }
}
