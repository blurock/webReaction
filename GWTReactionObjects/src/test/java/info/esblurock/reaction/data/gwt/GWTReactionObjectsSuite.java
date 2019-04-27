package info.esblurock.reaction.data.gwt;

import info.esblurock.reaction.data.gwt.client.GWTReactionObjectsTest;
import com.google.gwt.junit.tools.GWTTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

public class GWTReactionObjectsSuite extends GWTTestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite("Tests for GWTReactionObjects");
		suite.addTestSuite(GWTReactionObjectsTest.class);
		return suite;
	}
}
