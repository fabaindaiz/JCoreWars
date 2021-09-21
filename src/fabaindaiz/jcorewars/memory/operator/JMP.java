package fabaindaiz.jcorewars.memory.operator;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

public class JMP implements _IOperator {

    @Override
    public String toString() {
        return "JMP";
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
        addr.warrior.addProcess(addr.addrA);
        addr.manager.nextWarrior();
        return false;
    }
}
