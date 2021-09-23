package fabaindaiz.jcorewars.memory.operand;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

/**
 * Represents a class which can represent an instruction operand
 */
public interface _IOperand {

    /**
     * Execute current reference evaluation with operand A
     *
     * @param core Core reference
     * @param addr Current step executor
     */
    void executeA(Memory[] core, StepExecutor addr);

    /**
     * Execute current reference evaluation with operand B
     *
     * @param core Core reference
     * @param addr Current step executor
     */
    void executeB(Memory[] core, StepExecutor addr);

    /**
     * Return string representation for instruction operand
     *
     * @return String representation
     */
    @Override
    String toString();

}
