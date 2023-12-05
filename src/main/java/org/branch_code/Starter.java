package org.branch_code;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class Starter {
    public static void main(String[] args) throws IOException {
        Arrays.stream(new File(PathConstants.SOURCE_PATH).listFiles()).forEach(File::delete);

        FakeGenerator.generate();
        FileWorker.init();
        FileWorker.run();
    }
}