package Memory.operator;

import Memory.Address;
import Memory.Memory;
import marsVM.MarsVM;

public interface _IOperator {

    public void setExecuter(MarsVM vm);

    public void preExecute(Address addr);

    public void postExecute(Address addr);

    public boolean executeI(Memory[] core, Address addr);

    public boolean executeA(Memory[] core, Address addr);

    public boolean executeB(Memory[] core, Address addr);

    public boolean executeAB(Memory[] core, Address addr);

    public boolean executeBA(Memory[] core, Address addr);

    public boolean executeF(Memory[] core, Address addr);

    public boolean executeX(Memory[] core, Address addr);
}
