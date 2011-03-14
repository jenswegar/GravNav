package fi.wegar.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.SeekBar;

public class Slider extends SeekBar {

	/**
	 * Constructor. ÊUse this when instantiating view directly from code (not XML)
	 * 
	 * @param context
	 */
	public Slider(Context context) {
		super(context);
	}	
	
	/**
	 * Constructor. Used when inflating from XML layout. 
	 * 
	 * @param context
	 * @param attrs
	 */
	public Slider(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	
}
