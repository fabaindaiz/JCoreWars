package view;

import javax.swing.*;
import java.awt.*;

public class DisplayCore {

    public DisplayCore() {
        JFrame frame = new JFrame("Juno");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        JPanel left = new JPanel();
        JPanel centre = new JPanel();
        JPanel right = new JPanel();

        frame.getContentPane().add(left);
        frame.getContentPane().add(centre);
        frame.getContentPane().add(right);
        frame.setJMenuBar(getMenuBar());

        frame.setSize(920, 700);
        frame.setVisible(true);
    }

    private JMenuBar getMenuBar() {
        JMenuBar menu = new JMenuBar();
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

        menu.add(fileMenu);
        menu.add(editMenu);
        menu.add(simuMenu);
        menu.add(extrMenu);
        menu.add(windMenu);
        menu.add(helpMenu);
        return menu;
    }
}
