package com.test3;

import java.awt.Color;
import java.awt.Graphics;

class DrawTanks
{
	//drow your own tank
		public void drawTank
		(int x,int y,Graphics g,int direct,int type)
		{
			//decide the type of tank
			switch(type)
			{
			case 0:
				g.setColor(Color.cyan);
				break;
			case 1:
				g.setColor(Color.red);
				break;
			}
			
			//decide the direction
			switch(direct)
			{
			//up
			case 0:				
				g.fill3DRect(x, y, 5, 30, false);				
				g.fill3DRect(x+15, y, 5, 30, false);				
				g.fill3DRect
				(x+5, y+5, 10, 20, false);				
				g.fillOval(x+5, y+10, 9, 9);				
				g.drawLine(x+9, y+11, x+9, y+1);
				break;
			case 1:
				g.fill3DRect(x, y, 5, 30, false);
				g.fill3DRect(x+15, y, 5, 30, false);
				g.fill3DRect
				(x+5, y+5, 10, 20, false);
				g.fillOval(x+5, y+10, 9, 9);
				g.drawLine(x+9, y+18, x+9, y+28);
				break;
			case 2:				
				g.fill3DRect(x, y, 30, 5, false);
				g.fill3DRect(x, y+15, 30, 5, false);
				g.fill3DRect(x+5, y+5, 20, 10, false);
				g.fillOval(x+10, y+5, 9, 9);
				g.drawLine(x+10,y+9,x,y+9);
				break;
			case 3:				
				g.fill3DRect(x, y, 30, 5, false);
				g.fill3DRect(x, y+15, 30, 5, false);
				g.fill3DRect(x+5, y+5, 20, 10, false);
				g.fillOval(x+10, y+5, 9, 9);
				g.drawLine(x+19,y+9,x+29,y+9);
				break;
			}
		}
}

