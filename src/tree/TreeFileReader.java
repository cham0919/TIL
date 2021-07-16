package tree;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TreeFileReader {

    public static void main(String[] args) throws IOException {
        TreeFileReader treeFileReader = new TreeFileReader();
        treeFileReader.fileToString();

    }

    private final static String prefix = "├─";
    private final static String depth = "─";
    private final static String line = "│";


    public String fileToString() throws IOException {
        BufferedReader inFile = new BufferedReader(new InputStreamReader(new FileInputStream("./tree.txt"),"EUC-KR"));
        boolean flag = false;
        String sLine = null;
        StringBuilder sb = new StringBuilder();
        Node node = new Node("TIL", true);

        while( (sLine = inFile.readLine()) != null ) {

            if (sLine.startsWith(prefix)) {
                if (Character.isDigit(sLine.charAt(2))) {
                    flag = true;
                    node = new Node(extractPackageName(sLine), true);
                } else if (flag && !Character.isDigit(sLine.charAt(2))){
                    break;
                }
            }


            if (flag) {
                sLine = sLine.trim();
                if (sLine.equalsIgnoreCase(line)) {
                    sb.append(sLine + "<br/>\n");
                } else {
                    if (sLine.endsWith(".md")) {
                        String data = extractFileName(sLine);
                        sLine = sLine.replace(data, replaceLink(sLine, data, node));
                        sb.append(sLine + "<br/>\n");
                    } else if (sLine.contains(depth)){
                        String data = extractPackageName(sLine);
                        if (!node.getData().equals(data)) {
                            node = node.addChild(new Node(data, false));
                        }
                        replaceLink(sLine, data, node);
                        sLine = sLine.replace(data, replaceLink(sLine, data, node));
                        sb.append(sLine + "<br/>\n");
                    }
                }
            }
        }

        return sb.toString();
    }


    private String extractFileName(String sLine) {
        return sLine.substring(sLine.lastIndexOf("  ")+1).trim();
    }

    private String extractPackageName(String sLine) {
        return sLine.substring(sLine.lastIndexOf(depth)+1).trim();
    }

    private String replaceLink(String origin, String data, Node node) {
        String linkStr = "[" + data + "](./";
        StringBuilder link = new StringBuilder(")");
        if (!data.equals(node.getData())) {
            link.insert(0,"/" + data.replaceAll(" ", "%20"));
        }
        while (!node.isIfRoot()) {
            link.insert(0,"/" + node.getData().replaceAll(" ", "%20"));
            node = node.getParentTree();
        }
        link.insert(0, node.getData().replaceAll(" ", "%20"));

        return linkStr + link.toString();
    }
}
