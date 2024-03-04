package org.poo.cb;

import java.util.HashMap;
import java.util.Map;


/**
 * Clasa BankingApp este un singleton care contine o lista de utilizatori si doua mapari de tipul MapFromFile
 * care contin informatii despre cursul valutar si valoarea actiunilor.
 */
public class BankingApp {
    private static BankingApp instance = null;

    private final MapFromFile<?> exchangeRates;
    private final MapFromFile<?> stockValues;

    Map<String, User> users;

    /**
     * Constructorul privat al clasei initializeaza lista de useri si maparile de tipul MapFromFile
     * realizate prin intermediul metodei getMapFromFile (Metoda Factory)
     * @param exchangeFile - fisierul cu informatii despre cursul valutar
     * @param stockFile - fisierul cu informatii despre valoarea actiunilor
     */
    private BankingApp(String exchangeFile, String stockFile) {
        this.users = new HashMap<>();
        this.exchangeRates = getMapFromFile(exchangeFile);
        this.stockValues = getMapFromFile(stockFile);
        this.exchangeRates.parseFile(exchangeFile);
        this.stockValues.parseFile(stockFile);
    }

    public static BankingApp getInstance(String exchangeFile, String stockFile) {
        if (instance == null) {
            instance = new BankingApp(exchangeFile, stockFile);
        }
        return instance;
    }

    /**
     * Metoda getMapFromFile este o metoda Factory care returneaza o instanta a unei clase care implementeaza interfata MapFromFile
     * in functie de numele fisierului primit ca parametru
     * @param fileName - numele fisierului
     * @return - o instanta a unei clase care implementeaza interfata MapFromFile
     */
    public MapFromFile<?> getMapFromFile(String fileName) {
        if (fileName.contains("exchangeRates.csv")) {
            return new ExchangeRates();
        }
        if (fileName.contains("stockValues.csv")) {
            return new Stocks();
        }
        return null;
    }

    public MapFromFile<?> getExchangeRates() {
        return this.exchangeRates;
    }

    public MapFromFile<?> getStockValues() {
        return this.stockValues;
    }

    /**
     * Metoda cleanup este folosita pentru a sterge instanta clasei BankingApp si lista de useri
     */
    public void cleanup() {
        instance = null;
        users.clear();
    }

}
