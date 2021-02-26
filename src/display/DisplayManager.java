package display;

import marsVM.MarsCore;

import javax.swing.*;
import java.awt.*;

public class DisplayManager {

    MarsCore marsCore;
    JFrame frame;

    MenuDisplay menuDisplay;

    public DisplayManager(MarsCore core) {
        frame = new JFrame("Juno");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        marsCore = core;
        marsCore.application_init();

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
    }

    private JPanel getCorePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        marsCore.application_display(panel);

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

        marsCore.application_procList(procList);

        return procList;
    }

    private JSplitPane getCoreList() {
        JSplitPane coreList = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        coreList.setDividerSize(0);
        coreList.setDividerLocation(100);

        marsCore.application_coreList(coreList);

        return coreList;
    }
}
