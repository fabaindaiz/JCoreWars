package memory.operand;

import memory.Address;
import memory.Memory;

public interface _IOperand {

    public void executeA(Memory[] core, Address addr);

    public void executeB(Memory[] core, Address addr);
}
