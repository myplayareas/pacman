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

package ufpi.es.jogo.pacman.elements;

import java.awt.*;

import ufpi.es.jogo.pacman.builder.ImageBuilder;
import ufpi.es.jogo.pacman.builder.TablesControl;

public class Pac
{
	// frames to wait after eaten a dot
	final int DOT_WAIT=4;

	int iDotWait;

	// current position
	public int iX;

	public int iY;
	// current direction
	public int iDir;

	// the applet this object is associated to
	Window applet;
	Graphics graphics;

	// the pac image
	public Image [][] imagePac;

	// the knowledge of the maze
	Maze maze;

	// the knowledge of the power dots
	Powerdot powerDot;

	//    cpacmove cAuto;

	//  cpac(Window a, Graphics g, cmaze m, cpowerdot p, cghost cghost[])
	public Pac(Window a, Graphics g, Maze m, Powerdot p)    {
		applet=a;
		graphics=g;
		maze=m;
		powerDot=p;

		//      cAuto=new cpacmove(this, cghost, m);

		// initialize pac and pac image
		imagePac=new Image[4][4];
		for (int i=0; i<4; i++)
			for (int j=0; j<4; j++)
			{
				imagePac[i][j]=applet.createImage(18,18);
				ImageBuilder.drawPac(imagePac[i][j],i,j);
			}	
	}

	public void start()
	{
		iX=10*16;
		iY=10*16;
		iDir=1;		// downward, illegal and won't move
		iDotWait=0;
	}

	public void draw()
	{
		maze.DrawDot(iX/16, iY/16);
		maze.DrawDot(iX/16+(iX%16>0?1:0), iY/16+(iY%16>0?1:0));

		int iImageStep=(iX%16 + iY%16)/2; 	// determine shape of PAc
		if (iImageStep<4)
			iImageStep=3-iImageStep;
		else
			iImageStep-=4;
		graphics.drawImage(imagePac[iDir][iImageStep], iX-1, iY-1, applet);
	}	

	// return 1 if eat a dot
	// return 2 if eat power dot
	public int move(int iNextDir)
	{
		int eaten=0;

		//      iNextDir=cAuto.GetNextDir();

		if (iNextDir!=-1 && iNextDir!=iDir)	// not set or same
			// change direction
		{
			if (iX%16!=0 || iY%16!=0)
			{
				// only check go back
				if (iNextDir%2==iDir%2)
					iDir=iNextDir;
			}	
			else    // need to see whether ahead block is OK
			{
				if ( mazeOK(iX/16+ TablesControl.iXDirection[iNextDir],
						iY/16+ TablesControl.iYDirection[iNextDir]) )
				{
					iDir=iNextDir;
					iNextDir=-1;
				}
			}
		}
		if (iX%16==0 && iY%16==0)
		{

			// see whether has eaten something
			switch (maze.iMaze[iY/16][iX/16])
			{
			case Maze.DOT:
				eaten=1;
				maze.iMaze[iY/16][iX/16]=Maze.BLANK;	// eat dot
				maze.iTotalDotcount--;
				iDotWait=DOT_WAIT;
				break;
			case Maze.POWER_DOT:
				eaten=2;
				powerDot.eat(iX/16, iY/16);
				maze.iMaze[iY/16][iX/16]=Maze.BLANK;
				break;
			}

			if (maze.iMaze[iY/16+ TablesControl.iYDirection[iDir]]
			               [iX/16+ TablesControl.iXDirection[iDir]]==1)
			{
				return(eaten);	// not valid move
			}
		}
		if (iDotWait==0)
		{
			iX+= TablesControl.iXDirection[iDir];
			iY+= TablesControl.iYDirection[iDir];
		}
		else	iDotWait--;
		return(eaten);
	}	

	public boolean mazeOK(int iRow, int icol)
	{
		if ( (maze.iMaze[icol][iRow] & ( Maze.WALL | Maze.DOOR)) ==0)
			return(true);
		return(false);
	}
}









