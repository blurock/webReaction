/*
 * ObjectNotFoundException.java
 *
 * Created on February 22, 2001, 7:46 PM
 */

package blurock.core;

/**
 *
 * @author  reaction
 * @version 
 */
public class ObjectNotFoundException extends java.lang.Exception {

    /**
 * Creates new <code>ObjectNotFoundException</code> without detail message.
     */
    public ObjectNotFoundException() {
    }


    /**
 * Constructs an <code>ObjectNotFoundException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ObjectNotFoundException(String msg) {
        super(msg);
    }
}


