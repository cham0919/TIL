package readme;

import java.io.File;
import java.io.IOException;

public class ContentsProvider {

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
        StringBuilder sb = new StringBuilder(ReadMeFormat.HEADER.getLine());
        for (File file : files) {
            sb.append(dirToContent(file));
            dfsFiles(sb, file.listFiles(), "");
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
            dfsFiles(sb, file.listFiles(), prefix + "  ");
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
