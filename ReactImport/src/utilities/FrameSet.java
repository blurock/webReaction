/*
 * FrameSet.java
 *
 * Created on January 31, 2001, 2:25 PM
 */

package utilities;
import java.util.*;


/**
 *
 * @author  reaction
 * @version 
 */
public class FrameSet extends Hashtable {

    /** Creates new FrameSet */
    public FrameSet() {
    }
    

    public void putFrame(BaseFrame frame) {
        put((Object) frame.frameName, (Object) frame);
    }
    
    public BaseFrame getFrame(java.lang.String key) {
        return (BaseFrame) get((Object) key);
    }    
    
}
