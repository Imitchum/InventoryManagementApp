package Model.c482_ianmitchum;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

/** The Part class is the superclass for the InHouse and Outsource classes*/
public abstract class Part implements Initializable {
    private int id;
    private String name;
    private int stock;
    private double price;
    private int max;
    private int min;

    /** This is the constructor for the part class. It contains the fields that will be utilized.*/
    public Part(int id, String name, int stock, double price, int max, int min) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
        this.max = max;
        this.min = min;
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id setter
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name setter
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return stock
     */
    public int getStock() {
        return stock;
    }
    /**
     * @param stock setter
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    /**
     * @return price
     */
    public double getPrice() {
        return price;
    }
    /**
     * @param price setter
     */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
     * @return max
     */
    public int getMax() {
        return max;
    }
    /**
     * @param max setter
     */
    public void setMax(int max) {
        this.max = max;
    }
    /**
     * @return min
     */
    public int getMin() {
        return min;
    }
    /**
     * @param min setter
     */
    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

