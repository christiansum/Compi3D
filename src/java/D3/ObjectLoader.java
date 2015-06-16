package D3;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;
import java.awt.GraphicsConfiguration;
import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.objectfile.*;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * !Diese Klasse wurde f�r das Laden �ber ein JAR-Archiv
 * oder Applet welches ein JAR - Archiv nutzt angepasst
 * Um das Programm als einfache Applikation �ber einen class-File
 * laufen zu lassen bitte auf den Code zum Einladen der OBJ Datei
 * im Tutorial zur�ckgreifen! 
 */
public class ObjectLoader extends Applet{

	/**
	 * init Methoden f�r die Darstellung als Applet
	 */
	public void init(){
		setLayout(new BorderLayout());
		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
		canvas3D = new Canvas3D( config );
		add("Center", canvas3D);
		BranchGroup szene = macheSzene();
		szene.compile();
		universe = new SimpleUniverse( canvas3D );
		universe.getViewingPlatform().setNominalViewingTransform();
		universe.addBranchGraph(szene);
	}

		/**
	 * Erstellt den Szenegraphen
	 * @return BranchGroup
	 */
	public BranchGroup macheSzene(){
		BranchGroup objWurzel = new BranchGroup();
		// Transformation, 2 Rotationen:
		Transform3D drehung = new Transform3D();
		Transform3D drehung2 = new Transform3D();
		drehung.rotX(Math.PI / 4.0d);
		drehung2.rotY(Math.PI / 5.0d);
		drehung.mul(drehung2);
		TransformGroup objDreh =
						new TransformGroup(drehung);
		//Loader
		ObjectFile file = new ObjectFile (ObjectFile.RESIZE);
		Scene scene = null;
		try {
			// Applet Abfrage
			
			 scene = file.load("teapot.obj");
			
		}
		catch (FileNotFoundException e) {
			System.err.println(e);
			System.exit(1);
		}
		catch (ParsingErrorException e) {
			System.err.println(e);
			System.exit(1);
		}
		catch (IncorrectFormatException e) {
			System.err.println(e);
			System.exit(1);
		}
		objDreh.addChild(scene.getSceneGroup());
		
		DirectionalLight d_Licht = new DirectionalLight(
						 new Color3f (1.0f, 0.5f, 0.3f), new Vector3f (-1.0f, -1.0f, -1.0f));
		d_Licht.setInfluencingBounds (new BoundingSphere (new Point3d(0.0d,0.0d,0.0d), 100.0d));
//		d_Licht.setColor(new Color3f(1.0f,0.5f,0.3f));
		objDreh.addChild(d_Licht);

		
		objWurzel.addChild(objDreh);
		return objWurzel;
	}

	/**
	 * gibt speicher frei
	 */
	public void destroy(){
		universe.removeAllLocales();
	}

	public static void main(String[] args) {
		frame = new MainFrame(new ObjectLoader(),500,500);
		frame.setTitle("ObjectLoader");
	}

	//---- Attribute -----------------------
	private SimpleUniverse universe;
	private Canvas3D canvas3D;
	private static Frame frame;
}