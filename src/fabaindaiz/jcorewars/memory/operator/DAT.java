package fabaindaiz.jcorewars.memory.operator;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

public class DAT implements _IOperator {

    @Override
    public String toString(){
        return "DAT";
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
        addr.killProcess(addr.report);
        return false;
    }

    @Override
    public boolean executeA(Memory[] core, StepExecutor addr) {
        addr.killProcess(addr.report);
        return false;
    }

    @Override
    public boolean executeB(Memory[] core, StepExecutor addr) {
        addr.killProcess(addr.report);
        return false;
    }

    @Override
    public boolean executeAB(Memory[] core, StepExecutor addr) {
        addr.killProcess(addr.report);
        return false;
    }

    @Override
    public boolean executeBA(Memory[] core, StepExecutor addr) {
        addr.killProcess(addr.report);
        return false;
    }

    @Override
    public boolean executeF(Memory[] core, StepExecutor addr) {
        addr.killProcess(addr.report);
        return false;
    }

    @Override
    public boolean executeX(Memory[] core, StepExecutor addr) {
        addr.killProcess(addr.report);
        return false;
    }
}
