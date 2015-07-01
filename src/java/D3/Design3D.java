/**
 * @author Christian Sum 
 * Correo   :   Christian.osum@gmail.com
 * Carné    :   3190-09-02
 * Clase    :   Compiladores
 * Semestre :   5to
 * Catedratico  :   Ing. Elder Prado
 * Universidad Mariano Gálvez 
 * Guatemala 2015
 */
package D3;

import javax.vecmath.*;
import javax.media.j3d.*;
import java.util.Map;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.loaders.Loader;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import java.awt.*;
import java.io.FileReader;
import java.util.*;
import java.lang.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GraphicsConfiguration;
import java.io.*;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3f;


public class Design3D {
    private final TransformGroup plano_base;
    private final TransformGroup objetos;

    public Design3D() {
        this.objetos = new TransformGroup();
        this.plano_base = new TransformGroup();
    }
    
    /*  -- Clase principal --
     *  Genera la ventana de la grafica, agregando los elementos que se transfieren 
     *  por la variable obj, utilizando la funcion 
     */
    public void Design3D(ArrayList obj)throws Exception{
        /* Crea el universo */
        SimpleUniverse universe = new SimpleUniverse();
        universe.getViewingPlatform().setNominalViewingTransform();
        
        /*  Crea el grupo donde se agregarán los elementos principales
         *  y el escenario donde serán agregados los elementos.
         */
        BranchGroup group = new BranchGroup();
        TransformGroup escenarioCentral = null;

        universe.getViewingPlatform().setNominalViewingTransform();
        /* - Se obtienen los elementos - */
        recursiveArr(obj,0); // Se ejecuta la funcion para recorrer el ArrayList
        TransformGroup an_sin = objetos; //Se inserta variable global de todos los objetos
        escenarioCentral=plano_base; //Se inserta variable global para el Plano

        escenarioCentral.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        escenarioCentral.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        
        /* Configuracion para utilizar el mouse y mover el escenario */

        KeyNavigatorBehavior key = new KeyNavigatorBehavior( escenarioCentral );
        key.setSchedulingBounds( new BoundingSphere());
        key.setEnable( true );
        group.addChild( key );
        
        /* - Se agregan los elementos obtenidos - */
        /* - an_sin = Analizador Sintactico - */
        
        Transform3D e3d = new Transform3D();
        Vector3d v =  new  Vector3d (0f, 0f, +2.0f);
        e3d.set(v);
        escenarioCentral.setTransform(e3d);
               
        
        escenarioCentral.addChild(an_sin);
        group.addChild(escenarioCentral);
        universe.addBranchGraph(group);
    }
   
    /*
    * Metodo para evaluar los valores que esta recorriendo el metodo recursiveArr()
    * @param name Obtiene el nombre asignado al objeto
    * @param arr contiene el ArrayList asignado al name
    * @param ob 0 = Analiza objetos de configuracion para el universo, 1 = Analiza objetos de diseño
    */
    private void ListOpc(Object name, Object arr, Integer ob){
        Columna col = new Columna();
        Pared wall = new Pared();
        Puerta door = new Puerta();
        Techo ceiling = new Techo();
        Arbol tree = new Arbol();
        Persona person = new Persona();
        Vista view = new Vista();
        Plano map = new Plano();
        Camara cam = new Camara();
        
        if (name.equals("proyecto")){ // Confirma si es el nombre del proyecto - [proyecto, nombre_del_proyecto]
        }else if (name.equals("camara")){ // Confirma si es la camara donde se visualiza el plano - [camara, [ArrayList_objetos_camara]]
            objetos.addChild(cam.camara((ArrayList)arr)); //Agrega elemento a los objetos
        }else if (name.equals("plano")){ // Confirma si es el plano  - [plano,[dimX,numero],[dimY,numero],[objetos,[ArrayList_objetos_diseño]]]
            /*
            * De todos los elementos que tenga Plano evalua si existe uno que se llame "Objetos" y lo ingresa a recursividad
            */
            ArrayList OpcPla = (ArrayList)arr;
            Iterator ite = OpcPla.iterator();
            while(ite.hasNext()){              
                ArrayList obPla = (ArrayList)ite.next();
                if (obPla.get(0).equals("objetos")){ 
                    recursiveArr((ArrayList)obPla.get(1), 1);
                    //System.out.println(obPla.get(1));
                }
            }
            objetos.addChild(map.plano((ArrayList)arr));
            
        }else if (name.equals("objetos")){ 
            /* Sirve para que comienzce a analizar objeto por objeto
            * Se envia ob=1 para que evalue objetos ahora
            */
     
        }else if(ob==1){ //Sin importar que arr tenga, evalua si es ob=1 para analizar objetos de diseño
            ArrayList OpcObj = (ArrayList)arr;
            Iterator ite = OpcObj.iterator();
            while(ite.hasNext()){              
                ArrayList objto = (ArrayList)ite.next();          
                if (objto.get(1).equals("columna")){ // Evalua si es camara para crear elemento Columna
                    objetos.addChild(col.columna((ArrayList)arr)); 
                }else if (objto.get(1).equals("pared")){ //Evalua si es pared para crear elemento Pared
                    objetos.addChild(wall.pared((ArrayList)arr));
                }else if (objto.get(1).equals("puerta")){ //Evalua si es techo para crear elemento Techo
                    objetos.addChild(door.puerta((ArrayList)arr));
                }else if (objto.get(1).equals("techo")){ //Evalua si es techo para crear elemento Techo
                    //objetos.addChild(ceiling.techo((ArrayList)arr));
                }else if (objto.get(1).equals("arbol")){ //Evalua si es arbol para crear elemento Arbol
                   // objetos.addChild(tree.arbol((ArrayList)arr));
                }else if (objto.get(1).equals("persona")){ //Evalua si es persona para crear elemento Persona
                    objetos.addChild(person.persona((ArrayList)arr));
                }else if (objto.get(1).equals("vista")){ //Evalua si es vista para crear configuracion de Vista
                    //objetos.addChild(view.vista((ArrayList)arr));
                }
                
            }
        }else{
            System.out.println(arr); //Error Sintactico
        }
    }
    
    /*
    * Metodo para recursion de ArrayLists
    * @param arr Es el ArrayLisst que contiene el elemento a recorrer
    * @param ob 0 = Analiza objetos de configuracion para el universo, 1 = Analiza objetos de diseño
    */
    public void recursiveArr(ArrayList arr, Integer ob){
        Iterator ite = arr.iterator();
        while(ite.hasNext()){
            ArrayList objto = (ArrayList)ite.next();
            if(objto.get(0) instanceof String){ // Lee elemtos de un objeto
                ListOpc(objto.get(0),objto.get(1),ob);
            }else if(objto.get(0) instanceof Object){ //Lee objetos hijos de objetos parde y re-analiza
                recursiveArr((ArrayList)objto,ob); 
            }
        }
    }    
}
