package frontend;

import memory.Memory;

import javax.swing.*;

public class CoreList extends JScrollPane implements StepListener
{

    static JPanel panel = new JPanel();

    CustomListModel<String> instr;

    JList coreList;

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
        panel.removeAll();

        int i = 0;

        for (Memory mem: core) {
            instr.addElement(String.format("%04d", i) + core[i].toString());
            i++;
        }
        coreList = new JList(instr);
        coreList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        panel.add(coreList);
        update(getGraphics());
    }

    @Override
    public void stepProcess(StepReport report) {
        Memory[] core = report.core;

        int i;

        int[] addr = report.addrRead();
        for (i=0; i < addr.length; i++)
        {
            instr.set(i, String.format("%04d", i) + core[i].toString());
        }

        addr = report.addrWrite();
        for (i=0; i < addr.length; i++)
        {
            instr.set(i, String.format("%04d", i) + core[i].toString());
        }

        addr = report.addrDec();
        for (i=0; i < addr.length; i++)
        {
            instr.set(i, String.format("%04d", i) + core[i].toString());
        }

        addr = report.addrInc();
        for (i=0; i < addr.length; i++)
        {
            instr.set(i, String.format("%04d", i) + core[i].toString());
        }

        if ((i = report.addrExec()) != -1)
        {
            instr.set(i, String.format("%04d", i) + core[i].toString());
        }

        instr.update(0);
    }
}

class CustomListModel<T> extends DefaultListModel<T>
{
    public void update(int index)
    {
        fireContentsChanged(this, index, index);
    }
}
