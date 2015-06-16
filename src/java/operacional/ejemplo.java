/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacional;
import java.io.FileReader;
import java.io.IOException;
import com.sun.j3d.loaders.Scene; // Contains the object loaded from disk.
import com.sun.j3d.loaders.objectfile.ObjectFile; // Loader of .obj models

/**
 *
 * @author Christian.Sum
 */
public class ejemplo {
  public static Scene loadScene(String location) throws IOException {
    
    ObjectFile loader = new ObjectFile(ObjectFile.RESIZE); 
    return loader.load(new FileReader(location)); 
}
   }
