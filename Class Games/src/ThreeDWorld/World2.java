package ThreeDWorld;

/*
 * World2.java
 *
 * Create world with simple lighting and
 * materials that show shading effects.
 *
 * written by mike slattery - oct 2003
 */

import java.applet.Applet;
import java.awt.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.keyboard.*;

public class World2 extends Applet {

     SimpleUniverse simpleU=null;

     public BranchGroup createSceneGraph() {
		// Create the root of the branch graph
		BranchGroup objRoot = new BranchGroup();

		// Ambient light is just around (sort of like a cloudy day)
		float dimmer = 0.2f;
		AmbientLight lightA = new AmbientLight(new Color3f(dimmer, dimmer, dimmer));
		BoundingSphere bs = new BoundingSphere(new Point3d(0.0,0.0,0.0),1000.0);
		lightA.setInfluencingBounds(bs);
		objRoot.addChild(lightA);

        // Directional light comes from somewhere, so only lights up
        // one side of things.
        DirectionalLight lightD = new DirectionalLight();
        lightD.setInfluencingBounds(bs);
        Vector3f direction = new Vector3f(-1.0f, -1.0f, -0.5f);
        direction.normalize();
        lightD.setDirection(direction);
        lightD.setColor(new Color3f(0.9f, 0.5f, 0.9f));
        objRoot.addChild(lightD);

        // Mark the axes.
        Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
        Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
        Appearance appX = new Appearance();
        Color3f colorX = new Color3f(0.8f, 0.0f, 0.0f);
        appX.setMaterial(new Material(colorX, black, colorX, white, 80.0f));
        Box xaxis = new Box(8.0f, 0.1f, 0.1f, appX);
        objRoot.addChild(xaxis);
        Box zaxis = new Box(0.1f, 0.1f, 8.0f, appX);
        objRoot.addChild(zaxis);
        Box yaxis = new Box(0.1f, 8.0f, 0.1f, appX);
        objRoot.addChild(yaxis);

        // Add two blue spheres.
        Appearance appS = new Appearance();
		Color3f colorS = new Color3f(0.02f, 0.02f, 0.7f);
        appS.setMaterial(new Material(colorS, black, colorS, white, 80.0f));
        Sphere s1 = new Sphere(1.0f, appS);
        objRoot.addChild(s1);
        Sphere s2 = new Sphere(1.0f, appS);
        Transform3D tS = new Transform3D();
		tS.setTranslation(new Vector3f(0.0f, 0.0f, -5.0f));
        TransformGroup tgS = new TransformGroup(tS);
        tgS.addChild(s2);
        objRoot.addChild(tgS);

        // And one green box.
        Appearance appB = new Appearance();
		Color3f colorB = new Color3f(0.02f, 0.7f, 0.02f);
        appB.setMaterial(new Material(colorB, black, colorB, white, 80.0f));
        Box b1 = new Box(0.7f, 0.7f, 0.7f, appB);
        Transform3D tB = new Transform3D();
		tB.setTranslation(new Vector3f(4.0f, 2.0f, 1.5f));
        TransformGroup tgB = new TransformGroup(tB);
        tgB.addChild(b1);
        objRoot.addChild(tgB);


		return objRoot;
    } // end of CreateSceneGraph method of World2

    public World2() {
        setLayout(new BorderLayout());
        Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        add("Center", canvas3D);

        // SimpleUniverse is a Convenience Utility class
        simpleU = new SimpleUniverse(canvas3D);

        BranchGroup scene = createSceneGraph();

		//...and, set up a KeyNavigator to let us move about
		//
		TransformGroup vpTrans = simpleU.getViewingPlatform().getViewPlatformTransform();

		KeyNavigatorBehavior keyNav = new KeyNavigatorBehavior(vpTrans);
		keyNav.setSchedulingBounds(new BoundingSphere(new Point3d(), 1000.0));
        scene.addChild(keyNav);

		scene.compile();

        simpleU.addBranchGraph(scene);
    }

    //  The following allows this to be run as an application
    //  as well as an applet

    public static void main(String[] args) {
        Frame frame = new MainFrame(new World2(), 256, 256);
    } // end of main (method of World2)

} // end of class World2