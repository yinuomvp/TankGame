package com.test3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Over extends JPanel 
{
	
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 450, 400);
		g.setColor(Color.yellow);
		//the front
		Font myFont=new Font("»ªÎÄÐÂÎº",Font.BOLD,30);
		g.setFont(myFont);
		g.drawString("game over", 160, 170);		
		
	}
	
	
	

	
}
