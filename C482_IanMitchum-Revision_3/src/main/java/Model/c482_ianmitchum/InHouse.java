package Model.c482_ianmitchum;

import java.net.URL;
import java.util.ResourceBundle;

/** This InHouse class inherits from the part class with all fields and methods plus the machineID field*/
public class InHouse extends Part{

    private int machineID;

    /** This is the constructor for the InHouse class. It contains the fields that will be utilized.*/
    public InHouse(int id, String name, int stock, double price, int max, int min, int machineID) {
        super(id, name, stock, price, max, min);
        this.machineID = machineID;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     *
     * @return machineID
     */
    public int getMachineID() {
        return machineID;
    }

    /**
     *
     * @param machineID setter
     */
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
