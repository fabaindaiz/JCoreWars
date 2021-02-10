package frontend;

import javax.swing.*;

public class CustomListModel<T> extends DefaultListModel<T>
{
    public void update(int index)
    {
        fireContentsChanged(this, index, index);
    }
}
