package memory;

import steplistener.StepReport;
import marsVM.MarsVM;

public class Address {

    public StepReport report = new StepReport();

    public Memory instr = new Memory();	// copy of current instruction
    public int tempAddr;		// temporary address for use in mode evaluation

    public Memory instrA = new Memory();
    public int addrA = 0;			// A's address
    public int addrAAValue = 0;	// address A's A Value
    public int addrABValue = 0;	// address B's B Value

    public Memory instrB = new Memory();
    public int addrB = 0;			// address B
    public int addrBAValue = 0;	// address B's A Value
    public int addrBBValue = 0;	// address B's B Value

    public MarsVM executer;
    public int IP;

}
