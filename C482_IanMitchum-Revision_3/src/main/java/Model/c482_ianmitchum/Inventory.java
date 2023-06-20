package Model.c482_ianmitchum;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;

/** The Inventory class provides the methods for the Parts and Products controllers to use*/
public class Inventory implements Initializable {

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**This is the addPart method. It adds parts to the main screen part TableView. */
    public static void addPart(Part newPart) { allParts.add(newPart);}

    /**This is the addPart method. It adds parts to the main screen part TableView. */
    public static void addProduct(Product newProduct) {allProducts.add(newProduct);}

    /**This is the lookupPart method. It searches for parts by part id . */
    public static Part lookupPart(int partID)
    {
        for(Part ps: allParts) {
            if(ps.getId() == partID) {
                return ps;
            }
        }
        return null;
    }

    /**This is the lookupProduct method. It searches for products by product id . */
    public static Product lookupProduct(int productID)
    {
        for(Product pd: allProducts) {
            if(pd.getId() == productID) {
                return pd;
            }
        }
        return null;
    }

    /**This is the lookupPart method. It searches for parts by part name . */
    public static ObservableList<Part>lookupPart(String partName)
    {
        ObservableList<Part> partsNames = FXCollections.observableArrayList();
        ObservableList<Part> allParts = Inventory.getAllParts();

        for(Part parts: allParts) {
            if(parts.getName().contains(partName)) {
                partsNames.add(parts);
            }
        }
        return partsNames;
    }

    /**This is the lookupProduct method. It searches for products by product name . */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> productNames = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = Inventory.getAllProducts();

        for(Product products: allProducts) {
            if(products.getName().contains(productName)) {
                productNames.add(products);
            }
        }
        return productNames;
    }

    /**This is the updatePart method. It accepts an index and part parameter and modifies it . */
    public static void updatePart(int Index, Part selectedPart) { allParts.set(Index, selectedPart);}

    /**This is the updateProduct method. It accepts an index and product parameter and modifies it . */
    public static void updateProduct(int Index, Product newProduct)
    {
        allProducts.set(Index, newProduct);
    }

    /**This is the deletePart method. It removes a part from the main menu part TableView . */
    public static Boolean deletePart(Part selectedPart)
    {
        for(Part part: Inventory.getAllParts()) {
            if(part == selectedPart) {
                return Inventory.getAllParts().remove(part);
            }
        }
        return false;
    }

    /**This is the deleteProduct method. It removes a product from the main menu product TableView . */
    public static Boolean deleteProduct(Product selectedProduct)
    {
        for(Product product: Inventory.getAllProducts()) {
            if(product == selectedProduct) {
                return Inventory.getAllProducts().remove(product);
            }
        }
        return false;
    }

    /**This method retrieves all parts and returns it. */
    public static ObservableList<Part> getAllParts(){return allParts;}

    /**This method retrieves all products and returns it. */
    public static ObservableList<Product> getAllProducts(){return allProducts;}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
