package mule;

import java.awt.Color;
import java.awt.Graphics2D;

import gears.GBase;
import gears.GInput;
import gears.GSprite;
import gears.GState;

/**
 * A very simple state that just takes in a name and sends it back to the previous state. For use with the options menu.
 * @author Zack
 *
 */
public class Dialog_NameEntry extends GState {
	private GSprite backGround;
	private CharacterFilter filter;
	private String name;
	private double timer;

	@Override
	public void update() {
		super.update();

		if(GBase.input.justPressed(GInput.ESC)){
			GBase.stateManager.popState();
		}
		if(GBase.input.justPressed(GInput.Enter)){
			GBase.stateManager.passBack(name);
			GBase.stateManager.popState();
		}
		if(GBase.input.justPressed(GInput.Backspace) && name.length() > 0){
			name = name.substring(0, name.length()-1);
			timer = 0.5;
		}
		if(GBase.input.isDown(GInput.Backspace)){
			timer -= GBase.elapsedSeconds;
			if(timer <= 0.025){
				timer -= .025;
				name = name.length() > 0 ? name.substring(0, name.length()-1) : "";
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);
		g.drawString(name, 235, 250);
		g.setColor(Color.black);
		g.setFont(GBase.consoleFont);
	}

	@Override
	public void create() {
		super.create();
		GBase.input.registerListener(this);
		name = "";

		backGround = new GSprite(231, 213, AssetManager.optionsName);
		add(backGround);
		// right here
		filter = new CharacterFilter("qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890:;!@#$%^&*()/?", true);
		timer = 0.5;
	}

	@Override
	public void keyPress(int keyCode){
		name += filter.filterCharacter(keyCode);
	}
}
