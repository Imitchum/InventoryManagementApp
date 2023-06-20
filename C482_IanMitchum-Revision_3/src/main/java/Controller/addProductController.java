package Controller;

import Model.c482_ianmitchum.Inventory;
import Model.c482_ianmitchum.Part;
import Model.c482_ianmitchum.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** The Add Product controller gets the user input and saves it to be reflected on the Main Menu. */
public class addProductController implements Initializable {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    @FXML
    private TableView<Part> associatedPartTableView;
    @FXML
    private TableView<Part> AddProductTableView;
    @FXML
    private TableColumn<Part, Integer> associatedPartIDCol;
    @FXML
    private TableColumn<Part, String> associatedPartNameCol;
    @FXML
    private TableColumn<Part, Double> associatedPartPriceCol;
    @FXML
    private TableColumn<Part, Integer> associatedPartStockCol;
    @FXML
    private TableColumn<Part, Integer> AddpartIDCol;
    @FXML
    private TableColumn<Part, String> AddpartNameCol;
    @FXML
    private TableColumn<Part, Integer> AddpartPriceCol;
    @FXML
    private TableColumn<Part, Integer> AddpartStockCol;
    @FXML
    private TextField AddProductIDTxt;
    @FXML
    private TextField AddproductMaxTxt;
    @FXML
    private TextField AddproductMinTxt;
    @FXML
    private TextField AddproductNameTxt;
    @FXML
    private TextField AddproductPriceTxt;
    @FXML
    private TextField AddproductStockTxt;
    @FXML
    private TextField addPartSearch;

    Stage stage;
    Parent scene;

    /** This method adds and sets the associated parts to the appropriate TableView. */
    @FXML
    void onActionAddPart(ActionEvent event) {

            Part currentPart = AddProductTableView.getSelectionModel().getSelectedItem();
            associatedParts.add(currentPart);
            associatedPartTableView.setItems(associatedParts);
            if(AddProductTableView.getSelectionModel().getSelectedItem() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("add part alert");
                alert.setContentText("Please select a part to add");
                alert.showAndWait();
            }
    }

    /** This method takes the user to the main menu when clicking cancel. */
    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();}

    /** This method removes the associated part from the bottom TableView. */
    @FXML
    void onActionRemovePart(ActionEvent event) {
        //deletes associated part from table
        Part associatedProduct = associatedPartTableView.getSelectionModel().getSelectedItem();
        associatedParts.remove(associatedProduct);

        if(associatedPartTableView.getSelectionModel().getSelectedItem()== null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("remove alert");
            alert.setContentText("Please select a part to remove");
            alert.showAndWait();
        }
        }



    /** This method saves the data the user enters. It then takes the user to the main menu with the new product. */
    @FXML
    void AddProductSaveButton(ActionEvent event) throws IOException {
        try {
            int randomID = (int) Math.floor(Math.random() * (100 - 1 + 1) + 1);
            String name = AddproductNameTxt.getText();
            int stock = Integer.parseInt(AddproductStockTxt.getText());
            double price = Double.parseDouble(AddproductPriceTxt.getText());
            int max = Integer.parseInt(AddproductMaxTxt.getText());
            int min = Integer.parseInt(AddproductMinTxt.getText());

            if(min > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Product Alert");
                alert.setContentText("Min cannot be greater than Max");
                alert.showAndWait();
                //Revision G. 6/7/2023 - saving error
                return;

            } else if(stock < min || stock > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Add Product Alert");
                alert.setContentText("Inventory must be between Min and Max");
                alert.showAndWait();
                //Revision G 6/7/2023 - saving error
                return;
            }

            Product newProduct = new Product(randomID, name, stock, price, max, min);

            for (Part part : associatedParts) {
                if (part != associatedParts) { newProduct.addAssociatedPart(part); } }

            Inventory.getAllProducts().add(newProduct);

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();

        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modify Product Exception");
            alert.setContentText("Please enter valid values in fields.");
            alert.showAndWait();
        }
    }

    /** This method searches the top TableView by part id or name. If it finds a match it returns the result. */
    @FXML
    void onActionSearchByPartIDOrName(ActionEvent event) {
            try {
                String p = addPartSearch.getText();
                ObservableList<Part> partSearch = Inventory.lookupPart(p);
                AddProductTableView.setItems(partSearch);
                if(partSearch.size() == 0)
                {
                    int id = Integer.parseInt(p);
                    Part P = Inventory.lookupPart(id);
                    partSearch.add(P);
                } AddProductTableView.setItems(partSearch);

            } catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Part Search");
                alert.setContentText("No part found with that name.");
                alert.showAndWait();

            }
    }







    /** The initialize method sets the column names for both top and bottom TableViews. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AddProductTableView.setItems(Inventory.getAllParts());
        AddpartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        AddpartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddpartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        AddpartStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        associatedPartTableView.setItems(associatedParts);
        associatedPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedPartStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }
}
