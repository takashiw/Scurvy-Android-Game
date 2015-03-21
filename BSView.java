package com.example.dspritzman.myapplication;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.example.dspritzman.myapplication.BubbleThread;

public class BSView extends SurfaceView {
    private Bitmap bmp;
    private Paint emptyPaint;

    private SurfaceHolder holder;

    private BubbleThread gameLoopThread;

    private int x = 0;
    private int y = 0;

    private boolean firstTime = true;

    /*private int cons = 6;
    private double principle = 4;
    private double principle2 = principle;
    private int principle3;
    private double princMod = .3;
    private double princMod2 = princMod;

    private double principleSize;
    private double debriSize;
    */
    private static int controlScreenWidth = 720;
    private static int controlScreenHeight = 1280;

    private float tx, ty;
    private boolean touched = false;

    boolean isPaused = false;

    private int bx, by;
    private int ox, oy;
    private int dx, dy;

    private int rise, run;

    private int frame;


    public BSView(Context context)
    {
        super(context);
        gameLoopThread = new BubbleThread(this);
        holder = getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {
            @Override

            public void surfaceDestroyed(SurfaceHolder holder)
            {
                boolean retry = true;
                gameLoopThread.setRunning(false);

                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;

                    } catch (InterruptedException e) {

                    }
                }
            }


            @Override

            public void surfaceCreated(SurfaceHolder holder)
            {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,int width, int height) {

            }
        });

        //bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);




        //randWidth = new Random();
        //randHeight = new Random();
        //randQuantity = new Random();


        bx = 0;
        by = 0;
    }



    @Override
    protected
    void onDraw(Canvas canvas)
    {

        if (firstTime == true)
        {
            frame = 0;

            firstTime = false;
        }
        int tempInt = 0;
        //int tempInt2 = 0;
        int tempInt3 = 0;

        canvas.drawColor(Color.WHITE);
        //Draw background image
        //canvas.drawBitmap(0,0, bmp, emptyPaint);


        //bx.setX(bx - run);
        bx = bx -  run;
        //by.setX(by - rise);
        by = by - rise;
        Paint p1 = new Paint();
        p1.setColor(Color.RED);
        canvas.drawRect(100, 100, 100, 100, p1);
        Random test = new Random();
        int z = 0;
        for(int i = 0; i <1; ++i) {
            //z = test.nextInt(14);
            canvas.drawCircle(bx+z, by+ z, 5, p1);
        }

        

        ++frame;
        canvas.drawRect((getWidth() - 600), (getHeight() - 300), getWidth(), getHeight(), p1);

        //Draw Enemy bullets
        //for enemybullets iterator{

        //bx = bullets.getX();
        //by = bullets.getY();
        //rise = bullets.getRise();
        //run = bullets.getRun();

        //bx.setX(bx - run);
        //by.setX(by - rise);

        ///////////////////////////////////////////////////////

        //Draw ship bullets
        //for shipbullets iterator{

        //bx = bullets.getX();
        //by = bullets.getY();
        //rise = bullets.getRise();
        //run = bullets.getRun();

        //bx.setX(bx - run);
       // bx = bx -  run;
        //by.setX(by - rise);
        //by = by - rise;

        //}


        //Draw ship
        //canvas.drawBitmap(somex, somey, bmp, emptyPaint );

        //Draw enemies
        //enemy list iterator
        //canvas.drawBitmap(x,y, bmp, emptyPaint);

        //Draw foreground/waves

        boolean firstTouch = true;
        if(touched){

            bx = getWidth() - 300;
            by = getHeight() - 300;

            dx = (int) tx;
            dy = (int) ty;
            ox = getWidth() - 300;
            oy = getHeight() - 300;

            BulletPhysics physics = new BulletPhysics(ox, oy, dx, dy);
            rise = physics.getRise();
            run = physics.getRun();
        }
        else
        {
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        tx = event.getX();
        ty = event.getY();

        int action = event.getAction();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                touched = true;
                break;
            case MotionEvent.ACTION_MOVE:
                touched = true;
                break;
            case MotionEvent.ACTION_UP:
                touched = false;
                break;
            case MotionEvent.ACTION_CANCEL:
                touched = false;
                break;
            case MotionEvent.ACTION_OUTSIDE:
                touched = false;
                break;
            default:
        }
        return true;
    }

    public void paused(){
        gameLoopThread.setRunning(false);
        Log.d("DEBUG", "PAUSE");
    }

    public void resumed(){
        gameLoopThread.setRunning(true);
        Log.d("DEBUG", "RESUME");
    }
}
