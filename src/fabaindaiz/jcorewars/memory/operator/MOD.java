package fabaindaiz.jcorewars.memory.operator;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

/**
 * MOD replaces the B-target with the integral remainder of dividing the B-value by the A-value
 */
public class MOD implements _IOperator {

    @Override
    public String toString(){
        return "MOD";
    }

    @Override
    public void preExecute(StepExecutor addr) {
        addr.report.read(addr.addrA);
    }

    @Override
    public void postExecute(StepExecutor addr) {
        addr.report.write(addr.addrB);
    }

    @Override
    public boolean executeI(Memory[] core, StepExecutor addr) {
        if (addr.addrAAValue != 0)
            core[addr.addrB].aValue = addr.addrBBValue % addr.addrAAValue;
        if (addr.addrABValue != 0)
            core[addr.addrB].bValue = addr.addrBAValue % addr.addrABValue;
        if (addr.addrAAValue != 0) {
            addr.killProcess(addr.report);
            return false;
        }
        if (addr.addrABValue != 0) {
            addr.report.write(addr.addrB);
            addr.killProcess(addr.report);
            return false;
        }
        return true;
    }

    @Override
    public boolean executeA(Memory[] core, StepExecutor addr) {
        if (addr.addrAAValue == 0)
        {
            addr.killProcess(addr.report);
            return false;
        }
        core[addr.addrB].aValue = addr.addrBAValue % addr.addrAAValue;
        return true;
    }

    @Override
    public boolean executeB(Memory[] core, StepExecutor addr) {
        if (addr.addrABValue == 0)
        {
            addr.killProcess(addr.report);
            return false;
        }
        core[addr.addrB].bValue = addr.addrBBValue % addr.addrABValue;
        return true;
    }

    @Override
    public boolean executeAB(Memory[] core, StepExecutor addr) {
        if (addr.addrAAValue == 0)
        {
            addr.killProcess(addr.report);
            return false;
        }
        core[addr.addrB].bValue = addr.addrBBValue % addr.addrAAValue;
        return true;
    }

    @Override
    public boolean executeBA(Memory[] core, StepExecutor addr) {
        if (addr.addrABValue == 0)
        {
            addr.killProcess(addr.report);
            return false;
        }
        core[addr.addrB].aValue = addr.addrBAValue % addr.addrABValue;
        return true;
    }

    @Override
    public boolean executeF(Memory[] core, StepExecutor addr) {
        return executeI(core, addr);
    }

    @Override
    public boolean executeX(Memory[] core, StepExecutor addr) {
        if (addr.addrAAValue != 0)
            core[addr.addrB].bValue = addr.addrBBValue % addr.addrAAValue;
        if (addr.addrABValue != 0)
            core[addr.addrB].aValue = addr.addrBAValue % addr.addrABValue;
        if (addr.addrAAValue != 0) {
            addr.killProcess(addr.report);
            return false;
        }
        if (addr.addrABValue != 0) {
            addr.report.write(addr.addrB);
            addr.killProcess(addr.report);
            return false;
        }
        return true;
    }
}
