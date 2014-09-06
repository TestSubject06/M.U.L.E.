package mule;

import gears.GBase;
import gears.GInput;
import gears.GSprite;
import gears.GState;

import java.awt.Color;
import java.awt.Graphics2D;


/**
 *
 * @author Zack
 */
public class Menu_Options extends GState{
	private int currentSelection = 0;
	private String currentName;
	private GSprite backGround;
	private GSprite selectArrow;
	private GSprite extra;
	
	public Menu_Options(){
		
	}
	
	public Menu_Options(GSprite sprite){
		extra = sprite;
	}
	
	@Override
	public void update() {
		super.update();
		if(GBase.input.justPressed(GInput.ESC)){
			GBase.stateManager.popState();
		}
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
		if(GBase.input.justPressed(GInput.X)){
			switch(currentSelection){
			case 2:
				GBase.stateManager.addState(new Dialog_NameEntry());
				break;
			}
		}
		selectArrow.y = 154 + currentSelection*25;
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);

		g.drawString(currentName, 299, 223);
		g.setColor(Color.black);
		g.setFont(GBase.consoleFont);
	}

	@Override
	public void destroy() {
		ConfigurationManager.getInstance().setPlayerName(currentName);
		ConfigurationManager.getInstance().forceSave();
		super.destroy();
	}

	@Override
	public void create() {
		super.create();
		currentName = ConfigurationManager.getInstance().getPlayerName();
		backGround = new GSprite(178, 137, AssetManager.optionsBG);
		add(backGround);

		selectArrow = new GSprite(160, 154, null);
		selectArrow.loadGraphic(AssetManager.arrow, true, 30, 30);
		selectArrow.addAnimation("default", new int[]{1}, 0, false);
		selectArrow.addAnimation("error", new int[]{0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2}, 20, false);
		selectArrow.play("default");
		add(selectArrow);
		
		if(extra != null){
			add(extra);
		}
	}

	@Override
	public void passBack(String message){
		//The only class that could ever pass back to this right now is the name entry, and that could only ever pass back
		//the name that someone has typed.
		currentName = message;
	}
}
