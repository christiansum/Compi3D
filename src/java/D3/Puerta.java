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
    private ArrayList errores = new ArrayList();
     
    
    public TransformGroup puerta(ArrayList arr){
        float alto=0;
        float largo=0; 
        float grosor=0.1f;
        String tipo="";
        String orientacion="ejeX";
        String texture="madera";
        float posX=0, posY=0, posZ=0; 
        Boolean altoB=false, largoB=false, tipoB=false, orientacionB=false,
                textureB=false,posXB=false, posYB=false, posZB=false;
       
    
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
                tipoB=true;
            }else if (objto.get(0).equals("alto")){
                altoB=true;
                alto=Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("largo")){
                largoB=true;
                largo=Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("orientacion")){
                orientacionB=true;
                orientacion=objto.get(1).toString();
            }else if (objto.get(0).equals("textura")){
                textureB=true;
                texture=objto.get(1).toString();
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
        if(altoB==false && largoB==false && orientacionB==false && textureB==false && posXB==false && posYB==false && posZB==false){
                    setErrors("Alerta: Lista de propiedades vacia PUERTA.");
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
    public ArrayList getError(){
        return errores;
    }
    public void setErrors(String e){
        errores.add(e);
    }
}
