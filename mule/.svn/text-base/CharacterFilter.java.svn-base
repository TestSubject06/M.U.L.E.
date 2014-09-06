package mule;

import gears.GBase;
import gears.GInput;

/**
 * This class checks individual characters against a filter string to see if they are valid.
 * @author Daniel
 * 
 */
public class CharacterFilter {
	private String filter;
	private boolean handleShift;

	/**
	 * Creates a new instance of this class, using the string filter you specify.
	 * This filter is a series of allowed characters, for example "0123456789." would allow only
	 * numbers and the period to pass through, and all other characters would get filtered out.
	 * @param filter a string filter, in no particlar order
	 * @param handleShift whether this class should handle the shift key before sending the input back
	 */
	public CharacterFilter(String filter, boolean handleShift){
		this.filter = filter;
		this.handleShift = handleShift;
	}

	/**
	 * Takes in a character as an ascii key code, and checks if it should be allowed or not
	 * If the character is allowed, then the character is returned as a string in the form of
	 * @param keyCode the ascii keycode to check
	 * @return	empty string if the character is not allowed, the character if it is allowed.
	 */
	public String filterCharacter(int keyCode){
		//System.out.println(keyCode);
		//System.out.println((char)keyCode);
		if(!handleShift){
			//System.out.println("shift not pressed");
			if(filter.contains("" + (char)keyCode)){
				//System.out.println("will return");
				//GBase.log(keyCode + "");
				return ""+(char)keyCode;
			}
			else
				//System.out.println("will not return");
				return "";
		}
		else{
			// when handleShift is true
			boolean isShift = GBase.input.isDown(GInput.Shift);
			//If we get in an 'a' character, and the user is holding shift, we need to return it as an 'A'.
			//But we can't just add the shift constant for alpha characters, because if we do then if a user
			//hits say... Page Up, while shift is held down, then we'd add 32 to 33, and get 65 - or 'A'.

			//Unfortunately there are no easy-use formulas that handle this for all keys, so we'll go on a chunk basis

			//Basic alpha characters, easy mode, there's a formula for them.
			if(keyCode >= 65 && keyCode <= 93){
				if(isShift){
					if(filter.contains("" + (char)keyCode)){
						return ""+(char)keyCode;
					}
				}
				else{
					if(filter.contains("" + (char)(keyCode+32))){
						return ""+(char)(keyCode+32);
					}
				}
			}

			// Zack doesn't want hardcoded stuff. so commented this out.
			/*
			if(keyCode == 47){
				return "/";
			}
			 */

			//Numerals... not so much.
			if(keyCode >= 47 && keyCode <= 59){
				if(isShift){
					char suggestedKey = 0;
					//I know, I know... switch statements are bad and all, but when is ASCII ever going to change?
					switch(keyCode){
					case '0':
						suggestedKey = ')';
						break;

					case '1':
						suggestedKey = '!';
						break;

					case '2':
						suggestedKey = '@';
						break;

					case '3':
						suggestedKey = '#';
						break;

					case '4':
						suggestedKey = '$';
						break;

					case '5':
						suggestedKey = '%';
						break;

					case '6':
						suggestedKey = '^';
						break;

					case '7':
						suggestedKey = '&';
						break;

					case '8':
						suggestedKey = '*';
						break;

					case '9':
						suggestedKey = '(';
						break;

					case ';':
						suggestedKey = ':';
						break;

					case '/':
						suggestedKey = '?';
						break;

					}
					if(filter.contains("" + suggestedKey)){
						return ""+suggestedKey;
					}
				}
				// shift not held
				else{
					if(filter.contains("" + (char)keyCode)){
						return ""+(char)keyCode;
					}
				}

			}


		}	
		return "";
	}
}
