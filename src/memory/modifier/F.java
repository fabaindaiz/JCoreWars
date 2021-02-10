package memory.modifier;

import memory.Address;
import memory.Memory;
import memory.operator._IOperator;

public class F extends _AModifier {

    @Override
    public String toString(){
        return ".F ";
    }

    @Override
    public boolean execute(_IOperator operator, Memory[] core, Address addr) {
        return operator.executeF(core, addr);
    }
}
