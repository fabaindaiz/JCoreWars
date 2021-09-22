package fabaindaiz.jcorewars.memory.modifier;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;
import fabaindaiz.jcorewars.memory.operator._IOperator;

/**
 * Represents a class which can represent an instruction modifier
 */
public interface _IModifier {

    /**
     * Execute current instruction with specific modifier
     * @param operator Current operator
     * @param core Core reference
     * @param addr Current step executor
     * @return true if execution complete, otherwise false
     */
    boolean execute(_IOperator operator, Memory[] core, StepExecutor addr);

    /**
     * Return string representation for instruction modifier
     * @return String representation
     */
    @Override
    String toString();

}
