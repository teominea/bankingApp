package org.poo.cb;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Clasa Portfolio este folosita pentru a retine informatii despre conturile utilizatorilor.
 * Are ca atribute un map de tipul Map<Currency, Account> care reprezinta conturile utilizatorilor
 * si un map de tipul Map<String, Integer> care reprezinta actiunile utilizatorilor.
 */
public class Portfolio {
    Map<Currency, Account> accounts;
    Map<String, Integer> actions;

    public Portfolio() {
        this.accounts = new HashMap<>();
        this.actions = new LinkedHashMap<>();
    }
}
