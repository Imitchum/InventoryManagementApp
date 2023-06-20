package Controller;

import Model.c482_ianmitchum.InHouse;
import Model.c482_ianmitchum.Inventory;
import Model.c482_ianmitchum.Outsource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** The Add Part controller class handles the user input as well as saving the data to be reflected on the Main Menu. */
public class addPartController implements Initializable {
    @FXML
    private TextField addPartMax;
    @FXML
    private TextField addPartMin;
    @FXML
    private TextField addPartName;
    @FXML
    private TextField addPartPrice;
    @FXML
    private TextField addPartStock;
    @FXML
    private RadioButton inHouseBtn;
    @FXML
    private RadioButton outsourceBtn;
    @FXML
    private TextField addPartMachineID;
    @FXML
    private Label LblChange;
    Stage stage;
    Parent scene;

    /** This method takes the user to the main menu when clicking cancel. */
    @FXML
    void onActionGoToMainMenu(ActionEvent event) throws IOException {
        //Returns to Main Menu upon pressing Cancel Button
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** This method saves the data the user enters. It then takes the user to the main menu with the new part. */
    @FXML
    void onActionSave(ActionEvent event) throws IOException {
        //Saves user Info and returns to Main Menu with added part
        try {
            //Gets user Input
            String name = addPartName.getText();
            int stock = Integer.parseInt(addPartStock.getText());
            double price = Double.parseDouble(addPartPrice.getText());
            int max = Integer.parseInt(addPartMax.getText());
            int min = Integer.parseInt(addPartMin.getText());
            int machineID;
            String companyName;

            //Generates Unique ID
            int randomID = (int) Math.floor(Math.random() * (100 - 1 + 1) + 1);


            //Error dialog for min + max + inventory
            if(min > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Product Alert");
                alert.setContentText("Min cannot be greater than Max");
                alert.showAndWait();
                // Revision G 6/7/2023 - saving error
                return;


            } else if(stock < min || stock > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Product Alert");
                alert.setContentText("Inventory must be between Min and Max");
                alert.showAndWait();
                // Revision G 6/7/2023 - saving error
                return;

            }


            //Adds part based on inhouse or outsource selection
            if (inHouseBtn.isSelected()) {
                machineID = Integer.parseInt(addPartMachineID.getText());
                Inventory.addPart(new InHouse(randomID, name, stock, price, max, min, machineID));
            }
            if (outsourceBtn.isSelected()) {
                companyName = addPartMachineID.getText();
                Inventory.addPart(new Outsource(randomID, name, stock, price, max, min, companyName));
            }

            //Returns to Main screen upon saving
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

            //Error dialog for incorrect input
        }  catch(NumberFormatException exception) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Add Part Exception");
            alert.setContentText("Please enter a numeric value");
            alert.showAndWait();

        }

    }

    /** This method changes the last label to companyName if the user selected the company name radio button. */
    @FXML
    void onActionChangeToCompanyName(ActionEvent event) {
        LblChange.setText("Company Name:");
    }

    /** This method changes the last label to machineID if the user selected the machineID radio button. */
    @FXML
    void onActionChangetoMachineID(ActionEvent event) {
        LblChange.setText("Machine ID:");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
