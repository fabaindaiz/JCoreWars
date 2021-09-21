package fabaindaiz.jcorewars.memory.modifier;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;
import fabaindaiz.jcorewars.memory.operator._IOperator;

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
