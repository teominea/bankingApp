package org.poo.cb;


import java.util.ArrayList;

/**
 * Clasa CommandBuyStocks este folosita pentru a cumpara actiuni.
 * Are ca atribute un string care reprezinta emailul utilizatorului, un string care reprezinta numele actiunii, un int care reprezinta cantitatea de actiuni
 * si un obiect de tip BankObserver care reprezinta observatorul.
 */
public class CommandBuyStocks extends Command {
    String email;
    String stockName;
    Integer quantity;

    BankObserver observer;

    public CommandBuyStocks(String email, String stockName, int quantity) {
        this.email = email;
        this.stockName = stockName;
        this.quantity = quantity;
    }

    /**
     * Metoda execute este folosita pentru a cumpara actiuni.
     * @param s1 - numele fisierului cu informatii despre cursul valutar
     * @param s2 - numele fisierului cu informatii despre valoarea actiunilor
     * Acesti parametri sunt necesari pentru a putea accesa instanta clasei BankingApp.
     */
    @Override
    public void execute(String s1, String s2) {
        observer = new BankObserver(this);
        /*
        Vom prelua din lista de valori a actiunii ultimul pret si vom calcula pretul total al actiunilor.
         */
        User user = BankingApp.getInstance(s1, s2).users.get(email);
        MapFromFile<?> stocks = BankingApp.getInstance(s1, s2).getStockValues();
        Stocks stockValues = (Stocks) stocks;
        ArrayList<Double> values = stockValues.stockPrices.get(stockName);
        double lastPrice = values.get(values.size() - 1);
        double amount = lastPrice * quantity;
        /*
        Daca utilizatorul nu are suficienti bani in cont pentru a cumpara actiunile, notificam observatorul.
         */
        if (user.getPortfolio().accounts.get(Currency.USD).getBalance() < amount) {
            setMessage("Insufficient amount in account for buying stock");
            return;
        }
        /*
        Daca utilizatorul este premium, va plati 95% din valoarea actiunilor.
         */
        if (user.isPremium()) {
            user.getPortfolio().accounts.get(Currency.USD).setBalance(user.getPortfolio().accounts.get(Currency.USD).getBalance() - (amount * 0.95));
        } else {
            user.getPortfolio().accounts.get(Currency.USD).setBalance(user.getPortfolio().accounts.get(Currency.USD).getBalance() - amount);
        }
        /*
        Daca userul nu are actiuni din aceasta companie, le va cumpara, altfel va adauga la cantitatea existenta.
         */
        if (user.getPortfolio().actions.get(stockName) == null) {
            user.getPortfolio().actions.put(stockName, quantity);
        } else {
            user.getPortfolio().actions.put(stockName, user.getPortfolio().actions.get(stockName) + quantity);
        }
    }
}
