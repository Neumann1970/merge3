package org.branch_code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FileWorker {

    private static int last_count;
    private static final int CHUNKS = 64;

    {
        last_count = 0;
    }

    private static BufferedWriter[] writers = new BufferedWriter[CHUNKS];

    private static Set<String> sorter = new TreeSet<>();

    public static void init() throws IOException {
        cleanFiles();
        splitFile(CHUNKS);
    }

    public static void run() throws IOException {
        System.out.println("START SORTING");
        sortForChunks();
        mergeSorting();
        System.out.println("MERGE SORTING DONE!!!");
    }

    public static void splitFile(int i) throws IOException {
        for (int counter = 0; counter < i; counter++) {
            File file = new File(PathConstants.DEMO_PATH + PathConstants.DEMO_FILE_NAME + (counter + 1) + PathConstants.EXTENTION);
            file.createNewFile();
        }
        IntStream.range(0, i).forEach(index -> {
            try {
                writers[index] = new BufferedWriter(new FileWriter(new File(PathConstants.DEMO_PATH + PathConstants.DEMO_FILE_NAME + (index + 1) + PathConstants.EXTENTION), true));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        try (Stream<String> lines = java.nio.file.Files.lines(Paths.get(PathConstants.SOURCE_PATH + PathConstants.SOURCE_FILE_NAME))) {
            lines.forEach(line -> {
                try {
                    roundRobinWriter(writers, line);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        closeBuffer();
    }

    /*
     * Распределенное деленние элементов по файлам из истонника
     * */
    public static void roundRobinWriter(BufferedWriter[] writers, String line) throws IOException {
        writers[last_count].write(line + "\n");
        if (last_count == writers.length - 1) {
            last_count = 0;
        } else {
            last_count++;
        }
    }

    /*
     * Полученные файлы сортируются для дальнейшей обработки
     * */

    public static void sortForChunks() throws IOException {
        fileCreator(CHUNKS, PathConstants.SORTED_PATH, PathConstants.SORTED_FILE_NAME);
        Set<String> stringSet = new TreeSet<>();

        for (int i = 0; i < CHUNKS; i++) {
            try (Stream<String> lines = java.nio.file.Files.lines(Paths.get(PathConstants.DEMO_PATH + PathConstants.DEMO_FILE_NAME + (i + 1) + PathConstants.EXTENTION))) {
                lines.forEach(line -> {
                    stringSet.add(line);
                });
            }
            writers[i] = new BufferedWriter(new FileWriter(new File(PathConstants.SORTED_PATH + PathConstants.SORTED_FILE_NAME + (i + 1) + PathConstants.EXTENTION), true));
            System.out.println("Sorting: " + PathConstants.SORTED_FILE_NAME + (i + 1) + PathConstants.EXTENTION);

            for (String s : stringSet) {

                writers[i].write(s + "\n");
                writers[i].flush();
            }
            writers[i].close();
            stringSet.clear();
        }
    }

    /*
     * Сборка всех разбитых отсортированых файлов
     * построчное чтение из каждого из всех открытых файлов
     *
     * */
    private static void mergeSorting() throws IOException {
        System.out.println("START BUILDING: ");
        fileCreator(0, PathConstants.OUT_PATH, PathConstants.OUT_FILE_NAME);

        ArrayList<BufferedReader> readers = new ArrayList<>();

        for (int i = 0; i < CHUNKS; i++) {
            readers.add(i, new BufferedReader(new FileReader(new File(PathConstants.SORTED_PATH + PathConstants.SORTED_FILE_NAME + (i + 1) + PathConstants.EXTENTION))));
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(PathConstants.OUT_PATH + PathConstants.OUT_FILE_NAME + PathConstants.EXTENTION), true));
        boolean isEmpty = false;
        while (!isEmpty) {
            for (BufferedReader reader : readers) {
                String line = reader.readLine();
                mergeHelper(line, writer);
                writer.flush();
                if (line == null) {
                    isEmpty = true;
                }
            }
        }
        writer.close();
    }

    /*
     *
     * Добавление по одной записи  из каждого куска/файла
     * далее сортировка в TreeMap, с подледующей записю в файл через каждые 4096 элементов
     *
     * */
    private static void mergeHelper(String element, BufferedWriter writer) throws IOException {
        if (element != null) {
            if (sorter.size() < 4096) {
                sorter.add(element);
            } else {
                Iterator<String> it = sorter.iterator();
                while (it.hasNext()) {
                    String value = it.next();
                    writer.write(value + "\n");
                    writer.flush();
                    it.remove();
                    System.out.println(value);
                }
            }
        }
    }

    //-----------UTILS----METHODS--------------------

    private static void fileCreator(int quantity, String path, String name) {
        IntStream.range(0, quantity).forEach(index -> {
            File file = new File(path + name + (index + 1) + PathConstants.EXTENTION);
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static void closeBuffer() throws IOException {
        for (BufferedWriter writer : writers) {
            writer.flush();
            writer.close();
        }
    }

    private static void cleanFiles() {
        Arrays.stream(new File(PathConstants.DEMO_PATH).listFiles()).forEach(File::delete);
        Arrays.stream(new File(PathConstants.SORTED_PATH).listFiles()).forEach(File::delete);
        Arrays.stream(new File(PathConstants.OUT_PATH).listFiles()).forEach(File::delete);
    }
}
