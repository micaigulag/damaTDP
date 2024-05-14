package com.example.dama;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class campoDiGioco extends View {
    private int x=-1;
    private int y=-1;
    private Paint paint = new Paint();
    private final int MARGIN = 5+1;
    private final int BORDI = 34;
    private final int DIM_LATO = 126;
    private Bitmap bitmap;

    public campoDiGioco(Context context) {
        super(context);
        inizializza();
    }

    public campoDiGioco(Context context, AttributeSet attrs) {
        super(context, attrs);
        inizializza();
    }

    public campoDiGioco(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inizializza();
    }

    private void inizializza() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scacchiera);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(Bitmap.createScaledBitmap(bitmap, 1070, 1070, false), MARGIN, MARGIN, null);

        if((x >= 0 && y >= 0) && (x <= 1070 && y <= 1070)){
            paint.setColor(Color.GREEN);
            canvas.drawRect(this.x + (BORDI+MARGIN), this.y + (BORDI+MARGIN), this.x+DIM_LATO + (BORDI+MARGIN), this.y+DIM_LATO + (BORDI+MARGIN), paint);
        }else{
            //continue;
        }

        paint.setColor(Color.RED);
        canvas.drawRect(BORDI + MARGIN,(BORDI+MARGIN),BORDI+DIM_LATO+MARGIN,(BORDI+MARGIN)+DIM_LATO, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // recupero le coordinate del "tocco"
        int tempX;
        int tempY;
        switch (event.getAction()) {

            case (MotionEvent.ACTION_DOWN):
                System.out.println("Azione touch - down");
                tempX = (int) event.getX() - (BORDI+MARGIN);
                tempY = (int) event.getY() - (BORDI+MARGIN);

                this.x = (tempX/DIM_LATO)*DIM_LATO;
                this.y = (tempY/DIM_LATO)*DIM_LATO;

                Log.d("Dama","Coordinate: x" + this.x + ", y: "+this.y);
                invalidate();
                return true;

            case (MotionEvent.ACTION_MOVE):
                return true;

            default:
                return super.onTouchEvent(event);
        }
    }
}