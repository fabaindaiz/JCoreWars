package fabaindaiz.jcorewars.warrior;

import fabaindaiz.jcorewars.marsVM.MarsVM;
import fabaindaiz.jcorewars.memory.Memory;

import java.util.LinkedList;
import java.util.Queue;

public class WarriorManager {

    private final MarsVM marsVM;

    Queue<WarriorExecutor> warriors = new LinkedList<>();
    WarriorExecutor currentWarrior;

    private final int coreSize;
    private int numWarriors = 0;

    public WarriorManager(MarsVM marsVM) {
        this.marsVM = marsVM;

        this.coreSize = marsVM.coreSize;
    }

    public void loadWarriors(WarriorLoader[] warriors, int minWarriorDistance, int maxWarriorLength) {

        loadWarrior(warriors[0], 0, 0);
        int[] location = new int[warriors.length];

        for (int i = 1, r ; i < warriors.length; i++) {
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

    public void loadWarrior(WarriorLoader loader, int startPosition, int num) {
        Memory[] warriorMemory = loader.getMemory(coreSize);
        loader.num = num;

        if (!marsVM.copyWarrior(warriorMemory, startPosition)) {
            System.out.println("ERROR: could not load warrior " + (num + 1));
            return;
        }
        loader.normalizePSpace(coreSize);
        numWarriors ++;

        WarriorExecutor warrior = new WarriorExecutor(loader, startPosition+loader.getOffset(), loader.getPSpace());
        if (currentWarrior == null) {
            currentWarrior = warrior;
        } else {
            warriors.add(warrior);
        }
    }

    public WarriorExecutor getWarrior() {
        return currentWarrior;
    }

    public void removeWarrior() {
        numWarriors--;
        currentWarrior = warriors.remove();
        currentWarrior.setPCell(0, numWarriors);
    }

    public void nextWarrior() {
        warriors.add(currentWarrior);
        currentWarrior = warriors.remove();
    }

}
