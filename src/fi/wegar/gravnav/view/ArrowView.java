package fi.wegar.gravnav.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import fi.wegar.gravnav.R;

public class ArrowView extends View {
	
	
	private Paint color;
	/**
	 * The ratio by which the arrow length should be determined based on the distance from the center of the drawing surface to the closest edge. 
	 */
	private double mArrowLengthRatio = 0.5;
	
	private float mRadians = 0;
	
	/**
	 * Reference to the arrow graphic used to draw the point of the arrow
	 */
	private Bitmap arrowhead;
	
	/**
	 * The transform matrix used to rotate the arrow head into place
	 */
	private Matrix mRotate;
	
	/**
	 * Constructor. ÊUse this when instantiating view directly from code (not XML)
	 * 
	 * @param context
	 */
	public ArrowView(Context context) {
		super(context);
		
		initView();
	}	
	
	/**
	 * Constructor. Used when inflating from XML layout. 
	 * 
	 * @param context
	 * @param attrs
	 */
	public ArrowView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		initView();
	}
	
	
	private final void initView() {
		
		arrowhead = BitmapFactory.decodeResource(getResources(), R.drawable.arrow_head);
		
		color = new Paint();
		color.setColor(0xffffffff);
		color.setStrokeWidth(5);
		
		mRotate = new Matrix();
		
	}
	
	/**
	 * Set the angle in degrees in which the arrow should be painted
	 * 
	 * @param angle
	 */
	public void setAngle(double angle) {
		
		mRadians = (float) (angle * Math.PI / 180);
		
		invalidate();
	}
	
	public double getAngle() {
		
		return (mRadians * 180) / Math.PI;
	}
	
	/**
	 * Determine height of the view
	 */
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
		
		// calculate the vector
		
		double centerX = getWidth() / 2;
		double centerY = getHeight() / 2;
		

		double arrowLength = 0;
		if( centerX > centerY) {
			// running in landscape mode, so Y edge will be closer
			arrowLength = centerY * mArrowLengthRatio;
		} else {
			// running in portrait mode, so X edge will be closer
			arrowLength = centerX * mArrowLengthRatio;
		}
		
		double arrowX = arrowLength * Math.cos(mRadians);
		double arrowY = arrowLength * Math.sin(mRadians);
		
		canvas.drawLine( (float)centerX, (float)centerY, (float) (centerX + arrowX), (float) (centerY + arrowY), color);
		
		float arrowCenterX = (float) (centerX - arrowhead.getWidth() * 0.5);
		float arrowCenterY = (float) (centerY - arrowhead.getHeight());
		
		mRotate.setRotate( (float) getAngle()+90, (float) (arrowhead.getWidth()*0.5), arrowhead.getHeight());
		mRotate.postTranslate( (float)(arrowCenterX+arrowX), (float)(arrowCenterY+arrowY) );
		
		
		
		canvas.drawBitmap(arrowhead, mRotate, null);
		
	}
}


