package org.poo.cb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Clasa Stocks este folosita pentru a citi informatii despre actiuni dintr-un fisier si a le salva intr-un map.
 * Are ca atribute un map de tipul Map<String, ArrayList<Double>> care reprezinta preturile actiunilor.
 */
public class Stocks implements MapFromFile<String>{
    Map <String, ArrayList<Double>> stockPrices = new HashMap<>();

    /**
     * Metoda parseFile este folosita pentru a citi din fisier informatii despre actiuni si a le salva intr-un map.
     * @param stockFile - numele fisierului din care se citesc informatiile
     * @return - map-ul de tipul Map<String, ArrayList<Double>> care reprezinta preturile actiunilor
     */
    @Override
    public Map<String, ArrayList<Double>> parseFile (String stockFile) {
        /*
        Deschidem fisierul si citim informatiile.
         */
        try (BufferedReader br = new BufferedReader(new FileReader(stockFile))) {
            /*
            Pe prima linie vor fi afisate datele, acestea vor fi insa in ordine cronologica, asa ca putem crea
            un array de tip Double pentru fiecare valoare a actiunii, care va fi in mod implicit sortat dupa data.
             */
            String line;
            String header = br.readLine();
            /*
            Pentru fiecare linie citita vom retine intr-un String numele actiunii si intr-un array de tipul Double
            preturile actiunii.
             */
            while ((line = br.readLine()) != null) {
                String[] stock = line.split(",");
                String stockName = stock[0];
                ArrayList<Double> prices = new ArrayList<>();
                for(int i = 1; i < stock.length; i++) {
                    prices.add(Double.parseDouble(stock[i]));
                }
                /*
                Adaugam array-ul de preturi in map la cheia corespunzatoare numele actiunii.
                 */
                stockPrices.put(stockName, prices);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockPrices;
    }
}
