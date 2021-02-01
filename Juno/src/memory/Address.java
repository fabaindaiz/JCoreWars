package Memory;

import frontend.StepReport;

public class Address {

    public StepReport report = new StepReport();

    public Memory instr = new Memory();	// copy of current instruction
    public int tempAddr;		// temporary address for use in mode evaluation
    public int addrA = 0;			// A's address
    public Memory instrA = new Memory();
    public int addrAAValue = 0;	// address A's A Value
    public int addrABValue = 0;	// address B's B Value
    public int addrB = 0;			// address B
    public Memory instrB = new Memory();
    public int addrBAValue = 0;	// address B's A Value
    public int addrBBValue = 0;	// address B's B Value

    public int coreSize = 8000; //TEMPORAL

    public int IP;

}
