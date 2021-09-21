package fabaindaiz.jcorewars.display;

import fabaindaiz.jcorewars.marsVM.AplicationCore;

import javax.swing.*;
import java.awt.*;

public class DisplayManager {

    AplicationCore appCore;
    JFrame frame;

    MenuDisplay menuDisplay;

    public DisplayManager(AplicationCore core) {
        frame = new JFrame("Juno");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        appCore = core;

        JSplitPane mainPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        mainPanel.setLeftComponent(getDataPanel());
        mainPanel.setRightComponent(getCorePanel());

        mainPanel.setDividerSize(0);
        mainPanel.setDividerLocation(500);

        frame.setContentPane(mainPanel);

        frame.setSize(920, 700);
        frame.setVisible(true);
        frame.repaint();
    }

    public void setMenuDisplay() {
        menuDisplay = new MenuDisplay(frame);

        frame.setVisible(true);
        frame.repaint();
    }

    private JPanel getCorePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        appCore.application_display(panel);

        return panel;
    }

    private JSplitPane getDataPanel() {
        JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        panel.setLeftComponent(getCoreList());
        panel.setRightComponent(getProcList());

        panel.setDividerSize(0);
        panel.setDividerLocation(340);
        return panel;
    }

    private JSplitPane getProcList() {
        JSplitPane procList = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        procList.setDividerSize(0);
        procList.setDividerLocation(50);

        appCore.application_procList(procList);

        return procList;
    }

    private JSplitPane getCoreList() {
        JSplitPane coreList = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        coreList.setDividerSize(0);
        coreList.setDividerLocation(100);

        appCore.application_coreList(coreList);

        return coreList;
    }
}
