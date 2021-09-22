package fabaindaiz.jcorewars.display;

import javax.swing.*;

/**
 * Represent a class which manage menu interface
 */
public class MenuDisplay extends JMenuBar {

    /**
     * @param con Parent display jpane
     */
    public MenuDisplay(JFrame con) {

        con.setJMenuBar(this);

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu simuMenu = new JMenu("Simulator");
        JMenu extrMenu = new JMenu("Extras");
        JMenu windMenu = new JMenu("Window");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem runw = new JMenuItem("Run");
        JMenuItem brea = new JMenuItem("Break");
        JMenuItem step = new JMenuItem("Step");
        JMenuItem cont = new JMenuItem("Continue");
        JMenuItem rese = new JMenuItem("Reset Simulator");
        JMenuItem cleM = new JMenuItem("Clear Memory");
        JMenuItem cleP = new JMenuItem("Clear P-Space");
        JMenuItem opti = new JMenuItem("Options");

        simuMenu.add(runw);
        simuMenu.add(brea);
        simuMenu.add(step);
        simuMenu.add(cont);
        simuMenu.add(rese);
        simuMenu.add(cleM);
        simuMenu.add(cleP);
        simuMenu.add(opti);

        JMenuItem selA = new JMenuItem("Select Warrior A");
        JMenuItem selB = new JMenuItem("Select Warrior B");
        JMenuItem ediA = new JMenuItem("Edit Warrior A");
        JMenuItem ediB = new JMenuItem("Edit Warrior B");
        JMenuItem crun = new JMenuItem("Compile and Run");
        JMenuItem comp = new JMenuItem("Compile");
        JMenuItem tour = new JMenuItem("Tournament");
        JMenuItem exit = new JMenuItem("Exit");

        fileMenu.add(selA);
        fileMenu.add(selB);
        fileMenu.add(ediA);
        fileMenu.add(ediB);
        fileMenu.add(crun);
        fileMenu.add(comp);
        fileMenu.add(tour);
        fileMenu.add(exit);

        add(fileMenu);
        add(editMenu);
        add(simuMenu);
        add(extrMenu);
        add(windMenu);
        add(helpMenu);
    }

}
