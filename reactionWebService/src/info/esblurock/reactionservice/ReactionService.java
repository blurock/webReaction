package info.esblurock.reactionservice;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import blurock.reaction.common.SProperties;
import blurock.reaction.data.molecules.BRS.BRSMolecule;
import blurock.reaction.data.molecules.CML.CMLMolecule;
import blurock.reaction.link.ReactLink;
import blurock.reaction.parser.DirTraverser;
import blurock.reaction.reactions.BRS.BRSRxnPattern;
import blurock.reaction.reactions.CML.CMLRxnPattern;
import blurock.reactions.common.FileCluster;
import blurock.reactions.common.Log;
import blurock.reactions.common.SReaction;
import blurock.reactions.common.XMLFilter;
import blurock.reactions.mechanisms.BRS.BRSMechanism;
import blurock.reactions.mechanisms.BRS.BRSMechanismGeneration;
import blurock.reactions.mechanisms.CML.CMLMechanism;
import blurock.reactions.mechanisms.CML.CMLMechanismGeneration;
import info.esblurock.cml.generated.Cml;
import info.esblurock.cml.generated.List;
import info.esblurock.cml.generated.MechanismComponent;
import info.esblurock.cml.generated.ObjectFactory;
import info.esblurock.cml.generated.Scalar;
import info.esblurock.reaction.data.gwt.client.data.ReactionLog;

public class ReactionService {

    static private String REACTION_HOME;
    static private String scriptshome = "/programs/scripts";
    static private String user = "trial";
    static private String usershome = "/opt/ReactUsers/users";
    static private String printmolsS = "printmols.sh";

	public ReactionService() {
		
	}

    static private boolean setSystemProperties() {
        SProperties.load();
        //Logger.getLogger(Reaction.class.getName()).log(Level.SEVERE, "Read in file");
        //usershome = SProperties.getProperty("reaction.users.home");
        //user = SProperties.getProperty("reaction.user");
        //scriptshome = SProperties.getProperty("reaction.scripts.home");
        //printmolsS = SProperties.getProperty("reaction.scripts.printmol");

        File reactionhome = new File(usershome, user);
        REACTION_HOME = reactionhome.toString();

        Logger.getLogger(ReactionService.class.getName()).log(Level.INFO, "Reaction Home: " + REACTION_HOME);

        SProperties.setProperty("reaction.home", REACTION_HOME);

        return true;
    }
    public String listReactionObject(String cmd) {
        setSystemProperties();
        String out = "No output";
        String output = "ERROR\n";

        File scripthome = new File(REACTION_HOME, scriptshome);
        File script = new File(scripthome, cmd);
        File homedirF = new File(REACTION_HOME);
        try {
            ReactLink link = new ReactLink();
            link.setSystemProperty("REACTROOT", REACTION_HOME);
            link.setCommand(script.toString() + " " + REACTION_HOME);
            link.setExecuteDir(homedirF);
            link.start();
            out = link.getResult();
            Cml cml = parseList(out);

            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(cml.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18

            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            marshaller.marshal(cml, new PrintStream(bos));
            output = new String(bos.toByteArray());
        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N

            return "SERVER ERROR: " + ex.toString() + "\n" + out;
        } catch (Exception e) {
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, "Error in ReactLink call: " + e.toString()); //NOI18N

            return "SERVER ERROR: " + e.toString() + "\n" + out;
        }
        return output;
    }

    private Cml parseList(String out) throws Exception {
        ObjectFactory factory = new ObjectFactory();
        Cml cml = factory.createCml();
        List list = factory.createList();
        StringTokenizer lineToken = new StringTokenizer(out, "\n");
        boolean notdone = true;
        while (notdone) {
            String line = lineToken.nextToken();
            if (-1 != line.indexOf("=========Error Output ========")) {
                break;
            }
            StringTokenizer comToken = new StringTokenizer(line, ":");
            if (comToken.countTokens() == 2) {
                String id = new String(comToken.nextToken());
                String name = new String(comToken.nextToken());
                String isoname = name.substring(4, name.length() - 3).trim();
                Scalar s = factory.createScalar();
                s.setId(id.trim());
                s.setValue(isoname);
                s.setDataType("xsd:string");
                list.getAny().add(s);
            } else {
                throw new Exception("Unexpected list element: " + line);
            }
            if (!lineToken.hasMoreTokens()) {
                notdone = false;
            }
        }

        cml.getAny().add(list);
        return cml;
    }
    private String removeMeta(String s) {
        s = s.replaceAll("[\\n\\r]", "");
        s = s.replaceAll("\\(", "\\("); // this looks strange, but the first param is a regex and the second a string
        s = s.replaceAll("\\)", "\\)");
        s = s.replaceAll("\\[", "\\[");
        s = s.replaceAll("\\]", "\\]");
        s = s.replaceAll("&", "\\&");
        s = s.replaceAll(";", "\\;");
        s = s.replaceAll("'", "\\'");
        //s = s.replaceAll("�", "\\�");
        s = s.replaceAll("<", "\\<");
        s = s.replaceAll(">", "\\>");
        s = s.replaceAll("\\{", "\\{");
        s = s.replaceAll("\\}", "\\}");
        s = s.replaceAll("\\?", "\\?");
        s = s.replaceAll("\\*", "\\*");
        s = s.replaceAll("\\$", "\\$");
        s = s.replaceAll("~", "\\~");
        s = s.replaceAll("\\^", "\\^");
        s = s.replaceAll("\"", "\\\"");
        s = s.replaceAll("\\|", "\\|");
        return s;
    }

	public String listMolecules() {
        Logger.getLogger(ReactionService.class.getName()).log(Level.INFO, "listMolecules ");
        return listReactionObject(printmolsS);
    }
    public String listSubstructures() {
        String printsubsS = SProperties.getProperty("reaction.scripts.printsubs");
        return listReactionObject(printsubsS);
    }

    public String listRxnPatterns() {
        String printsubsS = SProperties.getProperty("reaction.scripts.printpats");
        return listReactionObject(printsubsS);
    }

    public String listMechanisms() {
        String printsubsS = SProperties.getProperty("reaction.scripts.printmechs");
        return listReactionObject(printsubsS);
    }

    public String listSubmechanismFiles() {
        setSystemProperties();
        String output = "ERROR";
        try {
            String lsrhome = SProperties.getProperty("reaction.lsr.home");
            File lsr_homeF = new File(REACTION_HOME, lsrhome);
            String lsr_home = lsr_homeF.toString();
            String lsr_suffix = SReaction.getProperty("reaction.lsr.suffix");

            Cml cml = DirTraverser.parseDir(lsr_home, lsr_suffix, true);

            try {
                javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(cml.getClass().getPackage().getName());
                javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
                marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18

                marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                marshaller.marshal(cml, new PrintStream(bos));
                output = new String(bos.toByteArray());
            } catch (javax.xml.bind.JAXBException ex) {
                // XXXTODO Handle exception
                java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N

            }

            return output;
        } catch (Exception e) {
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, "printSteps Error: " + e.toString());
            return "SERVER ERROR: " + e.toString();
        }

    }

    public String listDir(String dir, String filter, Boolean showFiles) {
        if (!setSystemProperties()) {
            return "SERVER ERROR: Operation not allowed for guest users.";
        }
        File start_dirF = new File(REACTION_HOME, SProperties.getProperty("reaction.data.home"));
        String start_dir = start_dirF.toString();
        StringTokenizer st = new StringTokenizer(dir, "/");
        while (st.hasMoreTokens()) {
            File d = new File(start_dir, st.nextToken());
            start_dir = d.toString();
        }
        String output = "ERROR";
        try {

            Cml cml = DirTraverser.parseDir(start_dir, filter, showFiles);
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(cml.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(cml, new PrintStream(bos));
            output = new String(bos.toByteArray());

        } catch (javax.xml.bind.JAXBException ex) {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, "listDir Marshelling Error: " + ex.toString()); //NOI18N
            return "SERVER ERROR: " + ex.toString();
        } catch (java.lang.Exception ex) {
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, "listDir Parsing Error: " + ex.toString()); //NOI18N
            return "SERVER ERROR: " + ex.toString();

        }

        return output;

    }

    public String printMolecule(String reference) {
        reference = removeMeta(reference);
        setSystemProperties();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ReactLink link = new ReactLink();
            link.setSystemProperty("REACTROOT", REACTION_HOME);
            String printmol = SProperties.getProperty("reaction.scripts.printmolecule");
            File commandF = new File(scriptshome, printmol);
            File fullcommandF = new File(REACTION_HOME, commandF.toString());
            link.setCommand(fullcommandF.toString());
            link.setParameters(new String[]{reference, REACTION_HOME});
            link.start();
            String out = link.getResult();

            BRSMolecule molecule = new BRSMolecule(link,REACTION_HOME);
            molecule.parse(out.getBytes());
            CMLMolecule cmlMolecule = new CMLMolecule();
            cmlMolecule.setData(molecule);
            ObjectFactory factory = new ObjectFactory();
            Cml cml = factory.createCml();
            cml.getAny().add(cmlMolecule.toCML());
            JAXBContext jc = JAXBContext.newInstance(cml.getClass().getPackage().getName());
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(cml, new PrintStream(bos));
        } catch (Exception e) {
            ReactionLog.logError("printMolecule Error: " + e.toString());
            return "SERVER ERROR: " + e.toString();
        }
        return new String(bos.toByteArray());

    }

    /**
     * Web service operation
     */
    public String printSubstructure(String reference) {
        setSystemProperties();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ReactLink link = new ReactLink();
            String printmol = SProperties.getProperty("reaction.scripts.printsubstructure");
            System.out.println("Print Substructure command" + printmol);
            File commandF = new File(scriptshome, printmol);
            File fullcommandF = new File(REACTION_HOME, commandF.toString());
            link.setCommand(fullcommandF.toString());
            link.setSystemProperty("REACTROOT", REACTION_HOME);
            link.setParameters(new String[]{reference, REACTION_HOME});
            link.start();
            String out = link.getResult();

            
            
            BRSMolecule molecule = new BRSMolecule(link,REACTION_HOME);
            molecule.parse(out.getBytes());
            CMLMolecule cmlMolecule = new CMLMolecule();
            cmlMolecule.setData(molecule);
            ObjectFactory factory = new ObjectFactory();
            Cml cml = factory.createCml();
            cml.getAny().add(cmlMolecule.toCML());
            JAXBContext jc = JAXBContext.newInstance(cml.getClass().getPackage().getName());
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(cml, new PrintStream(bos));

        } catch (Exception e) {
            ReactionLog.logError("printSubstructure Error: " + e.toString());
            return "SERVER ERROR: " + e.toString();
        }

        //return new String(XMLFilter.filter(bos.toByteArray()) );
        return new String(bos.toByteArray());
    }

    /**
     * Web service operation
     */
    public String printRxnPattern(String reference) {
        reference = removeMeta(reference);
        setSystemProperties();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ReactLink link = new ReactLink();
            String printpat = SProperties.getProperty("reaction.scripts.printrxnpattern");
            File commandF = new File(scriptshome, printpat);
            File fullcommandF = new File(REACTION_HOME, commandF.toString());
            link.setCommand(fullcommandF.toString());
            link.setSystemProperty("REACTROOT", REACTION_HOME);
            link.setParameters(new String[]{reference, REACTION_HOME});
            link.start();
            String out = link.getResult();

            link.setSystemProperty("REACTROOT", REACTION_HOME);
            String printsub = SProperties.getProperty("reaction.scripts.printsubstructure");
            File csubF = new File(scriptshome, printsub);
            File csub2F = new File(REACTION_HOME, csubF.toString());
            link.setCommand(csub2F.toString());
            BRSRxnPattern pattern = new BRSRxnPattern(link, REACTION_HOME);
            pattern.parse(out.getBytes());
            pattern.print();
            CMLRxnPattern cmlPattern = new CMLRxnPattern();
            cmlPattern.setData(pattern);

            ObjectFactory factory = new ObjectFactory();
            Cml cml = factory.createCml();
            cml.getAny().add(cmlPattern.toCML());
            //System.out.println( new String(cmlPatterncml.getClass().getPackage().getName().restore()) );
            JAXBContext jc = JAXBContext.newInstance(cml.getClass().getPackage().getName());
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(cml, new PrintStream(bos));

        } catch (Exception e) {
            ReactionLog.logError("printRxnPattern Error: " + e.toString());
            return "SERVER ERROR: " + e.toString();
        }

        return new String(bos.toByteArray());
    }

    /**
     * Web service operation
     */
    public String printSubmechanism(String pathDir) {
        pathDir = removeMeta(pathDir);
        setSystemProperties();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectFactory factory = new ObjectFactory();
            Cml cml = factory.createCml();

            File lsr_homeF = new File(REACTION_HOME, SProperties.getProperty("reaction.lsr.home"));
            String lsr_home = lsr_homeF.toString();
            String lsr_suffix = SProperties.getProperty("reaction.lsr.suffix");
            String file = lsr_home;
            StringTokenizer path = new StringTokenizer(pathDir, "/");

            while (path.hasMoreTokens()) {
                file += System.getProperty("file.separator") + path.nextToken();
            }
            file += lsr_suffix;

            BufferedReader br = new BufferedReader(new FileReader(new File(file)));
            StringBuffer sb = new StringBuffer(1024);
            while (br.ready()) {
                sb.append(br.readLine());
                sb.append("\n");
            }

            BRSMechanismGeneration mechanism = new BRSMechanismGeneration();
            mechanism.parse(sb.toString().getBytes());
            CMLMechanismGeneration cmlMechanism = new CMLMechanismGeneration();
            cmlMechanism.setData(mechanism);

            MechanismComponent me = cmlMechanism.toCML();
            cml.getAny().add(me);
            //CMLMechanism cmlMechanism2 = new CMLMechanism();
            //cmlMechanism2.fromCML(rle);

            JAXBContext jc = JAXBContext.newInstance(cml.getClass().getPackage().getName());
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(cml, new PrintStream(bos));

        } catch (Exception e) {
            ReactionLog.logError("printReactionStep Error: " + e.toString());
            return "SERVER ERROR: " + e.toString();
        }

        return new String(bos.toByteArray());
    }

    /**
     * Web service operation
     */
    public String printMechanism(String pathConcat) {

        pathConcat = removeMeta(pathConcat);
        setSystemProperties();
        ReactionLog.logInfo("Mechanism: 1");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectFactory factory = new ObjectFactory();
            Cml cml = factory.createCml();

            ReactionLog.logInfo("Mechanism: 2");

            File mech_homeF = new File(REACTION_HOME, SProperties.getProperty("reaction.mechanisms.home"));
            ReactionLog.logInfo("Mechanism: 2.1");
            String mech_home = mech_homeF.toString();
            String mech_suffix = SProperties.getProperty("reaction.mechs.suffix");
            String file = mech_home;

            ReactionLog.logInfo("Mechanism: 3");

            StringTokenizer path = new StringTokenizer(pathConcat, "/");

            while (path.hasMoreTokens()) {
                file += System.getProperty("file.separator") + path.nextToken();
            }
            file += mech_suffix;
            ReactionLog.logInfo("Mechanism: " + file);

            BRSMechanism mechbrs = new BRSMechanism();
            mechbrs.readFromFile(new File(file));
            CMLMechanism cmlMechanism = new CMLMechanism();
            cmlMechanism.setData(mechbrs);

            MechanismComponent mech = cmlMechanism.toCML();
            cml.getAny().add(mech);
            JAXBContext jc = JAXBContext.newInstance(cml.getClass().getPackage().getName());
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(cml, new PrintStream(bos));

        } catch (Exception e) {
            ReactionLog.logError("printMechanism Error: " + e.toString());
            return "SERVER ERROR: " + e.toString();
        }

        return new String(bos.toByteArray());
    }

    /**
     * Web service operation
     */
    public String printDBMechanism(String name) {
        setSystemProperties();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ReactLink link = new ReactLink();
            link.setSystemProperty("REACTROOT", REACTION_HOME);
            File tmpdir = new File(REACTION_HOME, SProperties.getProperty("reaction.tmp.home"));
            link.setExecuteDir(tmpdir);
            File f1 = new File(REACTION_HOME, scriptshome);
            File f2 = new File(f1, SProperties.getProperty("reaction.scripts.dbmechout"));
            link.setCommand(f2.toString() + " tmp_mech " + name + " " + REACTION_HOME);
            link.start();

            ObjectFactory factory = new ObjectFactory();
            Cml cml = factory.createCml();

            BRSMechanism mechanism = new BRSMechanism();
            mechanism.readFromFile(new File(tmpdir, "tmp_mech" + SProperties.getProperty("reaction.mechs.suffix")));
            CMLMechanism cmlMechanism = new CMLMechanism();
            cmlMechanism.setData(mechanism);

            MechanismComponent mech = cmlMechanism.toCML();
            cml.getAny().add(mech);

            JAXBContext jc = JAXBContext.newInstance(cml.getClass().getPackage().getName());
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(cml, new PrintStream(bos));
        } catch (Exception e) {
            ReactionLog.logError("printDBMechanism Error: " + e.toString());
            return "SERVER ERROR: " + e.toString();
        }

        return new String(bos.toByteArray());
    }

    public String getFiles(String filenames) {
        if (!setSystemProperties()) {
            return "SERVER ERROR: Operation not allowed for guest users.";
        }
        Log.println("getFiles " + filenames);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            FileCluster fc = new FileCluster();
            fc.setRootPath(new File(REACTION_HOME + SReaction.getDataHome()));

            StringTokenizer st = new StringTokenizer(filenames, ",");
            while (st.hasMoreTokens()) {
                StringBuffer path = new StringBuffer();
                path.append(REACTION_HOME);
                path.append(SReaction.getDataHome());
                File fpath = new File(path.toString());
                StringTokenizer stp = new StringTokenizer(st.nextToken(), "/\\");
                while (stp.hasMoreTokens()) {
                    fpath = new File(fpath.toString(), stp.nextToken());
                //path.append(FS);
                //path.append(stp.nextToken());
                }
                //fc.insertFile(new File(path.toString()), true);
                fc.insertFile(fpath, true);
            }
            JAXBContext jc = JAXBContext.newInstance("blurock.reaction.generated.core");
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(fc.toCML(), new PrintStream(bos));
        } catch (Exception e) {
            Log.println("getFiles Error: " + e.toString(), Log.ERROR);
            Log.println(e, Log.INFO);
            return "SERVER ERROR: " + e.toString();
        }

        return new String(XMLFilter.filter(bos.toByteArray()));
    }

    public String putFiles(String cml) {
        Log.println("putFiles call: start ");
        if (!setSystemProperties()) {
            return "SERVER ERROR: Operation not allowed for guest users.";
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            FileCluster files = new FileCluster();
            files.setRootPath(new File(REACTION_HOME + SReaction.getDataHome()));
            files.parse(cml.getBytes());
            int saved = files.saveAll();
            Cml cml_result = makeScalar(saved, "files saved");
            JAXBContext jc = JAXBContext.newInstance("blurock.reaction.generated.core");
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(cml_result, new PrintStream(bos));
        } catch (Exception e) {
            Log.println("putFiles Error: " + e.toString(), Log.ERROR);
            Log.println(e, Log.INFO);
            return "SERVER ERROR: " + e.toString();
        }
        return new String(XMLFilter.filter(bos.toByteArray()));
    }

    private Cml makeScalar(int data, String title) throws Exception {
        ObjectFactory factory = new ObjectFactory();
        Cml cml = factory.createCml();
        Scalar scalar = factory.createScalar();
        scalar.setTitle(title);
        scalar.setDataType("xsd:integer");
        scalar.setValue(Integer.toString(data));
        cml.getAny().add(scalar);
        return cml;
    }
         private Cml makeScalar(String data, String title) throws Exception
        {
            ObjectFactory factory = new ObjectFactory();
            Cml cml = factory.createCml();
            Scalar scalar = factory.createScalar();
            scalar.setTitle(title);
            scalar.setDataType("xsd:string");
            scalar.setValue(data);
            cml.getAny().add(scalar);
            return cml;
        }

        private Cml makeScalar(boolean data, String title) throws Exception
        {
            ObjectFactory factory = new ObjectFactory();
            Cml cml = factory.createCml();
            Scalar scalar = factory.createScalar();
            scalar.setTitle(title);
            scalar.setDataType("xsd:boolean");
            scalar.setValue(data?"true":"false");
            cml.getAny().add(scalar);
            return cml;
        }
    public String combineMechanisms(String root, String name, String mechanisms) {
        if (!setSystemProperties()) {
            return "SERVER ERROR: Operation not allowed for guest users.";
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            name = name.replaceAll("/", "-");

            StringTokenizer st = new StringTokenizer(mechanisms, ";");

            File genhomeF = new File(REACTION_HOME,SReaction.getGenerationHome());
            String nameS = name + ".lst";
            File nameF = new File(genhomeF,nameS);


            PrintWriter pw = new java.io.PrintWriter(nameF);
            while (st.hasMoreTokens()) {
                pw.println(st.nextToken());
            }
            pw.close();

            File scriptsF = new File(REACTION_HOME, SReaction.getScriptsHome());
            File combineF = new File(scriptsF, "combinemech.sh");
            File prtmechF = new File(scriptsF, "printoutmech.sh");
            ReactLink link = new ReactLink();
            link.setSystemProperty("REACTROOT", REACTION_HOME);
            link.setExecuteDir(new File(REACTION_HOME + SReaction.getGenerationHome()));

            link.setCommand(combineF.toString() + " " + root + " " + name + " " + REACTION_HOME);
            link.start();
            String out = "combinemech\n\n" + link.getResult();
            link.setExecuteDir(new File(REACTION_HOME + SReaction.getGenerationHome()));



            link.setCommand(prtmechF.toString() + " " + name + " " + name + " " + REACTION_HOME);
            link.start();
            out += "printoutmech\n\n" + link.getResult();
            Cml cml = makeScalar(out, "server output");
            JAXBContext jc = JAXBContext.newInstance("blurock.reaction.generated.core");
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(cml, new PrintStream(bos));
        } catch (Exception e) {
            Log.println("runchain Error: " + e.toString(), Log.ERROR);
            Log.println(e, Log.INFO);
            return "SERVER ERROR: " + e.toString();
        }

        return new String(XMLFilter.filter(bos.toByteArray()));
    }
    public String createSubmechanism(String mechanismName, String molecules, String outputRoot) {
        if (!setSystemProperties()) {
            return "SERVER ERROR: Operation not allowed for guest users.";
        }
        ReactLink link = new ReactLink();
        link.setSystemProperty("REACTROOT", REACTION_HOME);
        //String generate = SProperties.getProperty("reaction.scripts.generateSubmechanism");
        String generate = "runchain.sh";
        File commandF = new File(scriptshome, generate);
        File fullcommandF = new File(REACTION_HOME, commandF.toString());
        link.setCommand(fullcommandF.toString());
        String mechgen = SProperties.getProperty("reaction.mechanisms.home");
        File exe = new File(REACTION_HOME,mechgen);
        String mechschemes = SProperties.getProperty("reaction.lsr.home");
        File mechschemesF = new File(REACTION_HOME, mechschemes);
        File mechF = new File(mechschemesF, mechanismName);
        link.setExecuteDir(exe);
        String[] parameters = new String[3];
        parameters[0] = mechF.toString();
        parameters[1] = molecules;
        parameters[2] = outputRoot;
        link.setParameters(parameters);
        link.start();
        String out = link.getResult();

        return out;
    }

    public String alternativeNames(String altname, String rootMols, String rootCorrs) {
          setSystemProperties();
          ByteArrayOutputStream bos = new ByteArrayOutputStream();
          String output = "";
          try
          	{
                ReactLink link = new ReactLink();
                link.setSystemProperty("REACTROOT", REACTION_HOME);
                link.setExecuteDir( new File(REACTION_HOME) );
                File scriptsF = new File(REACTION_HOME,SReaction.getScriptsHome());
                File alternativeF = new File(scriptsF,"alternativenames.sh");
                link.setCommand(alternativeF.toString() +
                    " " + altname +
                    " " + rootMols +
                    " " + rootCorrs +
                    " " + REACTION_HOME);
                link.start();
                output = link.getResult();
        	}
         catch (Exception e) {
	        Log.println("alternative Names Error: " + e.toString(), Log.ERROR);
                Log.println(e, Log.INFO);
                return "SERVER ERROR: " + e.toString();
         }
          return output;
    }

}
