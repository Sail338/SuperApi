package Gui;

import com.dropbox.core.DbxEntry;
import com.dropbox.core.DbxException;
import handlers.BoxHandler;
import handlers.DropBoxHandler;
import handlers.FileHandler;
import handlers.GoogleDriveHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Hari on 2/15/2015.
 */
public class GuiForm extends JFrame{
    private JButton getAuthKeyButton;
    private JCheckBox googleDriveCheckBox;
    private JPanel lel;
    public String googleString;
    public JTextField textField1;
    private JButton authenticateButton;
    private JButton upload;
    private JCheckBox dropBoxCheckBox;
    private JButton getAuthKeyButton1;
    private JTextField textField2;
    private JButton authenticateButton1;
    private JButton uploadButton;
    private JCheckBox boxCheckBox;
    private JButton authenticateButton2;
    private JButton uploadButton1;
    private JFileChooser BrowserFiler;
    private JButton browseButton;
    private JTextField textField3;
    private JButton searchAndDownloadButton;
    BoxHandler boxHandler = new BoxHandler();
    FileHandler fileHandler = new FileHandler();



    GoogleDriveHandler handler = new GoogleDriveHandler();
    DropBoxHandler dropBoxHandler = new DropBoxHandler();

    public GuiForm() {
        super("FileUpload");
        setContentPane(lel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        final JFileChooser chooser = new JFileChooser();





        getAuthKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(googleDriveCheckBox.isSelected()){
                    try {

                        handler.autheticateGoogle();
                      //  System.out.println(handler.getCode().toString());
                        openWebPage(new URI(handler.getCode().toString()));

                    }
                    catch (Exception j) {
                        j.printStackTrace();
                    }


                }
            }
        });
        authenticateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                googleString = textField1.getText();
                try {
                    handler.startAutheicationProcess(googleString);
                }
                catch(IOException bade) {
                    bade.printStackTrace();
                }
            }
        });
        upload.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                try {
                    handler.uploadFile();
                }
                catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        getAuthKeyButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dropBoxCheckBox.isSelected()) {
                    try {
                        dropBoxHandler.authenticateDropBox();
                    }
                    catch(IOException badmof) {
                        badmof.printStackTrace();
                    }
                    catch(DbxException lol) {
                        lol.printStackTrace();
                    }
                    try {
                        openWebPage(new URI(dropBoxHandler.getAuthorize().toString()));

                    }
                    catch(Exception lol) {
                        lol.printStackTrace();
                    }

                }
            }
        });
        authenticateButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dropBoxHandler.setDropBoxStrin(textField2.getText());
                try{
                    dropBoxHandler.startProcess(dropBoxHandler.getDropBoxStrin());
                }
                catch(Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    dropBoxHandler.upLoadFile("/hi");
                }
                catch (Exception ugh) {

                }
            }
        });
        authenticateButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(boxCheckBox.isSelected()) {
                    boxHandler.authenticateBox();


                }
            }
        });
        uploadButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boxHandler.uploadFile();
                }
                catch(IOException io) {
                    io.printStackTrace();
                }
            }
        });

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == browseButton) {
                    int returnval = chooser.showOpenDialog(BrowserFiler);
                    if(returnval==chooser.APPROVE_OPTION) {
                        fileHandler.setFile(chooser.getSelectedFile().getPath());
                    }
                }


            }
        });
        searchAndDownloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
               dropBoxHandler.searchFile(textField3.getText());
               dropBoxHandler.downloadFile(dropBoxHandler.getBadList().get(0).asFile().path.toString());
                    JFileChooser fi = new JFileChooser();
                    int realval =  fi.showSaveDialog(BrowserFiler);
                    DbxEntry file = dropBoxHandler.dbxEntry();
                    if(realval == fi.APPROVE_OPTION) {
                        fi.setSelectedFile(new File(file.asFile().path.replace("/", "")));
                        FileWriter fileWriter = new FileWriter(fi.getSelectedFile());
                        fileWriter.write(file.toString());
                    }


                }
                catch (Exception he) {
                    he.printStackTrace();
                }
            }
        });
    }
    public static void openWebPage(URI uri){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() :null;
        if(desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
        try {
      desktop.browse(uri);
        }
        catch(Exception e) {
            e.printStackTrace();

            }
        }
    }
    public static void openWebPage(URL url) {
        try {
            openWebPage(url.toURI());
        }
        catch (Exception z) {
            z.printStackTrace();
        }

    }
    public String getGoogleString() {
        return googleString;
    }
}

