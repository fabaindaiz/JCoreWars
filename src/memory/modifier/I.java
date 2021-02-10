package memory.modifier;

import memory.Address;
import memory.Memory;
import memory.operator._IOperator;

public class I extends _AModifier {

    @Override
    public String toString(){
        return " .I ";
    }

    @Override
    public boolean execute(_IOperator operator, Memory[] core, Address addr) {
        return operator.executeI(core, addr);
    }
}
