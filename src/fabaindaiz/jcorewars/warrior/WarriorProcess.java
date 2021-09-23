package fabaindaiz.jcorewars.warrior;

import javax.swing.*;

/**
 * Represents a class which can store a warrior process reference
 *
 * @param <T> Warrior process reference
 */
public class WarriorProcess<T> extends DefaultListModel<T> {

    /**
     * Update warrior process list display
     */
    public void update() {
        fireContentsChanged(this, -1, -1);
    }
}
