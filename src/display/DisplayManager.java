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
        mainPanel.setLeftComponent(getLeftPanel());
        mainPanel.setRightComponent(getRightPanel());

        menuDisplay = new MenuDisplay(frame);
        frame.setContentPane(mainPanel);

        frame.setSize(920, 700);
        frame.setVisible(true);
        frame.repaint();
    }

    private JPanel getLeftPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(400, 1000);

        marsCore.interface_display(panel);

        return panel;
    }

    private JSplitPane getRightPanel() {
        JSplitPane panel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        panel.setLeftComponent(getProcList());
        panel.setRightComponent(getCoreList());

        panel.setDividerSize(0);
        panel.setDividerLocation(180);
        panel.setPreferredSize(new Dimension(460, 600));
        return panel;
    }

    private JSplitPane getProcList() {
        JSplitPane procList = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        marsCore.application_procList(procList);

        return procList;
    }

    private JSplitPane getCoreList() {
        JSplitPane coreList = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        marsCore.application_coreList(coreList);

        return coreList;
    }
}
