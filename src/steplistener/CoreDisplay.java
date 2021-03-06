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

import marsVM.FrontEndManager;
import marsVM.WarriorObj;

import java.awt.*;

/**
 * A pMARS style core display
 */
public class CoreDisplay extends javax.swing.JPanel implements StepListener {

	/* core background color */
	protected Color background;

	/* size of core */
	protected int coreSize;

	protected int width;
	protected int height;
	protected int dimW;
	protected int dimH;

	protected Image offScreen;
	protected Graphics buffer;

	/**
	 * Create a new core display for a specified core size and width.
	 *
	 * @param man   - Object managing the front end components.
	 * @param coreS - Size of core to be displayed.
	 */
	public CoreDisplay(FrontEndManager man, Container con, int coreS) {
		coreSize = coreS;
		width = 420;
		height = coreSize / 16;
		dimW = width / 80;
		dimH = height / (coreSize / 80);

		background = Color.black;

		man.registerStepListener(this);
		System.out.println("Constructor end size=" + getSize());

		con.add(this, BorderLayout.CENTER);

		validate();
	}

	/**
	 * Update display with info from a round.
	 *
	 * @param report - info from round
	 */
	@Override
	public void stepProcess(StepReport report) {
		reportProcess(report, report.warrior.getMyColor());
	}

	@Override
	public void endProcess(StepReport report) {
		reportProcess(report, report.warrior.getColors());
	}

	private void reportProcess(StepReport report, Color[] colors)
	{
		int i;
		int x,y;

		if (offScreen == null)
			return;

		buffer.setColor(colors[0]);

		int[] addr = report.addrRead();
		for (i=0; i < addr.length; i++)
		{
			x = addr[i] % 80;
			y = addr[i] / 80;

			buffer.fillRect(x *dimW, y * dimH, dimW, dimH);
		}

		buffer.setColor(colors[1]);

		addr = report.addrWrite();
		for (i=0; i < addr.length; i++)
		{
			x = addr[i] % 80;
			y = addr[i] / 80;

			buffer.fillRect(x *dimW, y * dimH, dimW, dimH);
		}

		addr = report.addrDec();
		for (i=0; i < addr.length; i++)
		{
			x = addr[i] % 80;
			y = addr[i] / 80;

			buffer.fillRect(x *dimW, y * dimH, dimW, dimH);
		}

		addr = report.addrInc();
		for (i=0; i < addr.length; i++)
		{
			x = addr[i] % 80;
			y = addr[i] / 80;

			buffer.fillRect(x *dimW, y * dimH, dimW, dimH);
		}

		buffer.setColor(colors[2]);

		if ((i = report.addrExec()) != -1)
		{
			x = i % 80;
			y = i / 80;

			if (report.pDeath()) buffer.setColor(report.warrior().getDColor());
			buffer.fillRect(x *dimW, y * dimH, dimW, dimH);
		}

		paint(getGraphics());
	}

	public void paintWarrior(WarriorObj warrior, int startPosition)
	{
		buffer.setColor(warrior.getNColor());

		for (int i = startPosition; i < warrior.getSize(); i++) {
			buffer.fillRect((i % 80) *dimW, (i / 80) * dimH, dimW, dimH);
		}

		paint(getGraphics());
	}
	
	/**
	 * clear the display
	 */
	public void clear()
	{
		if (offScreen == null)
			return;
		
		buffer.setColor(background);
		buffer.fillRect(0, 0, width, height);
	}

	/**
	 * paint the display on the screen
	 * @param screen - Graphics context to paint to
	 */
	public void paint(Graphics screen)
	{
		if (offScreen == null)
		{
			offScreen = createImage(width, height);
			buffer = offScreen.getGraphics();
			buffer.setColor(background);
			buffer.fillRect(0, 0, width, height);
		}

		screen.drawImage(offScreen, 0, 0, this);
	}
	
	/**
	 * Get the maximum size for the display
	 * @return java.awt.Dimension - maximum size
	 */
	public Dimension getMaximumSize()
	{
		return new Dimension(width, height);
	}
	
	/**
	 * Get the preffered size for the display
	 * @return java.awt.Dimension - preferred size
	 */
	public Dimension getPreferredSize()
	{
		return new Dimension(width, height);
	}
	
	/**
	 * Get the minimum size for the display
	 * @return java.awt.Dimension - minimum size
	 */
	public Dimension getMinimumSize()
	{
		return new Dimension(width, height);
	}
	
	/**
	 * Get X alignment the display wants in the layout. Asks for a center alignment.
	 * @return float - X alignment (0.5)
	 */
	public float getAlignmentX()
	{
		return 0.5F;
	}
	
	/**
	 * Get Y alignment the display wants in the layout. Asks for a center alignment.
	 * @return float - Y alignment (0.5)
	 */
	public float getAlignmentY()
	{
		return 0.5F;
	}
}
