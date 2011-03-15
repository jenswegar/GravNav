package fi.wegar.android.gravnav.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SpokeView extends View {

	
	private Paint color;
	
	/**
	 * The number of spokes to draw in the view
	 */
	private int mNumSpokes = 5;
	
	/**
	 * The angle in radians by which the entire canvas should be rotated before calculating the direction of the first spoke. 
	 */
	private double mShiftRadians = 0;
	
	/**
	 * The number of the first spoke to be draw on screen. Zero-based, going clockwise.
	 * 
	 */
	private int mDrawFromSpoke = 0;
	
	
	/**
	 * Constructor. ÊUse this when instantiating view directly from code (not XML)
	 * 
	 * @param context
	 */
	public SpokeView(Context context) {
		super(context);
		
		initView();
	}	
	
	/**
	 * Constructor. Used when inflating from XML layout. 
	 * 
	 * @param context
	 * @param attrs
	 */
	public SpokeView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		initView();
	}
	
	private final void initView() {
		
		color = new Paint();
		color.setStrokeWidth(5);
		setColor(0xffffffff);
		
	}
	
	/**
	 * set the color of the spokes
	 * 
	 * @param c The color value in ARGB
	 */
	public void setColor(int c) {
		color.setColor(c);
		
		invalidate();
	}
	
	public int getColor() {
		return color.getColor();
	}

	/**
	 * @param mShiftRadians the mShiftRadians to set
	 */
	public void setShiftRadians(double mShiftRadians) {
		this.mShiftRadians = mShiftRadians;
		
		invalidate();
	}

	/**
	 * @return the mShiftRadians
	 */
	public double getShiftRadians() {
		return mShiftRadians;
	}	
	
	/**
	 * @param mNumSpokes the mNumSpokes to set
	 */
	public void setNumSpokes(int numSpokes) {
		this.mNumSpokes = numSpokes;
		
		invalidate();
	}

	/**
	 * @return the mNumSpokes
	 */
	public int getNumSpokes() {
		return mNumSpokes;
	}	
	
	/**
	 * @param mDrawFromSpoke the mDrawFromSpoke to set
	 */
	public void setDrawFromSpoke(int spokeNr) {
		this.mDrawFromSpoke = spokeNr;
		invalidate();
	}

	/**
	 * @return the mDrawFromSpoke
	 */
	public int getDrawFromSpoke() {
		return mDrawFromSpoke;
	}	
	
	/**
	 * Determine width and height of the view
	 */
	//TODO: figure out how to properly implement this
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	/**
	 * Render the arrow
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		// calculate the vector length
		double centerX = getWidth() / 2;
		double centerY = getHeight() / 2;
		
		double spokeLength = 0;
		if( centerX > centerY) {
			// running in landscape mode, so Y edge will be closer
			spokeLength = centerY * 0.5;
		} else {
			// running in portrait mode, so X edge will be closer
			spokeLength = centerX * 0.5;
		}
		
		double stepSize = 360 / getNumSpokes();
		
		// loop for nr of spokes and draw
		for(int i=getDrawFromSpoke(); i < getNumSpokes(); i++ ) {
			
			// calculate angle of next vector
			double radians = Math.toRadians( stepSize * i );
			
			double arrowX = spokeLength * Math.cos( radians+getShiftRadians() );
			double arrowY = spokeLength * Math.sin( radians+getShiftRadians() );
			
			canvas.drawLine( (float)centerX, (float)centerY, (float) (centerX + arrowX), (float) (centerY + arrowY), color);
		}
	}

	
}