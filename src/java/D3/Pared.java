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
public class Pared {
    Textura textu= new Textura();
    
    public TransformGroup pared(ArrayList arr){
 
        float largo= 0;
        float alto=0;
        float grosor =0.1f;    
        String orientacion="ejeX";
        String texture="block";
        float posX=0, posY=0, posZ=0;
        
        Appearance ladrillo = textu.textura(getClass().getResource("/img/ladrillo.jpg"));
        Appearance piedra = textu.textura(getClass().getResource("/img/paredPiedra.jpg"));
        Appearance block = textu.textura(getClass().getResource("/img/paredBlock.jpg"));
        
        Box wall;
        TransformGroup ventana = new TransformGroup();
        TransformGroup modelo =  new  TransformGroup (); // leg2Group
        Transform3D t3d =  new  Transform3D (); // leg2Transform
        Transform3D rotateWall = new Transform3D(); //  rotarPared
        Vector3f posWall;// leg2Position
      
        Iterator ite = arr.iterator();
        while(ite.hasNext()){
            ArrayList objto = (ArrayList)ite.next();
            //System.out.println(objto)           
            
            if (objto.get(0).equals("tipo")){
                
            }else if (objto.get(0).equals("alto")){
                alto = Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("largo")){
                largo = Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("orientacion")){
                orientacion=objto.get(1).toString();
            }else if (objto.get(0).equals("textura")){
                texture=objto.get(1).toString();
            }else if (objto.get(0).equals("posX")){
                posX = Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("posY")){
                posY = Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("posZ")){
                posZ = Float.parseFloat(objto.get(1).toString());
            }else {   
                ventana= this.ventana((ArrayList)objto.get(1));
            }
            System.out.println(objto.get(0)+":"+objto.get(1));
        }
        
        if (orientacion.equals("ejeX")){
            rotateWall.rotX(0);
        }else if (orientacion.equals("ejeY")){
            rotateWall.rotY(-1.55f);
        }else if (orientacion.equals("ejeZ")){
            rotateWall.rotZ(-1.55f);
        }else{
            rotateWall.rotX(0);
        }
       
        Appearance aT =block;
        
        if (texture.equals("ladrillo")){
            aT = ladrillo;
        }else if (texture.equals("piedra")){
            aT = piedra;
        }else if (texture.equals("block")){
            aT = block;
        }
                
        wall= new Box(largo, alto, grosor, Primitive.GENERATE_TEXTURE_COORDS, aT);
        
        posWall =  new  Vector3f (posX, posY, posZ);
        t3d.set(posWall);
        rotateWall.mul(t3d);
        t3d.setTranslation(posWall);
        modelo.setTransform(rotateWall);
        modelo.addChild(wall);
        modelo.addChild(ventana);
        
        return modelo;
    }
    public TransformGroup ventana(ArrayList arr){
        float largo=0;
        float alto=0;
        float grosor =0.01f;
        String tipo="normal";
        String orientacion = "ejeY";
        float posX=0, posY=0, posZ=0;      

        Appearance vidrio = textu.textura(getClass().getResource("/Imagenes/window/ventana.png"));
        Appearance madera = textu.textura(getClass().getResource("/Imagenes/window/madera.jpg"));
        Appearance polarizado = textu.textura(getClass().getResource("/Imagenes/window/polarizado.jpg"));
  
        Box window;
        TransformGroup modelo = new TransformGroup(); //groupVentana
        Transform3D  t3d = new Transform3D(); //transformarVentana
        Transform3D rotateWindow = new Transform3D(); //rotarVentana
        Vector3f pW; //posisionVentana
    
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
            }else if (objto.get(0).equals("posX")){
                posX=Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("posY")){
                posY=Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("posZ")){
                posZ=Float.parseFloat(objto.get(1).toString());
            }
        }
        
        Appearance aT=vidrio;
           
        if (tipo.equals("normal")){
            aT = vidrio;
        }else if (tipo.equals("madera")){
            aT = madera;
        }else if (tipo.equals("polarizado")){
            aT = polarizado;
        }
        
        if (orientacion.equals("ejeX")){
            rotateWindow.rotX(0);
        }else if (orientacion.equals("ejeY")){
            rotateWindow.rotY(-1.55f);
        }else if (orientacion.equals("ejeZ")){
            rotateWindow.rotZ(-1.55f);
        }else{
            rotateWindow.rotX(0);
        }
    
        window = new Box (largo, alto, grosor, Primitive.GENERATE_TEXTURE_COORDS, aT);
    
        pW =  new  Vector3f (posX, posY, posZ);
        t3d.set(pW);
        rotateWindow.mul(t3d);
        t3d.setTranslation(pW);
        modelo.setTransform(rotateWindow);
        modelo.addChild(window);
        
        
        return modelo;
    }
}
