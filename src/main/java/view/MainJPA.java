package view;

import datasource.MariaDbConnectionJPA;

public class MainJPA {

    public static void main(String[] args) {
        try {
            CurrencyConverterViewJPA.launch(CurrencyConverterViewJPA.class, args);

        } finally {
            // Ensure that the EntityManagerFactory is closed when done
            MariaDbConnectionJPA.closeEntityManagerFactory();
        }
    }
}
