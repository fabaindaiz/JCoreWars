package fabaindaiz.jcorewars.marsVM;

import fabaindaiz.jcorewars.memory.Memory;
import fabaindaiz.jcorewars.warrior.WarriorExecutor;
import fabaindaiz.jcorewars.warrior.WarriorManager;

public class StepExecutor {

    public WarriorManager manager;
    public WarriorExecutor warrior;

    public StepReport report;
    public Memory[] core;
    public int coreSize;
    public int numProc;

    public Memory instr = new Memory();	// copy of current instruction
    public int tempAddr;		// temporary address for use in mode evaluation

    public Memory instrA = new Memory();
    public int addrA = 0;			// A's address
    public int addrAAValue = 0;	// address A's A Value
    public int addrABValue = 0;	// address B's B Value

    public Memory instrB = new Memory();
    public int addrB = 0;			// address B
    public int addrBAValue = 0;	// address B's A Value
    public int addrBBValue = 0;	// address B's B Value

    public MarsVM executer;
    public int currentInstr;

    public StepExecutor(MarsVM executer) {
        this.executer = executer;
        this.core = executer.core;
        this.coreSize = executer.coreSize;

        manager = executer.warriorManager;
        warrior = manager.getWarrior();
        report = new StepReport(executer.core, warrior);
        numProc = warrior.numProcess();
    }

    public StepReport executeStep() {
        currentInstr = warrior.getProcess();
        instr.copy(core[currentInstr]);

        tempAddr = (instr.aValue + currentInstr) % coreSize;
        instr.operandA.executeA(core, this);

        tempAddr = (instr.bValue + currentInstr) % coreSize;
        instr.operandB.executeB(core, this);

        report.execute(currentInstr);
        instr.operator.preExecute(this);

        if (! instr.modifier.execute(instr.operator, core, this)) {
            return report;
        }

        instr.operator.postExecute(this);

        warrior.addProcess((currentInstr + 1) % coreSize);
        manager.nextWarrior();

        return report;
    }

    public void killProcess(StepReport report) {

        report.pDie();
        report.numProc(warrior.numProcess());
        if (warrior.numProcess() > 0) {
            manager.nextWarrior();
            return;
        }

        report.wDie();
        manager.removeWarrior();
    }

}
