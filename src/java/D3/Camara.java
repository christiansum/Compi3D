/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package D3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
public class Camara {
    private ArrayList errores = new ArrayList();        
    
   
        
    
    public Vector3d base(ArrayList arr){
        double baseX=0f, baseY=-15f, baseZ=-120f; // Distancia y posicin fija
         Boolean baseXB=false, baseYB=false, baseZB=false; 
        Iterator ite = arr.iterator();
        while(ite.hasNext()){
            ArrayList objto = (ArrayList)ite.next();
            //System.out.println(objto);
            if (objto.get(0).equals("baseX")){
                baseXB=true;
                baseX=Double.parseDouble(objto.get(1).toString());
            }else if (objto.get(0).equals("baseY")){
                baseYB=true;
                baseY=Double.parseDouble(objto.get(1).toString());
            }else if (objto.get(0).equals("baseZ")){
                baseZB=true;
                baseZ=Double.parseDouble(objto.get(1).toString());
            }else {
              setErrors("Alerta: No se reconoce la propiedad: '"+objto.get(0)+"' Valor: "+objto); //Error Sintactico
            }
        }
        if(baseXB==false && baseYB==false && baseZB==false){
                    setErrors("Alerta: Lista de propiedades vacia. CAMARA BASE");
            }
        
        Vector3d v =  new  Vector3d (baseX, baseY, baseZ);
        //System.out.println(baseX+" | "+baseY+" | "+baseZ);
        return v;
    }
    
    public Point3d ojo(ArrayList arr){
        double ojoX=0f, ojoY=0f, ojoZ=10f;//Para rotacion u ojo
         Boolean ojoXB=false, ojoYB=false, ojoZB=false;//Para rotacion u ojo
        
        Iterator ite = arr.iterator();
        while(ite.hasNext()){
            ArrayList objto = (ArrayList)ite.next();
            
            if (objto.get(0).equals("ojoX")){
                ojoXB=true;
                ojoX=Double.parseDouble(objto.get(1).toString());
            }else if (objto.get(0).equals("ojoY")){
                ojoYB=true;
                ojoY=Double.parseDouble(objto.get(1).toString());
            }else if (objto.get(0).equals("ojoZ")){
                ojoZB=true;
                ojoZ=Double.parseDouble(objto.get(1).toString());
            }else {
               setErrors("Alerta: No se reconoce la propiedad: '"+objto.get(0)+"' Valor: "+objto); //Error Sintactico
            }
        }
        if(ojoXB==false && ojoYB==false && ojoZB==false){
                   setErrors("Alerta: Lista de propiedades vacia. CAMARA OJO");
            }
        
       // t3d.lookAt; // front
        //System.out.println(ojoX+" | "+ojoY+" | "+ojoZ);
        return new Point3d(ojoX,ojoY,ojoZ);
        
    }
    public ArrayList getError(){
        return errores;
    }
    public void setErrors(String e){
        errores.add(e);
    }
    
}
