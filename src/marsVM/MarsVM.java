package marsVM;

import frontend.StepReport;
import memory.Address;
import memory.Memory;

public class MarsVM {

    public final Memory core[];
    public WarriorRT currentW;		// current warrior
    int numWarriors;
    public final int maxProc;
    public final int coreSize;

    public MarsVM(int coreS, int maxP)
    {
        maxProc = maxP;
        coreSize = coreS;

        core = new Memory[coreSize];

        for (int i=0; i<core.length; i++)
        {
            core[i] = new Memory();
        }

    }

    public void reset()
    {
        currentW = null;
        numWarriors = 0;

        for (int i=0; i<core.length; i++)
        {
            core[i] = new Memory();
        }
    }

    /**
     * Load a warrior into the core.
     * @param warrior - warrior to load
     * @param startPosition - first memory location occupied by warrior
     * @returns boolean - false if couldn't load warrior
     */
    public boolean loadWarrior(WarriorObj warrior, int startPosition)
    {
        WarriorRT newWarrior;
        Memory wMemory[] = warrior.getMemory(coreSize);
        if ((startPosition + wMemory.length-1) > coreSize) return false;	// check that warrior fits in memory

        numWarriors++;

        for (int i=0; i<wMemory.length; i++)
            core[startPosition+i].copy(wMemory[i]);

        warrior.normalizePSpace(coreSize);
        newWarrior = new WarriorRT(warrior, startPosition+warrior.getOffset(), warrior.getPSpace());

        if (currentW == null)
        {
            currentW = newWarrior;
        } else {
            newWarrior.Insert(currentW, currentW.getNextWarrior());
        }

        return true;
    }

    /**
     * get the warrior object of the current warrior
     * @returns old.marsVM.WarriorObj - current warrior's object
     */
    public WarriorObj getCurrentWarrior()
    {
        return currentW.warrior();
    }

    /**
     * The heart of the VM it executes on instruction in core each time it is called
     * @returns old.marsVM.StepReport - report of what happened this instuction.
     */
    public StepReport executeInstr()
    {
        Address addr = new Address();

        addr.executer = this;

        // get instruction pointer
        addr.IP = currentW.getProc();
        addr.report.warrior(currentW.warrior());
        addr.report.numProc(currentW.numProc());

        // Get a Pointer to the current instruction
        addr.instr.copy(core[addr.IP]);

        // evaluate A operand
        addr.tempAddr = (addr.instr.aValue + addr.IP) % coreSize; // temporary address stuffed with the direct value to evaluate actions and help with indirect mode

        addr.instr.operandA.executeA(core, addr);

        // evaluate B operand
        addr.tempAddr = (addr.instr.bValue + addr.IP) % coreSize; // temporary address stuffed with the direct value to evaluate actions and help with indirect mode

        addr.instr.operandB.executeB(core, addr);

        // execute instruction
        addr.report.execute(addr.IP);

        addr.instr.operator.preExecute(addr);

        if (! addr.instr.modifier.execute(addr.instr.operator, core, addr)) {
            return addr.report;
        }

        addr.instr.operator.postExecute(addr);

        // point the IP to the next instruction
        currentW.addProc((addr.IP + 1) % coreSize);

        currentW = currentW.getNextWarrior();

        return addr.report;
    }

    public void killProc(StepReport report) {

        // delete the current process
        report.pDie();
        report.numProc(currentW.numProc());
        if (currentW.numProc() > 0)
        {
            currentW = currentW.getNextWarrior();
            return;
        }

        // else if that was the last process in that warrior kill it
        report.wDie();
        numWarriors--;
        currentW.setPCell(0, numWarriors);
        currentW = currentW.getNextWarrior();
        currentW.getPrevWarrior().Remove();
    }

}
