/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package D3;

import java.util.ArrayList;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

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
    public TransformGroup camara(ArrayList arr){
        double dimX=0;
        double dimY=0;
        Transform3D t3d = new Transform3D();
        TransformGroup modelo = new TransformGroup(t3d);
        return modelo;
    }
    
}
