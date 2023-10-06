package controller;

import entity.CurrenciesJPA;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Currency;
import model.CurrencyModelJPA; // Updated model

import java.util.List;

public class CurrencyConverterControllerJPA {
    private final CurrencyModelJPA currencyModelDB; // Updated model

    public CurrencyConverterControllerJPA() {
        currencyModelDB = new CurrencyModelJPA(); // Updated model
    }

    public ObservableList<Currency> getCurrencies() {
        List<CurrenciesJPA> currencyList = currencyModelDB.getCurrencies(); // Updated model
        ObservableList<Currency> observableCurrencyList = FXCollections.observableArrayList();

        for (CurrenciesJPA currencies : currencyList) {
            // Convert Currencies to Currency
            Currency currency = new Currency(
                    currencies.getAbbreviation(),
                    currencies.getCurrencyName(),
                    currencies.getConversionRate()
            );
            observableCurrencyList.add(currency);
        }

        return observableCurrencyList;
    }

    public double convertCurrency(double amount, Currency sourceCurrency, Currency targetCurrency) {
        if (sourceCurrency == null || targetCurrency == null) {
            return 0.0; // Handle invalid input gracefully
        }

        // Get the conversion rates
        double sourceRate = sourceCurrency.getConversionRate();
        double targetRate = targetCurrency.getConversionRate();

        // Calculate the converted amount
        return (amount / sourceRate) * targetRate;
    }

    // Updated method to persist new currency
    public void persist(CurrenciesJPA newCurrency) {
        currencyModelDB.persist(newCurrency);
    }
}
