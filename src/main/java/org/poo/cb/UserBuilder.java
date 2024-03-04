package org.poo.cb;

import java.util.HashMap;
import java.util.Map;

/**
 * Clasa UserBuilder este folosita pentru a construi un obiect de tipul User.
 * Are ca atribute un obiect de tipul User.
 */
public class UserBuilder {

    private User user = new User();

    /**
     * Metoda withName este folosita pentru a seta numele utilizatorului.
     * @param name - numele utilizatorului
     * @return - obiectul de tipul UserBuilder
     */
    public UserBuilder withName(String name) {
        user.setName(name);
        return this;
    }

    /**
     * Metoda withSurname este folosita pentru a seta prenumele utilizatorului.
     * @param surname - prenumele utilizatorului
     * @return - obiectul de tipul UserBuilder
     */
    public UserBuilder withSurname(String surname) {
        user.setSurname(surname);
        return this;
    }

    /**
     * Metoda withEmail este folosita pentru a seta email-ul utilizatorului.
     * @param email - email-ul utilizatorului
     * @return - obiectul de tipul UserBuilder
     */
    public UserBuilder withEmail(String email) {
        user.setEmail(email);
        return this;
    }
    /**
     * Metoda withAddress este folosita pentru a seta adresa utilizatorului.
     * @param address - adresa utilizatorului
     * @return - obiectul de tipul UserBuilder
     */
    public UserBuilder withAddress(String address) {
        user.setAddress(address);
        return this;
    }

    /**
     * Metoda withPortfolio este folosita pentru a seta portofoliul utilizatorului.
     * Va initializa un obiect de tipul Portfolio, care va fi setat ca atribut al utilizatorului.
     * @return - obiectul de tipul UserBuilder
     */
    public UserBuilder withPortfolio() {
        user.setPortfolio(new Portfolio());
        return this;
    }

    /**
     * Metoda withFriends este folosita pentru a seta prietenii utilizatorului.
     * Va initializa un map de tipul Map<String, User>, care va fi setat ca atribut al utilizatorului.
     * @return - obiectul de tipul UserBuilder
     */
    public UserBuilder withFriends() {
        user.setFriends(new HashMap<>());
        return this;
    }
    /**
     * Metoda build este folosita pentru a construi un obiect de tipul User.
     * @return - obiectul de tipul User
     */
    public User build() {
        return user;
    }


}
