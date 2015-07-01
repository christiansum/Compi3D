/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package D3;

import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Container;
import javax.media.j3d.Appearance;

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
