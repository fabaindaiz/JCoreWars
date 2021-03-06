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
 
package assambler;

import memory.Memory;
import memory.modifier.*;
import memory.operand.*;
import memory.operator.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

public class Assembler
{
	protected BufferedReader in;
	protected StreamTokenizer tok;
	protected int IP;
	protected int maxLength;
	protected Memory[] war;
	protected int start;
	
	// meta values
	protected String name;
	protected String author;
	
	
	public Assembler(Reader reader, int maxl)
	{
		in = new BufferedReader(reader);
		tok = new StreamTokenizer(in);
		tok.lowerCaseMode(true);
		tok.eolIsSignificant(true);
		tok.parseNumbers();
		tok.ordinaryChar('/');
		tok.ordinaryChar('.');
		tok.ordinaryChar(',');
		
		IP = 0;
		start = 0;
		maxLength = maxl;
		
		war = new Memory[maxl];
		
		for (int i=0; i<maxl; i++)
			war[i] = new Memory();
	}
	
	public Memory[] getWarrior()
	{
		Memory[] wMem = new Memory[IP];

		if (IP >= 0) System.arraycopy(war, 0, wMem, 0, IP);
		
		return wMem;
	}
	
	public int getOffset()
	{
		return start;
	}
	
	public String getName()
	{
		if (name != null)
			return name;

		return "";
	}
	
	public String getAuthor()
	{
		if (author != null)
			return author;

		return "";
	}
	
	public int length()
	{
		return IP;
	}
	
	public boolean assemble()
	{
		try
		{
			while(tok.nextToken() != StreamTokenizer.TT_EOF)
			{
				if(tok.ttype == ';')
					pComment();
				else if (tok.ttype == StreamTokenizer.TT_WORD && tok.sval.equals("org"))
				{
						if(tok.nextToken() != StreamTokenizer.TT_NUMBER)
							return false;

						start = (int) tok.nval;
						
						tok.nextToken();
						
						if (tok.ttype == ';')
							pComment();
						
				} else if (tok.ttype == StreamTokenizer.TT_WORD)
				{
					switch (tok.sval) {
						case "mov":
							war[IP].operator = new MOV();
							break;
						case "add":
							war[IP].operator = new ADD();
							break;
						case "sub":
							war[IP].operator = new SUB();
							break;
						case "mul":
							war[IP].operator = new MUL();
							break;
						case "div":
							war[IP].operator = new DIV();
							break;
						case "mod":
							war[IP].operator = new MOD();
							break;
						case "jmz":
							war[IP].operator = new JMZ();
							break;
						case "jmn":
							war[IP].operator = new JMN();
							break;
						case "djn":
							war[IP].operator = new DJN();
							break;
						case "cmp":
							war[IP].operator = new CMP();
							break;
						case "seq":
							war[IP].operator = new SEQ();
							break;
						case "slt":
							war[IP].operator = new SLT();
							break;
						case "spl":
							war[IP].operator = new SPL();
							break;
						case "dat":
							war[IP].operator = new DAT();
							break;
						case "jmp":
							war[IP].operator = new JMP();
							break;
						case "sne":
							war[IP].operator = new SNE();
							break;
						case "nop":
							war[IP].operator = new NOP();
							break;
						case "ldp":
							war[IP].operator = new LDP();
							break;
						case "stp":
							war[IP].operator = new STP();
							break;
						case "end":
							if (tok.nextToken() == StreamTokenizer.TT_NUMBER)
								start = (int) tok.nval;

							return true;
						default:
							return false;
					}

					if (!pModifier())
						return false;
						
					if (++IP > maxLength) return false;

					if (tok.ttype == ';')
						pComment();
				} 
				
				if (tok.ttype != StreamTokenizer.TT_EOL)
					return false;
			}

		} catch (IOException e)
		{
			System.out.println(e.toString());
			return false;
		}
		
		return true;
	}
	
	void pComment()
	{
		// this function is in place to get meta data
		try
		{
			if (tok.nextToken() == StreamTokenizer.TT_WORD)
			{ 
				if (tok.sval.equals("name"))
				{
					name = in.readLine();
				} else if (tok.sval.equals("author"))
				{
					author = in.readLine();
				} else
				{
					in.readLine();
				}
			} else
			{
				in.readLine();
			}
			
			tok.ttype = StreamTokenizer.TT_EOL;
			
		} catch (IOException e)
		{
			System.out.println(e.toString());
		}
	}
	
	boolean pModifier()
	{
		try
		{
			if (tok.nextToken() != '.')
				return pAOperand();
			else if (tok.nextToken() == StreamTokenizer.TT_WORD)
			{
				switch (tok.sval) {
					case "a":
						war[IP].modifier = new A();
						break;
					case "b":
						war[IP].modifier = new B();
						break;
					case "ab":
						war[IP].modifier = new AB();
						break;
					case "ba":
						war[IP].modifier = new BA();
						break;
					case "f":
						war[IP].modifier = new F();
						break;
					case "x":
						war[IP].modifier = new X();
						break;
					case "i":
						war[IP].modifier = new I();
						break;
					default:
						return false;
				}

				tok.nextToken();

				return pAOperand();
			} else 
				return false;

		} catch (IOException e)
		{
			System.out.println(e.toString());
			return false;
		}

	}
	
	boolean pAOperand()
	{
		switch (tok.ttype)
		{
			case StreamTokenizer.TT_NUMBER:
				return pAValue();
				
			case '#':
				war[IP].operandA = new Hash();
				break;
				
			case '$':
				war[IP].operandA = new Peso();
				break;
				
			case '@':
				war[IP].operandA = new At();
				break;
				
			case '<':
				war[IP].operandA = new Less();
				break;
				
			case '>':
				war[IP].operandA = new Greater();
				break;
				
			case '*':
				war[IP].operandA = new Asterisk();
				break;
				
			case '{':
				war[IP].operandA = new Open();
				break;
				
			case '}':
				war[IP].operandA = new Close();
				break;
				
			default:
				return false;
				
		}
		
		try
		{
			tok.nextToken();
		} catch (IOException e)
		{
			System.out.println(e.toString());
			return false;
		}

		return pAValue();

	}

	boolean pAValue()
	{
		if (tok.ttype != StreamTokenizer.TT_NUMBER)
			return false;
		
		war[IP].aValue = (int) tok.nval;
		
		try
		{
			if (tok.nextToken() != ',')
			{
				System.out.println("no comma after aValue");
				return false;
			}
		
			tok.nextToken();
		} catch (IOException e)
		{
			System.out.println(e.toString());
			return false;
		}
		
		return pBOperand();
	}
	
	
	boolean pBOperand()
	{
		switch (tok.ttype)
		{
			case StreamTokenizer.TT_NUMBER:
				return pBValue();

			case '#':
				war[IP].operandB = new Hash();
				break;

			case '$':
				war[IP].operandB = new Peso();
				break;

			case '@':
				war[IP].operandB = new At();
				break;

			case '<':
				war[IP].operandB = new Less();
				break;

			case '>':
				war[IP].operandB = new Greater();
				break;

			case '*':
				war[IP].operandB = new Asterisk();
				break;

			case '{':
				war[IP].operandB = new Open();
				break;

			case '}':
				war[IP].operandB = new Close();
				break;
				
			default:
				return false;
				
		}
		
		try
		{
			tok.nextToken();
		} catch (IOException e)
		{
			System.out.println(e.toString());
			return false;
		}

		return pBValue();
	}

	boolean pBValue()
	{
		if (tok.ttype != StreamTokenizer.TT_NUMBER)
			return false;
		
		war[IP].bValue = (int) tok.nval;
		
		try 
		{
			tok.nextToken();
		} catch (IOException e)
		{
			System.out.println(e.toString());
			return false;
		}
		
		return true;
	}
	
}
