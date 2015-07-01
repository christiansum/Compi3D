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
public class Puerta {
    public TransformGroup puerta(ArrayList arr){
        float alto=0;
        float largo=0; 
        float grosor=0.1f;
        String tipo="";
        String orientacion="ejeX";
        String texture="madera";
        float posX=0, posY=0, posZ=0; 
    
        Textura textu= new Textura();
        Appearance blanco = textu.textura(getClass().getResource("/img/blanco.jpg"));
        Appearance madera= textu.textura(getClass().getResource("/img/madera.jpg"));
        Appearance metal= textu.textura(getClass().getResource("/img/metal.jpg"));     
    
    
        Box Door; 
        TransformGroup modelo = new TransformGroup(); //groupPuerta
        Transform3D t3d = new Transform3D(); // transformpuerta
        Transform3D  rotateDoor= new Transform3D(); // rotarPuerta
        Vector3f pP; //posPuerta
        
        Iterator ite = arr.iterator();
        while(ite.hasNext()){
            ArrayList objto = (ArrayList)ite.next();
            //System.out.println(objto);
            if (objto.get(0).equals("tipo")){
                tipo=objto.get(1).toString();
            }else if (objto.get(0).equals("alto")){
                alto=Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("largo")){
                largo=Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("orientacion")){
                orientacion=objto.get(1).toString();
            }else if (objto.get(0).equals("textura")){
                texture=objto.get(1).toString();
            }else if (objto.get(0).equals("posX")){
                posX=Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("posY")){
                posY=Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("posZ")){
                posZ=Float.parseFloat(objto.get(1).toString());
            }
        }
        
        Appearance aT=blanco;
           
        if (tipo.equals("normal")){
            aT = blanco;
        }else if (tipo.equals("madera")){
            aT = madera;
        }else if (tipo.equals("metal")){
            aT = metal;
        }
        
        if (orientacion.equals("ejeX")){
            rotateDoor.rotX(0);
        }else if (orientacion.equals("ejeY")){
            rotateDoor.rotY(Math.PI/4.0d);
        }else if (orientacion.equals("ejeZ")){
            rotateDoor.rotZ(Math.PI/4.0d);
        }else{
            rotateDoor.rotX(0);
        }
             
        Door= new Box (largo, alto, grosor, Primitive.GENERATE_TEXTURE_COORDS, aT);
        pP = new Vector3f(posX, posY, posZ);
        t3d.set(pP);
        rotateDoor.mul(t3d);
        t3d.setTranslation(pP);
        modelo.setTransform(rotateDoor);
        modelo.addChild(Door);
        
        return modelo;
    }
}
