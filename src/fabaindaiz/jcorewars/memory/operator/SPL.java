package fabaindaiz.jcorewars.memory.operator;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

/**
 * SPL queues the next instruction and then queues the sum of the program counter and A-pointer.
 */
public class SPL implements _IOperator {

    @Override
    public String toString() {
        return "SPL";
    }

    @Override
    public void preExecute(StepExecutor addr) {
        return;
    }

    @Override
    public void postExecute(StepExecutor addr) {
        return;
    }

    @Override
    public boolean executeI(Memory[] core, StepExecutor addr) {
        return execute(addr);
    }

    @Override
    public boolean executeA(Memory[] core, StepExecutor addr) {
        return execute(addr);
    }

    @Override
    public boolean executeB(Memory[] core, StepExecutor addr) {
        return execute(addr);
    }

    @Override
    public boolean executeAB(Memory[] core, StepExecutor addr) {
        return execute(addr);
    }

    @Override
    public boolean executeBA(Memory[] core, StepExecutor addr) {
        return execute(addr);
    }

    @Override
    public boolean executeF(Memory[] core, StepExecutor addr) {
        return execute(addr);
    }

    @Override
    public boolean executeX(Memory[] core, StepExecutor addr) {
        return execute(addr);
    }

    private boolean execute(StepExecutor addr) {
        addr.warrior.addProcess((addr.currentInstr + 1) % addr.executer.coreSize);
        if (addr.warrior.numProcess() >= addr.executer.maxProc) {
            addr.manager.nextWarrior();
            return false;
        }
        addr.warrior.addProcess(addr.addrA);
        addr.report.numProc(addr.warrior.numProcess());
        addr.manager.nextWarrior();
        return false;
    }
}
