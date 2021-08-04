package readme;

import java.io.File;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class TILFileFetcher {

    Predicate<File> checkDir = (file) -> file.isDirectory();
    Predicate<String> checkTILDirName = (name) -> isDirName(name);
    BiPredicate<File, String> checkTILDir = (file, name) -> checkDir.test(file) && checkTILDirName.test(name);

    public File[] getFiles(){
        File TILDir =  new File("./");
        return getDirArray(TILDir);
    }

    private File[] getDirArray(File file) {
        return file.listFiles((dir, name) -> checkTILDir.test(dir,name));
    }

    private boolean isDirName(String str){
        String[] strArray = str.split("\\.");
        return strArray[0].matches("[0-9]+[\\\\.]?[0-9]*");
    }
}
