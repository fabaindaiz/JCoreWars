package fabaindaiz.jcorewars.memory;

import fabaindaiz.jcorewars.memory.operand.Peso;
import fabaindaiz.jcorewars.memory.operator._IOperator;
import fabaindaiz.jcorewars.memory.modifier._IModifier;
import fabaindaiz.jcorewars.memory.operand._IOperand;
import fabaindaiz.jcorewars.memory.operator.DAT;
import fabaindaiz.jcorewars.memory.modifier.F;

public class Memory {

    public _IOperator operator;
    public _IModifier modifier;
    public _IOperand operandA;
    public _IOperand operandB;
    public int aValue, bValue;

    public Memory() {
        operator = new DAT();
        modifier = new F();
        operandA = new Peso();
        operandB = new Peso();
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

    public boolean equals(Memory comp) {
        return (operator.toString().equals(comp.operator.toString())) &&
                (modifier.toString().equals(comp.modifier.toString())) &&
                (operandA.toString().equals(comp.operandA.toString())) &&
                (operandB.toString().equals(comp.operandB.toString())) &&
                (aValue == comp.aValue) &&
                (bValue == comp.bValue);
    }

    public boolean equals(Object obj) {
        if (obj instanceof Memory) {
            return equals((Memory) obj);
        }
        return false;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("   ");
        str.append(String.format("%6s", operator.toString()));
        str.append(String.format("%6s", modifier.toString()));

        str.append(String.format("%6s", operandA.toString()));
        str.append("   ");
        str.append(String.format("%06d", aValue));

        str.append("   ,");

        str.append(String.format("%5s", operandB.toString()));
        str.append("   ");
        str.append(String.format("%06d", bValue));

        return str.toString();
    }

}
