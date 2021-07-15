package tree;

import java.io.*;

public class TreeFileReader {

    public static void main(String[] args) throws IOException {
        TreeFileReader treeFileReader = new TreeFileReader();
        treeFileReader.fileToString();
    }

    public void fileToString() throws IOException {
        BufferedReader inFile = new BufferedReader(new InputStreamReader(new FileInputStream("./tree.txt"),"EUC-KR"));
        boolean flag = false;
        String sLine = null;
        StringBuilder sb = new StringBuilder();
        while( (sLine = inFile.readLine()) != null ) {
            if (Character.isDigit(sLine.charAt(2))) flag = true;
            if (sLine.charAt(2) == 'o') break;

            if (flag) {
                sb.append(sLine + "\n");
            }
        }
        System.out.println(sb.toString());
    }
}
