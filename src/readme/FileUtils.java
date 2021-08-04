package readme;

import java.io.File;

public class FileUtils {

    public static String getFileName(File file){
        String fileName = file.getName();
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    public static String getFilePath(File file){
        String filePath = file.getPath();
        return filePath.replaceAll(" ", "%20");
    }
}
