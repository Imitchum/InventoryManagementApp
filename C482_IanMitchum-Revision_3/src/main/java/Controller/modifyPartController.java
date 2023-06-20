package Controller;

import Model.c482_ianmitchum.InHouse;
import Model.c482_ianmitchum.Inventory;
import Model.c482_ianmitchum.Outsource;
import Model.c482_ianmitchum.Part;
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

/** The Modify Part controller class handles the data from the Main Screen, enables the user to modify it and saves it. */
public class modifyPartController implements Initializable {
    @FXML
    private ToggleGroup InHouseOutsourced;
    @FXML
    private TextField modifyMachineIdTxt;
    @FXML
    private TextField modifyPartIDTxt;
    @FXML
    private TextField modifyPartMaxTxt;
    @FXML
    private TextField modifyPartMinTxt;
    @FXML
    private TextField modifyPartNameTxt;
    @FXML
    private TextField modifyPartPriceTxt;
    @FXML
    private TextField modifyPartStockTxt;
    @FXML
    private RadioButton inHouseBtn;
    @FXML
    private RadioButton outsourceBTN;
    @FXML
    private Label lblChange;
    private int currentIndex;


    Stage stage;
    Parent scene;

    /** This method changes the bottom label to machineid if the user selects InHouse radio button. */
    @FXML
    void onActionChangetoMachineID(ActionEvent event) {
        if(inHouseBtn.isSelected()) {
            lblChange.setText("Machine ID:");
        }
    }

    /** This method changes the bottom label to companyName if the user selects Outsource radio button. */
    @FXML
    void onActionChangeToOutsource(ActionEvent event) {
        if(outsourceBTN.isSelected())
        {
            lblChange.setText("Company Name:");
        }
    }


    /** This method takes the user to the main menu when clicking cancel. */
    @FXML
    void onActionReturntoMain(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** This method retrieves the appropriate part data from the MainMenuController. This allows the user to modify the part. */
    public void sendPart(int Index, Part part)
    {
        currentIndex = Index;
        //allows use of subclass methods
        // 6/4/23-Added inhouse and outsource selection for data to be send from added part
        if(part instanceof InHouse)
        {
            inHouseBtn.setSelected(true);
            modifyMachineIdTxt.setText(String.valueOf(((InHouse) part).getMachineID()));
        }

        if(part instanceof Outsource)
        {
            outsourceBTN.setSelected(true);
            modifyMachineIdTxt.setText(((Outsource)part).getCompanyName());
        }



        modifyPartIDTxt.setText(String.valueOf(part.getId()));
        modifyPartNameTxt.setText(part.getName());
        modifyPartStockTxt.setText(String.valueOf(part.getStock()));
        modifyPartPriceTxt.setText(String.valueOf(part.getPrice()));
        modifyPartMinTxt.setText(String.valueOf(part.getMin()));
        modifyPartMaxTxt.setText(String.valueOf(part.getMax()));


    }

    /** This method saves the data the user enters. It then takes the user to the main menu with the modified part. */
    @FXML
    void onActionSaveModifiedPart(ActionEvent event) throws IOException {
        try {
            //Gets user input
            int id = Integer.parseInt(modifyPartIDTxt.getText());
            String name = modifyPartNameTxt.getText();
            int stock = Integer.parseInt(modifyPartStockTxt.getText());
            double price = Double.parseDouble(modifyPartPriceTxt.getText());
            int max = Integer.parseInt(modifyPartMaxTxt.getText());
            int min = Integer.parseInt(modifyPartMinTxt.getText());
            int machineID;
            String companyName;

            // Fix for min/max/stock errors 6/7/2023
            if(min > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Product Alert");
                alert.setContentText("Min cannot be greater than Max");
                alert.showAndWait();
                // Revision G. 6/7/2023 - saving error
                return;


            } else if(stock < min || stock > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Product Alert");
                alert.setContentText("Inventory must be between Min and Max");
                alert.showAndWait();
                // Revision G 6/7/2023 - saving error
                return;

            }

            //updates part based on radio button selection
            //separated previous if else statement with inhouse + radio Buttons-5/30/23
            //fixed issue with machine id + company name not being saved-5/30/23
            if (inHouseBtn.isSelected()) {
                machineID = Integer.parseInt(modifyMachineIdTxt.getText());
                InHouse modifiedPart = (new InHouse(id,name, stock, price, max, min, machineID));
                Inventory.updatePart(currentIndex, modifiedPart);
            }
            if (outsourceBTN.isSelected()) {
                companyName = modifyMachineIdTxt.getText();
                Outsource modifiedPart = (new Outsource(id,name, stock, price, max, min, companyName));
                Inventory.updatePart(currentIndex, modifiedPart);
            }

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();




            //Error dialog for incorrect input
        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Modify Part exception");
            alert.setContentText("Please enter a numeric value");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
