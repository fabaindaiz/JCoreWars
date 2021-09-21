package fabaindaiz.jcorewars.steplistener;

import fabaindaiz.jcorewars.listener.StepListener;
import fabaindaiz.jcorewars.marsVM.FrontEndManager;
import fabaindaiz.jcorewars.marsVM.StepReport;
import fabaindaiz.jcorewars.warrior.WarriorProcess;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class ProcList extends JSplitPane implements StepListener {

    static JPanel proc1 = new JPanel();
    static JPanel proc2 = new JPanel();
    static JScrollPane pane1 = new JScrollPane(proc1);
    static JScrollPane pane2 = new JScrollPane(proc2);

    Vector<JList<Integer>> procList;

    /**
     * Create a new core display for a specified core size and width.
     * @param man - Object managing the front end components.
     */
    public ProcList(FrontEndManager man, JSplitPane con)
    {

        this.setDividerSize(0);
        this.setDividerLocation(80);

        man.registerStepListener(this);
        con.setBottomComponent(this);

        this.setLeftComponent(pane1);
        this.setRightComponent(pane2);
    }

    public void loadProc(Vector<WarriorProcess<Integer>> list, Vector<Color> color)
    {
        procList = new Vector<>();

        for (int i = 0; i < Math.min(2, list.size()); i++)
        {
            JList<Integer> proc = new JList<>(list.get(i));

            proc.setFont(new Font("Droid Sans", Font.BOLD, 12));
            proc.setCellRenderer(new ProcListCellRenderer(color.get(i)));
            proc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            procList.add(proc);
        }

        proc1.removeAll();
        proc1.add(procList.get(0));

        if (procList.size() >= 2) {
            proc2.removeAll();
            proc2.add(procList.get(1));
        }

    }

    @Override
    public void stepProcess(StepReport report) {
    }

    @Override
    public void endProcess(StepReport report) {
    }

}

class ProcListCellRenderer extends DefaultListCellRenderer {

    Color procColor;

    public ProcListCellRenderer(Color c) {
        procColor = c;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, String.format("%06d", value), index, isSelected, cellHasFocus);
        c.setBackground(procColor);
        return c;
    }
}
