/**
 * 
 */
package fi.wegar.gravnav;

import android.R;
import android.app.Application;
import android.content.res.Resources;

/**
 * Generates a text from a given direction integer, telling the user which direction to go. Integers must range from 1 to X
 * 
 * @author jenswegar
 *
 */
public class DirectionToTextConverter {

	static final String FIRST = "st";
	static final String SECOND = "nd";
	static final String THIRD = "rd";
	static final String N_TH = "th";
		
	/**
	 * 
	 * @param dir
	 * @param numChoices
	 * @return A String representing the direction to travel, e.g. 4th choice from the left
	 */
	public static String getText(int dir, int numChoices) {
		
		String rtnStr = "Go ";
		
		
		if( numChoices == 2 ) {
			// special case, we'll use a switch to tell where to go
			rtnStr += getChoiceFromTwo(dir);
		} else if(numChoices == 3){
			// special case, we'll use a switch to tell where to go
			rtnStr += getChoiceFromThree(dir);
		} else {
			// need to calculate the center
			
			String leftRightCenter = "";
			
			int center = (int) Math.floor(numChoices / 2) + 1;
			
			// check if dir is below or above center, determines left or right direction
			leftRightCenter = (center > dir) ? "left" : "right";

			
			
			if(numChoices % 2 > 0 && dir == center) {
				// the center direction was chosen
				leftRightCenter = "straight";
			} else {
				
				int tmpDir = dir;
				
				if(dir > center) {
					// we're on the right side of straight, so need to invert the tmpDir
					tmpDir = (numChoices+1) - dir;
				}
				
				leftRightCenter = getDirSuffix( tmpDir )+" to your "+leftRightCenter;
			}

			rtnStr += leftRightCenter;
			
		}
		
		
		return rtnStr;
	}

	/**
	 * Returns one of the constants FIRST, SECOND, THIRD, N_TH based on the given order value
	 * @param order
	 * @return
	 */
	private static String getDirSuffix(int order) {
		String rtnStr = ""+order;
		
		switch(order) {
			case 1:
				rtnStr += FIRST;
				break;
			case 2:
				rtnStr += SECOND;
				break;
			case 3:
				rtnStr += THIRD;
				break;
			default:
				rtnStr += N_TH;
				break;
			}
		
		return rtnStr;
	}


	private static String getChoiceFromThree(int dir) {
		
		String rtnStr = "";
		
		switch(dir) {
			case 1:
				rtnStr = "left";
				break;
			case 2:
				rtnStr = "straight";
				break;
			case 3:
				rtnStr = "right";
				break;
		}
		
		return rtnStr;
	}
	
	private static String getChoiceFromTwo(int dir) {
		
		String rtnStr = "";
		
		switch(dir) {
		case 1:
			rtnStr = "left";
			break;
		case 2:
			rtnStr = "right";
			break;
		}
		
		return rtnStr;
	}
	
}
