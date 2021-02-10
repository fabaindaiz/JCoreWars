package frontend;

import marsVM.WarriorObj;
import memory.Memory;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class CoreList extends JScrollPane implements StepListener
{

    static JPanel panel = new JPanel();

    CustomListModel<String> instr;
    Vector<Color> instrColor;

    JList<String> coreList;

    /**
     * Create a new core display for a specified core size and width.
     * @param man - Object managing the front end components.
     */
    public CoreList(FrontEndManager man, JSplitPane con)
    {
        super(panel);

        man.registerStepListener(this);

        con.setBottomComponent(this);
    }

    public void loadCore(Memory[] core) {
        instr = new CustomListModel<>();
        instrColor = new Vector<>();
        panel.removeAll();

        int i = 0;

        for (Memory mem: core) {
            instr.addElement(String.format("%04d", i) + mem.toString());
            instrColor.add(Color.white);
            i++;
        }
        coreList = new JList<>(instr);
        coreList.setCellRenderer(new coreListCellRenderer(instrColor));
        coreList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        panel.add(coreList);
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
            instr.set(i, String.format("%04d", i) + core[i].toString());
            instrColor.set(i, colors[0]);
        }

        addr = report.addrWrite();
        for (i=0; i < addr.length; i++)
        {
            instr.set(i, String.format("%04d", i) + core[i].toString());
            instrColor.set(i, colors[1]);
        }

        addr = report.addrDec();
        for (i=0; i < addr.length; i++)
        {
            instr.set(i, String.format("%04d", i) + core[i].toString());
            instrColor.set(i, colors[1]);
        }

        addr = report.addrInc();
        for (i=0; i < addr.length; i++)
        {
            instr.set(i, String.format("%04d", i) + core[i].toString());
            instrColor.set(i, colors[1]);
        }

        if ((i = report.addrExec()) != -1)
        {
            instr.set(i, String.format("%04d", i) + core[i].toString());
            if (report.pDeath()) {
                instrColor.set(i, report.warrior.getDColor());
            } else {
                instrColor.set(i, colors[2]);
            }

        }

        instr.update(0);
    }

    public void paintWarrior(WarriorObj warrior, int startPosition)
    {
        for (int i = startPosition; i < warrior.getSize(); i++) {
            instrColor.set(i, warrior.getNColor());
        }

        instr.update(0);
    }
}

class coreListCellRenderer extends DefaultListCellRenderer {

    Vector<Color> instrColor;

    public coreListCellRenderer(Vector<Color> c) {
        instrColor = c;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        c.setBackground(instrColor.get(index));
        return c;
    }
}
