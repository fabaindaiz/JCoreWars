package steplistener;

import javax.swing.*;

public class CustomListModel<T> extends DefaultListModel<T>
{
    public void update()
    {
        fireContentsChanged(this, -1, -1);
    }
}
