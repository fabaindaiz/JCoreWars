package memory.modifier;

import memory.Address;
import memory.Memory;
import memory.operator._IOperator;

public class BA extends _AModifier {

    @Override
    public String toString(){
        return " .BA";
    }

    @Override
    public boolean execute(_IOperator operator, Memory[] core, Address addr) {
        return operator.executeBA(core, addr);
    }
}
