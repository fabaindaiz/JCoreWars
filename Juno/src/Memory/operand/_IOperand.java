package Memory.operand;

import Memory.Address;
import Memory.Memory;

public interface _IOperand {

    public void executeA(Memory[] core, Address addr);

    public void executeB(Memory[] core, Address addr);
}
