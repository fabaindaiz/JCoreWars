import marsVM.MarsCore;
import view.DisplayCore;

import java.awt.*;
import java.util.Vector;

public class Juno {

    public static void main(String[] args) {
        MarsCore marsCore = new MarsCore(new BorderLayout());
        DisplayCore mars = new DisplayCore(marsCore);

        Vector<String> wArgs = new Vector<>();
        wArgs.add("C:/informatica/Core Wars/warriors/Validate.red");
        marsCore.setWarriors(wArgs);

        marsCore.application_start();
    }
}
