package org.poo.cb;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        if(args == null) {
            System.out.println("Running Main");
            return;
        }
        String exchangeFile = "src/main/resources/" + args[0];
        String stockFile = "src/main/resources/" + args[1];
        String commandsFile = "src/main/resources/" + args[2];

        /*
        Eliberam memoria de resursele folosite anterior in cazul in care aplicatia a fost rulata anterior.
         */
        BankingApp.getInstance(exchangeFile, stockFile).cleanup();
        /*
        Deschidem fisierul cu comenzi si citim fiecare linie.
         */
        try (BufferedReader br = new BufferedReader(new FileReader(commandsFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] command = line.split(" ");
                String commandType = command[0] + " " + command[1];
                /*
                In cazul in care comanda este de tipul "CREATE USER", vom citi informatiile necesare
                si vom crea o instanta de CommandCreateUser care va fi executata.
                 */
                if (commandType.equals("CREATE USER")) {
                    String email = command[2];
                    String name = command[3];
                    String surname = command[4];
                    StringBuilder address = new StringBuilder();
                    for (int i = 5; i < command.length; i++) {
                        address.append(command[i]).append(" ");
                    }
                    address.deleteCharAt(address.length() - 1);
                    CommandCreateUser createUser = new CommandCreateUser(name, email, surname, address.toString());
                    createUser.execute(exchangeFile, stockFile);
                }
                /*
                In cazul in care comanda este de tipul "ADD FRIEND", vom citi informatiile necesare
                si vom crea o instanta de CommandAddFriend care va fi executata.
                 */
                if (commandType.equals("ADD FRIEND")) {
                    String email = command[2];
                    String friendEmail = command[3];
                    CommandAddFriend addFriend = new CommandAddFriend(email, friendEmail);
                    addFriend.execute(exchangeFile, stockFile);
                }
                /*
                In cazul in care comanda este de tipul "ADD ACCOUNT", vom citi informatiile necesare
                si vom crea o instanta de CommandAddAccount care va fi executata.
                 */
                if (commandType.equals("ADD ACCOUNT")) {
                    String email = command[2];
                    String currencyString = command[3];
                    Currency currency = Currency.valueOf(currencyString);
                    CommandAddAccount addAccount = new CommandAddAccount(email, currency);
                    addAccount.execute(exchangeFile, stockFile);
                }
                /*
                In cazul in care comanda este de tipul "ADD MONEY", vom citi informatiile necesare
                si vom crea o instanta de CommandAddMoney care va fi executata.
                 */
                if (commandType.equals("ADD MONEY")) {
                    String email = command[2];
                    String currencyString = command[3];
                    Currency currency = Currency.valueOf(currencyString);
                    double amount = Double.parseDouble(command[4]);
                    CommandAddMoney addMoney = new CommandAddMoney(email, currency, amount);
                    addMoney.execute(exchangeFile, stockFile);
                }
                /*
                In cazul in care comanda este de tipul "EXCHANGE MONEY", vom citi informatiile necesare
                si vom crea o instanta de CommandExchangeMoney care va fi executata.
                 */
                if (commandType.equals("EXCHANGE MONEY")) {
                    String email = command[2];
                    String currency1String = command[3];
                    String currency2String = command[4];
                    Currency currency1 = Currency.valueOf(currency1String);
                    Currency currency2 = Currency.valueOf(currency2String);
                    double amount = Double.parseDouble(command[5]);
                    CommandExchangeMoney exchangeMoney = new CommandExchangeMoney(email, currency1, currency2, amount);
                    exchangeMoney.execute(exchangeFile, stockFile);
                }
                /*
                In cazul in care comanda este de tipul "TRANSFER MONEY", vom citi informatiile necesare
                si vom crea o instanta de CommandTransferMoney care va fi executata.
                 */
                if (commandType.equals("TRANSFER MONEY")) {
                    String email = command[2];
                    String friendEmail = command[3];
                    String currencyString = command[4];
                    Currency currency = Currency.valueOf(currencyString);
                    double amount = Double.parseDouble(command[5]);
                    CommandTransferMoney transferMoney = new CommandTransferMoney(email, friendEmail, currency, amount);
                    transferMoney.execute(exchangeFile, stockFile);
                }
                /*
                In cazul in care comanda este de tipul "RECOMMEND STOCKS", vom crea o instanta de CommandRecommendStocks
                care va fi executata.
                 */
                if (commandType.equals("RECOMMEND STOCKS")) {
                    CommandRecommendStocks recommendStocks = new CommandRecommendStocks();
                    recommendStocks.execute(exchangeFile, stockFile);
                }
                /*
                In cazul in care comanda este de tipul "BUY STOCKS", vom citi informatiile necesare
                si vom crea o instanta de CommandBuyStocks care va fi executata.
                 */
                if (commandType.equals("BUY STOCKS")) {
                    String email = command[2];
                    String stockName = command[3];
                    int quantity = Integer.parseInt(command[4]);
                    CommandBuyStocks buyStocks = new CommandBuyStocks(email, stockName, quantity);
                    buyStocks.execute(exchangeFile, stockFile);
                }
                /*
                In cazul in care comanda este de tipul "LIST USER", vom citi informatiile necesare
                si vom crea o instanta de CommandListUser care va fi executata.
                 */
                if (commandType.equals("LIST USER")) {
                    String email = command[2];
                    CommandListUser listUser = new CommandListUser(email);
                    listUser.execute(exchangeFile, stockFile);
                }
                /*
                In cazul in care comanda este de tipul "LIST PORTFOLIO", vom citi informatiile necesare
                si vom crea o instanta de CommandListPortfolio care va fi executata.
                 */
                if (commandType.equals("LIST PORTFOLIO")) {
                    String email = command[2];
                    CommandListPortfolio listPortfolio = new CommandListPortfolio(email);
                    listPortfolio.execute(exchangeFile, stockFile);
                }
                /*
                In cazul in care comanda este de tipul "BUY PREMIUM", vom citi informatiile necesare
                si vom crea o instanta de CommandBuyPremium care va fi executata.
                 */
                if (commandType.equals("BUY PREMIUM")) {
                    String email = command[2];
                    CommandBuyPremium buyPremium = new CommandBuyPremium(email);
                    buyPremium.execute(exchangeFile, stockFile);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}