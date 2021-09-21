package fabaindaiz.jcorewars.warrior;

import javax.swing.*;

public class WarriorProcess<T> extends DefaultListModel<T> {

    public void update() {
        fireContentsChanged(this, -1, -1);
    }
}
