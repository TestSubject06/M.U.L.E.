/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mule;

import gears.GBase;
import gears.GInput;
import gears.GSprite;
import gears.GState;

import java.awt.Graphics2D;

import mule.AssetManager;
import mule.Dialogue_IPAddress;
/**
 * State to select between host or join game
 * @author Garrett Mallory
 * @version 1.0
 */
public class DialogMultiplayer extends GState{
	private int currentSelection = 0;
	private GSprite dialogMultiplayerBG;
	private GSprite selectArrow;
	@Override
	public void update() {
		super.update();
		if(GBase.input.justPressed(GInput.DownArrow)){
			currentSelection++;
			if(currentSelection > 1){
				currentSelection = 0;
			}
		}
		if(GBase.input.justPressed(GInput.UpArrow)){
			currentSelection--;
			if(currentSelection < 0){
				currentSelection = 1;
			}
		}

		//Base location and offset for the arrow

		selectArrow.y = 129 + (currentSelection*55);
				if(GBase.input.justPressed(GInput.X)){
					switch(currentSelection){
					case 0:
						GBase.stateManager.newStack(new Menu_Lobby());
						break;
					case 1:
						//Join game selected, move to ip screen
						GBase.stateManager.addState(new Dialogue_IPAddress());
						break;
					}
				}
				if(GBase.input.justPressed(GInput.ESC)){
					GBase.stateManager.popState();
				}
				if(selectArrow.curAnim.name.equals("error") && selectArrow.finished){
					selectArrow.play("default");
				}
	}

	@Override
	public void create() {
		super.create();
		//this is your real constructor.
		dialogMultiplayerBG = new GSprite(160, 120, AssetManager.hostOrJoinBG);
		add(dialogMultiplayerBG);

		selectArrow = new GSprite(262, 124, null);
		selectArrow.loadGraphic(AssetManager.arrow, true, 30, 30);
		selectArrow.addAnimation("default", new int[]{1}, 0, false);
		selectArrow.addAnimation("error", new int[]{0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2}, 20, false);
		selectArrow.play("default");
		add(selectArrow);

	}

}
