package fabaindaiz.jcorewars.display;

import fabaindaiz.jcorewars.marsVM.AplicationCore;

import javax.swing.*;
import java.awt.*;

/**
 * Represent a class which manage display interface
 */
public class DisplayManager {

    AplicationCore appCore;
    JFrame frame;

    MenuDisplay menuDisplay;

    /**
     * @param core Aplication core main class
     */
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

    /**
     * Sets menu display in interface
     */
    public void setMenuDisplay() {
        menuDisplay = new MenuDisplay(frame);

        frame.setVisible(true);
        frame.repaint();
    }

    /**
     * Gets core display manager
     * @return Core jpane
     */
    private JPanel getCorePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        appCore.application_display(panel);

        return panel;
    }

    /**
     * Gets data display manager
     * @return Data pane
     */
    private JSplitPane getDataPanel() {
        JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        panel.setLeftComponent(getCoreList());
        panel.setRightComponent(getProcList());

        panel.setDividerSize(0);
        panel.setDividerLocation(340);
        return panel;
    }

    /**
     * Gets process list display manager
     * @return Proc list jpane
     */
    private JSplitPane getProcList() {
        JSplitPane procList = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        procList.setDividerSize(0);
        procList.setDividerLocation(50);

        appCore.application_procList(procList);

        return procList;
    }

    /**
     * Gets core list display manager
     * @return Core list jpane
     */
    private JSplitPane getCoreList() {
        JSplitPane coreList = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        coreList.setDividerSize(0);
        coreList.setDividerLocation(100);

        appCore.application_coreList(coreList);

        return coreList;
    }
}
