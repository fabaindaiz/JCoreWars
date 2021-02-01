package memory.operator;

import memory.Address;
import memory.Memory;

public class JMP extends _AOperator {

    @Override
    public String toString() {
        return "JMP";
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
        return execute(addr);
    }

    @Override
    public boolean executeA(Memory[] core, Address addr) {
        return execute(addr);
    }

    @Override
    public boolean executeB(Memory[] core, Address addr) {
        return execute(addr);
    }

    @Override
    public boolean executeAB(Memory[] core, Address addr) {
        return execute(addr);
    }

    @Override
    public boolean executeBA(Memory[] core, Address addr) {
        return execute(addr);
    }

    @Override
    public boolean executeF(Memory[] core, Address addr) {
        return execute(addr);
    }

    @Override
    public boolean executeX(Memory[] core, Address addr) {
        return execute(addr);
    }

    private boolean execute(Address addr) {
        addr.executer.currentW.addProc(addr.addrA);
        addr.executer.currentW = addr.executer.currentW.getNextWarrior();
        return false;
    }
}
