package com.test3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;


public class MyStartPanel extends JPanel implements Runnable
{
	int times=0;
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 450, 400);
		
		
		if(times%2==0)
		{
			g.setColor(Color.yellow);
			//front
			Font myFont=new Font("»ªÎÄÐÂÎº",Font.BOLD,30);
			g.setFont(myFont);
			g.drawString("stage: 1", 170, 170);		
		}
	}

	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		while(true)
		{
			//sleep
			try 
			{
				Thread.sleep(500);
			} 
			catch (Exception e) 
			{
				// TODO: handle exception
				e.printStackTrace();
			}
			
			times++;
			if(times==201)
			{
				times=0;
			}
			
			this.repaint();
		}
	}
	

}
