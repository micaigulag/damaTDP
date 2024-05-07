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
import android.view.View;

public class campoDiGioco extends View {

    private Paint paint = new Paint();
    private final int MARGIN = 5;
    private final int BORDI = 35;
    private final int DIM_LATO = 165;


    public campoDiGioco(Context context) {
        super(context);
    }

    public campoDiGioco(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public campoDiGioco(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scacchiera);
        canvas.drawBitmap(Bitmap.createScaledBitmap(bitmap,1070, 1070,false ) , MARGIN, MARGIN, null);

        paint.setColor(Color.GREEN);
        canvas.drawRect((MARGIN + BORDI),(MARGIN + BORDI),165,165,paint);

        paint.setColor(Color.RED);
        canvas.drawRect(DIM_LATO + (MARGIN + BORDI)-30,(MARGIN + BORDI),DIM_LATO*2,DIM_LATO,paint);

        paint.setColor(Color.GREEN);
        canvas.drawRect(DIM_LATO*2 + (MARGIN + BORDI)-30,(MARGIN + BORDI),DIM_LATO*3,DIM_LATO,paint);

    }
}
