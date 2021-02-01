package Memory.operator;

import Memory.Address;
import Memory.Memory;

public class DAT extends _AOperator {


    @Override
    public String toString(){
        return "DAT";
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
        executer.killProc(addr.report);
        return false;
    }

    @Override
    public boolean executeA(Memory[] core, Address addr) {
        executer.killProc(addr.report);
        return false;
    }

    @Override
    public boolean executeB(Memory[] core, Address addr) {
        executer.killProc(addr.report);
        return false;
    }

    @Override
    public boolean executeAB(Memory[] core, Address addr) {
        executer.killProc(addr.report);
        return false;
    }

    @Override
    public boolean executeBA(Memory[] core, Address addr) {
        executer.killProc(addr.report);
        return false;
    }

    @Override
    public boolean executeF(Memory[] core, Address addr) {
        executer.killProc(addr.report);
        return false;
    }

    @Override
    public boolean executeX(Memory[] core, Address addr) {
        executer.killProc(addr.report);
        return false;
    }
}
