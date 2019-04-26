
package blurock.reaction.common;
import  java.text.ParseException;

import info.esblurock.reaction.data.gwt.client.data.IParsableElement;

/**
 * IParsableElement is an interface of all restorable elements. Assuming @see IParsableElement#parse is implemented 
 * IRestorableElement#restore should provide semantical, if not textual, equivalent data.
 * @author moliate
 */
public interface IRestorableElement extends IParsableElement
{
    /**
     * @return the element in a serialized form, the actual format depending on implementation
     */
    byte[] restore();
}
