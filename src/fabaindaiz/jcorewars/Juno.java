package fabaindaiz.jcorewars;

import fabaindaiz.jcorewars.display.DisplayManager;
import fabaindaiz.jcorewars.marsVM.AplicationCore;
import fabaindaiz.jcorewars.marsVM.MarsCore;

import java.util.Vector;

public class Juno {

    public static void main(String[] args) {
        AplicationCore aplicationCore = new AplicationCore();
        MarsCore marsCore = aplicationCore.core;
        DisplayManager mars = new DisplayManager(aplicationCore);
        mars.setMenuDisplay();

        Vector<String> wArgs = new Vector<>();
        wArgs.add("C:/informatica/Core Wars/warriors/Validate.red");
        //wArgs.add("C:/informatica/Core Wars/warriors/imp.red");
        //wArgs.add("C:/informatica/Core Wars/warriors/imp.red");
        //wArgs.add("C:/informatica/Core Wars/warriors/imp.red");
        marsCore.setWarriors(wArgs);

        aplicationCore.application_start();
    }
}
