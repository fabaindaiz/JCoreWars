/*-
 * Copyright (c) 1998 Brian Haskin jr.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 */

package steplistener;

import marsVM.WarriorObj;
import memory.Memory;

public class StepReport
{
	protected Memory[] core;
	protected WarriorObj warrior;
	
	protected int[] readAddr;
	protected int[] indirReadAddr;
	protected int[] writeAddr;
	protected int[] decAddr;
	protected int[] incAddr;
	protected int numRead;
	protected int numIndirRead;
	protected int numWrite;
	protected int numDec;
	protected int numInc;
	protected int execAddr;
	protected boolean pDie;
	protected int numProc;
	protected boolean wDeath;

	protected final static int MAX_READS = 4;
	protected final static int MAX_WRITES = 4;
	protected final static int MAX_INDIR_READS = 4;
	protected final static int MAX_DECS = 5;
	protected final static int MAX_INCS = 5;

	/**
	 * Creates a StepReport object.
	 */
	public StepReport()
	{
		readAddr = new int[MAX_READS];
		numRead = 0;
		indirReadAddr = new int[MAX_INDIR_READS];
		numIndirRead = 0;
		writeAddr = new int[MAX_WRITES];
		numWrite = 0;
		decAddr = new int[MAX_DECS];
		numDec = 0;
		incAddr = new int[MAX_INCS];
		numInc = 0;
		execAddr = -1;
		pDie = false;
		wDeath = false;
	}

	public void setCore(Memory[] c) {
		core = c;
	}
	
	/**
	 * Set the Warrior object
	 * @param warr - Warrior object of currently executing warrior.
	 */
	public void warrior(WarriorObj warr)
	{
		warrior = warr;
	}
	
	/**
	 * Set a location read from.
	 * @param addr - address of location read
	 */
	public void read(int addr)
	{
		readAddr[numRead] = addr;
		numRead++;
	}
	
	/**
	 * Set a location read from indirection
	 * @param addr - address of location read
	 */
	public void indirRead(int addr)
	{
		indirReadAddr[numIndirRead] = addr;
		numIndirRead++;
	}
	
	/**
	 * Set a location that was written to
	 * @param addr - address written to
	 */
	public void write(int addr)
	{
		writeAddr[numWrite] = addr;
		numWrite++;
	}
	
	/**
	 * Set a location that was decremented
	 * @param addr - address that was decremented
	 */
	public void decrement(int addr)
	{
		decAddr[numDec] = addr;
		numDec++;
	}
		
	/**
	 * Set a location that was incremented
	 * @param addr - address that was incremented
	 */
	public void increment(int addr)
	{
		incAddr[numInc] = addr;
		numInc++;
	}
	
	/**
	 * Set the location that was executed
	 * @param addr - address that was executed
	 */
	public void execute(int addr)
	{
		execAddr = addr;
	}
	
	/**
	 * set the number of processes in the current warrior.
	 * @param numP - number of processes
	 */
	public void numProc(int numP)
	{
		numProc = numP;
	}
	
	/**
	 * called if a process dies
	 */
	public void pDie()
	{
		pDie = true;
	}
	
	/**
	 * called if warrior dies
	 */
	public void wDie()
	{
		wDeath = true;
	}
	
	/**
	 * Get the warrior object of the currently executing warrior
	 * @return old.marsVM.WarriorObj
	 */
	public WarriorObj warrior()
	{
		return warrior;
	}
	
	/**
	 * Get the addresses read
	 * @return int[] - array of addresses
	 */
	public int[] addrRead()
	{
		int[] value = new int[numRead];

		if (numRead >= 0) System.arraycopy(readAddr, 0, value, 0, numRead);
		
		return value;
	}
	
	/**
	 * Get the addresses read through indirection
	 * @return int[] - array of addresses
	 */
	public int[] addrIndirRead()
	{
		int[] value = new int[numIndirRead];

		if (numIndirRead >= 0) System.arraycopy(indirReadAddr, 0, value, 0, numIndirRead);
			
		return value;
	}
	
	/**
	 * Get the addresses written to
	 * @return int[] - array of addresses
	 */
	public int[] addrWrite()
	{
		int[] value = new int[numWrite];

		if (numWrite >= 0) System.arraycopy(writeAddr, 0, value, 0, numWrite);
		
		return value;
	}
	
	/**
	 * Get the addresses decremented
	 * @return int[] - array of addresses
	 */
	public int[] addrDec()
	{
		int[] value = new int[numDec];

		if (numDec >= 0) System.arraycopy(decAddr, 0, value, 0, numDec);
		
		return value;
	}
	
	/**
	 * Get the addresses incremented
	 * @return int[] - array of addresses
	 */
	public int[] addrInc()
	{
		int[] value = new int[numInc];

		if (numInc >= 0) System.arraycopy(incAddr, 0, value, 0, numInc);
		
		return value;
	}
	
	/**
	 * Get the address executed
	 * @return int - address
	 */
	public int addrExec()
	{
		return execAddr;
	}
	
	/**
	 * Get the number of processes
	 * @return int - number of processes
	 */
	public int numProc()
	{
		return numProc;
	}
	
	/**
	 * Check to see if current process died
	 * @return boolean - true if process died
	 */
	public boolean pDeath()
	{
		return pDie;
	}
	
	/**
	 * Check to see if warrior died
	 * @return boolean - true if warrior died
	 */
	public boolean wDeath()
	{
		return wDeath;
	}
}
