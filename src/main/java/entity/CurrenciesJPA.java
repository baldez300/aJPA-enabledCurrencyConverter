package entity;

import jakarta.persistence.*;
import model.Currency;

@Entity
@Table(name = "currencies")
public class CurrenciesJPA extends Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "abbreviation")
    private String abbreviation;

    @Column(name = "currency_name")
    private String currencyName;

    @Column(name = "conversion_rate")
    private double conversionRate;

    public CurrenciesJPA(String abbreviation, String currencyName, double conversionRate) {
        super(abbreviation, currencyName, conversionRate);
        this.abbreviation = abbreviation;
        this.currencyName = currencyName;
        this.conversionRate = conversionRate;
    }

    public CurrenciesJPA() {
        super("", "", 0.0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }
}
