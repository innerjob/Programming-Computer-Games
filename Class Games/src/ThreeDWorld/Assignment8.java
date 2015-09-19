package ThreeDWorld;

import java.awt.Frame;

import javax.media.j3d.BranchGroup;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Assignment8 
{
	private SimpleUniverse su = null;
	
	public BranchGroup createSceneGraph()
	{
		BranchGroup objRoot = new BranchGroup();
		
		
		
		
		
		return objRoot;
	}
	
	public Assignment8()
	{
		
	}
	
	public static void main(String[] args)
	{
		Frame f = new MainFrame(new Assignment8(),300,300);
	}
}
