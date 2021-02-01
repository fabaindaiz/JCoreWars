package Memory;

import Memory.modifier._IModifier;
import Memory.operand._IOperand;
import Memory.operator._IOperator;

public class Memory {

    // fields of a memory cell
    public _IOperator operator;
    public _IModifier modifier;
    public _IOperand operandA;
    public _IOperand operandB;
    public int aValue, bValue;

    public Memory() {
        aValue = 0;
        bValue = 0;
    }

    public void copy(Memory src) {
        //POR MODIFICAR
        operator = src.operator;
        modifier = src.modifier;
        operandA = src.operandA;
        operandB = src.operandB;
        aValue = src.aValue;
        bValue = src.bValue;
    }

    public boolean equals(Memory comp)
    {
        /*if ((opcode != comp.opcode) ||
                (modifier != comp.modifier) ||
                (aIndir != comp.aIndir) ||
                (aAction != comp.aAction) ||
                (aTarget != comp.aTarget) ||
                (aTiming != comp.aTiming) ||
                (aValue != comp.aValue) ||
                (bIndir != comp.bIndir) ||
                (bAction != comp.bAction) ||
                (bTarget != comp.bTarget) ||
                (bTiming != comp.bTiming) ||
                (bValue != comp.bValue))
            return false;
            */
        return true;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof Memory)
            return equals((Memory) obj);

        return false;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();

        str.append(operator.toString());

        str.append(modifier.toString());

        str.append(operandA);

        int i = 6 - Integer.toString(aValue).length();
        for (;i > 0; i--)
        {
            str.append(" ");
        }

        str.append(" " + aValue + ", ");

        str.append(operandB.toString());

        i = 6 - Integer.toString(bValue).length();
        for (;i > 0; i--)
        {
            str.append(" ");
        }

        str.append(" " + bValue);

        return str.toString();
    }

};
