package org.poo.cb;

import java.util.*;

/**
 * Clasa CommandListPortfolio este folosita pentru a afisa portofoliul unui utilizator.
 * Are ca atribute un string care reprezinta emailul utilizatorului si un obiect de tip BankObserver care reprezinta observatorul.
 */
public class CommandListPortfolio extends Command {
    String email;

    BankObserver observer;

    public CommandListPortfolio(String email) {
        this.email = email;
    }

    /**
     * Metoda execute este folosita pentru a afisa portofoliul unui utilizator.
     * @param s1 - numele fisierului cu informatii despre cursul valutar
     * @param s2 - numele fisierului cu informatii despre valoarea actiunilor
     * Acesti parametri sunt necesari pentru a putea accesa instanta clasei BankingApp.
     */
    @Override
    public void execute (String s1, String s2) {
        observer = new BankObserver(this);
        /*
        Verificam daca utilizatorul exista si notificam observatorul in caz contrar.
         */
        if (BankingApp.getInstance(s1, s2).users.get(email) == null) {
            setMessage("User with email " + email +  " does not exist");
            return;
        }
        /*
        Construim un string cu toate informatiile dorite.
         */
        User user = BankingApp.getInstance(s1, s2).users.get(email);
        StringBuilder portfolio = new StringBuilder();
        portfolio.append("{");
        portfolio.append("\"stocks\":[");
        if (user.getPortfolio().actions.isEmpty())
            portfolio.append("[");
        for (Map.Entry<String, Integer> entry : user.getPortfolio().actions.entrySet()) {
            String stock = entry.getKey();
            Integer amount = entry.getValue();
            portfolio.append("{" + "\"stockName\":\"" + stock + "\",");
            portfolio.append("\"amount\":" + amount + "},");
        }
        portfolio.deleteCharAt(portfolio.length() - 1);
        portfolio.append("],");
        portfolio.append("\"accounts\":" + "[");
        /*
        Sortam map-ul de conturi in functie de ordinea monedelor (asa cum apare in enum) si construim string-ul.
         */
        Comparator <Currency> comparator = Comparator.comparing(Enum::ordinal);
        Map<Currency, Account> sortedMap = new TreeMap<>(comparator);
        sortedMap.putAll(user.getPortfolio().accounts);

        for (Currency currency : sortedMap.keySet()) {
            portfolio.append("{" + "\"currencyName\":\"" + currency + "\",");
            portfolio.append("\"amount\":\"" + String.format("%.2f", user.getPortfolio().accounts.get(currency).getBalance()) + "\"},");
        }
        portfolio.deleteCharAt(portfolio.length() - 1);
        portfolio.append("]}");
        /*
        Setam mesajul observatorului cu string-ul construit.
         */
        setMessage(portfolio.toString());
    }
}
