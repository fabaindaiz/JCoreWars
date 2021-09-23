package fabaindaiz.jcorewars;

import fabaindaiz.jcorewars.display.DisplayManager;
import fabaindaiz.jcorewars.marsVM.ApplicationCore;
import fabaindaiz.jcorewars.marsVM.MarsCore;

import java.util.Vector;

/**
 * Represent a main class which start an environment to execution of warriors redcode
 */
public class Juno {

    /**
     * Start a JCoreWars windows interface
     *
     * @param args array of command line arguments
     */
    public static void main(String[] args) {
        ApplicationCore applicationCore = new ApplicationCore();
        MarsCore marsCore = applicationCore.core;
        DisplayManager mars = new DisplayManager(applicationCore);
        mars.setMenuDisplay();

        Vector<String> wArgs = new Vector<>();
        wArgs.add("C:/informatica/Core Wars/warriors/imp.red");
        wArgs.add("C:/informatica/Core Wars/warriors/imp.red");
        wArgs.add("C:/informatica/Core Wars/warriors/imp.red");
        marsCore.setWarriors(wArgs);

        applicationCore.application_start();
    }
}
