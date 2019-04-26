/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blurock.reaction.parser;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Vector;

import info.esblurock.cml.generated.Cml;
import info.esblurock.cml.generated.List;
import info.esblurock.cml.generated.ObjectFactory;
import info.esblurock.cml.generated.Scalar;


/**
 *
 * @author blurock
 */
public class DirTraverser {

    public static File[] traverseDir(File base) throws IOException {
        return traverse(base, new suffixFilter(""));
    }

    public static File[] traverse(File base, String suffix) throws IOException {
        return traverse(base, new suffixFilter(suffix));
    }

    public static File[] traverse(File base, FileFilter filter) throws IOException {
        Vector v = new Vector();
        File[] files = base.listFiles(filter);
        if (null == files) {
            return new File[0];
        }
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                v.add(files[i]);
            } else {
                File[] list = traverse(files[i], filter);
                for (int ii = 0; ii < list.length; ii++) {
                    v.add(list[ii]);
                }
            }
        }

        File[] result = new File[v.size()];
        v.copyInto(result);
        Arrays.sort(result);
        return result;
    }

    public static Cml parseDir(String home, String suffix, boolean showFiles) throws Exception {
java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.INFO, "parseDir: " + home);
        ObjectFactory factory = new ObjectFactory();
        Cml cml = factory.createCml();
        List list = factory.createList();

        String home_replace = home;
        File homeF = new File(home);
        String FS = File.separator;

        //home_replace = home_replace.replaceAll(FS+FS, "");
        if (FS.equals("\\")) {
            home_replace = home.replaceAll("\\\\", "\\\\\\\\");
        }
        File[] files = traverse(new File(home), suffix);
        System.out.println("parseDir: Number of Files: " + files.length);
        java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.INFO, "Number of Files: " + files.length);
        for (int i = 0; i < files.length; i++) {
            String file = files[i].getPath();
            
            //file = file.replaceAll(FS+FS, "");
            file = file.replaceAll("^" + home_replace, "");    // remove prefix
            file = file.replaceAll(suffix + "$", "");// and suffix
            
            //java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.INFO, "File: " + file);
            //java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.INFO, "FS: '" + FS + "'");
            StringTokenizer st = new StringTokenizer(file,FS);

            List base = list;
            path_traverser:
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                //java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.INFO, "File Token: " + token);
                if (!st.hasMoreTokens()) {
                    // ----- a file ----- 
                    //java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.INFO, "File File Token: " + token);
                    if (showFiles) {
                        Scalar scalar = factory.createScalar();
                        scalar.setValue(token);
                        scalar.setDataType("xsd:string");
                        base.getAny().add(scalar);
                    }
                } else {
                    // ----- a directory ----- 
                    // search for existing directory list
                    java.util.List l = base.getAny();
                    for (int ii = 0; ii < l.size(); ii++) {
                        Object item = l.get(ii);
                        if (item instanceof List) {
                            List li = (List) item;
                            if (li.getTitle().equals(token)) {
                                base = li;
                                java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.INFO, "Found Directory Token: " + token);
                                continue path_traverser;
                            }
                        }
                    }
                    List cl = factory.createList();
                    cl.setTitle(token);
                    base.getAny().add(cl);
                    base = cl;
                }
            }
            
        }
        cml.getAny().add(list);
        return cml;
    }
};