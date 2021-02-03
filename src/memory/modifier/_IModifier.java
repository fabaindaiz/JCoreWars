package memory.modifier;

import memory.Address;
import memory.Memory;
import memory.operator._IOperator;

public interface _IModifier {

    boolean execute(_IOperator operator, Memory[] core, Address addr);
}
