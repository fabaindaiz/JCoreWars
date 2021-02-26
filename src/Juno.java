import marsVM.MarsCore;
import display.DisplayManager;

import java.util.Vector;

public class Juno {

    public static void main(String[] args) {
        MarsCore marsCore = new MarsCore();
        DisplayManager mars = new DisplayManager(marsCore);
        mars.setMenuDisplay();

        Vector<String> wArgs = new Vector<>();
        wArgs.add("C:/informatica/Core Wars/warriors/imp.red");
        wArgs.add("C:/informatica/Core Wars/warriors/imp.red");
        wArgs.add("C:/informatica/Core Wars/warriors/imp.red");
        marsCore.setWarriors(wArgs);

        marsCore.application_start();
    }
}
