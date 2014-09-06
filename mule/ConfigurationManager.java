package mule;

import gears.GBase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * This is the configuration manager for our MULE game. This will facilitate IO with the data file, and provide specific
 * access to fields within the file instead of requiring a new parse every time you need something from the file.
 * 
 * If you wish to parse the data file yourself it has the following format:
 * Four lines long
 * :Player Name
 * :Last IP Address successfully connected to
 * :Sound Volume(0-100) Music Volume(0, 100)
 * :space separated key codes for the following keys, in order - LEFT, RIGHT, UP, DOWN, ACTION/OKAY, BACK/CANCEL
 * 
 * This is also using that singleton design we learned in class. Not sure if I like it yet.
 * @author Zack
 *
 */
public class ConfigurationManager {
	private static ConfigurationManager instance;
	private String playerName;
	private int raceID;
	private String lastIP;
	private int musicVolume;
	private int soundVolume;
	private int[] keyBindings;
	private File save;

	public static ConfigurationManager getInstance(){
		if(instance == null){
			instance = new ConfigurationManager("Mule.dat");
		}
		return instance;
	}

	private ConfigurationManager(String fileName){
		save = new File(fileName); 
		if(!save.exists()){ 
			try{
				save.createNewFile();
			}catch(Throwable t){
				GBase.log(t.toString());
			}
		}
		refreshDataFromFile();
	}

	/**
	 * This method will overwrite all instance fields with what is found in the file.
	 * If the file is empty the defaults here will be used.
	 */
	public void refreshDataFromFile(){
		try{
			FileReader fr = new FileReader(save);
			BufferedReader br = new BufferedReader(fr);
			playerName = br.readLine();
			String stringRaceID = br.readLine();
			lastIP = br.readLine();
			String s1 = br.readLine();
			String s2 = br.readLine();

			//Set up some default values
			if(playerName == null){
				playerName = "User" + Math.round(Math.random()*3000);
			}
			
			if(stringRaceID == null){
				stringRaceID = "1";
			}

			if(lastIP == null){
				lastIP = "";
			}

			if(s1 == null){
				s1 = "100 100";
			}

			if(s2 == null){
				s2 = "37 39 38 40 88 90";
			}
			
			raceID = Integer.valueOf(stringRaceID);
			
			String[] volumes = s1.split(" ");
			musicVolume = Integer.valueOf(volumes[1]);
			soundVolume = Integer.valueOf(volumes[0]);

			String[] bindings = s2.split(" ");
			keyBindings = new int[6];
			for(int i = 0; i<bindings.length; i++){
				keyBindings[i] = Integer.valueOf(bindings[i]);
			}

			br.close();
			fr.close();
		}catch(Throwable t){
			GBase.log(t.toString());
		}
	}

	public String getPlayerName(){
		return playerName;
	}

	public void setPlayerName(String name){
		playerName = name;
	}
	
	public int getRaceID(){
		return raceID;
	}
	
	public void setRaceID(int ID){
		raceID = ID;
	}

	public int getMusicVolume(){
		return musicVolume;
	}

	public void setMusicVolume(int volume){
		musicVolume = volume;
	}

	public int getSoundVolume(){
		return soundVolume;
	}

	public void setSoundVolume(int volume){
		soundVolume = volume;
	}

	public int[] getkeyBindings(){
		return keyBindings;
	}

	public void setKeyBindings(int[] bindings){
		keyBindings = bindings;
	}

	public void forceSave(){
		try{
			FileWriter writer = new FileWriter(save, false);
			BufferedWriter out = new BufferedWriter(writer);
			out.write(playerName);
			out.newLine();
			out.write(lastIP);
			out.newLine();
			out.write("" + soundVolume + " " + musicVolume);
			out.newLine();
			out.write("" + keyBindings[0] + " " + keyBindings[1] + " " + keyBindings[2] + " " + keyBindings[3] + " " + keyBindings[4] + " " + keyBindings[5]);
			out.flush();
			out.close();
			writer.close();
		}catch(Throwable t){
			GBase.log(t.toString());
		}
	}
}
