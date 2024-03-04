package org.poo.cb;

import java.util.Date;

/**
 * Clasa CommandRecommendStocks este folosita pentru a recomanda actiuni care ar trebui cumparate.
 * Are ca atribute un obiect de tip BankObserver care reprezinta observatorul.
 */
public class CommandRecommendStocks extends Command {

    BankObserver observer;

    /**
     * Metoda execute este folosita pentru a recomanda actiuni care ar trebui cumparate.
     * @param s1 - numele fisierului cu informatii despre cursul valutar
     * @param s2 - numele fisierului cu informatii despre valoarea actiunilor
     * Acesti parametri sunt necesari pentru a putea accesa instanta clasei BankingApp.
     */
    @Override
    public void execute(String s1, String s2) {
        observer = new BankObserver(this);
        /*
        Obtinem informatiile despre valoarea actiunilor.
         */
        MapFromFile<?> stocks = BankingApp.getInstance(s1, s2).getStockValues();
        StringBuilder recommendedStocks = new StringBuilder();
        recommendedStocks.append("{" + "\"stockstobuy\":[");
        Stocks stockValues = (Stocks) stocks;
        /*
        Calculam media din ultimele 5 zile a preturilor actiunilor si media din ultimele 10 zile a preturilor actiunilor.
         */
        for (String stock : stockValues.stockPrices.keySet()) {
            double priceForLastFiveDays = 0.00;
            double priceForLastTenDays = 0.00;
            int i = 0;
            for (Double price : stockValues.stockPrices.get(stock)) {
                if (i >= 5 && i < 10) {
                    priceForLastFiveDays += price;
                }
                if (i < 10) {
                    priceForLastTenDays += price;
                }
                i++;
            }
            /*
            Daca media din ultimele 5 zile este mai mare decat media din ultimele 10 zile, adaugam actiunea in lista de recomandari.
             */
            priceForLastFiveDays /= 5;
            priceForLastTenDays /= 10;
            if (priceForLastFiveDays > priceForLastTenDays) {
                recommendedStocks.append("\"" + stock + "\",");
            }
        }
        recommendedStocks.deleteCharAt(recommendedStocks.length() - 1);
        recommendedStocks.append("]}");
        /*
        Setam mesajul cu lista de recomandari.
         */
        setMessage(recommendedStocks.toString());
    }
}
