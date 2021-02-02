package memory;

import memory.modifier._IModifier;
import memory.operand.Hash;
import memory.operand._IOperand;
import memory.operator._IOperator;

import memory.operator.DAT;
import memory.modifier.F;

public class Memory {

    // fields of a memory cell
    public _IOperator operator;
    public _IModifier modifier;
    public _IOperand operandA;
    public _IOperand operandB;
    public int aValue, bValue;

    public Memory() {
        operator = new DAT();
        modifier = new F();
        operandA = new Hash();
        operandB = new Hash();
        aValue = 0;
        bValue = 0;
    }

    public void copy(Memory src) {
        operator = src.operator;
        modifier = src.modifier;
        operandA = src.operandA;
        operandB = src.operandB;
        aValue = src.aValue;
        bValue = src.bValue;
    }

    public boolean equals(Memory comp)
    {
        if ((operator.toString() != comp.operator.toString()) ||
            (modifier.toString() != comp.modifier.toString()) ||
            (operandA.toString() != comp.operandA.toString()) ||
            (operandB.toString() != comp.operandB.toString()) ||
            (aValue != comp.aValue) ||
            (bValue != comp.bValue))
            return false;

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
