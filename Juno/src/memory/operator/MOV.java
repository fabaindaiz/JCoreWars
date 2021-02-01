package Memory.operator;

import Memory.Address;
import Memory.Memory;

public class MOV extends _AOperator {

    @Override
    public String toString(){
        return "MOV";
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
        core[addr.addrB].copy(addr.instrA);
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, Address addr) {
        core[addr.addrB].aValue = addr.addrAAValue;
        return true;
    }

    @Override
    public boolean executeB(Memory[] core, Address addr) {
        core[addr.addrB].bValue = addr.addrABValue;
        return true;
    }

    @Override
    public boolean executeAB(Memory[] core, Address addr) {
        core[addr.addrB].bValue = addr.addrAAValue;
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, Address addr) {
        core[addr.addrB].aValue = addr.addrABValue;
        return true;
    }

    @Override
    public boolean executeF(Memory[] core, Address addr) {
        core[addr.addrB].aValue = addr.addrAAValue;
        return true;
        // fallthrough for rest
    }

    @Override
    public boolean executeX(Memory[] core, Address addr) {
        core[addr.addrB].bValue = addr.addrAAValue;
        return true;
        // fallthrough for rest
    }
}
