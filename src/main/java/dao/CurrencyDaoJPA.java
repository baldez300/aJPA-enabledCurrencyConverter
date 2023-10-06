package dao;

import datasource.MariaDbConnectionJPA;
import entity.CurrenciesJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class CurrencyDaoJPA {

    public void persist(CurrenciesJPA curJPA) {
        EntityManager currency = MariaDbConnectionJPA.createEntityManager();
        try {
            currency.getTransaction().begin();
            currency.persist(curJPA);
            currency.getTransaction().commit();
        } finally {
            if (currency != null && currency.isOpen()) {
                currency.close();
            }
        }
    }

    public CurrenciesJPA find(int id) {
        EntityManager currency = MariaDbConnectionJPA.createEntityManager();
        try {
            return currency.find(CurrenciesJPA.class, id);
        } finally {
            if (currency != null && currency.isOpen()) {
                currency.close();
            }
        }
    }

    public void update(CurrenciesJPA curJPA) {
        EntityManager currency = MariaDbConnectionJPA.createEntityManager();
        try {
            currency.getTransaction().begin();
            currency.merge(curJPA);
            currency.getTransaction().commit();
        } finally {
            if (currency != null && currency.isOpen()) {
                currency.close();
            }
        }
    }

    public void delete(CurrenciesJPA curJPA) {
        EntityManager currency = MariaDbConnectionJPA.createEntityManager();
        try {
            currency.getTransaction().begin();
            currency.remove(curJPA);
            currency.getTransaction().commit();
        } finally {
            if (currency != null && currency.isOpen()) {
                currency.close();
            }
        }
    }

    // Updated method to get all currencies
    public List<CurrenciesJPA> getAllCurrencies() {
        EntityManager currency = MariaDbConnectionJPA.createEntityManager();
        try {
            TypedQuery<CurrenciesJPA> query = currency.createQuery("SELECT c FROM CurrenciesJPA c", CurrenciesJPA.class);
            return query.getResultList();
        } finally {
            if (currency != null && currency.isOpen()) {
                currency.close();
            }
        }
    }
}
