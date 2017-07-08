package com.test3;

import java.awt.*;
import javax.swing.*;


public class Explosion 
{
	int x;
	int y;
	int life=12;
	boolean isLive=true;
	
	public Explosion(int x,int y)
	{
		this.x=x;
		this.y=y;
		
	}
	
	public void lifedown()
	{
		if(life>0)
		{
			life--;
		}
		else
		{
			this.isLive=false;
		}
	}
	

}
