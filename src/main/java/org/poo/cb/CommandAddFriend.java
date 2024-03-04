package org.poo.cb;

/**
 * Clasa CommandAddFriend este folosita pentru a adauga un prieten unui utilizator.
 * Are ca atribute doua stringuri care reprezinta email-urile utilizatorilor
 */
public class CommandAddFriend extends Command {

    private String userEmail;
    private String friendEmail;

    BankObserver observer;

    public CommandAddFriend(String userEmail, String friendEmail) {
        this.userEmail = userEmail;
        this.friendEmail = friendEmail;
    }

    /**
     * Metoda execute este folosita pentru a adauga un prieten unui utilizator.
     * @param s1 - numele fisierului cu informatii despre cursul valutar
     * @param s2 - numele fisierului cu informatii despre valoarea actiunilor
     * Acesti parametri sunt necesari pentru a putea accesa instanta clasei BankingApp.
     */
    @Override
    public void execute(String s1, String s2) {

        observer = new BankObserver(this);
        /*
        Verficam daca utilizatorul exista si daca prietenul exista si notificam observatorul in caz contrar.
         */
        if (BankingApp.getInstance(s1, s2).users.get(userEmail) == null) {
            setMessage("User with " + this.userEmail +  " doesn't exist");
            return;
        }
        if (BankingApp.getInstance(s1, s2).users.get(friendEmail) == null) {
            setMessage("User with " + this.friendEmail +  " doesn't exist");
            return;
        }
        /*
        Verificam daca utilizatorii sunt deja prieteni si notificam observatorul in caz afirmativ.
         */
        if (BankingApp.getInstance(s1, s2).users.get(userEmail).getFriends().get(friendEmail) != null) {
            setMessage("User with " + this.friendEmail +  " is already a friend");
            return;
        }
        /*
        Adaugam prietenul in lista de prieteni a utilizatorului si utilizatorul in lista de prieteni a prietenului.
         */
        User user = BankingApp.getInstance(s1, s2).users.get(userEmail);
        User friend = BankingApp.getInstance(s1, s2).users.get(friendEmail);
        user.getFriends().put(friendEmail, friend);
        friend.getFriends().put(userEmail, user);
    }
}
