package org.poo.cb;

/**
 * Clasa CommandExchangeMoney este folosita pentru a schimba bani intre doua conturi bancare.
 * Are ca atribute un string care reprezinta email-ul utilizatorului, doua obiecte de tip Currency care reprezinta monedele conturilor,
 * un double care reprezinta suma de bani care va fi schimbata si un obiect de tip BankObserver care reprezinta observatorul.
 */
public class CommandExchangeMoney extends Command {

    private String email;
    private Currency currency1;
    private Currency currency2;
    private double amount;

    BankObserver bankObserver;


    public CommandExchangeMoney(String email, Currency currency1, Currency currency2, double amount) {
        this.email = email;
        this.currency1 = currency1;
        this.currency2 = currency2;
        this.amount = amount;
    }

    /**
     * Metoda execute este folosita pentru a schimba bani intre doua conturi bancare.
     * @param s1 - numele fisierului cu informatii despre cursul valutar
     * @param s2 - numele fisierului cu informatii despre valoarea actiunilor
     * Acesti parametri sunt necesari pentru a putea accesa instanta clasei BankingApp.
     */
    @Override
    public void execute(String s1, String s2) {
        bankObserver = new BankObserver(this);
        /*
        Verificam daca utilizatorul are suficienti bani in primul cont pentru a face schimbul si notificam observatorul in caz contrar.
         */
        if (BankingApp.getInstance(s1, s2).users.get(email).getPortfolio().accounts.get(currency1).getBalance() < amount) {
            setMessage("Insufficient amount in account " + this.currency1 + " for exchange");
            return;
        }
        /*
        Preluam valorile cursului valutar din map-ul exchangeRates si calculam suma de bani schimbata.
         */
        User user = BankingApp.getInstance(s1, s2).users.get(email);
        MapFromFile<?> exchangeRates = BankingApp.getInstance(s1, s2).getExchangeRates();
        ExchangeRates exchangeRates1 = (ExchangeRates) exchangeRates;
        double rate = exchangeRates1.exchangeRates.get(currency2).get(currency1);
        double amountExchanged = amount * rate;
        /*
        Daca utilizatorul nu este premium, va plati comision de 1% din suma schimbata.
         */
        if (!user.isPremium()) {
            if (amountExchanged > (user.getPortfolio().accounts.get(currency1).getBalance() * 0.5)) {
                user.getPortfolio().accounts.get(currency1).setBalance(user.getPortfolio().accounts.get(currency1).getBalance() - amountExchanged * 0.01);
            }
            if (BankingApp.getInstance(s1, s2).users.get(email).getPortfolio().accounts.get(currency1).getBalance() < amount) {
                setMessage("Insufficient amount in account " + this.currency1 + " for exchange");
                return;
            }
        }
        /*
        Schimbam suma de bani intre cele doua conturi.
         */
        user.getPortfolio().accounts.get(currency1).setBalance(user.getPortfolio().accounts.get(currency1).getBalance() - amountExchanged);
        user.getPortfolio().accounts.get(currency2).setBalance(user.getPortfolio().accounts.get(currency2).getBalance() + amount);
    }

}
