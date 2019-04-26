/*
 * $Id: FileFrame.java,v 1.1 2008/01/25 13:24:33 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock
 * All Rights Reserved.
 */
package utilities;

import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.filechooser.FileFilter;
import utilities.ErrorFrame;

/**
 * A Frame for the selection of files
 */
public class FileFrame extends JFrame
{
    /**
     * The current directory
     */
    public String defaultDirectory;
    /**
     * The extension of the files to be selected
     */
    String defaultPattern;
    /**
     * A description of the type of file to be selected (with that extension).
     */
    String fileTitle;

    /**
     * The file that was chosen
     */
    public File chosenFile;
    
    /**
     * The file filter to be used (set up when a selection is to be made).
     */
    FileFilter lstfilter;
    
    /**
     * The default constructor (no information given)
     */
    public FileFrame() {
        super("Choose File");
    }

    /**
     * Setting up the choosing of the files with a pattern and a 
     * top directory
     * @param title The description of the files to be selected
     * @param dir The top directory of the files to be selected
     * @param pat The extension of the files to be selected
     */
    public FileFrame(String title,String dir, String pat)
    {
	super(title);
        setupButton(title,dir,pat);
    }
    /**
     * Set up with a predetermined filter
     * @param title The description of the files to be selected
     * @param dir The top directory
     * @param filter A file filter to be used
     */
    public FileFrame(String title, String dir, FileFilter filter) {
	defaultDirectory = dir;
	defaultPattern = "*";
        fileTitle = title;
        lstfilter = filter;
    }
    /**
     * Sets up the file choosing criteria (after the initial
     * construction).
     * @param title The file description
     * @param dir The top directory
     * @param pat The extension of the files to be selected
     */
    public void setupButton(String title, String dir, String pat) {
	defaultDirectory = dir;
	defaultPattern = pat;
        fileTitle = title;
    }
    /**
     * Select a single file and put the result in chosenFile.
     * The default directory is reset.
     * @return true if a file has been selected
     */
    public boolean getFile() {
	lstfilter = new ExampleFileFilter(defaultPattern,fileTitle);
        return getFileBase();
    }
    /**
     * Get a selected file with the given filter. The chosen file
     * is in chosenFile and the default directory is set.
     * @param filter The filter to use
     * 
     * @return true if a file has been selected
     */
    public boolean getFile(FileFilter filter) {
        lstfilter = filter;
        return getFileBase();
    }
    /**
     * Choose a single file and set the default directory.  The file
     * is in chosenfile.
     * @return true if a file was selected
     */
    public boolean getFileBase() {        
	JFileChooser f = new JFileChooser(defaultDirectory);
        boolean ans = true;
	f.addChoosableFileFilter(lstfilter);
	int returnVal = f.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            chosenFile  = f.getSelectedFile();
            String name = f.getName(chosenFile);
            System.out.println("The default path is set to: " + chosenFile.getAbsolutePath());
            defaultDirectory = chosenFile.getParent();
        } else {
            ans = false;
        }
        return ans;
        
    }
    /**
     * This returns the file path relative to the base directory given. If the chosen file
     * is not in this base directory, the method fails (an Error Frame pops up).
     * @param directoryBase The relative directory
     * @return The relative path to the file with respect to the directory given
     */
    public boolean getFileBase(String directoryBase) {
        defaultDirectory = directoryBase;
        boolean ans = getFileBase();
        if(ans) {
            try {
                String relative = getRelativePath(chosenFile, directoryBase);
                chosenFile = new File(relative);
            } catch(IOException ex) { 
                ans = false;
            }
        }
        return ans;
    }
    /**
     * Allow selection of several files.  The default directory is
     * set to that of the first file.
     * @return An array of selected files
     * @throws java.io.IOException Throws an exception if no file has been chosen
     */
    public File[] getChoosenFiles() throws IOException {
        File[] files = null;
	JFileChooser f = new JFileChooser(defaultDirectory);
        f.setMultiSelectionEnabled(true);
	f.addChoosableFileFilter(lstfilter);
	int returnVal = f.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            files  = f.getSelectedFiles();
            if(files.length > 0) {
            defaultDirectory = files[0].getParent();
            } else {
                throw new IOException("No File Choosen");
            }
        }else {
                throw new IOException("No File Choosen");
            }
        return files;
    }
    /**
     * Choose a directory instead of a file
     * @return The directory
     */
    public boolean chooseDirectory() {
	JFileChooser f = new JFileChooser(defaultDirectory);
        f.setFileSelectionMode(f.DIRECTORIES_ONLY);
        boolean ans = true;
	int returnVal = f.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            chosenFile = f.getSelectedFile();
            defaultDirectory = chosenFile.toString();
         } else {
             ans = false;
         }
        return ans;
    }
    
    String getRelativePath(File file, String baseDirectory) throws IOException {
      String userpath = "";
      String path = file.getAbsolutePath();
      System.out.println("getRelativePath: " + path + "," + baseDirectory);
      
      boolean isinuserdir = path.startsWith(baseDirectory);
      if(isinuserdir) {
           userpath = path.substring(baseDirectory.length()+1);
       } else {
           ErrorFrame error = new ErrorFrame("File not in user directory: " + baseDirectory +
                       "\n " + path);
           error.setVisible(true);
           throw new IOException();
       }
      return userpath;
    }
}
