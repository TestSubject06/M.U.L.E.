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
/**
 * First state seen by user upon opening game. Gives options to start game
 * @author Zack
 */
public class MainMenu extends GState{
	private int currentSelection = 0;
	private GSprite backGround;
	private GSprite selectArrow;
	public String typed = "";
	@Override
	public void update() {
		super.update();
		if(GBase.input.justPressed(GInput.DownArrow)){
			currentSelection++;
			if(currentSelection > 3){
				currentSelection = 0;
			}
		}
		if(GBase.input.justPressed(GInput.UpArrow)){
			currentSelection--;
			if(currentSelection < 0){
				currentSelection = 3;
			}
		}
		//Base location and offset for the arrow
		selectArrow.y = 264 + (currentSelection*29);
		if(GBase.input.justPressed(GInput.X)){
			switch(currentSelection){
			case 1:
				//Add the dialog to the state stack
				GBase.stateManager.addState(new DialogMultiplayer());
				break;

			case 2:
				GBase.stateManager.addState(new Menu_Options());
				break;

			case 3:
				System.exit(0);
				break;
			default:
				selectArrow.play("error");
				break;
			}
		}
		if(selectArrow.curAnim.name.equals("error") && selectArrow.finished){
			selectArrow.play("default");
		}

	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void create() {
		super.create();
		//this is your real constructor.
		AssetManager.initImages();

		backGround = new GSprite(0, 0, AssetManager.mainMenuBG);
		add(backGround);

		selectArrow = new GSprite(204, 0, null);
		selectArrow.loadGraphic(AssetManager.arrow, true, 30, 30);
		selectArrow.addAnimation("default", new int[]{1}, 0, false);
		selectArrow.addAnimation("error", new int[]{0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2}, 20, false);
		selectArrow.play("default");
		add(selectArrow);
	}

}
