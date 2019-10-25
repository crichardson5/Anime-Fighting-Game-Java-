 import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

 /**
  * MainMenu - the class for the main gui
  *
  * @author Caleb Richardson
  */
 public class MainMenu {
    public JComboBox getArmySelect() {
        return ArmySelect;
    }

    private JComboBox ArmySelect;
    private JTextField Strength;
    private JSlider strengthSlider;


    private JSlider ASSlider;
    private JSlider AgilitySlider;
    private JSlider HPSlider;
    private JTextField AS;
    private JTextField Agility;
    private JTextField HP;
    private JButton AddArmy;
    private JButton DeleteArmy;
    public JButton BeginGame;
    private JButton RunSim;

    public JPanel getMainMenu() {
        return MainMenu;
    }

    private JPanel MainMenu;
    private JTextField Welcome;
    private JCheckBox quickVisualsCheckBox;
    private JButton exitButton;
    private JButton resumeButton;
    private JButton pauseButton;
    private JButton setStrengthButton;
    private JButton setAgilityButton;
    private JButton setAttackSpeedButton;
    private JButton setHPButton;
    private JButton restartButton;

    private JFrame frame = new JFrame("PresetArmys");

    private int gamePoints = 280;


     /**
      * MainMenu - the class for instantiating the main menu
      * @param miniMassive
      */
    public MainMenu(BattleSim miniMassive) {

        //sets buttons for the simulation inactive during the initial setup
        pauseButton.setEnabled(false);
        resumeButton.setEnabled(false);
        restartButton.setEnabled(false);

        RunSim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miniMassive.setSimBegun(true);
                pauseButton.setEnabled(true);
                resumeButton.setEnabled(true);
                restartButton.setEnabled(true);
                AddArmy.setEnabled(false);
            }
        });
        quickVisualsCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (quickVisualsCheckBox.isSelected()) {
                    miniMassive.setVisuals(false);
                    miniMassive.setMuted(true);
                } else {
                    miniMassive.setVisuals(true);
                    miniMassive.setMuted(false);
                }
            }
        });

        DeleteArmy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object item = ArmySelect.getSelectedItem();
                Army curArmy = ((ArmySelectComboBox) item).getValue();

                if (ArmySelect.getItemCount() == 1) {
                    JOptionPane.showConfirmDialog(null,
                            "You cannot delete the last army", "", JOptionPane.DEFAULT_OPTION);
                } else if (JOptionPane.showConfirmDialog(null, "Are you sure you would like to delete " + curArmy.armyID + "?") == JOptionPane.YES_OPTION) {
                    miniMassive.deleteArmy(curArmy);
                    ArmySelect.removeItem(item);
                    ArmySelect.revalidate();
                    ArmySelect.repaint();
                }

                if (ArmySelect.getItemCount() != 3) {
                    BeginGame.setEnabled(false);
                } else {
                    BeginGame.setEnabled(true);
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        setAttackSpeedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Object item = ArmySelect.getSelectedItem();
                Army curArmy = ((ArmySelectComboBox) item).getValue();

                for (int i = 0; i < curArmy.warriors.size(); i++) {
                    curArmy.warriors.get(i).setAttackSpeed(ASSlider.getValue());
                }
                if(miniMassive.isGame()){
                    int sliderSum = HPSlider.getValue() + strengthSlider.getValue() + AgilitySlider.getValue() + ASSlider.getValue();

                    if (sliderSum <= gamePoints) {
                        setAttackSpeedButton.setEnabled(false);
                        ASSlider.setEnabled(false);
                    } else {
                        JOptionPane.showConfirmDialog(null,
                                "You only have "+gamePoints+" points to assign to attributes, you are "
                                        +(sliderSum-gamePoints)+" points over. Please set a lower value.", "", JOptionPane.DEFAULT_OPTION);
                    }
                }
            }
        });
        ArmySelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                Object item = ArmySelect.getSelectedItem();
                Army curArmy = ((ArmySelectComboBox) item).getValue();

                strengthSlider.setValue(curArmy.warriors.get(0).rawAgility);
                AgilitySlider.setValue(curArmy.warriors.get(0).rawStrength);
                ASSlider.setValue(curArmy.warriors.get(0).attackSpeed);
                HPSlider.setValue(curArmy.warriors.get(0).hp);

            }
        });
        setStrengthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Object item = ArmySelect.getSelectedItem();
                Army curArmy = ((ArmySelectComboBox) item).getValue();

                for (int i = 0; i < curArmy.warriors.size(); i++) {
                    curArmy.warriors.get(i).setRawStrength(strengthSlider.getValue());
                }
                if(miniMassive.isGame()){
                    int sliderSum = HPSlider.getValue() + strengthSlider.getValue() + AgilitySlider.getValue() + ASSlider.getValue();

                    if (sliderSum <= gamePoints) {
                        setStrengthButton.setEnabled(false);
                        strengthSlider.setEnabled(false);
                    } else {
                        JOptionPane.showConfirmDialog(null,
                                "You only have "+gamePoints+" points to assign to attributes, you are "
                                        +(sliderSum-gamePoints)+" points over. Please set a lower value.", "", JOptionPane.DEFAULT_OPTION);
                    }
                }
            }
        });
        setAgilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Object item = ArmySelect.getSelectedItem();
                Army curArmy = ((ArmySelectComboBox) item).getValue();

                for (int i = 0; i < curArmy.warriors.size(); i++) {
                    curArmy.warriors.get(i).setRawAgility(AgilitySlider.getValue());
                }
                if(miniMassive.isGame()){
                    int sliderSum = HPSlider.getValue() + strengthSlider.getValue() + AgilitySlider.getValue() + ASSlider.getValue();

                    if (sliderSum <= gamePoints) {
                        setAgilityButton.setEnabled(false);
                        AgilitySlider.setEnabled(false);
                    } else {
                        JOptionPane.showConfirmDialog(null,
                                "You only have "+gamePoints+" points to assign to attributes, you are "
                                        +(sliderSum-gamePoints)+" points over. Please set a lower value.", "", JOptionPane.DEFAULT_OPTION);
                    }
                }
            }
        });
        setHPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Object item = ArmySelect.getSelectedItem();
                Army curArmy = ((ArmySelectComboBox) item).getValue();

                for (int i = 0; i < curArmy.warriors.size(); i++) {
                    curArmy.warriors.get(i).setHp(HPSlider.getValue());
                }
                if(miniMassive.isGame()){
                    int sliderSum = HPSlider.getValue() + strengthSlider.getValue() + AgilitySlider.getValue() + ASSlider.getValue();

                    if (sliderSum <= gamePoints) {
                        setHPButton.setEnabled(false);
                        HPSlider.setEnabled(false);
                    } else {
                        JOptionPane.showConfirmDialog(null,
                                "You only have "+gamePoints+" points to assign to attributes, you are "
                                        +(sliderSum-gamePoints)+" points over. Please set a lower value.", "", JOptionPane.DEFAULT_OPTION);
                    }
                }
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!miniMassive.isPause()) {
                    miniMassive.setPause(true);
                }
            }
        });
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (miniMassive.isPause()) {
                    miniMassive.setPause(false);
                }
            }
        });
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                miniMassive.reset();
            }
        });

        AddArmy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (ArmySelect.getItemCount() == 4) {
                    JOptionPane.showConfirmDialog(null,
                            "You cannot add more than 4 armies", "", JOptionPane.DEFAULT_OPTION);
                }else {
                    Object item = ArmySelect.getSelectedItem();
                    Army curArmy = ((ArmySelectComboBox) item).getValue();

                    PresetComboBox preset = new PresetComboBox(miniMassive, MainMenu.this);
                    frame.setContentPane(preset.getPanel1());
                    frame.setAlwaysOnTop(true);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                }


            }
        });

        BeginGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showConfirmDialog(null,
                        "You are now in control of the Hero Association. Your goal is to create an army to defeat the other anime warriors.\n" +
                                "Thankfully you will have the legendary ONE PUNCH MAN on your side. \nSet your armies values for victory and then click run sim when you are ready to begin\nChoose wisely, however, you only have " +
                                gamePoints + " points to allocate", "", JOptionPane.DEFAULT_OPTION);

                //adds the hero association
                miniMassive.addArmy(armyPresets.HERO, 5);
                int num = miniMassive.getArmies().size();
                ArmySelect.setSelectedItem(ArmySelect.getItemAt(ArmySelect.getItemCount()-1));
                ArmySelect.repaint();
                //locks the combo box
                ArmySelect.setEnabled(false);
                //locks irrelevant boxes
                AddArmy.setEnabled(false);
                DeleteArmy.setEnabled(false);
                BeginGame.setEnabled(false);

                //turns the game on
                miniMassive.setGame(true);
            }
        });


        ArmySelect.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                if (ArmySelect.getItemCount() != 3) {
                    BeginGame.setEnabled(false);
                } else {
                    BeginGame.setEnabled(true);
                }
            }
        });
    }

     /**
      * closeWindow - closes any GUI windows instantiated by the main menu
      */
    public void closeWindow() {
        frame.dispose();
    }

}