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
  Reads a warrior from a stream and sends it to the assembler. Then holds
  Compiled warrior and all warrior stats.
 */
 
package marsVM;

import steplistener.StepReport;
import memory.Memory;
import assambler.Assembler;

import java.awt.*;
import java.io.Reader;

public class WarriorObj
{
	public WarriorRT warriorRT;

	public Memory[] wInst;
	public int wOffset;
	protected int[] pSpace;

	protected String name;
	protected String author;

	public Color nColor;
	public Color dColor;
	public Color[] MyColor;
	public Color[] colors;
	public int numProc;
	public boolean Alive;

	public StepReport lastReport;
	
	public WarriorObj(Reader file, int maxLength, Color c, Color d)
	{
		nColor = c;
		dColor = d;

		MyColor = new Color[] {c, c, c};
		colors = new Color[] {c, c.darker(), c.brighter()};

		Assembler parser = new Assembler(file, maxLength);
		
		if (parser.assemble())
		{
			wInst = parser.getWarrior();
			wOffset = parser.getOffset();
			name = parser.getName();
			author = parser.getAuthor();
			Alive = true;
		} else
		{
			wInst = new Memory[0];
			wOffset = 0;
			Alive = false;
		}
		
	}
	
	public Memory[] getMemory(int coreSize)
	{
		for (Memory memory : wInst) {
			while (memory.aValue < 0)
				memory.aValue += coreSize;

			memory.aValue %= coreSize;

			while (memory.bValue < 0)
				memory.bValue += coreSize;

			memory.bValue %= coreSize;
		}

		return wInst;
	}

	public int getSize()
	{
		return wInst.length;
	}

	public Color getNColor() {
		return nColor;
	}
	
	public Color getDColor()
	{
		return dColor;
	}

	public Color[] getMyColor()
	{
		return MyColor;
	}

	public Color[] getColors()
	{
		return colors;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public int getOffset()
	{
		return wOffset;
	}
	
	public void initPSpace(int length)
	{
		pSpace = new int[length];
	}
	
	public int[] getPSpace()
	{
		return pSpace;
	}
	
	public void normalizePSpace(int coreSize)
	{
		for (int i=0; i<pSpace.length; i++)
		{
			while (pSpace[i] < 0)
			{
				pSpace[i] += coreSize;
			}
			
			pSpace[i] %= coreSize;
		}
	}
	
	public void setPCell(int index, int value)
	{
		if (index < 0 || index >= pSpace.length)
		 return;
		 
		pSpace[index] = value;
	}

	public void setWarriorRT(WarriorRT war) {
		warriorRT = war;
	}
}
