package com.theironyard;

import jodd.json.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static ArrayList<Country> countries = new ArrayList<>();
    static HashMap<String, ArrayList<Country>> map = new HashMap<>();

        public static ArrayList<Country> parseCountry() throws FileNotFoundException {

            File f = new File("countries.txt");
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] columns = line.split("\\|");
                Country country = new Country(columns[0], columns[1]);
                countries.add(country);
            }
            return countries;
        }


    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        parseCountry();

         for(Country country: countries){
             String l = country.getName();
             char l2 = l.charAt(0);
             String letter = String.valueOf(l2);
             ArrayList names = new ArrayList();
             String n = country.getName();
             names.add(n);
             map.put(letter, names);

         }

        System.out.println("Type a letter");
        String selection = scanner.nextLine();
        ArrayList selectedCountries = new ArrayList<>();
        for (Country country : countries){
            String n = country.getName();
            if (n.startsWith(selection)){
                selectedCountries.add(n);
            }
        }
        System.out.println(map);
    }

}
