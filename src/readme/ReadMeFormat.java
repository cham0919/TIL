package readme;

import java.io.File;

public enum ReadMeFormat {


    HEADER("# TIL (Today I Learned)\n" +
            "\n" +
            "오늘보다 더 나은 내일이 되기 위해 배운 것들을 기록하는 공간입니다.\n" +
            "\n" +
            "<br/>\n"),

    FILE("- [{fileName}]({filePath})\n"){
        @Override
        public String getLine(File file) {
            String fileName = FileUtils.getFileName(file);
            String filePath = FileUtils.getFilePath(file);
            return getLine().replace("{fileName}", fileName)
                    .replace("{filePath}", filePath);
        }
    },

    DIR("\n<br/>\n" +
            "\n" +
            "### \uD83D\uDCCC  {fileName}\n" +
            "\n" +
            "---\n"){
        @Override
        public String getLine(File file) {
            return getLine().replace("{fileName}", file.getName());
        }
    },

    SUB_DIR("- #### {fileName}\n"){
        @Override
        public String getLine(File file) {
            return getLine().replace("{fileName}", file.getName());
        }
    };

    private String line;


    ReadMeFormat(String line) {
        this.line = line;
    }

    public String getLine(File file){
        return getLine();
    }

    public String getLine() {
        return line;
    }






}
