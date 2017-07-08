package com.test3;

import java.io.*;

public class Recorder 
{
	
		//record the score
		private static int enNum=20;
		//how many lives in game
		private static int mylife=3;
		private static int allEnNum=0;
		
		private static FileWriter fw=null;
		private static BufferedWriter bw=null;
		private static FileReader fr=null;
		private static BufferedReader br=null;
		
		//Save and load function but not finish
		public static void getRecord()
		{
			try 
			{
				fr=new FileReader("c:\\myRecorder.txt");
				br=new BufferedReader(fr);
				String n=br.readLine();
				allEnNum=Integer.parseInt(n);
				enNum-=allEnNum;
								
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				// TODO: handle exception
			}
			finally
			{
				try 
				{
					br.close();
					fr.close();
				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();
					// TODO: handle exception
				}
			}
		}
		
		public static void keepRecord()
		{
			try 
			{
				fw=new FileWriter("c:\\myRecorder.txt");
				bw=new BufferedWriter(fw);
				
				bw.write(allEnNum+"\r\n");
			} 
			catch (Exception e) 
			{
				// TODO: handle exception
				e.printStackTrace();
			}
			finally
			{
				try 
				{
					bw.close();
					fw.close();
				} 
				catch (Exception e2) 
				{
					e2.printStackTrace();
					// TODO: handle exception
				}
			}
		}
					
		public static int getAllEnNum() {
			return allEnNum;
		}
		public static void setAllEnNum(int allEnNum) {
			Recorder.allEnNum = allEnNum;
		}
		public static int getEnNum() {
			return enNum;
		}
		public static void setEnNum(int enMum) {
			Recorder.enNum = enMum;
		}
		public static int getMylife() {
			return mylife;
		}
		public static void setMylife(int mylife) {
			Recorder.mylife = mylife;
		}
		public static void reduceEnNum()
		{
			enNum--;
		}
		public static void killEnNum()
		{
			allEnNum++;
		}
		
}
