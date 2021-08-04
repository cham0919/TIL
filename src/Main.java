import readme.CommandLineExecutor;
import readme.ContentsProvider;
import readme.ReadMeUpdate;
import readme.TILFileFetcher;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        TILFileFetcher fileFetcher = new TILFileFetcher();
        ContentsProvider contentsProvider = new ContentsProvider();
        ReadMeUpdate readMe = new ReadMeUpdate();

        File[] files = fileFetcher.getFiles();
        String contents = contentsProvider.execute(files);
        readMe.readMeUpdate(contents);

        CommandLineExecutor.execute("git add README.md");
        CommandLineExecutor.execute("git commit -m \"doc. README Update\"");
        CommandLineExecutor.execute("git push origin main");
    }

}
