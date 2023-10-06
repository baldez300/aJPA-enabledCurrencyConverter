package model;

import java.util.List;
import dao.CurrencyDaoJPA;
import entity.CurrenciesJPA;

public class CurrencyModelJPA {
    private final CurrencyDaoJPA currencyDao;

    public CurrencyModelJPA() {
        currencyDao = new CurrencyDaoJPA();
    }

    public List<CurrenciesJPA> getCurrencies() {
        return currencyDao.getAllCurrencies();
    }

    // Updated method to persist new currency
    public void persist(CurrenciesJPA newCurrency) {
        currencyDao.persist(newCurrency);
    }
}
