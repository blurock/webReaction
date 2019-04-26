/*
 * traverseDirectory.java
 *
 * Created on October 4, 2004, 9:24 AM
 */

package utilities;
import java.io.File;
import java.util.Vector;
import java.io.IOException;
/**
 *
 * @author  reaction
 */
public class traverseDirectory {
    public File[] listOfFiles = null;
    /** Creates a new instance of DeepCopyDirectory */
    public traverseDirectory() {
    }
    public File[] fileList(File dir) {
        Vector filevec = fileVector(dir);
        listOfFiles = new File[filevec.size()];
System.out.println(filevec.size());
        filevec.copyInto(listOfFiles);
        return listOfFiles;
    }
    Vector fileVector(File dir) {
        Vector filevec = new Vector();
        File[] files = dir.listFiles();
        Vector simplyfiles = isolateFiles(files);
        System.out.println(simplyfiles.size());
        Vector dirs = isolateDirectories(files);
        filevec.addAll(simplyfiles);
        for(int i=0;i<dirs.size();i++) {
            File simpledir = (File) dirs.elementAt(i);
            Vector fls = fileVector(simpledir);
            System.out.println(fls.size());
            filevec.addAll(fls);
        }
        System.out.println(filevec.size());
        return filevec;
    }
    Vector isolateDirectories(File[] files) {
        Vector dirvec = new Vector();
        for(int i=0;i<files.length;i++) {
            if(files[i].isDirectory()) {
                dirvec.add(files[i]);
            }
        }
        return dirvec;
    }
    Vector isolateFiles(File[] files) {
        Vector filevec = new Vector();
        for(int i=0;i<files.length;i++) {
            if(!files[i].isDirectory()) {
                System.out.println(files[i]);
                filevec.add(files[i]);
            }
        }
        System.out.println(filevec.size());
        return filevec;
    }
    public void copyDirectoryStructure(File inputdir, File outputdir) throws IOException {
        fileList(inputdir);
        CopyFile copyfile = new CopyFile();
        
        for(int i=0;i<listOfFiles.length;i++) {
            String name = listOfFiles[i].toString();
            File newfile = switchRoot(outputdir,listOfFiles[i]);
            System.out.println(name + " => " + newfile.toString());
            System.out.println(newfile.getParentFile().getAbsoluteFile() + " => " + newfile.getParentFile().getAbsoluteFile().exists());
            newfile.getParentFile().mkdirs();
           copyfile.copyFile(listOfFiles[i],newfile);
        }
    }
    void createDirectoryStructure(File newdir) throws IOException {
        if(newdir.exists()) {
            return;
        } else {
            createDirectoryStructure(newdir.getParentFile());
        }
        newdir.createNewFile();
    }
    File switchRoot(File root, File name) {
        File rootparent = root.getParentFile();
        File nameparent = name.getParentFile();
        if(rootparent.equals(nameparent)) {
            return root;
        }
        File newroot = switchRoot(root,nameparent);
        File newname = new File(newroot,name.getName().toString());
        return newname;
    }
        
    public String toString() {
        StringBuffer buf = new StringBuffer();
        for(int i=0;i<listOfFiles.length;i++) {
            buf.append(listOfFiles[i].toString());
            buf.append("\n");
        }
        return buf.toString();
    }
}
