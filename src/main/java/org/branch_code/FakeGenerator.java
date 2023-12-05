package org.branch_code;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import java.util.stream.IntStream;

public class FakeGenerator {
    private static final int FAKE_ROWS_LENGTH = 5000000;

    public static void generate() throws IOException {
        // Faker faker = new Faker();
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(PathConstants.SOURCE_PATH+PathConstants.SOURCE_FILE_NAME), true));
        IntStream.range(0, FAKE_ROWS_LENGTH)
                .forEach(index -> {
                    try {
                        writer.write(UserNameGenerator.getUserText(null) + ":" + UUID.randomUUID().toString().replace("-", "") + "\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        writer.close();
    }
}
