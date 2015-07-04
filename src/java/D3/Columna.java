/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package D3;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
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
public class Columna {
    private ArrayList errores = new ArrayList();
    
    public TransformGroup columna(ArrayList arr){
        float alto=0;
        float ancho =0.1f;
        String tipo="";
        String forma="";
        float posX=0, posY=0, posZ=0;
        Boolean altoB=false, anchoB=false, tipoB=false, formaB=false,
                        posXB=false,posYB=false, posZB=false;
        
        
        Textura textu= new Textura();
        Appearance blanco = textu.textura(getClass().getResource("/img/blanco.jpg"));
    
        Box cC = new Box();// Columna Cuadrada
        Cylinder cR = new Cylinder(); //Columna Redonda
        Vector3f pC ; // Posicion Columna
         
        Transform3D t3d = new Transform3D();
        TransformGroup modelo = new TransformGroup(t3d);

        Iterator ite = arr.iterator();
        while(ite.hasNext()){
            ArrayList objto = (ArrayList)ite.next();
            //System.out.println(objto);
            if (objto.get(0).equals("alto")){
                altoB=true;
                alto=Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("forma")){
                formaB=true;
                forma=objto.get(1).toString();
            }else if (objto.get(0).equals("posX")){
                posXB=true;
                posX=Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("posY")){
                posYB=true;
                posY=Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("posZ")){
                posZB=true;
                posZ=Float.parseFloat(objto.get(1).toString());
            }else if(altoB==false && anchoB==false && tipoB==false && formaB==false && posXB==false && posYB==false && posZB==false){
                    setErrors("Alerta: Lista de propiedades vacia.");
            }else{
                setErrors("Alerta: No se reconoce la propiedad: '"+objto.get(0)+"' Valor: "+objto); //Error Sintactico
            }
        }
        
        //System.out.println(forma);
        if (forma.equals("cuadrada")){
            cC = new Box (0.1f, alto, 0.1f, Primitive.GENERATE_TEXTURE_COORDS, blanco);
            modelo.addChild(cC);
        }else if(forma.equals("redonda")){
            cR =new Cylinder(0.2f, alto, Primitive.GENERATE_TEXTURE_COORDS, blanco);
            modelo.addChild(cR);
        }else{
            cC = new Box (0.1f, alto, 0.1f, Primitive.GENERATE_TEXTURE_COORDS, blanco);
            modelo.addChild(cC);
        }
        pC = new Vector3f(posX, posY, posZ);
        t3d.setTranslation(pC);
        modelo.setTransform(t3d);       
        return modelo;
    }
    public ArrayList getError(){
        return errores;
    }
    public void setErrors(String e){
        errores.add(e);
    }
}
