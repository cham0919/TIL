package readme;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class TILFileFetcher {

    Predicate<File> checkDir = (file) -> file.isDirectory();
    Predicate<String> checkTILDirName = (name) -> isDirName(name);
    BiPredicate<File, String> checkTILDir = (file, name) -> checkDir.test(file) && checkTILDirName.test(name);
    Comparator<File> compateToFilePrefixNumber = (o1, o2) -> {
        String o1Name = o1.getName();
        String o2Name = o2.getName();
        return Integer.valueOf(o1Name.substring(0, o1Name.indexOf("."))).compareTo(
                Integer.valueOf(o2Name.substring(0, o2Name.indexOf(".")))
        );
    };

    public File[] getFiles(){
        File TILDir =  new File("./");
        File[] files = getDirArray(TILDir);
        return Arrays.stream(files).sorted(compateToFilePrefixNumber)
        .toArray(File[]::new);
    }

    private File[] getDirArray(File file) {
        return file.listFiles((dir, name) -> checkTILDir.test(dir,name));
    }

    private boolean isDirName(String str){
        String[] strArray = str.split("\\.");
        return strArray[0].matches("[0-9]+[\\\\.]?[0-9]*");
    }
}
