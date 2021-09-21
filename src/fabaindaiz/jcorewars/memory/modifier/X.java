package fabaindaiz.jcorewars.memory.modifier;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;
import fabaindaiz.jcorewars.memory.operator._IOperator;

public class X implements _IModifier {

    @Override
    public String toString(){
        return " .X";
    }

    @Override
    public boolean execute(_IOperator operator, Memory[] core, StepExecutor addr) {
        return operator.executeX(core, addr);
    }

}
