package frontend;

import marsVM.WarriorObj;
import memory.Memory;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class ProcList extends JScrollPane implements StepListener
{

    static JPanel panel = new JPanel();

    CustomListModel<String> procs;
    Vector<Color> procsColor;

    JList<String> procList;

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

    public void loadCore(Memory[] core) {
        procs = new CustomListModel<>();
        procsColor = new Vector<>();
        panel.removeAll();

        int i = 0;

        for (Memory mem: core) {
            procs.addElement(String.format("%04d", i) + mem.toString());
            procsColor.add(Color.white);
            i++;
        }
        procList = new JList<>(procs);
        procList.setCellRenderer(new ProcListCellRenderer(procsColor));
        procList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        panel.add(procList);
        update(getGraphics());
    }

    @Override
    public void stepProcess(StepReport report) {
        if (report.warrior.lastReport != null)
            reportProcess(report, report.warrior.getMyColor());
        report.warrior.lastReport = report;

        reportProcess(report, report.warrior.getColors());
    }

    private void reportProcess(StepReport report, Color[] colors)
    {
        Memory[] core = report.core;

        int i;

        int[] addr = report.addrRead();
        for (i=0; i < addr.length; i++)
        {
            procs.set(i, String.format("%04d", i) + core[i].toString());
            procsColor.set(i, colors[0]);
        }

        addr = report.addrWrite();
        for (i=0; i < addr.length; i++)
        {
            procs.set(i, String.format("%04d", i) + core[i].toString());
            procsColor.set(i, colors[1]);
        }

        addr = report.addrDec();
        for (i=0; i < addr.length; i++)
        {
            procs.set(i, String.format("%04d", i) + core[i].toString());
            procsColor.set(i, colors[1]);
        }

        addr = report.addrInc();
        for (i=0; i < addr.length; i++)
        {
            procs.set(i, String.format("%04d", i) + core[i].toString());
            procsColor.set(i, colors[1]);
        }

        if ((i = report.addrExec()) != -1)
        {
            procs.set(i, String.format("%04d", i) + core[i].toString());
            if (report.pDeath()) {
                procsColor.set(i, report.warrior.getDColor());
            } else {
                procsColor.set(i, colors[2]);
            }

        }

        procs.update(0);
    }

    public void paintWarrior(WarriorObj warrior, int startPosition)
    {
        for (int i = startPosition; i < warrior.getSize(); i++) {
            procsColor.set(i, warrior.getNColor());
        }

        procs.update(0);
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
