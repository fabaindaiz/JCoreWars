package memory.modifier;

import memory.Address;
import memory.Memory;
import memory.operator._IOperator;

public class AB extends _AModifier {

    @Override
    public String toString(){
        return ".AB";
    }

    @Override
    public boolean execute(_IOperator operator, Memory[] core, Address addr) {
        return operator.executeAB(core, addr);
    }
}
