package fabaindaiz.jcorewars.steplistener;

import fabaindaiz.jcorewars.listener.StepListener;
import fabaindaiz.jcorewars.marsVM.FrontEndManager;
import fabaindaiz.jcorewars.marsVM.StepReport;
import fabaindaiz.jcorewars.memory.Memory;
import fabaindaiz.jcorewars.warrior.WarriorLoader;
import fabaindaiz.jcorewars.warrior.WarriorProcess;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class CoreList extends JScrollPane implements StepListener {

    FrontEndManager man;

    static JPanel panel = new JPanel();

    WarriorProcess<String> instr;
    Vector<Color> instrColor;

    JList<String> coreList;

    /**
     * Create a new core display for a specified core size and width.
     * @param man - Object managing the front end components.
     */
    public CoreList(FrontEndManager man, JSplitPane con) {
        super(panel);

        this.man = man;

        man.registerStepListener(this);

        con.setBottomComponent(this);
    }

    public void loadCore(Memory[] core) {
        instr = new WarriorProcess<>();
        instrColor = new Vector<>();
        panel.removeAll();

        int i = 0;

        for (Memory mem: core) {
            instr.addElement(String.format("%04d", i) + mem.toString());
            instrColor.add(Color.white);
            i++;
        }
        coreList = new JList<>(instr);
        coreList.setFont(new Font("Droid Sans", Font.BOLD, 12));
        coreList.setCellRenderer(new coreListCellRenderer(instrColor));
        coreList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        panel.add(coreList);
    }

    @Override
    public void stepProcess(StepReport report) {
        reportProcess(report, man.getColors(report.warrior().getLoader().num));
    }

    @Override
    public void endProcess(StepReport report) {
        reportProcess(report, man.getDynColors(report.warrior().getLoader().num));
    }

    private void reportProcess(StepReport report, Color[] colors)
    {
        Memory[] core = report.core;

        int i;

        int[] addr = report.addrRead();
        for (i=0; i < addr.length; i++)
        {
            instr.set(addr[i], String.format("%06d", addr[i]) + core[i].toString());
            instrColor.set(addr[i], colors[0]);
        }

        addr = report.addrWrite();
        for (i=0; i < addr.length; i++)
        {
            instr.set(addr[i], String.format("%06d", addr[i]) + core[i].toString());
            instrColor.set(addr[i], colors[1]);
        }

        addr = report.addrDec();
        for (i=0; i < addr.length; i++)
        {
            instr.set(addr[i], String.format("%06d", addr[i]) + core[i].toString());
            instrColor.set(addr[i], colors[1]);
        }

        addr = report.addrInc();
        for (i=0; i < addr.length; i++)
        {
            instr.set(addr[i], String.format("%06d", addr[i]) + core[i].toString());
            instrColor.set(addr[i], colors[1]);
        }

        if ((i = report.addrExec()) != -1)
        {
            instr.set(i, String.format("%06d", i) + core[i].toString());
            if (report.pDeath()) {
                instrColor.set(i, man.getDColor(report.warrior().getLoader().num));
            } else {
                instrColor.set(i, colors[2]);
            }
        }

    }

    public void paintWarrior(WarriorLoader warrior, int startPosition)
    {
        for (int i = startPosition; i < warrior.getSize(); i++) {
            instrColor.set(i, man.getColor(warrior.num));
        }

        instr.update();
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
        c.setBackground(instrColor.get(index).brighter());
        return c;
    }
}
