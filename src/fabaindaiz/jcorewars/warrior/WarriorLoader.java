package fabaindaiz.jcorewars.warrior;

import fabaindaiz.jcorewars.loader.Assembler;
import fabaindaiz.jcorewars.memory.Memory;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class WarriorLoader {

    public WarriorExecutor warriorExecutor;

    public Memory[] warriorInstr;
    public int warriorOffset;
    protected int[] pSpace;
    public int num;

    protected String name;
    protected String author;
    protected boolean loaded;

    public WarriorLoader (Reader file, int maxLength) {

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

    public int getSize() {
        return warriorInstr.length;
    }

    public int getOffset() {
        return warriorOffset;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public void initPSpace(int length) {
        pSpace = new int[length];
    }

    public int[] getPSpace() {
        return pSpace;
    }

    public void normalizePSpace(int coreSize) {
        for (int i=0; i<pSpace.length; i++) {
            while (pSpace[i] < 0) {
                pSpace[i] += coreSize;
            }
            pSpace[i] %= coreSize;
        }
    }

    public void setPCell(int index, int value) {
        if (index < 0 || index >= pSpace.length) {
            return;
        }
        pSpace[index] = value;
    }

    public void setWarriorExecutor(WarriorExecutor warrior) {
        this.warriorExecutor = warrior;
    }
}
