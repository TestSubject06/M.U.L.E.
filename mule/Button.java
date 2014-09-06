package mule;

import gears.GBase;
import gears.GPoint;
import gears.GSprite;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

/**
 * Buttons take in a static image that have four states, top to bottom in the following order:
 * Idle 		-The button is being left alone
 * Hovering		-The user has their cursor over the button
 * Held Down	-The user has depressed the button
 * Inactive		-The button is un-clickable
 * @author Zack
 *
 */
public class Button extends GSprite {
	public GPoint helperPoint;
	public boolean buttonActive;
	private ButtonListener listener;
	private GSprite tooltipBacking;
	private String tooltip;
	private double timer;
	/**
	 * Creates a new button
	 * @param x The X location of the button
	 * @param y	The Y location of the button
	 * @param buttonImage The image that the button will use
	 * @param Toggle True if this button is a toggle switch, false if this button is to be a normal button
	 */
	public Button(int x, int y, Image buttonImage, ButtonListener listener, boolean toggle){
		super(x, y, null);
		loadGraphic(buttonImage, false, buttonImage.getWidth(null), buttonImage.getHeight(null)/4);
		_curIndex = 0;
		timer = 0;
		buttonActive = true;
		this.listener = listener;
	}

	public Button(int x, int y, Image buttonImage, ButtonListener listener){
		this(x, y, buttonImage, listener, false);
	}

	@Override
	public void update(){
		if(buttonActive){
			setCurIndex(0);
			//If the mouse is within the bounds of this button
			if(containsPoint(GBase.input.getMouseLocation())){
				if(GBase.input.mouseDown())
					setCurIndex(2);
				else
					setCurIndex(1);
			}
			if(GBase.input.mouseJustReleased() && _curIndex > 0){
				listener.buttonClicked();
			}
		}else{
			setCurIndex(3);
		}
		if(tooltipBacking != null && tooltipBacking.exists){
			tooltipBacking.update();
		}
	}

	@Override
	public void render(Graphics2D g){
		super.render(g);
		if(tooltipBacking != null && tooltipBacking.exists){
			tooltipBacking.render(g);
		}
	}

	/**
	 * This is unfinished. Leave it alone for now.
	 * @param tooltip
	 * @param states
	 */
	public void setTooltip(String tooltip, int[] states){
		this.tooltip = tooltip;
		tooltipBacking = new GSprite(0,0, null);
		tooltipBacking.exists = false;
		tooltipBacking.createImage(GBase.consoleFontMetrics.stringWidth(tooltip) + 4, GBase.consoleFontMetrics.getHeight(), new Color(0xFFFFFFCC, true));
	}
}
