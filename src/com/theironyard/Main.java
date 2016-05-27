package com.theironyard;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static ArrayList<Country> countries = new ArrayList<>();
    static HashMap<String, ArrayList<Country>> map = new HashMap();

    public static void parseCountry() throws FileNotFoundException {
        File f = new File("countries.txt");
        Scanner fileScanner = new Scanner(f);
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\|");
            Country country = new Country(columns[0], columns[1]);
            countries.add(country);
        }
        for (Country country : countries){
            String name = country.getName();
            String firstLetter = String.valueOf(name.charAt(0));
            if (!map.containsKey(firstLetter)) {
                map.put(firstLetter, new ArrayList<>());
            }
            ArrayList<Country> arr = map.get(firstLetter);
            arr.add(country);
        }
    }

    public static void selectAndSave() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type a letter");
        String selection = scanner.nextLine();
        int selectionLength = selection.length();
        if (selectionLength == 1) {
            ArrayList<Country> selectedCountries = map.get(selection);
            JsonSerializer serializer = new JsonSerializer();
            String json = serializer.serialize(selectedCountries);
            File f = new File(selection + "_countries.txt");
            try {
                FileWriter fw = new FileWriter(f);
                fw.write(json);
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new Exception("Type one and only one letter.");
        }
    }
    public static void main(String[] args) throws Exception {

        parseCountry();

        selectAndSave();

        System.out.println("Done.");
    }

}
