package fabaindaiz.jcorewars.memory.modifier;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;
import fabaindaiz.jcorewars.memory.operator._IOperator;

/**
 * Instructions use the B-numbers of the A-instructions and the A-numbers of the B-instructions and write A-numbers.
 */
public class BA implements _IModifier {

    @Override
    public String toString(){
        return " .BA";
    }

    @Override
    public boolean execute(_IOperator operator, Memory[] core, StepExecutor addr) {
        return operator.executeBA(core, addr);
    }

}
