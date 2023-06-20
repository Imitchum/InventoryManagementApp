package Model.c482_ianmitchum;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
/**LOGICAL ERROR-In the search methods for Parts and products when the user inputs anything other
 than an integer to search the program would issue a NullPointerException.
 Added a try/catch block around the code and the search works as intended without errors. */

/**FUTURE ENHANCEMENT- The data that is added or modified does not save when the user exits the program.
 Adding a database to save and store the data would fix this. */

/** This is the Main Class, data for the Parts and Products tables are loaded when the program runs.*/
public class MainMenu extends Application {
    /** This loads the Main Menu. */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainMenu.class.getResource("/View/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 400);
        stage.setScene(scene);
        stage.show();
    }
    /** This is the Main method. It is the first method called when the program is run.*/
    public static void main(String[] args) {
        //Parts Tableview populated
        Part part1 = new InHouse(1, "Brakes", 10, 15.00, 20, 10,1);
        Part part2 = new InHouse(2, "Wheel", 16, 11.00, 20, 10,2);
        Part part3 = new InHouse(3, "Seat", 10, 15.00, 20, 10,3);
        Part part4 = new Outsource(4, "Radiator", 10, 400.00, 10,1,"Bob's Parts");

        //Adds part
        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);

        //Products Tableview populated
        Product product1 = new Product(1000, "Giant Bike", 5, 299.99, 20, 10);
        Product product2 = new Product(1001, "Tricycle", 3, 99.99, 20, 10);
        Product product3 = new Product(1002, "Unicycle", 6, 50.00, 20, 10);
        Product product4 = new Product(1003, "Scooter", 9, 75.00, 20, 10);
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        Inventory.addProduct(product4);

        launch();
    }
}