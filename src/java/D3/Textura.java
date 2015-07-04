/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package D3;

import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Container;
import java.util.ArrayList;
import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.PolygonAttributes;
import javax.vecmath.Color3f;

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
public class Textura {
    private ArrayList errores = new ArrayList();
    
    Appearance textura(java.net.URL path){
        try{
            Appearance apariencia = new Appearance();
            apariencia.setCapability(Appearance.ALLOW_MATERIAL_WRITE);

            TextureLoader tex = new TextureLoader(path,
                new String("RGB"),
                TextureLoader.BY_REFERENCE | TextureLoader.Y_UP,
                new Container());
            apariencia.setTexture(tex.getTexture());
            return apariencia;
        }catch (Exception e){
            Appearance ap = new Appearance();

            //create a colored material
            Color3f aColor = new Color3f(0.1f, 0.1f, 0.1f);
            Color3f eColor = new Color3f(0.0f, 0.0f, 0.0f);
            Color3f dColor = new Color3f(0.8f, 0.8f, 0.8f);
            Color3f sColor = new Color3f(1.0f, 1.0f, 1.0f);
            Material m = new Material(aColor, eColor, dColor, sColor, 80.0f);

            //enable lighting and assign material
            m.setLightingEnable(true);
            ap.setMaterial(m);

            //render the Box as a wire frame
            PolygonAttributes polyAttrbutes = new PolygonAttributes();
            polyAttrbutes.setPolygonMode( PolygonAttributes.POLYGON_LINE );
            polyAttrbutes.setCullFace(PolygonAttributes.CULL_NONE);
            ap.setPolygonAttributes(polyAttrbutes);
            return ap;
        }
    }
    public ArrayList getError(){
        return errores;
    }
    public void setErrors(String e){
        errores.add(e);
    }
}
