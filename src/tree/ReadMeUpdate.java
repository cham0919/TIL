package tree;

import java.io.*;

public class ReadMeUpdate {

    public static void main(String[] args) {
        ReadMeUpdate readMeUpdate = new ReadMeUpdate();
        readMeUpdate.readMeUpdate();
    }

    private final static String prefixREADME =
            "# TIL (Today I Learned)\n" +
                    "\n" +
                    "오늘보다 더 나은 내일이 되기 위해 배운 것들을 기록하는 공간입니다.\n" +
                    "\n" +
                    "# Tree\n";

    public void readMeUpdate() {
        File file = new File("./README.md");
        TreeFileReader tree = new TreeFileReader();
        FileWriter writer = null;
        try{
            new FileOutputStream("./README.md").close();
            //입력 스트림 생성
            writer = new FileWriter(file, true);
            writer.write(prefixREADME);
            writer.write(tree.fileToString());

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally {

            try {
                if(writer != null) {
                    writer.flush();
                    writer.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }





//
//        try {
//            // 기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정한다.
//            writer = new FileWriter(file, true);
//            writer.write(message);
//            writer.flush();
//
//            System.out.println("DONE");
//        } catch(IOException e) {
//            e.printStackTrace();
//        } finally {
////            try {
////                if(writer != null) writer.close();
////            } catch(IOException e) {
////                e.printStackTrace();
////            }
////        }


    }
}
