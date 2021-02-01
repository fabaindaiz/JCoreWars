package memory.modifier;

import memory.Address;
import memory.Memory;
import memory.operator._IOperator;

public class B extends _AModifier {

    public boolean execute(_IOperator operator, Memory[] core, Address addr) {
        return operator.executeB(core, addr);
    }
}
