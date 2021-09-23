package fabaindaiz.jcorewars.warrior;

import fabaindaiz.jcorewars.loader.Assembler;
import fabaindaiz.jcorewars.memory.Memory;

import java.io.Reader;

/**
 * Represents a class which can load a warrior from redcode file
 */
public class WarriorLoader {

    public WarriorExecutor warriorExecutor;

    public Memory[] warriorInstr;
    public int warriorOffset;
    public int num;
    protected int[] pSpace;
    protected String name;
    protected String author;
    protected boolean loaded;

    /**
     * @param file      Redcode warrior instructions file reader
     * @param maxLength warrior file max length
     */
    public WarriorLoader(Reader file, int maxLength) {

        Assembler parser = new Assembler(file, maxLength);

        if (parser.assemble()) {
            warriorInstr = parser.getWarrior();
            warriorOffset = parser.getOffset();
            name = parser.getName();
            author = parser.getAuthor();
            loaded = true;
        } else {
            warriorInstr = new Memory[0];
            warriorOffset = 0;
            loaded = false;
        }
    }

    /**
     * Gets the warrior instructions in array
     *
     * @param coreSize Core size
     * @return warrior instructions array
     */
    public Memory[] getMemory(int coreSize) {
        for (Memory memory : warriorInstr) {
            while (memory.aValue < 0)
                memory.aValue += coreSize;
            memory.aValue %= coreSize;

            while (memory.bValue < 0)
                memory.bValue += coreSize;
            memory.bValue %= coreSize;
        }
        return warriorInstr;
    }

    /**
     * Gets the warrior instructions length
     *
     * @return warrior instructions length
     */
    public int getSize() {
        return warriorInstr.length;
    }

    /**
     * Gets the warrior offset
     *
     * @return Warrior offset
     */
    public int getOffset() {
        return warriorOffset;
    }

    /**
     * Gets the warrior name
     *
     * @return Warrior name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the warrior author
     *
     * @return warrior author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Create the warrior pSpace register
     *
     * @param length pSpace length
     */
    public void initPSpace(int length) {
        pSpace = new int[length];
    }

    /**
     * Gets the pSpace register
     *
     * @return pSpace register
     */
    public int[] getPSpace() {
        return pSpace;
    }

    /**
     * Normalize PSpace values references
     *
     * @param coreSize Core size
     */
    public void normalizePSpace(int coreSize) {
        for (int i = 0; i < pSpace.length; i++) {
            while (pSpace[i] < 0) {
                pSpace[i] += coreSize;
            }
            pSpace[i] %= coreSize;
        }
    }

    /**
     * Sets a value into loader pSpace register
     *
     * @param index PSpace address
     * @param value Value to store
     */
    public void setPCell(int index, int value) {
        if (index < 0 || index >= pSpace.length) {
            return;
        }
        pSpace[index] = value;
    }

    /**
     * Save warriors pSpace from warrior executer
     * @param warrior Warrior executer
     */
    public void savePSpace(WarriorExecutor warrior) {
        System.arraycopy(pSpace, 0, warrior.pSpace, 0, pSpace.length);
    }

    /**
     * Sets the warrior executor for this loader
     * @param warrior Warrior executor
     */
    public void setWarriorExecutor(WarriorExecutor warrior) {
        this.warriorExecutor = warrior;
    }
}
