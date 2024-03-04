package org.poo.cb;

/**
 * Clasa CommandBuyPremium este folosita pentru a cumpara optiunea de premium a unui utilizator.
 * Are ca atribute un obiect de tip BankObserver care reprezinta observatorul si un String care reprezinta email-ul utilizatorului.
 */
public class CommandBuyPremium extends Command {
    BankObserver bankObserver;

    private String email;

    public CommandBuyPremium(String email) {
        this.email = email;
    }

    /**
     * Metoda execute este folosita pentru a cumpara optiunea de premium a unui utilizator.
     * @param s1 - numele fisierului cu informatii despre cursul valutar
     * @param s2 - numele fisierului cu informatii despre valoarea actiunilor
     * Acesti parametri sunt necesari pentru a putea accesa instanta clasei BankingApp.
     */
    @Override
    public void execute(String s1, String s2) {
        bankObserver = new BankObserver(this);
        /*
        Verificam daca utilizatorul exista si notificam observatorul in caz contrar.
         */
        if (BankingApp.getInstance(s1, s2).users.get(email) == null) {
            setMessage("User with " + email + " doesn't exist");
            return;
        }
        User user = BankingApp.getInstance(s1, s2).users.get(email);
        /*
        Verificam daca utilizatorul are suficienti bani in cont pentru a cumpara optiunea si notificam observatorul in caz contrar.
         */
        if (user.getPortfolio().accounts.get(Currency.USD).getBalance() < 100) {
            setMessage("Insufficient amount in account for buying premium option");
            return;
        }
        /*
        Scadem 100 de dolari din contul utilizatorului si apelam metoda upgrade a utilizatorului.
         */
        user.getPortfolio().accounts.get(Currency.USD).setBalance(user.getPortfolio().accounts.get(Currency.USD).getBalance() - 100);
        user.upgrade();
    }
}
