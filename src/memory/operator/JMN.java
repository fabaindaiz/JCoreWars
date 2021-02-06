package memory.operator;

import memory.Address;
import memory.Memory;

public class JMN extends _AOperator {

    @Override
    public String toString() {
        return "JMN";
    }

    @Override
    public void preExecute(Address addr) {
        addr.report.read(addr.addrB);
    }

    @Override
    public void postExecute(Address addr) {
        return;
    }

    @Override
    public boolean executeI(Memory[] core, Address addr) {
        if ((addr.addrBAValue != 0) || (addr.addrBBValue != 0))
            return execute(addr);
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
        if (addr.addrBBValue != 0)
            return execute(addr);
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, Address addr) {
        if (addr.addrBAValue != 0)
            return execute(addr);
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

    private boolean execute(Address addr) {
        addr.executer.currentW.addProc(addr.addrA);
        addr.executer.currentW = addr.executer.currentW.getNextWarrior();
        return false;
    }
}
