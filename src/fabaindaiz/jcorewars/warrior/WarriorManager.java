package fabaindaiz.jcorewars.warrior;

import fabaindaiz.jcorewars.marsVM.MarsVM;
import fabaindaiz.jcorewars.memory.Memory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Represents a class which manage all warriors in execution
 */
public class WarriorManager {

    private final MarsVM marsVM;
    private final int coreSize;

    private final ArrayList<WarriorExecutor> warriorsSave = new ArrayList<>();
    private final Queue<WarriorExecutor> warriors = new LinkedList<>();
    protected WarriorExecutor currentWarrior;
    private int numWarriors = 0;

    /**
     * @param marsVM Redcode executor main class
     */
    public WarriorManager(MarsVM marsVM) {
        this.marsVM = marsVM;

        this.coreSize = marsVM.coreSize;
    }

    /**
     * Load all warriors instructions in core
     *
     * @param warriors           Warrior loader list
     * @param minWarriorDistance Min warrior distance parameter
     * @param maxWarriorLength   Max warrior length parameter
     */
    public void loadWarriors(WarriorLoader[] warriors, int minWarriorDistance, int maxWarriorLength) {

        loadWarrior(warriors[0], 0, 0);
        int[] location = new int[warriors.length];

        for (int i = 1, r; i < warriors.length; i++) {
            boolean validSpot;
            do {
                validSpot = true;
                r = (int) (Math.random() * coreSize);

                if (r < minWarriorDistance || r > (coreSize - minWarriorDistance)) {
                    validSpot = false;
                }
                for (int k : location) {
                    if (r < (k + maxWarriorLength + minWarriorDistance) && r > (k - minWarriorDistance)) {
                        validSpot = false;
                        break;
                    }
                }
            } while (!validSpot);

            loadWarrior(warriors[i], r, i);
            location[i] = r;
        }
    }

    /**
     * Load a warrior instructions in core
     *
     * @param loader        Warrior loader
     * @param startPosition Warrior start position
     * @param num           Warrior number reference
     */
    public void loadWarrior(WarriorLoader loader, int startPosition, int num) {
        Memory[] warriorMemory = loader.getMemory(coreSize);
        loader.num = num;

        if (!marsVM.copyWarrior(warriorMemory, startPosition)) {
            System.out.println("ERROR: could not load warrior " + (num + 1));
            return;
        }
        loader.normalizePSpace(coreSize);
        numWarriors++;

        WarriorExecutor warrior = new WarriorExecutor(loader, startPosition + loader.getOffset(), loader.getPSpace());
        warriorsSave.add(warrior);
        if (currentWarrior == null) {
            currentWarrior = warrior;
        } else {
            warriors.add(warrior);
        }
    }

    /**
     * Gets the current warrior in execution
     * @return Current warrior executor
     */
    public WarriorExecutor getWarrior() {
        return currentWarrior;
    }

    /**
     * Remove the current warrior from execution
     */
    public void removeWarrior() {
        numWarriors--;
        currentWarrior = warriors.remove();
        currentWarrior.setPCell(0, numWarriors);
    }

    /**
     * Make a step to next warrior in execution queue
     */
    public void nextWarrior() {
        warriors.add(currentWarrior);
        currentWarrior = warriors.remove();
    }

    /**
     * Save warriors pSpace before a round finished
     */
    public void saveWarriors() {
        warriorsSave.forEach((warrior)-> warrior.getLoader().savePSpace(warrior));
    }

}
