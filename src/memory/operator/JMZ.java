package memory.operator;

import memory.Address;
import memory.Memory;

public class JMZ extends _AOperator {

    @Override
    public String toString() {
        return "JMZ";
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
        if ((addr.addrBAValue == 0) || (addr.addrBBValue == 0))
            return execute(addr);
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, Address addr) {
        return executeBA(core, addr);
    }

    @Override
    public boolean executeB(Memory[] core, Address addr) {
        return executeAB(core, addr);
    }

    @Override
    public boolean executeAB(Memory[] core, Address addr) {
        if (addr.addrBBValue == 0)
            return execute(addr);
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, Address addr) {
        if (addr.addrBAValue == 0)
            return execute(addr);
        return true;
    }

    @Override
    public boolean executeF(Memory[] core, Address addr) {
        return executeI(core, addr);
    }

    @Override
    public boolean executeX(Memory[] core, Address addr) {
        return executeI(core, addr);
    }

    private boolean execute(Address addr) {
        addr.executer.currentW.addProc(addr.addrA);
        addr.executer.currentW = addr.executer.currentW.getNextWarrior();
        return false;
    }
}
