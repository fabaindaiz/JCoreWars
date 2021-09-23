package fabaindaiz.jcorewars.memory.modifier;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;
import fabaindaiz.jcorewars.memory.operator._IOperator;

/**
 * Instructions use the A-numbers of the A-instructions and the B-numbers of the B-instructions and write B-numbers.
 */
public class AB implements _IModifier {

    @Override
    public String toString() {
        return " .AB";
    }

    @Override
    public boolean execute(_IOperator operator, Memory[] core, StepExecutor addr) {
        return operator.executeAB(core, addr);
    }

}
