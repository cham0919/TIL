package readme;

import java.io.File;

public enum ReadMeFormat {


    HEADER("# TIL (Today I Learned)\n" +
            "\n" +
            "오늘보다 더 나은 내일이 되기 위해 배운 것들을 기록하는 공간입니다.\n" +
            "\n" +
            "<br/>\n"){
        @Override
        public String getLine(File file) {
            return line;
        }
    },

    FILE("- [{fileName}]({filePath})\n"){
        @Override
        public String getLine(File file) {
            String fileName = FileUtils.getFileName(file);
            String filePath = FileUtils.getFilePath(file);
            return line.replace("{fileName}", fileName)
                    .replace("{filePath}", filePath.replace("\\", "/"));
        }
    },

    DIR("\n<br/>\n" +
            "\n" +
            "## \uD83D\uDCCC  {fileName}\n" +
            "\n"){
        @Override
        public String getLine(File file) {
            return line.replace("{fileName}", file.getName());
        }
    },

    SUB_DIR("- ### {fileName}\n"){
        @Override
        public String getLine(File file) {
            return line.replace("{fileName}", file.getName());
        }
    };

    protected String line;
    public abstract String getLine(File file);

    ReadMeFormat(String line) {
        this.line = line;
    }


}
