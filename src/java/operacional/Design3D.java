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
package operacional;

import javax.vecmath.*;
import javax.media.j3d.*;
import java.util.Map;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.loaders.Loader;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
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


public class Design3D {
    private TransformGroup plano_base = new TransformGroup();
    
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
        TransformGroup an_sin = getArrElement(obj);
        escenarioCentral=plano_base;

        escenarioCentral.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        escenarioCentral.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        
        /* Configuracion para utilizar el mouse y mover el escenario */
        MouseRotate myMouseRotate = new MouseRotate();
        myMouseRotate.setTransformGroup(escenarioCentral);
        myMouseRotate.setSchedulingBounds(new BoundingSphere());
        group.addChild(myMouseRotate);

        MouseTranslate myMouseTranslate = new MouseTranslate();
        myMouseTranslate.setTransformGroup(escenarioCentral);
        myMouseTranslate.setSchedulingBounds(new BoundingSphere());
        group.addChild(myMouseTranslate);

        MouseZoom myMouseZoom = new MouseZoom();
        myMouseZoom.setTransformGroup(escenarioCentral);
        myMouseZoom.setSchedulingBounds(new BoundingSphere());
        group.addChild(myMouseZoom);  
        
        /* - Se agregan los elementos obtenidos - */
        /* - an_sin = Analizador Sintactico - */
        escenarioCentral.addChild(an_sin);
        group.addChild(escenarioCentral);
        universe.addBranchGraph(group);
    }
   
    
    private Boolean ListOpc(String name){
        if (name.equals("proyecto")){
            
        }else if (name.equals("camara")){
            
        }else if (name.equals("plano")){
            
        }else if (name.equals("objetos")){
            
        }else if (name.equals("columna")){
            
        }else if (name.equals("pared")){
            
        }else if (name.equals("techo")){
            
        }else if (name.equals("arbol")){
            
        }else if (name.equals("persona")){
            
        }else if (name.equals("vista")){
            
        }
        
    }
    
    
    
    public TransformGroup getArrElement(ArrayList obj) throws Exception{
        TransformGroup objetos = new TransformGroup();
        Iterator ite = obj.iterator();
        while(ite.hasNext()){
            ArrayList objto = (ArrayList)ite.next();
            if (objto.get(0).equals("proyecto")){
                //funcion
            }else if(objto.get(0) instanceof ArrayList){
                Iterator lh = objto.iterator();
                while (lh.hasNext()){
                    ArrayList hijo = (ArrayList)lh.next();
                    if (hijo.get(0).equals("camara")){
                        objetos.addChild(camara((ArrayList)hijo.get(1)));
                        //System.out.println(hijo.get(1));
                    }else if (hijo.get(0).equals("plano")){
                        //objetos.addChild(plano((ArrayList)hijo.get(1)));
                        plano((ArrayList)hijo.get(1));
                        //System.out.println(hijo.get(1));
                        
                        if(hijo.get(1) instanceof ArrayList){
                            ArrayList hijoP1 =(ArrayList)hijo.get(1);
                            Iterator lh1 = hijoP1.iterator();
                            while (lh1.hasNext()){
                                ArrayList hijo1 = (ArrayList)lh1.next();
                                if(hijo1.get(0).equals("objetos")){
                                    if(hijo1.get(1) instanceof ArrayList){
                                        ArrayList hijoP2 =(ArrayList)hijo1.get(1);
                                        Iterator lh2 = hijoP2.iterator();
                                        while (lh2.hasNext()){
                                            ArrayList hijo2 = (ArrayList)lh2.next();
                                            objetos.addChild(objetos((ArrayList)hijo2.get(1)));
                                        }
                                    }
                                }
                            }
                        }
                    }  
                }
            }
        }
        return objetos;
    }  
   
    public TransformGroup objetos(ArrayList arr){
        TransformGroup objetos = new TransformGroup();
        Iterator ite = arr.iterator();
        while(ite.hasNext()){
            ArrayList objto = (ArrayList)ite.next();
            //System.out.println(objto);
            if (objto.get(1).equals("columna")){
                //System.out.println(arr);
                objetos.addChild(columna(arr));
            }else if (objto.get(0).equals("pared")){
                //objetos.addChild(pared(arr));
            }else if (objto.get(0).equals("techo")){
                //objetos.addChild(techo(arr));
            }else if (objto.get(0).equals("arbol")){
                //objetos.addChild(arbol(arr));
            }else if (objto.get(0).equals("persona")){
                //objetos.addChild(persona(arr));
            }else if (objto.get(0).equals("vista")){
                //objetos.addChild(vista(arr));
            }
        }
        return objetos;
    }
    
    public TransformGroup camara(ArrayList arr){
        double dimX=0;
        double dimY=0;
        Transform3D t3d = new Transform3D();
        TransformGroup modelo = new TransformGroup(t3d);
        return modelo;
    }
    
    public void plano(ArrayList arr){
        float dimX=20;
        //System.out.println("Si entrooo");
        float dimY=20;
        Appearance grama= textura(getClass().getResource("/img/grama.jpg"));
        
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
                System.out.println(dimY);
            }
        }
    //elderprado9@gmail.com
        plano= new Box(dimX, 0.01f, dimY, Primitive.GENERATE_TEXTURE_COORDS, grama);
        posP =  new  Vector3f(0f,-0.9f,0f); 
        transP . setTranslation ( posP ); 
        modelo . setTransform ( transP ); 
        modelo . addChild ( plano );
        plano_base=modelo;
        
    }
    
    public TransformGroup columna(ArrayList arr){
        float alto=0;
        float ancho =0.1f;
        String tipo="";
        String forma="";
        float posX=0, posY=0, posZ=0;
    
        Appearance blanco= textura(getClass().getResource("/img/blanco.jpg"));
    
         Box cC = new Box();// Columna Cuadrada
         Cylinder cR = new Cylinder(); //Columna Redonda
         Vector3f pC ; // Posicion Columna
         
        Transform3D t3d = new Transform3D();
        TransformGroup modelo = new TransformGroup(t3d);
        
        Iterator ite = arr.iterator();
        while(ite.hasNext()){
            ArrayList objto = (ArrayList)ite.next();
            ///System.out.println(objto);
            if (objto.get(0).equals("tipo")){
                tipo=objto.get(1).toString();
            }else if (objto.get(0).equals("alto")){
                alto=Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("forma")){
                forma=objto.get(1).toString();
            }else if (objto.get(0).equals("posX")){
                posX=Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("posY")){
                posY=Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("posZ")){
                posZ=Float.parseFloat(objto.get(1).toString());
            }
        }
        
        if (forma.equals("cuadrada")){
            cC = new Box (0.1f, alto, 0.1f, Primitive.GENERATE_TEXTURE_COORDS, blanco);
        }else if(forma.equals("redonda")){
            cR =new Cylinder(ancho, alto+1, Primitive.GENERATE_TEXTURE_COORDS, blanco);
        }else{
        }
        pC = new Vector3f(posX, posY, posZ);
        t3d.setTranslation(pC);
        modelo.setTransform(t3d);
        modelo.addChild(cC);
        modelo.addChild(cR);

        
        return modelo;
    }
    
    public TransformGroup pared(ArrayList arr){
 
        float largo= 0;
        float alto=0;
        float grosor =0.1f;    
        String orientacion="ejeX";
        String texture="block";
        float posX=0, posY=0, posZ=0;
        
        Appearance ladrillo= textura(getClass().getResource("/img/pared/ladrillos.jpg"));
        Appearance piedra= textura(getClass().getResource("/img/pared/piedra.jpg"));
        Appearance block= textura(getClass().getResource("/img/pared/block.jpg"));
        
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
                alto = Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("orientacion")){
                orientacion=objto.get(1).toString();
            }else if (objto.get(0).equals("textura")){
                texture=objto.get(1).toString();
            }else if (objto.get(0).equals("posX")){
                alto = Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("posY")){
                alto = Float.parseFloat(objto.get(1).toString());
            }else if (objto.get(0).equals("posZ")){
                alto = Float.parseFloat(objto.get(1).toString());
            }else {   
                ventana= this.ventana((ArrayList)objto.get(1));
            }
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

        Appearance vidrio= textura(getClass().getResource("/Imagenes/window/ventana.png"));
        Appearance madera= textura(getClass().getResource("/Imagenes/window/madera.jpg"));
        Appearance polarizado= textura(getClass().getResource("/Imagenes/window/polarizado.jpg"));
  
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
    
    public TransformGroup puerta(ArrayList arr){
        float alto=0;
        float largo=0; 
        float grosor=0.03f;
        String tipo="";
        String orientacion="ejeX";
        String texture="madera";
        float posX=0, posY=0, posZ=0; 
    
        Appearance blanco= textura(getClass().getResource("/img/blanco.jpg"));
        Appearance madera= textura(getClass().getResource("/img/madera.jpg"));
        Appearance metal= textura(getClass().getResource("/img/metal.jpg"));     
    
    
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
    
    public TransformGroup techo(ArrayList arr){
        float ancho=0;
        float largo=0;
        float alto= 0.1f;
        String forma="plano";
        String orientacion="ejeY";
        float posX=0,posY=0,posZ=0;
        
        Appearance teja= textura(getClass().getResource("/img/teja.jpg"));
        Appearance cemento= textura(getClass().getResource("/img/cemento.jpg"));
        Appearance metal= textura(getClass().getResource("/img/metal.jpg"));
    
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
    
    public TransformGroup arbol(ArrayList arr){
        Transform3D t3d = new Transform3D();
        TransformGroup modelo = new TransformGroup(t3d);
        Iterator ite = arr.iterator();
        while(ite.hasNext()){
            ArrayList objto = (ArrayList)ite.next();
            //System.out.println(objto);
            if (objto.get(1).equals("columna")){}
        }
        return modelo;
    }
    
    public TransformGroup persona(ArrayList arr){
        Transform3D t3d = new Transform3D();
        TransformGroup modelo = new TransformGroup(t3d);
        Iterator ite = arr.iterator();
        while(ite.hasNext()){
            ArrayList objto = (ArrayList)ite.next();
            //System.out.println(objto);
            if (objto.get(1).equals("columna")){}
        }
        return modelo;
    }
    
    public TransformGroup vista(ArrayList arr){
        Transform3D t3d = new Transform3D();
        TransformGroup modelo = new TransformGroup(t3d);
        Iterator ite = arr.iterator();
        while(ite.hasNext()){
            ArrayList objto = (ArrayList)ite.next();
            //System.out.println(objto);
            if (objto.get(1).equals("columna")){}
        }
           
        return modelo;
    }
    
    Appearance textura(java.net.URL path){
            Appearance apariencia = new Appearance();
            apariencia.setCapability(Appearance.ALLOW_MATERIAL_WRITE);
     
            TextureLoader tex = new TextureLoader(path,
                new String("RGB"),
                TextureLoader.BY_REFERENCE | TextureLoader.Y_UP,
                new Container());

        apariencia.setTexture(tex.getTexture());
        return apariencia;
    }
}
