package agh.ics.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

public class ReadParameters {
    private static final String FILE = "Projekt_obiektowe_2024_drozdz_wietrzny/src/main/resources/setup.csv";

    public static List<String[]> read() throws FileNotFoundException {
        List<String[]> options = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                options.add(line.split(" "));
            }
        }

        return options;
    }
    public static void insertData(String[] settings) throws Exception {
        FileWriter fileWriter = new FileWriter(FILE, true);

        String line = String.join(" ", settings);
        line =line + "\n";
        fileWriter.write(line);

        fileWriter.flush();
        fileWriter.close();
    }
    public static String[] searchByName(String identifier) throws FileNotFoundException {
        List<String[]> setups = read();
        for (String[] setup : setups) {
            if (setup[0].equals(identifier)) {
                return Arrays.copyOfRange(setup, 0, setup.length);
            }
        }
        return null;
    }
    public static String[] getAllNames() throws FileNotFoundException {
        List<String[]> setups = read();
        String[] nameList = new String[setups.size() - 1];
        for (int i = 0; i < nameList.length; i++) {
            nameList[i] = setups
                    .get(i + 1)[0];
        }
        return nameList;
    }
}