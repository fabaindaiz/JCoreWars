package fabaindaiz.jcorewars.steplistener;

import fabaindaiz.jcorewars.listener.StepListener;
import fabaindaiz.jcorewars.marsVM.FrontEndManager;
import fabaindaiz.jcorewars.marsVM.StepReport;
import fabaindaiz.jcorewars.warrior.WarriorProcess;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Represent a class which manage process list display
 */
public class ProcList extends JSplitPane implements StepListener {

    static JPanel proc1 = new JPanel();
    static JPanel proc2 = new JPanel();
    static JScrollPane pane1 = new JScrollPane(proc1);
    static JScrollPane pane2 = new JScrollPane(proc2);

    Vector<JList<Integer>> procList;

    /**
     * Create a new core display for a specified core size and width.
     *
     * @param man Object managing the front end components.
     * @param con Parent display jpane
     */
    public ProcList(FrontEndManager man, JSplitPane con) {

        this.setDividerSize(0);
        this.setDividerLocation(80);

        man.registerStepListener(this);
        con.setBottomComponent(this);

        this.setLeftComponent(pane1);
        this.setRightComponent(pane2);
    }

    /**
     * Load process list from warrior process list
     *
     * @param list  Warrior process list
     * @param color Warrior color representation
     */
    public void loadProc(Vector<WarriorProcess<Integer>> list, Vector<Color> color) {
        procList = new Vector<>();

        for (int i = 0; i < Math.min(2, list.size()); i++) {
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

    /**
     * Update display with info from a round
     *
     * @param report Info from round
     */
    @Override
    public void stepProcess(StepReport report) {
    }

    /**
     * Method called to stop every step process from report
     *
     * @param report Results of last step
     */
    @Override
    public void endProcess(StepReport report) {
    }

}

/**
 * Represents a class which render a process list display
 */
class ProcListCellRenderer extends DefaultListCellRenderer {

    Color procColor;

    public ProcListCellRenderer(Color c) {
        procColor = c;
    }

    /**
     * Paint the current process list cell
     *
     * @param list         Process list to paint
     * @param value        Process value to print
     * @param index        Process index in list
     * @param isSelected   true if cell is selected
     * @param cellHasFocus true if cell has focus
     * @return Java component to paint
     */
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Component c = super.getListCellRendererComponent(list, String.format("%06d", value), index, isSelected, cellHasFocus);
        c.setBackground(procColor);
        return c;
    }
}
