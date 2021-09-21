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
 
package fabaindaiz.jcorewars.loader;

import fabaindaiz.jcorewars.memory.Memory;
import fabaindaiz.jcorewars.memory.operand.*;
import fabaindaiz.jcorewars.memory.modifier.*;
import fabaindaiz.jcorewars.memory.operator.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

public class Assembler {

	protected BufferedReader input;
	protected StreamTokenizer tokenizer;
	protected int IP;
	protected int maxLength;
	protected Memory[] warrior;
	protected int start;
	
	// meta values
	protected String name;
	protected String author;
	
	public Assembler(Reader reader, int maxl) {

		input = new BufferedReader(reader);
		tokenizer = new StreamTokenizer(input);
		tokenizer.eolIsSignificant(true);
		tokenizer.lowerCaseMode(true);
		tokenizer.parseNumbers();
		tokenizer.ordinaryChar('/');
		tokenizer.ordinaryChar('.');
		tokenizer.ordinaryChar(',');
		
		IP = 0;
		start = 0;
		maxLength = maxl;
		
		warrior = new Memory[maxl];
		
		for (int i=0; i<maxl; i++)
			warrior[i] = new Memory();
	}
	
	public Memory[] getWarrior() {

		Memory[] wMem = new Memory[IP];

		if (IP >= 0) System.arraycopy(warrior, 0, wMem, 0, IP);
		
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
			while(tokenizer.nextToken() != StreamTokenizer.TT_EOF)
			{
				if(tokenizer.ttype == ';')
					pComment();
				else if (tokenizer.ttype == StreamTokenizer.TT_WORD && tokenizer.sval.equals("org"))
				{
						if(tokenizer.nextToken() != StreamTokenizer.TT_NUMBER)
							return false;

						start = (int) tokenizer.nval;
						
						tokenizer.nextToken();
						
						if (tokenizer.ttype == ';')
							pComment();
						
				} else if (tokenizer.ttype == StreamTokenizer.TT_WORD)
				{
					switch (tokenizer.sval) {
						case "mov":
							warrior[IP].operator = new MOV();
							break;
						case "add":
							warrior[IP].operator = new ADD();
							break;
						case "sub":
							warrior[IP].operator = new SUB();
							break;
						case "mul":
							warrior[IP].operator = new MUL();
							break;
						case "div":
							warrior[IP].operator = new DIV();
							break;
						case "mod":
							warrior[IP].operator = new MOD();
							break;
						case "jmz":
							warrior[IP].operator = new JMZ();
							break;
						case "jmn":
							warrior[IP].operator = new JMN();
							break;
						case "djn":
							warrior[IP].operator = new DJN();
							break;
						case "cmp":
							warrior[IP].operator = new CMP();
							break;
						case "seq":
							warrior[IP].operator = new SEQ();
							break;
						case "slt":
							warrior[IP].operator = new SLT();
							break;
						case "spl":
							warrior[IP].operator = new SPL();
							break;
						case "dat":
							warrior[IP].operator = new DAT();
							break;
						case "jmp":
							warrior[IP].operator = new JMP();
							break;
						case "sne":
							warrior[IP].operator = new SNE();
							break;
						case "nop":
							warrior[IP].operator = new NOP();
							break;
						case "ldp":
							warrior[IP].operator = new LDP();
							break;
						case "stp":
							warrior[IP].operator = new STP();
							break;
						case "end":
							if (tokenizer.nextToken() == StreamTokenizer.TT_NUMBER)
								start = (int) tokenizer.nval;

							return true;
						default:
							return false;
					}

					if (!pModifier())
						return false;
						
					if (++IP > maxLength) return false;

					if (tokenizer.ttype == ';')
						pComment();
				} 
				
				if (tokenizer.ttype != StreamTokenizer.TT_EOL)
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
			if (tokenizer.nextToken() == StreamTokenizer.TT_WORD)
			{ 
				if (tokenizer.sval.equals("name"))
				{
					name = input.readLine();
				} else if (tokenizer.sval.equals("author"))
				{
					author = input.readLine();
				} else
				{
					input.readLine();
				}
			} else
			{
				input.readLine();
			}
			
			tokenizer.ttype = StreamTokenizer.TT_EOL;
			
		} catch (IOException e)
		{
			System.out.println(e.toString());
		}
	}
	
	boolean pModifier()
	{
		try
		{
			if (tokenizer.nextToken() != '.')
				return pAOperand();
			else if (tokenizer.nextToken() == StreamTokenizer.TT_WORD)
			{
				switch (tokenizer.sval) {
					case "a":
						warrior[IP].modifier = new A();
						break;
					case "b":
						warrior[IP].modifier = new B();
						break;
					case "ab":
						warrior[IP].modifier = new AB();
						break;
					case "ba":
						warrior[IP].modifier = new BA();
						break;
					case "f":
						warrior[IP].modifier = new F();
						break;
					case "x":
						warrior[IP].modifier = new X();
						break;
					case "i":
						warrior[IP].modifier = new I();
						break;
					default:
						return false;
				}

				tokenizer.nextToken();

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
		switch (tokenizer.ttype)
		{
			case StreamTokenizer.TT_NUMBER:
				return pAValue();
				
			case '#':
				warrior[IP].operandA = new Hash();
				break;
				
			case '$':
				warrior[IP].operandA = new Peso();
				break;
				
			case '@':
				warrior[IP].operandA = new At();
				break;
				
			case '<':
				warrior[IP].operandA = new Less();
				break;
				
			case '>':
				warrior[IP].operandA = new Greater();
				break;
				
			case '*':
				warrior[IP].operandA = new Asterisk();
				break;
				
			case '{':
				warrior[IP].operandA = new Open();
				break;
				
			case '}':
				warrior[IP].operandA = new Close();
				break;
				
			default:
				return false;
				
		}
		
		try
		{
			tokenizer.nextToken();
		} catch (IOException e)
		{
			System.out.println(e.toString());
			return false;
		}

		return pAValue();

	}

	boolean pAValue()
	{
		if (tokenizer.ttype != StreamTokenizer.TT_NUMBER)
			return false;
		
		warrior[IP].aValue = (int) tokenizer.nval;
		
		try
		{
			if (tokenizer.nextToken() != ',')
			{
				System.out.println("no comma after aValue");
				return false;
			}
		
			tokenizer.nextToken();
		} catch (IOException e)
		{
			System.out.println(e.toString());
			return false;
		}
		
		return pBOperand();
	}
	
	
	boolean pBOperand()
	{
		switch (tokenizer.ttype)
		{
			case StreamTokenizer.TT_NUMBER:
				return pBValue();

			case '#':
				warrior[IP].operandB = new Hash();
				break;

			case '$':
				warrior[IP].operandB = new Peso();
				break;

			case '@':
				warrior[IP].operandB = new At();
				break;

			case '<':
				warrior[IP].operandB = new Less();
				break;

			case '>':
				warrior[IP].operandB = new Greater();
				break;

			case '*':
				warrior[IP].operandB = new Asterisk();
				break;

			case '{':
				warrior[IP].operandB = new Open();
				break;

			case '}':
				warrior[IP].operandB = new Close();
				break;
				
			default:
				return false;
				
		}
		
		try
		{
			tokenizer.nextToken();
		} catch (IOException e)
		{
			System.out.println(e.toString());
			return false;
		}

		return pBValue();
	}

	boolean pBValue()
	{
		if (tokenizer.ttype != StreamTokenizer.TT_NUMBER)
			return false;
		
		warrior[IP].bValue = (int) tokenizer.nval;
		
		try 
		{
			tokenizer.nextToken();
		} catch (IOException e)
		{
			System.out.println(e.toString());
			return false;
		}
		
		return true;
	}
	
}
