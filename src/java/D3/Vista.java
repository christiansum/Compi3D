/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package D3;

import java.util.ArrayList;
import java.util.Iterator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

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
public class Vista {
    private ArrayList errores = new ArrayList();
    
    
    public Point3d vista(ArrayList arr){
        String view = "";
        Point3d p = new Point3d();
        Boolean camaraB=false;
        Iterator ite = arr.iterator();
        while(ite.hasNext()){
            ArrayList objto = (ArrayList)ite.next();
            //System.out.println(objto);
            if (objto.get(0).equals("camara")){
                camaraB=true;
                view=(String)objto.get(1);
            }else {
                setErrors("Alerta: No se reconoce la propiedad: '"+objto.get(0)+"' Valor: "+objto); //Error Sintactico
            }
        }
        if(camaraB==false){
                    setErrors("Alerta: Lista de propiedades vacia VISTA.");
            }
        if(view.equals("frontal")){
            p = new Point3d(20f,10f,10f); // front
        }else if(view.equals("lateralDerecha")){
            p = new Point3d(30f,10f,0f); // west
        }else if(view.equals("lateralIzquierda")){
            p = new Point3d(-40f,10f,0f); // east
        }else if(view.equals("aerea")){
            p = new Point3d(20f,20f,1f); // aerea
        }else if(view.equals("atras")){
            p = new Point3d(20f,10f,-10f); //back 
        }else{
            p = new Point3d(20f,10f,10f); // front
        }
        return p;
    }
    public ArrayList getError(){
        return errores;
    }
    public void setErrors(String e){
        errores.add(e);
    }
}
