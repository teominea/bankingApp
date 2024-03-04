package org.poo.cb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Clasa ExchangeRates este folosita pentru a citi din fisier informatii despre cursul valutar.
 * Are ca atribute un map de tipul Map<Currency, Map<Currency, Double>> care reprezinta cursul valutar.
 */
public class ExchangeRates implements MapFromFile<Currency> {
    public Map<Currency, Map<Currency, Double>> exchangeRates = new HashMap<>();

    public ExchangeRates() {
    }

    /**
     * Metoda parseFile este folosita pentru a citi din fisier informatii despre cursul valutar.
     * @param exchangeFile - numele fisierului din care se citesc informatiile
     * @return - map-ul de tipul Map<Currency, Map<Currency, Double>> care reprezinta cursul valutar
     */
    @Override
    public Map<Currency, Map<Currency, Double>> parseFile(String exchangeFile) {
        /*
        Deschidem fisierul si citim informatiile.
         */
        try (BufferedReader br = new BufferedReader(new FileReader(exchangeFile))) {
            String line;
            String header = br.readLine();
            /*
            Pe prima linie vor fi afisate monedele, asa ca le citim si le salvam intr-un array de tipul Currency.
             */
            String[] currenciesString = header.split(",");
            Currency[] currencies = new Currency[currenciesString.length];
            for(int i = 1; i < currenciesString.length; i++) {
                currencies[i] = Currency.valueOf(currenciesString[i]);
            }
            /*
            Pentru fiecare linie citita vom crea un map de tipul Map<Currency, Double> care va contine cursul valutar pentru fiecare moneda.
             */
            while ((line = br.readLine()) != null) {
                String[] exchange = line.split(",");
                Currency currency = Currency.valueOf(exchange[0]);
                Map<Currency, Double> rates = new HashMap<>();
                for(int i = 1; i < currencies.length; i++) {
                    rates.put(currencies[i], Double.parseDouble(exchange[i]));
                }
                /*
                Adaugam map-ul in map-ul principal la cheia corespunzatoare monedei.
                 */
                exchangeRates.put(currency, rates);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return exchangeRates;
    }
}
