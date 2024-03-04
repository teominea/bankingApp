package org.poo.cb;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa Command este folosita pentru a implementa comenzi care vor fi executate in aplicatie.
 * Are ca atribute un String message care reprezinta mesajul rezultat al unei comenzi si o lista de tip BankObserver care reprezinta observatorii.
 */

public abstract class Command {

    private String message = null;

    private List<BankObserver> observers = new ArrayList<>();
    /**
     * Metoda execute va fi redefinita in clasele care extind clasa Command.
     * @param s1 - numele fisierului cu informatii despre cursul valutar
     * @param s2 - numele fisierului cu informatii despre valoarea actiunilor
     * Acesti parametri sunt necesari pentru a putea accesa instanta clasei BankingApp.
     */
    abstract void execute(String s1, String s2);

    public void attach(BankObserver observer) {
        this.observers.add(observer);
    }
    public void notifyObserver() {
        for (BankObserver observer : observers) {
            observer.update();
        }
    }

    /**
     * Metoda setMessage este folosita pentru a seta mesajul rezultat al unei comenzi si pentru a notifica observatorul.
     * @param message - mesajul rezultat al unei comenzi
     */
    public void setMessage(String message) {
        this.message = message;
        notifyObserver();
    }

    public String getMessage() {
        return message;
    }

}
