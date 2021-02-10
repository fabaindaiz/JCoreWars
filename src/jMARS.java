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
  jMARS is a corewars interpreter in which programs (warriors)
  battle in the memory of a virtual machine (the MARS) and try to disable
  the other program.
 */

import java.awt.*;
import java.util.Vector;

import marsVM.*;

import javax.swing.*;

public class jMARS
{
	
	/**
	 * Starting function for the application. It sets up a frame and adds the applet to it.
	 * @param args - array of command line arguments
	 */
	public static void main(String[] args)
	{
		Vector<String> wArgs = new Vector<>();
		int numWarriors = 0;

		if (args.length == 0)
		{
			System.out.println("usage: jMARS [options] warrior1.red [warrior2.red ...]");
			return;
		}

		JFrame frame = new JFrame("jMARS");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(520, 600));

		MarsCore app = new MarsCore();
		frame.add(app);
		frame.setVisible(true);

		app.application_init();
		app.application_display();

		for (int i=0; i<args.length; i++)
		{
			if (args[i].charAt(0) == '-')
			{
				switch (args[i]) {
					case "-r":
						app.setRounds(Integer.parseInt(args[++i]));
						break;
					case "-s":
						app.setCoreSize(Integer.parseInt(args[++i]));
						break;
					case "-c":
						app.setCycles(Integer.parseInt(args[++i]));
						break;
					case "-p":
						app.setMaxProc(Integer.parseInt(args[++i]));
						break;
					case "-l":
						app.setMaxWarriorLength(Integer.parseInt(args[++i]));
						break;
					case "-d":
						app.setMinWarriorDistance(Integer.parseInt(args[++i]));
						break;
					case "-S":
						app.setPSpaceSize(Integer.parseInt(args[++i]));
						break;
				}
			} else
			{
				numWarriors++;
				wArgs.addElement(args[i]);
			}
		}

		if (numWarriors == 0) {
			System.out.println("ERROR: no warrior files specified");
			return;
		}

		app.setWarriors(wArgs);
		app.application_start();

	}

}

