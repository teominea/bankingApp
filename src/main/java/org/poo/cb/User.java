package org.poo.cb;

import java.util.Map;

/**
 * Clasa User este folosita pentru a retine informatii despre utilizatori.
 * Are ca atribute numele, email-ul, prenumele, adresa, un map de tipul Map<String, User> care reprezinta prietenii utilizatorului,
 * un boolean care reprezinta daca utilizatorul este premium si un obiect de tipul Portfolio care reprezinta portofoliul utilizatorului.
 * Metodele de set si get sunt folosite pentru a accesa atributele utilizatorului, necesare pentru a putea construi
 * un obiect de tipul User in clasa UserBuilder.
 */
public class User {

    private String name;
    private String email;
    private String surname;
    private String address;
    private Map<String, User> friends;
    private boolean isPremium = false;

    private Portfolio portfolio;

    public User () {}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public Map<String, User> getFriends() {
        return friends;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }
    /*
    Metoda upgrade este folosita pentru a face utilizatorul premium.
     */
    public void upgrade() {
        isPremium = true;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFriends(Map<String, User> friends) {
        this.friends = friends;
    }


}
