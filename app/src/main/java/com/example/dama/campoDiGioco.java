package com.example.dama;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class campoDiGioco extends View {

    private Paint paint = new Paint();
    private final int MARGIN = 5;
    private final int BORDI = 20;
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
        canvas.drawRect((MARGIN + BORDI),(MARGIN + BORDI),120,120,paint);

        paint.setColor(Color.RED);
        canvas.drawRect((MARGIN + BORDI)+100,(MARGIN + BORDI),200,200,paint);

    }
}
