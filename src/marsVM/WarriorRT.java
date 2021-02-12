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

/* 
 * This class implements the warrior runtime object for corewars
 * it basically takes care of all the proccess management for MarsVM
 */
 
package marsVM;

import steplistener.CustomListModel;

public class WarriorRT
{
	protected CustomListModel<Integer> procQueue;
	protected WarriorObj warrior;
	protected int[] pspace;
	protected int numProc;		// Current number of processes

	protected WarriorRT prev;	// previous warrior in the execute queue
	protected WarriorRT next;	// next warrior in the execute queue
	
	public WarriorRT(WarriorObj war, int firstInst, int[] p)
	{
		war.setWarriorRT(this);
		warrior = war;

		procQueue = new CustomListModel<>();
		procQueue.addElement(firstInst);
		numProc = 1;

		pspace = p;

		prev = this;
		next = this;
	}
	
	public void Insert(WarriorRT prevWarr, WarriorRT nextWarr)
	{
		prev = prevWarr;
		next = nextWarr;
		
		prevWarr.next = this;
		nextWarr.prev = this;
	}
	
	public void Remove()
	{
		prev.next = next;
		next.prev = prev;
		
		prev = this;
		next = this;
	}
	
	public WarriorObj warrior()
	{
		return warrior;
	}

	public CustomListModel<Integer> getProcQueue() {
		return procQueue;
	}

	public void addProc(int inst)
	{
		numProc++;
		procQueue.addElement(inst);
	}
	
	public int getProc()
	{
		numProc--;
		return procQueue.remove(0);
	}

	public WarriorRT getPrevWarrior()
	{
		return prev;
	}
	
	public WarriorRT getNextWarrior()
	{
		return next;
	}
	
	public int numProc()
	{
		return numProc;
	}

	public void setPCell(int addr, int value)
	{
		pspace[addr%pspace.length] = value;
	}
	
	public int getPCell(int addr)
	{
		return pspace[addr%pspace.length];
	}
	
	public int[] getPSpace()
	{
		return pspace;
	}
}
		
	
