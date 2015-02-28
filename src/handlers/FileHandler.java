package handlers;

import java.io.File;

/**
 * Created by Hari on 2/14/2015.
 */
public class FileHandler {
    public static File file;

    public static void setFile(String path) {

     file = new File(path);

    }
    public static File getFile() {
        return file;
    }
}
