package mule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class SaveHelper {
	
	public static int[] storeResources, player1Resource, player2Resource, player3Resource, player4Resource, turnOrder;
	public static int roundNumber, turnNumber, numPlayers, numMules, p1C, p2C, p3C, p4C, p1R, p2R, p3R, p4R, p1M, p2M, p3M, p4M, roundTime, roundHalfTime, roundNoTime;
	public static int[] mapInfo;
	
	public static String getSaveString(){
		String s = "";
		s += numPlayers + " " + roundNumber + " " + turnNumber + " " + roundTime + " " + roundHalfTime + " " + roundNoTime;
		for(int i = 0; i < turnOrder.length; i++){
			s += " " + turnOrder[i];
		}
		s+= "\n";
		
		//Store resource pool
		for(int i = 0; i < storeResources.length; i++){
			s += storeResources[i] + " ";
		}
		s+= numMules + "\n";
		
		//Player pools
		s+= p1C + " " + p1R + " " + p1M + " " + player1Resource[0] + " " + player1Resource[1] + " " + player1Resource[2] + " " + player1Resource[3] +"\n";
		s+= p2C + " " + p2R + " " + p2M + " " + player2Resource[0] + " " + player2Resource[1] + " " + player2Resource[2] + " " + player2Resource[3] +"\n";
		s+= p3C + " " + p3R + " " + p3M + " " + player3Resource[0] + " " + player3Resource[1] + " " + player3Resource[2] + " " + player3Resource[3] +"\n";
		s+= p4C + " " + p4R + " " + p4M + " " + player4Resource[0] + " " + player4Resource[1] + " " + player4Resource[2] + " " + player4Resource[3] +"\n";
		
		for(int i = 0; i < mapInfo.length; i++){
			s+=mapInfo[i] + ((i==mapInfo.length-1)?"\n":" ");
		}
		return s;
	}
	
	public static void loadFieldsFromFile(File file){
		try{
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			//Parse the first line - general game settings.
			String line = br.readLine();
			String[] brokenUp = line.split(" ");
			numPlayers = Integer.parseInt(brokenUp[0]);
			roundNumber = Integer.parseInt(brokenUp[1]);
			turnNumber = Integer.parseInt(brokenUp[2]);
			roundTime = Integer.parseInt(brokenUp[3]);
			roundHalfTime = Integer.parseInt(brokenUp[4]);
			roundNoTime = Integer.parseInt(brokenUp[5]);
			turnOrder = new int[brokenUp.length-6];
			for(int i = 0; i < brokenUp.length-6; i++){
				turnOrder[i] =  Integer.parseInt(brokenUp[i+6]);
			}
			
			//Parse the store resource pool.
			line = br.readLine();
			brokenUp = line.split(" ");
			storeResources = new int[4];
			for(int i = 0; i < 4; i++){
				storeResources[i] = Integer.parseInt(brokenUp[i]);
			}
			numMules = Integer.parseInt(brokenUp[4]);
			
			//Parse the players.
			line = br.readLine();
			brokenUp = line.split(" ");
			p1C = Integer.parseInt(brokenUp[0]);
			p1R = Integer.parseInt(brokenUp[1]);
			p1M = Integer.parseInt(brokenUp[2]);
			player1Resource = new int[4];
			for(int i = 0; i < 4; i++){
				player1Resource[i] = Integer.parseInt(brokenUp[i+3]);
			}
			
			line = br.readLine();
			brokenUp = line.split(" ");
			p2C = Integer.parseInt(brokenUp[0]);
			p2R = Integer.parseInt(brokenUp[1]);
			p2M = Integer.parseInt(brokenUp[2]);
			player2Resource = new int[4];
			for(int i = 0; i < 4; i++){
				player2Resource[i] = Integer.parseInt(brokenUp[i+3]);
			}
			
			line = br.readLine();
			brokenUp = line.split(" ");
			p3C = Integer.parseInt(brokenUp[0]);
			p3R = Integer.parseInt(brokenUp[1]);
			p3M = Integer.parseInt(brokenUp[2]);
			player3Resource = new int[4];
			for(int i = 0; i < 4; i++){
				player3Resource[i] = Integer.parseInt(brokenUp[i+3]);
			}
			
			line = br.readLine();
			brokenUp = line.split(" ");
			p4C = Integer.parseInt(brokenUp[0]);
			p4R = Integer.parseInt(brokenUp[1]);
			p4M = Integer.parseInt(brokenUp[2]);
			player4Resource = new int[4];
			for(int i = 0; i < 4; i++){
				player4Resource[i] = Integer.parseInt(brokenUp[i+3]);
			}
			
			//Get the map tiles.
			line = br.readLine();
			brokenUp = line.split(" ");
			mapInfo = new int[9*5*3];
			for(int i = 0; i < (9*5*3); i++){
				mapInfo[i] = Integer.parseInt(brokenUp[i]);
			}
			
			br.close();
			fr.close();
		}catch(Throwable t){
			//Figure this out later.
		}
	}

}
