package org.poo.cb;

/**
 * Clasa CommandTransferMoney este folosita pentru a transfera bani intre doi utilizatori.
 * Are ca atribute doua stringuri care reprezinta email-urile utilizatorilor, un obiect de tip Currency care reprezinta moneda transferata
 * un double care reprezinta suma de bani care urmeaza sa fie transferata si un obiect de tip BankObserver care reprezinta observatorul.
 */
public class CommandTransferMoney extends Command {
    String email;
    String friendEmail;
    Currency currency;
    double amount;

    BankObserver bankObserver;


    public CommandTransferMoney(String email, String friendEmail, Currency currency, double amount) {
        this.email = email;
        this.friendEmail = friendEmail;
        this.currency = currency;
        this.amount = amount;
    }

    /**
     * Metoda execute este folosita pentru a transfera bani intre doi utilizatori.
     * @param s1 - numele fisierului cu informatii despre cursul valutar
     * @param s2 - numele fisierului cu informatii despre valoarea actiunilor
     * Acesti parametri sunt necesari pentru a putea accesa instanta clasei BankingApp.
     */
    @Override
    public void execute(String s1, String s2) {
        bankObserver = new BankObserver(this);
        /*
        Verificam daca utilizatorii sunt prieteni si notificam observatorul in caz contrar.
         */
        if (BankingApp.getInstance(s1,s2).users.get(email).getFriends().get(friendEmail) == null) {
            setMessage("You are not allowed to transfer money to " + this.friendEmail);
            return;
        }
        /*
        Verificam daca utilizatorul are suficienti bani in cont pentru a efectua transferul si notificam observatorul in caz contrar.
         */
        if (BankingApp.getInstance(s1, s2).users.get(email).getPortfolio().accounts.get(currency).getBalance() < amount) {
            setMessage("Insufficient amount in account " + this.currency + " for transfer");
            return;
        }
        /*
        Transferam suma de bani din contul utilizatorului in contul prietenului.
         */
        User user = BankingApp.getInstance(s1, s2).users.get(email);
        User friend = BankingApp.getInstance(s1, s2).users.get(friendEmail);
        user.getPortfolio().accounts.get(currency).setBalance(user.getPortfolio().accounts.get(currency).getBalance() - amount);
        friend.getPortfolio().accounts.get(currency).setBalance(friend.getPortfolio().accounts.get(currency).getBalance() + amount);
    }

}
