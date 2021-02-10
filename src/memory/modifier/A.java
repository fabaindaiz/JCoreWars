package memory.modifier;

import memory.Address;
import memory.Memory;
import memory.operator._IOperator;

public class A extends _AModifier {

    @Override
    public String toString(){
        return " .A";
    }

    @Override
    public boolean execute(_IOperator operator, Memory[] core, Address addr) {
        return operator.executeA(core, addr);
    }
}
