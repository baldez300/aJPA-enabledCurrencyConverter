package model;

/*
    Assignment: A JPA-enabled currency converter
    In the previous assignment, you made your currency converter application use a database for persisting the exchange rates and other currency information. Now, you will make the application use JPA for persisting the data.

    Create a new project for the assignment. Alternatively, you can use the same project as in the previous assignment, but you need to remove the database code from the project. If you choose the latter option, make a new branch for the assignment.

    Step 1:

    Your first task is simply to make the application use JPA for retrieving the data. The application should work exactly as in the previous assignment. The only difference is that the data is now retrieved from the database using JPA.

    As your application will use a pre-existing database schema, make sure the attribute jakarta.persistence.schema-generation.database.action in the persistence.xml file is set to none.

    Like before, the application should react to errors gracefully. If the application cannot retrieve the data from the database, it should print an error message in the graphical user interface.

    Step 2:

    Add functionality to insert a new currency into the database. The application should have a button for adding a new currency. When the button is pressed, the application should open a new window where the user can enter the currency information.

    Technically, your primary stage (window) should have a button handling method that creates a new stage (Stage newStage = new Stage();), builds the scene into the stage, and finally shows the stage (newStage.show();). The new stage should in turn have a button handling method that inserts the new currency into the database and closes the newly-created stage.

    Hint: the newly-inserted currency should be displayed in the primary stage. You can use the showAndWait() method to display the new stage. As a consequence, the main stage will wait for the new stage to close before continuing. You can add a method call for updating the currency information in the main stage after the showAndWait() method call.

    For this assignment, you get points for the following:

    The application correctly establishes a connection to the database and recognizes the database schema. (1 point)
    There is a Dao method for retrieving the currency information from the database. (1 point)
    The application uses the Dao method for retrieving the currency information from the database, and displays the information in the graphical user interface. (1 point)
    The application opens a new window for adding a new currency. (1 point)
    The application correctly inserts the new currency into the database. (1 point)
    The application gracefully handles errors. (1 point)
    You get an extra point if: 7. The newly-inserted currency is displayed as a currency option in the graphical user interface. (1 point)
*/

public class Currency {
    private final String abbreviation;
    private final String name;
    private final double conversionRate;

    public Currency(String abbreviation, String name, double conversionRate) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.conversionRate = conversionRate;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    @Override
    public String toString() {
        return abbreviation + " - " + name;
    }
}
