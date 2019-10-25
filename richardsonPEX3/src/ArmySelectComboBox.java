/**
 * ArmySelectComboBox  = the class for the combo box to switch between armies and change their attributes
 *
 * @author Caleb Richardson
 */
public class ArmySelectComboBox {

    private String key;
    private Army value;

    /**
     * ArmySelectComboBox - constructor for the army combo box
     *
     * @param army - the name of the army to display in the combo box
     * @param value - the value of the army to store in the combo box
     */
    public ArmySelectComboBox(String army, Army value){
        this.key =  army;
        this.value = value;
    }

    @Override
    public String toString() {
        return key;
    }
    public String getKey() {
        return key;
    }

    public Army getValue() {
        return value;
    }



}
