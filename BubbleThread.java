package com.example.dspritzman.myapplication;

import android.annotation.SuppressLint;

/**
 * Created by Dspritzman on 3/21/2015.
 */

    import android.annotation.SuppressLint;
    import android.content.Context;
    import android.graphics.BitmapFactory;
    import android.graphics.Canvas;
    import android.graphics.Color;
    import android.os.Handler;
    import android.util.Log;
    import android.view.SurfaceHolder;

    @SuppressLint("WrongCall")
    public class BubbleThread extends Thread {

        static final long FPS = 60;

        private BSView view;

        private boolean running = false;

        private boolean mPaused;
        private boolean mFinished;


        public BubbleThread(BSView view) {

            this.view = view;
            mPaused = false;
            mFinished = false;

        }



        public void setRunning(boolean run) {

            running = run;
            Log.d("DEBUG", String.valueOf(running));
        }



        @Override

        public void run() {

            long ticksPS = 1000 / FPS;

            long startTime;

            long sleepTime;

            while (running) {

                Canvas c = null;

                startTime = System.currentTimeMillis();

                try {

                    c = view.getHolder().lockCanvas();

                    synchronized (view.getHolder()) {


                        view.onDraw(c);

                    }

                } finally {

                    if (c != null) {

                        view.getHolder().unlockCanvasAndPost(c);

                    }

                }

                sleepTime = (ticksPS-(System.currentTimeMillis() - startTime));

                try {

                    if (sleepTime > 0)

                        sleep(sleepTime);

                    else

                        sleep(0);

                } catch (Exception e) {}

            }

        }


        /**
         * Call this on pause.
         */
        public void onPause() {
            synchronized (view.getHolder()) {
                mPaused = true;
                running = false;
                Log.d("DEBUG", "PAUSE");
            }
        }

        /**
         * Call this on resume.
         */
        protected void onResume() {
            synchronized (view.getHolder()) {
                mPaused = false;
                running = true;
                view.getHolder().notifyAll();
                Log.d("DEBUG", "RESUME");
            }
        }

    }

