package org.poo.cb;

/**
 * Clasa BankObserver este folosita pentru a notifica utilizatorul despre rezultatul unei comenzi.
 * Are ca atribute un obiect de tip Command care reprezinta comanda.
 */
public class BankObserver {

    Command subject;

    public BankObserver(Command subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    /**
     * Metoda update este folosita pentru a afisa la consola mesajul rezultat al unei comenzi.
     */
    public void update() {
        System.out.println(subject.getMessage());
    }
}
