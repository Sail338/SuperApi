package handlers;

import Gui.GuiForm;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Hari on 2/14/2015.
 */
public class GoogleDriveHandler {
    Drive Service;
    String code;
    JsonFactory factory;
    HttpTransport transport;
    GoogleAuthorizationCodeFlow flow;
   // GuiForm guiForm = new GuiForm();
     String ID = "476186802144-3e7o48beciln3m72t2j0nipt91hgfqf4.apps.googleusercontent.com";
     String Secret = "tqnQTykdXr6irZjypv7h4nb5";
     String REDIRECT_URL = "urn:ietf:wg:oauth:2.0:oob";
    public void autheticateGoogle() throws IOException {
        ID = "476186802144-3e7o48beciln3m72t2j0nipt91hgfqf4.apps.googleusercontent.com";
        Secret = "tqnQTykdXr6irZjypv7h4nb5";
        REDIRECT_URL = "urn:ietf:wg:oauth:2.0:oob";
        transport = new NetHttpTransport();
        factory = new JacksonFactory();
        flow = new GoogleAuthorizationCodeFlow.Builder(transport, factory, ID, Secret, Arrays.asList(DriveScopes.DRIVE)).setAccessType("online").setApprovalPrompt("auto").build();
        code = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URL).build();

        System.out.print(code);
        //System.out.println("Please Goto:"+ code);
        //System.out.println("Please Coopy the URL Code and paste it");
    }

    public void startAutheicationProcess(String s) throws IOException{


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
       // String input = bufferedReader.readLine();
        String input = s;
        GoogleTokenResponse response = flow.newTokenRequest(input).setRedirectUri(REDIRECT_URL).execute();
        GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);
        Service = new Drive.Builder(transport,factory,credential).build();




    }
    public void uploadFile()  throws IOException{
        FileHandler fileHandler = new FileHandler();
        File file = new File();
        file.setTitle(fileHandler.getFile().toString());
        file.setDescription("Your Document");
        file.setMimeType("/*");
        FileContent media = new FileContent("/*",fileHandler.getFile());
        File push = Service.files().insert(file,media).execute();
        System.out.println("uploaded" + "  " + push.getId());

    }
    public void downloadFile(String s) throws IOException {
        File badfile = new File();
        badfile.get(s);

    }   //work in porgress
    FileHandler fileHandler = new FileHandler();
    File file = new File();
    public String getCode() {
        return code;
    }
    public void searchFile(String s) {
        File searchFile = new File();
        searchFile.setTitle(s);
        try {
            int bigList = Service.files().list().getMaxResults();
            System.out.println(bigList);
        }
        catch (Exception l) {
            l.printStackTrace();
        }

    }
    public void getRefreshToken() {

    }
}

