package Memory.operator;

import Memory.Address;
import Memory.Memory;

public class MOD extends _AOperator {

    @Override
    public String toString(){
        return "MOD";
    }

    @Override
    public void preExecute(Address addr) {
        addr.report.read(addr.addrA);
    }

    @Override
    public void postExecute(Address addr) {
        addr.report.write(addr.addrB);
    }

    @Override
    public boolean executeI(Memory[] core, Address addr) {
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, Address addr) {
        if (addr.addrAAValue == 0)
        {
            executer.killProc(addr.report);
            return false;
        }
        core[addr.addrB].aValue = addr.addrBAValue % addr.addrAAValue;
        return true;
    }

    @Override
    public boolean executeB(Memory[] core, Address addr) {
        if (addr.addrABValue == 0)
        {
            executer.killProc(addr.report);
            return false;
        }
        core[addr.addrB].bValue = addr.addrBBValue % addr.addrABValue;
        return true;
    }

    @Override
    public boolean executeAB(Memory[] core, Address addr) {
        if (addr.addrAAValue == 0)
        {
            executer.killProc(addr.report);
            return false;
        }
        core[addr.addrB].bValue = addr.addrBBValue % addr.addrAAValue;
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, Address addr) {
        if (addr.addrABValue == 0)
        {
            executer.killProc(addr.report);
            return false;
        }
        core[addr.addrB].aValue = addr.addrBAValue % addr.addrABValue;
        return true;
    }

    @Override
    public boolean executeF(Memory[] core, Address addr) {
        if (addr.addrAAValue != 0)
        {
            core[addr.addrB].aValue = addr.addrBAValue % addr.addrAAValue;
            if (addr.addrABValue == 0)
            {
                executer.killProc(addr.report);
                return false;
            }
            core[addr.addrB].bValue = addr.addrBBValue % addr.addrABValue;
            return true;
        } else {
            if (addr.addrABValue == 0)
            {
                executer.killProc(addr.report);
                return false;
            }
            core[addr.addrB].bValue = addr.addrBBValue % addr.addrABValue;
            addr.report.write(addr.addrB);
            executer.killProc(addr.report);
            return false;
        }
    }

    @Override
    public boolean executeX(Memory[] core, Address addr) {
        if (addr.addrABValue != 0)
        {
            core[addr.addrB].aValue = addr.addrBAValue % addr.addrABValue;
            if (addr.addrAAValue == 0)
            {
                executer.killProc(addr.report);
                return false;
            }
            core[addr.addrB].bValue = addr.addrBBValue % addr.addrAAValue;
            return true;
        } else {
            if (addr.addrAAValue == 0)
            {
                executer.killProc(addr.report);
                return false;
            }
            core[addr.addrB].bValue = addr.addrBBValue % addr.addrAAValue;
            addr.report.write(addr.addrB);
            executer.killProc(addr.report);
            return false;
        }
    }
}
