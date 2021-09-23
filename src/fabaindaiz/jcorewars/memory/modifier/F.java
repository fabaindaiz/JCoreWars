package fabaindaiz.jcorewars.memory.modifier;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;
import fabaindaiz.jcorewars.memory.operator._IOperator;

/**
 * Instructions use both the A-numbers and the B-numbers, using and writing A-to-A, B-to-B.
 */
public class F implements _IModifier {

    @Override
    public String toString() {
        return "  .F ";
    }

    @Override
    public boolean execute(_IOperator operator, Memory[] core, StepExecutor addr) {
        return operator.executeF(core, addr);
    }

}
