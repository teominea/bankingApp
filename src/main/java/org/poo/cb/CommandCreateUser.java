package org.poo.cb;

/**
 * Clasa CommandCreateUser este folosita pentru a implementa comanda de creare a unui utilizator.
 * Are ca atribute patru stringuri care reprezinta numele, email-ul, prenumele adresa utilizatorului,
 * si un obiect de tip BankObserver care reprezinta observatorul.
 */

public class CommandCreateUser extends Command {
    private String name;
    private String email;
    private String surname;
    private String address;

    BankObserver observer;

    public CommandCreateUser(String name, String email, String surname, String address) {
        this.name = name;
        this.email = email;
        this.surname = surname;
        this.address = address;
    }


    /**
     * Metoda execute este folosita pentru a crea un utilizator.
     * @param s1 - numele fisierului cu informatii despre cursul valutar
     * @param s2 - numele fisierului cu informatii despre valoarea actiunilor
     * Acesti parametri sunt necesari pentru a putea accesa instanta clasei BankingApp.
     */
    @Override
    public void execute(String s1, String s2) {
        observer = new BankObserver(this);
        /*
        Verificam daca utilizatorul exista si notificam observatorul in caz afirmativ.
         */
        if (BankingApp.getInstance(s1, s2).users.get(email) != null) {
            setMessage("User with " + email +  " already exists");
            return;
        }
        /*
        Vom crea o noua instanta de user folosind clasa UserBuilder si o vom adauga in lista de utilizatori a aplicatiei.
         */
        User user = new UserBuilder().withEmail(email).withName(name).withSurname(surname).withAddress(address).withFriends().withPortfolio().build();
        BankingApp.getInstance(s1, s2).users.put(email, user);
    }
}
