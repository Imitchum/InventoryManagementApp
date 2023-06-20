package Controller;

import Model.c482_ianmitchum.Inventory;
import Model.c482_ianmitchum.Part;
import Model.c482_ianmitchum.Product;
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
import java.util.Optional;
import java.util.ResourceBundle;

/** The Main Menu controller class is where the TableViews are set up. As well as the various onAction methods with the add, modify and delete buttons.*/
public class MainMenuController implements Initializable {

    @FXML
    private TableView<Part> PartTablevIew;
    @FXML
    private TableColumn<Part, Integer> partIDCol;
    @FXML
    private TableColumn<Part, String> partNameCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol;
    @FXML
    private TableColumn<Part, Integer> partStockCol;
    @FXML
    private TableColumn<Product, Integer> productIDCol;
    @FXML
    private TableColumn<Product, String> productNameCol;
    @FXML
    private TableColumn<Product, Double> productPriceCol;
    @FXML
    private TableColumn<Product, Integer> productStockCol;
    @FXML
    private TableView<Product> productTableView;
    @FXML
    private TextField partSearchTXT;
    @FXML
    private TextField productSearchTxt;

    Stage stage;
    Parent scene;

    /** This is the add part method for the main menu. It takes the user to the add part screen. */
    @FXML
    void onActionAddPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/addPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This is the add product method for the main menu. It takes the user to the add product screen. */
    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/View/addProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /** This method closes the application when the user clicks exit. */
    @FXML
    void onActionCloseProgram(ActionEvent event) {
        System.exit(0);
    }


    /** This is the delete part method for the main menu. It removes the selected part from the TableView. */
    @FXML
    void onActionDeletePart(ActionEvent event) {

            Part part = PartTablevIew.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            if (!(part == null)) {
                alert.setTitle("Delete Part");
                alert.setContentText("Are you sure you want to delete part?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Inventory.deletePart(part);
                }
            } else {
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Main Menu Alert");
                alert2.setContentText("No part selected, please select part.");
                alert2.showAndWait();
            }
    }

    /** This is the delete product method for the main menu.
     * It removes the selected product from the TableView.
     It also displays an alert if a product has associated parts. */
    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        try {
            Product product = productTableView.getSelectionModel().getSelectedItem();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Product");
            alert.setContentText("Are you sure you want to delete product?");
            Optional<ButtonType> result = alert.showAndWait();
            //Confirms deletion and checks if there are any associated parts
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (!product.getallAssociatedParts().isEmpty()) {
                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert2.setTitle("Delete Product");
                    alert2.setContentText("Product has associated Parts");
                    alert2.showAndWait();
                } else if (product.getallAssociatedParts().isEmpty()) {
                    Inventory.deleteProduct(product);
                }
            }

            if (!product.getallAssociatedParts().isEmpty()) {
                Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                alert2.setTitle("Delete Product");
                alert2.setContentText("Product has associated Parts");
                alert2.showAndWait();
            }
        } catch(NullPointerException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Delete Part Alert");
            alert.setContentText("No product available to delete. Please select product first.");
            alert.showAndWait();
        }
    }

    /** This is the modify part method for the main menu.
     It sends the information from the main menu controller to the modify part controller.
     It also loads the modify part screen. */
    @FXML
    void onActionModifyPart(ActionEvent event) throws IOException {
        try {
            FXMLLoader modPartsLoader = new FXMLLoader();
            modPartsLoader.setLocation(getClass().getResource("/View/modifyPart.fxml"));
            modPartsLoader.load();

            modifyPartController MPController = modPartsLoader.getController();
            MPController.sendPart(PartTablevIew.getSelectionModel().getSelectedIndex(), PartTablevIew.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = modPartsLoader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch(NullPointerException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modify Part Alert");
            alert.setContentText(" Please select part first.");
            alert.showAndWait();
        }

    }

    /** This is the modify product method for the main menu.
     * It sends the information from the main menu controller to the modify product controller.
     It also loads the modify product screen. */
    @FXML
    void onActionModifyProduct(ActionEvent event) throws IOException {
        try {
            FXMLLoader modProductsLoader = new FXMLLoader();
            modProductsLoader.setLocation(getClass().getResource("/View/modifyProduct.fxml"));
            modProductsLoader.load();

            modifyProductController ModifyProductController = modProductsLoader.getController();
            ModifyProductController.sendProduct(productTableView.getSelectionModel().getSelectedIndex(), productTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = modProductsLoader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch(NullPointerException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Modify Product Alert");
            alert.setContentText("Please select product first.");
            alert.showAndWait();
        }

    }

    /** This is the part search method for the main menu. It searches by part id or name. */
    @FXML
    void onActionSearchPart(ActionEvent event) {
        String p = partSearchTXT.getText();
        ObservableList<Part> partSearch = Inventory.lookupPart(p);

            try{
            if (partSearch.size() == 0) {
                int id = Integer.parseInt(p);
                partSearch.add(Inventory.lookupPart(id));
            } PartTablevIew.setItems(partSearch);

            } catch(NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Part Search");
                alert.setContentText("No part found with that name.");
                alert.showAndWait();
            }

        }


    /** This is the product search method for the main menu. It searches by product id or name. */
    @FXML
    void onActionSearchProduct(ActionEvent event) {
        try {
            String pd = productSearchTxt.getText();
            ObservableList<Product> productSearch = Inventory.lookupProduct(pd);
            if(productSearch.size() == 0)
            {
                int id = Integer.parseInt(pd);
                productSearch.add(Inventory.lookupProduct(id));
            } productTableView.setItems(productSearch);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Product Search");
            alert.setContentText("No product found with that name.");
            alert.showAndWait();}
    }





    /** The initialize method sets the column data for both top and bottom TableViews. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PartTablevIew.setItems(Inventory.getAllParts());
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        productTableView.setItems(Inventory.getAllProducts());
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productStockCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

    }
}