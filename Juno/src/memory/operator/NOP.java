package memory.operator;

import memory.Address;
import memory.Memory;

public class NOP extends _AOperator {

    @Override
    public String toString() {
        return "NOP";
    }

    @Override
    public void preExecute(Address addr) {
        return;
    }

    @Override
    public void postExecute(Address addr) {
        return;
    }

    @Override
    public boolean executeI(Memory[] core, Address addr) {
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, Address addr) {
        return true;
    }

    @Override
    public boolean executeB(Memory[] core, Address addr) {
        return true;
    }

    @Override
    public boolean executeAB(Memory[] core, Address addr) {
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, Address addr) {
        return true;
    }

    @Override
    public boolean executeF(Memory[] core, Address addr) {
        return true;
    }

    @Override
    public boolean executeX(Memory[] core, Address addr) {
        return true;
    }
}
