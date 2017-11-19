/**
 * Copyright (C) 1997-2010 Junyang Gu <mikejyg@gmail.com>
 * 
 * This file is part of javaiPacman.
 *
 * javaiPacman is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * javaiPacman is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with javaiPacman.  If not, see <http://www.gnu.org/licenses/>.
 */

package ufpi.es.jogo.pacman.screens;

import java.awt.*;
import java.awt.event.*;

// to display about information
public class About extends Window
implements MouseListener
{
	private static final long serialVersionUID = -6444989674095739037L;

	final String[] about = {
			"JavaiPacman with sound effects - version 1.1",
			"",
			"  - Copyright 1997-2010 Junyang Gu <mikejyg@gmail.com>",
			"",
			"An intelligent pacman game implmented in Java",
			"This is javaiPacman, a intelligent pacman game written in Java.", 
			"The original goal of the project is to demonstrate how", 
			" randomness contributes to the intelligent movements", 
			" of the ghosts.", 
			"I think the movements of the ghosts makes this game ", 
			"interesting.",
			"Note the one rule of ghosts' movement - they are not ", 
			"allowed to reverse course.", 
			"",
			"Controls:",
			"",
			"1. Use UP arrow key to start.",
			"2. Use arrows to move.",
			"3. Sorry, no end of game key provided, just run into a ghost.",
			"",
			"Scoring:",
			"", 
			"1. 10 points per dot. 200 points first ghost, 400 for next", 
			"and so on, finally, 1600 points.",
			"2. You get a bonus pac every 10000 points. ( Hope this will help )"	 
	};

	public About(Frame parent)
	{
		super(parent);

		setSize(420, 490);
		setLocation(100, 100);
		show();

		addMouseListener(this);
	}

	public void paint(Graphics g)
	{
		g.setColor(Color.black);
		g.setFont(new Font("Helvetica", Font.BOLD, 12));
		for (int i=0; i<about.length; i++)
			g.drawString(about[i], 6, (i+1)*18);
	}

	public void mouseClicked(MouseEvent e)
	{
		dispose();
	}

	public void mousePressed(MouseEvent e) 
	{}

	public void mouseReleased(MouseEvent e) 
	{}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}



