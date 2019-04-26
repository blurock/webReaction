package blurock.reaction.data.common;
import  java.text.ParseException;

/**
 * IParsableElement is an interface of all parsable elements. 
 *
 * @author moliate
 */
public interface IParsableElement
{
        /**
         * parse is the method that does the parsing. Usually it is implemented in
         * a subclass of the class that actually contains the data.
         * @param data the input as a string of bytes. For text files this usually comes from String.getBytes()
         */
	void parse(byte[] data) throws ParseException;
        /**
         * Prints an instance of the element, usually to Log.CRITICAL
         */
	void print();
        /**
         * Similar to the clone() method of objects, this method is included to allow the setting of 
         * data on an abstract baseclass.
         */
	public void setData(IParsableElement element);
        
}
