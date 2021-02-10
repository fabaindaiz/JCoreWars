package memory.modifier;

import memory.Address;
import memory.Memory;
import memory.operator._IOperator;

public class X extends _AModifier {

    @Override
    public String toString(){
        return " .X";
    }

    @Override
    public boolean execute(_IOperator operator, Memory[] core, Address addr) {
        return operator.executeX(core, addr);
    }
}
