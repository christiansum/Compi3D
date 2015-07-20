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
    private Point3d p = new Point3d(0f,0f,10f) ;
    private Point3d p1 = new Point3d(0f,40f,10f) ;
    private Vector3d v = new Vector3d(0f,-15f,-120f);
    private ArrayList errores = new ArrayList();
    
    private Columna col = new Columna();
    private Pared wall = new Pared();
    private Puerta door = new Puerta();
    private Techo ceiling = new Techo();
    private Arbol tree = new Arbol();
    private Persona person = new Persona();
    private Vista view = new Vista();
    private Plano map = new Plano();
    private Camara cam = new Camara();

    private Boolean proyectoB=false, planoB=false,camaraB=false,objetosB=false, 
                    paredB=false, columnaB=false,puertaB=false, techoB=false,
                    arbolB=false,vistaB=false,personaB=false;
    
    public Design3D() {
        this.objetos = new TransformGroup();
        this.plano_base = new TransformGroup();

    }
    
    /*  -- Clase principal --
     *  Genera la ventana de la grafica, agregando los elementos que se transfieren 
     *  por la variable obj, utilizando la funcion 
     */
    public void Design3D(ArrayList obj)throws Exception{
        try{
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
            escenarioCentral = plano_base; //Se inserta variable global para el Plano

            escenarioCentral.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            escenarioCentral.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

            /* Configuracion para utilizar el mouse y mover el escenario */

            KeyNavigatorBehavior key = new KeyNavigatorBehavior( escenarioCentral );
            key.setSchedulingBounds( new BoundingSphere());
            key.setEnable( true );
            group.addChild( key );

            /* - Se agregan los elementos obtenidos - */

            Transform3D e3d = new Transform3D();
            /*
            * No tiene nada de logica, pero las siguientes lineas no funcionan:
            * e3d.setTranslation(v);
            * e3d.setTranslation((Vector3d)v);
            * e3d.setTranslation(new Vector3d(v.getX,v.getY,v.getZ));
            * a pesar de que los resultados es un Vector3d y los otros son doubles, no funcionaba hasta que lo dejé asi:
            */
            e3d.setTranslation(new Vector3d(Double.parseDouble(String.valueOf(v.getX()).toString()),Double.parseDouble(String.valueOf(v.getY()).toString()),Double.parseDouble(String.valueOf(v.getZ()).toString())));

            e3d.lookAt(new Point3d(p.getX(),p.getY(),p.getZ()),
                    new Point3d(0f,0f,0f),new Vector3d(0f,1f,0f));
            //*/

            escenarioCentral.setTransform(e3d);
            /* - Se configura la profundidad de todo el universo - */
            universe.getViewer().getView().setBackClipDistance(1000);

            /* - an_sin = Analizador Sintactico - */
            escenarioCentral.addChild(an_sin);
            group.addChild(escenarioCentral);
            universe.addBranchGraph(group);
    
        }catch (Exception e){
            setErrors("Critico: No fue posible generar el universo: Method Desig3D"+e);
        }
    }
    /*
    * Metodo para evaluar los valores que esta recorriendo el metodo recursiveArr()
    * @param name Obtiene el nombre asignado al objeto
    * @param arr contiene el ArrayList asignado al name
    * @param ob 0 = Analiza objetos de configuracion para el universo, 1 = Analiza objetos de diseño
    */
    private void ListOpc(Object name, Object arr, Integer ob){
        
        
        if (name.equals("proyecto")){ // Confirma si es el nombre del proyecto - [proyecto, nombre_del_proyecto]
            proyectoB=true;
        }else if (name.equals("camara")){ // Confirma si es la camara donde se visualiza el plano - [camara, [ArrayList_objetos_camara]]
            camaraB=true;
            v=cam.base((ArrayList)arr); //Genera la base donde se visualiza el objeto
            p1=cam.ojo((ArrayList)arr); //genera el ojo
            setErrors(cam.getError());
        }else if (name.equals("plano")){ // Confirma si es el plano  - [plano,[dimX,numero],[dimY,numero],[objetos,[ArrayList_objetos_diseño]]]
            planoB=true;
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
            setErrors(map.getError());
            
        }else if(ob==1){ //Sin importar que arr tenga, evalua si es ob=1 para analizar objetos de diseño
            objetosB=true;
            ArrayList OpcObj = (ArrayList)arr;
            Iterator ite = OpcObj.iterator();
            while(ite.hasNext()){              
                ArrayList objto = (ArrayList)ite.next();          
                if (objto.get(1).equals("columna")){ // Evalua si es camara para crear elemento Columna
                    columnaB=true;
                    objetos.addChild(col.columna((ArrayList)arr)); 
                    setErrors(col.getError());
                }else if (objto.get(1).equals("pared")){ //Evalua si es pared para crear elemento Pared
                    paredB=true;
                    objetos.addChild(wall.pared((ArrayList)arr));
                    setErrors(wall.getError());
                }else if (objto.get(1).equals("puerta")){ //Evalua si es techo para crear elemento Techo
                    puertaB=true;
                    objetos.addChild(door.puerta((ArrayList)arr));
                    setErrors(door.getError());
                }else if (objto.get(1).equals("techo")){ //Evalua si es techo para crear elemento Techo
                    techoB=true;
                    objetos.addChild(ceiling.techo((ArrayList)arr));
                    setErrors(ceiling.getError());
                }else if (objto.get(1).equals("arbol")){ //Evalua si es arbol para crear elemento Arbol
                    arbolB=true;
                    objetos.addChild(tree.arbol((ArrayList)arr));
                    setErrors(person.getError());
                }else if (objto.get(1).equals("persona")){ //Evalua si es persona para crear elemento Persona
                    personaB=true;
                    objetos.addChild(person.persona((ArrayList)arr));
                    setErrors(person.getError());
                }else if (objto.get(1).equals("vista")){ //Evalua si es vista para crear configuracion de Vista
                    vistaB=true;
                    p = view.vista((ArrayList)arr);
                    setErrors(view.getError());
                }else {
                    //setErrors("Alerta: No se reconoce la propiedad: '"+objto.get(1)+"' Valor: "+objto); //Error Sintactico
                }
                
            }
            
        }else if(proyectoB==false || camaraB==false || planoB==false || objetosB==false){
            setErrors("Critico: No Existen los elementos 'proyecto', 'camara' y 'plano'. Por favor verifique el texto ingresado."+arr); //Error Sintactico
        }else if(columnaB==false && paredB==false && puertaB==false && techoB==false && arbolB==false && personaB==false && vistaB==false){
                    setErrors("Alerta: Lista de objetos vacia.");
                }else{
           // setErrors("Alerta: No se reconoce la propiedad: '"+name.toString()+"' Valor: "+arr); //Error Sintactico
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
    
    public ArrayList getError(){
        return errores;
    }
    public void setErrors(Object e){
        errores.add(e);
    }
}
