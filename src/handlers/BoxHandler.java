package handlers;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxUser;
import com.box.sdk.ProgressListener;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Hari on 2/14/2015.
 */
public class BoxHandler {
    BoxAPIConnection boxAPIConnection;
    public void authenticateBox() {
        final String DeveloperToken = "jlULLzpqYXlHXAQ3Lz0YRGVS5emSsv2b";
        final String Id = "jimwbsbvtu1koldxkruwfvwbxwtjb2o8";
        final String Secret = "zMlisikdk6aq8UlDx99U58x30jEyCBBc";


        boxAPIConnection = new BoxAPIConnection(DeveloperToken);
        BoxUser.Info boxuser = BoxUser.getCurrentUser(boxAPIConnection).getInfo();

        System.out.format(boxuser.getName(),boxuser.getLogin());

    }
    public void uploadFile() throws IOException {
        FileHandler handler = new FileHandler();
        BoxFolder boxFolder = new BoxFolder(boxAPIConnection, "root");
        if (boxFolder == null) {
            System.out.print("BUSHot");
        }
        BoxFolder rootfolder = BoxFolder.getRootFolder(boxAPIConnection);
        FileInputStream stream = new FileInputStream(handler.getFile().toString());
        rootfolder.uploadFile(stream, handler.getFile().getName().toString(), 1024, new ProgressListener() {
            @Override
            public void onProgressChanged(long numBytes, long totalBytes) {
                double progressbar = numBytes/totalBytes;
                System.out.println(progressbar);
            }
        });
        //Box Only uploads one copy of the file else outputs error
        System.out.println("Uploaded" + " " + handler.getFile().getName().toString());

stream.close();
    }
    public void searchFile() {
    }


}

