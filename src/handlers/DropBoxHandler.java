package handlers;

import com.dropbox.core.*;

import java.io.*;
import java.util.List;
import java.util.Locale;

/**
 * Created by Hari on 2/14/2015.
 */
public class DropBoxHandler {
    DbxClient client;
    String authorize;
    DbxAppInfo dbxAppInfo;
    DbxRequestConfig config;
    DbxWebAuthNoRedirect webAuth;
    List<DbxEntry> badList;
    DbxEntry.File downloadfile;
    public String getDropBoxStrin() {
        return dropBoxStrin;
    }

    public void setDropBoxStrin(String dropBoxStrin) {
        this.dropBoxStrin = dropBoxStrin;
    }

    private String dropBoxStrin;
    public void authenticateDropBox() throws IOException, DbxException {

        final String APP_KEY = "MY APP KEY";
        final String APP_SECRET = "SECRET KEY";

         dbxAppInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
         config = new DbxRequestConfig("uploader", Locale.getDefault().toString());
         webAuth = new DbxWebAuthNoRedirect(config, dbxAppInfo);
         authorize = webAuth.start();
    }
    public void startProcess(String jeses) throws IOException, DbxException{
       // System.out.println("1. Go to: " + authorize);
       // System.out.println("2. Click \"Allow\" (you might have to log in first)");
       // System.out.println("3. Copy the authorization code.");
        //String code = new BufferedReader(new InputStreamReader(System.in)).readLine().trim();
        String code = jeses;

        DbxAuthFinish authFinish = webAuth.finish(code);
        String accessToken = authFinish.accessToken;
        client = new DbxClient(config, accessToken);


    }
    public DbxClient getClient() {
        return  client;
    }
    public void upLoadFile(String pathInDropBox) throws IOException,DbxException,FileNotFoundException {

        FileHandler fileHandler = new FileHandler();

            FileInputStream stream = new FileInputStream(fileHandler.getFile());
            DbxEntry.File uploadFile = getClient().uploadFile("//"+fileHandler.getFile().getName().toString(), DbxWriteMode.add(), fileHandler.getFile().length(), stream);
        System.out.println("uploaded" + "  "+ uploadFile.toString());
        stream.close();

}
    public void downloadFile(String filename) throws DbxException, IOException{
        FileOutputStream stream = new FileOutputStream(filename.replace("/",""));
        
         downloadfile = getClient().getFile("//"+filename,null,stream);
        System.out.println("Downloading" + downloadfile.toString());
        stream.close();
    }
    public String getAuthorize(){
        return authorize;
    }
    public void searchFile(String shit) throws DbxException{
     badList = getClient().searchFileAndFolderNames("/", shit);
        System.out.println(badList.toString());
    }
    public List<DbxEntry> getBadList() {
        return  badList;
    }
    public DbxEntry.File dbxEntry () {
        return downloadfile;
    }
}









