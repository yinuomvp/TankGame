/**
 * function: TankGame
 */
package com.test3;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.*;
import java.io.*;

public class MyTankGame2 extends JFrame implements ActionListener,Runnable
{

	/**
	 * @param args
	 */
	TankMove tankmove=null;
	int times=0;
	
	//create a new start panel
	MyStartPanel msp=null;
	
	//create the menu vars
	JMenuBar jmb=null;
	JMenu jm1=null;
	JMenuItem jmi1=null;
	JMenuItem jmi2=null;
		
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		MyTankGame2 mtg= new MyTankGame2();
		
	
	}
	
	
	
	public MyTankGame2()
	{
			
		//create the menu
		
	    jmb=new JMenuBar();
	    jm1=new JMenu("Game(G)");	    
	    jm1.setMnemonic('G');
	    jmi1 =new JMenuItem("Start New Game(N)");
	    jmi2 =new JMenuItem("Exit(E)");
	    jmi2.setMnemonic('E');
	    
	    jmi1.addActionListener(this);
	    jmi1.setActionCommand("newgame");
	    jmi2.addActionListener(this);
	    jmi2.setActionCommand("exit");
	    
	    jm1.add(jmi1);
	    jm1.add(jmi2);
	    jmb.add(jm1);
	    
		
		msp=new MyStartPanel();
		
		this.setJMenuBar(jmb);
		
		
		this.add(msp);
		Thread t=new Thread(msp);
		t.start();
		this.setSize(700,650);
		this.setVisible(true);
		this.setLocation(500, 150);
		this.setDefaultCloseOperation
		(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		if(times==0&&e.getActionCommand().equals("newgame"))
		{
			//Delete the old panel
			this.remove(msp);
			tankmove = new TankMove();
			this.add(tankmove.mp);
			this.addKeyListener(tankmove);
			//Show and refresh JFrame
			this.setVisible(true);
			times++;
		}
		if(times>0&&e.getActionCommand().equals("newgame"))
		{
			//Delete the old panel
			this.remove(tankmove.mp);
			Recorder.setEnNum(20);
			Recorder.setAllEnNum(0);
			tankmove = new TankMove();
			this.add(tankmove.mp);
			this.addKeyListener(tankmove);
			//Show and refresh JFrame
			this.setVisible(true);
		}
		else if(e.getActionCommand().equals("exit"))
		{					
			System.exit(0);
		}
		else if(e.getActionCommand().equals("save"))
		{
			//save the number of the destroyed enemy tanks
			Recorder.keepRecord();
		}
		else if(e.getActionCommand().equals("load"))
		{
			Recorder.getRecord();
			this.remove(tankmove.mp);
			tankmove = new TankMove();
			this.add(tankmove.mp);
			this.addKeyListener(tankmove);
			//Show and refresh JFrame
			this.setVisible(true);
		}
	}



	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		while(true)
		{
			try 
			{
				Thread.sleep(100);
			} 
			catch (Exception e) 
			{
				// TODO: handle exception
			}
		
			if(this.tankmove.mp.over==1)
			{
				Over o=new Over();
				this.remove(this.tankmove.mp);
				this.add(o);
				this.setVisible(true);
		
			}
		}
	}

	
	

}


//My panel
class MyPanel extends JPanel implements Runnable
{
		
	//define my hero tank
	int over=0;
	Hero hero=null;	
	Image image1=null;
	Image image2=null;
	Image image3=null;
	Image image4=null;
	
	//define enemy tanks
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	
	//define bullets
	Vector<Explosion> exv=new Vector<Explosion>();
	
	int enSize=7;
	
			

	public MyPanel()
	{
		
		
		hero=new Hero(200,300,0,2);
			
		
		//initialize all tanks 
		for(int i=0;i<enSize;i++)
		{
			//create an enemy tank
			EnemyTank et=new EnemyTank((i+1)*50,0,1,1);
			et.setZ(1);
			//add
			ets.add(et);
			
			et.setEtsandHero(ets,hero);
			hero.setEts(ets);
			
			Thread t=new Thread(et);
			t.start();
			
			Shot s=new Shot(et.x+10,et.y+30,1);
			et.ss.add(s);
			Thread t2=new Thread(s);
			t2.start();
			
			
		}
		
		try 
		{
			image1=ImageIO.read(new File("bomb_1.gif"));
			image2=ImageIO.read(new File("bomb_2.gif"));
			image3=ImageIO.read(new File("bomb_3.gif"));
			image4=ImageIO.read(new File("bomb_4.gif"));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		//image1=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
		//image2=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
		//image3=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
		//image4=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_4.gif"));
	}
	
	//show the information
	public void showinfo(Graphics g)
	{
		//show the detail information
		Recorder recorder=new Recorder();
		DrawTanks dt=new DrawTanks();
		g.setColor(Color.black);
		Font f=new Font("宋体",Font.BOLD,20);
		g.setFont(f);
		g.drawString("still have:", 50, 420);
		dt.drawTank(50,450,g,0,1);
		g.setColor(Color.black);
		g.drawString(recorder.getEnNum()+"", 80, 470);
		dt.drawTank(120, 450, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(recorder.getMylife()+"", 150, 470);
		
		//print the score
		g.setColor(Color.black);
		g.setFont(f);
		g.drawString("Your score:", 460, 30);
		
		dt.drawTank(460, 60, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Recorder.getAllEnNum()+"", 490, 80);
		
	}
	
	
		
	
	//paint	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, 450, 400);
				
		DrawTanks dt=new DrawTanks();
		
		
		//show the information
		this.showinfo(g);		
		
		
		//draw your hero tank
		if(hero.isLive)
		{
		dt.drawTank
		(hero.getX(), hero.getY(), g, hero.getZ(), 0);
		}
		
		//draw the bullet
		for(int i=0;i<hero.ss.size();i++)
		{
			Shot myshot=hero.ss.get(i);
			if(myshot!=null&&myshot.shotisLive==true)
			{
			g.draw3DRect(myshot.x, myshot.y, 1, 1, false);
			}
			if(myshot.shotisLive==false)
			{
				hero.ss.remove(myshot);
			}
		}
		
		//draw the explosion
		for(int i=0;i<exv.size();i++)
		{
			//get bullets
			Explosion ex=exv.get(i);
			
			if(ex.life>9)
			{	
				g.drawImage(image1, ex.x, ex.y, 30, 30, this);
			}
			else if(ex.life>6)
			{	
				g.drawImage(image2, ex.x, ex.y, 30, 30, this);
			}
			else if(ex.life>3)
			{	
				g.drawImage(image3, ex.x, ex.y, 30, 30, this);
			}
			else
			{	
				g.drawImage(image4, ex.x, ex.y, 30, 30, this);
			}
			
			ex.lifedown();
			
			if(ex.life==0)
			{
				exv.remove(ex);
			}
			
		}
		
		
		//draw enemy tanks
		for(int i=0;i<ets.size();i++)
		{
			EnemyTank et_temp=ets.get(i);
			if(et_temp.isLive)
			{
				dt.drawTank
				(et_temp.getX(), et_temp.getY(), g, et_temp.getZ(), 1);
				for(int j=0;j<et_temp.ss.size();j++)
				{	
					Shot enemyshot=et_temp.ss.get(j);
					if(enemyshot.shotisLive)
					{
						g.draw3DRect(enemyshot.x, enemyshot.y, 1, 1, false);
					}
					else
					{
						et_temp.ss.remove(enemyshot);
					}
				}
			
			}
		}
	}
	
	
	
	//decide if our tank is shot
    public void hithero()
    {
    	for(int i=0;i<this.ets.size();i++)
    	{
    		//get enemy tanks
    		EnemyTank et=ets.get(i);
    		
    		for(int j=0;j<et.ss.size();j++)
    		{
    			Shot enemyshot=et.ss.get(j);
    			if(hero.isLive)
    			{
    				this.hitMyTank(enemyshot, hero, exv);
    				
    			}    			
    		}
    	}
    }
    
    //decide if enemy tank is shot
    public void hitEnemy()
    {

		for(int i=0;i<hero.ss.size();i++)
		{
			Shot myshot=hero.ss.get(i);
			if(myshot.shotisLive)
			{
				//get enemy tanks
				for(int j=0;j<ets.size();j++)
				{
					
					if(ets.get(j).isLive)
					{
						if(this.hitEnemyTank(myshot, ets.get(j),exv))
						{								
			    			hero.ss.get(i).shotisLive=false;
							Recorder.reduceEnNum();
			    			Recorder.killEnNum();
						}
					}
				}
			}
			
		}
    }
    
	
	 public boolean hitEnemyTank(Shot s,Tank et,Vector<Explosion> exv)
	    {
	    	//decide the direction of tanks
	    	boolean a=false;
	    	if(s.shotisLive==true){
	    	switch(et.z)
	    	{
	    	case 0:
	    	case 1:
	    		if(s.x>=et.x&&s.x<=et.x+20&&s.y>=et.y&&s.y<=et.y+30)
	    		{
	    			//be shot
	    			//bullet is dead
	    			s.shotisLive=false;    			
	    			//enemy tank is dead
	    			et.isLive=false;
	    			a=true;
	    			//create a bullet
	    			Explosion ex=new Explosion(et.x,et.y);
	    			exv.add(ex);
	    			this.ets.remove(et);
	    			
	    		}
	    		break;
	    	case 2:
	    	case 3:
	    		if(s.x>=et.x&&s.x<=et.x+30&&s.y>=et.y&&s.y<=et.y+20)
	    		{
	    			//be shot
	    			//bullet is dead
	    			s.shotisLive=false;
	    			//enemy tank is dead
	    			et.isLive=false;
	    			a=true;
	    			//create a bullet
	    			Explosion ex=new Explosion(et.x,et.y);
	    			exv.add(ex);
	    			this.ets.remove(et);
	    			
	    		}
	    		break;
	    	}
	    	}
	    	return a;
	    }
	 
	 public void hitMyTank(Shot s,Tank et,Vector<Explosion> exv)
	    {
		 	//decide the direction of tanks
	    	switch(et.z)
	    	{
	    	case 0:
	    	case 1:
	    		if(s.x>=et.x&&s.x<=et.x+20&&s.y>=et.y&&s.y<=et.y+30)
	    		{
	    			//be shot
	    			//bullet is dead
	    			s.shotisLive=false;
	    			//enemy tank is dead
	    			et.isLive=false;    			
	    			//create a bullet
	    			Explosion ex=new Explosion(et.x,et.y);
	    			exv.add(ex);
	    			
	    		}
	    		break;
	    	case 2:
	    	case 3:
	    		if(s.x>=et.x&&s.x<=et.x+30&&s.y>=et.y&&s.y<=et.y+20)
	    		{
	    			//be shot
	    			//bullet is dead
	    			s.shotisLive=false;
	    			//enemy tank is dead
	    			et.isLive=false;    			
	    			//create a bullet
	    			Explosion ex=new Explosion(et.x,et.y);
	    			exv.add(ex);
	    			
	    		}
	    		break;
	    	}
	    }


	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		//every 100 ms to redraw
		while(true)
		{
			try 
			{
				Thread.sleep(10);
			} 
			catch (Exception e) 
			{
				// TODO: handle exception
			}
			
			//decide if enemy tank is shot
			
			this.hitEnemy();
			
			//decide if our hero tank is shot
			this.hithero();
			
			if((Recorder.getEnNum()-ets.size())>0)
			{
			NewEnemy ne=new NewEnemy(ets);
			ne.creat();
			this.ets=ne.ets;
			}
			if((Recorder.getMylife()>0&&hero.isLive==false))
			{
				NewHero nh=new NewHero(ets);
				nh.creat();
				
			}
			if(Recorder.getMylife()==0&&hero.isLive==false)
			{
				over=1;
			}
			if(Recorder.getEnNum()==0&&ets.size()==0)
			{
				over=1;
			}
			
			this.repaint();
		}
	}

		
}

//class Tank Move
class TankMove implements KeyListener
{
	
	MyPanel mp=null;
		
	public TankMove()
	{
		mp= new MyPanel();
		Thread t=new Thread(mp);
		t.start();
	}
		
	
	
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public void keyPressed(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{			
			mp.hero.MoveDown();			
			mp.hero.setZ(1);
			
		}
		else if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			mp.hero.MoveUp();
			mp.hero.setZ(0);
			
		}
		else if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{			
			mp.hero.MoveLeft();			
			mp.hero.setZ(2);
			
		}
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{			
			mp.hero.MoveRight();			
			mp.hero.setZ(3);
			
		}
		if(e.getKeyCode()==KeyEvent.VK_J)
		{
			//open fire
			if(this.mp.hero.ss.size()<5)
			{
			this.mp.hero.shotEnemy();
			}
		}
		
		this.mp.repaint();
	}
	
	public void keyReleased(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	
	

	
}

class NewEnemy
{
	
	Vector<EnemyTank> ets=null;
	int times=0;
	
	public NewEnemy(Vector<EnemyTank> ets)	
	{
		this.ets=ets;
	}
	
	public void creat()
	{
		if(ets.size()<5){
		for(int i=0;i<ets.size();i++)
		{
			if(ets.get(i).x>=30&&ets.get(i).y>=30)
			{
				times++;
			}
		}
		if(times==this.ets.size())
		{
			EnemyTank et=new EnemyTank(0,0,1,1);
			this.ets.add(et);
		}
		}
	}
}

class NewHero
{
	
	Vector<EnemyTank> ets=null;
	int times=0;
	
	public NewHero(Vector<EnemyTank> ets)	
	{
		this.ets=ets;
	}
	
	public void creat()
	{
		
		for(int i=0;i<ets.size();i++)
		{
			if(ets.get(i).x>=200&&ets.get(i).x<=230&&ets.get(i).y>=300&&ets.get(i).y<=330)
			{
				times++;
			}
		}
		if(times==this.ets.size())
		{
			Hero hero=new Hero(200,300,0,2);
			
		}
		
	}
}


