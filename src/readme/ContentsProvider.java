package readme;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;


public class ContentsProvider {

    Comparator<File> fileComparator = (o1, o2) -> {
        if (o1.isDirectory() && !o2.isDirectory()) return 1;
        else if (o2.isDirectory() && !o1.isDirectory()) return -1;
        else return o1.compareTo(o2);
    };

    public static void main(String[] args) throws IOException {
        ContentsProvider provider = new ContentsProvider();
        File file = new File("./");
        TILFileFetcher tree = new TILFileFetcher();
        File[] files = tree.getFiles();
        System.out.println(provider.execute(files));
    }

    public String execute(File[] dirs) {
        return getContents(dirs);
    }

    public String getContents(File[] files){
        StringBuilder sb = new StringBuilder(ReadMeFormat.HEADER.getLine(null));
        for (File file : files) {
            sb.append(dirToContent(file));
            File[] sortedFiles = Arrays.stream(file.listFiles()).sorted(fileComparator).toArray(File[]::new);
            dfsFiles(sb, sortedFiles, "");
        }
        return sb.toString();
    }

    private void dfsFiles(StringBuilder sb, File[] files, String prefix) {
        for (File file : files) {
            dfsFile(sb, file, prefix);
        }
    }

    private void dfsFile(StringBuilder sb, File file, String prefix) {
        if (file.isDirectory()) {
            sb.append(subDirToContent(file));
            File[] files = Arrays.stream(file.listFiles()).sorted(fileComparator).toArray(File[]::new);
            dfsFiles(sb, files, prefix + "  ");
        } else {
            sb.append(fileToContent(file, prefix));
        }
    }

    private String fileToContent(File file, String prefix){
        return  prefix + ReadMeFormat.FILE.getLine(file);
    }

    private String dirToContent(File file){
        return ReadMeFormat.DIR.getLine(file);
    }

    private String subDirToContent(File file){
        return ReadMeFormat.SUB_DIR.getLine(file);
    }
}
