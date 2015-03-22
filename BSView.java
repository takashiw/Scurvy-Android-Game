package com.example.dspritzman.myapplication;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
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
    private Bitmap background;
    private Bitmap background1;
    private Bitmap background2;
    private Bitmap background3;

    private Bitmap screen;

    private Bitmap bulletBall;

    private Bitmap waveFront;
    private Bitmap waveFront1, waveFront2, waveFront3;

    private Bitmap waveBack;
    private Bitmap waveBack1, waveBack2, waveBack3;

    private Bitmap pufferFish;
    private Bitmap flyingFish;
    private Bitmap narwhal;
    private Bitmap manatee;
    private Bitmap seahorse;

    private Bitmap boat;
    private ArrayList<Bitmap> enemies = new ArrayList<Bitmap>();
    private int bgX1;
    private int bgX2;
    private int bgX3;

    private int wfY1, wfY2, wfY3;
    private int wfB1, wfB2, wfB3;

    Paint p5 = new Paint();

    private Game myGame = new Game();

    private boolean screenX;



    public BSView(Context context)
    {
        super(context);



        screenX = true;
        bgX1 = 0;
        bgX2 = 2560;
        bgX3 = 5120;

        wfY1 = 0;
        wfY2 = 1904;
        wfY3 = 3808;

        wfB1 = 0;
        wfB2 = -1904;
        wfB3 = -3808;


        //x + 30, y - 30

        pufferFish = BitmapFactory.decodeResource(getResources(), R.drawable.pufferfish);
        pufferFish = Bitmap.createScaledBitmap(pufferFish, 90, 90, false);
        pufferFish = RotateBitmap(pufferFish, 90);


        //x + 50, y - 50
        flyingFish = BitmapFactory.decodeResource(getResources(), R.drawable.flyingfish);
        flyingFish = Bitmap.createScaledBitmap(flyingFish, 100, 100, false);
        flyingFish = RotateBitmap(flyingFish, 90);

        //x + 300, y - 300
        narwhal = BitmapFactory.decodeResource(getResources(), R.drawable.narwhal);
        narwhal = Bitmap.createScaledBitmap(narwhal, 600, 600, false);
        narwhal = RotateBitmap(narwhal, 90);


        //x + 65, y - 65
        manatee = BitmapFactory.decodeResource(getResources(), R.drawable.manatee);
        manatee = Bitmap.createScaledBitmap(manatee, 130, 130, false);
        manatee = RotateBitmap(manatee, 90);

        //x + 50, y - 50
        seahorse = BitmapFactory.decodeResource(getResources(), R.drawable.seahorse);
        seahorse = Bitmap.createScaledBitmap(seahorse, 100, 100, false);
        seahorse = RotateBitmap(seahorse, 90);

        screen = BitmapFactory.decodeResource(getResources(), R.drawable.titlescreen);
        screen = Bitmap.createScaledBitmap(screen, 1280, 720, false);
        screen = RotateBitmap(screen, 90);

        bulletBall = BitmapFactory.decodeResource(getResources(), R.drawable.cannon);
        bulletBall = Bitmap.createScaledBitmap(bulletBall, 15, 15, false);
        bulletBall = RotateBitmap(bulletBall, 90);






        boat = BitmapFactory.decodeResource(getResources(), R.drawable.boat);
        boat = Bitmap.createScaledBitmap(boat, 243, 576, false);
        boat = RotateBitmap(boat, 90);


        //background = BitmapFactory.decodeFile(bname);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        background = Bitmap.createScaledBitmap(background, 2560, 720, false);
        background = RotateBitmap(background, 90);
        background1 = background2 = background3 = background;

        waveFront = BitmapFactory.decodeResource(getResources(), R.drawable.wavefront);
        waveFront = Bitmap.createScaledBitmap(waveFront, 1904, 241, false);
        waveFront = RotateBitmap(waveFront, 90);
        waveFront1 = waveFront2 = waveFront3 = waveFront;

        waveBack = BitmapFactory.decodeResource(getResources(), R.drawable.waveback);
        waveBack = Bitmap.createScaledBitmap(waveBack, 1904, 239, false);
        waveBack = RotateBitmap(waveBack, 90);
        waveBack1 = waveBack2 = waveBack3 = waveBack;


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

        emptyPaint = new Paint();
        //emptyPaint.setAntiAlias(true);


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

        if (!screenX) {
            myGame.tick();


            int tempInt = 0;
            //int tempInt2 = 0;
            int tempInt3 = 0;

            canvas.drawColor(Color.WHITE);
            canvas.drawBitmap(background1, 0, bgX1, emptyPaint);
            canvas.drawBitmap(background2, 0, bgX2, emptyPaint);
            canvas.drawBitmap(background3, 0, bgX3, emptyPaint);

            if (bgX1 < -2559) {
                bgX1 = 5119;
            }
            if (bgX2 < -2559) {
                bgX2 = 5119;
            }
            if (bgX3 < -2559) {
                bgX3 = 5119;
            }

            bgX1 -= 1;
            bgX2 -= 1;
            bgX3 -= 1;


            int goLeft = 200;
            int goRight = 190;
            boolean direction = true;


            canvas.drawBitmap(waveBack1, 15, wfB1, emptyPaint);
            canvas.drawBitmap(waveBack2, 15, wfB2, emptyPaint);
            canvas.drawBitmap(waveBack3, 15, wfB3, emptyPaint);

            if (wfB1 > 1904) {
                wfB1 = -3807;
            }
            if (wfB2 > 1904) {
                wfB2 = -3807;
            }
            if (wfB3 > 1904) {
                wfB3 = -3807;
            }
            //Draw background image
            //canvas.drawBitmap(0,0, bmp, emptyPaint);


            //bx.setX(bx - run);
            bx = bx + run;
            if (run == 0) {
                bx += 1;
            }
            //by.setX(by - rise);
            by = by + rise;
            if (rise == 0) {
                by += 1;
            }
            Paint p1 = new Paint();
            p1.setColor(Color.RED);
            canvas.drawRect(100, 100, 100, 100, p1);
            Random test = new Random();
            int z = 0;
            for (int i = 0; i < 1; ++i) {
                //z = test.nextInt(14);
                // canvas.drawCircle(bx, by, 5, p1);
            }


            ++frame;
            //canvas.drawRect((getWidth() - 600), (getHeight() - 300), getWidth(), getHeight(), p1);

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

            //for (int i = 0; i < myGame.getNarwhalSize(); ++i)
            {
                //canvas.drawBitmap(myGame.narIterator(i).getX(), myGame.narIterator(i).getY())
            }

            for (int i = 0; (i < myGame.getNarwhalSize() && myGame.getNarwhalSize() != 0); ++i) {
                canvas.drawBitmap(narwhal, myGame.narIterator(i).getX(), myGame.narIterator(i).getY(), emptyPaint);
            }

            for (int i = 0; (i < myGame.getManSize() && myGame.getManSize() != 0); ++i) {
                canvas.drawBitmap(manatee, myGame.manIterator(i).getX(), myGame.manIterator(i).getY(), emptyPaint);
                //Log.d("puff", "X: " + myGame.puffIterator(i).getY() + "Y: "  + myGame.puffIterator(i).getX() );
            }

            for (int i = 0; (i < myGame.getPuffSize() && myGame.getPuffSize() != 0); ++i) {
                canvas.drawBitmap(pufferFish, myGame.puffIterator(i).getX(), myGame.puffIterator(i).getY(), emptyPaint);
                //Log.d("puff", "X: " + myGame.puffIterator(i).getY() + "Y: "  + myGame.puffIterator(i).getX() );
            }

            for (int i = 0; (i < myGame.getSeaSize() && myGame.getSeaSize() > 0); ++i) {
                //canvas.drawBitmap(seahorse, myGame.seaIterator(i).getX(), myGame.seaIterator(i).getY(), emptyPaint);
                //Log.d("puff", "X: " + myGame.puffIterator(i).getY() + "Y: "  + myGame.puffIterator(i).getX() );
            }


            for (int i = 0; (i < myGame.getFlyingSize() && myGame.getFlyingSize() != 0); ++i) {
                canvas.drawBitmap(flyingFish, myGame.flyIterator(i).getX(), myGame.flyIterator(i).getY(), emptyPaint);
                //Log.d("puff", "X: " + myGame.puffIterator(i).getY() + "Y: "  + myGame.puffIterator(i).getX() );
            }


            for (int i = 0; (i < myGame.Bullets.size()); ++i) {
                canvas.drawBitmap(bulletBall, myGame.Bullets.get(i).getX(), myGame.Bullets.get(i).getY(), emptyPaint);
            }


            //Draw foreground/waves


            canvas.drawBitmap(boat, 50, 0, emptyPaint);

            canvas.drawBitmap(waveFront1, -20, wfY1, emptyPaint);
            canvas.drawBitmap(waveFront2, -20, wfY2, emptyPaint);
            canvas.drawBitmap(waveFront3, -20, wfY3, emptyPaint);
            if (wfY1 < -1902) {
                wfY1 = 3807;
            }
            if (wfY2 < -1902) {
                wfY2 = 3807;
            }
            if (wfY3 < -1902) {
                wfY3 = 3807;
            }

            if (direction) {
                --goLeft;
                --wfY1;
                --wfY2;
                --wfY3;
                --wfY1;
                --wfY2;
                --wfY3;
                ++wfB1;
                ++wfB2;
                ++wfB3;
            } else {
                --goRight;
                ++wfY1;
                ++wfY2;
                ++wfY3;
                --wfB1;
                --wfB2;
                --wfB3;
            }

            if (goRight == 0) {
                goRight = 190;
                direction = true;
            }
            if (goLeft == 0) {
                direction = false;
                goLeft = 200;
            }

            Bitmap cmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888 );
            Canvas cTest = new Canvas(cmap);

            p5.setTextSize(20);
            p5.setColor(Color.BLACK);
            cTest.drawText(Integer.toString(myGame.getScore()), 50, getHeight() / 2, p5);
            cTest.rotate(90);

            canvas.drawBitmap(cmap, 50, getHeight() / 2, emptyPaint);

            //canvas.drawt

            //bgX1 -= 1;
            //bgX2 -= 1;
            //bgX3 -= 1;

            // myGame.updateBullets();

        }
        else
        {
            canvas.drawBitmap(screen, 0, 0, emptyPaint);
        }
        boolean firstTouch = true;
        if(touched){


            screenX = false;
            bx = 453;
            by = 115;

            dx = (int) tx;
            dy = (int) ty;
            ox = 453;
            oy = 115;

            if (firstTouch) {
                myGame.addBullet(430, 115, dx, dy);
                firstTouch = false;
            }

            /*BulletPhysics physics = new BulletPhysics(ox, oy, dx, dy);
            rise = physics.getRise();
            run = physics.getRun();*/
        }
        else
        {
            firstTouch = true;
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

    public static Bitmap RotateBitmap(Bitmap source, float angle)
    {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
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
