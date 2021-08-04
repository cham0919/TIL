package readme;

import java.io.*;

public class ReadMeUpdate {

    public static void main(String[] args) {
        ReadMeUpdate readMeUpdate = new ReadMeUpdate();
        readMeUpdate.readMeUpdate("ㅋㅋ");
    }

    public void readMeUpdate(String contents) {
        File file = new File("./README.md");
        try(FileWriter writer = new FileWriter(file)) {
            writer.write(contents);
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
