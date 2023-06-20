package Model.c482_ianmitchum;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

/** The Product class provides the fields and methods for working with the Product controllers as well as associated parts. */
public class Product implements Initializable {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private int stock;
    private double price;
    private int max;
    private int min;

    /** This is the constructor for the product class. It contains the fields that will be utilized.*/
    public Product(int id, String name, int stock, double price, int max, int min) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.max = max;
        this.min = min;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    /**
     *
     * @return product id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id setter
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return product name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name setter
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return stock
     */
    public int getStock() {
        return stock;
    }

    /**
     *
     * @param stock setter
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     *
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @param price setter
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @return max
     */
    public int getMax() {
        return max;
    }

    /**
     *
     * @param max setter
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     *
     * @return min
     */
    public int getMin() {
        return min;
    }

    /**
     *
     * @param min setter
     */
    public void setMin(int min) {
        this.min = min;
    }


    /** This is the addAssociatedPart method. It adds the associated parts to an observable list.*/
    /**
     *
     * @param part
     */
    public void addAssociatedPart(Part part) {associatedParts.add(part);}


    /**This is the deleteAssociatedPart method. It deletes associated parts. */
    /**
     *
     * @param selectedAssociatedPart
     * @return associated Part
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {return associatedParts.remove(selectedAssociatedPart);}


    /**This method gets all associated parts.*/
    /**
     *
     * @return associated parts
     */

    public ObservableList<Part> getallAssociatedParts(){
        return associatedParts;
    }

}
