package view;

import marsVM.MarsCore;

import javax.swing.*;
import java.awt.*;

public class DisplayCore {

    MarsCore marsCore;

    public DisplayCore(MarsCore core) {
        JFrame frame = new JFrame("Juno");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new FlowLayout());

        marsCore = core;
        marsCore.application_init();
        marsCore.interface_display();

        JSplitPane mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        mainPanel.setLeftComponent(getLeftPanel());
        mainPanel.setRightComponent(getRightPanel());

        frame.setJMenuBar(getMenuBar());
        frame.getContentPane().add(mainPanel);

        frame.setSize(920, 700);
        frame.setVisible(true);
        frame.repaint();
    }

    private JPanel getLeftPanel() {
        JPanel panel = new JPanel();
        panel.setSize(400, 1000);

        panel.add(marsCore);

        return panel;
    }

    private JSplitPane getRightPanel() {
        JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        panel.setLeftComponent(getProcList());
        panel.setRightComponent(getCoreList());

        panel.setDividerSize(0);
        panel.setDividerLocation(160);
        panel.setPreferredSize(new Dimension(460, 600));
        return panel;
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

    private JSplitPane getProcList() {
        JSplitPane procList = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        return procList;
    }

    private JSplitPane getCoreList() {
        JSplitPane coreList = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        marsCore.application_coreList(coreList);

        return coreList;
    }
}
