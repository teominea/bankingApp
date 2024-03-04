package org.poo.cb;


/**
 * Clasa CommandAddMoney este folosita pentru a adauga bani in contul unui utilizator.
 * Are ca atribute un String userEmail care reprezinta adresa de email a utilizatorului, un obiect de tip Currency care reprezinta moneda contului
 * un double care reprezinta suma de bani care urmeaza sa fie adaugata si un obiect de tip BankObserver care reprezinta observatorul.
 */
public class CommandAddMoney extends Command {

    private String userEmail;
    private Currency currency;
    private double amount;

    BankObserver bankObserver;

    public CommandAddMoney(String userEmail, Currency currency, double amount) {
        this.userEmail = userEmail;
        this.currency = currency;
        this.amount = amount;
    }

    /**
     * Metoda execute este folosita pentru a adauga bani in contul unui utilizator.
     * @param s1 - numele fisierului cu informatii despre cursul valutar
     * @param s2 - numele fisierului cu informatii despre valoarea actiunilor
     * Acesti parametri sunt necesari pentru a putea accesa instanta clasei BankingApp.
     */
    @Override
    public void execute(String s1, String s2) {
        bankObserver = new BankObserver(this);
        /*
        Adaugam bani in contul utilizatorului.
        (Se presupune ca aceasta operatie va fi mereu efectuata cu succes.)
         */
        User user = BankingApp.getInstance(s1, s2).users.get(userEmail);
        user.getPortfolio().accounts.get(currency).setBalance(user.getPortfolio().accounts.get(currency).getBalance() + amount);
    }
}
