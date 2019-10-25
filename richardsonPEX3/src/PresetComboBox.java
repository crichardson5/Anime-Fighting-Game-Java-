import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * PresetComboBox - the combo box for choosing an army to add
 *
 * @author Caleb Richardson
 */
public class PresetComboBox {


    public JComboBox getComboBox1() {
        return comboBox1;
    }

    private JComboBox comboBox1;

    public JPanel getPanel1() {
        return panel1;
    }

    private JPanel panel1;
    private JButton OKButton;

    public boolean isArmyAdded() {
        return armyAdded;
    }

    private boolean armyAdded = false;

    ButtonGroup buttonGroup = new ButtonGroup();
    private JRadioButton alliance1RadioButton;
    private JRadioButton alliance2RadioButton;
    private JRadioButton alliance3RadioButton;
    private JRadioButton alliance4RadioButton;


    /**
     * PresetComboBox - creates a combo box for adding preset armies
     * @param battle - the battle sim in which to create the armies
     * @param menu - the menu that called the combo box
     */
    public PresetComboBox(BattleSim battle, MainMenu menu) {
        buttonGroup.add(alliance1RadioButton);
        buttonGroup.add(alliance2RadioButton);
        buttonGroup.add(alliance3RadioButton);
        buttonGroup.add(alliance4RadioButton);


        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = comboBox1.getSelectedItem().toString();

                int ally;
                if(alliance1RadioButton.isSelected()){
                    ally = 1;
                } else if (alliance2RadioButton.isSelected()){
                    ally = 2;
                } else if (alliance3RadioButton.isSelected()){
                    ally = 3;
                } else {
                    ally = 4;
                }

                if (selected.equals("The Saiyan Army")){
                    battle.addArmy(armyPresets.SAIYAN, ally);
                } else if (selected.equals("The Hidden Leaf Village")){
                    battle.addArmy(armyPresets.NINJA, ally);
                } else if (selected.equals("The Hero Association")){
                    battle.addArmy(armyPresets.HERO, ally);
                } else if (selected.equals("The Soul Society")){
                    battle.addArmy(armyPresets.SHINIGAMI, ally);
                }


                if (menu.getArmySelect().getItemCount() != 3) {
                    menu.BeginGame.setEnabled(false);
                } else {
                    menu.BeginGame.setEnabled(true);
                }
                menu.closeWindow();
            }
        });
    }


}


