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

/** The Modify Product controller class handles the data from the Main Screen, enables the user to modify it and saves it. */
public class modifyProductController implements Initializable {
    @FXML
    private TableColumn<Part, Integer> associatedModifyPartIDCol;
    @FXML
    private TableColumn<Part, String> associatedModifyPartNameCol;
    @FXML
    private TableColumn<Part, Double> associatedModifyPartPriceCol;
    @FXML
    private TableColumn<Part,Integer > associatedmodifyPartStockCol;
    @FXML
    private TableView<Part> modProductCurrentTableView;
    @FXML
    private TableView<Part> modifyProductAssociatedPartsTableView;
    @FXML
    private TableColumn<Part, Integer> partIDCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    @FXML
    private TableColumn<Part, Integer> partStockCol;
    @FXML
    private TextField productIDTxt;
    @FXML
    private TextField productMaxTxt;
    @FXML
    private TextField productMinTxt;
    @FXML
    private TextField productNameTxt;
    @FXML
    private TextField productPriceTxt;
    @FXML
    private TextField productStockTxt;
    @FXML
    private TextField modifyProductSearchBar;
    private int currentIndex;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    Stage stage;
    Parent scene;

    /** This is the add part method for the modify product screen.
     It adds the selected part from the top TableView and adds it to the bottom one. */
    @FXML
    void onActionAddPart(ActionEvent event) {
        //adds selected part from top table to bottom table
        Part currentPart = modProductCurrentTableView.getSelectionModel().getSelectedItem();
        associatedParts.add(currentPart);
        modifyProductAssociatedPartsTableView.setItems(associatedParts);

        if(modProductCurrentTableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("add part alert");
            alert.setContentText("Please select a part to add");
            alert.showAndWait();
        }
    }

    /** This method takes the user to the main menu when they click cancel. */
    @FXML
    void onActionMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /** This method receives the product and associated part info from the main screen to modify. */
    public void sendProduct(int Index, Product product)
    {
        currentIndex = Index;
        productIDTxt.setText(String.valueOf(product.getId()));
        productNameTxt.setText(product.getName());
        productStockTxt.setText(String.valueOf(product.getStock()));
        productPriceTxt.setText(String.valueOf(product.getPrice()));
        productMinTxt.setText(String.valueOf(product.getMin()));
        productMaxTxt.setText(String.valueOf(product.getMax()));
        associatedParts.addAll(product.getallAssociatedParts());
        //exception for not selecting a part display an alert
    }

    /** This method removes the selected associated part from the bottom TableView. */
    @FXML
    void onActionRemovePart(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Modify Product Remove");
        alert.setContentText("Are you sure you want to delete part?");
        alert.showAndWait();

        Part associatedProduct = modifyProductAssociatedPartsTableView.getSelectionModel().getSelectedItem();
        associatedParts.remove(associatedProduct);

        if(modifyProductAssociatedPartsTableView.getSelectionModel().getSelectedItem()== null) {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setTitle("remove alert");
            alert2.setContentText("Please select a part to remove");
            alert2.showAndWait();
        }

    }

    /** This method saves the part info that the user modifies.
     It then goes back to the main menu with the updated information. */
    @FXML
    void onActionSavePart(ActionEvent event) throws IOException {
        try {

            int id = Integer.parseInt(productIDTxt.getText());
            String name = productNameTxt.getText();
            int stock = Integer.parseInt(productStockTxt.getText());
            double price = Double.parseDouble(productPriceTxt.getText());
            int max = Integer.parseInt(productMaxTxt.getText());
            int min = Integer.parseInt(productMinTxt.getText());

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
                //Revision G. 6/7/2023 - saving error
                return;
            }

            Product modifiedProduct = new Product(id, name, stock, price, max, min);
            Inventory.updateProduct(currentIndex, modifiedProduct);


            for (Part part : associatedParts) {
                if (part != associatedParts) {
                    modifiedProduct.addAssociatedPart(part);}}

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
          //Exception Handling
        } catch(NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modify Product Exception");
            alert.setContentText("Please enter valid values in fields.");
            alert.showAndWait();
        }

    }

    /** This method searches the top TableView by part id or name.
      If there are no results, it returns the full list of parts. */
    @FXML
    void onActionSearchByPartIDOrName(ActionEvent event) {
        //looks up part by name
        String m = modifyProductSearchBar.getText();
        ObservableList<Part> productSearch = Inventory.lookupPart(m);
        modProductCurrentTableView.setItems(productSearch);

        //looks up part by id
        try {
            if(productSearch.size() == 0)
            {
                int id = Integer.parseInt(m);
                Part M = Inventory.lookupPart(id);
                productSearch.add(M);

            }

            } catch (NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Part Search");
            alert.setContentText("No part found with that name.");
            alert.showAndWait();
        }

        //Repopulates Search Bar with Parts
        if(modifyProductSearchBar.getText().isEmpty()){
            Inventory.getAllParts();
        }
    }

    /** This method initializes the top and bottom TableView data. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //sets items for top table
        modProductCurrentTableView.setItems(Inventory.getAllParts());
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        //sets items for bottom table
        modifyProductAssociatedPartsTableView.setItems(associatedParts);
        associatedModifyPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedModifyPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedModifyPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedmodifyPartStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

    }


}
