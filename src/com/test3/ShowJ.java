package com.test3;

import java.util.*;

public class ShowJ 
{
	int weizhi = 0;
	Vector<EnemyTank> ets=null;
	EnemyTank et=null;
	public ShowJ(Vector<EnemyTank> ets,EnemyTank et)
	{
		this.ets=ets;
		this.et=et;
	}
	
	public int showy()
	{
						
		for(int i=0;i<this.ets.size();i++)
		{
			if(ets.get(i)==et)
			{
				weizhi=i;
			}
		
		}
		return weizhi;
	}

}
