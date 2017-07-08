package com.test3;

import java.util.*;

class Tank
{
	//represent the location of the tank
	public int x=0;
	public int y=0;
	//1 represent up,2 represent down,3 represent left,4 represent right 
	public int z=0;
	//the tank speed
	boolean isLive=true;
	public int speed=1;
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	
	
	
	public Tank(int x,int y,int z,int speed)
	{
		this.x=x;
		this.y=y;
		this.z=z;
		this.speed=speed;
	}
	
	
	
	
}

//hero tank
class Hero extends Tank
{
	//bullets
	Vector<Shot> ss=new Vector<Shot>();
	Shot s=null;
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	public Hero(int x,int y,int z,int speed)
	{
		super(x,y,z,speed);	
		
	}
	
	public void setEts(Vector<EnemyTank> vv)
	{
		this.ets=vv;		
	}
	
	public void shotEnemy()
	{
		
		switch(this.z)
		{
		case 0:
			s=new Shot(x+10,y,0);			
			ss.add(s);
			break;
		case 1:
			s=new Shot(x+10,y+30,1);
			ss.add(s);
			break;
		case 2:
			s=new Shot(x,y+10,2);
			ss.add(s);
			break;
		case 3:
			s=new Shot(x+30,y+10,3);
			ss.add(s);
			break;	
		
		}
		Thread t=new Thread(s);
		t.start();
				
	}
	
	public boolean isTouchOtherEnemy()
	{
		boolean b=false;
		
		switch(this.z)
		{
		case 0:
			for(int i=0;i<ets.size();i++)
			{
				EnemyTank et=ets.get(i);				
				if(et.z==0||et.z==1)
				{
					if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
					{
						return true;
					}
					if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
					{
						return true;
					}
				}
				if(et.z==2||et.z==3)
				{
					if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
					{
						return true;
					}
					if(this.x+20>=et.x&&this.x+20<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
					{
						return true;
					}
				}
			}
					
			break;
		case 1:
			for(int i=0;i<ets.size();i++)
			{
				EnemyTank et=ets.get(i);
				if(et.z==0||et.z==1)
				{
					if(this.x>=et.x&&this.x<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
					{
						return true;
					}
					if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
					{
						return true;
					}
				}
				if(et.z==2||et.z==3)
				{
					if(this.x>=et.x&&this.x<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
					{
						return true;
					}
					if(this.x+20>=et.x&&this.x+20<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
					{
						return true;
					}
				}
							
			}
			break;
		case 2:
			for(int i=0;i<ets.size();i++)
			{
				EnemyTank et=ets.get(i);
					if(et.z==0||et.z==1)
					{
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					if(et.z==2||et.z==3)
					{
						if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x>=et.x&&this.x<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
						{
							return true;
						}
					}
				
			}
			break;
		case 3:
			for(int i=0;i<ets.size();i++)
			{
				EnemyTank et=ets.get(i);
					if(et.z==0||et.z==1)
					{
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					if(et.z==2||et.z==3)
					{
						if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
						{
							return true;
						}
					}
				
			}
			break;
		}
		
		return b;
	}
	
	public void MoveUp()
	{
		if(y>0&&!this.isTouchOtherEnemy())
		{
		y-=speed;
		}
	}
	public void MoveDown()
	{
		if(y<370&&!this.isTouchOtherEnemy())
		{
		y+=speed;
		}
	}
	public void MoveLeft()
	{
		if(x>0&&!this.isTouchOtherEnemy())
		{
		x-=speed;
		}
	}
	public void MoveRight()
	{
		if(x<420&&!this.isTouchOtherEnemy())
		{
		x+=speed;
		}
	}
}

//enemy tank
class EnemyTank extends Tank implements Runnable
{
	//bullet
	Vector<Shot> ss=new Vector<Shot>();
	Hero hero=null;
	
	int times=0;
	
	//vector that have all enemy tanks
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	
	
	public EnemyTank(int x,int y,int z,int speed)
	{
		super(x,y,z,speed);
	}
	
	public void setEtsandHero(Vector<EnemyTank> vv,Hero hero)
	{
		this.ets=vv;
		this.hero=hero;
	}
	
	//decide if touch other tanks
	public boolean isTouchOtherEnemy()
	{
		boolean b=false;
		
		switch(this.z)
		{
		case 0:			
			for(int i=0;i<ets.size();i++)
			{
				EnemyTank et=ets.get(i);				
				if(et!=this)
				{
					if(et.z==0||et.z==1)
					{
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
					}
					if(et.z==2||et.z==3)
					{
						if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x+20>=et.x&&this.x+20<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			if(hero.z==0||hero.z==1)
			{
				if(this.x>=hero.x&&this.x<=hero.x+20&&this.y>=hero.y&&this.y<=hero.y+30)
				{
					return true;
				}
				if(this.x+20>=hero.x&&this.x+20<=hero.x+20&&this.y>=hero.y&&this.y<=hero.y+30)
				{
					return true;
				}
			}
			if(hero.z==2||hero.z==3)
			{
				if(this.x>=hero.x&&this.x<=hero.x+30&&this.y>=hero.y&&this.y<=hero.y+20)
				{
					return true;
				}
				if(this.x+20>=hero.x&&this.x+20<=hero.x+30&&this.y>=hero.y&&this.y<=hero.y+20)
				{
					return true;
				}
			}
			break;
		case 1:			
			for(int i=0;i<ets.size();i++)
			{
				EnemyTank et=ets.get(i);				
				if(et!=this)
				{					
					if(et.z==0||et.z==1)
					{
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
						if(this.x+20>=et.x&&this.x+20<=et.x+20&&this.y+30>=et.y&&this.y+30<=et.y+30)
						{
							return true;
						}
					}
					if(et.z==2||et.z==3)
					{
						if(this.x>=et.x&&this.x<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
						{
							return true;
						}
						if(this.x+20>=et.x&&this.x+20<=et.x+30&&this.y+30>=et.y&&this.y+30<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			if(hero.z==0||hero.z==1)
			{
				if(this.x>=hero.x&&this.x<=hero.x+20&&this.y+30>=hero.y&&this.y+30<=hero.y+30)
				{
					return true;
				}
				if(this.x+20>=hero.x&&this.x+20<=hero.x+20&&this.y+30>=hero.y&&this.y+30<=hero.y+30)
				{
					return true;
				}
			}
			if(hero.z==2||hero.z==3)
			{
				if(this.x>=hero.x&&this.x<=hero.x+30&&this.y+30>=hero.y&&this.y+30<=hero.y+20)
				{
					return true;
				}
				if(this.x+20>=hero.x&&this.x+20<=hero.x+30&&this.y+30>=hero.y&&this.y+30<=hero.y+20)
				{
					return true;
				}
			}
			
			break;
		case 2:			
			for(int i=0;i<ets.size();i++)
			{
				EnemyTank et=ets.get(i);				
				if(et!=this)
				{					
					if(et.z==0||et.z==1)
					{
						if(this.x>=et.x&&this.x<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						if(this.x>=et.x&&this.x<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					if(et.z==2||et.z==3)
					{
						if(this.x>=et.x&&this.x<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x>=et.x&&this.x<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			if(hero.z==0||hero.z==1)
			{
				if(this.x>=hero.x&&this.x<=hero.x+20&&this.y>=hero.y&&this.y<=hero.y+30)
				{
					return true;
				}
				if(this.x>=hero.x&&this.x<=hero.x+20&&this.y+20>=hero.y&&this.y+20<=hero.y+30)
				{
					return true;
				}
			}
			if(hero.z==2||hero.z==3)
			{
				if(this.x>=hero.x&&this.x<=hero.x+30&&this.y>=hero.y&&this.y<=hero.y+20)
				{
					return true;
				}
				if(this.x>=hero.x&&this.x<=hero.x+30&&this.y+20>=hero.y&&this.y+20<=hero.y+20)
				{
					return true;
				}
			}
			
			break;
		case 3:			
			for(int i=0;i<ets.size();i++)
			{
				EnemyTank et=ets.get(i);				
				if(et!=this)
				{					
					if(et.z==0||et.z==1)
					{
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y>=et.y&&this.y<=et.y+30)
						{
							return true;
						}
						if(this.x+30>=et.x&&this.x+30<=et.x+20&&this.y+20>=et.y&&this.y+20<=et.y+30)
						{
							return true;
						}
					}
					if(et.z==2||et.z==3)
					{
						if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y>=et.y&&this.y<=et.y+20)
						{
							return true;
						}
						if(this.x+30>=et.x&&this.x+30<=et.x+30&&this.y+20>=et.y&&this.y+20<=et.y+20)
						{
							return true;
						}
					}
				}
			}
			if(hero.z==0||hero.z==1)
			{
				if(this.x+30>=hero.x&&this.x+30<=hero.x+20&&this.y>=hero.y&&this.y<=hero.y+30)
				{
					return true;
				}
				if(this.x+30>=hero.x&&this.x+30<=hero.x+20&&this.y+20>=hero.y&&this.y+20<=hero.y+30)
				{
					return true;
				}
			}
			if(hero.z==2||hero.z==3)
			{
				if(this.x+30>=hero.x&&this.x+30<=hero.x+30&&this.y>=hero.y&&this.y<=hero.y+20)
				{
					return true;
				}
				if(this.x+30>=hero.x&&this.x+30<=hero.x+30&&this.y+20>=hero.y&&this.y+20<=hero.y+20)
				{
					return true;
				}
			}
			
			break;
		}
		return b;
	}

	@Override
	public void run() 
	{
		while(true)
		{			
			switch(this.z)
			{
			case 0:
				for(int i=0;i<30;i++)
				{
					if(y>0&&!this.isTouchOtherEnemy())
					{
						y-=speed;
					}
					try
					{
						Thread.sleep(50);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}					
				}
				break;									
			case 1:
				for(int i=0;i<30;i++)
				{
					if(y<370&&!this.isTouchOtherEnemy())
					{
					y+=speed;
					}
					try
					{
						Thread.sleep(50);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					
				}
				break;		
			case 2:
				for(int i=0;i<30;i++)
				{
					if(x>0&&!this.isTouchOtherEnemy())
					{
					x-=speed;
					}
					try
					{
						Thread.sleep(50);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					
				}
				break;			
			case 3:
				for(int i=0;i<30;i++)
				{
					if(x<420&&!this.isTouchOtherEnemy())
					{
					x+=speed;
					}
					try
					{
						Thread.sleep(50);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					
				}
				break;			
			}
			
			this.setX(x);
			this.setY(y);
			
			this.times++;
			
			if(times%2==0)
			{
				if(this.isLive)
				{
					if(this.ss.size()<2)
					{
						Shot s=null;
						switch(z)
						{
						case 0:
							s=new Shot(x+10,y,0);			
							this.ss.add(s);
							break;
						case 1:
							s=new Shot(x+10,y+30,1);
							this.ss.add(s);
							break;
						case 2:
							s=new Shot(x,y+10,2);
							this.ss.add(s);
							break;
						case 3:
							s=new Shot(x+30,y+10,3);
							this.ss.add(s);
							break;	
						}
						Thread t=new Thread(s);
						t.start();
					}
				}
			}
			
			this.z=(int)(Math.random()*4); 
			
			if(this.isLive==false)
			{
				//if tank died, exit the thread
				break;
			}
			
			
		}
	}
}
