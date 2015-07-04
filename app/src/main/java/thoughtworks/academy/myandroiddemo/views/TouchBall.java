package thoughtworks.academy.myandroiddemo.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wjlin on 7/4/15.
 */
public class TouchBall extends View {

    private float currentX = 40;
    private float currentY = 40;

    Paint paint = new Paint();

    public TouchBall(Context context) {
        super(context);
    }

    public TouchBall(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.CYAN);
        canvas.drawCircle(currentX, currentY, 30, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentX = event.getX();
        currentY = event.getY();

        invalidate();
        return true;
    }
}
