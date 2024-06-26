package com.example.dama;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class campoDiGioco extends View {
    //private int ruoloGioco = 0;
    private int counter;

    private boolean isFinita = false;
    private TextView testoGioco;
    private TextView turnoGioco;
    private int[][] matriceCampo = {
                                {0,1,0,1,0,1,0,1},
                                {1,0,1,0,1,0,1,0},
                                {0,1,0,1,0,1,0,1},
                                {0,0,0,0,0,0,0,0},
                                {0,0,0,0,0,0,0,0},
                                {2,0,2,0,2,0,2,0},
                                {0,2,0,2,0,2,0,2},
                                {2,0,2,0,2,0,2,0},
                            };
    private int x=-1;
    private int y=-1;
    private Paint paint = new Paint();
    private final int MARGIN = 5+1;
    private final int BORDI = 34;
    private final int DIM_LATO = 126;
    private Bitmap bitmap;
    private Bitmap bianca;
    private Bitmap nera;

    private Bitmap damaBianca;
    private Bitmap damaNera;
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
        //setContentView(R.layout.partita2);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.scacchiera);
        bitmap = Bitmap.createScaledBitmap(bitmap, 1070, 1070, false);

        bianca = BitmapFactory.decodeResource(getResources(), R.drawable.bianco);
        bianca = Bitmap.createScaledBitmap(bianca, DIM_LATO-5, DIM_LATO-5, false);

        nera = BitmapFactory.decodeResource(getResources(), R.drawable.nero);
        nera = Bitmap.createScaledBitmap(nera, DIM_LATO-5, DIM_LATO-5, false);

        damaBianca = BitmapFactory.decodeResource(getResources(), R.drawable.dbianco);
        damaBianca = Bitmap.createScaledBitmap(damaBianca, DIM_LATO-5, DIM_LATO-5, false);

        damaNera = BitmapFactory.decodeResource(getResources(), R.drawable.damonenero);
        damaNera = Bitmap.createScaledBitmap(damaNera, DIM_LATO-5, DIM_LATO-5, false);

        this.counter = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(bitmap, MARGIN, MARGIN, null);

        for(int i=0; i<8;i++){
            for(int j=0;j<8;j++){
                switch (matriceCampo[i][j]) {
                    case 1:
                            canvas.drawBitmap(bianca, (BORDI+MARGIN) + DIM_LATO*j,(BORDI+MARGIN) + DIM_LATO*i, null);
                            break;
                    case 2:
                            canvas.drawBitmap(nera,  (BORDI+MARGIN) + DIM_LATO*j,(BORDI+MARGIN) + DIM_LATO*i, null);
                            break;
                    case 3:
                             canvas.drawBitmap(damaBianca,  (BORDI+MARGIN) + DIM_LATO*j,(BORDI+MARGIN) + DIM_LATO*i, null);
                            break;
                    case 4:
                            canvas.drawBitmap(damaNera,  (BORDI+MARGIN) + DIM_LATO*j,(BORDI+MARGIN) + DIM_LATO*i, null);
                            break;
                    default:
                        continue;
                }
            }
        }

        /* NON MI SERVE PIU' IN TEORIA, PERCHE' TUTTE LE OPERAZIONI SONO TRACCIATE NELLA MATRICE
        if((x >= 0 && y >= 0) && (x <= 1070-BORDI && y <= 1070-BORDI)){
            paint.setColor(Color.GREEN);
            canvas.drawBitmap(bianca, this.x + (BORDI+MARGIN), this.y + (BORDI+MARGIN), null);
            //canvas.drawRect(this.x + (BORDI+MARGIN), this.y + (BORDI+MARGIN), this.x+DIM_LATO + (BORDI+MARGIN), this.y+DIM_LATO + (BORDI+MARGIN), paint);
        }else{
            //continue;
        }*/

    }


    public void settingTextView(TextView v){
        this.testoGioco = v;
    }
    public void settingMessageView(TextView v){
        this.turnoGioco = v;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // recupero le coordinate del "tocco"
        int tempX;
        int tempY;

        int currentX;
        int currentY;

        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN):
                System.out.println("Azione touch - down");
                tempX = Math.round(event.getX() - (BORDI+MARGIN));
                tempY = Math.round(event.getY() - (BORDI+MARGIN));

                currentX = tempX-(tempX%DIM_LATO);
                currentY = tempY-(tempY%DIM_LATO);

                currentX=(currentX/DIM_LATO >= 8)? 7 : currentX/DIM_LATO;
                currentY=(currentY/DIM_LATO >= 8)? 7 : currentY/DIM_LATO;

                Log.d("Dama","Coordinate: x: " + currentX + ", y: "+currentY);

                if(!isFinita){
                    switch(counter){
                        case 0:
                            if(matriceCampo[currentY][currentX]%2 == 1){
                                counter++;
                                this.x = currentX;
                                this.y = currentY;
                            }else if(matriceCampo[currentY][currentX]%2 == 0){
                                testoGioco.setText("Pedina selezionata non valida");
                            }else{
                                testoGioco.setText("Selezionare prima una pedina");
                            }
                            break;
                        case 1:
                            if(matriceCampo[currentY][currentX] == 0){
                                if(isValidMove(matriceCampo[this.y][this.x],this.x,this.y,currentX,currentY)){
                                    turnoGioco.setText("Turno pedine nere");
                                    counter++;
                                    //Gestione della promozione e spostamento
                                    matriceCampo[currentY][currentX] = (currentY == 7)? 3 : matriceCampo[this.y][this.x];
                                    matriceCampo[this.y][this.x] = 0;
                                    if(Math.abs(currentY - this.y) == 2 && (currentX - this.x == -2 || currentX - this.x == 2 ) ){
                                        matriceCampo[(currentY+this.y)/2][(currentX+this.x)/2] = 0;
                                        this.x = currentX;
                                        this.y = currentY;
                                        if(canStillEating(matriceCampo[this.y][this.x], this.x,this.y) ){
                                            counter = 4;
                                            testoGioco.setText("Devi mangiare ancora");
                                        }
                                    }
                                }else{
                                    testoGioco.setText("Mossa non valida!");
                                }
                            }else if(matriceCampo[currentY][currentX]%2 == 1){
                                this.x = currentX;
                                this.y = currentY;
                            }else{
                                testoGioco.setText("Selezionare una cella libera!");
                            }
                            break;
                        case 2:
                            if(matriceCampo[currentY][currentX] == 2 || matriceCampo[currentY][currentX] == 4){
                                counter++;
                                this.x = currentX;
                                this.y = currentY;
                            }else if(matriceCampo[currentY][currentX]%2 == 1){
                                testoGioco.setText("Pedina selezionata non valida");
                            }else{
                                testoGioco.setText("Selezionare prima una pedina");
                            }
                            break;
                        case 3:
                            if(matriceCampo[currentY][currentX] == 0){
                                if(isValidMove(matriceCampo[this.y][this.x],this.x,this.y,currentX,currentY)) {
                                    counter = 0;
                                    turnoGioco.setText("Turno pedine bianche");

                                    //Gestione della promozione e spostamento
                                    matriceCampo[currentY][currentX] = (currentY == 0) ? 4 : matriceCampo[this.y][this.x];
                                    matriceCampo[this.y][this.x] = 0;
                                    if (Math.abs(currentY - this.y) == 2 && (currentX - this.x == 2 || currentX - this.x == -2)){
                                        matriceCampo[(currentY + this.y) / 2][(currentX + this.x) / 2] = 0;
                                        this.x = currentX;
                                        this.y = currentY;
                                        if (canStillEating(matriceCampo[this.y][this.x], this.x, this.y)) {
                                            counter = 4;
                                            testoGioco.setText("Devi mangiare ancora");
                                        }
                                    }
                                }else{
                                    testoGioco.setText("Mossa non valida!");
                                }
                            }else if(matriceCampo[currentY][currentX] == 2 || matriceCampo[currentY][currentX] == 4 ){
                                this.x = currentX;
                                this.y = currentY;
                            }else{
                                testoGioco.setText("Selezionare una cella libera!");
                            }
                            break;
                        case 4:
                            //mangiare in catena non fa parte del turno normale
                            if(isValidMove(matriceCampo[y][x],x,y,currentX,currentY)) {
                                if (isMangiabile(matriceCampo[y][x], (currentX + x) / 2, (currentY + y) / 2)) {
                                    matriceCampo[(currentY + y) / 2][(currentX + x)/2] =0;

                                    //Effettuo lo spostamento e possibile upgrade
                                    if( currentY == 7 && matriceCampo[y][x] == 1)
                                        matriceCampo[currentY][currentX] = 3;
                                    else if (currentY == 0 && matriceCampo[y][x] == 2)
                                        matriceCampo[currentY][currentX] = 4;
                                    else
                                        matriceCampo[currentY][currentX] = matriceCampo[y][x];

                                    matriceCampo[y][x] = 0;  //Elimino nel posto vecchio

                                    this.x = currentX;
                                    this.y = currentY;

                                    if( !canStillEating(matriceCampo[y][x],x,y) ){
                                        if(matriceCampo[y][x] == 1 || matriceCampo[y][x] == 3 ){
                                            counter = 2;
                                        }else{
                                            counter = 0;
                                        }
                                    }
                                } else {
                                    testoGioco.setText("Seleziona la giusta pedina da mangiare!");
                                }
                            }
                            break;
                    }
                }
                invalidate();
                return true;

            case (MotionEvent.ACTION_MOVE):
                return true;

            default:
                return super.onTouchEvent(event);
        }
    }


    private boolean canStillEating (int type,int x,int y) {
        if(isMangiabile(type,x-1,y-1))
            return isValidMove(type,x, y,x-2,y-2);
        else if(isMangiabile(type,x-1,y+1))
            return isValidMove(type,x, y,x-2,y+2);
        else if(isMangiabile(type,x+1,y-1))
            return isValidMove(type,x, y,x+2,y-2);
        else{
            if(isMangiabile(type,x+1,y+1)){
                return isValidMove(type,x, y,x+2,y+2);
            }
            return false;
        }
    }

    private boolean isMangiabile(int type,int x,int y){
        int tempType;
        try {
            tempType = matriceCampo[y][x];
        }catch(Exception e){
            return false;
        }
        switch(type){
            case 1:
                if(tempType == 2)
                    return true;
                else
                    return false;
            case 2:
                if(tempType == 1)
                    return true;
                else
                    return false;
            case 3:
                if(tempType == 2 || tempType == 4)
                    return true;
                else
                    return false;
            case 4:
                if(tempType == 1 || tempType == 3)
                    return true;
                else
                    return false;
        }
        return false;
    }

    private boolean isValidMove(int type,int x,int y, int x2,int y2){
        if(x2 < 0 || y2 < 0  || x2 > 7 || y2 >7 )
            return false;
        switch(type){
            case 1:
                if(y2-y == 1 && Math.abs(x2-x) == 1 && matriceCampo[y2][x2] == 0 ){
                    return true;
                }
                if(y2-y == 2 &&  Math.abs(x2-x) == 2 && matriceCampo[y2][x2] == 0 && matriceCampo[(y2+y)/2][(x2+x)/2] == 2){
                    return true;
                }
                return false;
            case 2:
                if(y2-y == -1 && Math.abs(x2-x) == 1 && matriceCampo[y2][x2] == 0 ){
                    return true;
                }
                if(y2-y == -2 &&  Math.abs(x2-x) == 2 && matriceCampo[y2][x2] == 0 && matriceCampo[(y2+y)/2][(x2+x)/2] == 1){
                    return true;
                }
                return false;
            case 3:
                if(Math.abs(y2-y) == 1 && Math.abs(x2-x) == 1 && matriceCampo[y2][x2] == 0 ){
                    return true;
                }
                if(Math.abs(y2-y)== 2 &&  Math.abs(x2-x) == 2 && matriceCampo[y2][x2] == 0 && (matriceCampo[(y2+y)/2][(x2+x)/2] == 2 || matriceCampo[(y2+y)/2][(x2+x)/2] == 4 )){
                    return true;
                }
                return false;
            case 4:
                if(Math.abs(y2-y) == 1 && Math.abs(x2-x) == 1 && matriceCampo[y2][x2] == 0 ){
                    return true;
                }
                if(Math.abs(y2-y) == 2 &&  Math.abs(x2-x) == 2 && matriceCampo[y2][x2] == 0 && (matriceCampo[(y2+y)/2][(x2+x)/2] == 1 || matriceCampo[(y2+y)/2][(x2+x)/2] == 3 )){
                    return true;
                }
                return false;
        }
        return false;
    }
    private void isFinished() {
        int pBianche = 0;
        int pNere = 0;

        for(int i=0; i < 8; i++){
            for(int j=0; j < 8; j++){
                if( matriceCampo[x][y] != 0 && matriceCampo[x][y] % 2 == 0 )
                    pNere++;
                else if(matriceCampo[x][y] != 0 && matriceCampo[x][y] % 2 == 1)
                    pBianche++;
            }
        }
        if(pNere == 0){
            isFinita = true;
        }else if(pBianche == 0){
            isFinita = true;
        }
    }
}