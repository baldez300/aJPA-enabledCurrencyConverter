package application;

import entity.*;
import dao.*;
public class CurrencyAppJPA {

    public static void main(String[] args) {

        CurrencyDaoJPA curdao = new CurrencyDaoJPA();

        curdao.persist(new CurrenciesJPA("CFA", "Franc (CFA)", 621.63));
        curdao.persist(new CurrenciesJPA("BTC", "Bitcoin", 0.000036));

        CurrenciesJPA curr = curdao.find(9);
        System.out.println(curr.getAbbreviation() + " " + curr.getCurrencyName());

        curr.setCurrencyName("Guinean Franc (GNF)");
        curdao.update(curr);
    }

}
