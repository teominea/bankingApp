package org.poo.cb;

/**
 * Clasa Acount este folosita pentru a retine informatii despre un cont bancar.
 * Are ca atribute un obiect de tip Currency care reprezinta moneda contului si un double care reprezinta balanta contului.
 */
public class Account {

    private Currency currency;
    private double balance;

    public Account(Currency currency, double balance) {
        this.currency = currency;
        this.balance = balance;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }
}
