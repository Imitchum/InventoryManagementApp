package Model.c482_ianmitchum;

import java.net.URL;
import java.util.ResourceBundle;

/** This Outsource class inherits from the part class with all fields and methods plus the companyName field*/
public class Outsource extends Part{
    private String companyName;

    /** This is the constructor for the Outsource class. It contains the fields that will be utilized.*/
    public Outsource(int id, String name, int stock, double price, int max, int min, String companyName) {
        super(id, name, stock, price, max, min);
        this.companyName = companyName;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    /**
     *
     * @return companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     *
     * @param companyName setter
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
