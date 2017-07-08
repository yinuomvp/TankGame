package com.test3;

public class Shot implements Runnable
{
	int x;
	int y;
	int z;
	int speed=2;
	boolean shotisLive=true;
	public Shot(int x,int y,int z)
	{
		this.x=x;
		this.y=y;
		this.z=z;
	}
	@Override
	public void run() 
	{
		while(true)
		{
			
			try
			{
				Thread.sleep(50);
			}
			catch(Exception e)
			{
				
			}
			
			switch(this.z)
			{
			case 0:
				//up
				y-=speed;
				break;
			case 1:
				//down
				y+=speed;
				break;
			case 2:
				//left
				x-=speed;
				break;
			case 3:
				//right
				x+=speed;
				break;
			}
			
			//when did the bullet die
			//if the bullet reach the bound
			if(x<0||x>450||y<0||y>400)
			{
				this.shotisLive=false;
				break;
			}
			
			
		}
		
	}
}
