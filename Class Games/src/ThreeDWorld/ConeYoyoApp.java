package ThreeDWorld;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Frame;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;


public class ConeYoyoApp extends Applet {

    /////////////////////////////////////////////////
    //
    // create scene graph branch group
    //
    public class ConeYoyo{

        private BranchGroup yoyoBG;

	////////////////////////////////////////////
	//
	// create Shape3D with geometry and appearance
        // the geometry is created in method yoyoGeometry
        // the appearance is created in method yoyoAppearance
	//
	public ConeYoyo() {

	    yoyoBG = new BranchGroup();

		Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
		Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
		Color3f colorX = new Color3f(0.8f, 0.0f, 0.0f);

		Appearance app1 = new Appearance();
		app1.setMaterial(new Material(colorX, black, colorX, white, 80.0f));

		colorX = new Color3f(0.0f, 0.7f, 0.7f);
		Appearance app2 = new Appearance();
			app2.setMaterial(new Material(colorX, black, colorX, white, 80.0f));

	    // rotate object has composited transformation matrix
	    Transform3D rotate = new Transform3D();
	    Transform3D translate = new Transform3D();

	    translate.set(new Vector3f(0.1f, 0.0f, 0.0f));
	    TransformGroup yoyoTGT1 = new TransformGroup(translate);
	    yoyoBG.addChild(yoyoTGT1);

            rotate.rotZ(Math.PI/2.0d);
	    TransformGroup yoyoTGR1 = new TransformGroup(rotate);
	    Cone cone1 = new Cone(0.6f, 0.2f);
	    cone1.setAppearance(app1);
	    yoyoTGR1.addChild(cone1);
	    yoyoTGT1.addChild(yoyoTGR1);

	    translate.set(new Vector3f(-0.1f, 0.0f, 0.0f));
	    TransformGroup yoyoTGT2 = new TransformGroup(translate);
	    yoyoBG.addChild(yoyoTGT2);

            rotate.rotZ(-Math.PI/2.0d);
	    TransformGroup yoyoTGR2 = new TransformGroup(rotate);
	    Cone cone2 = new Cone(0.6f, 0.2f);
	    cone2.setAppearance(app2);
	    yoyoTGR2.addChild(cone2);
	    yoyoTGT2.addChild(yoyoTGR2);

	    yoyoBG.compile();

	} // end of ConeYoyo constructor

	public BranchGroup getBG(){
	    return yoyoBG;
	}

    } // end of class ConeYoyo

    /////////////////////////////////////////////////
    //
    // create scene graph branch group
    //
    public BranchGroup createSceneGraph() {

	BranchGroup objRoot = new BranchGroup();

	// Ambient light is just around (sort of like a cloudy day)
		float dimmer = 0.5f;
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

      // Create the transform group node and initialize it to the
      // identity.  Enable the TRANSFORM_WRITE capability so that
      // our behavior code can modify it at runtime.  Add it to the
      // root of the subgraph.
      TransformGroup objSpin = new TransformGroup();
      objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

      objRoot.addChild(objSpin);
      objSpin.addChild(new ConeYoyo().getBG());

      // Create a new Behavior object that will perform the desired
      // operation on the specified transform object and add it into
      // the scene graph.
      Alpha rotationAlpha = new Alpha(-1, 4000);

      RotationInterpolator rotator =
          new RotationInterpolator(rotationAlpha, objSpin);
      BoundingSphere bounds =
          new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
      rotator.setSchedulingBounds(bounds);
      objSpin.addChild(rotator);

	// Let Java 3D perform optimizations on this scene graph.
        objRoot.compile();

	return objRoot;
    } // end of CreateSceneGraph method of yoyo1

    // Create a simple scene and attach it to the virtual universe

    public ConeYoyoApp() {
        setLayout(new BorderLayout());
        Canvas3D canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        add("Center", canvas3D);

        BranchGroup scene = createSceneGraph();

        SimpleUniverse simpleU = new SimpleUniverse(canvas3D);

        simpleU.getViewingPlatform().setNominalViewingTransform();

        simpleU.addBranchGraph(scene);
    } // end of ConeYoyoApp constructor

    //  The following allows this to be run as an application
    //  as well as an applet

    public static void main(String[] args) {
        Frame frame = new MainFrame(new ConeYoyoApp(), 256, 256);
    } // end of main method of coneyoyo

} // end of class ConeYoyoApp