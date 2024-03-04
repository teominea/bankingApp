package org.poo.cb;

/**
 * Clasa CommandAddAccount este folosita pentru a adauga un cont bancar unui utilizator.
 * Are ca atribute un String userEmail care reprezinta adresa de email a utilizatorului, un obiect de tip Currency care reprezinta moneda contului
 * si un obiect de tip BankObserver care reprezinta observatorul.
 */
public class CommandAddAccount extends Command {

        private String userEmail;
        private Currency currency;

        BankObserver bankObserver;


        public CommandAddAccount(String userEmail, Currency currency) {
            this.userEmail = userEmail;
            this.currency = currency;
        }


    /**
     * Metoda execute este folosita pentru a adauga un cont bancar unui utilizator.
     * @param s1 - numele fisierului cu informatii despre cursul valutar
     * @param s2 - numele fisierului cu informatii despre valoarea actiunilor
     * Acesti parametri sunt necesari pentru a putea accesa instanta clasei BankingApp.
     */
    @Override
        public void execute(String s1, String s2) {
            bankObserver = new BankObserver(this);
            /*
            Verficam daca exista deja contul in moneda dorita si notificam observatorul in caz afirmativ.
             */
            if (BankingApp.getInstance(s1, s2).users.get(userEmail).getPortfolio().accounts.get(currency) != null) {
                setMessage("Account with" + this.currency +  "already exists");
                notifyObserver();
                return;
            }
            /*
            Adaugam contul in portofoliul utilizatorului.
             */
            User user = BankingApp.getInstance(s1, s2).users.get(userEmail);
            if (user.getPortfolio() == null)
                user.setPortfolio(new Portfolio());
            user.getPortfolio().accounts.put(currency, new Account(currency, 0));

        }

}
