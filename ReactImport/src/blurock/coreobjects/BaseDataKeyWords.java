/*
 * BaseDataKeyWords.java
 *
 * Created on January 31, 2002, 9:54 PM
 */

package blurock.coreobjects;
import java.util.StringTokenizer;
import java.io.IOException;
import blurock.core.*;
/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataKeyWords extends BaseDataObject {
    public String[] keyWords;
    
    /** Creates new BaseDataKeyWords */
    public BaseDataKeyWords() {
        keyWords = new String[0];
    }
    public BaseDataKeyWords(String[] keys) {
        keyWords = keys;
    }
    public BaseDataKeyWords(String objstring) {
        isolateObject(objstring);
    }
    public BaseDataKeyWords Clone() {
        BaseDataKeyWords keys = new BaseDataKeyWords();
        keys.CopyClone(this);
        return keys;
    }
    public void CopyClone(BaseDataKeyWords key) {
        keyWords = new String[key.keyWords.length];
        for(int i=0;i<key.keyWords.length;i++) {
            keyWords[i] = new String(key.keyWords[i]);
        }
    }
    public String[] isolateObject(String objstring) {
        StringTokenizer keys = new StringTokenizer(objstring);
        int numparams = keys.countTokens()-1;
        keyWords = new String[numparams];
        for(int i=0;i<numparams;i++) {
            keyWords[i] = keys.nextToken();
        }
        return keyWords;
    }
    public String[] keyWordAsStringArray() {
        return keyWords;
    }
    public void Read(RWManagerBase io) throws IOException {
        String name = "";
        try {
            StringBuffer keys = new StringBuffer();
            name = io.readElement();
            while(!name.equals("END")) {
                keys.append(name);
                keys.append(" ");
                name = io.readElement();
            }
            keys.append("END");
            isolateObject(keys.toString());
        } catch(NumberFormatException exp) {
            throw new IOException("Expected keyword list (ending with 'END'): '" + name + "'");
        }
     }
    
     public String asString() {
         StringBuffer buf = new StringBuffer();
         for(int i=0;i<keyWords.length;i++) {
             buf.append(keyWords[i]);
             buf.append("\n");
             //if(i%5 & i !=0) buf.append("\n");
         }
         buf.append("END\n");
         return buf.toString();
     }
    public void Write(RWManagerBase io) throws IOException {
        String comment = "%%%KeyWords: " + Name;
        io.printLine(comment);
        io.printLine(asString());
    }
    /*
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataInteger(man,this,cls);
    }
     */
}
