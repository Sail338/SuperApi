import Gui.GuiForm;
import com.dropbox.core.DbxException;
import handlers.BoxHandler;
import handlers.DropBoxHandler;
import handlers.FileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Hari on 2/14/2015.
 */
public class Main  {
    public static void main(String[] args) throws IOException,FileNotFoundException,DbxException{
        //authenticate DropBox
DropBoxHandler dropBoxHandler = new DropBoxHandler();
        FileHandler fileHandler = new FileHandler();
        Scanner sc = new Scanner(System.in);
        GuiForm form = new GuiForm();


        fileHandler.setFile("C:\\Users\\Hari\\Documents\\Chess\\Aronian.pgn");

        //dropBoxHandler.authenticateDropBox();
        //upload a file to the folder CodeDay
        //dropBoxHandler.upLoadFile("/codeday");
        //Google Drive stuff
        //handlers.GoogleDriveHandler googleDriveHandler = new handlers.GoogleDriveHandler();
        //googleDriveHandler.autheticateGoogle();
        //googleDriveHandler.uploadFile();
        //Box
      // BoxHandler boxHandler = new BoxHandler();
        //boxHandler.authenticateBox();
        //Box Only uploads one copy of the file else outputs error
        //boxHandler.uploadFile();


    }


}
