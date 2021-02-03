package memory.operand;

import memory.Address;
import memory.Memory;

public interface _IOperand {

    void executeA(Memory[] core, Address addr);

    void executeB(Memory[] core, Address addr);
}
