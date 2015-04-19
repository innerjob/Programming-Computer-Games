package ThreeDWorld;

/*
 * World4.java
 *
 * Try user-defined geometry
 *  (with GeometryInfo)
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

public class World4 extends Applet {

     SimpleUniverse simpleU=null;

     public BranchGroup createSceneGraph() {
		// Create the root of the branch graph
		BranchGroup objRoot = new BranchGroup();

		// Ambient light is just around (sort of like a cloudy day)
		float dimmer = 0.3f;
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


        /*
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
        */

        // Define shuttle-craft
        double[] sh_coords = {
          -1.0, 0.0, 1.0,
          1.0, 0.0, 0.3,
          1.0, 0.0, -0.3,
          -1.0, 0.0, -1.0,
          //
          -1.0, 1.0, 0.0,
          -1.0, 0.0, 0.0,
          1.0, 0.0, 0.0
        };

        int[] strip_cnt = {4, 3};

        GeometryInfo gi = new GeometryInfo(GeometryInfo.POLYGON_ARRAY);
        gi.setCoordinates(sh_coords);
        gi.setStripCounts(strip_cnt);
        // Need normals to apply lighting
        NormalGenerator ng = new NormalGenerator();
        ng.generateNormals(gi);
        //
        Shape3D shuttle = new Shape3D();
        shuttle.setGeometry(gi.getGeometryArray());
        //
        Color3f black = new Color3f(0.0f,0.0f,0.0f);
        Color3f white = new Color3f(1.0f,1.0f,1.0f);
         Appearance appB = new Appearance();
				Color3f colorB = new Color3f(0.02f, 0.7f, 0.02f);
        appB.setMaterial(new Material(colorB, black, colorB, white, 80.0f));
        /**/
        PolygonAttributes pA = new PolygonAttributes();
        pA.setCullFace(PolygonAttributes.CULL_NONE);
        appB.setPolygonAttributes(pA);
        /**/
        shuttle.setAppearance(appB);
        //
        objRoot.addChild(shuttle);


		return objRoot;
    } // end of CreateSceneGraph method of World4

    public World4() {
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
        Frame frame = new MainFrame(new World4(), 256, 256);
    } // end of main (method of World4)

} // end of class World4
