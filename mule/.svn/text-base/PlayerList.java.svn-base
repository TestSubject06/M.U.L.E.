package mule;

import gears.GSprite;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

/**
 * Contains logic for passing the players from lobby to gameState
 * @author Zack
 *
 */
public class PlayerList extends GSprite {
	private ArrayList<PlayerListEntry> players;
	private ArrayList<Button> playerColorButtons;
	private ArrayList<GSprite> playerColors;
	private ArrayList<Button> playerRaceButtons;
	private ArrayList<GSprite> playerRaces;
	public int[] usedColors, usedRaces;
	
	/**
	 * Constructor 
	 * @param x
	 * @param y
	 * @param backing
	 */
	public PlayerList(int x, int y, Image backing){
		super(x, y, backing);
		players = new ArrayList<>();
		playerColors = new ArrayList<>();
		playerColorButtons = new ArrayList<>();
		playerRaces = new ArrayList<>();
		playerRaceButtons = new ArrayList<>();
		usedColors = new int[]{-1, -1, -1, -1};
		usedRaces = new int[]{-1, -1, -1, -1};
	}
	
	/**
	 * Add a player to the list
	 * @param name
	 */
	public void addPlayer(String name){
		int color = 0;
		boolean notDone = true;
		while(notDone){
			notDone = false;
			for(int i = 0; i < 4; i++){
				if(color == usedColors[i]){
					color++;
					notDone = true;
				}
			}
		}
		usedColors[players.size()] = color;
		
		int race = 0;
		usedRaces[players.size()] = race;
		
		GSprite tmp = new GSprite(165, players.size()*25 + 42, null);
		tmp.loadGraphic(AssetManager.lobbyColors, false, 10, 10);
		tmp.setCurIndex(color);
		playerColors.add(tmp);
		playerColorButtons.add(new Button(162, players.size()*25 + 39, AssetManager.playerPropertyButton, new BL(players.size())));
		
		GSprite tmp2 = new GSprite(180, players.size()*25 + 42, null);
		tmp2.loadGraphic(AssetManager.lobbyRaces, false, 10, 10);
		tmp2.setCurIndex(race);
		playerRaces.add(tmp2);
		playerRaceButtons.add(new Button(177, players.size()*25 + 39, AssetManager.playerPropertyButton, new BL2(players.size())));
		
		players.add(new PlayerListEntry(name, color, race, players.size()));
	}
	
	@Override
	public void update(){
		super.update();
		for(Button a : playerColorButtons){
			a.update();
		}
		for(GSprite a : playerColors){
			a.update();
		}
		for(Button a : playerRaceButtons){
			a.update();
		}
		for(GSprite a : playerRaces){
			a.update();
		}
	}
	
	@Override
	public void render(Graphics2D g){
		super.render(g);
		for(Button a : playerColorButtons){
			a.render(g);
		}
		for(GSprite a : playerColors){
			a.render(g);
		}
		for(Button a : playerRaceButtons){
			a.render(g);
		}
		for(GSprite a : playerRaces){
			a.render(g);
		}
		for(PlayerListEntry a : players){
			g.drawString(a.name, 25, (a.playerNumber*25 + 52));
		}
	}
	
	
	/** 
	 * Return size of player array
	 * @return
	 */
	public int getNumberPlayers(){
		return players.size();
	}
	
	/**
	 * Button listener
	 * @author Zack
	 *
	 */
	private class BL implements ButtonListener{
		private int buttonIndex;
		public BL(int index){
			buttonIndex = index;
		}
		public void buttonClicked(){
			int proposedColor = usedColors[buttonIndex] +1;
			boolean notDone = true;
			while(notDone){
				notDone = false;
				for(int i = 0; i < 4; i++){
					if(proposedColor == usedColors[i]){
						proposedColor++;
						notDone = true;
					}
				}
				if(proposedColor > 6){
					proposedColor = 0;
					notDone = true;
				}
			}
			playerColors.get(buttonIndex).setCurIndex(proposedColor);
			usedColors[buttonIndex] = proposedColor;
		}
		
	}
	
	/**
	 * Second button listener
	 * @author Zack
	 *
	 */
	private class BL2 implements ButtonListener{
		private int buttonIndex;
		public BL2(int index){
			buttonIndex = index;
		}
		public void buttonClicked(){
			int proposedRace = usedRaces[buttonIndex] +1;
			if(proposedRace > 9){
				proposedRace = 0;
			}
			playerRaces.get(buttonIndex).setCurIndex(proposedRace);
			usedRaces[buttonIndex] = proposedRace;
		}
		
	}
	
}
