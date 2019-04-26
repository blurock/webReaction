/*
 * ListOfFiles.java
 *
 * Created on March 22, 2001, 9:41 PM
 */

package blurock.files;

/**
 *
 * @author  reaction
 * @version 
 */
public class ListOfFiles extends java.io.File implements java.io.FilenameFilter {

    public String[] listOfNames;
    String[] nameList;
    String name;
    String prefixString;
    String postfixString;
    
    /** Creates new ListOfFiles */
    public ListOfFiles(String path) {
        super(path);
    }
    void findFileEndingWith(String postfix, String ext) {
        prefixString = null;
        postfixString = postfix + "." + ext;
        getList();
        System.out.println("-------- List of Files -----");
        for(int i=0;i<nameList.length;i++) {
	    int inc = nameList[i].length() - postfixString.length();
            listOfNames[i] = nameList[i].substring(0,inc);
            System.out.println(listOfNames[i]);
        }
    }
    public void findFileWithExtension(String ext) {
        prefixString = null;
        postfixString = "." + ext;
        getList();
            System.out.println("-------- List of Files -----");
            for(int i=0;i<nameList.length;i++) {
	        int inc = nameList[i].length() - postfixString.length();
		listOfNames[i] = nameList[i].substring(0,inc);
                System.out.println(listOfNames[i]);
            }
    }
    public void findFileStartingWith(String prefix, String ext) {
        prefixString = prefix;
        postfixString = "." + ext;
        getList();
        System.out.println("-------- List of Files -----");
        for(int i=0;i<nameList.length;i++) {
	    int inc = nameList[i].length() - postfixString.length();
            listOfNames[i] = nameList[i].substring(prefixString.length(),inc);
            System.out.println(listOfNames[i]);
        }
    }
    public void getList() {
        try {
            nameList = list(this);
            listOfNames = new String[nameList.length];
        } catch(NullPointerException ex) {
                System.out.println("No files in '" + getPath() + "' of type '" + postfixString + "'");
        }
    }
    
    public boolean accept(java.io.File p1,java.lang.String p2) {
            boolean starts = true;
            boolean ends = true;
            System.out.println("accept? " + p2);
            if(postfixString != null) {
                System.out.println("post: " + postfixString);
                ends = p2.endsWith(postfixString);
            }
            if(prefixString != null) {
                System.out.println("pre: " + prefixString);
                starts = p2.startsWith(prefixString);
            }
            return starts && ends;
    }
    
}
