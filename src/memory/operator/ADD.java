package memory.operator;

import memory.Address;
import memory.Memory;

public class ADD extends _AOperator {

    @Override
    public String toString(){
        return "ADD";
    }

    @Override
    public void preExecute(Address addr) {
        return;
    }

    @Override
    public void postExecute(Address addr) {
        addr.report.read(addr.addrA);
        addr.report.write(addr.addrB);
    }

    @Override
    public boolean executeI(Memory[] core, Address addr) {
        core[addr.addrB].aValue = (addr.addrBAValue + addr.addrAAValue) % addr.executer.coreSize;
        core[addr.addrB].bValue = (addr.addrBBValue + addr.addrABValue) % addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, Address addr) {
        core[addr.addrB].aValue = (addr.addrBAValue + addr.addrAAValue) % addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeB(Memory[] core, Address addr) {
        core[addr.addrB].bValue = (addr.addrBBValue + addr.addrABValue) % addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeAB(Memory[] core, Address addr) {
        core[addr.addrB].bValue = (addr.addrBBValue + addr.addrAAValue) % addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, Address addr) {
        core[addr.addrB].aValue = (addr.addrBAValue + addr.addrABValue) % addr.executer.coreSize;
        return true;
    }

    @Override
    public boolean executeF(Memory[] core, Address addr) {
        return executeI(core, addr);
    }

    @Override
    public boolean executeX(Memory[] core, Address addr) {
        core[addr.addrB].bValue = (addr.addrBAValue + addr.addrABValue) % addr.executer.coreSize;
        core[addr.addrB].aValue = (addr.addrBBValue + addr.addrAAValue) % addr.executer.coreSize;
        return true;
    }
}
