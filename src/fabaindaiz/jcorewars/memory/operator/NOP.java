package fabaindaiz.jcorewars.memory.operator;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

/**
 * No operation takes place.
 */
public class NOP implements _IOperator {

    @Override
    public String toString() {
        return "NOP";
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
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, StepExecutor addr) {
        return true;
    }

    @Override
    public boolean executeB(Memory[] core, StepExecutor addr) {
        return true;
    }

    @Override
    public boolean executeAB(Memory[] core, StepExecutor addr) {
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, StepExecutor addr) {
        return true;
    }

    @Override
    public boolean executeF(Memory[] core, StepExecutor addr) {
        return true;
    }

    @Override
    public boolean executeX(Memory[] core, StepExecutor addr) {
        return true;
    }
}
