package steplistener;

import marsVM.FrontEndManager;
import marsVM.WarriorObj;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class ProcList extends javax.swing.JScrollPane implements StepListener
{

    static JPanel panel = new JPanel();

    Vector<CustomListModel<Integer>> procs;
    Vector<Color> procsColor;

    JList<CustomListModel<Integer>> procTable;

    /**
     * Create a new core display for a specified core size and width.
     * @param man - Object managing the front end components.
     */
    public ProcList(FrontEndManager man, JSplitPane con)
    {
        super(panel);

        man.registerStepListener(this);

        con.setBottomComponent(this);
    }

    public void loadProc(Vector<CustomListModel<Integer>> p) {
        procs = p;
        panel.removeAll();

        procTable = new JList<>(procs);
    }

    @Override
    public void stepProcess(StepReport report) {
        reportProcess(report, report.warrior.getMyColor());
    }

    @Override
    public void endProcess(StepReport report) {
        reportProcess(report, report.warrior.getColors());
    }

    private void reportProcess(StepReport report, Color[] colors)
    {


    }

    public void paintWarrior(WarriorObj warrior, int startPosition)
    {
        for (int i = startPosition; i < warrior.getSize(); i++) {
            procsColor.set(i, warrior.getNColor());
        }

    }
}

class ProcListCellRenderer extends DefaultListCellRenderer {

    Vector<Color> instrColor;

    public ProcListCellRenderer(Vector<Color> c) {
        instrColor = c;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        c.setBackground(instrColor.get(index));
        return c;
    }
}
