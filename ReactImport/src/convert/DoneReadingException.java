/*
 * DoneReadingException.java
 *
 * Created on December 16, 2002, 5:28 PM
 */

package convert;

/**
 *
 * @author  reaction
 */
public class DoneReadingException extends java.lang.Exception {
    
    /**
     * Creates a new instance of <code>DoneReadingException</code> without detail message.
     */
    public DoneReadingException() {
        super("Done");
    }
    
    
    /**
     * Constructs an instance of <code>DoneReadingException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public DoneReadingException(String msg) {
        super(msg);
    }
}
