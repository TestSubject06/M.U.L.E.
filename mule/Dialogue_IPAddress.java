package mule;




import gears.GBase;
import gears.GInput;
import gears.GSprite;
import gears.GState;

import java.awt.Color;
import java.awt.Graphics2D;


/**
 * State to allow user to input IP address
 * @author Daniel
 */
public class Dialogue_IPAddress extends GState{
	// private instances
	private GSprite backGround;
	private CharacterFilter filter;

	// this should be the string that represents the IP address


	private String ip;


	// update the state(when user is typing)
	@Override
	public void update() {
		super.update();

		if(GBase.input.justPressed(GInput.ESC)){
			GBase.stateManager.popState();
		}

	}

	@Override
	public void render(Graphics2D g) {
		super.render(g);

		g.drawString(ip, 70, 110);
		g.setColor(Color.black);
		g.setFont(GBase.consoleFont);

	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	// this is the real constructor
	public void create() {
		super.create();
		GBase.input.registerListener(this);
		ip = "";
		// the image size: 715 x 217
		backGround = new GSprite(0, 0, AssetManager.joiningGame);
		add(backGround);
		// only integer and period can go through
		filter = new CharacterFilter("0123456789.", false);
	}


	@Override
	public void keyPress(int keyCode){
		// handle backspace
		if(GBase.input.justPressed(GInput.Backspace) && ip.length() > 0){
			ip = ip.substring(0, ip.length()-1);
		}
		else{
			ip += filter.filterCharacter(keyCode);
		}
	}
}
