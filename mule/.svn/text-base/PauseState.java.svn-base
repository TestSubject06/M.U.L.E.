package mule;

import gears.GBase;
import gears.GInput;
import gears.GSprite;
import gears.GState;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;

public class PauseState extends GState {
	private GSprite background;
	private GSprite arrow;
	private int currentSelection = 0;
	
	public PauseState() {
		passUpdate = true;
		passDraw = true;
	}

	@Override
	public void update(){
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
		arrow.y = 140+45 + (currentSelection*35);
		if(GBase.input.justReleased(GInput.X)){
			switch(currentSelection){
				case 0:
					GBase.stateManager.popState();
				break;
				case 1:
					//Save Game
					GBase.input.resetInput();
	        		FileDialog chooser2 = new FileDialog(new Frame(), "Select location to save to", FileDialog.LOAD);
	        		chooser2.setVisible(true);
        			if(chooser2.getFile() != null){
					    String sb = SaveHelper.getSaveString();
				        try {
				        	String fileName = chooser2.getFile();
				            FileWriter fw = new FileWriter(fileName + (fileName.substring(fileName.length()-4, fileName.length()).equals(".sav")? "":".sav"));
				            fw.write(sb.toString());
				            fw.flush();
				            fw.close();
				        } catch (Exception ex) {
				            ex.printStackTrace();
				        }
			        }
				break;
				
				case 2:
					//Open the options menu
					GBase.stateManager.addState(new Menu_Options());
				break;
				
				case 3:
					GBase.stateManager.newStack(new MainMenu());
					break;
			}
		}
		super.update();
		GBase.input.resetInput();
	}
	
	@Override
	public void create(){
		super.create();
		background = new GSprite(170, 140, AssetManager.smallMenu);
		add(background);
		
		arrow = new GSprite(170+4, 140+45, null);
		arrow.loadGraphic(AssetManager.arrow, true, 30, 30);
		arrow.addAnimation("default", new int[]{1}, 0, false);
		arrow.addAnimation("error", new int[]{0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2}, 20, false);
		arrow.play("default");
		add(arrow);
	}
}
