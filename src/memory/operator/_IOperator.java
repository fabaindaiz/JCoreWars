package memory.operator;

import memory.Address;
import memory.Memory;

public interface _IOperator {

    void preExecute(Address addr);

    void postExecute(Address addr);

    boolean executeI(Memory[] core, Address addr);

    boolean executeA(Memory[] core, Address addr);

    boolean executeB(Memory[] core, Address addr);

    boolean executeAB(Memory[] core, Address addr);

    boolean executeBA(Memory[] core, Address addr);

    boolean executeF(Memory[] core, Address addr);

    boolean executeX(Memory[] core, Address addr);
}
