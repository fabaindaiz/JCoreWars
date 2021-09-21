package fabaindaiz.jcorewars.memory.operand;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

public interface  _IOperand {

    void executeA(Memory[] core, StepExecutor addr);

    void executeB(Memory[] core, StepExecutor addr);

}
