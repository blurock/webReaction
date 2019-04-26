/*
 * BaseDataClassInfo.java
 *
 * Created on May 13, 2001, 9:25 AM
 */

package xml.structure;
import java.io.IOException;
import blurock.coreobjects.*;
import blurock.core.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataClassInfo extends blurock.coreobjects.BaseDataObject {
    int maxFlagCount;
    public boolean[] functionFlags;
    
    public String language;
    
    public String[] flagNames;
    public String[] flagDescriptions;
    public int flagCount = 0;
    String beginGenerateString = "<generate-flag ";
    String endGenerateString = " /> ";
    String languageString = " language=";
    String routineString = " routine=";

    /** Creates new BaseDataClassInfo */
    public BaseDataClassInfo(int id, String lang, 
                    String[] names, String[] descr,
                    boolean init) {
        Name = lang;
        Identification = id;
        language = lang;
        flagNames = names;
        flagDescriptions = descr;
        functionFlags = new boolean[names.length];
       for(int i=0;i<names.length;i++) {
            functionFlags[i] = init;
        }
        maxFlagCount = names.length;
    }
    public BaseDataClassInfo() {
        language = "C";
        Name = language;
        maxFlagCount = 20;
        functionFlags = new boolean[maxFlagCount];
        flagNames  = new String[maxFlagCount];
    }
    public void addFlag(String Name, String descr, boolean defaultValue) {
        if(flagCount < maxFlagCount) {
            flagNames[flagCount] = Name;
            flagDescriptions[flagCount] = descr;
            functionFlags[flagCount] = defaultValue;
            flagCount = flagCount + 1;
        }
    }
  void FillInGenerateFlag(String flagName, String language) {
      int length = flagNames.length;
      System.out.println("flagName: " + flagName + "  " + language);
      int i = 0;
      boolean notdone = true;
      while(i<length && notdone) {
          if(flagNames[i].equals(flagName)) {
              notdone = false;
              functionFlags[i] = true;
          }
          i++;
      }
  }
            
        
    public void Read(RWManager io) throws IOException {
   }
    
    public void Write(RWManager io) throws IOException {
        for(int i=0;i<functionFlags.length;i++) {
            if(functionFlags[i]) {
            io.printLine(beginGenerateString + 
                            languageString + "\"" + language + "\"" + 
                            routineString + "\"" + flagNames[i] + "\"" + 
                            endGenerateString);
            }
        }
     }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataClassInfo(man,this,cls);
    }
    
}
