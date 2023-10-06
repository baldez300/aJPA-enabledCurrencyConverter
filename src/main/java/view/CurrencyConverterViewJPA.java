package view;

import controller.CurrencyConverterControllerJPA;
import entity.CurrenciesJPA;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.Currency;

public class CurrencyConverterViewJPA extends Application {
    private CurrencyConverterControllerJPA controller;

    private TextField amountField;
    private ChoiceBox<Currency> sourceCurrencyChoice;
    private ChoiceBox<Currency> targetCurrencyChoice;
    private Label resultLabel;

    // Define the text fields for adding a new currency
    private TextField abbreviationField;
    private TextField nameField;
    private TextField conversionRateField;

    @Override
    public void start(Stage window) {
        controller = new CurrencyConverterControllerJPA();

        amountField = new TextField();
        amountField.addEventFilter(KeyEvent.KEY_TYPED, numericInputFilter());
        sourceCurrencyChoice = new ChoiceBox<>(controller.getCurrencies());
        targetCurrencyChoice = new ChoiceBox<>(controller.getCurrencies());
        Button convertButton = new Button("Convert");
        resultLabel = new Label();

        sourceCurrencyChoice.setConverter(new CurrencyStringConverter());
        targetCurrencyChoice.setConverter(new CurrencyStringConverter());

        Button addCurrencyButton = new Button("Add New Currency");
        addCurrencyButton.setOnAction(event -> openNewCurrencyWindow());

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                new Label("Amount to convert (data from DB):"),
                amountField,
                new Label("From Currency:"),
                sourceCurrencyChoice,
                new Label("To Currency:"),
                targetCurrencyChoice,
                convertButton,
                addCurrencyButton,
                new Label("Result:"),
                resultLabel
        );

        convertButton.setOnAction(event -> convertCurrency());

        Scene view = new Scene(layout, 600, 550);
        view.getStylesheets().add("style.css");
        window.setTitle("Currency Converter from DB");
        window.setScene(view);
        window.show();
    }

    private EventHandler<KeyEvent> numericInputFilter() {
        return event -> {
            String input = event.getCharacter();
            if (!input.matches("[0-9]*\\.?[0-9]*")) {
                resultLabel.setText("Only digits and a decimal point are allowed.");
                resultLabel.getStyleClass().add("error-message");
                event.consume();
            } else {
                resultLabel.getStyleClass().remove("error-message");
                resultLabel.setText("");
            }
        };
    }

    private void convertCurrency() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            Currency sourceCurrency = sourceCurrencyChoice.getValue();
            Currency targetCurrency = targetCurrencyChoice.getValue();

            if (sourceCurrency == null || targetCurrency == null) {
                resultLabel.setText("Select both: From Currency and To Currency");
                resultLabel.getStyleClass().add("error-message");
            } else {
                double result = controller.convertCurrency(amount, sourceCurrency, targetCurrency);
                resultLabel.setText(String.format("%.6f %s", result, targetCurrency.getAbbreviation()));
                resultLabel.getStyleClass().add("success-message");
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid amount.");
            resultLabel.getStyleClass().add("error-message");
        }
    }

    private static class CurrencyStringConverter extends StringConverter<Currency> {
        @Override
        public String toString(Currency currency) {
            return currency != null ? currency.getName() : "";
        }

        @Override
        public Currency fromString(String string) {
            return null;
        }
    }

    private void openNewCurrencyWindow() {
        Stage newCurrencyWindow = new Stage();
        newCurrencyWindow.initModality(Modality.APPLICATION_MODAL);

        TextField abbreviationField = new TextField();
        TextField nameField = new TextField();
        TextField conversionRateField = new TextField();
        Label newCurrencyResultLabel = new Label();
        Button addButton = new Button("Add Currency");

        addButton.setOnAction(event -> {
            addCurrency(abbreviationField.getText(), nameField.getText(), conversionRateField.getText(), newCurrencyResultLabel);
            newCurrencyWindow.close();
        });

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(10));
        layout.getChildren().addAll(
                new Label("Abbreviation:"),
                abbreviationField,
                new Label("Currency Name:"),
                nameField,
                new Label("Conversion Rate:"),
                conversionRateField,
                addButton,
                newCurrencyResultLabel
        );

        Scene newCurrencyScene = new Scene(layout, 400, 300);
        newCurrencyWindow.setScene(newCurrencyScene);
        newCurrencyWindow.setTitle("Add New Currency");
        newCurrencyWindow.showAndWait();

        // Update the choice boxes
        updateChoiceBoxes();
    }

    private void addCurrency(String abbreviation, String name, String conversionRate, Label resultLabel) {
        try {
            double rate = Double.parseDouble(conversionRate);
            CurrenciesJPA newCurrency = new CurrenciesJPA(abbreviation, name, rate);
            controller.persist(newCurrency);

            // Display the newly-inserted currency in choice boxes
            sourceCurrencyChoice.getItems().add(newCurrency); // Add to source currency choice box
            targetCurrencyChoice.getItems().add(newCurrency); // Add to target currency choice box

            // Clear input fields and display a success message
            abbreviationField.clear();
            nameField.clear();
            conversionRateField.clear();

            resultLabel.setText("Currency added successfully.");
            resultLabel.getStyleClass().remove("error-message");
            resultLabel.getStyleClass().add("success-message");
        } catch (NumberFormatException e) {
            // Handle invalid conversion rate input
            resultLabel.setText("Invalid conversion rate. Please enter a valid number.");
            resultLabel.getStyleClass().add("error-message");
        } catch (Exception e) {
            // Handle other exceptions, e.g., database errors
            resultLabel.setText("An error occurred while adding the currency.");
            resultLabel.getStyleClass().add("error-message");
        }
    }

    private void updateChoiceBoxes() {
        sourceCurrencyChoice.getItems().setAll(controller.getCurrencies());
        targetCurrencyChoice.getItems().setAll(controller.getCurrencies());
    }

}
