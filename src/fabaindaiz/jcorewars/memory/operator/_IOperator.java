package fabaindaiz.jcorewars.memory.operator;

import fabaindaiz.jcorewars.marsVM.StepExecutor;
import fabaindaiz.jcorewars.memory.Memory;

/**
 * Represents a class which can represents an instruction
 */
public interface _IOperator {

    /**
     * Pre execution tasks for instruction
     * @param addr Current step executor
     */
    void preExecute(StepExecutor addr);

    /**
     * Post execution tasks for instruction
     * @param addr Current step executor
     */
    void postExecute(StepExecutor addr);

    /**
     * Execute current instruction with operator I
     * @param core Core reference
     * @param addr Current step executor
     * @return true if execution complete, otherwise false
     */
    boolean executeI(Memory[] core, StepExecutor addr);

    /**
     * Execute current instruction with operator A
     * @param core Core reference
     * @param addr Current step executor
     * @return true if execution complete, otherwise false
     */
    boolean executeA(Memory[] core, StepExecutor addr);

    /**
     * Execute current instruction with operator B
     * @param core Core reference
     * @param addr Current step executor
     * @return true if execution complete, otherwise false
     */
    boolean executeB(Memory[] core, StepExecutor addr);

    /**
     * Execute current instruction with operator AB
     * @param core Core reference
     * @param addr Current step executor
     * @return true if execution complete, otherwise false
     */
    boolean executeAB(Memory[] core, StepExecutor addr);

    /**
     * Execute current instruction with operator BA
     * @param core Core reference
     * @param addr Current step executor
     * @return true if execution complete, otherwise false
     */
    boolean executeBA(Memory[] core, StepExecutor addr);

    /**
     * Execute current instruction with operator F
     * @param core Core reference
     * @param addr Current step executor
     * @return true if execution complete, otherwise false
     */
    boolean executeF(Memory[] core, StepExecutor addr);

    /**
     * Execute current instruction with operator X
     * @param core Core reference
     * @param addr Current step executor
     * @return true if execution complete, otherwise false
     */
    boolean executeX(Memory[] core, StepExecutor addr);

    /**
     * Return string representation for operator
     * @return String representation
     */
    @Override
    String toString();

}
