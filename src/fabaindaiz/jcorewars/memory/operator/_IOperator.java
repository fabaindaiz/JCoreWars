package fabaindaiz.jcorewars.memory.operator;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

public interface _IOperator {

    void preExecute(StepExecutor addr);

    void postExecute(StepExecutor addr);

    boolean executeI(Memory[] core, StepExecutor addr);

    boolean executeA(Memory[] core, StepExecutor addr);

    boolean executeB(Memory[] core, StepExecutor addr);

    boolean executeAB(Memory[] core, StepExecutor addr);

    boolean executeBA(Memory[] core, StepExecutor addr);

    boolean executeF(Memory[] core, StepExecutor addr);

    boolean executeX(Memory[] core, StepExecutor addr);
}
